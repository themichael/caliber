package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.NoteDAO;

public class BatchDAOTest extends CaliberTest {
	
	private static final Logger log = Logger.getLogger(BatchDAOTest.class);
	
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private NoteDAO noteDAO;
	
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrent(trainerId)
	 */
	@Test
	public void findAllCurrentIntTest(){
		log.info("Testing the BatchDAO.findAllCurrent(trainerId)");
		List<Batch> batches = batchDAO.findAllCurrent(1);
		int expected = 3; //only 3 current batches with trainerId: 1
		int actual = batches.size();
		assertEquals(expected, actual);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrentWithNotes()
	 */
	@Test
	public void findAllCurrentWithNotesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithNotesTest()");
		List<Batch> batches = batchDAO.findAllCurrentWithNotes();
		int expected = 1; //only one current batch has notes
		int actual = batches.size();
		assertEquals(expected, actual);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrentWithNotesAndTrainees()
	 */
	@Test
	public void findAllCurrentWithNotesAndTraineesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithNotesAndTrainees()");
		List<Batch> batches = batchDAO.findAllCurrentWithNotesAndTrainees();
		int expected = 1; //Only one current batch has notes and trainees
		int actual = batches.size();
		assertEquals(expected, actual);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrentWithTrainees()
	 */
	@Test
	public void findAllCurrentWithTraineesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithTrainees()");
		List<Batch> batches = batchDAO.findAllCurrentWithTrainees();
		int expected = 3;  //All current batches have trainees
		int actual = batches.size();
		assertEquals(expected, actual);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAll()
	 */
	@Test
	public void findAllTest() {
		log.info("Testing the BatchDAO.findAll()");
		String sql = "SELECT * FROM CALIBER_BATCH";
		int expect = jdbcTemplate.queryForList(sql).size();
		int actual = batchDAO.findAll().size();
		assertEquals(expect, actual);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllAfterDate()
	 */
	@Test
	public void findAllAfterDateTest() {
		log.info("Testing the BatchDAO.findAllAfterDateTest()");
		// positive test
		// find how many after a specific date
		String sql = "SELECT START_DATE FROM CALIBER_BATCH WHERE START_DATE >= '2017-01-01'";
		int expect = jdbcTemplate.queryForList(sql).size();
		int actual = batchDAO.findAllAfterDate(0, 1, 2017).size();
		assertEquals(expect, actual);

		// negative test
		sql = "SELECT START_DATE FROM CALIBER_BATCH WHERE START_DATE >= '2017-01-01'";
		expect = jdbcTemplate.queryForList(sql).size();
		actual = batchDAO.findAllAfterDate(Integer.MAX_VALUE, 1, 2017).size();
		//If SQL statement found at least 1 batch start date they should not equal. Otherwise they both should be equal
		if (expect > 0) {
			assertNotEquals(expect, actual);
		} else {
			assertEquals(expect, actual);
		}
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllByTrainer()
	 */
	@Test
	public void findAllByTrainerTest() {
		log.info("Testing the BatchDAO.findAllByTrainerTest()");
		//positive testing
		String sql = "SELECT TRAINER_ID FROM CALIBER_TRAINER WHERE ROWNUM = 1";
		Integer trainerId = jdbcTemplate.queryForObject(sql, Integer.class);
		sql = "SELECT * FROM CALIBER_BATCH WHERE TRAINER_ID = " + trainerId + " OR CO_TRAINER_ID = " + trainerId;
		int expect = jdbcTemplate.queryForList(sql).size();
		int actual = batchDAO.findAllByTrainer(trainerId).size();
		assertEquals(expect, actual);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrent()
	 */
	@Test
	public void findAllCurrentTest() {
		log.info("Testing the BatchDAO.findAllCurrentTest()");
		//This allows for 1 month flexibility. This was needed because in BatchDao, the query takes into account 1 month ago as 'current'
		int endDateLimit = 30; 
		String sql = "SELECT * FROM CALIBER_BATCH WHERE END_DATE+" + endDateLimit+" >= TO_DATE(SYSDATE,'YYYY/MM/DD') AND START_DATE <= TO_DATE(SYSDATE,'YYYY/MM/DD');";
		int expect = jdbcTemplate.queryForList(sql).size();
		int actual = batchDAO.findAllCurrent().size();
		assertEquals(expect, actual);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO.findOne(Integer batchId)
	 */
	@Test
	public void findOneTest(){
		log.info("Testing method BatchDAO.findOne(Integer batchId)");
		int expected = 2050;
		int actual = batchDAO.findOne(expected).getBatchId();
		assertEquals(expected, actual);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO.findOneWithDroppedTrainees(Integer batchId)
	 * Finds from a batch with known dropped trainees, upon finding a single trainee with
	 * the TrainingStatus of Dropped, calls it good.
	 */
	@Test
	public void findOneWithDroppedTraineesTest(){
		log.info("Testing method BatchDAO.findOneWithDroppedTrainees(Integer batchId)");
		boolean success = false;
		Set<Trainee> resultSet = batchDAO.findOneWithDroppedTrainees(2150).getTrainees();
		for (Trainee resultSetTrainee : resultSet){
			if (resultSetTrainee.getTrainingStatus() == TrainingStatus.Dropped){
				success = true;
				break;
			}
		}
		assertTrue(success);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO.findOneWithTraineesAndGrades(Integer batchId)
	 */
	@Test
	public void findOneWithTraineesAndGradesTest(){
		log.info("Testing method BatchDAO.findOneWithTraineesAndGrades(Integer batchId)");
		String expected = "1602 Feb08 Java";
		String actual = batchDAO.findOneWithTraineesAndGrades(2050).getTrainingName();
		assertEquals(expected, actual);
		boolean success = true;
		Set<Trainee> resultSet = batchDAO.findOneWithTraineesAndGrades(2050).getTrainees();
		for (Trainee resultSetTrainee : resultSet){
			if (resultSetTrainee.getTrainingStatus() == TrainingStatus.Dropped){
				success = false;
				break;
			}
		}
		assertTrue(success);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO.update()
	 */
	@Test
	public void updateTest(){
		log.info("Testing method BatchDAO.update(Batch batch)");
		Batch testBatch = batchDAO.findOne(2050);
		testBatch.setLocation("The basement");
		batchDAO.update(testBatch);
		Batch updatedTestBatch = batchDAO.findOne(2050);
		assertEquals(updatedTestBatch.getLocation(),"The basement");
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO.save(Batch batch)
	 */
	@Test
	public void saveTest(){
		log.info("Testing method BatchDAO.save(Batch batch)");
		Trainer testTrainer = new Trainer("Sir. Test","Tester","test@test.test",TrainerRole.ROLE_TRAINER);
		testTrainer.setTrainerId(2);
		Batch testBatch = new Batch("Test Name",testTrainer, Date.from(Instant.now()),Date.from(Instant.now().plus(Period.ofDays(60))),"Test Location");
		batchDAO.save(testBatch);
		List<Batch> resultSet = batchDAO.findAll();
		boolean success = false;
		for(Batch found: resultSet){
			if(found.getLocation().equals("Test Location")){
				success = true;
				break;
			}
		}
		assertTrue(success);
	}
}
