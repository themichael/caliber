/**
 * 
 */
package com.revature.caliber;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.revature.caliber.beans.Note;
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
	@Transactional
	public void testSave() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#update(com.revature.caliber.beans.Note)}
	 * .
	 */
	@Test
	public void testUpdate() {
		// TODO
		fail("Could not update ");

	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindBatchNotes() {
		// TODO
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findIndividualNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindIndividualNotes() {
		
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.data.NoteDAO#findQCBatchNotes(java.lang.Integer, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindQCBatchNotes() {
		
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

}
