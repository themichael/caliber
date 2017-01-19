package com.revature.caliber.assessments.data.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.assessments.beans.Note;
import com.revature.caliber.assessments.data.NoteDAO;

public class NoteDAOImpl implements NoteDAO {
	
	private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
    

	@Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class})
	public Note getNote(String note) {
		return (Note) sessionFactory.getCurrentSession().get(Note.class, note);
	}

	@Override
	public List<Note> getAllNotes() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Note.class);
		return criteria.list();
	}


}
