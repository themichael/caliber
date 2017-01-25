package com.revature.caliber.assessments.data.implementations;

<<<<<<< HEAD
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
=======
import com.revature.caliber.assessments.data.BatchNoteDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
>>>>>>> c43c8df3317f30945a49523e62942b32c36aaf3d
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

<<<<<<< HEAD
import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.data.BatchNoteDAO;

public class BatchNoteDAOImplTest {

	private static ApplicationContext ctxt;
	private static Logger logger;
	

	@BeforeClass
	public static void setup() {
		ctxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		logger = Logger.getRootLogger();
	}

	/* TODO need to change by accepting BatchNote object */
	@Test
	@Ignore
	public void getAllBatchNotesTest() {
		logger.debug("Starting getAllBatchNotesTest....");
		int batch = 1;
		List<BatchNote> batchNotes = ctxt.getBean(BatchNoteDAO.class).allBatchNotes(batch);
		assertNotNull(batchNotes);
		logger.debug("All of Batch " + batch + "'s batchNotes: " + batchNotes);
	}

	@Test
	@Ignore
	public void getAllBatchNotesforWeekTest() {
		
		logger.debug("Starting getAllBatchNotesForWeekTest....");
=======
import static org.junit.Assert.assertTrue;

public class BatchNoteDAOImplTest {
    static Logger log;
    private static AbstractApplicationContext context;
    private BatchNoteDAO batchNoteDAO;

    @BeforeClass
    public static void setUp() {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        // rootLogger is for debugging purposes
        log = Logger.getRootLogger();
    }

    @Before
    public void setUpTest() {
        batchNoteDAO = (BatchNoteDAO) context.getBean("batchNoteDAO");
    }

/*
    @Test
	//TODO need to change by accepting BatchNote object
	    public void createBatchNoteTest(){
		int batchId = 1;
>>>>>>> c43c8df3317f30945a49523e62942b32c36aaf3d
		int weekId = 1;
		List<BatchNote> batchNotesForWeek = ctxt.getBean(BatchNoteDAO.class).allBatchNotesByWeek(weekId);

		assertNotNull(batchNotesForWeek);
		logger.debug("All of BatchNotes for all batches within week " + weekId + ": " + batchNotesForWeek);
	}

	@Test
	@Ignore
	public void updateBatchNoteTest() {
		
		logger.debug("Starting updatingBatchNoteTest");
		int batch = 1;
		int week = 1;
		String stringCoated = "This is the Sugar Coated Comments";
		String content = "These are the unfiltered comments of the QC board";

<<<<<<< HEAD
		BatchNote batchNote = ctxt.getBean(BatchNoteDAO.class).getBatchNote(batch, week);

		batchNote.setContent(content);
		batchNote.setSugarCoatedContent(stringCoated);
		ctxt.getBean(BatchNoteDAO.class).updateBatchNote(batchNote);

		assertNotNull(batchNote);
		logger.debug("Update BatchNote pertaining to batch ID='" + batch + "' and weekID= '" + week
				+ "' with new contents.");
	}

	@Test
	@Ignore
	public void createBatchNoteTest() {
		
		logger.debug("Starting createBatchNoteTest....");
		BatchNote newBatchNote = new BatchNote();

		newBatchNote.setBatch(3);
		newBatchNote.setNoteId(3);
		newBatchNote.setWeek(3);
		newBatchNote.setContent("This batch is horrible in design patterns.");
		newBatchNote.setSugarCoatedContent("Needs some improvement in design patterns.");
		ctxt.getBean(BatchNoteDAO.class).createBatchNote(newBatchNote);

		assertNotNull(newBatchNote);
		logger.debug("BatchNote was successfully created");
	}

	@Test
	@Ignore
	public void deleteBatchNote() {
		
		logger.debug("Starting deleteBatchNoteTest....");
		BatchNote batchNote = ctxt.getBean(BatchNoteDAO.class).getBatchNote(3, 3);
		ctxt.getBean(BatchNoteDAO.class).deleteBatchNote(batchNote);

		assertNotNull(batchNote);
		logger.debug("BatchNote was deleted");
	}

	@Test
	//@Ignore
	public void getBatchNoteTest() {
		logger.debug("Starting getBatchNoteTest....");
		BatchNote batchNote = ctxt.getBean(BatchNoteDAO.class).getBatchNote(1, 1);
		assertNotNull(batchNote);
		logger.debug("Get the BtachNote object: " + batchNote);
	}
=======
		BatchNote note = new BatchNote();
		note.setBatch(batchId);
		note.setWeek(weekId);

	    	batchNoteDAO.createBatchNote(batchId, weekId);

	    	//TODO need to fetch from database and assert not null on that
	    	assertTrue(true);
	    }*/

    @Test
    public void getBatchNoteforWeekTest() {
        int batchId = 1;
        int weekId = 1;
        batchNoteDAO.getBatchNote(batchId, weekId);
        assertTrue(true);
    }

    @Test
    public void getAllBatchNotesForWeekTest() {
        int weekId = 1;
        batchNoteDAO.allBatchNotesByWeek(weekId);
    }
>>>>>>> c43c8df3317f30945a49523e62942b32c36aaf3d

}
