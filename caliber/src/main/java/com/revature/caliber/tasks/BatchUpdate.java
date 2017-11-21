package com.revature.caliber.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.SalesforceDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

public class BatchUpdate {
	

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
		System.out.println("Update Batch Task");
		List<Batch> salesforceBatches = salesforceDao.getAllRelevantBatches();
		List<Batch> caliberBatches = batchDao.findAll();
		//List<Batch> notSalesforceBatch = batchDao.findAll();
		
		compareBatches(caliberBatches,salesforceBatches);
		
		System.out.println("End of Update Task");
		
	}
	
	public void compareBatches(List<Batch> caliberBatches,List<Batch> salesforceBatches) {
		for(int sIndex=0;sIndex<salesforceBatches.size();sIndex++) {
			String sResourceId = salesforceBatches.get(sIndex).getResourceId();
			//int sResourceId = salesforceBatch.get(sIndex).getBatchId();
			for(int cIndex=0;cIndex<caliberBatches.size();cIndex++) {
				String cResourceId = caliberBatches.get(cIndex).getResourceId();
				if(cResourceId == null) {
					continue;
				}
				//int cResourceId = caliberBatch.get(cIndex).getBatchId();
				if(cResourceId.equals(sResourceId)) {
					System.out.println("Caliber batch: "+cResourceId+"Salesforce Batch: "+sResourceId);
					Batch caliberBatch = caliberBatches.get(cIndex);
					Batch salesforceBatch = salesforceBatches.get(sIndex);
					if(!caliberBatch.getTrainer().getEmail().equals(salesforceBatch.getTrainer().getEmail())) {
						caliberBatch.getTrainer().getBatches().remove(caliberBatch);
						trainerDao.update(caliberBatch.getTrainer());
						caliberBatch.setTrainer(salesforceBatch.getTrainer());
						caliberBatch.getTrainer().getBatches().add(caliberBatch);
						trainerDao.update(caliberBatch.getTrainer());
						batchDao.update(caliberBatch);
					}
					if(!caliberBatch.getCoTrainer().getEmail().equals(salesforceBatch.getCoTrainer().getEmail())) {
						caliberBatch.getCoTrainer().getBatches().remove(caliberBatch);
						caliberBatch.setCoTrainer(salesforceBatch.getCoTrainer());
						batchDao.update(caliberBatch);
					}
					Set<Trainee> salesforceTrainees = salesforceBatch.getTrainees();
					Set<Trainee> caliberTrainees = caliberBatch.getTrainees();
					if(caliberTrainees.containsAll(salesforceTrainees) && caliberTrainees.size() ==  salesforceTrainees.size()) {
						continue;
					} else {
						updateTrainees(caliberTrainees,salesforceTrainees);
					}
				}
			}
		}
	}
	
	/* 
	 * 	Compare the set of Trainees in Caliber with the Trainees retrieved from Salesforce
	 * 	and update the Caliber information if a change occurred
	 */
	private void updateTrainees(Set<Trainee> caliberTrainees,Set<Trainee> salesforceTrainees) {
		System.out.println("Update Trainees");
		Iterator<Trainee> cIt = caliberTrainees.iterator();
		
		while(cIt.hasNext()) {
			Trainee cTrainee = cIt.next();
			//int cResourceId = cTrainee.getTraineeId();
			String cResourceId = cTrainee.getResourceId();
			Iterator<Trainee> sIt = salesforceTrainees.iterator();
			while(sIt.hasNext()) {
				Trainee sTrainee = sIt.next();
				//int sResourceId = sTrainee.getTraineeId();
				String sResourceId = sTrainee.getResourceId();
				if(cResourceId.equals(sResourceId)) {
					if(!cTrainee.getName().equals(sTrainee.getName())) {
						cTrainee.setName(sTrainee.getName());
					}
					if(!cTrainee.getEmail().equals(sTrainee.getEmail())) {
						cTrainee.setEmail(sTrainee.getEmail());
					}
					if(!cTrainee.getPhoneNumber().equals(sTrainee.getPhoneNumber())) {
						cTrainee.setPhoneNumber(sTrainee.getPhoneNumber());
					}
					if(!cTrainee.getTrainingStatus().equals(sTrainee.getTrainingStatus())) {
						cTrainee.setTrainingStatus(sTrainee.getTrainingStatus());
					}
					traineeDao.update(cTrainee);
					System.out.println("Save Trainee updates");
				}
				
			}
			
		}
	}
}