package com.revature.caliber.assessments.data.implementations;

import static org.junit.Assert.*;

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
	    	trainerNoteDAO.createTrainerNote(trainerId);
	    	assertTrue(true);
	    }
	    
	    @Test
	    public void getTrainerNotesforWeek(){
	    	int trainerId = 1;
	    	int weekId = 1;
	    	trainerNoteDAO.getTrainerNoteForWeek(trainerId, weekId);
	    	assertTrue(true);
	    }
	    
	    @Test
	    public void getAllTrainerNotes(){
	    	int trainerId = 1;
	    	trainerNoteDAO.getAllTrainerNotesByTrainer(trainerId);
	    	assertTrue(true);
	    }

}
