package com.jamp.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jamp.app.service.RabbitService;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RabbitService rabbit;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error, HttpServletRequest request) throws AmqpException, JsonProcessingException {
        LOGGER.debug("Getting login page, error={}", error);
        
        rabbit.createPageLoadEvent(request.getRequestURI(), null);
        
        return new ModelAndView("login", "error", error);
    }

}
