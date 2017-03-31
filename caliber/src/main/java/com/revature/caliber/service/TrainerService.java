package com.revature.caliber.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Tier;
import com.revature.caliber.beans.Trainer;

/**
 * Provides services and data access
 *
 */
@Service
public class TrainerService {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void createTrainer(Trainer trainer) {
		sessionFactory.getCurrentSession().save(trainer);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Trainer getTrainer(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainer.class);
		criteria.add(Restrictions.eq("trainerId", id));
		return (Trainer) criteria.uniqueResult();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Trainer getTrainer(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainer.class);
		criteria.add(Restrictions.eq("email", email));
		return (Trainer) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public Set<Trainer> getAllTrainers() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainer.class);
		return new HashSet<>(criteria.list());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void updateTrainer(Trainer trainer) {
		sessionFactory.getCurrentSession().saveOrUpdate(trainer);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void deleteTrainer(Trainer trainer) {
		sessionFactory.getCurrentSession().delete(trainer);
	}
	/**
	 * Creates a new tier
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void createTier(Tier tier) {
		sessionFactory.getCurrentSession().save(tier);
	}

	/**
	 * Returns the tier associated with a specific id where id is a <b>short</b>
	 * .
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tier getTier(short id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tier.class);
		criteria.add(Restrictions.eq("tierId", id));
		return (Tier) criteria.uniqueResult();
	}

	/**
	 * Returns the tier associated with a specific name
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tier getTier(String name) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tier.class);
		criteria.add(Restrictions.eq("tier", name));
		return (Tier) criteria.uniqueResult();
	}

	/**
	 * Gets a list of all tiers
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Tier> getAllTiers() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tier.class);

		return criteria.list();
	}

	/**
	 * Updates a tier
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTier(Tier tier) {
		sessionFactory.getCurrentSession().saveOrUpdate(tier);
	}

	/**
	 * Deletes a tier
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTier(Tier tier) {
		sessionFactory.getCurrentSession().delete(tier);
	}

	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public List<Trainer> getTrainersInTier(short tierId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Trainer.class);
		criteria.add(Restrictions.eq("tier.tierId", tierId));
		return criteria.list();
	}
}
