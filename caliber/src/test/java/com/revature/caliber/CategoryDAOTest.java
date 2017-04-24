/**
 * 
 */
package com.revature.caliber;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.data.CategoryDAO;

import junit.framework.Assert;

/**
 * @author user
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class CategoryDAOTest {

	@Autowired
	private CategoryDAO categoryDAO = new CategoryDAO();
	private static Logger log = Logger.getLogger(CategoryDAO.class);
	private SessionFactory sessionFactory;
	
	/**
	 * Test method for {@link com.revature.caliber.data.CategoryDAO#setSessionFactory(org.hibernate.SessionFactory)}.
	 */
	@Test
	@Autowired
	public void testSetSessionFactory() {
		categoryDAO.setSessionFactory(sessionFactory);
	}

	/**
	 * Test method for {@link com.revature.caliber.data.CategoryDAO#findAll()}.
	 */
	@Test
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public void testFindAll() {
		testSetSessionFactory();
		categoryDAO.findAll();
	}

	/**
	 * Test method for {@link com.revature.caliber.data.CategoryDAO#findOne(java.lang.Integer)}.
	 */
	@Test
	public void testFindOne() {
		testSetSessionFactory();
		categoryDAO.findOne(6);
	}

}
