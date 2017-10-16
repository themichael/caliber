package com.revature.caliber.test.unit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.TrainerDAO;
import com.revature.caliber.email.Mailer;

/**
 * 
 * @author Will Underwood
 *
 */
public class MailerTest extends CaliberTest {
	
	private Mailer mailer;
	private Set<Trainer> trainersToSubmitGrades;
	private TrainerDAO trainerDAO;
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
		this.Gray = this.trainerDAO.findByEmail("grawyne@gmail.com");
		this.Patrick = this.trainerDAO.findByEmail("patrick.walsh@revature.com");
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
