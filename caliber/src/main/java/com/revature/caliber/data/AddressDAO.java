package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Address;

@Repository
public class AddressDAO {

	private static final Logger log = Logger.getLogger(AddressDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Find all locations.
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<Address> findAll() {
		log.info("Finding all locations");
		return sessionFactory.getCurrentSession().createCriteria(Address.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * Save location
	 * 
	 * @param location
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Address location) {
		log.info("Save location" + location);
		sessionFactory.getCurrentSession().save(location);
	}

	/**
	 * Updates a location in the database.
	 * 
	 * @param location
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Address location) {
		log.info("Update location " + location);
		sessionFactory.getCurrentSession().saveOrUpdate(location);
	}

	/**
	 * Deletes a location from the database.
	 * 
	 * @param location
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Address location) {
		log.info("Delete location " + location);
		sessionFactory.getCurrentSession().delete(location);
	}

	public Address getOne(long l) {
		return (Address) sessionFactory.getCurrentSession().get(Address.class, l);
	}

}
