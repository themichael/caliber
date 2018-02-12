package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.SimplePanelFeedback;

@Service
public class PanelFeedbackRepositoryMessagingService {

	@Autowired
	private PanelFeedbackRepositoryRequestDispatcher panelFeedbackRepositoryRequestDispatcher;
	
	/**
	 * Receives a message from the SimplePanelFeedback RabbitMQ queue, parses the message string as a JsonObject,
	 * and passes it to the request dispatcher.
	 * @param message The message received from the messaging queue
	 * @return The simple panel feedbacks returned by the request dispatcher
	 */
	@RabbitListener(queues = "revature.caliber.repos.panelfeedback")
	public SimplePanelFeedback receiveSingleSimplePanelFeedbackRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return panelFeedbackRepositoryRequestDispatcher.processSingleSimplePanelFeedbackRequest(request);
	}

	/**
	 * Receives a message from the list SimplePanelFeedback RabbitMQ queue, parses the message string as a JsonObject,
	 * and passes it to the request dispatcher.
	 * @param message The message received from the messaging queue
	 * @return The list of simple panel feedbacks returned by the request dispatcher
	 */
	@RabbitListener(queues = "revature.caliber.repos.panelfeedback.list")
	public List<SimplePanelFeedback> receiveListSimplePanelFeedbackRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return panelFeedbackRepositoryRequestDispatcher.processListSimplePanelFeedbackRequest(request);
	}
}
