package com.revature.caliber.test.unit;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

import java.util.List;

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
		//List<Batch> list = batchDAO.findAllCurrent(1);
		//assertEquals(list.size(),3);
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithNotes()
	 */
	@Test
	public void findAllCurrentWithNotesTest(){
		
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithNotesAndTrainees()
	 */
	@Test
	public void findAllCurrentWithNotesAndTraineesTest(){
		
	}
	
	/**
	 * Tests methods:
	 * @see com.reavture.caliber.data.BatchDAO#findAllCurrentWithTrainees()
	 */
	@Test
	public void findAllCurrentWithTraineesTest(){
		
	}
}
