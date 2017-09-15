package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.log.SysoCounter;
import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.controllers.TrainingController;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.test.integration.AssessmentTest;


public class TraineeDAOTest extends CaliberTest {
	
	private static final Logger log = Logger.getLogger(AssessmentTest.class);
	
	@Autowired
	private TrainingController trainingController;
	private TraineeDAO traineeDAO;
	private static final String TRAINEE_COUNT = "select count(trainee_id) from caliber_trainee";
	
	
	@Autowired
	public void setTraineeDAO(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}
	
	
	@Test
	public void testSave() {
		log.info("CREATE TRAINEE");
		Batch batch = trainingController.getAllBatches().getBody().get(0);
		String name = "Danny McQuack";
		String email = "test@anotherDomain.com";
		Trainee trainee = new Trainee(name, null, email, batch);		
		Long before = jdbcTemplate.queryForObject(TRAINEE_COUNT, Long.class);
		traineeDAO.save(trainee);
		
		try {
			Trainee nullName = new Trainee(null,null,email,batch);
			traineeDAO.save(nullName);
			
		} catch(ConstraintViolationException e) {
			log.debug(e);
		}try {
			Trainee nullEmail = new Trainee(name,null,null,batch);
			traineeDAO.save(nullEmail);
		}catch(ConstraintViolationException e) {
			log.debug(e);
		}try {
			Trainee nullBatch = new Trainee(name,null,email,null);
			traineeDAO.save(nullBatch);
		}catch(ConstraintViolationException e) {
			log.debug(e);
		}
		Long after = jdbcTemplate.queryForObject(TRAINEE_COUNT, Long.class);		
		assertEquals(++before, after);	
	}
	@Test
	public void testFindAll() {
		log.info("FIND ALL TRAINEES");
		Long sizeActual = jdbcTemplate.queryForObject(TRAINEE_COUNT, Long.class);
		List<Trainee> trainees = traineeDAO.findAll();
		Long sizeExpected = (long)trainees.size();
		assertEquals(sizeExpected, sizeActual);	
	}
	
	@Test
	public void testFindAllByBatch(){
		log.info("FIND ALL TRAINEES BY BATCH");
		Batch batch = trainingController.getAllBatches().getBody().get(0);	
		String traineeCountByBatch = TRAINEE_COUNT + " where batch_id = " + batch.getBatchId() + " and training_status != 'Dropped'";	
		List<Trainee> trainees = traineeDAO.findAllByBatch(batch.getBatchId());		
		Long actualBatchSize = jdbcTemplate.queryForObject(traineeCountByBatch, Long.class);		
		Long expectedBatchSize = (long)trainees.size();		
		assertEquals(expectedBatchSize, actualBatchSize);		
		int badBatchId = 5; // no batch with this Id exists
		List<Trainee> badBatchCall = traineeDAO.findAllByBatch(badBatchId);
		int badBatchCallSize = badBatchCall.size();
		assertEquals(0,badBatchCallSize);	
	}
	
	@Test
	public void testFindAllDroppedByBatch() {
		log.info("FIND ALL TRAINEES DROPPED BY BATCH");
		
		Batch batch = trainingController.getAllBatches().getBody().get(0);	
		String traineeCountByBatch = TRAINEE_COUNT + " where batch_id = " + batch.getBatchId() + " and training_status = 'Dropped'";
		Long actualBatchDroppedSize = jdbcTemplate.queryForObject(traineeCountByBatch, Long.class);
		List<Trainee> droppedTrainees = traineeDAO.findAllDroppedByBatch(batch.getBatchId());
		Long expectedBatchDroppedSize = (long)droppedTrainees.size();
		assertEquals(expectedBatchDroppedSize, actualBatchDroppedSize);
		
		int badBatchId = 5; // no batch with this Id exists
		List<Trainee> badBatchCall = traineeDAO.findAllDroppedByBatch(badBatchId);
		int badBatchCallSize = badBatchCall.size();
		assertEquals(0,badBatchCallSize);
	}
	
	@Test
	public void testFindAllByTrainer() {
		log.info("FIND ALL TRAINEES BY TRAINER");
		Trainer trainer = trainingController.getAllTrainers().getBody().get(0);
		String traineeCountByTrainer = TRAINEE_COUNT + " WHERE BATCH_ID IN "
				+"(SELECT BATCH_ID FROM CALIBER_BATCH WHERE TRAINER_ID = " + trainer.getTrainerId() +") " 
				+" AND TRAINING_STATUS != 'Dropped'";
		Long actualCountSize = jdbcTemplate.queryForObject(traineeCountByTrainer, Long.class);
		List<Trainee> trainees = traineeDAO.findAllByTrainer(trainer.getTrainerId());
		Long expectedCountSize = (long)trainees.size();
		assertEquals(expectedCountSize, actualCountSize);
		
		int badTrainerId= -1; // no trainer with this Id exists
		List<Trainee> badTrainerCall = traineeDAO.findAllByTrainer(badTrainerId);
		int badTrainerCallSize = badTrainerCall.size();
		assertEquals(0,badTrainerCallSize);
	}
}
