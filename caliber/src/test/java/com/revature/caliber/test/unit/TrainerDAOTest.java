package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.TrainerDAO;

public class TrainerDAOTest extends CaliberTest {

	private static final String NOT_YET_IMPLEMENTED = "Not yet implemented";

	private static final Logger log = Logger.getLogger(TrainerDAOTest.class);
	private TrainerDAO trainerDAO;

	@Autowired
	public void setTrainerDAO(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	@Test
	public void testFindAllTrainerTitles() {
		log.info("Find all trainer titles");
		List<String> expected = Arrays
				.asList(new String[] { "Senior Trainer", "Senior Technical Manager", "Lead Trainer",
						"Vice President of Technology", "Senior Java Developer", "Technology Manager", "Trainer" });
		assertTrue(trainerDAO.findAllTrainerTitles().containsAll(expected));
	}

	@Test
	public void testFindByEmail() {
		log.info("Find trainer by email");
		String email = "patrick.walsh@revature.com";
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", email, TrainerRole.ROLE_VP);
		assertEquals(expected, trainerDAO.findByEmail(email));
	}

	@Test
	public void testFindAll() {
		log.info("Find all trainers");
		assertEquals(20, trainerDAO.findAll().size());
	}

	@Test
	public void testSave() {
		log.info("Save a new trainer");
		int before = trainerDAO.findAll().size();
		trainerDAO.save(new Trainer("Alex Cobian", "Genius Trainer", "cobian448@yahoo.com", TrainerRole.ROLE_VP));
		int after = trainerDAO.findAll().size();
		assertEquals(before, --after);
	}

	@Test
	public void testFindOne() {
		log.info("Find trainer by id");
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		assertEquals(expected, trainerDAO.findOne(1));
	}

	@Test
	public void testUpdate() {

		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	@Test
	public void testDelete() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

}
