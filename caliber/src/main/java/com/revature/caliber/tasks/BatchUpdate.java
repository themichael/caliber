package com.revature.caliber.tasks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.SalesforceDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

@Component
public class BatchUpdate {
	
	private static final Logger log=Logger.getLogger(BatchUpdate.class);

	@Autowired
	private SalesforceAuth salesforceAuth;
	@Autowired
	private SalesforceDAO salesforceDao;
	@Autowired
	private BatchDAO batchDao;
	@Autowired
	private TraineeDAO traineeDao;
	@Autowired
	private TrainerDAO trainerDao;

	/*
	 * Test Method: Used cron to perform midnight execution 
	 * 				To update batches
	 */
	
	//@Scheduled(cron = "0 0 0 * * *")  //Midnight
	@Scheduled(cron = "0 0/30 * 1/1 * ?") 	//Every minute (testing)
	public void updateBatchTask() {
		
		log.info("Update Batch Task");
		boolean userSet = salesforceAuth.setUser();
		if(userSet) {
			List<Batch> salesforceBatches = salesforceDao.getAllRelevantBatches();
			List<Batch> caliberBatches = batchDao.findAll();
			
			compareBatches(caliberBatches,salesforceBatches);
		} else {
			log.error("Unable to perform BatchUpdate");
		}
		
		salesforceAuth.clearUser();
		log.info("End of Update Task");
	}
	
	/*
	 *	Compares a Batch from Caliber with it's SalesForce data (based on the Resource id)
	 *	and updates the Caliber Batch if a change has occurred
	 *
	 *	Junit tests will fail unless you comment out all of the DAO update lines in the function
	 */
	public boolean compareBatches(List<Batch> caliberBatches,List<Batch> salesforceBatches) {
		boolean batchUpdated = false;
		for(int sIndex=0;sIndex<salesforceBatches.size();sIndex++) {
			String sResourceId = salesforceBatches.get(sIndex).getResourceId();
			for(int cIndex=0;cIndex<caliberBatches.size();cIndex++) {
				String cResourceId = caliberBatches.get(cIndex).getResourceId();
				if(cResourceId == null) {
					PopulateResourceId.getBatchResourceId(caliberBatches.get(cIndex), salesforceBatches);
					cResourceId = caliberBatches.get(cIndex).getResourceId();
				}
				if(cResourceId != null && cResourceId.equals(sResourceId)) {
					log.debug("Comparing Caliber batch: "+cResourceId+" to Salesforce Batch: "+sResourceId);
					log.info("Comparing Caliber batch: "+cResourceId+" to Salesforce Batch: "+sResourceId);
					Batch caliberBatch = caliberBatches.get(cIndex);
					Batch salesforceBatch = salesforceBatches.get(sIndex);
					
					batchUpdated = updateBatches(caliberBatch,salesforceBatch);
					
					Set<Trainee> salesforceTrainees = salesforceBatch.getTrainees();
					Set<Trainee> caliberTrainees = caliberBatch.getTrainees();
					
					log.info("Update Caliber Trainees");
					boolean traineeUpdate = updateTrainees(caliberTrainees,salesforceTrainees);
					batchUpdated = batchUpdated ? true : traineeUpdate;
				}
			}
		}
		return batchUpdated;
	}
	
	private boolean updateBatches(Batch caliberBatch,Batch salesforceBatch) {
		boolean batchUpdated = false;
		if(!caliberBatch.getTrainer().getEmail().equals(salesforceBatch.getTrainer().getEmail())) {
			log.info("Update Caliber Trainer");
			
			Set<Batch> trainerBatches = salesforceBatch.getTrainer().getBatches();
			if(trainerBatches != null) {
				salesforceBatch.getTrainer().getBatches().add(salesforceBatch);
				log.info("Add to Trainer Batches");
			} else {
				trainerBatches = new HashSet<>();
				trainerBatches.add(caliberBatch);
				salesforceBatch.getTrainer().setBatches(trainerBatches);
				log.info("Create new set of batches");
			}
			
			caliberBatch.setTrainer(salesforceBatch.getTrainer());
			trainerDao.update(caliberBatch.getTrainer());
			batchDao.update(caliberBatch);
			batchUpdated = true;
		}
		if(caliberBatch.getCoTrainer() != null && 
				!caliberBatch.getCoTrainer().getEmail().equals(salesforceBatch.getCoTrainer().getEmail())) {
			caliberBatch.getCoTrainer().getBatches().remove(caliberBatch);
			caliberBatch.setCoTrainer(salesforceBatch.getCoTrainer());
			batchDao.update(caliberBatch);
			batchUpdated = true;
		}
		
		return batchUpdated;
	}
	
	/* 
	 * 	Compare the set of Trainees in Caliber with the Trainees retrieved from Salesforce
	 * 	and update the Caliber information if a change occurred
	 * 
	 * 	Junit tests will fail unless you comment out all of the DAO update lines in the function
	 */
	public boolean updateTrainees(Set<Trainee> caliberTrainees,Set<Trainee> salesforceTrainees) {
		log.debug("Update Trainees");
		Iterator<Trainee> cIt = caliberTrainees.iterator();
		boolean traineeUpdated = false;
		
		while(cIt.hasNext()) {
			Trainee cTrainee = cIt.next();
			String cResourceId = cTrainee.getResourceId();
			if(cResourceId == null) {
				PopulateResourceId.getTraineeResourceId(cTrainee, salesforceTrainees);
				cResourceId = cTrainee.getResourceId();
			}
			if(cResourceId != null) {
				Iterator<Trainee> sIt = salesforceTrainees.iterator();
				boolean update = false;
				while(sIt.hasNext()) {
					Trainee sTrainee = sIt.next();
					update = checkTraineeChange(cTrainee,sTrainee);
				}
				if(update) {
					traineeUpdated = true;
				}
			}
		}
		return traineeUpdated;
	}
	
	private boolean checkTraineeChange(Trainee cTrainee, Trainee sTrainee) {
		boolean traineeUpdated = false;
		
		String cResourceId = cTrainee.getResourceId();
		String sResourceId = sTrainee.getResourceId();
		
		if(cResourceId.equals(sResourceId)) {
			if(!cTrainee.getName().equals(sTrainee.getName())) {
				cTrainee.setName(sTrainee.getName());
				traineeUpdated = true;
			}
			if(!cTrainee.getEmail().equals(sTrainee.getEmail())) {
				cTrainee.setEmail(sTrainee.getEmail());
				traineeUpdated = true;
			}
			if(!cTrainee.getPhoneNumber().equals(sTrainee.getPhoneNumber())) {
				cTrainee.setPhoneNumber(sTrainee.getPhoneNumber());
				traineeUpdated = true;
			}
			if(!cTrainee.getTrainingStatus().equals(sTrainee.getTrainingStatus())) {
				cTrainee.setTrainingStatus(sTrainee.getTrainingStatus());
				traineeUpdated = true;
			}
			if(traineeUpdated) {
				traineeDao.update(cTrainee);
				return traineeUpdated;
			}
			
			log.debug(cTrainee);
			
		}
		
		return traineeUpdated;
	}
	
}