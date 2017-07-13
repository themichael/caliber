package com.revature.caliber.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.controllers.CategoryController;

public class CategoryTest extends CaliberTest{
	
	private static final Logger log = Logger.getLogger(AssessmentTest.class);

	@Autowired
	private CategoryController categoryController;

	private static final String CATEGORY_COUNT = "select count(category_id) from caliber_category";

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// ASSESSMENT API
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.CategoryController#findAllCategories()
	 */
	public List<Category> findAllCategories(){
		
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.CategoryController#findCategoryById(int)
	 */
	public Category findCategoryById(int id){
		
	}
	
}
