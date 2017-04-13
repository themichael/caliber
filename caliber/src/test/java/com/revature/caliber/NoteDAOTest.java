/**
 * 
 */
package com.revature.caliber;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.event.TransactionalEventListener;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainerRole;
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
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#save(com.revature.caliber.beans.Note)}
	 * .
	 */
	@Test
	@Transactional
	public void testSave() {
		// TODO
		Note note = new Note();
		assertNotNull(noteDAO.save(note));
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#update(com.revature.caliber.beans.Note)}
	 * .
	 */
	@Test
	public void testUpdate() {
		// TODO
		try {

			// noteDAO.update(note);
		} catch (Exception e) {
			fail("Could not update " + e);
		}
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindBatchNotes() {
		// TODO
		try {
			log.info(noteDAO.findAllBatchNotes(1077, 3));
		} catch (Exception e) {
			fail("cannot find batch note successfully");
		}
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findIndividualNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindIndividualNotes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findQCBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindQCBatchNotes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findQCIndividualNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindQCIndividualNotes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllBatchNotes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllIndividualNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllIndividualNotes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllPublicIndividualNotes(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllPublicIndividualNotes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllQCBatchNotes(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllQCBatchNotes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findAllQCTraineeNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindAllQCTraineeNotes() {
		fail("Not yet implemented"); // TODO
	}

}
