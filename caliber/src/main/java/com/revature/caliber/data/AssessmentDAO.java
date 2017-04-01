package com.revature.caliber.data;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Assessment;

@Repository
public class AssessmentDAO {

	private final static Logger log = Logger.getLogger(AssessmentDAO.class);
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,
			propagation=Propagation.REQUIRES_NEW)
	public void save(Assessment assessment){
		log.info("Saving assessment " + assessment);
		sessionFactory.getCurrentSession().save(assessment);
	}
	
}
