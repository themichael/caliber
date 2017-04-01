package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Category;
import com.revature.caliber.data.CategoryDAO;

/**
 * Provides logic concerning categories data.
 * Application logic has no business being in a DAO
 * nor in a Controller. This is the ideal place for calculations 
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class CategoryService {

	private static final Logger log = Logger.getLogger(CategoryService.class);
	private CategoryDAO categoryDAO;
	
	@Autowired
	public void setCategoryDAO(CategoryDAO categoryDAO) {this.categoryDAO = categoryDAO;}
	
	public List<Category> findAll(){
		log.debug("Requesting categories");
		return categoryDAO.findAll();
	}
	
}
