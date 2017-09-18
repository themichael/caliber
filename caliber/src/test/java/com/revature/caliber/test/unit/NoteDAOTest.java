package com.revature.caliber.test.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;

public class NoteDAOTest extends CaliberTest {
	
	private static final Logger log = Logger.getLogger(NoteDAOTest.class);
	
	private static final int TEST_TRAINEE_ID = 5529;
	private static final int TEST_BATCH_ID = 2100;
	
	@Autowired
	private NoteDAO noteDAO;
	@Autowired
	private TraineeDAO traineeDAO;
	@Autowired
	private BatchDAO batchDAO;
	
	/**
	 * Test methods:
	 * Positive tests for finding individual notes for trainee
	 * @see com.revature.caliber.data.GradeDAO#save(Grade)
	 */
	@Test
	public void findQCIndividualNote(){
		log.trace("GETTING individual notes by Trainee");
		//get trainee 
		Trainee trainee = traineeDAO.findOne(TEST_TRAINEE_ID);
		
		List<Note> notes = noteDAO.findQCIndividualNotes(trainee.getTraineeId(), 2);
		assertEquals(notes.size(),1);
		assertEquals(notes.get(0).getContent(),"technically weak on SQL.");
		
		
	}
	
	@Test
	public void findAllBatchNotes(){
		log.trace("GETTING individual notes by Batch");
		//get batch 
		Batch batch = batchDAO.findOne(TEST_BATCH_ID);
		
		List<Note> notes = noteDAO.findAllBatchNotes(batch.getBatchId(), 2);
		assertEquals(notes.size(),1);
		
	}
	
	@Test
	public void findAllIndividualNotes(){
		log.trace("GETTING individual notes by Batch");
		//get batch 
		Trainee trainee = traineeDAO.findOne(TEST_TRAINEE_ID);
		
		List<Note> notes = noteDAO.findAllIndividualNotes(trainee.getTraineeId(), 2);
		assertEquals(notes.size(),1);
		/*for(Note note:notes){
			assertEquals(note.getContent(),"");
		}*/
		
	}
	
	
	@Test
	public void findAllPublicIndividualNotes(){
		log.trace("GETTING all public individual notes by Trainee ID");
		//get batch 
		Trainee trainee = traineeDAO.findOne(TEST_TRAINEE_ID);
		
		List<Note> notes = noteDAO.findAllPublicIndividualNotes(trainee.getTraineeId());
		assertTrue(notes.size() > 0);
		/*for(Note note:notes){
			assertEquals(note.getContent(),"");
		}*/
		
	}

}
