package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
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
	private String createBatch = "vp/batch/create";
	private String deleteBatch = "all/batch/delete/{id}";
	private String findAllBatchesByTrainer = "trainer/batch/all";
	private String createWeek = "trainer/week/new/{batchId}";
	private String findCommonLocations = "all/locations";

	@Test
	public void findByEmail() throws Exception {
		
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(1);
		log.info("API Testing findTrainerByEmail at " + baseUrl + findByEmail);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findByEmail).then().assertThat().statusCode(200)
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
		
	}

	@Test
	public void createTrainer() throws Exception {
		
		Trainer expected = new Trainer("Randolph Scott", "Senior Trainer", "randolph.scott@revature.com",
				TrainerRole.ROLE_TRAINER);
		log.info("API Testing createTrainer at " + baseUrl + createTrainer);
		given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()
				.post(baseUrl + createTrainer).then().assertThat().statusCode(201);
		
	}
	
	@Test
	public void deleteBatch() throws Exception {
		
		given().spec(requestSpec).header(auth, accessToken).pathParam("id", 2201).contentType(ContentType.JSON).when()
				.delete(baseUrl + deleteBatch).then().assertThat().statusCode(200);
		
	}
	
	@Test
	public void createBatch() throws Exception {
		
		Trainer expectedTrainer = new Trainer("Dan Pickles", "Senior Trainer", "dan.pickles@gmail.com", TrainerRole.ROLE_TRAINER);
		Batch expected = new Batch("Create Controller TrainingAPI Test", expectedTrainer, java.sql.Date.valueOf(LocalDate.now().toString()), java.sql.Date.valueOf(LocalDate.now().toString()), "Some Location" );
		log.info("API Testing createBatch at " + baseUrl + createBatch);
		
		given().spec(requestSpec).header(auth, accessToken)
			.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()
			//request to create a batch
					.post(baseUrl + createBatch)
			//assertions
					.then().assertThat().statusCode(201);
		
	}
	
	@Test
	public void findAllBatchesByTrainer() throws Exception {
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
        //get request for all batches by a trainer
        .when().get(baseUrl + findAllBatchesByTrainer)
        //assertions
        .then().assertThat().statusCode(200);
		
	}
	
	@Test
	public void createWeek() throws Exception {
		
		given().spec(requestSpec).header(auth, accessToken).pathParam("batchId", 2201).contentType(ContentType.JSON)
			//request to create a week for a specific batch
			.when().post(baseUrl + createWeek)
			//assertions
			.then().assertThat().statusCode(201);
		
	}
	
	@Test
	public void findCommonLocations() throws Exception {
		
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
		//request to get a list of common locations
			.when().get(baseUrl + findCommonLocations)
		//assertions
			.then().assertThat().statusCode(200);
		
	}

}
