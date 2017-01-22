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
 * Implementation for trainee DAO crud methods. Methods are self-explanatory.
 * I did not make 1-line calls just in case I would need to add something.
 *
 * @author Ilya
 */
@Repository
public class TraineeDAOImplementation implements TraineeDAO{

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
    public List<Trainee> getTraineesInBatch(Integer batchId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Trainee.class);
        criteria.add(Restrictions.eq("batch.batchId", batchId));
        return criteria.list();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
    public Trainee getTrainee(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Trainee.class);
        criteria.add(Restrictions.eq("traineeId", id));
        return (Trainee)criteria.uniqueResult();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
    public Trainee getTrainee(String name) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Trainee.class);
        criteria.add(Restrictions.eq("name", name));
        return (Trainee)criteria.uniqueResult();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
    public void createTrainee(Trainee trainee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(trainee);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
    public void deleteTrainee(Trainee trainee) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(trainee);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
    public void updateTrainee(Trainee trainee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(trainee);
    }
}
