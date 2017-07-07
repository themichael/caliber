package com.revature.caliber.transform;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.caliber.salesforce.SalesforceTransformerToCaliber;
import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;

@Component
public class TransformDev {	
	@Autowired
	private SalesforceBatch salesforceBatch; 
	@Autowired
	private SalesforceTrainee salesforceTrainee;
	@Autowired
	private BatchTrainer batchTrainer;	
	@Autowired
	SalesforceTransformerToCaliber sttc = new SalesforceTransformerToCaliber();
	

	
	@Test
	public void transformBatch(){
		sttc.transformBatch(salesforceBatch);
		Logger logger = Logger.getLogger("com.revature");
		logger.debug(sttc.getClass());	
	}
	@Test
	public void transformTrainee(){
		sttc.transformTrainee(salesforceTrainee);
		Logger logger = Logger.getLogger("com.revature");
		logger.debug(sttc.getClass());
	}
	@Test
	public void transformTrainer(){
		sttc.transformTrainer(batchTrainer);
		Logger logger = Logger.getLogger("com.revature");
		logger.debug(sttc.getClass());
	}
}
