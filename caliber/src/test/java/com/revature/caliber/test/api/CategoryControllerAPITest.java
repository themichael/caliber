package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import javax.swing.tree.RowMapper;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.controllers.CategoryController;
import com.revature.caliber.data.CategoryDAO;

import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;

public class CategoryControllerAPITest extends AbstractAPITest {
	private static final Logger log = Logger.getLogger(CategoryControllerAPITest.class);
	String FINDALLTEST = "select count(CATEGORY_ID) from CALIBER_CATEGORY";
	String UPDATE = "select SKILL_CATEGORY from CALIBER_CATEGORY where CATEGORY_ID = 1";
	String ALL = "select * from CALIBER_CATEGORY";
	String SKILL = "select SKILL_CATEGORY from CALIBER_CATEGORY";
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
	@Ignore
	@Test
	public void findAllCategoriesTest(){
		log.info("Testing findAllCategories function from CategoryController");
	//	given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
	//	.get(baseUrl + "category/all").then().assertThat()
	//	.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	@Ignore
	@Test
	public void findAllTest(){
		log.info("Testing findAll function from CategoryController");
		int size = jdbcTemplate.queryForObject(FINDALLTEST,Integer.class);
	//	given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
	//	.get(baseUrl + "vp/category").then().assertThat()
	//	.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	@Test
	public void findCategoryByIdTest() throws JsonProcessingException{
		log.info("Testing findCategoryById function from CategoryController");
		Category expected = new Category("BUSTOP",true);
		//expected.setCategoryId(100);
		//dao.save(expected);
		//Category test = dao.findOne(expected.getCategoryId());
		//System.out.println(test+"  FSDSDFSDFSDFSDFSDFS");
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + "category/"+expected.getCategoryId()).then().assertThat()
		.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
		
		
	
	@Test//(expected = IndexOutOfBoundsException.class)
	public void FailfindCategoryByIdTest(){
		log.info("Testing findCategoryById function from CategoryController");
		controller.findCategoryById(1);
		
	}
	@Test
	public void updateCategoryTest() throws Exception{
		log.info("Testing updateCategory function from CategoryController");
		Category category = dao.findOne(1);
		//System.out.println(category+"AAAAAAAAAA");
		category.setSkillCategory("Swifter Mop");
		category.setActive(false);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + "vp/category/update").then().assertThat()
		.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(category)));
	}
	@Test
	public void saveCategoryTest() throws JsonProcessingException{
		log.info("Testing saveCategory function from CategoryController");
		//int before = jdbcTemplate.queryForObject(FINDALLTEST,Integer.class);
		Category category2 = new Category("Super Linux2",false);
		//category.setCategoryId(47);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + "vp/category/").then().assertThat()
		.statusCode(201).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(category2)));		
	}
}
