package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleCategory;

@Service
public class AssessmentCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	private static final String SINGLE_BATCH_ROUTING_KEY = "XLNbCWqQzFHr9JfZ";
	private static final String SINGLE_CATEGORY_ROUTING_KEY = "utMPxDus2M9qy9Bh";
	private static final String SINGLE_GRADE_ROUTING_KEY = "aYF4wPtsGMjq72Lu";

	/**
	 * Sends a request for a SimpleBatch to the Batch service identified by a
	 * batchId
	 * 
	 * @param batchId
	 *            The batchId that identifies the SimpleBatch
	 * @return The SimpleBatch returned by the Batch service
	 */
	public SimpleBatch sendSingleSimpleBatchRequest(Integer batchId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findOne");
		batchRequest.addProperty("batchId", batchId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}

	/**
	 * Sends a request for a SimpleCategory to the Category service identified by a
	 * categoryId
	 * 
	 * @param categoryId
	 *            The categoryId that identifies the SimpleCategory
	 * @return The SimpleCategory returned by the Category service
	 */
	public SimpleCategory sendSingleSimpleCategoryRequest(Integer categoryId) {
		JsonObject categoryRequest = new JsonObject();

		categoryRequest.addProperty("methodName", "findOne");
		categoryRequest.addProperty("categoryId", categoryId);

		return (SimpleCategory) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_CATEGORY_ROUTING_KEY,
				categoryRequest.toString());
	}

	/**
	 * Sends a request to delete a set of Grades based of their parent Assessment
	 * Parent Assessment found using assessmentId
	 * 
	 * @param assessmentId
	 *            The assessmentId that identifies the Assessment
	 */
	public void sendGradeDeleteRequestForAssessmentId(Long assessmentId) {
		JsonObject gradeDeleteRequest = new JsonObject();

		gradeDeleteRequest.addProperty("methodName", "delete");
		gradeDeleteRequest.addProperty("assessmentId", assessmentId);

		rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_GRADE_ROUTING_KEY,
				gradeDeleteRequest.toString());
	}

	/**
	 * Sends a request for a SimpleBatch to the Batch service identified by
	 * resourceId
	 * 
	 * @param resourceId
	 *            The resourceId that identifies the SimpleBatch
	 * @return The SimpleBatch returned by the Batch service
	 */
	public SimpleBatch sendSingleSimpleBatchRequest(String resourceId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findOneByResourceId");
		batchRequest.addProperty("resourceId", resourceId);

		return (SimpleBatch) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}

	/**
	 * Sends a request for a SimpleCategory to the Category service identified by a
	 * category
	 * 
	 * @param category
	 *            The category that identifies the SimpleCategory
	 * @return The SimpleCategory returned by the Category service
	 */
	public SimpleCategory sendSingleSimpleCategoryRequest(String category) {
		JsonObject catRequest = new JsonObject();

		catRequest.addProperty("methodName", "findOneBySkillCategory");
		catRequest.addProperty("category", category);

		return (SimpleCategory) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_CATEGORY_ROUTING_KEY,
				catRequest.toString());
	}

}