package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelStatus;

/**
 * @author Connor Monson
 */

@Repository
public class PanelDAO {

	private static final Logger log = Logger.getLogger(PanelDAO.class);
	private static final String PANELS = "panels";
	private static final String INTERVIEW_DATE = "interviewDate";
	private static final String P_PANEL_STATUS = "p.panelStatus";
	private static final String PANEL_ID = "id";
	private SessionFactory sessionFactory;

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
	 * Find panel by the given identifier
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Panel findOne(Integer panelId) {
		log.info("Find panel by id: " + panelId);
		return (Panel) sessionFactory.getCurrentSession().createCriteria(Panel.class)
				.add(Restrictions.eq(PANEL_ID, panelId)).uniqueResult();
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
	public void delete(Panel panel) {
		log.info("Delete panel " + panel);
		sessionFactory.getCurrentSession().delete(panel);
	}

}
