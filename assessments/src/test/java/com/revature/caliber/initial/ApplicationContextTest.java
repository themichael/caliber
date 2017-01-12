package com.revature.caliber.initial;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextTest {

	@Test
	public void context(){
		assertNotNull(new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml"));
	}
	
}
