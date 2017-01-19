package com.revature.caliber.data.implementations;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Week;
import com.revature.caliber.training.data.WeekDAO;

public class WeekDAOImplementationTest {
	
	private static ApplicationContext context;

	@BeforeClass
	public static void preClass() {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	}
	
	@Test
	public void getAll(){
	}
	
}
