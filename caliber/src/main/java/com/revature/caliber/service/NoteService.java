package com.revature.caliber.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Note;

/**
 * Provides services and data access
 *
 */
@Service
public class NoteService {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public void createNote(Note note) {
        sessionFactory.getCurrentSession().save(note);
    }

    //Get a List of BatchNotes for a batch within a week pertaining to a single Batch
    //If two trainers have separate feedback
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public Note getBatchesNotesWeek(int batchId, int week) {
        return (Note) sessionFactory.getCurrentSession().createCriteria(Note.class)
                .add(Restrictions.eq("batch", batchId))
                .add(Restrictions.eq("week", week)).uniqueResult();
    }

    //Update a BatchNote
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public void updateNote(Note note) {
        sessionFactory.getCurrentSession().update(note);
    }

	//Get a BatchNote by a specific ID
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
	public Note getNoteById(int noteId) {
		return (Note) sessionFactory.getCurrentSession().get(Note.class, noteId);
	}

    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class})
    public Note getNoteForTraineeWeek(Integer traineeId, Integer week) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Note.class);
        criteria.add(Restrictions.eq("trainee", traineeId));
        criteria.add(Restrictions.eq("week", week));
        return (Note) criteria.setMaxResults(1).uniqueResult();
    }
	
}
