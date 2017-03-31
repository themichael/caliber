package com.revature.caliber.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Trainee;

/**
 * Provides services and data access
 *
 */
@Service
public class TraineeService {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Trainee> getTraineesInBatch(Integer batchId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainee.class);
		criteria.add(Restrictions.eq("batch.batchId", batchId.longValue()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Trainee> getTraineesByTrainer(Long trainerId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainee.class);
		criteria.createAlias("batch", "b").createAlias("b.trainer", "t")
				.add(Restrictions.eq("t.trainerId", trainerId.intValue()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return criteria.list();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Trainee getTrainee(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainee.class);
		criteria.add(Restrictions.eq("traineeId", id));
		return (Trainee) criteria.uniqueResult();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Trainee getTrainee(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainee.class);
		criteria.add(Restrictions.eq("email", email));
		return (Trainee) criteria.uniqueResult();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public long createTrainee(Trainee trainee) {
		Session session = sessionFactory.getCurrentSession();
		Integer traineeId = (Integer) session.save(trainee);

		return Long.valueOf(traineeId.longValue());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void deleteTrainee(Trainee trainee) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(trainee);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void updateTrainee(Trainee trainee) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(trainee);
	}
}
