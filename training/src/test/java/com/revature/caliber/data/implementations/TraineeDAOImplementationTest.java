package com.revature.caliber.data.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.data.TraineeDAO;

/**
 * Test for TraineeDAOImplementation. Assumes that DB has at least one trainee
 * with id 1. (IMPORTANT) //TODO fix
 */
public class TraineeDAOImplementationTest {

	private static ApplicationContext context;
	private static SessionFactory sf;
	private static Logger logger;

	private static int newBatchId, newTrainerId, newTierId, newTraineeId;

	@BeforeClass
	public static void preClass() {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");

		// create transient SQL test data
		sf = (SessionFactory) context.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "";
		int index = 0;
		int resultNum = 0;

		index = 1;
		do {
			sql = "SELECT count(*) from CALIBER_TIER where TIER_ID  = ?";
			Query q = session.createSQLQuery(sql);
			q.setInteger(0, index++);
			resultNum = ((BigDecimal) q.uniqueResult()).intValue();
		} while (resultNum > 0);

		newTierId = index - 1;

		sql = "INSERT INTO CALIBER_TIER(TIER_ID, RANKING, TIER) VALUES(?, ?, ?)";
		Query tierq = session.createSQLQuery(sql);
		tierq.setInteger(0, newTierId);
		tierq.setInteger(1, 999);
		tierq.setString(2, "Test tier (TraineeDAO Test)");

		resultNum = tierq.executeUpdate();

		if (resultNum != 1) {
			tx.commit();
			session.close();
			fail("Failed to create test tier");
		}

		index = 1;
		do {
			sql = "SELECT count(*) FROM CALIBER_TRAINER WHERE TRAINER_ID = ?";
			Query q = session.createSQLQuery(sql);
			q.setInteger(0, index++);
			resultNum = ((BigDecimal) q.uniqueResult()).intValue();
		} while (resultNum > 0);

		newTrainerId = index - 1;

		sql = "INSERT INTO CALIBER_TRAINER(TRAINER_ID, EMAIL, NAME, SF_ACCOUNT, SF_AUTHENTICATION_TOKEN, SF_REFRESH_TOKEN, TITLE, TIER) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		Query trainerq = session.createSQLQuery(sql);
		trainerq.setInteger(0, newTrainerId);
		trainerq.setString(1, "email");
		trainerq.setString(2, "Test trainee (TraineeDAO Test)");
		trainerq.setString(3, "sf_account");
		trainerq.setString(4, "sf_auth_token");
		trainerq.setString(5, "sf_refr_token");
		trainerq.setString(6, "title");
		trainerq.setInteger(7, newTierId);

		resultNum = trainerq.executeUpdate();

		if (resultNum != 1) {
			tx.commit();
			session.close();
			fail("Failed to create test Trainer");
		}

		index = 1;
		do {
			sql = "SELECT count(*) FROM CALIBER_BATCH WHERE BATCH_ID = ?";
			Query q = session.createSQLQuery(sql);
			q.setInteger(0, index++);
			resultNum = ((BigDecimal) q.uniqueResult()).intValue();

		} while (resultNum > 0);

		newBatchId = index - 1;

		sql = "INSERT INTO CALIBER_BATCH(BATCH_ID, BORDERLINE_GRADE_THRESHOLD, END_DATE, GOOD_GRADE_THRESHOLD, LOCATION, SKILL_TYPE, START_DATE, TRAINING_NAME, TRAINING_TYPE, TRAINER_ID)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Query batchq = session.createSQLQuery(sql);
		batchq.setInteger(0, newBatchId);
		batchq.setInteger(1, 10);
		batchq.setString(2, "13-NOV-13");
		batchq.setInteger(3, 11);
		batchq.setString(4, "test location");
		batchq.setString(5, "test skill_type");
		batchq.setString(6, "15-NOV-13");
		batchq.setString(7, "Test batch (TraineeDAO test)");
		batchq.setString(8, "type");
		batchq.setInteger(9, newTrainerId);

		resultNum = batchq.executeUpdate();

		if (resultNum != 1) {
			tx.commit();
			session.close();
			fail("Failed to create test Batch");
		}

		index = 1;
		do {
			sql = "SELECT count(*) FROM CALIBER_TRAINEE WHERE TRAINEE_ID = ?";
			Query q = session.createSQLQuery(sql);
			q.setInteger(0, index++);
			resultNum = ((BigDecimal) q.uniqueResult()).intValue();

		} while (resultNum > 0);

		newTraineeId = index - 1;

		sql = "INSERT INTO CALIBER_TRAINEE(TRAINEE_ID, TRAINEE_EMAIL, TRAINEE_NAME, TRAINING_STATUS, BATCH_ID) "
				+ "VALUES(?, ?, ?, ?, ?)";
		Query traineeq = session.createSQLQuery(sql);
		traineeq.setInteger(0, newTraineeId);
		traineeq.setString(1, "new_email");
		traineeq.setString(2, "Test Trainee (TraineeDAO Test)");
		traineeq.setString(3, "new_status");
		traineeq.setInteger(4, newBatchId);

		resultNum = traineeq.executeUpdate();

		if (resultNum != 1) {
			tx.commit();
			session.close();
			fail("Failed to create test Batch");
		}

		tx.commit();
		session.close();
		// end of creating data

		logger = Logger.getRootLogger();
		logger.debug("\n--- TRAINEE DAO IMPLEMENTATION TEST START ---\n");
	}

