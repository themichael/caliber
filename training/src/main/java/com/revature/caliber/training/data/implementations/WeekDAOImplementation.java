package com.revature.caliber.training.data.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.beans.Week;
import com.revature.caliber.training.data.WeekDAO;


@Repository(value = "trainingTraineeDAOImplementation")
public class WeekDAOImplementation implements WeekDAO {
	
	private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

	
	@Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,
                    propagation = Propagation.REQUIRED,
                    rollbackFor = {Exception.class})
	public List<Week> getAllWeeks() {
		 Session session = sessionFactory.getCurrentSession();
	     Criteria allWeeks = session.createCriteria(Trainee.class);
	     return allWeeks.list();
	}

	@Override
	public Week getWeek(Integer weekId) {
		return (Week) sessionFactory.getCurrentSession().get(Week.class, weekId);
	}

	@Override
	public void createWeek(Week newWeek) {
		sessionFactory.getCurrentSession().saveOrUpdate(newWeek);
		
	}
	
}
