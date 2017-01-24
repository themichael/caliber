package com.revature.caliber.assessments.data.implementations;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

import com.revature.caliber.assessments.beans.TrainerNote;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.assessments.data.TrainerNoteDAO;

import java.util.Set;

public class TrainerNoteDAOImplTest {
	private static AbstractApplicationContext context;
	private static TrainerNoteDAO trainerNoteDAO;
	private static SessionFactory sf;
	static Logger logger;
	private static int id = 3050, trainerId = 5, weekId = 123;

	@BeforeClass
	public static void preClass () {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		logger = Logger.getRootLogger();
		trainerNoteDAO = context.getBean(TrainerNoteDAO.class);
		logger.debug("Starting TrainerNoteTest");
		sf = (SessionFactory) context.getBean("sessionFactory");
		populateTable();
	}

	/**
	 * Populates table with Assessment used for testing
	 */
	private static void populateTable() {
		//Adding required data
/*		String note = "Testing note";
		String sugarNote = "Testing sugar note";
		TrainerNote trainerNote = new TrainerNote();
		trainerNote.setTrainer(trainerId);
		trainerNote.setWeek(weekId);
		trainerNote.setContent(note);
		trainerNote.setSugarCoatedContent(sugarNote);*/

		//trainerNoteDAO.createTrainerNote(trainerNote);

		String sql = "";

		sql = "INSERT INTO CALIBER_NOTE(NOTE_ID, NOTE_CONTENT, NOTE_SUGAR)" +
				" VALUES (?, ?, ?)";

		Query noteq = sf.getCurrentSession().createSQLQuery(sql);
		noteq.setInteger(1, 3050);
		noteq.setString(2, "content");
		noteq.setString(3, "sugar");
		noteq.executeUpdate();

		sql = "INSERT INTO CALIBER_TRAINER_NOTE (TRAINEE_ID, WEEK_ID, NOTE_ID) " +
				"VALUES(?, ?, ?)";

		Query trainerNote = sf.getCurrentSession().createSQLQuery(sql);
		trainerNote.setInteger(1, trainerId);
		trainerNote.setInteger(2, weekId);
		trainerNote.setInteger(3, id);
		trainerNote.executeUpdate();
	}


	@AfterClass
	public static void cleanUp() {

		int trainerNoteId = 3050;

		TrainerNote trainerNote = trainerNoteDAO.getTrainerNoteById(trainerNoteId);

		if (trainerNote != null) {
			trainerNoteDAO.deleteTrainerNote(trainerNote);
			trainerNote = trainerNoteDAO.getTrainerNoteById(trainerNoteId);
			assertNull(trainerNote);
		}

		logger.debug("Ending AssessmentServiceTest");
	}

	@Test
	public void createTrainerNoteTest(){
		logger.debug("Starting create trainer note");

		String note = "Testing note";
		String sugarNote = "Testing sugar note";

		TrainerNote trainerNote = new TrainerNote();
		trainerNote.setTrainer(45);
		trainerNote.setWeek(6690);
		trainerNote.setContent(note);
		trainerNote.setSugarCoatedContent(sugarNote);

		trainerNoteDAO.createTrainerNote(trainerNote);

		logger.debug("Ending create trainer note");
	}

	@Test
	public void getTrainerNoteById(){
		int id = 3050;
		logger.debug("Starting getTrainerNoteById = " + id);

		TrainerNote trainerNote = trainerNoteDAO.getTrainerNoteById(id);
		assertNotNull(trainerNote);

		logger.debug("Ending getTrainerNoteById");
	}

	@Test
	public void getTrainerNoteByTrainerWeek(){
		logger.debug("Starting getTrainerNoteByTrainerWeek = " + trainerId + " " + weekId);

		TrainerNote trainerNote = trainerNoteDAO.getTrainerNoteForTrainerWeek(trainerId, weekId);
		assertNotNull(trainerNote);

		logger.debug("Ending getTrainerNoteByTrainerWeek ");
	}

	@Test
	public void getTrainerNoteByTrainer(){
		logger.debug("Starting getTrainerNoteByTrainer = " + trainerId);

		Set<TrainerNote> trainerNote = trainerNoteDAO.getTrainerNotesByTrainer(trainerId);
		assertNotNull(trainerNote);

		logger.debug("Ending getTrainerNoteByTrainer");
	}

	@Test
	public void getTrainerNoteByWeek(){
		logger.debug("Starting getTrainerNoteByWeek = " + weekId);

		Set<TrainerNote> trainerNote = trainerNoteDAO.getTrainerNotesByTrainer(weekId);
		assertNotNull(trainerNote);

		logger.debug("Ending getTrainerNoteByWeek");
	}

	@Test
	public void updateTrainerNote(){
		int id = 3050;
		logger.debug("Starting updateTrainerNote = " + id);

		TrainerNote trainerNote = trainerNoteDAO.getTrainerNoteById(id);
		trainerNote.setContent("Updated content");
		trainerNoteDAO.updateTrainerNote(trainerNote);
		assertNotNull(trainerNote);

		logger.debug("Ending updateTrainerNote");
	}

	@Test
	public void deleteTrainerNote(){
		logger.debug("Starting deleteTrainerNote = " + id);

		TrainerNote trainerNote = trainerNoteDAO.getTrainerNoteById(id);
		trainerNoteDAO.updateTrainerNote(trainerNote);
		assertNotNull(trainerNote);

		logger.debug("Ending updateTrainerNote");
	}
}
