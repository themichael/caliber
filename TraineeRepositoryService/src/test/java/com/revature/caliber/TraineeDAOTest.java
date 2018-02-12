package com.revature.caliber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.SimpleTrainee;
import com.revature.caliber.model.TrainerRole;
import com.revature.caliber.repository.TraineeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TraineeDAOTest {
	private static final Logger log = Logger.getLogger(TraineeDAOTest.class);

	private int traineeId = 1;
	private String email = "patrick.walsh@revature.com";

	@Autowired
	TraineeRepository dao;

	SimpleTrainee test;

	@Before
	public void initialize() {
		log.info("Initalizing a Test Trainee for use in Tests");
		test = new SimpleTrainee();
		test.setName("TRAINEE_NAME");
		test.setBatchId(1);
		test.setCollege("COLLEGE");
		test.setDegree("DEGREE");
		test.setEmail("TRAINEE_EMAIL@EMAIL.COM");
		test.setMajor("TEST_MAJOR");
		test.setName("TEST_NAME");
		test.setPhoneNumber("TEST_NUMBER");
		test.setProfileUrl("WWW.TEST.COM");
		test.setProjectCompletion("TEST_COMPLETE");
		test.setRecruiterName("TEST_RECRUTER");
		test.setResourceId("TEST_RESOURCEID");
		test.setSkypeId("TEST_SKYPE");
		test.setTechScreenerName("TEST_SCREENER");
		test.setTraineeId(555);
		
	}

	@Test
	public void testFindAll() {
		log.info("Getting All Trainees");
		List<SimpleTrainee> trainees = dao.findAll();

		assertFalse(trainees.isEmpty());
	}

//	@Test
//	public void findByTrainerId() {
//		log.info("Getting by traineeId");
//		SimpleTrainee trainee = dao.findOne(555);
//		assertEquals(555, trainee.getTraineeId());
//	}

	@Test
	public void addTrainee() {
		log.info("Adding Trainee");
		SimpleTrainee savedTrainee = dao.save(test);
		assertEquals(test.getTraineeId(), savedTrainee.getTraineeId());
	}

	@Test
	public void updateTrainee() {
		log.info("Updating Trainee");
		SimpleTrainee savedTrainee = dao.save(test);
		savedTrainee.setName("UPDATED_NAME");
		SimpleTrainee updatedTrainee = dao.save(savedTrainee);
		assertEquals(savedTrainee.getName(), updatedTrainee.getName());
	}

	@Test
	public void deleteTrainee() {
		log.info("Deleting Trainee");
		SimpleTrainee savedTrainee = dao.save(test);
		dao.delete(savedTrainee);
		assertNull(dao.findOne(savedTrainee.getTraineeId()));
	}

}
