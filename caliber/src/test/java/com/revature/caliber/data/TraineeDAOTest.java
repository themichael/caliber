/*package com.revature.caliber.data;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class TraineeDAOTest {

	private final static Logger log = Logger.getLogger(TraineeDAOTest.class);
	@Autowired
	TraineeDAO dao = new TraineeDAO();

	@Autowired
	BatchDAO bDAO = new BatchDAO();
	
	
	
	 * For testing deleting and saving of trainees
	 
	Batch batch;
	
	Trainee trainee = new Trainee("Tester", "Test@Test.com", TrainingStatus.Confirmed
			  , "234-567-8901", "SkyperTest", "WWE.COM", batch);
	trainee.

	@Test
	public void testSetSessionFactory() {

		try {
			log.info(dao.findAll());
			log.info("Session was injected");
		} catch (NullPointerException e) {
			e.printStackTrace();
			fail("a Null pointer exception was thrown in the Test for the Session Factory");
		}
	}

	
	 * @Test public void testSave() { Batch batch = bDAO.findOne(1050);
	 * dao.save(new Trainee("Tester", "Test@Test.com", TrainingStatus.Confirmed
	 * , "234-567-8901", "SkyperTest", "WWE.COM", batch));
	 * 
	 * 
	 * }
	 

	
	 * @Test public void testDelete() { Batch batch = bDAO.findOne(1050);
	 * Trainee trainee = dao.findOne(2200); dao.delete(trainee); }
	 

	@Test
	public void testFindAll() {

		try {
			System.out.println(dao.findAll());
		} catch (NullPointerException e) {
			fail("exception was thrown" + e.getStackTrace());
		}
	}

	@Test
	public void testFindAllByBatch() {
		try {
			System.out.println(dao.findAllByBatch(1050));
		} catch (NullPointerException e) {
			fail("exception was thrown" + e.getStackTrace());
		}
	}

	@Test
	public void testFindAllByTrainer() {
		try {
			System.out.println(dao.findAllByTrainer(1050));
		} catch (NullPointerException e) {
			fail("exception was thrown" + e.getStackTrace());
		}
	}

	
	 * @Test @Autowired public void testFindOne() {
	 * 
	 * /*System.out.println(dao.findOne(1050)); }
	 

	
	 * @Test public void testFindByEmail() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testUpdate() { fail("Not yet implemented"); }
	 

}
*/