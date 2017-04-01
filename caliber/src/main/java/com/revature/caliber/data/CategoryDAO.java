package com.revature.caliber.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.beans.Category;

@Repository
public class CategoryDAO {

	private final static Logger log = Logger.getLogger(CategoryDAO.class);
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,
			propagation=Propagation.REQUIRES_NEW)
	public List<Category> findAll(){
		log.debug("Fetching categories");
		
		@SuppressWarnings("unchecked")
		Set<Category> noduplicates = new HashSet<Category>(sessionFactory.getCurrentSession()
				.createCriteria(Category.class).list());
		return new ArrayList<Category>(noduplicates);
	}
	
}
