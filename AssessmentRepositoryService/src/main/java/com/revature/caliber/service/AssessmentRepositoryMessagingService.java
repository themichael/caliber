package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.SimpleAssessment;

@Service
public class AssessmentRepositoryMessagingService {

	@Autowired
	private AssessmentRepositoryRequestDispatcher assessmentRepositoryRequestDispatcher;
	
	@Autowired
	private AssessmentCompositionDispatcher acd;

	
	/**
	 * Receives a message from the single SimpleAssessment RabbitMQ queue, parses
	 * the message string as a JsonObject, and passes it to the request dispatcher.
	 * 
	 * @param message
	 *            The message received from the messaging queue
	 * @return The SimpleAssessment returned by the request dispatcher
	 */
	@RabbitListener(queues = "revature.caliber.repos.assessment")
	public SimpleAssessment receiveSingleSimpleAssessmentRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return assessmentRepositoryRequestDispatcher.processSingleSimpleAssessmentRequest(request);
	}

	/**
	 * Receives a message from the list SimpleAssessment RabbitMQ queue, parses the
	 * message string as a JsonObject, and passes it to the request dispatcher.
	 * 
	 * @param message
	 *            The message received from the messaging queue
	 * @return The list of SimpleAssessments returned by the request dispatcher
	 */
	@RabbitListener(queues = "revature.caliber.repos.assessment.list")
	public List<SimpleAssessment> receiveListSimpleAssessmentRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return assessmentRepositoryRequestDispatcher.processListSimpleAssessmentRequest(request);
	}
	
	@RabbitListener(queues = "revature.caliber.service.assessment.list")
//	@RabbitListener(queues = "revature.caliber.service.test.list")
	public List<Assessment> receiveListAssessmentRequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return acd.processAssessmentRequest(request);
	}
	/**
	 * Receives a message from the single Assessment RabbitMQ queue, parses the
	 * message string as a JsonObject, and passes it to the request dispatcher.
	 * 
	 * @param message
	 *            The message received from the messaging queue
	 * @return The Assessment returned by the request dispatcher
	 */
	@RabbitListener(queues = "revature.caliber.dto.assessment")
	public Assessment receiveAssessmentDTORequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return assessmentRepositoryRequestDispatcher.processAssessmentDTORequest(request);
	}
}
