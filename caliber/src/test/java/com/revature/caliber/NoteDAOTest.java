/**
 * 
 */
package com.revature.caliber;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.data.NoteDAO;

/**
 * @author Sudish
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class NoteDAOTest {

	private static Logger log = Logger.getLogger(NoteDAOTest.class);

	@Autowired
	private NoteDAO noteDAO;

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#setSessionFactory(org.hibernate.SessionFactory)}
	 * .
	 */
	@Test
	public void testSetSessionFactory() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#save(com.revature.caliber.beans.Note)}
	 * .
	 */
	@Test
	public void testSave() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#update(com.revature.caliber.beans.Note)}
	 * .
	 */
	@Test
	public void testUpdate() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	@Ignore
	public void testFindBatchNotes() {
		List<Note> notes = noteDAO.findBatchNotes(1050, 5);
		List<Trainee> trainees = new ArrayList<>();
		for(Note n: notes){
			trainees.addAll(n.getBatch().getTrainees());
		}
		testTrainees(trainees);
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findIndividualNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	@Ignore
	public void testFindIndividualNotes() {
		List<Note> notes = noteDAO.findIndividualNotes(1050, 6);
		List<Trainee> trainees = new ArrayList<>();
		for(Note n: notes){
			trainees.add(n.getTrainee());
		}
		testTrainees(trainees);
	}
	
	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findQCBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	@Ignore
	public void testFindQCBatchNotes() {
		Note note = noteDAO.findQCBatchNotes(1050, 5);
		List<Trainee> trainees = new ArrayList<>(note.getBatch().getTrainees());
		testTrainees(trainees);
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findQCIndividualNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindQCIndividualNotes() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllBatchNotes() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllIndividualNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllIndividualNotes() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllPublicIndividualNotes(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllPublicIndividualNotes() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllQCBatchNotes(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllQCBatchNotes() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllQCTraineeNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllQCTraineeNotes() {
		
	}

	public void testTrainees(List<Trainee> trainees){
		log.info("SIze of Trainees: " + trainees.size());
		Integer notDropped = 0;
		Integer dropped = 0;
		for(Trainee t: trainees){
			if(t.getTrainingStatus().equals(TrainingStatus.Dropped)){
				dropped++;
			} else {
				notDropped++;
			}
		}
		assertTrue(dropped == 0);
		assertTrue(trainees.size() == notDropped);
	}
	
}
