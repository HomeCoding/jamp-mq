package com.jamp.app.service.impl;

import java.util.Date;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamp.app.domain.LogEvent;
import com.jamp.app.domain.PageLoadEvent;
import com.jamp.app.domain.User;
import com.jamp.app.service.RabbitService;

@Service
public class RabbitServiceImpl implements RabbitService {
	
    @Autowired
    private AmqpTemplate template;
    
    @Autowired 
    private ObjectMapper objectMapper;

	@Override
	public void createLoginEvent(String eventType, String userName) throws AmqpException, JsonProcessingException {
        LogEvent logEvent = new LogEvent();
        logEvent.setEventType(eventType);
        logEvent.setUserName(userName);
        logEvent.setDate(new Date());
        
        template.convertAndSend("loginQ", objectMapper.writeValueAsString(logEvent));
 	}

	@Override
	public void createPageLoadEvent(String url, User user) throws AmqpException, JsonProcessingException {
		PageLoadEvent event = new PageLoadEvent();
		event.setDate(new Date());
		event.setUrl(url);
		event.setUser(user);
		
		template.convertAndSend("pageLoadQ", objectMapper.writeValueAsString(event));
	}
}
