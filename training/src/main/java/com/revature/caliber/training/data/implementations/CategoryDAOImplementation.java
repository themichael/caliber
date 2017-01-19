package com.revature.caliber.training.data.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.data.CategoryDAO;

/**
 * 
 * Implementation for the Category DAO 
 *
 */
public class CategoryDAOImplementation implements CategoryDAO{
	
	private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
    
    
    
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class})
	public Category getCategory(String category) {
		return (Category) sessionFactory.getCurrentSession().get(Category.class, category);
	}
	
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class})
	public List<Category> getAllCategories() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
		return criteria.list();
	}

   

}
