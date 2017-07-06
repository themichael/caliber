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

	public void transformId(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setResourceId(salesforceBatch.getId());
	}

	public void transformName(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setTrainingName(salesforceBatch.getName());
	}

	public void transformBatchStartDate(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setStartDate(salesforceBatch.getBatchStartDate());
	}

	public void transformBatchEndDate(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setEndDate(salesforceBatch.getBatchEndDate());
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
		switch (stringSkillType) {
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

	public void transformTraineeId(SalesforceTrainee salesforceTrainee){
		Trainer trainer = new Trainer();
		trainer.setTrainerId(Integer.parseInt(salesforceTrainee.getId()));

	}
	public void transformTraineeName(SalesforceTrainee salesforceTrainee){
		Trainer trainer = new Trainer();
		trainer.setName(salesforceTrainee.getName());
	}
	public void transformTraineeTrainingStatus(){
		
	}
	public void transformTraineePhone(){
		
	}
	public void transformTraineeEmail(){
		
	}
	public void transformTraineeMobilePhone(){
		
	}
	public void transformTraineeBatchId(SalesforceTrainee salesforceTrainee){
		Trainee trainee = new Trainee();
		Batch batch = new Batch();
		salesforceTrainee.getBatchId();
	}

}
