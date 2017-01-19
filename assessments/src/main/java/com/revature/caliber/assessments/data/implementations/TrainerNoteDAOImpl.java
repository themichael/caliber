package com.revature.caliber.assessments.data.implementations;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.TrainerNoteDAO;

@Repository(value = "trainerNoteDAO")
public class TrainerNoteDAOImpl implements TrainerNoteDAO {

	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void createTrainerNote(int trainerId) {
		TrainerNote traineeNote = (TrainerNote) session.get(TrainerNote.class, trainerId);
		session.saveOrUpdate(traineeNote);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@SuppressWarnings("unchecked")
	public List<TrainerNote> getAllTrainerNotesByTrainer(int trainerId) {
		List<TrainerNote> trainerNotes = session.createCriteria(TrainerNote.class).
				add(Restrictions.eq("TRAINER_ID", trainerId)).list();
		return trainerNotes;
	}
	
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public TrainerNote getTrainerNoteForWeek(int trainerId, int weekId) {
		TrainerNote trainerNoteForWeek = (TrainerNote) session.createCriteria(TrainerNote.class)
				.add(Restrictions.eq("TRAINER_ID", trainerId))
				.add(Restrictions.eq("WEEK_ID", weekId)).uniqueResult();
		return trainerNoteForWeek;
	}
	

}
