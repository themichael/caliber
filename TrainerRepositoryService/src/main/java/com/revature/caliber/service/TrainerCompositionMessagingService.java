package com.revature.caliber.service;

import java.util.List;
import java.util.Set;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleBatch;

@Service
public class TrainerCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	private static final String LIST_BATCH_ROUTING_KEY = "BSVihZkuxwdg9Dxy";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";

	/**
	 * Send a message to Batch to find all batches associated with a given trainer id
	 *
	 * @param trainerId
	 *
	 * @return List of SimpleBatch
	 */
	public List<SimpleBatch> sendSingleSimpleBatchRequest(Integer trainerId) {
		JsonObject batchRequest = new JsonObject();

		batchRequest.addProperty("methodName", "findAllByTrainerId");
		batchRequest.addProperty("trainerId", trainerId);

		return (List<SimpleBatch>) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, LIST_BATCH_ROUTING_KEY,
				batchRequest.toString());
	}
}