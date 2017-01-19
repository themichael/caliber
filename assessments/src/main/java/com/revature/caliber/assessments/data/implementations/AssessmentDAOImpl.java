package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.AssessmentDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository(value = "assessmentDAO")
public class AssessmentDAOImpl implements AssessmentDAO {

    private SessionFactory sessionFactory;

    //    Spring setter based DI
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //    Get

    @Override
    public Assessment getById(int id) {
        return (Assessment) sessionFactory.getCurrentSession().get(Assessment.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashSet<Assessment> getAll() {
        return new HashSet<>(
                sessionFactory.getCurrentSession()
                        .createCriteria(Assessment.class).list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashSet<Assessment> getByTrainerId(int id) {
        return new HashSet<>(
                sessionFactory.getCurrentSession()
                        .createCriteria(Assessment.class)
                        .add(Restrictions.eq("TRAINER_ID", id)).list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashSet<Assessment> getByWeekId(int id) {
        return new HashSet<>(
                sessionFactory.getCurrentSession()
                        .createCriteria(Assessment.class)
                        .add(Restrictions.eq("WEEK_ID", id)).list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashSet<Assessment> getByBatchId(int id) {
        return new HashSet<>(
                sessionFactory.getCurrentSession()
                        .createCriteria(Assessment.class)
                        .add(Restrictions.eq("BATCH_ID", id)).list());
    }

    //    Create
    @Override
    public void insert(Assessment assessment) {
        sessionFactory.getCurrentSession().save(assessment);
    }

    //    Update
    @Override
    public void update(Assessment assessment) {
        sessionFactory.getCurrentSession().update(assessment);

    }

    //    Delete
    @Override
    public void delete(Assessment assessment) {
        sessionFactory.getCurrentSession().delete(assessment);
    }
}
