package com.revature.caliber.assessments.data.implementations;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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

		
	@Test
	public void createBatchNoteTest() {	
		logger.debug("Starting createBatchNoteTest....");
		BatchNote newBatchNote = new BatchNote();

		newBatchNote.setBatch(5);
		newBatchNote.setNoteId(5);
		newBatchNote.setWeek(5);
		newBatchNote.setContent("This batch has a hard time understanding Spring.");
		newBatchNote.setSugarCoatedContent("Very good on all topics but still can improve.");
		ctxt.getBean(BatchNoteDAO.class).createBatchNote(newBatchNote);

		assertNotNull(newBatchNote);
		logger.debug("BatchNote was successfully created");
	}
	
	@Test
	public void getBatchNoteTest() {
		logger.debug("Starting getBatchNoteTest....");
		BatchNote batchNote = ctxt.getBean(BatchNoteDAO.class).getBatchNote(1, 1);
		assertNotNull(batchNote);
		logger.debug("Get the BtachNote object: " + batchNote);
	}
	
	/* TODO need to change by accepting BatchNote object */
	@Test
	public void getAllBatchNotesTest() {
		logger.debug("Starting getAllBatchNotesTest....");
		int batch = 1;
		List<BatchNote> batchNotes = ctxt.getBean(BatchNoteDAO.class).allBatchNotes(batch);
		assertNotNull(batchNotes);
		logger.debug("All of Batch " + batch + "'s batchNotes: " + batchNotes);
	}

	@Test
	public void getAllBatchNotesforWeekTest() {
		
		logger.debug("Starting getAllBatchNotesForWeekTest....");
		int weekId = 1;
		List<BatchNote> batchNotesForWeek = ctxt.getBean(BatchNoteDAO.class).allBatchNotesByWeek(weekId);

		assertNotNull(batchNotesForWeek);
		logger.debug("All of BatchNotes for all batches within week " + weekId + ": " + batchNotesForWeek);
	}

	@Test
	public void updateBatchNoteTest() {
		
		logger.debug("Starting updatingBatchNoteTest");
		int batch = 1;
		int week = 1;
		String stringCoated = "This is the Sugar Coated Comments";
		String content = "These are the unfiltered comments of the QC board";

		BatchNote batchNote = ctxt.getBean(BatchNoteDAO.class).getBatchNote(batch, week);

		batchNote.setContent(content);
		batchNote.setSugarCoatedContent(stringCoated);
		ctxt.getBean(BatchNoteDAO.class).updateBatchNote(batchNote);

		assertNotNull(batchNote);
		logger.debug("Update BatchNote pertaining to batch ID='" + batch + "' and weekID= '" + week
				+ "' with new contents.");
	}

	
	@Test
	public void deleteBatchNote() {
		
		logger.debug("Starting deleteBatchNoteTest....");
		BatchNote batchNote = ctxt.getBean(BatchNoteDAO.class).getBatchNote(5, 5);
		ctxt.getBean(BatchNoteDAO.class).deleteBatchNote(batchNote);

		assertNotNull(batchNote);
		logger.debug("BatchNote was deleted");
	}

}
