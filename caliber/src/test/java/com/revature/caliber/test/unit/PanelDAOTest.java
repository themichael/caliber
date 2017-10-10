package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.InterviewFormat;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

import oracle.sql.TIMESTAMP;

/**
 * @author Connor Monson
 */

public class PanelDAOTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(PanelDAOTest.class);
	private static final String PANEL_COUNT = "select count(panel_id) from caliber_panel";

	@Autowired
	private PanelDAO panelDAO;
	
	@Autowired
	private TraineeDAO traineeDao;
	
	@Autowired
	private PanelFeedbackDAO pfDao;

	@Autowired
	private CategoryDAO cDao;
	
	@Autowired
	private TrainerDAO trDao;
	
	public void setPanelDAO(PanelDAO panelDAO) {
		this.panelDAO = panelDAO;
	}
	
	public void setTraineeDao(TraineeDAO dao) {
		this.traineeDao = dao;
	}
	
	public void setFeedbackDao(PanelFeedbackDAO dao) {
		this.pfDao = dao;
	}
	
	public void setCategoryDao(CategoryDAO dao) {
		this.cDao = dao;
	}

	public void setTrainerDao(TrainerDAO dao) {
		this.trDao = dao;
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
//		log.info("Testing method PanelDAO.saveTest()");
//		Panel testPanel = new Panel();
//		Trainer testTrainer = new Trainer("Sir. Test", "Tester", "test@test.test", TrainerRole.ROLE_TRAINER);
//		testTrainer.setTrainerId(2);
//		testPanel.setPanelist(testTrainer);
//		Batch testBatch = new Batch("Test Name", testTrainer, Date.from(Instant.now()),
//				Date.from(Instant.now().plus(Period.ofDays(60))), "Test Location");
//		
//		Trainee testTrainee = traineeDao.findOne(5500);
//		testPanel.setTrainee(testTrainee);
//		testPanel.setStatus(PanelStatus.Pass);
//		testPanel.setPanelRound(1);
//		testPanel.setFormat(InterviewFormat.Phone);
//		PanelFeedback pf = new PanelFeedback();
//		//pf.setId(50);
//		pf.setComment("test");
//		pf.setPanel(testPanel);
//		pf.setResult(5);
//		pf.setStatus(PanelStatus.Pass);
//		Category technology = cDao.findOne(1);
//		pf.setTechnology(technology);
//		System.out.println(pf);
//		pfDao.save(pf);
//		Set<PanelFeedback> set = new HashSet<PanelFeedback>();
//		set.add(pf);
//		testPanel.setFeedback(set);
//		panelDAO.save(testPanel);
//		List<Panel> resultSet = panelDAO.findAll();
//		boolean success = false;
//		for (Panel found : resultSet) {
//			if (found.equals(testPanel)) {
//				success = true;
//				break;
//			}
//		}
//		assertTrue(success);
		log.info("Saving a new Feedback using PanelFeedbackDAO");
		int before = jdbcTemplate.queryForObject(PANEL_COUNT, Integer.class);
		//PanelFeedback pf = new PanelFeedback();
		Panel p = new Panel();
		p.setFormat(InterviewFormat.Phone);
		p.setPanelRound(1);
		//p.setInterviewDate(Timestamp.);
		p.setStatus(PanelStatus.Pass);
		p.setTrainee(traineeDao.findOne(1));
		p.setPanelist(trDao.findOne(1));
		//Category c = cDao.findOne(1); 
		
//		pf.setComment("test");
//		pf.setResult(5);
//		pf.setTechnology(c);
//		pf.setStatus(PanelStatus.Pass);
//		pf.setPanel(p);
		
		panelDAO.save(p);
		int pid = p.getId();
		int after = jdbcTemplate.queryForObject(PANEL_COUNT, Integer.class);
		List<Panel> resultSet = panelDAO.findAll();
		boolean success = false;
		for (Panel found : resultSet) {
			if (p.getId() == found.getId()){
				success = true;
				break;
			}
		}
		//p2.setInterviewDate(interviewDate);
		//System.out.println(Timestamp.valueOf(p.getInterviewDate().toString()));
		assertTrue(success);
		assertEquals(++before, after);
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
	
	@Test
	public void deletePanelTest(){
		log.info("DELETE PANEL DAO");
		int beforeTest = jdbcTemplate.queryForObject(PANEL_COUNT, Integer.class);
		System.out.println(beforeTest);
		Panel p = panelDAO.findOne(1);
		System.out.println(p);
		panelDAO.delete(p);
		int afterTest = jdbcTemplate.queryForObject(PANEL_COUNT, Integer.class);
		System.out.println(afterTest);
		assertEquals(--beforeTest, afterTest);
	}

}
