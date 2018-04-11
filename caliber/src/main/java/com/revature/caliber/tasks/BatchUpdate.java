package com.revature.caliber.tasks;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.SalesforceDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;
import com.revature.caliber.services.SalesforceService;

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
	@Autowired
	private TrainerDAO trainerDao;

	/**
	 * Used cron to perform midnight execution To update batches
	 */
	@Scheduled(cron = "0 0/15 * * * ?") // Every 30 minutes
	@Scheduled(cron = "0 0 0 * * *") // Midnight
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateBatchTask() {
		try {
			log.info("Update Batch Task");
			boolean userSet = salesforceAuth.setUser();
			if (userSet) {
				List<Batch> caliberBatches = batchDao.findAll();
				log.debug("Caliber Batch list size: " + caliberBatches.size());
				List<Batch> salesforceBatches = salesforceDao.getAllBatches();

				compareBatches(caliberBatches, salesforceBatches);
			} else {
				log.error("Unable to perform BatchUpdate");
			}

			salesforceAuth.clearUser();
			log.info("End of Update Task");
		} catch (Exception e) {
			log.fatal(e);
		}
	}

	/**
	 * Grabs all batches from Salesforce and all batches from Caliber. For each of
	 * the Caliber batches, it checks against all the Salesforce batches.. if the
	 * resourceIds match, then the batch details in Caliber need to be updated with
	 * the data in the Salesforce. Furthermore, we then need to check each of the
	 * trainees in that batch and update their information from the salesforce as
	 * well.
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public boolean compareBatches(List<Batch> caliberBatches, List<Batch> salesforceBatches) {
		log.info("Comparing batches...");
		for (int sIndex = 0; sIndex < salesforceBatches.size(); sIndex++) {
			for (int cIndex = 0; cIndex < caliberBatches.size(); cIndex++) {
				// if caliber batch does not have resourceId, it cannot be synced. continue...
				if (caliberBatches.get(cIndex).getResourceId() == null)
					continue;
				// if resourceIds are same, update all the datas with fresh Salesforce data
				if (caliberBatches.get(cIndex).getResourceId().equals(salesforceBatches.get(sIndex).getResourceId())) {
					log.debug("Caliber batch: " + caliberBatches.get(cIndex).getResourceId() + " === "
							+ "Salesforce batch: " + salesforceBatches.get(sIndex).getResourceId());
					// extract salesforce data and save
					updateBatch(caliberBatches.get(cIndex), salesforceBatches.get(sIndex));

					// extract trainee information from Salesforce and update the trainees in the
					// Caliber batch
					for (Trainee trainee : caliberBatches.get(cIndex).getTrainees()) {
						for (Trainee salesforceTrainee : salesforceDao
								.getBatchDetails(caliberBatches.get(cIndex).getResourceId())) {
							// if caliber trainee does not have resourceId, it cannot be synced. continue...
							if (trainee.getResourceId() == null)
								continue;
							if (trainee.getResourceId().equals(salesforceTrainee.getResourceId())) {
								log.debug("Caliber trainee: " + trainee.getResourceId() + " === "
										+ "Salesforce trainee: " + salesforceTrainee.getResourceId());
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

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	private void updateTrainee(Trainee caliberTrainee, Trainee salesforceTrainee) {
		try {
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
		} catch (Exception e) {
			log.fatal(e);
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	private void updateBatch(Batch caliberBatch, Batch salesforceBatch) {
		try {
			if (salesforceBatch.getTrainer() != null) {
				caliberBatch.setTrainer(trainerDao.findByEmail(salesforceBatch.getTrainer().getEmail()));
			} else {
				log.info("Trainer is null for " + salesforceBatch.getTrainingName());
				caliberBatch.setTrainer(trainerDao.findByEmail(SalesforceService.DEFAULT_TRAINER));
				log.info("Trainer is now " + SalesforceService.DEFAULT_TRAINER + " for "
						+ salesforceBatch.getTrainingName());
			}
			if (salesforceBatch.getCoTrainer() != null) {
				log.info("Cotrainer for " + salesforceBatch.getTrainingName() + " is: "
						+ salesforceBatch.getCoTrainer());
				caliberBatch.setCoTrainer(trainerDao.findByEmail(salesforceBatch.getCoTrainer().getEmail()));
			}
			caliberBatch.setEndDate(salesforceBatch.getEndDate());
			caliberBatch.setSkillType(salesforceBatch.getSkillType());
			caliberBatch.setStartDate(salesforceBatch.getStartDate());
			caliberBatch.setTrainingName(salesforceBatch.getTrainingName());
			caliberBatch.setTrainingType(salesforceBatch.getTrainingType());
			batchDao.update(caliberBatch);
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
