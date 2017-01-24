package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.TrainerNoteDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository(value="trainerNoteDAO")
public class TrainerNoteDAOImpl implements TrainerNoteDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public void createTrainerNote(TrainerNote trainerNote) {
        sessionFactory.getCurrentSession().save(trainerNote);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    @SuppressWarnings("unchecked")
    public Set<TrainerNote> getTrainerNotesByTrainerId(int trainerId) {
        return new HashSet<>(
                sessionFactory.getCurrentSession()
                        .createCriteria(TrainerNote.class)
                        .add(Restrictions.eq("trainer", trainerId)).list());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public TrainerNote getTrainerNoteForWeek(int trainerId, int weekId) {
        TrainerNote trainerNoteForWeek = (TrainerNote) sessionFactory.getCurrentSession()
                .createCriteria(TrainerNote.class)
                    .add(Restrictions.eq("trainer", trainerId))
                    .add(Restrictions.eq("week", weekId))
                .uniqueResult();
        return trainerNoteForWeek;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
            Exception.class})
    public void updateTrainerNote(TrainerNote trainerNote) {
        sessionFactory.getCurrentSession().saveOrUpdate(trainerNote);
    }
}
