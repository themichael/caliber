package com.revature.caliber.training.data.implementations;

import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.data.TraineeDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation for trainee DAO crud methods
 */
@Repository(value = "trainingTraineeDAOImplementation")
public class TraineeDAOImplementation implements TraineeDAO{

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
    public List<Trainee> getTraineesInBatch(Integer batchId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Trainee.class);
        criteria.add(Restrictions.eq("BATCH_ID", batchId));
        return criteria.list();
    }

    @Override
    public Trainee getTrainee(Integer id) {
        return null;
    }

    @Override
    public Trainee getTrainee(String name) {
        return null;
    }

    @Override
    public void createTrainee(Trainee trainee) {

    }

    @Override
    public void deleteTrainee(Trainee trainee) {

    }

    @Override
    public void updateTrainee(Trainee trainee) {

    }
}
