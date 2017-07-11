package com.revature.caliber.salesforce;

import org.apache.log4j.Logger;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;

public class SalesforceTransformerToCaliber {

	private static final Logger logger = Logger.getLogger(SalesforceTransformerToCaliber.class);

	public Batch transformBatch(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		if (salesforceBatch == null) {
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
		batch.setLocation(salesforceBatch.getLocation());

		return batch;
	}

	// TO DO - Tranform batchtrainers into trainers
	public Trainer transformTrainer(BatchTrainer batchTrainer) {
		Trainer trainer = new Trainer();
		if (batchTrainer == null) {
			return trainer;
		}
		trainer.setName(batchTrainer.getName());
		trainer.setEmail(batchTrainer.getEmail());
		return trainer;
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

	public Trainee transformTrainee(SalesforceTrainee salesforceTrainee) {

		Trainee trainee = new Trainee();

		if (salesforceTrainee == null) {
			return trainee;
		}

		trainee.setName(salesforceTrainee.getName());
		trainee.setEmail(salesforceTrainee.getEmail());
		trainee.setTrainingStatus(transformStatus(salesforceTrainee));
		trainee.setPhoneNumber(salesforceTrainee.getPhone());
		trainee.setPhoneNumber(salesforceTrainee.getMobilePhone());
		trainee.setResourceId(salesforceTrainee.getId());
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
