package com.revature.caliber.test.unit;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;

@Transactional
public class NoteDAOTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(NoteDAOTest.class);

	@Autowired
	private NoteDAO notedao;
	@Autowired
	private BatchDAO batchdao;
	@Autowired
	private TraineeDAO traineedao;

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

		notedao.save(note);

	}

	@Test
	// update the notes content.
	public void testUpdateNote() {
		log.trace("Testing updating note");

		// set the batch and week number
		final int batchId = 2201;
		final int week = 2;

		// get the list of individual notes

		List<Note> notes = notedao.findIndividualNotes(batchId, week);

		// testing the list size;

		assertTrue(notes.size() > 0);

		Note note = notes.get(1);

		// Old Content = Superstar. Great communication skill and good solid
		// knowledge.

		String oldContent = note.getContent();

		note.setContent("Hello");

		notedao.update(note);

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
		List<Note> batchNotes = notedao.findBatchNotes(batchId, week);

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

		List<Note> notes = notedao.findIndividualNotes(batchId, week);

		// check if the individual notes size is equal to 16

		assertTrue(notes.size() == 16);

	}

}
