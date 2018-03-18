package com.revature.caliber.tasks;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.SalesforceDAO;
import com.revature.caliber.data.TraineeDAO;

@Component
public class BatchUpdate {

	private static final Logger log = Logger.getLogger(BatchUpdate.class);

	@Autowired
	private SalesforceAuth salesforceAuth;
	@Autowired
	private SalesforceDAO salesforceDao;
	@Autowired
	private BatchDAO batchDao;
	@Autowired
	private TraineeDAO traineeDao;

	/*
	 * Test Method: Used cron to perform midnight execution To update batches
	 */

	// @Scheduled(cron = "0 0/5 * * * ?") //Every 5 minutes (testing)
	@Scheduled(cron = "0 0 0 * * *") // Midnight
	public void updateBatchTask() {

		log.info("Update Batch Task");
		boolean userSet = salesforceAuth.setUser();
		if (userSet) {
			List<Batch> caliberBatches = batchDao.findAll();
			log.info("Caliber Batch list size: " + caliberBatches.size());
			List<Batch> salesforceBatches = salesforceDao.getAllRelevantBatches();

			compareBatches(caliberBatches, salesforceBatches);
		} else {
			log.error("Unable to perform BatchUpdate");
		}

		salesforceAuth.clearUser();
		log.info("End of Update Task");
	}

	/*
	 * Compares a Batch from Caliber with it's SalesForce data (based on the
	 * Resource id) and updates the Caliber Batch if a change has occurred
	 *
	 * Junit tests will fail unless you comment out all of the DAO update lines in
	 * the function
	 */
	public boolean compareBatches(List<Batch> caliberBatches, List<Batch> salesforceBatches) {
		for (int sIndex = 0; sIndex < salesforceBatches.size(); sIndex++) {
			for (int cIndex = 0; cIndex < caliberBatches.size(); cIndex++) {
				// if resourceIds are same, update all the datas with fresh Salesforce data
				if (caliberBatches.get(cIndex).getResourceId().equals(salesforceBatches.get(sIndex).getResourceId())) {
					// extract salesforce data and save
					updateBatch(caliberBatches.get(cIndex), salesforceBatches.get(sIndex));
					for(Trainee trainee : caliberBatches.get(cIndex).getTrainees()) {
						for(Trainee salesforceTrainee : salesforceBatches.get(sIndex).getTrainees()) {
							if(trainee.getResourceId().equals(salesforceTrainee.getResourceId())) {
								// extract salesforce data and save
								updateTrainee(trainee, salesforceTrainee);
							}
						}
					}
				}
			}
		}
		return true;
	}

	private void updateTrainee(Trainee caliberTrainee, Trainee salesforceTrainee) {
		caliberTrainee.setTrainingStatus(salesforceTrainee.getTrainingStatus());
		caliberTrainee.setCollege(salesforceTrainee.getCollege());
		caliberTrainee.setDegree(salesforceTrainee.getDegree());
		caliberTrainee.setEmail(salesforceTrainee.getEmail());
		caliberTrainee.setMajor(salesforceTrainee.getMajor());
		caliberTrainee.setName(salesforceTrainee.getName());
		caliberTrainee.setPhoneNumber(salesforceTrainee.getPhoneNumber());
		caliberTrainee.setProjectCompletion(salesforceTrainee.getProjectCompletion());
		caliberTrainee.setRecruiterName(salesforceTrainee.getRecruiterName());
		caliberTrainee.setTechScreenerName(salesforceTrainee.getTechScreenerName());
		traineeDao.update(caliberTrainee);
	}

	private void updateBatch(Batch caliberBatch, Batch salesforceBatch) {
		caliberBatch.setTrainer(salesforceBatch.getTrainer());
		caliberBatch.setCoTrainer(salesforceBatch.getCoTrainer());
		caliberBatch.setEndDate(salesforceBatch.getEndDate());
		caliberBatch.setSkillType(salesforceBatch.getSkillType());
		caliberBatch.setStartDate(salesforceBatch.getStartDate());
		caliberBatch.setTrainingName(salesforceBatch.getTrainingName());
		caliberBatch.setTrainingType(salesforceBatch.getTrainingType());
		batchDao.update(caliberBatch);
	}

}
