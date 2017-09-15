package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.TrainerDAO;
import com.revature.caliber.services.TrainingService;

public class TrainingServiceTest extends CaliberTest {
	
	TrainingService service;
	TrainerDAO dao;
	
	@Autowired
	public void setDao(TrainerDAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setService(TrainingService service) {
		this.service = service;
	}
	
	String INACTIVE = "select ";
	
	@Test
	public void makeInactiveWeek(){
		TrainerRole before = dao.findOne(1).getTier();
		assertEquals(TrainerRole.ROLE_VP,before);
		service.makeInactive(dao.findOne(1));
		TrainerRole after = dao.findOne(1).getTier();
		assertNotEquals(before, after );
		assertEquals(TrainerRole.ROLE_INACTIVE, after);
	}
	
	@Test
	public void addWeek(){
		//service.addWeek(1);
		
	}

}
