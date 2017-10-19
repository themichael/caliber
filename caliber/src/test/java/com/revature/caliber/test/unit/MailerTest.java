package com.revature.caliber.test.unit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
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
	private Trainer Gray;
	private Trainer Patrick;
	private Trainer Dan;
	private Trainer Orson;
	private Trainer Shelby;
	
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
		this.Gray = this.trainerDAO.findByEmail("grawyne@gmail.com");
		this.Patrick = this.trainerDAO.findByEmail("patrick.walsh@revature.com");
		this.Dan = this.trainerDAO.findByEmail("pjw6193@hotmail.com");
		this.Orson = this.trainerDAO.findByEmail("owallace@mailinator.com");
		this.Shelby = this.trainerDAO.findByEmail("slevinson@mailinator.com");
	}
	
	/**
	 * Gray Wynne should be absent from the collection, because he submitted all his grades
	 */
	@Test
	public void testGrayAbsent() {
		assertFalse(this.trainersToSubmitGrades.contains(this.Gray));
	}
	
	/**
	 * Patrick Walsh should be in the collection, because he has submitted only some grades
	 */
	@Test
	public void testPatrickPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.Patrick));
	}
	
	/**
	 * Dan Pickles should be in the collection, because he has not submitted any grades
	 */
	@Test
	public void testDanPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.Dan));
	}
	
	/**
	 * Orson Wallace should be in the collection, because he has submitted all but one grade
	 */
	@Test
	public void testOrsonPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.Orson));
	}
	
	@Test
	public void testShelbyPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.Shelby));
	}

}
