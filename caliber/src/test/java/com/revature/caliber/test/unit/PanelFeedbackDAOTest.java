package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.InterviewFormat;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

public class PanelFeedbackDAOTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(PanelFeedbackDAOTest.class);
	private static final String PANELF_COUNT = "select count(panel_feedback_id) from caliber_panel_feedback";
	
	
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
	

	//Tests the save method in AddressDAO. Creates a new address and checks if the address appears, and then checks to make sure the size has increased.
	@Test
	public void saveFeedbackDAO(){
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
		long pfid= pf.getId();
		System.out.println(pfid);
		int after = jdbcTemplate.queryForObject(PANELF_COUNT, Integer.class);
		assertEquals(pf.toString(), dao.findOne(pfid).toString());
		assertEquals(++before, after);
	}
	
	@Test
	public void getPanelByIdDAO(){
		log.info("Finding Location by address");
		int search = 1;
		Panel p = pDAO.findOne(search);
		assertEquals(pDAO.findOne(1).toString(),p.toString());
	}
	
	
	@Test
	public void findAllFeedbackDAO(){
		log.info("Getting all feedback using PanelFeedbackDAO getAll function");
		long num = jdbcTemplate.queryForObject(PANELF_COUNT, Long.class);
		assertNotNull(dao.findAll());
		assertEquals(dao.findAll().size(), num);
	}

	
	@Test
	public void getFeedbackByIdDAO(){
		log.info("Finding Location by address");
		long search = 1;
		PanelFeedback feedback = dao.findOne(search);
		assertEquals(dao.findOne((long)1).toString(),feedback.toString());
	}

	//Tests to make sure an address not in the database returns null. 
	@Test
	public void nullGetPanelFeedbackByInt(){
		log.info("About to fail gettingAddressByInt");
		PanelFeedback feedback = dao.findOne(99999999);
		assertNull(feedback);
	}

	//Tests update method of AddressDAO. Asserts that addresses zipcode was changed.
	@Test
	public void updateFeedbackDAO(){
		log.info("UpdateAddessDAO Test");
		String comment = "11111";
		PanelFeedback test = dao.findOne((long)2);
		System.out.println(test);
		test.setComment(comment);
		dao.update(test);
		assertEquals(comment, dao.findOne((long)2).getComment());
	}
	
//	@Test
//	public void deleteFeedbackDAO(){
//		log.info("DELETE FEEDBACK DAO");
//		int beforeTest = jdbcTemplate.queryForObject(PANELF_COUNT, Integer.class);
//		System.out.println(beforeTest);
//		PanelFeedback pf = dao.findOne(1);
//		System.out.println(pf);
//		dao.delete(pf);
//		int afterTest = jdbcTemplate.queryForObject(PANELF_COUNT, Integer.class);
//		System.out.println(afterTest);
//		assertEquals(--beforeTest, afterTest);
//	}
	
}

