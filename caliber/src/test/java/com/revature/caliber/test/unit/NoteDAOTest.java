package com.revature.caliber.test.unit;


import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;


import java.util.List;

import org.apache.log4j.Logger;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;

import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.beans.QCStatus;

import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;


import com.revature.caliber.beans.QCStatus;


@Transactional
public class NoteDAOTest extends CaliberTest {


	private static final Logger log = Logger.getLogger(NoteDAOTest.class);

	private static final int TEST_TRAINEE_ID = 5529;
	private static final int TEST_BATCH_ID = 2100;
	
	@Autowired
	private NoteDAO noteDao;
	@Autowired
	private BatchDAO batchDao;
	@Autowired
	private TraineeDAO traineeDao;
	
	/**
	 * Test methods:
	 * Positive tests for finding individual notes for trainee
	 * @see com.revature.caliber.data.GradeDAO#save(Grade)
	 */
	@Test
	public void findQCIndividualNote(){
		log.trace("GETTING individual notes by Trainee");
		//get trainee 
		Trainee trainee = traineeDao.findOne(TEST_TRAINEE_ID);
		
		List<Note> notes = noteDao.findQCIndividualNotes(trainee.getTraineeId(), 2);
		assertEquals(notes.size(),1);
		assertEquals(notes.get(0).getContent(),"technically weak on SQL.");
		
		
	}
	
	/**
	 * Test methods:
	 * Positive tests for finding individual notes for a bacth in a given week
	 * @see com.revature.caliber.data.NoteDAO#findAllBatchNotes(batch,week)
	 */
	@Test
	public void findAllBatchNotes(){
		log.trace("GETTING individual notes by Batch");
		//get batch 
		Batch batch = batchDao.findOne(TEST_BATCH_ID);
		
		List<Note> notes = noteDao.findAllBatchNotes(batch.getBatchId(), 2);
		assertEquals(notes.size(),1);
		assertEquals(notes.get(0).getNoteId(),5133);
		
	}
	
	/**
	 * Test methods:
	 * Positive tests for finding individual notes for a trainee in a given week
	 * @see com.revature.caliber.data.NoteDAO#findAllIndividualNotes(trainee,week)
	 */
	@Test
	public void findAllIndividualNotes(){
		log.trace("GETTING individual notes by Batch");
		//get batch 
		Trainee trainee = traineeDao.findOne(TEST_TRAINEE_ID);
		
		List<Note> notes = noteDao.findAllIndividualNotes(trainee.getTraineeId(), 2);
		assertEquals(notes.size(),1);
		assertEquals(notes.get(0).getContent(),"Good communication. Not as confident and less use of technical terms");
		
		
	}
	
	/**
	 * Test methods:
	 * Positive tests for finding all individual notes for a trainee
	 * @see com.revature.caliber.data.NoteDAO#findAllPublicIndividualNotes(trainee)
	 */
	@Test
	public void findAllPublicIndividualNotes(){
		log.trace("GETTING all public individual notes by Trainee ID");
		//get batch 
		Trainee trainee = traineeDao.findOne(TEST_TRAINEE_ID);
		
		List<Note> notes = noteDao.findAllPublicIndividualNotes(trainee.getTraineeId());
		assertTrue(notes.size() == 7);
		
		for(Note note : notes){
			assertEquals(trainee,note.getTrainee());
		}
		
	}

	
	
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
	public void findAllQCBatchNotes(){
		log.trace("GETTING ALL QC BATCH NOTES");
		int batch_id = 2201;
		List<Note> notes = noteDao.findAllQCBatchNotes(batch_id);
		
		int[] expected = {6369,6390,6391,6420,6438,6457,6470};
		assertTrue(notes.size()>0);
		for(int j = 0; j<expected.length; j++){
		assertEquals(expected[j], notes.get(j).getNoteId());
		}
	}
	
	@Test	
	public void findAllQCTraineeNotes(){
		log.trace("GETTING ALL QC TRAINEE NOTES");
		int batch_id = 2201;
		int week = 7;
		List<Note> notes = noteDao.findAllQCTraineeNotes(batch_id, week);
		assertTrue(notes.size()>0);
		int[] expected = {6459,6460,6461,6462,6463,6464,6465,6466,6467,6468,6469,6471,6472,6473,6474,6475};
		for(int j = 0; j<expected.length; j++){
			assertEquals(expected[j], notes.get(j).getNoteId());
		}
	}
	
	
	@Test	
	public void findAllQCTraineeOverallNotes(){
		log.trace("GETTING ALL QC TRAINEE OVERALL NOTES");
		int trainee_id=5524;
		List<Note> notes = noteDao.findAllQCTraineeOverallNotes(trainee_id);
		assertTrue(notes.size()>0);
		int[] expected = {6355,6381,6394,6439,6423,6453,6463};
		for(int j = 0; j<expected.length; j++){
			assertEquals(expected[j], notes.get(j).getNoteId());
		}
	}

	@Test
	// saving a note
	public void testSaveNote() {
		log.trace("Tesing Save Note");

		final Batch batch = batchDao.findOne(2201);
		final Trainee trainee = traineeDao.findAll().get(0);
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
