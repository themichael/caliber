package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.data.BatchDAO;

public class BatchDAOTest extends CaliberTest{
	
	private static final Logger log = Logger.getLogger(BatchDAOTest.class);
	
	@Autowired
	private BatchDAO batchDAO;

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.BatchDAO#findAllCurrent(trainerId)
	 */
	@Test
	public void findAllCurrentIntTest(){
		log.info("Testing the BatchDAO.findAllCurrent(trainerId)");
		List<Batch> batches = batchDAO.findAllCurrent(1);
		assertEquals(4, batches.size());
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithNotes()
	 */
	@Test
	public void findAllCurrentWithNotesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithNotesTest()");
		List<Batch> batches = batchDAO.findAllCurrentWithNotes();
		assertEquals(1,batches.size());
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithNotesAndTrainees()
	 */
	@Test
	public void findAllCurrentWithNotesAndTraineesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithNotesAndTrainees()");
		List<Batch> batches = batchDAO.findAllCurrentWithNotesAndTrainees();
		assertEquals(1,batches.size());
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithTrainees()
	 */
	@Test
	public void findAllCurrentWithTraineesTest(){
		log.info("Testing the BatchDAO.findAllCurrentWithTrainees()");
		List<Batch> batches = batchDAO.findAllCurrentWithTrainees();
		assertEquals(4,batches.size());
	}
}
