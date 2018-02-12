package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.Trainer;
import com.revature.caliber.repository.TrainerRepository;

@Service
public class TrainerRepositoryRequestDispatcher {

	@Autowired
	private TrainerRepository trainerRepository;
	
	@Autowired
	private TrainerCompositionService trainerService;
	
	/**
	 * Parse JsonObject for method to execute
	 * Executable methods: 
	 * 		findOne - find a SimpleTrainer by trainerId
	 * 		delete - delete a Trainer by trainerId
	 *
	 * @param request
	 *
	 * @return result
	 */
	public SimpleTrainer processSingleSimpleTrainerRequest(JsonObject request) {
		SimpleTrainer result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			Integer trainerId = request.get("trainerId").getAsInt();
			result = trainerRepository.findOne(trainerId);
		} else if(methodName.equals("delete")) {
			trainerRepository.delete(request.get("trainerId").getAsInt());
			result = null;
		}
		
		return result;
	}
	
	/**
	 * Parse JsonObject for method to execute
	 * Executable methods: 
	 * 		findOne - find all SimpleTrainer
	 *
	 * @param request
	 *
	 * @return result
	 */
	public List<SimpleTrainer> processListSimpleTrainerRequest(JsonObject request) {
		List<SimpleTrainer> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = trainerRepository.findAll();
		}
		
		return result;
	}

	/**
	 * Parse JsonObject for method to execute
	 * Executable methods: 
	 * 		findByEmail - find a Trainer by email
	 *
	 * @param request
	 *
	 * @return result
	 */
	public Trainer processSingleTrainerRequest(JsonObject request) {
		Trainer result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findByEmail")) {
			result = trainerService.findByEmail(request.get("email").getAsString());
		}
		
		return result;
	}
}
