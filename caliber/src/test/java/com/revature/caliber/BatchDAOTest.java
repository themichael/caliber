package com.revature.caliber;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class BatchDAOTest {

	private static Logger log = Logger.getLogger(CheckDAO.class);

	@Autowired
	private BatchDAO batchdao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetSessionFactory() {

	}

	@Test
	public void testSave() {

	}

	@Test
	public void testFindAll() {
		List<Batch> batches = batchdao.findAllCurrent();
		log.info("Number of Batches: " + batches.size());
		for(Trainee t : batches.get(0).getTrainees()){
				log.info("Number of Notes: " + t.getNotes().size());
		}
		
	}

	@Test
	public void testFindAllByTrainer() {

	}

	@Test
	public void testFindAllCurrentInteger() {

	}

	@Test
	public void testFindAllCurrent() {

	}

	@Test
	public void testFindOne() {

	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testDelete() {

	}

	@Test
	public void testFindCommonLocations() {

	}

}
