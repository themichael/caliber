package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.data.NoteDAO;

public class NoteDAOTest extends CaliberTest {
	
	private static final Logger log = Logger.getLogger(NoteDAOTest.class);
	
	@Autowired
	private NoteDAO noteDao;
	
	@Test
	public void testFindTraineeNote(){
		log.trace("Testing findTraineeNote");
		final int traineeID = 5350;
		final int weekNum = 2;
		final String content = "Superstar. Impeccable project, very strong technically.";
		
		Note actual = noteDao.findTraineeNote(traineeID, weekNum);

		assertFalse(actual.isQcFeedback());
		assertEquals(content, actual.getContent());
		assertEquals(weekNum, actual.getWeek());
		assertEquals(traineeID, actual.getTrainee().getTraineeId());
	}
	
	@Test
	public void testFindQCTraineeNote(){
		log.trace("Testing findQCTraineeNote");
		final int traineeID = 5532;
		final int weekNum = 3;
		final QCStatus status = QCStatus.Good;
		
		Note actual = noteDao.findQCTraineeNote(traineeID, weekNum);

		assertTrue(actual.isQcFeedback());
		assertEquals(status, actual.getQcStatus());
		assertEquals(weekNum, actual.getWeek());
		assertEquals(traineeID, actual.getTrainee().getTraineeId());
	}
	
	@Test
	public void testFindQCBatchNotes(){
		log.trace("Testing findQCBatchNotes");
		final int batchId = 2201;
		final int week = 5;
		
		Note actual = noteDao.findQCBatchNotes(batchId, week);
		
		assertEquals(batchId, actual.getBatch().getBatchId());
		assertEquals(week, actual.getWeek());
	}
	
	@Test
	public void testFindAllBatchQcNotes(){
		log.trace("Testing findAllBatchQcNotes");
		final int batchId = 2201;
		final int batchSize = 7;
		
		List<Note> actual = noteDao.findAllBatchQcNotes(batchId);
		
		assertEquals(batchSize, actual.size());
	}
	

}
