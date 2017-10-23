package com.revature.caliber.test.unit;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.TrainerDAO;
import com.revature.caliber.email.Mailer;

/**
 * Tests that the correct trainers are selected for emailing.
 * @author Will Underwood
 *
 */
public class MailerTest extends CaliberTest {
	
	private Mailer mailer;
	private Set<Trainer> trainersToSubmitGrades;
	private TrainerDAO trainerDAO;
	private Trainer gray;
	private Trainer patrick;
	private Trainer dan;
	private Trainer orson;
	private Trainer shelby;
	
	@Autowired
	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}
	
	@Autowired
	public void setTrainerDAO(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	/**
	 * Finds trainers who need to submit grades
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.trainersToSubmitGrades = this.mailer.getTrainersWhoNeedToSubmitGrades();
		this.gray = this.trainerDAO.findByEmail("grawyne@gmail.com");
		this.patrick = this.trainerDAO.findByEmail("patrick.walsh@revature.com");
		this.dan = this.trainerDAO.findByEmail("pjw6193@hotmail.com");
		this.orson = this.trainerDAO.findByEmail("owallace@mailinator.com");
		this.shelby = this.trainerDAO.findByEmail("slevinson@mailinator.com");
	}
	
	/**
	 * Gray Wynne should be absent from the collection, because he submitted all his grades
	 */
	@Test
	public void testGrayAbsent() {
		assertFalse(this.trainersToSubmitGrades.contains(this.gray));
	}
	
	/**
	 * Patrick Walsh should be in the collection, because he has submitted only some grades
	 */
	@Test
	public void testPatrickPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.patrick));
	}
	
	/**
	 * Dan Pickles should be in the collection, because he has not submitted any grades
	 */
	@Test
	public void testDanPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.dan));
	}
	
	/**
	 * Orson Wallace should be in the collection, because he has submitted all but one grade
	 */
	@Test
	public void testOrsonPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.orson));
	}
	
	/**
	 * Shelby Levinson should be in the collection, because she submitted only one grade
	 */
	@Test
	public void testShelbyPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.shelby));
	}

}
