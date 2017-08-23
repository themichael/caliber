package com.revature.caliber.data;

import java.util.Calendar;
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

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.TrainingStatus;

/**
 * @author Patrick Walsh
 * @author Ateeb Khawaja
 *
 */
@Repository
public class BatchDAO {

	private static final Logger log = Logger.getLogger(BatchDAO.class);
	private SessionFactory sessionFactory;
	private static final int MONTHS_BACK = -1;
	private static final String TRAINEES = "trainees";
	private static final String T_TRAINING_STATUS = "t.trainingStatus";
	private static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";
	private static final String G_SCORE = "g.score";
	private static final String T_GRADES = "trainees.grades";
	private static final String BATCH_ID = "batchId";

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
	 * Looks for all batches without any restriction. Likely to only be useful for
	 * calculating reports.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAll() {
		log.info("Fetching all batches");
		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped)).addOrder(Order.desc(START_DATE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	}

	/**
	 * Looks for all batches where the user was the trainer or co-trainer.
	 * 
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllByTrainer(Integer trainerId) {
		log.info("Fetching all batches for trainer: " + trainerId);
		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(Restrictions.eq("trainer.trainerId", trainerId),
						Restrictions.eq("coTrainer.trainerId", trainerId)))
				.addOrder(Order.desc(START_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		batches.parallelStream()
				.forEach(b -> b.getTrainees().removeIf(t -> t.getTrainingStatus().equals(TrainingStatus.Dropped)));
		return batches;
	}

	/**
	 * Looks for all batches where the user was the trainer or co-trainer. Batches
	 * returned are currently actively in training.
	 * 
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllCurrent(Integer trainerId) {
		log.info("Fetching all current batches for trainer: " + trainerId);
		Calendar endDateLimit = Calendar.getInstance();
		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(Restrictions.eq("trainer.trainerId", trainerId),
						Restrictions.eq("coTrainer.trainerId", trainerId)))
				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).addOrder(Order.desc(START_DATE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		batches.parallelStream()
				.forEach(b -> b.getTrainees().removeIf(t -> t.getTrainingStatus().equals(TrainingStatus.Dropped)));
		return batches;
	}

	/**
	 * Looks for all batches that are currently actively in training including
	 * trainees, notes and grades
	 * 
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllCurrentWithNotesAndTrainees() {
		log.info("Fetching all current batches with trainees, grades and notes");
		Calendar endDateLimit = Calendar.getInstance();
		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias("trainees.notes", "n", JoinType.LEFT_OUTER_JOIN)
				.createAlias(T_GRADES, "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).add(Restrictions.eq("n.qcFeedback", true))
				.addOrder(Order.desc(START_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllCurrentWithNotes() {
		log.info("Fetching all current batches with trainees, and notes");
		Calendar endDateLimit = Calendar.getInstance();
		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias("trainees.notes", "n", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).add(Restrictions.eq("n.qcFeedback", true))
				.addOrder(Order.desc(START_DATE)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllCurrentWithTrainees() {
		log.info("Fetching all current batches with trainees, grades");
		Calendar endDateLimit = Calendar.getInstance();
		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias(T_GRADES, "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).addOrder(Order.desc(START_DATE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Looks for all batches that are currently actively in training. Useful for VP
	 * and QC to get snapshots of currently operating batches.
	 * 
	 * @param auth
	 * @returnF
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllCurrent() {
		log.info("Fetching all current batches with active trainees");
		Calendar endDateLimit = Calendar.getInstance();
		endDateLimit.add(Calendar.MONTH, MONTHS_BACK);
		List<Batch> batches = sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.le(START_DATE, Calendar.getInstance().getTime()))
				.add(Restrictions.ge(END_DATE, endDateLimit.getTime())).addOrder(Order.desc(START_DATE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		batches.parallelStream()
				.forEach(b -> b.getTrainees().removeIf(t -> t.getTrainingStatus().equals(TrainingStatus.Dropped)));
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
		return (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq(BATCH_ID, batchId))
				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped)).uniqueResult();
	}

	/**
	 * Find a batch by its given identifier
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Batch findOneWithDroppedTrainees(Integer batchId) {
		log.info("Fetching batch: " + batchId);
		return (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq(BATCH_ID, batchId))
				.uniqueResult();
	}

	/**
	 * Find a batch by its given identifier, all trainees, and all their grades
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Batch findOneWithTraineesAndGrades(Integer batchId) {
		log.info("Fetching batch with trainees: " + batchId);
		return (Batch) sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias("t.grades", "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
				.add(Restrictions.eq(BATCH_ID, batchId)).add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
				.uniqueResult();
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
	 * Looks for all batches that whose starting date is after the given year,
	 * month, and day. Month is 0-indexed Return all batches, trainees for that
	 * batch, and the grades for each trainee
	 * 
	 * @param auth
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Batch> findAllAfterDate(Integer month, Integer day, Integer year) {
		Calendar startDate = Calendar.getInstance();
		startDate.set(year, month, day);
		log.info("Fetching all current batches since: " + startDate.getTime().toString());
		return sessionFactory.getCurrentSession().createCriteria(Batch.class)
				.createAlias(TRAINEES, "t", JoinType.LEFT_OUTER_JOIN)
				.createAlias(T_GRADES, "g", JoinType.LEFT_OUTER_JOIN).add(Restrictions.gt(G_SCORE, 0.0))
				.add(Restrictions.ne(T_TRAINING_STATUS, TrainingStatus.Dropped))
				.add(Restrictions.ge(START_DATE, startDate.getTime())).addOrder(Order.desc(START_DATE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
}
