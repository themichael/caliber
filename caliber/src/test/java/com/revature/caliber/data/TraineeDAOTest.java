package com.revature.caliber.data;

import static org.junit.Assert.*;

import java.util.List;

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

	/* For testing deleting and saving of trainees */
	Batch batch;

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

	@Test
	public void testSave() {
		Batch batch = bDAO.findOne(1050);

		try {
			dao.save(new Trainee("Tester", "Test@Test.com", TrainingStatus.Confirmed, "234-567-8901", "SkyperTest",
					"WWE.COM", batch));
			assertNotNull(dao.findByEmail("Test@Test.com"));
			log.info("test Save persisted the new trainee Objects");

		} catch (NullPointerException e) {
			log.info("test Save did not persist the new trainee Object");
		}
	}

	@Test
	public void testDelete() {
		try {
			Trainee trainee = dao.findByEmail("Test@Test.com");
			assertNotNull(dao.findByEmail("Test@Test.com"));
			log.info("test delete retrieved the trainee Object");
			dao.delete(trainee);
			assertNull(dao.findByEmail("Test@Test.com"));
			log.info("test delete deleted the trainee Object");
		} catch (Exception e) {
			log.info("The trainee Object wasn't deleted");
		}
	}

	@Test
	public void testFindAll() {

		try {
			List<Trainee> list = dao.findAll();
			assertNotNull(list);
		} catch (NullPointerException e) {
			log.info("testFind all didn't retrieve ");
			fail("exception was thrown" + e.getStackTrace());
		}
	}

	@Test
	public void testFindAllByBatch() {
		try {
			List<Trainee> list = dao.findAllByBatch(1050);
			assertNotNull(list);
		} catch (NullPointerException e) {
			log.info("testFind all by batch didn't retrieve anything");
			fail("exception was thrown" + e.getStackTrace());
		}
	}

	@Test
	public void testFindAllByTrainer() {
		try {
			List<Trainee> list = dao.findAllByTrainer(1050);
			assertNotNull(list);
		} catch (NullPointerException e) {
			log.info("testFind all by trainer didn't retrieve anything");
			fail("exception was thrown" + e.getStackTrace());
		}
	}

	/*@Test
	public void testFindOne() {
		try {
			Trainee trainee = dao.findOne(1050);
			log.info("<----------------------------------------------------------------------->" + trainee);
			assertNotNull(trainee);
		} catch (NullPointerException e) {
			log.info("testFind one retrieve anything");
			fail("exception was thrown" + e.getStackTrace());
		}

	}*/

	/*
	 * @Test public void testFindByEmail() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testUpdate() { fail("Not yet implemented"); }
	 */

}
