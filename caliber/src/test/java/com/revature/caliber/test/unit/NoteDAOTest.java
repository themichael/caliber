package com.revature.caliber.test.unit;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Note;
import com.revature.caliber.data.NoteDAO;
@Transactional
public class NoteDAOTest extends CaliberTest {
	
	@Autowired
	NoteDAO noteDAO;
	
	private static final Logger log = Logger.getLogger(NoteDAOTest.class);
	private static final int TEST_BATCH_ID = 2201;
	private static final int TEST_WEEK=7;
	private static final int TEST_TRAINER_ID=5524;

	@Test	
	public void findAllQCBatchNotes(){
		log.trace("GETTING ALL QC BATCH NOTES");
		List<Note> notes = noteDAO.findAllQCBatchNotes(TEST_BATCH_ID);
		assertTrue(notes.size()>0);
	}
	
	@Test	
	public void findAllQCTraineeNotes(){
		log.trace("GETTING ALL QC TRAINEE NOTES");
		List<Note> notes = noteDAO.findAllQCTraineeNotes(TEST_BATCH_ID, TEST_WEEK);
		assertTrue(notes.size()>0);
	}
	
	@Test	
	public void findAllQCTraineeOverallNotes(){
		log.trace("GETTING ALL QC TRAINEE OVERALL NOTES");
		List<Note> notes = noteDAO.findAllQCTraineeOverallNotes(TEST_TRAINER_ID);
		assertTrue(notes.size()>0);
	}
}
