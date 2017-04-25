package com.revature.caliber;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
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
	private BatchDAO batchDAO;
	
	@Test
	@Ignore
	public void testSetSessionFactory() {

	}

	@Test
	@Ignore
	public void testSave() {

	}

	@Test
	@Ignore
	public void testFindAll() {
		List<Batch> batches = batchDAO.findAllCurrent();
		log.info("Number of Batches: " + batches.size());
		for(Trainee t : batches.get(0).getTrainees()){
				log.info("Number of Notes: " + t.getNotes().size());
		}
		
	}

	@Test
	@Ignore
	public void testFindAllByTrainer() {
		List<Batch> batches = batchDAO.findAllByTrainer(23);
		for(Batch b : batches){
			log.info("Batch Name: " + b.getTrainingName());
			log.info("Num Trainees: " + b.getTrainees().size());
		}
	}

	@Test
	@Ignore
	public void testFindAllCurrentInteger() {

	}

	@Test
	@Ignore
	public void testFindAllCurrent() {

	}

	@Test
	@Ignore
	public void testFindOne() {
		Batch b = batchDAO.findOne(1050);
		Integer count = b.getTrainees().size();
		log.info("Number of Trainees in Batch: ");
		log.info(count);
	}

	@Test
	@Ignore
	public void testUpdate() {

	}

	@Test
	@Ignore
	public void testDelete() {

	}

	@Test
	@Ignore
	public void testFindCommonLocations() {

	}
	
	@Test
	@Ignore
	public void testFindAllAfterDate(){
		Integer month = 3;
		Integer day = 28;
		Integer year = 2017;
		List<Batch> batches = batchDAO.findAllAfterDate(month, day, year);
		log.info("TESTING => findAllAfterDate");
		log.info(batches.size());
		for(Batch b: batches){
			log.info("Grades Size: " + b.getTrainees().iterator().next().getGrades().size());
		}
	}

}
