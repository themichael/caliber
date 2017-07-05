package com.revature.caliber.salesforce;

import com.revature.caliber.beans.Batch;
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

	public void transformBatchStartDate(SalesforceBatch salesforceBatch) {
		Batch batch = new Batch();
		batch.setStartDate(salesforceBatch.getBatchStartDate());
	}

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

}
