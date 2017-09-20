package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.tree.RowMapper;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.controllers.CategoryController;
import com.revature.caliber.data.CategoryDAO;

import antlr.collections.List;
import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
public class CategoryAPITest extends AbstractAPITest {
	private static final Logger log = Logger.getLogger(CategoryAPITest.class);
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
	
	@Test
	public void findCategoryByIdTest() throws JsonProcessingException{
		log.info("Testing findCategoryById function from CategoryController");
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + "category/1").then().assertThat()
		.statusCode(200).body("skillCategory", equalTo("Java"),"active", equalTo(true));
	}
	@Test
	public void FailfindCategoryByIdTest(){
		log.info("Testing FAIL findCategoryById function from CategoryController");
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + "category/-1").then().assertThat()
		.statusCode(200).body("results", Matchers.isEmptyOrNullString());
	}
	@Test
	public void findAllActiveTest(){
		log.info("Testing findAllCategories function from CategoryController");
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + "category/all").then().assertThat()
		.statusCode(200).body("body.size",is(46),"active",not(hasItem(false))); 
	}
	//I create a false object otherwise there is no false object and it fails.
	@Test
	public void findAllTest(){
		log.info("Testing findAll function from CategoryController");
		Category category2 = new Category("Super Linux2",false);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(category2).when()
		.post(baseUrl + "vp/category/").then().assertThat()
		.statusCode(201);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + "vp/category/").then().assertThat()
		.statusCode(200).body("active",hasItem(false));
	}
	@Test
	public void updateCategoryTest() throws Exception{
		log.info("Testing updateCategory function from CategoryController");
		String[] theList = new String[]{"The Room","Trolls2","Grabage pail kids","Little panda fighter"};
		int randomtheList = ThreadLocalRandom.current().nextInt(0, 3);
		Category category = dao.findOne(45);
		category.setSkillCategory(theList[randomtheList]);
		category.setActive(true);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(category).when()
		.put(baseUrl + "vp/category/update").then().assertThat()
		.statusCode(200).and().body("skillCategory",equalTo(category.getSkillCategory()),"active",equalTo(category.isActive()));
	}
	@Test
	public void saveCategoryTest() throws JsonProcessingException{
		log.info("Testing saveCategory function from CategoryController");
		//int before = jdbcTemplate.queryForObject(FINDALLTEST,Integer.class);
		Category category2 = new Category("Super Linux2",false);
		//category.setCategoryId(47);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(category2).when()
		.post(baseUrl + "vp/category/").peek().then().assertThat()
		.statusCode(201).and().body("skillCategory",equalTo("Super Linux2"));
	}
}
