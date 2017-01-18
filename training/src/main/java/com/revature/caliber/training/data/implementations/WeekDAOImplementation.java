package com.revature.caliber.training.data.implementations;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.beans.Week;
import com.revature.caliber.training.data.WeekDAO;

@Repository(value = "trainingWeekDAOImplementation")
public class WeekDAOImplementation implements WeekDAO{

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
	
	
    @Transactional(isolation = Isolation.REPEATABLE_READ,
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class})
	public List<Week> getAllWeek() {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("from com.revature.caliber.training.beans.Week");
    	return query.list();
	}

	@Override
	public Week getWeekById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Week> getWeekByWeekNumber(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Week> getWeekByTopic(Category topic) {
		// TODO Auto-generated method stub
		return null;
	}

}
