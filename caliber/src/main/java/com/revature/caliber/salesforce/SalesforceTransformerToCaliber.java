package com.revature.caliber.salesforce;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.caliber.beans.Batch;
<<<<<<< HEAD
=======
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainer;
import com.revature.salesforce.beans.BatchTrainer;
>>>>>>> 4ea7c31629ce9093ce06d2fdead7906109a86adc
import com.revature.salesforce.beans.SalesforceBatch;

public class SalesforceTransformerToCaliber {

	public void transformId(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setResourceId(salesforceBatch.getId());
	}
	
	public void transformName(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setTrainingName(salesforceBatch.getName());
	}
<<<<<<< HEAD

=======
	
>>>>>>> 4ea7c31629ce9093ce06d2fdead7906109a86adc
	public void transformBatchStartDate(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setStartDate(salesforceBatch.getBatchStartDate());
	}
<<<<<<< HEAD

	public void transformBatchEndDate(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setEndDate(salesforceBatch.getBatchEndDate());
	}

	public void transformTrainer(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
	}

	public void transformCotrainer(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
	}

	public void transformSkillType(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
	}

	public void transformType(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setTrainingType(salesforceBach.getType);
	}

=======
	
	public void transformBatchEndDate() {

	}
	
	public void transformTrainer(SalesforceBatch salesforceBatch) {
		Trainer trainer = new Trainer();
		Batch batch = new Batch();
		trainer.setName(salesforceBatch.getTrainer().toString());
		batch.setTrainer(trainer);
	}
	
	public void transformCotrainer(SalesforceBatch salesforceBatch) {	
		Trainer trainer = new Trainer();
		Batch batch = new Batch();
		trainer.setName(salesforceBatch.getCotrainer().toString());
		batch.setCoTrainer(trainer);
	}
	
	public void transformSkillType(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();		
		String stringSkillType = salesforceBatch.getSkillType();
		SkillType skill = null;
		switch(stringSkillType){		
		case "J2EE":
			batch.setSkillType(skill.J2EE);
			break;
		case ".NET":
			batch.setSkillType(skill.NET);
			break;
		case "SDET":
			batch.setSkillType(skill.SDET);
			break;
		case "BPM":
			batch.setSkillType(skill.BPM);
			break;
		default:
			batch.setSkillType(skill.OTHER);
			break;
		
		}
		
	}
	
>>>>>>> 4ea7c31629ce9093ce06d2fdead7906109a86adc
}