	@AfterClass
	public static void afterClass() {

		// Delete created data
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sql = "";
		Query q;

		sql = "DELETE FROM CALIBER_TRAINEE WHERE TRAINEE_ID = ?";
		q = session.createSQLQuery(sql);
		q.setInteger(0, newTraineeId);
		q.executeUpdate();

		sql = "DELETE FROM CALIBER_BATCH WHERE BATCH_ID = ?";
		q = session.createSQLQuery(sql);
		q.setInteger(0, newBatchId);
		q.executeUpdate();

		sql = "DELETE FROM CALIBER_TRAINER WHERE TRAINER_ID = ?";
		q = session.createSQLQuery(sql);
		q.setInteger(0, newTrainerId);
		q.executeUpdate();

		sql = "DELETE FROM CALIBER_TIER WHERE TIER_ID = ?";
		q = session.createSQLQuery(sql);
		q.setInteger(0, newTierId);
		q.executeUpdate();

		sql = "DELETE FROM CALIBER_TRAINEE WHERE TRAINEE_ID = ?";
		q = session.createSQLQuery(sql);
		q.setInteger(0, newTraineeId);
		q.executeUpdate();

		tx.commit();
		session.close();
		// end delete of data

		logger.debug("\n--- TRAINEE DAO IMPLEMENTATION TEST END ---\n");
	}

	@Test
	public void createTraineeTest() {
		logger.debug("   Create trainee test.");

		TraineeDAO dao = context.getBean(TraineeDAO.class);

		Batch batch = new Batch();
		batch.setBatchId(newBatchId);

		Trainee trainee = new Trainee();
		trainee.setTraineeId(1);
		trainee.setName("Super Mario Bros");
		trainee.setEmail("tismario@mario.io");
		trainee.setTrainingStatus("Super Dope");
		trainee.setBatch(batch);

		dao.createTrainee(trainee);
		assertTrue(true); // if doesn't throw Exception, means created

		logger.debug("     trainee created");
	}

	@Test
	public void getTraineeTestGetById() {
		logger.debug("   Get trainee by id test.");

		TraineeDAO dao = context.getBean(TraineeDAO.class);

		logger.debug("     using id " + newTraineeId);
		Trainee trainee = dao.getTrainee(newTraineeId);

		assertNotNull(trainee);
		assertEquals(newTraineeId, trainee.getTraineeId());

		logger.debug("     trainee that I got:" + trainee);
		logger.debug("       trainee id: " + trainee.getTraineeId());
	}

	@Test
	public void getTraineeTestGetByName() {
		logger.debug("   Get trainee by name test.");
		logger.debug("     trying to get previously create trainee \"Super Mario Bros\"");

		TraineeDAO dao = context.getBean(TraineeDAO.class);

		Trainee trainee = dao.getTrainee("Super Mario Bros");

		assertNotNull(trainee);
		assertEquals("Super Mario Bros", trainee.getName());

		logger.debug("     trainee that I got:" + trainee);
		logger.debug("       trainee name: " + trainee.getName());
	}

	@Test
	public void getTraineesInBatchTest() {
		logger.debug("   Get trainees in a batch test.");
		logger.debug("     \"Super Mario Bros\" trainee is in the batch with id 1");

		TraineeDAO dao = context.getBean(TraineeDAO.class);

		List<Trainee> trainees = dao.getTraineesInBatch(newBatchId);

		assertNotNull(trainees);
		assertNotEquals(0, trainees.size());

		logger.debug("     trainees that I got " + trainees);
		logger.debug("     their size(should be at least 1): " + trainees.size());
	}

	@Test
	public void updateTraineeTest() {
		logger.debug("   Update trainee test.");
		logger.debug("     let's take \"Super Mario Bros\" and change it's name");

		TraineeDAO dao = context.getBean(TraineeDAO.class);

		Trainee trainee = dao.getTrainee("Super Mario Bros");
		assertNotNull(trainee);

		String newName = "Trololo lolo lolo";
		trainee.setName(newName);

		int id = trainee.getTraineeId();

		dao.updateTrainee(trainee);

		logger.debug("     updated trainee");

		trainee = dao.getTrainee(id);
		assertNotNull(trainee);
		assertEquals(newName, trainee.getName());

		logger.debug("     checking trainee:");
		logger.debug("       trainee that I got: " + trainee);
		logger.debug("       it's name: " + trainee.getName());
	}

	@Test
	public void deleteTraineeTest() {
		logger.debug("   Delete trainee test.");
		logger.debug("     let's get trainee with id 1 and just wipe it!");

		TraineeDAO dao = context.getBean(TraineeDAO.class);

		Trainee trainee = dao.getTrainee("Trololo lolo lolo");
		assertNotNull(trainee);

		logger.debug("     trainee was read.");

		int id = trainee.getTraineeId();

		dao.deleteTrainee(trainee);

		trainee = dao.getTrainee(id);
		assertNull(trainee);

		logger.debug("     trainee with id [" + id + "] was deleted.");
	}

}
