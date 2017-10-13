package com.revature.caliber.email;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.TrainerDAO;

/**
 * 
 * @author Will Underwood
 *
 */
public class MailerTest extends CaliberTest {
	
	private Mailer mailer;
	private Set<Trainer> trainersToSubmitGrades;
	private TrainerDAO trainerDAO;
	private Trainer Nick;
	private Trainer Gray;
	private Trainer Patrick;
	
	@Autowired
	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}
	
	@Autowired
	public void setTrainerDAO(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	@Before
	public void setUp() throws Exception {
		this.trainersToSubmitGrades = this.mailer.getTrainersWhoNeedToSubmitGrades();
		this.Nick = this.trainerDAO.findByEmail("nickolas.jurczak@revature.com");
		this.Gray = this.trainerDAO.findByEmail("grawyne@gmail.com");
		this.Patrick = this.trainerDAO.findByEmail("patrick.walsh@revature.com");
	}
	
	@Test
	public void testNickPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.Nick));
	}
	
	@Test
	public void testGrayPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.Gray));
	}
	
	@Test
	public void testPatrickAbsent() {
		assertFalse(this.trainersToSubmitGrades.contains(this.Patrick));
	}

}
