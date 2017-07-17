package com.revature.caliber.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.controllers.CategoryController;

public class CategoryTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(CategoryTest.class);

	@Autowired
	private CategoryController categoryController;

	private static final String CATEGORY_COUNT = "select count(category_id) from caliber_category";

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// CATEGORY API
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.CategoryController#findAllCategories()
	 */
	@Test
	public void findAllCategories() {
		
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.CategoryController#findCategoryById(int)
	 */
	@Test
	public void findCategoryById(int id) {
		
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.CategoryController#findAll()
	 */
	@Test
	public void findAll() {
		
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.CategoryController#updateCategory(Category)
	 */
	@Test
	public void updateCategory(Category category) {
		
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.CategoryController#saveCategory(Category)
	 */
	@Test
	public void saveCategory(Category category) {
		
	}
}
