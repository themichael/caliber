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

import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;

@Repository
public class TraineeDAO extends BaseDAO {

	private final static Logger log = Logger.getLogger(TraineeDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Save a trainee to the database
	 * 
	 * @param trainee
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void save(Trainee trainee) {
		log.info("Saving trainee " + trainee);
		sessionFactory.getCurrentSession().save(trainee);
	}

	/**
	 * Find all trainees without condition. Useful for calculating report data
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainee> findAll() {
		log.info("Fetching all trainees");
		return sessionFactory.getCurrentSession().createCriteria(Trainee.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Find all trainees in a given batch
	 * 
	 * @param batchId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainee> findAllByBatch(Integer batchId) {
		log.info("Fetching all trainees by batch: " + batchId);
		List<Trainee> trainees = sessionFactory.getCurrentSession().createCriteria(Trainee.class)
				.add(Restrictions.eq("batch.batchId", batchId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		return initializeActiveTrainees(trainees);
	}

	/**
	 * Find all dropped trainees in a given batch
	 * 
	 * @param batchId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainee> findAllDroppedByBatch(Integer batchId) {
		log.info("Fetching all trainees by batch: " + batchId);
		List<Trainee> trainees = sessionFactory.getCurrentSession().createCriteria(Trainee.class)
				.add(Restrictions.eq("batch.batchId", batchId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		return initializeDroppedTrainees(trainees);
	}

	/**
	 * Find all trainees by the trainer's identifier
	 * 
	 * @param trainerId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainee> findAllByTrainer(Integer trainerId) {
		log.info("Fetch all trainees by trainer: " + trainerId);
		List<Trainee> trainees = sessionFactory.getCurrentSession().createCriteria(Trainee.class)
				.createAlias("batch", "b").createAlias("b.trainer", "t").createAlias("grades", "g")
				.createAlias("notes", "n").add(Restrictions.eq("t.trainerId", trainerId))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return initializeActiveTrainees(trainees);
	}

	/**
	 * * Find a trainee by the given identifier
	 * 
	 * @param traineeId
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Trainee findOne(Integer traineeId) {
		log.info("Fetch trainee by id: " + traineeId);
		return (Trainee) sessionFactory.getCurrentSession().createCriteria(Trainee.class)
				.setFetchMode("batch", FetchMode.JOIN).add(Restrictions.eq("traineeId", traineeId))
				.add(Restrictions.ne("trainingStatus", TrainingStatus.Dropped)).uniqueResult();
	}

	/**
	 * Find a trainee by email address
	 * 
	 * @param email
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Trainee findByEmail(String email) {
		log.info("Fetch trainee by email address: " + email);
		return (Trainee) sessionFactory.getCurrentSession().createCriteria(Trainee.class)
				.add(Restrictions.eq("email", email)).uniqueResult();
	}

	/**
	 * Delete the given trainee
	 * 
	 * @param trainee
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Trainee trainee) {
		log.info("Delete trainee: " + trainee);
		sessionFactory.getCurrentSession().delete(trainee);
	}

	/**
	 * Update the trainee details in the database
	 * 
	 * @param trainee
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Trainee trainee) {
		log.info("Update trainee: " + trainee);
		sessionFactory.getCurrentSession().saveOrUpdate(trainee);
	}

}
