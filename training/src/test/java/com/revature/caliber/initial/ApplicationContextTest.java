package com.revature.caliber.initial;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextTest {

	@Test
	public void context(){
		ApplicationContext ctxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		assertNotNull(ctxt);
	}
	
}
