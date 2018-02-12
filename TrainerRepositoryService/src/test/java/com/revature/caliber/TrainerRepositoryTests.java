package com.revature.caliber;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.SimpleTrainer;
import com.revature.caliber.model.TrainerRole;
import com.revature.caliber.repository.TrainerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainerRepositoryTests {
	
	@Autowired
	TrainerRepository test;
	
	@Test
	public void TestfindAll() {
		List<SimpleTrainer> trainer = test.findAll();
		assertNotNull(trainer);
	}
	
	@Test
	public void TestfindByTrainerId() {
		SimpleTrainer trainer = test.findByTrainerId(1);
		assertNotNull(trainer);
	}
	
	@Test
	public void TestfindAllTrainerTitles() {
		List<String> trainer = test.findAllTrainerTitles();
		assertNotNull(trainer);
	}
	
	@Test
	public void TestfindByEmail() {
		SimpleTrainer trainer = test.findByEmail("howard.johnson@hotmail.com");
		assertNotNull(trainer);
	}
	
	@Test
	public void TestupdateTrainerInfoById() {
		test.updateTrainerInfoById("test", "test", TrainerRole.ROLE_INACTIVE, (Integer) 1);
		assertNotNull(test.findOne(1));
	}
}
