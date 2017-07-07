package com.revature.caliber.transform;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.salesforce.SalesforceTransformerToCaliber;
import com.revature.salesforce.beans.SalesforceBatch;
import com.revature.salesforce.beans.SalesforceTrainee;


public class TransformDev {	
	@Autowired
	private SalesforceBatch salesforceBatch = new SalesforceBatch(); 
	private SalesforceTrainee salesforceTrainee = new SalesforceTrainee();
	private BatchTrainer batchTrainer = new BatchTrainer();
	
	
	SalesforceTransformerToCaliber sttc = new SalesforceTransformerToCaliber();
	

	
	@Test
	//@Ignore
	public void transformBatch(){
		//salesforceBatch.setTrainer(batchTrainer);
		sttc.transformBatch(salesforceBatch);
		Logger logger = Logger.getLogger("com.revature");
		logger.debug(sttc.getClass());	
	}
	@Test
	public void transformTrainee(){
		//salesforceTrainee.setBatch(salesforceBatch);
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
