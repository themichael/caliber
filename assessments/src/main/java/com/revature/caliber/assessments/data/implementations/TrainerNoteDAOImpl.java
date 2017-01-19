package com.revature.caliber.assessments.data.implementations;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.TrainerNoteDAO;

public class TrainerNoteDAOImpl implements TrainerNoteDAO {

	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void createTrainerNote(int trainerId) {
		TrainerNote traineeNote = (TrainerNote) session.get(TrainerNote.class, trainerId);
		session.saveOrUpdate(traineeNote);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashSet<TrainerNote> getAllTrainerNotesByTrainer(int trainerId) {
		HashSet<TrainerNote> trainerNotes = (HashSet<TrainerNote>) session.createCriteria(TrainerNote.class).
				add(Restrictions.eq("TRAINER_ID", trainerId)).list();
		return trainerNotes;
	}
	
	@Override
	public TrainerNote getTrainerNoteForWeek(int trainerId, int weekId) {
		TrainerNote trainerNoteForWeek = (TrainerNote) session.createCriteria(TrainerNote.class)
				.add(Restrictions.eq("TRAINER_ID", trainerId))
				.add(Restrictions.eq("WEEK_ID", weekId)).uniqueResult();
		return trainerNoteForWeek;
	}
	

}
