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

import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.data.TierDAO;

@Repository(value = "tierDao")
public class TierDAOImplementation implements TierDAO {
	
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation=Isolation.REPEATABLE_READ,
					propagation=Propagation.REQUIRED,
					rollbackFor = {Exception.class})
	public void createTier(Tier tier) {
		sessionFactory.getCurrentSession().save(tier);
	}

	public Tier getTier(Integer id) {
		return null;
	}

	/**
	 * @deprecated This method currently serves no purpose
	 */
	public Tier getTier(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<Tier> getAllTiers() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tier.class);
		
		return criteria.list();
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateTier(Tier tier) {
		sessionFactory.getCurrentSession().saveOrUpdate(tier);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteTier(Tier tier) {
		sessionFactory.getCurrentSession().delete(tier);
	}

}
