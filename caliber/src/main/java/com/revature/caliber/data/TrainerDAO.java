package com.revature.caliber.data;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Trainer;

@Repository
public class TrainerDAO{
	
	private final static Logger log = Logger.getLogger(TrainerDAO.class);
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Used to help login a user by their email address ID
	 * @param email
	 * @return
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,
		propagation=Propagation.REQUIRES_NEW)
	public Trainer getByEmail(String email) {
		Trainer trainer = (Trainer) sessionFactory.getCurrentSession().createCriteria(Trainer.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
		log.info("DAO found trainer by email " + trainer);
		return trainer;
	}

}
