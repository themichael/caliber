package com.revature.caliber.data.implementations;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
	@Ignore
	public void getWeekByIdTest(){
		Week week = context.getBean(WeekDAO.class).getWeekById(50);
	}
	
	@Test
	@Ignore
	public void getAllWeekTest(){

		List<Week> weeks = new ArrayList<Week>(); 
		weeks =	context.getBean(WeekDAO.class).getAllWeek();
	}
	
	
	@Test
	@Ignore
	public void insertWeekTest(){
		Week newWeek = new Week(1, null, null);
		context.getBean(WeekDAO.class).createNewWeek(newWeek);
	}
	
}
