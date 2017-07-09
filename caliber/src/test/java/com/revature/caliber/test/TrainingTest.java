package com.revature.caliber.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.controllers.TrainingController;

public class TrainingTest extends CaliberTest{

	private static Logger log = Logger.getLogger(TrainingTest.class);

	@Autowired
	private TrainingController trainingController;

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findTrainer(String
	 * email)
	 * com.revature.caliber.controllers.TrainingController.createBatch(Batch
	 * batch)
	 */
	@Test
	public void testBatchSave() {
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
			log.info("Test passed: End date > start date"  + e);
		}

		// Trainer != co-trainer
		try {
			Batch batch = new Batch("1704 Java", trainer, new Date(), new Date(), "Manhattan, NY");
			batch.setCoTrainer(trainer);
			trainingController.createBatch(batch);
			fail();
		} catch (ConstraintViolationException e) {
			log.info("Test passed: Trainer != co-trainer"  + e);
		}

		// find new count. should be +1
		Long newRowCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		log.info("New batch count: " + newRowCount);
		assertEquals(++rowCount, newRowCount);
	}

	/**
	 * Tests methods:
	 * com.revature.caliber.controllers.TrainingController.findTrainer(String
	 * email)
	 * com.revature.caliber.controllers.TrainingController.makeInactive(Trainer trainer)
	 */
	@Test
	public void testDeactiveTrainer(){
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
