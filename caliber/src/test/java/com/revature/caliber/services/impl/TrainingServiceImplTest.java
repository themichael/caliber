package com.revature.caliber.services.impl;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.revature.caliber.beans.Week;
import com.revature.caliber.gateway.services.impl.TrainingServiceImpl;

public class TrainingServiceImplTest {
	
	@Test
	@Ignore
	public void getAllWeekTest(){
		
		TrainingServiceImpl training = new TrainingServiceImpl();
		List<Week> weeks = training.getAllWeek();
		System.out.println(weeks);
	}
	
	@Test
	@Ignore
	public void addNewWeekTest(){
		Week newWeek = new Week();
		newWeek.setWeekNumber(2099);
		TrainingServiceImpl training = new TrainingServiceImpl();
		training.createWeek(newWeek);
	}
	
	
	

}
