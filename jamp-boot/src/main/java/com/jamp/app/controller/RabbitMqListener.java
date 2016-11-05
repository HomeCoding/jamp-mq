package com.jamp.app.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
	Logger logger = Logger.getLogger(RabbitMqListener.class);

	@RabbitListener(queues = "loginQ")
	public void processLoginQ(String message) {
		logger.info("Received from loginQ: " + message);
	}

	@RabbitListener(queues = "pageLoadQ")
	public void processPageLoadQ(String message) {
		logger.info("Received from pageLoadQ: " + message);
	}
}
