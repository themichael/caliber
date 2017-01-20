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

import com.revature.caliber.training.beans.Week;
import com.revature.caliber.training.data.WeekDAO;

@Repository(value = "weekDao")
public class WeekDAOImplementation implements WeekDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Week> getAllWeek() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from com.revature.caliber.training.beans.Week");
		return query.list();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Week getWeekById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Week) session.get(Week.class, id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createNewWeek(Week newWeek) {
		Session session = sessionFactory.getCurrentSession();
		session.save(newWeek);
	}

}
