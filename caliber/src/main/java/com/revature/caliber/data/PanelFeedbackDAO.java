package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.PanelStatus;

@Repository
public class PanelFeedbackDAO {

	private static final Logger log = Logger.getLogger(PanelFeedbackDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Find all panels titles to be displayed on front end
	 * 
	 * 
	 * 
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public List<String> findAllPanelTitles() {
//		String hql = "select distinct title FROM Panel";
//		return sessionFactory.getCurrentSession().createQuery(hql).list();
//	}

	/**
	 * Find all panels. Useful for listing available panels
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<PanelFeedback> findAll() {
		log.info("Finding all panel feedback");
		return sessionFactory.getCurrentSession().createCriteria(PanelFeedback.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
	/**
	 * Find all panel feedbacks for one panel
	 * @author emmabownes
	 * @return List of panel feedbacks for a given panel
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<PanelFeedback> findFeedbackByPanel(Panel panel) {
		log.info("Finding all panel feedback for panel "+ panel);
		return sessionFactory.getCurrentSession().createCriteria(PanelFeedback.class).add(Restrictions.eq("panel.id", panel.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Find all panel feedbacks for one panel
	 * @author emmabownes
	 * @return List of panel feedbacks for a given panel
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<PanelFeedback> findFailedFeedbackByPanel(Panel panel) {
		log.info("Finding failed panel feedback for panel "+ panel);
		return sessionFactory.getCurrentSession().createCriteria(PanelFeedback.class)
				.add(Restrictions.eq("panel.id", panel.getId()))
				.add(Restrictions.eq("status", PanelStatus.Repanel))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
	/**
	 * 
	 * Convenience method only. Not practical in production since panels must
	 * be registered in the Salesforce with a matching email address.
	 * 
	 * @param panel
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(PanelFeedback panelf) {
		log.info("Save panel feedback " + panelf);
		sessionFactory.getCurrentSession().save(panelf);
	}

	/**
	 * Find panel by the given identifier
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public PanelFeedback findOne(long panelFId) {
		log.info("Find panel feedback by id: " + panelFId);
		return (PanelFeedback) sessionFactory.getCurrentSession().createCriteria(PanelFeedback.class)
				.add(Restrictions.eq("id", panelFId)).uniqueResult();
	}

	/**
	 * Updates a panel in the database.
	 * 
	 * @param panel
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(PanelFeedback panel) {
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
	public void delete(PanelFeedback panel) {
		log.info("Delete panel " + panel);
		sessionFactory.getCurrentSession().delete(panel);
	}

}

