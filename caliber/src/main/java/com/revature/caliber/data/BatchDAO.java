package com.revature.caliber.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.beans.TrainingStatus;

@Repository
public class BatchDAO extends BaseDAO {

	private final static Logger log = Logger.getLogger(BatchDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Save a batch to the database.
	 * 
	 * @param batch
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Batch batch) {
		log.info("Saving Batch " + batch);
		sessionFactory.getCurrentSession().save(batch);
	}

	/**
	 * Looks for all batches without any restriction. Likely to only be useful for calculating reports.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAll() {
		log.info("Fetching all batches");
		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.addOrder(Order.desc("startDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Looks for all batches where the user was the trainer or cotrainer.
	 * 
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllByTrainer(Integer trainerId) {
		log.info("Fetching all batches for trainer: " + trainerId);

		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(Restrictions.eq("trainer.trainerId", trainerId),
						Restrictions.eq("coTrainer.trainerId", trainerId)))
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped)).addOrder(Order.desc("startDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return batches;
	}

	/**
	 * Looks for all batches where the user was the trainer or co-trainer. Batches returned are currently actively in
	 * training.
	 * 
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllCurrent(Integer trainerId) {
		log.info("Fetching all current batches for trainer: " + trainerId);
		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(Restrictions.eq("trainer.trainerId", trainerId),
						Restrictions.eq("coTrainer.trainerId", trainerId)))
				.add(Restrictions.le("startDate", Calendar.getInstance().getTime()))
				.add(Restrictions.ge("endDate", Calendar.getInstance().getTime()))
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped)).addOrder(Order.desc("startDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return batches;
	}

	/**
	 * Looks for all batches that are currently actively in training. Useful for VP and QC to get snapshots of currently
	 * operating batches.
	 * 
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllCurrent() {
		log.info("Fetching all current batches");
		Calendar endDateLimit = Calendar.getInstance();
		endDateLimit.add(Calendar.MONTH, -3);
		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias("trainees.notes", "n", JoinType.LEFT_OUTER_JOIN)
				.createAlias("trainees.grades", "g", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.gt("g", 0.0))
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.le("startDate", Calendar.getInstance().getTime()))
				.add(Restrictions.ge("endDate", endDateLimit.getTime()))
				.add(Restrictions.ge("n.maxVisibility", TrainerRole.ROLE_QC)).add(Restrictions.eq("n.qcFeedback", true))
				.addOrder(Order.desc("startDate")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return batches;
	}

	/**
	 * Find a batch by its given identifier
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Batch findOne(Integer batchId) {
		log.info("Fetching batch: " + batchId);
		Batch batch = (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("batchId", batchId))
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped)).uniqueResult();
		return batch;
	}

	/**
	 * Find a batch by its given identifier, all trainees, and all their grades
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Batch findOneWithTraineesAndGrades(Integer batchId) {
		log.info("Fetching batch: " + batchId);
		Batch batch = (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias("t.grades", "g", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.gt("g", 0.0))
				.add(Restrictions.eq("batchId", batchId))
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.uniqueResult();
		return batch;
	}
	
	/**
	 * Update details for a batch
	 * 
	 * @param batch
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Batch batch) {
		log.info("Updating batch: " + batch);
		sessionFactory.getCurrentSession().saveOrUpdate(batch);
	}

	/**
	 * Delete a batch from the database
	 * 
	 * @param batch
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Batch batch) {
		log.info("Deleting batch: " + batch);
		sessionFactory.getCurrentSession().delete(batch);
	}

	/**
	 * Return commonly-used locations for combo box convenience
	 * 
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<String> findCommonLocations() {
		log.info("Getting common locations");
		@SuppressWarnings("unchecked")
		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.setProjection(Projections
						.distinct(Projections.projectionList().add(Projections.property("location"), "location")))
				.setResultTransformer(Transformers.aliasToBean(Batch.class)).list();
		List<String> locations = new ArrayList<String>();

		if (batches != null) {
			for (Batch batch : batches) {
				locations.add(batch.getLocation());
			}
		}
		return locations;
	}

	/**
	 * Looks for all batches that whose starting date is after the given year, month, and day.
	 * Return all batches, trainees for that batch, and the grades for each trainee
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllAfterDate(Integer month, Integer day, Integer year) {
		Calendar startDate = Calendar.getInstance();
		startDate.set(year, month - 1, day);
		log.info("Fetching all current batches since: " + startDate.getTime().toString());
		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias("trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias("trainees.grades", "g", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.gt("g", 0.0))
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.ge("startDate", startDate.getTime()))
				.addOrder(Order.desc("startDate"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		return batches;
	}
	
}
