package com.revature.caliber.data.implementations;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.BatchDAO;
import com.revature.caliber.training.data.TrainerDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BatchDAOImplementationTest {
	private static AbstractApplicationContext context;
	private static SessionFactory sf;
	static Logger log;
	static int id = 2434;
	private static BatchDAO batchDAO;

	@BeforeClass
	public static void preClass () {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		log = Logger.getRootLogger();
		batchDAO = context.getBean(BatchDAO.class);
		log.debug("Starting BatchTest");
		sf = context.getBean(SessionFactory.class);
	}

	/**
	 * Populates table with Assessment used for testing
	 */
	private static void populateTable() {
		TrainerDAO trainerDAO = context.getBean(TrainerDAO.class);
		Date startDate = new Date(1481394352000L);
		Date endDate = new Date(1458757552000L);

		String sql = "INSERT INTO CALIBER_Batch(BATCH_ID, TRAINER_ID, END_DATE, START_DATE, LOCATION, BORDERLINE_GRADE_THRESHOLD)" +
				" VALUES (?, ?, ?, ?, ?, ?)";

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Query noteq = session.createSQLQuery(sql);
		noteq.setInteger(0, 2);
		noteq.setInteger(1, 1);
		noteq.setTimestamp(2, endDate);
		noteq.setTimestamp(3, startDate);
		noteq.setString(4, "New York");
		noteq.setInteger(5, 60);

		noteq.executeUpdate();

		tx.commit();
		session.close();
	}

	@AfterClass
	public static void cleanUp() {

		int trainerNoteId = 3050;

		Batch batch = batchDAO.getBatch(id);

		if (batch != null) {
			batchDAO.deleteBatch(batch);
			batch = batchDAO.getBatch(id);
			assertNull(batch);
		}

		log.debug("Ending AssessmentServiceTest");
	}

	// Works
	@Test
	@Ignore
	public void createBatch() {
		log.debug("Create batch test.");

		TrainerDAO trainerDAO = context.getBean(TrainerDAO.class);
		Trainer trainer = trainerDAO.getTrainer(1);
		Tier tier = new Tier();
		tier.setTierId((short) 1);
		Date startDate = new Date(1481394352000L);
		Date endDate = new Date(1458757552000L);
		Batch batch = new Batch(9999, "trainingName", trainer, null, "skillType", "trainingType", startDate, endDate,
				"Virgina", (short) 60, (short) 80, null, null);

		batchDAO.createBatch(batch);

		log.debug("Batch created");

		Session session = ((SessionFactory) context.getBean("sessionFactory")).openSession();
		Criteria criteria = session.createCriteria(Batch.class);
		criteria.add(Restrictions.eq("LOCATION", "Virgina"));
		Batch newBatch = (Batch) criteria.uniqueResult();
		assertEquals(batch.getBatchId(), newBatch.getBatchId());
		assertEquals(batch.getLocation(), newBatch.getLocation());

		//cleanup
		Transaction tx = session.beginTransaction();
		session.delete(newBatch);
		tx.commit();
		session.close();

		log.debug("Delete after create");

	}

	// Works
	@Test
	public void getAll() {
		log.debug(" Get all Batch test");
		List<Batch> batch = batchDAO.getAllBatch();
		log.debug("Got All " + batch);
	}

	// Work
	@Test
	public void getTrainerBatch() {
		log.debug("Get batch by Trainer id");
		List<Batch> batch = batchDAO.getTrainerBatch(1);
		log.debug("got batches by trainer id " + batch);
	}

	// Works
	@Test
	public void getCurrentBatch() {
		log.debug("Get active batches");
		List<Batch> batch = batchDAO.getCurrentBatch();
		log.debug("Got active batches " + batch);
	}

	// Work
	@Test
	public void getCurrentBatchWithId() {
		log.debug("Get active batches with trainer id");

		List<Batch> batch = batchDAO.getCurrentBatch(id);

		log.debug("Got active batches with trainer id " + batch);
	}

	// Works
	@Test
	public void getBatch() {
		log.debug("Get batch by id");

		Batch batch = batchDAO.getBatch(id);

		log.debug("got batch by id " + batch);
	}

	// Works
	@Test
	public void updateBatch() {
		log.debug("Updating batch");

		Batch batch = batchDAO.getBatch(id);
		batch.setLocation("New York");
		batchDAO.updateBatch(batch);

		log.debug("updated batch");
	}

	// Work on delete method

}