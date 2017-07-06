package com.revature.caliber.transform;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.caliber.salesforce.SalesforceTransformerToCaliber;
import com.revature.salesforce.beans.BatchTrainer;
import com.revature.salesforce.beans.SalesforceBatch;

public class TransformTest {
	
	SalesforceBatch salesforceBatch = new SalesforceBatch(); 
	SalesforceTransformerToCaliber sttc = new SalesforceTransformerToCaliber();
	
	@Test
	public void transformBatch(){
		sttc.transformBatch(salesforceBatch);
		Logger logger = Logger.getLogger("com.revature");
		logger.debug(sttc.getClass());	
	}
	
}
