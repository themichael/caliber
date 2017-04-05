package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.TrainerRole;

@Repository
public class NoteDAO {

	private final static Logger log = Logger.getLogger(NoteDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Save a new note
	 * @param note
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Note note) {
		log.info("Saving Note " + note);
		sessionFactory.getCurrentSession().save(note);
	}

	/**
	 * Update note contents
	 * @param note
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Note note) {
		log.info("Updating Note " + note);
		sessionFactory.getCurrentSession().saveOrUpdate(note);
	}

	/**
	 * Returns all batch-level notes for a given week.
	 * Only notes written by trainers are returned.
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Note> findBatchNotes(Integer batchId, Integer week) {
		log.info("Finding batch notes for week " + week + " for batch: " + batchId);
		return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("batch", "b")
				.add(Restrictions.eq("b.batchId", batchId)).add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.ge("maxVisibility", TrainerRole.TRAINER))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Returns all individual notes for a given week.
	 * Only notes written by trainers are returned.
	 * 
	 * @param traineeId
	 * @param week
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Note> findIndividualNotes(Integer traineeId, Integer week) {
		log.info("Finding individual notes for week " + week + " for trainee: " + traineeId);
		return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("trainee", "t")
				.add(Restrictions.eq("t.traineeId", traineeId)).add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.le("maxVisibility", TrainerRole.TRAINER))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Returns all 
	 * @param batchId
	 * @param week
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Note findQCBatchNotes(Integer batchId, Integer week) {
		log.info("Finding QC batch notes for week " + week + " for batch: " + batchId);
		return (Note) sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("batch", "b")
				.add(Restrictions.eq("batch.batchId", batchId)).add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.ge("maxVisibility", TrainerRole.QC))
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
	}

	/**
	 * Returns all individual notes written by QC for a given week.
	 * @param traineeId
	 * @param week
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Note> findQCIndividualNotes(Integer traineeId, Integer week) {
		log.info("Finding QC individual notes for week " + week + " for trainee: " + traineeId);
		return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("trainee", "t")
				.add(Restrictions.eq("t.traineeId", traineeId)).add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.ge("maxVisibility", TrainerRole.QC))
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Returns all batch-level notes for a given week.
	 * @param batchId
	 * @param week
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Note> findAllBatchNotes(Integer batchId, Integer week) {
		log.info("Finding All batch notes for week " + week + " for batch: " + batchId);
		return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("batch", "b")
				.add(Restrictions.eq("batch.batchId", batchId)).add(Restrictions.eq("week", week.shortValue()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Returns all individual trainee notes for a particular week.
	 * @param traineeId
	 * @param week
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Note> findAllIndividualNotes(Integer traineeId, Integer week) {
		log.info("Finding All individual notes for week " + week + " for trainee: " + traineeId);
		return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("trainee", "t")
				.add(Restrictions.eq("t.traineeId", traineeId)).add(Restrictions.eq("week", week.shortValue()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Returns all notes for a particular trainee. 
	 * Used for cumulative reporting on a single trainee.
	 * Only public notes are displayed. 
	 * 
	 * @param traineeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Note> findAllPublicIndividualNotes(Integer traineeId) {
		log.info("Finding All individual notes for trainee: " + traineeId);
		return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("trainee", "t")
				.add(Restrictions.eq("t.traineeId", traineeId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.ge("maxVisibility", TrainerRole.TRAINER))
				.list();
	}
	
	/**
	 * Returns all qc notes for batches
	 * @return
	 */
	@SuppressWarnings("unchecked")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Note> findAllQCBatchNotes(Integer batchId) {
        log.info("Find All QC Batch notes");
        return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("batch", "b")
        		.add(Restrictions.eq("b.batchId", batchId))
        		.add(Restrictions.ge("maxVisibility", TrainerRole.QC))
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.addOrder(Order.asc("week")).list();
    }
	
	/**
	 * Returns all qc notes for trainee
	 * @return
	 */
	@SuppressWarnings("unchecked")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Note> findAllQCTraineeNotes() {
        log.info("Find All QC Trainee notes");
        return sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("trainee", "t")
        		.add(Restrictions.ge("maxVisibility", TrainerRole.QC)).createAlias("trainee", "t")
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.addOrder(Order.asc("week")).list();
    }
}
