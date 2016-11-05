package com.jamp.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jamp.app.domain.User;
import com.jamp.app.domain.UserCreateForm;
import com.jamp.app.service.RabbitService;
import com.jamp.app.service.UserService;
import com.jamp.app.validator.UserCreateFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class UserController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;
    private final RabbitService rabbit;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator, RabbitService rabbit) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
        this.rabbit = rabbit;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id, HttpServletRequest request) throws AmqpException, JsonProcessingException {
        LOGGER.debug("Getting user page for user={}", id);
        
        Optional<User> user = userService.getUserById(id);
        
        rabbit.createPageLoadEvent(request.getRequestURI(), user.get());
        rabbit.createLoginEvent("login", user.get().getEmail());
        
        return new ModelAndView("user", "user", user
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        
        System.out.println("create new user");
        
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        // ok, redirect
        return "redirect:/users";
    }

}
