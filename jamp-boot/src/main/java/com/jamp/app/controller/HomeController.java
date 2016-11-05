package com.jamp.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jamp.app.service.RabbitService;



@Controller
public class HomeController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private RabbitService rabbit;
    
    @RequestMapping("/")
    public String getHomePage(HttpServletRequest request) throws AmqpException, JsonProcessingException {
        LOGGER.debug("Getting home page");
        rabbit.createPageLoadEvent(request.getRequestURI(), null);
        return "home";
    }

}
