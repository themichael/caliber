package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Assessment;

@Repository
public class AssessmentDAO extends BaseDAO {

	private final static Logger log = Logger.getLogger(AssessmentDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void save(Assessment assessment) {
		log.info("Saving assessment " + assessment);
		sessionFactory.getCurrentSession().save(assessment);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Assessment findOne(long id) {
		log.info("Finding one assessment " + id);
		return (Assessment) sessionFactory.getCurrentSession().get(Assessment.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Assessment> findAll() {
		log.info("Find all assessment");
		return sessionFactory.getCurrentSession().createCriteria(Assessment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Assessment> findByWeek(Integer batchId, Integer week) {
		log.info("Find assessment by week number " + week + " for batch " + batchId + " ");
		List<Assessment> assessments = sessionFactory.getCurrentSession().createCriteria(Assessment.class)
				.createAlias("batch", "batch").createAlias("batch.trainees", "t")
				.add(Restrictions.and(Restrictions.eq("batch.batchId", batchId),
						Restrictions.eq("week", week.shortValue())))
				.setFetchMode("grades", FetchMode.JOIN).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		for (Assessment assessment : assessments) {
			initializeActiveTrainees(assessment);
		}
		return assessments;
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Assessment> findByBatchId(Integer batchId) {
		log.info("Find assessment by batchId" + batchId + " ");
		List<Assessment> assessments = sessionFactory.getCurrentSession().createCriteria(Assessment.class)
				.createAlias("batch", "batch").createAlias("batch.trainees", "t")
				.add(Restrictions.and(Restrictions.eq("batch.batchId", batchId)))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		for (Assessment assessment : assessments) {
			initializeActiveTrainees(assessment);
		}
		return assessments;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void update(Assessment assessment) {
		log.info("Updating assessment " + assessment);
		sessionFactory.getCurrentSession().update(assessment);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void delete(Assessment assessment) {
		log.info("Deleting assessment " + assessment);
		sessionFactory.getCurrentSession().delete(assessment);
	}

}
