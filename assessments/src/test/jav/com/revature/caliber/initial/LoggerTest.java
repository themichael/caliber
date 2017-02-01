package com.revature.caliber.initial;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

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
