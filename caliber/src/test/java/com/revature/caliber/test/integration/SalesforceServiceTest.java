package com.revature.caliber.test.integration;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.services.SalesforceService;

public class SalesforceServiceTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(SalesforceServiceTest.class);
	
	@Autowired
	SalesforceService salesforceService;
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.service.SalesforceService#getAllRelevantBatches()
	 */
	@Ignore
	@Test
	public void getAllRelevantBatchesTest(){
		log.debug("Testing SalesforceService.getAllRelevantBatches()");
		List<Batch> batches = salesforceService.getAllRelevantBatches();
		log.debug(batches);
	}
}
