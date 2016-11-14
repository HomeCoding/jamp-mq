package com.jamp.app.controller;


import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamp.app.domain.LogEvent;
import com.jamp.app.domain.PageLoadEvent;
import com.jamp.app.repository.LogEventRepository;
import com.jamp.app.repository.PageLoadRepository;

@EnableRabbit
@Component
public class RabbitMqListener {
    @Autowired 
    private ObjectMapper objectMapper;
    
    @Autowired
    private PageLoadRepository pageRepo;
    
    @Autowired
    private LogEventRepository logRepo;
	
	@RabbitListener(queues = "loginQ")
	public void processLoginQ(String message) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Received from loginQ: " + message);
		
		LogEvent logEvent = objectMapper.<LogEvent> readValue(message, LogEvent.class);
		System.out.println("Parse object: " + logEvent);
		
		logRepo.save(logEvent);
	}

	@RabbitListener(queues = "pageLoadQ")
	public void processPageLoadQ(String message) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Received from pageLoadQ: " + message);
		
		PageLoadEvent pageLoadEvent = objectMapper.<PageLoadEvent> readValue(message, PageLoadEvent.class);
		System.out.println("Parse object: " + pageLoadEvent);
		
		pageRepo.save(pageLoadEvent);
		
	}
}
