package com.revature.caliber.data;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.Trainer;

@Repository
public class TrainerDAO {

	private final static Logger log = Logger.getLogger(TrainerDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Find a trainer by their email address. Practical for authenticating users
	 * through SSO
	 * 
	 * @param email
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Trainer findByEmail(String email) {
		Trainer trainer = (Trainer) sessionFactory.getCurrentSession().createCriteria(Trainer.class)
				.add(Restrictions.eq("email", email)).uniqueResult();
		log.info("DAO found trainer by email " + trainer);
		return trainer;
	}

	/**
	 * Find all trainers. Useful for listing available trainers to select as
	 * trainer and cotrainer
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Trainer> findAll() {
		log.info("Finding all trainers");
		return sessionFactory.getCurrentSession().createCriteria(Trainer.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * 
	 * Convenience method only. Not practical in production since trainers must
	 * be registered in the Salesforce with a matching email address.
	 * 
	 * @param trainer
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Trainer trainer) {
		log.info("Save trainer " + trainer);
		sessionFactory.getCurrentSession().save(trainer);
	}

	/**
	 * Find trainer by the given identifier
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Trainer findOne(Integer trainerId) {
		log.info("Find trainer by id: " + trainerId);
		return (Trainer) sessionFactory.getCurrentSession().createCriteria(Trainer.class)
				.add(Restrictions.eq("trainerId", trainerId)).uniqueResult();
	}

	/**
	 * Updates a trainer in the database.
	 * 
	 * @param trainer
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Trainer trainer) {
		log.info("Update trainer " + trainer);
		sessionFactory.getCurrentSession().saveOrUpdate(trainer);
	}

	/**
	 * Convenience method only. Deletes a trainer from the database. Trainer
	 * will still be registered with a Salesforce account.
	 * 
	 * @param trainer
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Trainer trainer) {
		log.info("Delete trainer " + trainer);
		sessionFactory.getCurrentSession().delete(trainer);
	}

}
