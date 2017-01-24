package com.revature.caliber.assessments.data.implementations;

import static org.junit.Assert.*;

import com.revature.caliber.assessments.beans.TrainerNote;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.assessments.data.TrainerNoteDAO;

public class TrainerNoteDAOImplTest {
	 private static AbstractApplicationContext context;
	private TrainerNoteDAO trainerNoteDAO;
	static Logger log;

	@BeforeClass
	public static void setUp () {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		// rootLogger is for debugging purposes
		log = Logger.getRootLogger();
	}
	@Before
	public void setUpTest() {
		trainerNoteDAO = (TrainerNoteDAO) context.getBean("trainerNoteDAO");
	}

	@Test
	public void createTrainerNoteTest(){
		int trainerId = 1;
		int weekId = 1;
		String note = "Testing note";
		String sugerNote = "Testing sugar note";

		TrainerNote trainerNote = new TrainerNote();
		trainerNote.setTrainer(trainerId);
		trainerNote.setWeek(weekId);
		trainerNote.setContent(note);
		trainerNote.setSugarCoatedContent(sugerNote);

		trainerNoteDAO.createTrainerNote(trainerNote);
		assertTrue(true);
	}
/*
	@Test
	public void getTrainerNotesforWeek(){
		int trainerId = 1;
		int weekId = 1;
		trainerNoteDAO.getTrainerNoteForWeek(trainerId, weekId);
		assertTrue(true);
	}*/

/*	@Test
	public void getAllTrainerNotes(){
		int trainerId = 1;
		trainerNoteDAO.getTrainerNotesByTrainerId(trainerId);
		assertTrue(true);
	}*/

/*	@Test
	public void updateTrainerNote(){
		TrainerNote trainerNote = trainerNoteDAO.getTrainerNoteForWeek(1,1);
		trainerNote.setContent("Updated content");
		trainerNote.setSugarCoatedContent("Updated sugar content");
		trainerNoteDAO.updateTrainerNote(trainerNote);
	}*/

}
