package com.revature.caliber.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.caliber.model.Grade;
import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.repository.GradeRepository;

@Service
public class GradeRepositoryMessagingService {
	
	@Autowired
	private GradeRepositoryRequestDispatcher gradeRepositoryRequestDispatcher;
	
	
	/**
	 * Listens for a request for a grade list and will parse the message. 
	 * The message will be sent to repository request dispatcher for processing
	 * and will return a list of grades.
	 * 
	 * @param message
	 * @return 
	 */
	@RabbitListener(queues = "revature.caliber.repos.grade.list")
	public List<SimpleGrade> receiveList(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return gradeRepositoryRequestDispatcher.processListSimpleGradeRequest(request);

	}
	
	
	/**
	 * Listens for a request for a grade and will parse the message. 
	 * The message will be sent to repository request dispatcher for processing
	 * and will return the grade.
	 * 
	 * @param message
	 * @return 
	 */
	@RabbitListener(queues = "revature.caliber.repos.grade")
	public SimpleGrade receive(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return gradeRepositoryRequestDispatcher.processSingleSimpleGradeRequest(request);
	}
	
	/**
	 * Listens for a request for a service grade list and will parse the message. 
	 * The message will be sent to repository request dispatcher for processing
	 * and will return a list of grades.
	 * 
	 * @param message
	 * @return 
	 */
	@RabbitListener(queues = "revature.caliber.service.grade.list")
//	@RabbitListener(queues = "revature.caliber.service.test.list")
	public List<Grade> recieveComplex(String message) {
		System.out.println(message);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		return gradeRepositoryRequestDispatcher.processListComplexGradeRequest(request);
	}
	
	@RabbitListener(queues = "revature.caliber.dto.grade")
	public Grade receiveGradeDTORequest(String message) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();
		
		return gradeRepositoryRequestDispatcher.processGradeDTORequest(request);
	}
}
