package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.SalesforceDAO;
import com.revature.caliber.data.TrainerDAO;

@Service
public class SalesforceService {

	private static final Logger log = Logger.getLogger(SalesforceService.class);

	@Autowired
	private SalesforceDAO salesforceDAO;
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private HashMap<Integer, Trainer> trainers;
	@Autowired 
	private TrainerDAO trainerDAO;
	
	public void setSalesforceDAO(SalesforceDAO salesforceDAO) {
		this.salesforceDAO = salesforceDAO;
	}

	/**
	 * FIND ALL TRAINERS AND MATCH WITH EMAILS
	 * 
	 * Return List of Trainers
	 */
	public List<Trainer> getAllTrainers(){
		List<Trainer> allCaliberTrainers = trainerDAO.findAll();
		for (int cIndex = 0; cIndex < allCaliberTrainers.size(); cIndex++) {
			String cEmails = allCaliberTrainers.get(cIndex).getEmail();
			if (cEmails == null) {
				continue;
			}
			for (int sfIndex = 0; sfIndex < allCaliberTrainers.size(); sfIndex++) {
				if(cEmails.equals(trainers.get(sfIndex))) {
					break;
				}
			}
			
		}
		return allCaliberTrainers;
	}
	
	/**
	 * FIND ALL CURRENT SALESFORCE BATCHES
	 * 
	 * @return List of Batches
	 */
	public List<Batch> getAllRelevantBatches() {
		log.debug("Find all current batches by year");
		List<Batch> allSalesForceBatches = salesforceDAO.getAllRelevantBatches();
		List<Batch> allCaliberBatches = batchDAO.findAll();
		
		//Removing batches already in Caliber database
		for (int cIndex = 0; cIndex < allCaliberBatches.size(); cIndex++) {
			String cResourceId = allCaliberBatches.get(cIndex).getResourceId();
			if (cResourceId == null) {
				continue;
			}
			for (int sfIndex = 0; sfIndex < allSalesForceBatches.size(); sfIndex++) {
				String sfResourceId = allSalesForceBatches.get(sfIndex).getResourceId();
				if(cResourceId.equals(sfResourceId)) {
					allSalesForceBatches.remove(sfIndex);
					break;
				}
			}
			
		}

		return allSalesForceBatches;
	}

	
	/**
	 * FIND ALL TRAINEES
	 * @return List of Trainees
	 */
	
	public List<Trainee> getAllTraineesFromBatch(String resourceId){
		log.debug("Find all trainees");
		return salesforceDAO.getBatchDetails(resourceId);
	}
	

}
