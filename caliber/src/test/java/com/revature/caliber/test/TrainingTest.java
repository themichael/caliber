package com.revature.caliber.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.controllers.TrainingController;
import com.revature.caliber.security.models.SalesforceUser;

public class TrainingTest extends CaliberTest {

	private static Logger log = Logger.getLogger(TrainingTest.class);

	@Autowired
	private TrainingController trainingController;

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// TRAINEE API
	//////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findAllByBatch(Integer
	 * batch)
	 */
	@Test
	public void findAllByBatch() {
		log.info("FIND TRAINEES BY BATCH");
		int expected = 16;
		int actual = trainingController.findAllByBatch(2201).getBody().size();
		assertEquals(expected, actual);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findAllDroppedByBatch(Integer
	 * batch)
	 */
	@Test
	public void findAllDroppedByBatch() {
		log.info("FIND DROPPED TRAINEES BY BATCH");
		int expected = 4;
		int actual = trainingController.findAllDroppedByBatch(2201).getBody().size();
		assertEquals(expected, actual);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.createTrainee(Trainee
	 * trainee)
	 */
	@Test
	public void createTrainee() {
		log.info("CREATE TRAINEE");
		Batch batch = trainingController.getAllBatches().getBody().get(0);
		Trainee trainee = new Trainee("Randolph Scott", "", "randolph@scott.edu", batch);
		Long rowCount = jdbcTemplate.queryForObject("select count(trainee_id) from caliber_trainee", Long.class);
		trainingController.createTrainee(trainee);
		Long newRowCount = jdbcTemplate.queryForObject("select count(trainee_id) from caliber_trainee", Long.class);
		assertEquals(++rowCount, newRowCount);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.updateTrainee(Trainee
	 * trainee)
	 */
	@Test
	public void updateTrainee() {
		log.info("UPDATE TRAINEE");
		String phone = "(555) 555-5555";
		Trainee trainee = trainingController.retreiveTraineeByEmail("kchangfatt@gmail.com").getBody();
		assertNotEquals(phone, trainee.getPhoneNumber());
		trainee.setPhoneNumber(phone);
		trainingController.updateTrainee(trainee);
		assertEquals(phone, trainee.getPhoneNumber());
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.deleteTrainee(int id)
	 */
	@Test
	public void deleteTrainee() {
		log.info("DELETE TRAINEE");
		Long rowCount = jdbcTemplate.queryForObject("select count(trainee_id) from caliber_trainee", Long.class);
		trainingController.deleteTrainee(1);
		Long newRowCount = jdbcTemplate.queryForObject("select count(trainee_id) from caliber_trainee", Long.class);
		assertEquals(--rowCount, newRowCount);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.retreiveTraineeByEmail(String
	 * traineeEmail)
	 */
	@Test
	public void retreiveTraineeByEmail() {
		log.info("FIND TRAINEE BY EMAIL");
		Trainee trainee = trainingController.retreiveTraineeByEmail("kchangfatt@gmail.com").getBody();
		Long traineeId = jdbcTemplate.queryForObject(
				"select trainee_id from caliber_trainee where trainee_email = 'kchangfatt@gmail.com'", Long.class);
		assertEquals(traineeId.intValue(), trainee.getTraineeId());
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// BATCH API
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.updateBatch(Batch
	 * batch)
	 */
	@Test
	public void testUpdateBatch() {
		log.info("UPDATE BATCH");
		Batch batch = trainingController.getAllBatches().getBody().get(0);
		String miami = "Miami, FL";
		assertNotEquals(miami, batch.getLocation());
		batch.setLocation(miami);
		trainingController.updateBatch(batch);
		String location = jdbcTemplate.queryForObject(
				"select location from caliber_batch where batch_id = " + batch.getBatchId(), String.class);
		assertEquals(miami, location);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.deleteBatch(int id)
	 */
	@Test
	public void testDeleteBatch() {
		log.info("DELETE BATCH");
		// create an empty batch
		Trainer trainer = trainingController.findTrainer("pjw6193@hotmail.com").getBody();
		Batch newBatch = new Batch("1707 Delete Me", trainer, new Date(), new Date(), "Reston, VA");
		// save batch
		Batch batch = trainingController.createBatch(newBatch).getBody();
		Long rowCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		// delete batch
		trainingController.deleteBatch(batch.getBatchId());
		Long newRowCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		assertEquals(--rowCount, newRowCount);
	}

	/**
	 * Expects that all batches are not current (ended over 1 month ago)
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.getAllCurrentBatches()
	 */
	@Test
	public void testGetAllCurrentBatch() {
		log.info("FIND ALL CURRENT BATCHES");
		// update a batch to be 'current'
		Batch batch = trainingController.getAllBatches().getBody().get(0);
		batch.setStartDate(new Date());
		batch.setEndDate(new Date());
		trainingController.updateBatch(batch);
		// check that there is 1 current batch
		int actual = trainingController.getAllCurrentBatches().getBody().size();
		assertEquals(1, actual);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.getAllBatches()
	 */
	@Test
	public void testGetAllBatch() {
		log.info("FIND ALL BATCHES");
		Long expected = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		int actual = trainingController.getAllBatches().getBody().size();
		assertEquals(expected.intValue(), actual);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.createWeek(int
	 * batchId)
	 */
	@Test
	public void testCreateWeek() {
		log.info("CREATE NEW WEEK");
		int batchId = 2200;
		int before = jdbcTemplate
				.queryForObject("select number_of_weeks from caliber_batch where batch_id = " + batchId, Integer.class);
		trainingController.createWeek(2200);
		int after = jdbcTemplate.queryForObject("select number_of_weeks from caliber_batch where batch_id = " + batchId,
				Integer.class);
		assertEquals(++before, after);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findCommonLocations()
	 */
	@Test
	public void testCommonLocations() {
		log.info("FIND COMMON LOCATIONS");
		List<String> expectedLocations = jdbcTemplate.queryForList("select distinct location from caliber_batch",
				String.class);
		List<String> actualLocations = trainingController.findCommonLocations().getBody();
		assertEquals(expectedLocations, actualLocations);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findAllBatchesByTrainer(Authentication
	 * auth)
	 */
	public void findBatchesByTrainer() {
		log.info("FIND BATCHES BY TRAINER");
		Long batchCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch where trainer_id = "
				+ ((SalesforceUser) mockAuth().getPrincipal()).getCaliberUser().getTrainerId(), Long.class);
		List<Batch> batches = trainingController.findAllBatchesByTrainer(mockAuth()).getBody();
		assertEquals(batchCount.intValue(), batches.size());
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findTrainer(String
	 * email)
	 * com.revature.caliber.controllers.TrainingController.createBatch(Batch
	 * batch)
	 */
	@Test
	public void testBatchSave() {
		log.info("CREATE BATCH");
		// find initial row count
		Long rowCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		log.info("Current batch count: " + rowCount);

		// run the test
		Trainer trainer = trainingController.findTrainer("pjw6193@hotmail.com").getBody();
		trainingController.createBatch(new Batch("1701 Java", trainer, new Date(), new Date(), "Reston, VA"));

		/****** test batch validation *****/
		// Good grade > borderline grade
		try {
			Batch batch = new Batch("1702 Java", trainer, new Date(), new Date(), "Tampa, FL");
			batch.setGoodGradeThreshold((short) 70);
			batch.setBorderlineGradeThreshold((short) 90);
			trainingController.createBatch(batch);
			fail();
		} catch (ConstraintViolationException e) {
			log.info("Test passed: Good grade > borderline grade " + e);
		}

		// End date > start date
		try {
			Calendar start = Calendar.getInstance();
			start.set(2017, Calendar.AUGUST, 25);
			Calendar end = Calendar.getInstance();
			end.set(2017, Calendar.AUGUST, 10);

			trainingController
					.createBatch(new Batch("1703 Java", trainer, start.getTime(), end.getTime(), "Queens, NY"));
			fail();
		} catch (ConstraintViolationException e) {
			log.info("Test passed: End date > start date" + e);
		}

		// Trainer != co-trainer
		try {
			Batch batch = new Batch("1704 Java", trainer, new Date(), new Date(), "Manhattan, NY");
			batch.setCoTrainer(trainer);
			trainingController.createBatch(batch);
			fail();
		} catch (ConstraintViolationException e) {
			log.info("Test passed: Trainer != co-trainer" + e);
		}

		// find new count. should be +1
		Long newRowCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		log.info("New batch count: " + newRowCount);
		assertEquals(++rowCount, newRowCount);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// TRAINER API
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Tests method:
	 * com.revature.caliber.controllers.TrainingController.createTrainer(Trainer
	 * trainer)
	 * 
	 */
	@Test
	public void createTrainer() {
		log.info("CREATE TRAINER");
		Trainer good = new Trainer("Randolph Scott", "Trainer", "randolph.scott@revature.com",
				TrainerRole.ROLE_TRAINER);
		Trainer nullName = new Trainer("", "Trainer", "abc@revature.com", TrainerRole.ROLE_TRAINER);
		Trainer nullTitle = new Trainer("ABC", "", "abc@revature.com", TrainerRole.ROLE_TRAINER);
		Trainer nullEmail = new Trainer("ABC", "Trainer", "", TrainerRole.ROLE_TRAINER);
		Trainer nullTier = new Trainer("ABC", "Trainer", "abc@revature.com", null);
		Trainer invalidEmail = new Trainer("ABC", "Trainer", "abcabcabc", null);
		Trainer notUniqueEmail = new Trainer("Randolph Scott Clone", "Clone Trooper", "randolph.scott@revature.com",
				TrainerRole.ROLE_TRAINER); // uses same email as Trainer `good`

		Long trainerCountBeforeInsert = jdbcTemplate.queryForObject("select count(trainer_id) from caliber_trainer",
				Long.class);
		trainingController.createTrainer(good);
		Long trainerCountAfterInsert = jdbcTemplate.queryForObject("select count(trainer_id) from caliber_trainer",
				Long.class);
		assertEquals(++trainerCountBeforeInsert, trainerCountAfterInsert);

		// negative inputs
		try {
			trainingController.createTrainer(nullName);
			fail();
		} catch (ConstraintViolationException e) {
			log.info(e);
		}
		try {
			trainingController.createTrainer(nullTitle);
			fail();
		} catch (ConstraintViolationException e) {
			log.info(e);
		}
		try {
			trainingController.createTrainer(nullEmail);
			fail();
		} catch (ConstraintViolationException e) {
			log.info(e);
		}
		try {
			trainingController.createTrainer(nullTier);
			fail();
		} catch (ConstraintViolationException e) {
			log.info(e);
		}
		try {
			trainingController.createTrainer(invalidEmail);
			fail();
		} catch (ConstraintViolationException e) {
			log.info(e);
		}
		try {
			trainingController.createTrainer(notUniqueEmail);
			fail();
		} catch (DataIntegrityViolationException e) {
			log.info(e);
		}
	}

	/**
	 * Tests method:
	 * com.revature.caliber.controllers.TrainingController.getAllTrainers()
	 * 
	 */
	@Test
	public void getAllTrainers() {
		log.info("GET ALL ACTIVE TRAINERS");
		Long trainerCount = jdbcTemplate.queryForObject(
				"select count(trainer_id) from caliber_trainer where tier != 'ROLE_INACTIVE'", Long.class);
		List<Trainer> trainers = trainingController.getAllTrainers().getBody();
		assertEquals(trainerCount.intValue(), trainers.size());
	}

	/**
	 * Tests method:
	 * com.revature.caliber.controllers.TrainingController.updateTrainer(Trainer
	 * trainer)
	 * 
	 */
	@Test
	public void updateTrainer() {
		log.info("UPDATE TRAINER");
		String jobTitle = "Chief Information Officer";
		Trainer trainer = trainingController.findTrainer("pjw6193@hotmail.com").getBody();
		assertNotEquals(jobTitle, trainer.getTier());
		trainer.setTitle(jobTitle);
		trainingController.updateTrainer(trainer);
		Trainer result = trainingController.findTrainer("pjw6193@hotmail.com").getBody();
		assertEquals(jobTitle, result.getTitle());
	}

	/**
	 * Tests method:
	 * com.revature.caliber.controllers.TrainingController.findTrainer(String
	 * email)
	 * 
	 */
	@Test
	public void getTrainerByEmail() {
		log.info("GET TRAINER BY EMAIL");
		Trainer expected = new Trainer("Dan Pickles", "Lead Trainer", "pjw6193@hotmail.com", TrainerRole.ROLE_VP);
		Trainer actual = trainingController.findTrainer("pjw6193@hotmail.com").getBody();
		assertEquals(expected, actual);
	}

	/**
	 * Tests method:
	 * com.revature.caliber.controllers.TrainingController.getAllTrainersTitles()
	 * 
	 */
	@Test
	public void getCommonJobTitles() {
		log.info("GET COMMON JOB TITLES");
		List<String> expectedJobTitles = jdbcTemplate.queryForList("select distinct title from caliber_trainer",
				String.class);
		List<String> jobTitles = trainingController.getAllTrainersTitles().getBody();
		assertEquals(expectedJobTitles, jobTitles);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findTrainer(String
	 * email)
	 * com.revature.caliber.controllers.TrainingController.makeInactive(Trainer
	 * trainer)
	 */
	@Test
	public void testDeactiveTrainer() {
		log.info("DEACTIVATE TRAINER");
		// testing findTrainer by email
		Trainer trainer = trainingController.findTrainer("karan.dhirar@revature.com").getBody();
		// make sure user isn't already inactive
		assertNotEquals(TrainerRole.ROLE_INACTIVE, trainer.getTier());
		// testing this method
		trainingController.makeInactive(trainer);
		Trainer result = trainingController.findTrainer("karan.dhirar@revature.com").getBody();
		// user is now inactive
		assertEquals(TrainerRole.ROLE_INACTIVE, result.getTier());
	}
}
