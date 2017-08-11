package com.revature.caliber.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Address;

/**
 * 
 * @author Christian Acosta
 *
 */
@Repository
public class AddressDAO {

	private static final Logger log = Logger.getLogger(AddressDAO.class);
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Save an address to the database
	 * 
	 * @param address
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveAddress(Address address) {
		log.info("Saving Address " + address);
		sessionFactory.getCurrentSession().save(address);

	}

	/**
	 * 
	 * @return a list of all addresses in the database
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Address> getAll() {
		log.info("Fetching all Addresses");
		try {
			return sessionFactory.getCurrentSession().createQuery("FROM Address").list();
		} catch (NullPointerException e) {
			log.debug(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param id
	 * @return the address with the specified id
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Address getAddressById(int id) {
		log.info("Fetching address with id " + id);
		return (Address) sessionFactory.getCurrentSession().get(Address.class, id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateAddress(Address toUpdate) {
		log.info("Updating " + toUpdate);
		sessionFactory.getCurrentSession().update(toUpdate);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAddress(Address toDelete) {
		sessionFactory.getCurrentSession().delete(toDelete);
	}
}
