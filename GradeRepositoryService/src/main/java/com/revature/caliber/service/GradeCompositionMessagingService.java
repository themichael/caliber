package com.revature.caliber.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.model.SimpleTrainee;

@Service
public class GradeCompositionMessagingService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static final String SINGLE_ASSESSMENT_ROUTING_KEY = "F82jS9KJpwqLk3dj";
	private static final String LIST_ASSESSMENT_ROUTING_KEY = "7c6tMqRRPpZ8Z7xh";
	private static final String SINGLE_TRAINEE_ROUTING_KEY = "JyoH3uRmktGn9MnW";
	private static final String SINGLE_TRAINEE_LIST_ROUTING_KEY = "eRQ7GaBRnHgGdV9D";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	
	/**
	 * Makes a json request for a simple trainee for the 
	 * findOne method
	 * 
	 * @param traineeId
	 * @return
	 */
	
	public SimpleTrainee sendSimpleTraineeRequest(Integer traineeId) {
		JsonObject traineeRequest = new JsonObject();
		
		traineeRequest.addProperty("methodName", "findOne");
		traineeRequest.addProperty("traineeId", traineeId);
		
		return (SimpleTrainee) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_TRAINEE_ROUTING_KEY, traineeRequest.toString());
	}
	
	/**
	 * Makes a json request for a simple assessment for the 
	 * findOne method
	 * 
	 * @param assessmentId
	 * @return
	 */
	public SimpleAssessment sendSimpleAssessmentRequest(Long assessmentId) {
		JsonObject assessmentRequest = new JsonObject();
		
		assessmentRequest.addProperty("methodName", "findOne");
		assessmentRequest.addProperty("assessmentId", assessmentId);
		
		return (SimpleAssessment) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_ASSESSMENT_ROUTING_KEY, assessmentRequest.toString());
	}
	
	
	/**
	 * Makes a json request for a list of simple trainee in a batch for the 
	 * findAllByBatchId method
	 * 
	 * @param batchId
	 * @return
	 */
	public List<SimpleTrainee> sendSimpleFindAllByBatchIdRequest(Integer batchId) {
		JsonObject traineeByBatchRequest = new JsonObject();
		
		traineeByBatchRequest.addProperty("methodName", "findAllByBatchId");
		traineeByBatchRequest.addProperty("batchId", batchId);
		
		return (List<SimpleTrainee>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_TRAINEE_LIST_ROUTING_KEY, traineeByBatchRequest.toString());
	}
	
	
	/**
	 * Makes a json request for a list of simple assessments in a category for the 
	 * findByCategoryId method
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<SimpleAssessment> sendListAssessmentRequest(Integer categoryId) {
		JsonObject assessmentRequest = new JsonObject();
		
		assessmentRequest.addProperty("methodName",	"findByCategoryId");
		assessmentRequest.addProperty("categoryId", categoryId);
		
		return (List<SimpleAssessment>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, LIST_ASSESSMENT_ROUTING_KEY, assessmentRequest.toString());
	}


	public SimpleTrainee sendSimpleTraineeRequest(String traineeResourceId) {
		JsonObject traineeRequest = new JsonObject();
		
		traineeRequest.addProperty("methodName", "findOneByResourceId");
		traineeRequest.addProperty("resourceId", traineeResourceId);
		
		return (SimpleTrainee) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_TRAINEE_ROUTING_KEY, traineeRequest.toString());
	}
	
}
