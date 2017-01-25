package com.revature.caliber.assessments.data.implementations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.TrainerNoteDAO;

public class TrainerNoteDAOImplTest {
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

	
}
