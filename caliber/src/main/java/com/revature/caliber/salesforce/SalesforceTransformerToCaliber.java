package com.revature.caliber.salesforce;

import org.apache.log4j.Logger;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;

public class SalesforceTransformerToCaliber {

	private static final Logger logger = Logger.getLogger(SalesforceTransformerToCaliber.class);
	
	public Batch transformBatch(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		if(salesforceBatch == null){
			return batch;
		}
		batch.setResourceId(salesforceBatch.getId());
		batch.setTrainingName(salesforceBatch.getName());
		batch.setStartDate(salesforceBatch.getBatchStartDate());
		batch.setTrainer(transformTrainer(salesforceBatch.getTrainer()));
		batch.setCoTrainer(transformTrainer(salesforceBatch.getCotrainer()));
		batch.setEndDate(salesforceBatch.getBatchEndDate());
		batch.setResourceId(salesforceBatch.getId());
		batch.setSkillType(transformSkillType(salesforceBatch));
		batch.setBorderlineGradeThreshold((short) 70);
		batch.setGoodGradeThreshold((short) 100);
		batch.setLocation(salesforceBatch.getLocation());

		return batch;
	}
	
	//TO DO - Tranform batchtrainers into trainers
	public Trainer transformTrainer(BatchTrainer batchTrainer){
		Trainer trainer = new Trainer();
		if(batchTrainer == null){
			return trainer;
		}
		trainer.setName(batchTrainer.getName());
		trainer.setEmail(batchTrainer.getEmail());	
		trainer.setTier(transformTier("ROLE_QC"));
		trainer.setTitle("J2EE");
		return trainer;
	}	
	public TrainerRole transformTier(String batchTrainer) {
		String stringTier = batchTrainer;
		if(stringTier == null){
			return TrainerRole.ROLE_TRAINER;
		}
		return transformTierHelper(stringTier);
	}
	
	private TrainerRole transformTierHelper(String tier){
		switch (tier) {
		case "ROLE_QC":
			return TrainerRole.ROLE_QC;
		case "ROLE_VP":
			return TrainerRole.ROLE_VP;
		default:
			return TrainerRole.ROLE_TRAINER;
		}
	}

	public SkillType transformSkillType(SalesforceBatch salesforceBatch) {
		String stringSkillType = salesforceBatch.getSkillType();
		if (stringSkillType == null) {
			return SkillType.OTHER;
		}
		return transformSkillTypeHelper(stringSkillType);



	}

	private SkillType transformSkillTypeHelper(String skillType) {
		switch (skillType) {
		case "J2EE":
			return SkillType.J2EE;
		case ".NET":
			return SkillType.NET;
		case "SDET":
			return SkillType.SDET;
		case "BPM":
			return SkillType.BPM;
		default:
			return SkillType.OTHER;
		}
	}

	public Trainer transformBatch(BatchTrainer batchTrainer) {
		Trainer trainer = new Trainer();

		if(batchTrainer == null){
			return trainer;
		}
		trainer.setName(batchTrainer.getName());	

		return trainer;
	}

	public Trainee transformTrainee(SalesforceTrainee salesforceTrainee) {

		Trainee trainee = new Trainee();

		if(salesforceTrainee == null){
			return trainee;
		}

		trainee.setName(salesforceTrainee.getName());
		trainee.setEmail(salesforceTrainee.getEmail());
		trainee.setBatch(transformBatch(salesforceTrainee.getBatch()));
		trainee.setTrainingStatus(transformStatus(salesforceTrainee));
		trainee.setPhoneNumber(salesforceTrainee.getPhone());
		trainee.setPhoneNumber(salesforceTrainee.getMobilePhone());
		trainee.setResourceId(salesforceTrainee.getId());
		trainee.setSkypeId("Yuvimon333");
		trainee.setResourceId("3234");
		trainee.setProfileUrl("http://www.google.com");
		return trainee;
	}

	public TrainingStatus transformStatus(SalesforceTrainee salesforceTrainee) {

		String stringTrainingStatus = salesforceTrainee.getTrainingStatus();

		if (stringTrainingStatus == null) {
			stringTrainingStatus = "";
		}
		return transformStatusHelper(stringTrainingStatus);

	}

	private TrainingStatus transformStatusHelper(String stringTrainingStatus) {
		try {
			return TrainingStatus.valueOf(stringTrainingStatus);
		} catch (IllegalArgumentException exp) {
			logger.debug("Exp caught in SalesforceTransformer.transformStatusHelper");
	        logger.debug(exp);
			return TrainingStatus.Training;
		}
	}
}
