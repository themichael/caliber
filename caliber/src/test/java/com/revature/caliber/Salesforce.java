package com.revature.caliber;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.data.SalesforceDAO;

public class Salesforce extends CaliberTest{

	private static final Logger log = Logger.getLogger(Salesforce.class);
	
	@Autowired
	private SalesforceDAO salesforceDAO;
	
	@Test
	public void go(){
		log.info("Getting batches");
		salesforceDAO.getAllRelevantBatches();
		log.info("Getting batch details");
		salesforceDAO.getBatchDetails("a0Yi000000F0b7I");
	}
	
}
