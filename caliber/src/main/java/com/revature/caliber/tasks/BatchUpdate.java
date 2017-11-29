package com.revature.caliber.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.SalesforceDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

public class BatchUpdate {
	
	private static final Logger log=Logger.getLogger(BatchUpdate.class);

	@Autowired
	SalesforceDAO salesforceDao;
	@Autowired
	BatchDAO batchDao;
	@Autowired
	TraineeDAO traineeDao;
	@Autowired
	TrainerDAO trainerDao;

	/*
	 * Test Method: Used cron to perform midnight execution 
	 * 				To update batches
	 */
	
	//@Scheduled(cron = "0 0 0 * * *")  //Midnight
	@Scheduled(cron = "0 * * * * *") 	//Every minute (testing)
	public void updateBatchTask() {
		//Update job goes here
		log.debug("Update Batch Task");
		List<Batch> salesforceBatches = salesforceDao.getAllRelevantBatches();
		List<Batch> caliberBatches = batchDao.findAll();
		//List<Batch> notSalesforceBatches = batchDao.findAll();
		
		compareBatches(caliberBatches,salesforceBatches);
		
		log.debug("End of Update Task");
		
	}
	
	/*
	 *	Compares a Batch from Caliber with it's SalesForce data (based on the Resource id)
	 *	and updates the Caliber Batch if a change has occurred
	 */
	public boolean compareBatches(List<Batch> caliberBatches,List<Batch> salesforceBatches) {
		boolean batchUpdated = false;
		for(int sIndex=0;sIndex<salesforceBatches.size();sIndex++) {
			String sResourceId = salesforceBatches.get(sIndex).getResourceId();
			//int sResourceId = salesforceBatches.get(sIndex).getBatchId();
			for(int cIndex=0;cIndex<caliberBatches.size();cIndex++) {
				String cResourceId = caliberBatches.get(cIndex).getResourceId();
				if(cResourceId == null) {
					continue;
				}
				//int cResourceId = caliberBatches.get(cIndex).getBatchId();
				if(cResourceId.equals(sResourceId)) {
					log.debug("Comparing Caliber batch: "+cResourceId+" to Salesforce Batch: "+sResourceId);
					log.info("Comparing Caliber batch: "+cResourceId+" to Salesforce Batch: "+sResourceId);
					Batch caliberBatch = caliberBatches.get(cIndex);
					Batch salesforceBatch = salesforceBatches.get(sIndex);
					if(!caliberBatch.getTrainer().getEmail().equals(salesforceBatch.getTrainer().getEmail())) {
						caliberBatch.getTrainer().getBatches().remove(caliberBatch);
						trainerDao.update(caliberBatch.getTrainer());
						caliberBatch.setTrainer(salesforceBatch.getTrainer());
						caliberBatch.getTrainer().getBatches().add(caliberBatch);
						trainerDao.update(caliberBatch.getTrainer());
						//batchDao.update(caliberBatch);
						batchUpdated = true;
					}
					if(caliberBatch.getCoTrainer() != null) {
						if(!caliberBatch.getCoTrainer().getEmail().equals(salesforceBatch.getCoTrainer().getEmail())) {
							caliberBatch.getCoTrainer().getBatches().remove(caliberBatch);
							caliberBatch.setCoTrainer(salesforceBatch.getCoTrainer());
							//batchDao.update(caliberBatch);
							batchUpdated = true;
						}
					}
					
					Set<Trainee> salesforceTrainees = salesforceBatch.getTrainees();
					Set<Trainee> caliberTrainees = caliberBatch.getTrainees();
					if(caliberTrainees.containsAll(salesforceTrainees) && caliberTrainees.size() ==  salesforceTrainees.size()) {
						continue;
					} else {
						updateTrainees(caliberTrainees,salesforceTrainees);
						batchUpdated = true;
					}
				}
			}
		}
		return batchUpdated;
	}
	
	/* 
	 * 	Compare the set of Trainees in Caliber with the Trainees retrieved from Salesforce
	 * 	and update the Caliber information if a change occurred
	 */
	public boolean updateTrainees(Set<Trainee> caliberTrainees,Set<Trainee> salesforceTrainees) {
		log.debug("Update Trainees");
		Iterator<Trainee> cIt = caliberTrainees.iterator();
		boolean traineeUpdated = false;
		
		while(cIt.hasNext()) {
			Trainee cTrainee = cIt.next();
			//int cResourceId = cTrainee.getTraineeId();
			String cResourceId = cTrainee.getResourceId();
			if(cResourceId != null) {
				Iterator<Trainee> sIt = salesforceTrainees.iterator();
				while(sIt.hasNext()) {
					Trainee sTrainee = sIt.next();
					//int sResourceId = sTrainee.getTraineeId();
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
							//traineeDao.update(cTrainee);
						}
						
						log.debug(cTrainee);
						
					}
				}
			}
		}
		return traineeUpdated;
	}
}