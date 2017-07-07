package com.revature.caliber.salesforce;


import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;

public class SalesforceTransformerToCaliber {

	public Batch transformBatch(SalesforceBatch salesforceBatch){
		Batch batch = new Batch();
		batch.setResourceId(salesforceBatch.getId());
		batch.setTrainingName(salesforceBatch.getName());
		batch.setStartDate(salesforceBatch.getBatchStartDate());
		batch.setTrainer(transformTrainer(salesforceBatch.getTrainer()));
		batch.setCoTrainer(transformTrainer(salesforceBatch.getTrainer()));
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
		trainer.setName("Yuvi");
		trainer.setName(batchTrainer.getName());
		return trainer;
	}
	
	public SkillType transformSkillType(SalesforceBatch salesforceBatch) {
		String stringSkillType = salesforceBatch.getSkillType();
		if(stringSkillType == null){
			return SkillType.OTHER;
		}
		return transformSkillTypeHelper(stringSkillType);

		
	}
	
	private SkillType transformSkillTypeHelper(String skillType){
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
	
	public Trainer transformBatch(BatchTrainer batchTrainer){
		Trainer trainer = new Trainer();
		trainer.setName(batchTrainer.getName());	
		return trainer;
	}
	
	public Trainee transformTrainee(SalesforceTrainee salesforceTrainee){
		
		Trainee trainee = new Trainee();
	
		trainee.setName(salesforceTrainee.getName());
		trainee.setEmail(salesforceTrainee.getEmail());
		trainee.setBatch(transformBatch(salesforceTrainee.getBatch()));
		trainee.setTrainingStatus(transformStatus(salesforceTrainee));
		trainee.setPhoneNumber(salesforceTrainee.getPhone());
		trainee.setResourceId(salesforceTrainee.getId());
		return trainee;
	}
	
	public TrainingStatus transformStatus(SalesforceTrainee salesforceTrainee) {
		
		String stringTrainingStatus = salesforceTrainee.getTrainingStatus();
		
		if(stringTrainingStatus == null){
			stringTrainingStatus = "";
		}
		return transformStatusHelper(stringTrainingStatus);

	}

	private TrainingStatus transformStatusHelper(String stringTrainingStatus){
		switch (stringTrainingStatus) {
		case "Confirmed":
			return TrainingStatus.Confirmed;
			
		case "Dropped":
			return TrainingStatus.Dropped;
		
		case "Employed":
			return TrainingStatus.Employed;
		
		case "Marketing":
			return TrainingStatus.Marketing;
			
		case "Selected":
			return TrainingStatus.Selected;
			
		case "Signed":
			return TrainingStatus.Signed;
			
		default:
			return TrainingStatus.Training;
		}
	}
}
