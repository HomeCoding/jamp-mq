package eu.kielczewski.example.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.kielczewski.example.domain.LogEvent;


@Controller
public class HomeController {
	
    @Autowired
    private AmqpTemplate template;
    
    @Autowired 
    private ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String getHomePage() {
        LOGGER.debug("Getting home page");
        
        LogEvent logEvent = new LogEvent();
        logEvent.setEventType("login");
        logEvent.setUserName("some@mal");
        logEvent.setDate(new Date());
        
        try {
        	template.convertAndSend("queue1", objectMapper.writeValueAsString(logEvent));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}        
        
        
        return "home";
    }

}
