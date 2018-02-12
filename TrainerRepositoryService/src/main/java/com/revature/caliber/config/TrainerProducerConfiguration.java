package com.revature.caliber.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.caliber.service.TrainerCompositionService;

@Configuration
public class TrainerProducerConfiguration {
	@Autowired
	private TrainerCompositionService mms;

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setExchange("revature.caliber.repos");
		return new RabbitTemplate(factory);
	}

	@Bean
	public TrainerCompositionService trainerCompositionService() {
		return new TrainerCompositionService();
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> {

		};
	}

}
