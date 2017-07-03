package com.revature.caliber;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.data.SalesforceDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class SalesforceDataIntegration {

	private static final Logger log = Logger.getLogger(SalesforceDataIntegration.class);
	
	@Autowired
	private SalesforceDAO salesforceDAO;

	public void setSalesforceDAO(SalesforceDAO salesforceDAO) {
		this.salesforceDAO = salesforceDAO;
	}
	
	@Test
	public void getAllBatches() {
		salesforceDAO.getAllBatches();
		log.info("Success!");
	}
}
