package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.TraineeDAO;

/**
 * @author Connor Monson
 */

public class PanelDAOTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(PanelDAOTest.class);

	@Autowired
	private PanelDAO panelDAO;

	public void setPanelDAO(PanelDAO panelDAO) {
		this.panelDAO = panelDAO;
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.PanelDAO#findAll()
	 */
	@Test
	public void findAllTest() {
		log.info("Testing the PanelDAO.findAll()");
		String sql = "SELECT * FROM CALIBER_PANEL";
		int expect = jdbcTemplate.queryForList(sql).size();
		int actual = panelDAO.findAll().size();
		assertEquals(expect, actual);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.PanelDAO#findAllByTrainee() Returns
	 *      panels with specified trainee
	 */
	@Test
	public void findAllByTraineeTest() {
		log.info("Testing the PanelDAO.findAllByTraineeTest()");
		// positive testing
		Integer traineeId = 5500;
		String sql = "SELECT * FROM CALIBER_PANEL WHERE TRAINEE_ID=" + traineeId;
		int expect = jdbcTemplate.queryForList(sql).size();
		List<Panel> panels = panelDAO.findAllByTrainee(traineeId);
		int actual = panels.size();
		assertEquals(expect, actual);

		// negative testings
		traineeId = Integer.MIN_VALUE;
		expect = 0;
		actual = panelDAO.findAllByTrainee(traineeId).size();
		assertEquals(expect, actual);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.PanelDAO.findOne(Integer panelId)
	 * Find a known panel, assert that the IDs are the same.
	 * Try to find a panel that doesn't exist, fail if it does.
	 */
	@Test
	public void findOneTest() {
		log.info("Testing method PanelDAO.findOne(Integer panelId)");
		int expected = 40;
		int actual = panelDAO.findOne(expected).getId();
		assertEquals(expected, actual);
		try{
			expected = -234;
			actual = panelDAO.findOne(expected).getId();
			fail();
		}
		catch(Exception e){
			log.info(e);
		}
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.PanelDAO.update()
	 * This test needs the findOne method to work.
	 * It finds a panel from the database, changes a value, updates the database,
	 * loads the panel from the database again.
	 * Tries to update a non-existing panel.
	 */
	@Test
	public void updateTest() {
		log.info("Testing method PanelDAO.update(Panel panel)");
		Panel testPanel = panelDAO.findOne(1);
		testPanel.setPanelRound(100);
		panelDAO.update(testPanel);
		
		Panel updatedTestPanel = panelDAO.findOne(1);
		assertEquals(updatedTestPanel.getPanelRound(), 100);
		try{
			testPanel.setId(-984);
			panelDAO.update(testPanel);
			fail();
		}
		catch(Exception e){
			log.info(e);
		}
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.data.PanelDAO.Delete(Panel panel)
	 */
	@Test
	public void saveTest() {
		log.info("Testing method PanelDAO.saveTest()");
		Panel testPanel = new Panel();
		Trainer testTrainer = new Trainer("Sir. Test", "Tester", "test@test.test", TrainerRole.ROLE_TRAINER);
		testTrainer.setTrainerId(2);
		testPanel.setPanelist(testTrainer);
		Batch testBatch = new Batch("Test Name", testTrainer, Date.from(Instant.now()),
				Date.from(Instant.now().plus(Period.ofDays(60))), "Test Location");
		TraineeDAO traineeDao = new TraineeDAO();
		Trainee testTrainee = traineeDao.findOne(5500);
		testPanel.setTrainee(testTrainee);
		testPanel.setStatus(PanelStatus.Pass);
		testPanel.setPanelRound(1);
		
		panelDAO.save(testPanel);
		List<Panel> resultSet = panelDAO.findAll();
		boolean success = false;
		for (Panel found : resultSet) {
			if (testPanel.equals(found)) {
				success = true;
				break;
			}
		}
		assertTrue(success);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.PanelDAO#findAllCurrent() The PanelDAO
	 *      findAllCurrent takes into account 30 days before the current date.
	 *      It also removes dropped trainees from the paneles returned
	 */
	@Test
	public void findAllRepanelTest() {
		log.info("Testing the PanelDAO.findAllRepanelTest()");
		
		String sql = "SELECT * FROM CALIBER_PANEL WHERE panel_status = 'Repanel'";
		int expect = jdbcTemplate.queryForList(sql).size();
		List<Panel> paneles = panelDAO.findAllRepanel();
		int actual = paneles.size();
		assertEquals(expect, actual);

	}

}
