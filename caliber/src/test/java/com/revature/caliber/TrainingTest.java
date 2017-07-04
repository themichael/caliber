package com.revature.caliber;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainingType;
import com.revature.caliber.controllers.TrainingController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:integration-test.xml" })
public class TrainingTest {

	private static Logger log = Logger.getLogger(TrainingTest.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TrainingController trainingController;

	/**
	 * Tests methods:
	 * 		com.revature.caliber.controllers.TrainingController.findTrainer(String email)
	 * 		com.revature.caliber.controllers.TrainingController.createBatch(Batch batch)
	 */
	@Test
	@Sql(scripts = "/setup.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = "/teardown.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testSave() {
		// find initial row count
		Long rowCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		log.info("Current batch count: " + rowCount);

		// run the test
		Trainer trainer = trainingController.findTrainer("pjw6193@hotmail.com").getBody();
		trainingController.createBatch(new Batch("1702 Java", trainer,
				SkillType.J2EE, TrainingType.Revature, new Date(), new Date(), "Reston, VA", 80, 70, 1));
		
		/****** test batch validation *****/
		// Good grade > borderline grade
		try{
			trainingController.createBatch(new Batch("1702 Java", trainer,
					SkillType.J2EE, TrainingType.Revature, new Date(), new Date(), "Reston, VA", 80, 100, 1));
		}catch(ConstraintViolationException e){
			log.info("Test passed: Good grade > borderline grade");
		}
		
		// End date > start date
		try{
			Calendar start = Calendar.getInstance(); start.set(2017, Calendar.AUGUST, 25);
			Calendar end = Calendar.getInstance(); end.set(2017, Calendar.AUGUST, 10);
			
			trainingController.createBatch(new Batch("1702 Java", trainer,
					SkillType.J2EE, TrainingType.Revature, start.getTime(), end.getTime(), "Reston, VA", 100, 90, 1));
		}catch(ConstraintViolationException e){
			log.info("Test passed: End date > start date");
		}
		
		// Trainer != co-trainer
		try{
			Batch batch = new Batch("1702 Java", trainer,
					SkillType.J2EE, TrainingType.Revature,  new Date(), new Date(), "Reston, VA", 100, 90, 1);
			batch.setCoTrainer(trainer);
			trainingController.createBatch(batch);
		}catch(ConstraintViolationException e){
			log.info("Test passed: Trainer != co-trainer");
		}
		
		// find new count. should be +1
		Long newRowCount = jdbcTemplate.queryForObject("select count(batch_id) from caliber_batch", Long.class);
		log.info("New batch count: " + newRowCount);
		assertEquals(++rowCount, newRowCount);
	}

}
