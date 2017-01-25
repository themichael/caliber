package com.revature.caliber.assessments.data.implementations;

<<<<<<< HEAD
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;
=======
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
>>>>>>> c43c8df3317f30945a49523e62942b32c36aaf3d

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.TrainerNoteDAO;

import java.util.Set;

public class TrainerNoteDAOImplTest {
<<<<<<< HEAD
	private static AbstractApplicationContext ctxt;
	private TrainerNoteDAO trainerNoteDAO;
	static Logger logger;

	@BeforeClass
	public static void setUp() {
		ctxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		// rootLogger is for debugging purposes
		logger = Logger.getRootLogger();
	}

	@Before
	public void setUpTest() {
		trainerNoteDAO = (TrainerNoteDAO) ctxt.getBean("trainerNoteDAO");
	}

	@Test
	@Ignore
	public void createTrainerNoteTest() {
		logger.debug("Starting createTrainerNoteTest....");
		TrainerNote newTrainerNote = new TrainerNote();

		newTrainerNote.setTrainer(1);
		newTrainerNote.setNoteId(1);
		newTrainerNote.setWeek(1);
		newTrainerNote.setContent("This trainee is not as productive as he could be.");
		newTrainerNote.setSugarCoatedContent("This trainee needs some polishing but gives good effort.");
		ctxt.getBean(TrainerNoteDAO.class).createTrainerNote(newTrainerNote);

		assertNotNull(newTrainerNote);
		logger.debug("BatchNote was successfully created");
	}
	
	@Test
	@Ignore
	public void getTrainerNoteByIdTest(){
		Integer trainerNoteId = 6600;
		logger.debug("Starting Get Trainer Note By ID Test....");
		TrainerNote trainerNote = ctxt.getBean(TrainerNoteDAO.class).getTrainerNoteById(trainerNoteId);
		assertNotNull(trainerNote);
		System.out.println(trainerNote);
		logger.debug("Get the TrainerNoteID=" + trainerNoteId + ". Note Contents: " + trainerNote);
	}
	
	@Test
	@Ignore
	public void getTrainerNoteForTrainerWeekTest(){
		Integer trainerId = 1;
		Integer weekId = 1;
		logger.debug("Starting Get Trainer Note For Trainer Week Test....");
		TrainerNote trainerNote = ctxt.getBean(TrainerNoteDAO.class).getTrainerNoteForTrainerWeek(trainerId, weekId);
		assertNotNull(trainerNote);
		logger.debug("Get the TrainerNote for the Trainer's Week. Note Contents: " + trainerNote);
	}
	
	@Test
	//@Ignore
	public void getTrainerNotesByTrainerTest(){
		Integer trainerId = 1;
		logger.debug("Starting Get Trainer Notes By Trainer Test....");
		Set<TrainerNote> trainerNotes = ctxt.getBean(TrainerNoteDAO.class).getTrainerNotesByTrainer(trainerId);
		assertNotNull(trainerNotes);
		logger.debug("Get all the TrainerNotes for TrainerID: " + trainerId + ". Note Contents: "+ trainerNotes);
	}
	
	@Test
	@Ignore
	public void getTrainerNotesByWeekTest(){
		Integer weekId = 1;
		logger.debug("Starting Get Trainer Notes By Week Test....");
		Set<TrainerNote> trainerNotes = ctxt.getBean(TrainerNoteDAO.class).getTrainerNotesByTrainer(weekId);
		assertNotNull(trainerNotes);
		logger.debug("Get all the TrainerNotes by WeekID: " + weekId + ". Note Contents: "+ trainerNotes);
	}
	
	
	@Test
	@Ignore
	public void updateTrainerNoteTest(){
		Integer trainerNoteId = 1;  
		String stringCoated = "This is the Trainer's Sugar Coated Comments.";
		String content = "These are the Trainer's actual comments.";
		
		TrainerNote trainerNote = ctxt.getBean(TrainerNoteDAO.class).getTrainerNoteById(trainerNoteId);
		trainerNote.setContent(content);
		trainerNote.setSugarCoatedContent(stringCoated);
		ctxt.getBean(TrainerNoteDAO.class).updateTrainerNote(trainerNote);
		
		assertNotNull(trainerNote);
		logger.debug("Updated the TrainerNote");
	}
	
	
	@Test
	@Ignore
	public void deleteTrainerNoteTest(){
		Integer trainerNoteId = 1;  
		
		TrainerNote trainerNote = ctxt.getBean(TrainerNoteDAO.class).getTrainerNoteById(trainerNoteId);
		ctxt.getBean(TrainerNoteDAO.class).deleteTrainerNote(trainerNote);
		
		assertNull(trainerNote);
		logger.debug("Deleted the TrainerNote");
	}

	
=======
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
		sf = context.getBean(SessionFactory.class);
		populateTable();
	}

	/**
	 * Populates table with Assessment used for testing
	 */
	private static void populateTable() {
		String sql = "";

		sql = "INSERT INTO CALIBER_NOTE(NOTE_ID, NOTE_CONTENT, NOTE_SUGAR)" +
				" VALUES (?, ?, ?)";

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Query noteq = session.createSQLQuery(sql);
		noteq.setInteger(0, 3050);
		noteq.setString(1, "content");
		noteq.setString(2, "sugar");
		noteq.executeUpdate();

		sql = "INSERT INTO CALIBER_TRAINER_NOTE (TRAINER_ID, WEEK_ID, NOTE_ID) " +
				"VALUES(?, ?, ?)";

		Query trainerNote = session.createSQLQuery(sql);

		trainerNote.setInteger(0, trainerId);
		trainerNote.setInteger(1, weekId);
		trainerNote.setInteger(2, id);
		trainerNote.executeUpdate();

		tx.commit();
		session.close();
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

		String note = "Testing content: this is content 12";
		String sugarNote = "Testing sugar note";

		TrainerNote trainerNote = new TrainerNote();
		trainerNote.setTrainer(45);
		trainerNote.setWeek(6690);
		trainerNote.setContent(note);
		trainerNote.setSugarCoatedContent(sugarNote);

		trainerNoteDAO.createTrainerNote(trainerNote);

		//Test if it was created
		Session session = ((SessionFactory) context.getBean("sessionFactory")).openSession();
		Criteria criteria = session.createCriteria(TrainerNote.class);
		criteria.add(Restrictions.eq("content", "Testing content: this is content 12"));
		TrainerNote newnote = (TrainerNote) criteria.uniqueResult();
		assertEquals(trainerNote.getNoteId(), newnote.getNoteId());
		assertEquals(trainerNote.getContent(), newnote.getContent());

		//cleanup
		Transaction tx = session.beginTransaction();
		session.delete(newnote);
		tx.commit();
		session.close();

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
		trainerNoteDAO.deleteTrainerNote(trainerNote);
		assertNotNull(trainerNote);

		logger.debug("Ending updateTrainerNote");
	}
>>>>>>> c43c8df3317f30945a49523e62942b32c36aaf3d
}
