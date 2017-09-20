package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;

import io.restassured.http.ContentType;

/**
 * API testing at the RESTful service message layer using REST Assured. All API
 * tests must be ignored in the initial build until the app is deployed into the
 * test environment.
 * 
 * @author Patrick Walsh
 *
 */
public class TrainingAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(TrainingAPITest.class);
	
	/*
	 * Trainer API endpoints
	 */
	private String findByEmail = "training/trainer/byemail/patrick.walsh@revature.com/";
	private String createTrainer = "vp/trainer/create";
	private String updateTrainer = "vp/trainer/update";
	private String makeInactive = "vp/trainer/delete";
	private String getAllTrainersTitles = "vp/trainer/titles";
	private String getAllTrainers= "all/trainer/all";
	//TEST ROLES!
	@Test
	public void findByEmail() throws Exception {
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(1);
		log.info("API Testing findTrainerByEmail at " + baseUrl + findByEmail);
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findByEmail).then().assertThat().statusCode(200)
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.TrainingController.createTrainer(Trainer)
	 * @throws Exception
	 */
	@Test
	public void createTrainer() throws Exception{
		Trainer expected = new Trainer("RolledBack", "Senior Trainer", "don.welshy@revature.com",
				TrainerRole.ROLE_TRAINER);
		log.info("API Testing createTrainer at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()			
		.post(baseUrl + createTrainer)
		.then().assertThat().statusCode(201);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.TrainingController.updateTrainer(Trainer)
	 * @throws Exception
	 */
	@Test
	public void updateTrainer() throws Exception{
		Trainer expected = new Trainer("Newwer Trainer", "Senior Trainer", "don.welshy@revature.com",
				TrainerRole.ROLE_TRAINER);
		expected.setTrainerId(3);
		log.info("API Testing updateTrainer at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()				
		.put(baseUrl + updateTrainer)
		.then().assertThat().statusCode(204);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.TrainingController.makeInactive(Trainer)
	 * @throws Exception
	 */
	@Test
	public void makeInactive() throws Exception{
		Trainer expected = new Trainer("Dan Pickles", "Lead Trainer", "pjw6193@hotmail.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(2);
		log.info("API Testing makeInactiv at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()				
		.delete(baseUrl + makeInactive)
		.then().assertThat().statusCode(204);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.TrainingController.getAllTrainersTitles()
	 * @throws Exception
	 */
	@Test
	public void getAllTrainersTitles() throws Exception {
		log.info("API Testing findTrainerByEmail at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllTrainersTitles).then().assertThat()
				.statusCode(200);
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.TrainingController.getAllTrainers()
	 * @throws Exception
	 */
	@Test
	public void getAllTrainers() throws Exception {
		log.info("API Testing findTrainerByEmail at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllTrainers).then().assertThat()
				.statusCode(200);
	}

}

