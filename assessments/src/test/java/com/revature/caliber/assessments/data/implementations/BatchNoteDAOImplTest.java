package com.revature.caliber.assessments.data.implementations;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.assessments.data.BatchNoteDAO;

public class BatchNoteDAOImplTest {
	 private static AbstractApplicationContext context;
	    private BatchNoteDAO batchNoteDAO;
	    static Logger log;

	    @BeforeClass
	    public static void setUp () {
	        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	        // rootLogger is for debugging purposes
	        log = Logger.getRootLogger();
	    }
	    
	    @Before
	    public void setUpTest() {
	        batchNoteDAO = (BatchNoteDAO) context.getBean("batchNoteDAO");
	    }

	    @Test
	    public void createBatchNoteTest(){
	    	int batchId = 1;
	    	int weekId = 1;
	    	batchNoteDAO.createBatchNote(batchId, weekId);
	    	assertTrue(true);
	    }
	    
	    @Test
	    public void getBatchNoteforWeekTest(){
	    	int batchId = 1;
	    	int weekId = 1;
	    	batchNoteDAO.getBatchNote(batchId, weekId);
	    	assertTrue(true);
	    }
	    
	    @Test
	    public void getAllBatchNotesForWeekTest(){
	    	int weekId=1;
	    	batchNoteDAO.allBatchNotesByWeek(weekId);
	    }

}
