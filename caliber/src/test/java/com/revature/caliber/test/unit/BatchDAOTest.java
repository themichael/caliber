package com.revature.caliber.test.unit;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.TrainingStatus;
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
		log.info(batches.size());
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
