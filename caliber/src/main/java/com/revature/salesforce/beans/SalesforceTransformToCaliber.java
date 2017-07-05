package com.revature.salesforce.beans;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;

public class SalesforceTransformToCaliber {
	
	public void transformTrainer(BatchTrainer batchTrainer){
		Trainer trainer = new Trainer();		
		trainer.setName(batchTrainer.getName());
	}
	
	public void transformBatchName(SalesforceBatch salesforceBatch){
		Batch batch = new Batch();
		batch.setTrainingName(salesforceBatch.getName());
	}
	
}
