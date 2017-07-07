package com.revature.caliber.salesforce;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainer;
import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;

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
		batchTrainer=null;
		return trainer;
	}
	
	public SkillType transformSkillType(SalesforceBatch salesforceBatch) {
		String stringSkillType = salesforceBatch.getSkillType();
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
