package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.swing.tree.RowMapper;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.controllers.CategoryController;
import com.revature.caliber.data.CategoryDAO;

public class CategoryControllerTest extends AbstractAPITest {
	private static final Logger log = Logger.getLogger(AddressDAOTest.class);
	String FINDALLTEST = "select count(CATEGORY_ID) from CALIBER_CATEGORY";
	String UPDATE = "select * from CALIBER_CATEGORY where CATEGORY_ID = 1";
	String ALL = "select * from CALIBER_CATEGORY";
	
	CategoryDAO dao;
	CategoryController controller;
	@Autowired
	public void setDao(CategoryDAO dao) {
		this.dao = dao;
	}
	@Autowired
	public void setController(CategoryController controller) {
		this.controller = controller;
	}
	
	@Test
	public void findAllCategoriesTest(){
		log.info("Testing findAllCategories function from CategoryController");
		//given().when().get("/category/all").then().statusCode(200);
		int size = jdbcTemplate.queryForObject(FINDALLTEST,Integer.class);
		assertEquals(size,controller.findAllCategories().getBody().size());
	}
	@Test
	public void findAllTest(){
		log.info("Testing findAll function from CategoryController");
		int size = jdbcTemplate.queryForObject(FINDALLTEST,Integer.class);
		assertEquals(size,controller.findAll().getBody().size());
		assertEquals(jdbcTemplate.queryForList(ALL), controller.findAll().getBody().toArray());
	}
	@Test
	public void findCategoryByIdTest(){
		log.info("Testing findCategoryById function from CategoryController");
		Category category = dao.findOne(1);
		assertEquals(controller.findCategoryById(1), category);
		
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void FailfindCategoryByIdTest(){
		log.info("Testing findCategoryById function from CategoryController");
		controller.findCategoryById(0);
		
	}
	@Test
	public void updateCategoryTest(){
		log.info("Testing updateCategory function from CategoryController");
		Category category = dao.findOne(1);
		category.setSkillCategory("Swifter Mop");
		controller.updateCategory(category);
		assertEquals(jdbcTemplate.queryForObject(UPDATE, RowMapper.class), category);
	}
	@Test
	public void saveCategoryTest(){
		log.info("Testing saveCategory function from CategoryController");
		int before = jdbcTemplate.queryForObject(FINDALLTEST,Integer.class);
		Category category = new Category("Super Linux",false);
		controller.saveCategory(category);
		int categoryId = category.getCategoryId();
		int after = jdbcTemplate.queryForObject(FINDALLTEST,Integer.class);
		assertEquals(++before, after);
		assertEquals(category,controller.findAllCategories().getBody().get(categoryId));
		
	}
}
