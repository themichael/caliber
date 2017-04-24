package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.beans.TrainingStatus;

@Repository
public class NoteDAO extends BaseDAO{

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
	public int save(Note note) {
		log.info("Saving Note " + note);
		return (int) sessionFactory.getCurrentSession().save(note);
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
		List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.createAlias("batch", "b")
				.createAlias("b.trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.eq("b.batchId", batchId))
				.add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.eq("type", NoteType.BATCH))
				.add(Restrictions.ge("maxVisibility", TrainerRole.ROLE_TRAINER))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return notes;
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
	public List<Note> findIndividualNotes(Integer batchId, Integer week) {
		log.info("Finding individual notes for week " + week + " for batch: " + batchId);
		List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.createAlias("trainee", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.createAlias("t.batch", "b")
				.add(Restrictions.eq("b.batchId", batchId)).add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.eq("maxVisibility", TrainerRole.ROLE_TRAINER))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return notes;
	}
	
	/**
	 * Returns Trainee note for the week
	 * 
	 * @param traineeId
	 * @param week
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Note findTraineeNote(Integer traineeId, Integer week) {
		Note note = (Note)sessionFactory.getCurrentSession().createCriteria(Note.class).setFetchMode("batch", FetchMode.JOIN)
				.createAlias("trainee", "t").add(Restrictions.eq("t.traineeId", traineeId))
				.add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.eq("type", NoteType.TRAINEE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return note;
	}
	
	/**
	 * Returns QCTrainee note for the week(Michael)
	 * 
	 * @param traineeId
	 * @param week
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Note findQCTraineeNote(Integer traineeId, Integer week) {
		Note note = (Note)sessionFactory.getCurrentSession().createCriteria(Note.class).setFetchMode("batch", FetchMode.JOIN)
				.createAlias("trainee", "t").add(Restrictions.eq("t.traineeId", traineeId))
				.add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.eq("type", NoteType.QC_TRAINEE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();

		return note;
	}
	
	/**
	 * Returns batch note for the batch for the week
	 * @param batchId
	 * @param week
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Note findQCBatchNotes(Integer batchId, Integer week) {
		log.info("Finding QC batch notes for week " + week + " for batch: " + batchId);
		Note note = (Note) sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("batch", "b")
				.createAlias("b.trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.eq("batch.batchId", batchId)).add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.ge("maxVisibility", TrainerRole.ROLE_QC))
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		return note;
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
		List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.createAlias("trainee", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.eq("t.traineeId", traineeId)).add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.ge("maxVisibility", TrainerRole.ROLE_QC))
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		for(Note note : notes){
			initializeActiveTrainees(note);
		}
		return notes;
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
		List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("batch", "b")
				.createAlias("b.trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.eq("batch.batchId", batchId)).add(Restrictions.eq("week", week.shortValue()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return notes;
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
		List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.createAlias("trainee", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.eq("t.traineeId", traineeId)).add(Restrictions.eq("week", week.shortValue()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return notes;
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
		List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.createAlias("trainee", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
				.add(Restrictions.eq("t.traineeId", traineeId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.ge("maxVisibility", TrainerRole.ROLE_TRAINER))
				.list();
		return notes;
	}
	
	/**
	 * Returns all qc notes for a batch
	 * @return
	 */
	@SuppressWarnings("unchecked")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Note> findAllQCBatchNotes(Integer batchId) {
        log.info("Find All QC Batch notes");
        List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class).createAlias("batch", "b")
				.createAlias("b.trainees", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
        		.add(Restrictions.eq("b.batchId", batchId))
        		.add(Restrictions.ge("maxVisibility", TrainerRole.ROLE_QC))
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.addOrder(Order.asc("week")).list();
		return notes;
    }
	
	/**
	 * Returns all QC notes for trainee in the batch for the week
	 * @return
	 */
	@SuppressWarnings("unchecked")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Note> findAllQCTraineeNotes(Integer batchId, Integer week) {
        log.info("Find All QC Trainee notes");
        List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.createAlias("trainee", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
        		.createAlias("t.batch", "b").add(Restrictions.eq("b.batchId", batchId))
        		.add(Restrictions.ge("maxVisibility", TrainerRole.ROLE_QC))
        		.add(Restrictions.eq("week", week.shortValue()))
				.add(Restrictions.eq("qcFeedback", true))
				.add(Restrictions.eq("type", NoteType.QC_TRAINEE))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.addOrder(Order.asc("week")).list();
		return notes;
    }
	
	/**
	 * Returns all QC notes for trainee in the batch
	 * @return
	 */
	@SuppressWarnings("unchecked")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Note> findAllQCTraineeOverallNotes(Integer traineeId) {
        log.info("Find All QC Trainee notes for that trainee");
        List<Note> notes = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.createAlias("trainee", "t", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.ne("t.trainingStatus", TrainingStatus.Dropped))
        		.add(Restrictions.eq("t.traineeId", traineeId))
        		.add(Restrictions.ge("maxVisibility", TrainerRole.ROLE_QC))
				.add(Restrictions.eq("qcFeedback", true)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.addOrder(Order.asc("week")).list();
		return notes;
    }
}