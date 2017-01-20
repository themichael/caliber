package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.TrainerNoteDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "trainerNoteDAO")
public class TrainerNoteDAOImpl implements TrainerNoteDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public void createTrainerNote(int trainerId) {
        TrainerNote traineeNote = (TrainerNote) sessionFactory.getCurrentSession().get(TrainerNote.class, trainerId);
        sessionFactory.getCurrentSession().saveOrUpdate(traineeNote);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    @SuppressWarnings("unchecked")
    public List<TrainerNote> getAllTrainerNotesByTrainer(int trainerId) {
        List<TrainerNote> trainerNotes = sessionFactory.getCurrentSession().createCriteria(TrainerNote.class).
                add(Restrictions.eq("TRAINER_ID", trainerId)).list();
        return trainerNotes;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public TrainerNote getTrainerNoteForWeek(int trainerId, int weekId) {
        TrainerNote trainerNoteForWeek = (TrainerNote) sessionFactory.getCurrentSession().createCriteria(TrainerNote.class)
                .add(Restrictions.eq("TRAINER_ID", trainerId))
                .add(Restrictions.eq("WEEK_ID", weekId)).uniqueResult();
        return trainerNoteForWeek;
    }

}
