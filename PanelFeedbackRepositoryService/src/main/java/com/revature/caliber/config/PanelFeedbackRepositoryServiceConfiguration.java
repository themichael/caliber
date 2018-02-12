package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.caliber.service.PanelFeedbackCompositionService;

@Configuration
public class PanelFeedbackRepositoryServiceConfiguration {
	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		return new RabbitTemplate(factory);
	}
	
	@Bean
	public PanelFeedbackCompositionService panelFeedbackCompositionService() {
		return new PanelFeedbackCompositionService();
	}
}
