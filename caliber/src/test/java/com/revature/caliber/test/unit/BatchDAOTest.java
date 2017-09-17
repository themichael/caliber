package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.BatchDAO;

public class BatchDAOTest extends CaliberTest {

	@Autowired
	BatchDAO batchDAO;

	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	@Test
	public void findAllTest() {
		String sql = "SELECT * FROM CALIBER_BATCH";
		int expect = jdbcTemplate.queryForList(sql).size();
		int actual = batchDAO.findAll().size();
		assertEquals(expect, actual);
	}

	@Test
	public void findAllAfterDateTest() {
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

	@Test
	public void findAllByTrainerTest() {

		//positive testing
		String sql = "SELECT TRAINER_ID FROM CALIBER_TRAINER WHERE ROWNUM = 1";
		Integer trainerId = jdbcTemplate.queryForObject(sql, Integer.class);
		sql = "SELECT * FROM CALIBER_BATCH WHERE TRAINER_ID = " + trainerId + " OR CO_TRAINER_ID = " + trainerId;
		int expect = jdbcTemplate.queryForList(sql).size();
		int actual = batchDAO.findAllByTrainer(trainerId).size();
		assertEquals(expect, actual);
		
		//parameter testing
		trainerId = Integer.MAX_VALUE;
		sql = "SELECT * FROM CALIBER_BATCH WHERE TRAINER_ID = " + trainerId + " OR CO_TRAINER_ID = " + trainerId;
		expect = jdbcTemplate.queryForList(sql).size();
		actual = batchDAO.findAllByTrainer(trainerId).size();
		assertEquals(expect, actual);
		
		trainerId = Integer.MIN_VALUE;
		sql = "SELECT * FROM CALIBER_BATCH WHERE TRAINER_ID = " + trainerId + " OR CO_TRAINER_ID = " + trainerId;
		expect = jdbcTemplate.queryForList(sql).size();
		actual = batchDAO.findAllByTrainer(trainerId).size();
		assertEquals(expect, actual);
	}

	@Test
	public void findAllCurrentTest() {

	}
}
