package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.messaging.SalesforceMessageSender;
import com.revature.caliber.repository.SalesforceDAO;

@Service
public class SalesforceService {

	private static final Logger log = Logger.getLogger(SalesforceService.class);
	
	SalesforceDAO salesforceDAO = new SalesforceDAO();
	SalesforceMessageSender messageSender = new SalesforceMessageSender();

	//TODO messages to all dao services to get necessary information
	
	/**
	 * FIND ALL CURRENT SALESFORCE BATCHES
	 * 
	 * @return List of Batches
	 */
	public List<Batch> getAllRelevantBatches() {
		log.debug("Find all current batches by year");
		List<Batch> allSalesForceBatches = salesforceDAO.getAllRelevantBatches();
		JsonObject getAllBatches = new JsonObject();
		getAllBatches.addProperty("methodName", "findAll");
		List<Batch> allCaliberBatches = messageSender.sendToBatch(getAllBatches, "batchList");

		// load trainer and co-trainer from Caliber DB
		Map<String, Trainer> trainerMap = loadTrainers();
		for (Batch batch : allSalesForceBatches) {
			batch.setTrainer(trainerMap.get(batch.getTrainer().getEmail()));
			batch.setCoTrainer(trainerMap.get(batch.getCoTrainer().getEmail()));
			log.debug(batch.getTrainer());
			log.debug(batch.getCoTrainer());
		}
		
		// Removing batches already in Caliber database
		for (int cIndex = 0; cIndex < allCaliberBatches.size(); cIndex++) {
			String cResourceId = allCaliberBatches.get(cIndex).getResourceId();
			if (cResourceId == null) {
				continue;
			}
			for (int sfIndex = 0; sfIndex < allSalesForceBatches.size(); sfIndex++) {
				String sfResourceId = allSalesForceBatches.get(sfIndex).getResourceId();
				if (cResourceId.equals(sfResourceId)) {
					allSalesForceBatches.remove(sfIndex);
					break;
				}
			}
		}

		return allSalesForceBatches;
	}

	private Map<String, Trainer> loadTrainers() {
		JsonObject getAllTrainers = new JsonObject();
		getAllTrainers.addProperty("methodName", "findAll");
		List<Trainer> trainers = messageSender.sendToTrainer(getAllTrainers, "trainerList");
		Map<String, Trainer> trainerMap = new HashMap<>();
		for (Trainer t : trainers) {
			trainerMap.putIfAbsent(t.getEmail(), t);
		}
		return trainerMap;
	}

	/**
	 * FIND ALL TRAINEES
	 * 
	 * @return List of Trainees
	 */

	public List<Trainee> getAllTraineesFromBatch(String resourceId) {
		log.debug("Find all trainees");
		return salesforceDAO.getBatchDetails(resourceId);
	}
	
	/**
	 * Debug method to check Salesforce data
	 * @return
	 */
	public String logBatches(){
		String message = salesforceDAO.getSalesforceResponseString();
		log.warn(message);
		return message;
	}

	/**
	 * Debug method to check Salesforce data
	 * @return
	 */
	public String logBatches(String resourceId){
		String message = salesforceDAO.getSalesforceTraineeResponseString(resourceId);
		log.warn(message);
		return message;
	}
	
}
