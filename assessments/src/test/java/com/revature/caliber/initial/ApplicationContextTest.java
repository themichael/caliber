package com.revature.caliber.initial;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextTest {

	@Test
	@Ignore
	public void context(){
		ApplicationContext ctxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		assertNotNull(ctxt);
	}
	
}
