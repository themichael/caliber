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

import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.services.CategoryService;

/**
 * @author user
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class CategoryServiceTest {

	@Autowired
	private CategoryDAO categoryDAO;
	CategoryService categoryService = new CategoryService();
	private static Logger log = Logger.getLogger(CategoryServiceTest.class);


	/**
	 * Test method for
	 * {@link com.revature.caliber.services.CategoryService#setCategoryDAO(com.revature.caliber.data.CategoryDAO)}.
	 */
	@Test
	public void testSetCategoryDAO() {
		log.info("Testing setCategoryDAO");
		categoryService.setCategoryDAO(categoryDAO);
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.services.CategoryService#findAllCategories()}.
	 */
	@Test
	public void testFindAllCategories() {
		log.info("Test to find all categories");

		try {
			testSetCategoryDAO();
			categoryService.findAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.revature.caliber.services.CategoryService#findCategory(int)}.
	 */
	@Test
	public void testFindCategory() {
		log.info("test to find a specific category");

		try {
			testSetCategoryDAO();
			categoryService.findCategory(66);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}