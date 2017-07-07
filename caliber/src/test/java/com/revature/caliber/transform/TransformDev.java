package com.revature.caliber.transform;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.caliber.salesforce.SalesforceTransformerToCaliber;
import com.revature.salesforce.beans.SalesforceBatch;

public class TransformDev {
	
	SalesforceBatch salesforceBatch = new SalesforceBatch(); 
	SalesforceTransformerToCaliber sttc = new SalesforceTransformerToCaliber();
	
	@Test
	public void transformBatch(){
		sttc.transformBatch(salesforceBatch);
		Logger logger = Logger.getLogger("com.revature");
		logger.debug(sttc.getClass());	
	}
	
}
