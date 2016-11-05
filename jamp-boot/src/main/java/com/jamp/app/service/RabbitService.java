package com.jamp.app.service;

import org.springframework.amqp.AmqpException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jamp.app.domain.User;

public interface RabbitService {
	void createLoginEvent(String eventType, String userName) throws AmqpException, JsonProcessingException;
	
	void createPageLoadEvent(String url, User user) throws AmqpException, JsonProcessingException;
}
