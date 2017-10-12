package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.InterviewFormat;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

public class PanelFeedbackDAOTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(PanelFeedbackDAOTest.class);
	private static final String PANELF_COUNT = "SELECT count(panel_feedback_id) FROM caliber_panel_feedback";
	private static final String PANELF_COUNT_ID = PANELF_COUNT + " WHERE panel_id = ";

	private PanelFeedbackDAO dao;
	private CategoryDAO cDAO;
	private PanelDAO pDAO;
	private TraineeDAO teDAO;
	private TrainerDAO trDAO;

	@Autowired
	public void setDao(PanelFeedbackDAO dao) {
		this.dao = dao;
	}

	@Autowired
	public void setCategoryDao(CategoryDAO dao) {
		this.cDAO = dao;
	}

	@Autowired
	public void setPanelDao(PanelDAO dao) {
		this.pDAO = dao;
	}

	@Autowired
	public void setTraineeDao(TraineeDAO dao) {
		this.teDAO = dao;
	}

	@Autowired
	public void setTrainerDao(TrainerDAO dao) {
		this.trDAO = dao;
	}

	/**
	 * Tests the save method in AddressDAO. Creates a new address and checks if
	 * the address appears, and then checks to make sure the size has increased.
	 */
	@Test
	public void saveFeedbackDAO() {
		log.info("Saving a new Feedback using PanelFeedbackDAO");
		int before = jdbcTemplate.queryForObject(PANELF_COUNT, Integer.class);
		PanelFeedback pf = new PanelFeedback();
		Panel p = new Panel();
		p.setFormat(InterviewFormat.Phone);
		p.setPanelRound(1);
		p.setStatus(PanelStatus.Pass);
		p.setTrainee(teDAO.findOne(1));
		p.setPanelist(trDAO.findOne(1));
		Category c = cDAO.findOne(1);

		pf.setComment("test");
		pf.setResult(5);
		pf.setTechnology(c);
		pf.setStatus(PanelStatus.Pass);
		pf.setPanel(p);

		dao.save(pf);
		long pfid = pf.getId();
		System.out.println(pfid);
		int after = jdbcTemplate.queryForObject(PANELF_COUNT, Integer.class);
		assertEquals(pf.toString(), dao.findOne(pfid).toString());
		assertEquals(++before, after);
	}

	/**
	 * Tests getting all feedback from the database
	 */
	@Test
	public void findAllFeedbackDAO() {
		log.info("Getting all feedback using PanelFeedbackDAO getAll function");
		long num = jdbcTemplate.queryForObject(PANELF_COUNT, Long.class);
		assertNotNull(dao.findAll());
		assertEquals(dao.findAll().size(), num);
	}

	/**
	 * Tests getting all feedback for a certain panel feedback id
	 */
	@Test
	public void getFeedbackByIdDAO() {
		log.info("Finding feedback by panel id");
		long panelFId = 140;
		int expected = 70;
		assertEquals(dao.findOne(panelFId).getPanel().getId(), expected);
	}

	/**
	 * Tests to make sure an address not in the database returns null.
	 */
	@Test
	public void nullGetPanelFeedbackByInt() {
		log.info("Attempting to get a panel that doesn't exist");
		PanelFeedback feedback = dao.findOne(99999999);
		assertNull(feedback);
	}

	/**
	 * Tests update method of FeedbackDAO. Asserts that addresses zipcode was
	 * changed.
	 */
	@Test
	public void updateFeedbackDAO() {
		log.info("UpdateFeedbackDAO Test");
		String comment = "11111";
		long panelFId = 10;

		PanelFeedback actual = dao.findOne(panelFId);

		actual.setComment(comment);
		dao.update(actual);
		assertEquals(dao.findOne(panelFId).getComment(), actual.getComment());
	}

	/**
	 * Test finding all feedbacks for a panel id
	 */
	@Test
	public void findAllForPanelDAO() {
		int panelId = 40;
		int actual = dao.findAllForPanel(panelId).size();
		int expected = jdbcTemplate.queryForObject(PANELF_COUNT_ID + panelId, Integer.class);

		assertEquals(expected, actual);

		panelId = -789;
		actual = dao.findAllForPanel(panelId).size();
		expected = jdbcTemplate.queryForObject(PANELF_COUNT_ID + panelId, Integer.class);

		assertEquals(expected, actual);
	}

	/**
	 * 
	 */
	@Test
	public void findFailedFeedbackByPanelDAO() {
		// positive testing
		int panelId = 60;
		int actual = dao.findFailedFeedbackByPanel(pDAO.findOne(panelId)).size();
		int expected = jdbcTemplate.queryForObject(PANELF_COUNT_ID + panelId + " AND panel_status = 'Repanel'",
				Integer.class);

		assertEquals(expected, actual);

		// negative testing
		panelId = -8309;
		actual = dao.findFailedFeedbackByPanel(pDAO.findOne(panelId)).size();
		expected = jdbcTemplate.queryForObject(PANELF_COUNT_ID + panelId + " AND panel_status = 'Repanel'",
				Integer.class);

		assertEquals(expected, actual);
	}

}
