package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.data.BatchDAO;

public class BatchDAOTest extends CaliberTest {
	
	private static final Logger log = Logger.getLogger(BatchDAOTest.class);
	
	@Autowired
	private BatchDAO batchDAO;
	
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
		assertEquals(3, batches.size());
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrentWithNotes()
	 */
	@Test
	public void findAllCurrentWithNotesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithNotesTest()");
		List<Batch> batches = batchDAO.findAllCurrentWithNotes();
		assertEquals(1,batches.size());
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrentWithNotesAndTrainees()
	 */
	@Test
	public void findAllCurrentWithNotesAndTraineesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithNotesAndTrainees()");
		List<Batch> batches = batchDAO.findAllCurrentWithNotesAndTrainees();
		assertEquals(1,batches.size());
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrentWithTrainees()
	 */
	@Test
	public void findAllCurrentWithTraineesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithTrainees()");
		List<Batch> batches = batchDAO.findAllCurrentWithTrainees();
		assertEquals(3,batches.size());
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
}
