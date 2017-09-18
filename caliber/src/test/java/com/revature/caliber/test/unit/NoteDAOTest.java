package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.beans.QCStatus;

@Transactional
public class NoteDAOTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(NoteDAOTest.class);
	
	@Autowired
	private NoteDAO noteDao;
	@Autowired
	private BatchDAO batchdao;
	@Autowired
	private TraineeDAO traineedao;
	
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

	@Test
	// saving a note
	public void testSaveNote() {
		log.trace("Tesing Save Note");

		final Batch batch = batchdao.findOne(2201);
		final Trainee trainee = traineedao.findAll().get(0);
		final short week = 1;

		Note note = new Note();
		note.setNoteId(0);
		note.setContent("Note Test");
		note.setBatch(batch);
		note.setQcFeedback(false);
		note.setWeek(week);
		note.setTrainee(trainee);
		note.setQcStatus(null);
		note.setMaxVisibility(null);
		note.setType(null);

		noteDao.save(note);

	}

	@Test
	// update the notes content.
	public void testUpdateNote() {
		log.trace("Testing updating note");

		// set the batch and week number
		final int batchId = 2201;
		final int week = 2;

		// get the list of individual notes

		List<Note> notes = noteDao.findIndividualNotes(batchId, week);

		// testing the list size;

		assertTrue(notes.size() > 0);

		Note note = notes.get(1);

		// Old Content = Superstar. Great communication skill and good solid
		// knowledge.

		String oldContent = note.getContent();

		note.setContent("Hello");

		noteDao.update(note);

		String newContentSet = note.getContent();

		// Old Content should not equal new Content if updated
		assertNotEquals(oldContent, newContentSet);

	}

	@Test
	// finding batch notes
	public void testfindBatchNotes() {
		log.trace("Finding Batch Notes");

		final int batchId = 2100;
		final int week = 2;

		// get the list of batch notes
		List<Note> batchNotes = noteDao.findBatchNotes(batchId, week);

		System.out.println("Batch notes: " + batchNotes.size());

		// only 1 batch note
		assertTrue(batchNotes.size() == 1);
	}

	@Test
	public void testfindIndividualNotes() {
		log.trace("Finding Individual Notes");

		// set the batch and week number
		final int batchId = 2201;
		final int week = 2;

		// get the list of individual notes

		List<Note> notes = noteDao.findIndividualNotes(batchId, week);

		// check if the individual notes size is equal to 16

		assertTrue(notes.size() == 16);

	}

}
