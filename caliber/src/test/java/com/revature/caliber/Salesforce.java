package com.revature.caliber;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.data.SalesforceDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:integration-test.xml" })
public class Salesforce {

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
