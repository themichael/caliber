package com.revature.caliber.messaging;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;

@Service
public class SalesforceMessageSender {
	
	public SalesforceMessageSender() {}

	@Autowired
	AmqpTemplate rabbitMqTemplate;
	
	public List<Trainer> sendToTrainer(JsonObject msg, String queue) {
		//rabbitMqTemplate.convertAndSend("caliber.exchange", "caliber.queue", msg);
		return (List<Trainer>)rabbitMqTemplate.convertSendAndReceive("revature.caliber.service", queue, msg);
	}
	
	public List<Batch> sendToBatch(JsonObject msg, String queue) {
		//rabbitMqTemplate.convertAndSend("caliber.exchange", "caliber.queue", msg);
		return (List<Batch>)rabbitMqTemplate.convertSendAndReceive("revature.caliber.service", queue, msg);
	}
	
	
}