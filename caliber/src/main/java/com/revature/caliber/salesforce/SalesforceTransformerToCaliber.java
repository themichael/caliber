package com.revature.caliber.salesforce;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
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
		//TODO -Change Location
		batch.setLocation("Reston VA");
		
		return batch;
	}
	
	//TODO - Tranform batchtrainer into trainer
	public Trainer transformTrainer(BatchTrainer batchTrainer){
		Trainer trainer = new Trainer();
		return trainer;
	}
	
	public SkillType transformSkillType(SalesforceBatch salesforceBatch) {
		String stringSkillType = salesforceBatch.getSkillType();
		System.out.println(stringSkillType);
		if(stringSkillType == null){
			stringSkillType = "";
		}
		switch (stringSkillType) {
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

}
