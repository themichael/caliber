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
	
	//@Scheduled(cron = "0 0 0 * * *")  //Midnight
	@Scheduled(cron = "0 * * * * *") 	//Every minute (testing)
	public void updateBatchTask() {
		//Update job goes here
		
		//List<Batch> salesforceBatch = salesforceDao.getAllRelevantBatches();
		List<Batch> caliberBatch = batchDao.findAll();
		List<Batch> notSalesforceBatch = batchDao.findAll();
		caliberBatch.get(0).getTrainees().add(new Trainee());
		
		for(int sIndex=0;sIndex<notSalesforceBatch.size();sIndex++) {
			//String sResourceId = notSalesforceBatch.get(sIndex).getResourceId();
			int sResourceId = notSalesforceBatch.get(sIndex).getBatchId();
			for(int cIndex=0;cIndex<caliberBatch.size();cIndex++) {
				//String cResourceId = caliberBatch.get(cIndex).getResourceId();
				int cResourceId = caliberBatch.get(cIndex).getBatchId();
				if(cResourceId == sResourceId) {
					Batch cBatch = caliberBatch.get(cResourceId);
					Batch sBatch = notSalesforceBatch.get(sResourceId);
					if(!cBatch.getTrainer().getEmail().equals(sBatch.getTrainer().getEmail())) {
						cBatch.setTrainer(sBatch.getTrainer());
						batchDao.update(cBatch);
					}
					if(!cBatch.getCoTrainer().getEmail().equals(sBatch.getCoTrainer().getEmail())) {
						cBatch.setCoTrainer(sBatch.getCoTrainer());
						batchDao.update(cBatch);
					}
					Set<Trainee> salesforceTrainees = sBatch.getTrainees();
					Set<Trainee> caliberTrainees = cBatch.getTrainees();
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
			int cResourceId = cTrainee.getTraineeId();
			boolean found = false;
			Iterator<Trainee> sIt = salesforceTrainees.iterator();
			while(sIt.hasNext()) {
				Trainee sTrainee = sIt.next();
				int sResourceId = sTrainee.getTraineeId();
				if(cResourceId == sResourceId) {
					found = true;
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
				}
				
			}
			
		}
	}
}