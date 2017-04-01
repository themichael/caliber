package com.revature.caliber;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class InitialTest {
	
	private static Logger log = Logger.getLogger(InitialTest.class);
	
	@Test
	public void testing() {
		log.info("Testing Spring bean configurations");
		AbstractApplicationContext ctxt =
				new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		ctxt.close();
	}	
}
