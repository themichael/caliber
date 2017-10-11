package com.revature.caliber.data;

import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.beans.Trainee;

/**
 * @author Connor Monson
 */

@Repository
public class PanelDAO {

	private static final Logger log = Logger.getLogger(PanelDAO.class);
	private static final String INTERVIEW_DATE = "interviewDate";
	private static final String PANEL_ID = "id";
	private SessionFactory sessionFactory;
	
	@Autowired
	PanelFeedbackDAO panelFeedbackDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Find all panels. Useful for listing available panels
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Panel> findAll() {
		log.info("Finding all panels");
		return sessionFactory.getCurrentSession().createCriteria(Panel.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
	/**
	 * Looks for all panels where the user was the trainee
	 * 
	 * @param traineeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Panel> findAllByTrainee(Integer traineeId) {
		log.info("Fetching all panels for trainee: " + traineeId);
		List<Panel> panels = sessionFactory.getCurrentSession().createCriteria(Panel.class)
				.add(Restrictions.eq("trainee.traineeId", traineeId))
				.addOrder(Order.desc(INTERVIEW_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		panels.parallelStream();
		return panels;
	}

	
	/**
	 * Looks for all panels with the panel status: Repanel 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Panel> findAllRepanel() {
		log.info("Fetching all panels with the status Repanel");
		return sessionFactory.getCurrentSession().createCriteria(Panel.class)
				.add(Restrictions.eq("status", PanelStatus.Repanel))
				.addOrder(Order.desc(INTERVIEW_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * 
	 * Convenience method only. Not practical in production since panels must
	 * be registered in the Salesforce with a matching email address.
	 * 
	 * @param panel
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Panel panel) {
		log.info("Save panel " + panel);
		sessionFactory.getCurrentSession().save(panel);
	}

	/**
	 * Find panel by the given identifier. Initialize panel feedback
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Panel findOne(Integer panelId) {
		log.info("Find panel by id: " + panelId);
		Panel p = (Panel) sessionFactory.getCurrentSession().get(Panel.class, panelId);
//		p.setFeedback(new HashSet<PanelFeedback>(panelFeedbackDao.findAllForPanel(panelId)));
		return p;
	}

	/**
	 * Updates a panel in the database.
	 * 
	 * @param panel
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Panel panel) {
		log.info("Update panel " + panel);
		sessionFactory.getCurrentSession().saveOrUpdate(panel);
	}

	/**
	 * Convenience method only. Deletes a panel from the database. Panel
	 * will still be registered with a Salesforce account.
	 * 
	 * @param panel
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(int panelId) {
		log.info("Delete panel " + panelId);
		Panel panel = findOne(panelId);
		if (panel != null)
			sessionFactory.getCurrentSession().delete(panel);
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainee> findAllTraineesAndPanels(Integer batchId) {
		log.info("get trainees and their panelInterviews from " + batchId);
		return (List<Trainee>)sessionFactory.getCurrentSession()
				.createCriteria(Trainee.class)
				.add(Restrictions.eq("batch.batchId", batchId))
				.createCriteria("panelInterviews").addOrder(Order.desc(INTERVIEW_DATE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	


}
