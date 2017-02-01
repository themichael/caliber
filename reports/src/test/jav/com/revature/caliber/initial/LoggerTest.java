package com.revature.caliber.initial;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"WEB-INF/beans.xml"})
public class LoggerTest {

	private Logger logger;
	
	@Before
	public void configureLogger(){
		this.logger = Logger.getRootLogger();
	}
	
	@Test
	public void test(){
		logger.trace("Lowest priority");
		logger.debug("Low priority");
		logger.info("Standard message");
		logger.warn("High priority");
		logger.error("Higher priority");
		logger.fatal("Highest priority");
	}
	
}
