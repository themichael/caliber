package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.caliber.beans.Address;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * API testing at the RESTful service message layer using REST Assured. All API
 * tests must be ignored in the initial build until the app is deployed into the
 * test environment.
 * 
 * @author Patrick Walsh
 *
 */
public class TrainingAPITest extends AbstractAPITest {

	private static final Logger log = Logger.getLogger(TrainingAPITest.class);

	/*
	 * Training API endpoints
	 */
	private String findByEmail = "training/trainer/byemail/patrick.walsh@revature.com/";

	private String createTrainer = "vp/trainer/create";
	private String updateTrainer = "vp/trainer/update";
	private String makeInactive = "vp/trainer/delete";
	private String getAllTrainersTitles = "vp/trainer/titles";
	private String getAllTrainers= "all/trainer/all";
	private String createLocationTest = "vp/location/create";
	private String updateLocationTest = "vp/location/update";
	private String getAllLocationTest = "all/location/all";
	private String removeLocationTest = "vp/location/delete";
	private String reactivateLocationTest = "vp/location/reactivate";
	
	private Address cherryStreetAddress = new Address(1, "299 CherryStreet", "FruityCity", "FL", "55555", "Revature", true);
	private String createBatch = "vp/batch/create";
	private String deleteBatch = "all/batch/delete/{id}";
	private String findAllBatchesByTrainer = "trainer/batch/all";
	private String createWeek = "trainer/week/new/{batchId}";
	private String findCommonLocations = "all/locations";
	private String leadTrainer = "Lead Trainer";
	private String seniorTrainer = "Senior Trainer";

	@Test
	public void findByEmail() throws Exception {
		
		Trainer expected = new Trainer("Patrick Walsh", leadTrainer, "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(1);
		log.info("API Testing findTrainerByEmail at " + baseUrl + findByEmail);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).when()
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
		
		Trainer expected = new Trainer("RolledBack", seniorTrainer, "don.wels23hy@revature.com",
				TrainerRole.ROLE_TRAINER);
		log.info("API Testing createTrainer at baseUrl  " + baseUrl + createTrainer);
		given().spec(requestSpec).header(auth, accessToken)
		.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()			
		.post(baseUrl + createTrainer)
		.then().assertThat().statusCode(201).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
		
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.TrainingController.updateTrainer(Trainer)
	 * @throws Exception
	 */
	@Test
	public void updateTrainer() throws Exception{
		Trainer expected = new Trainer("Newwer Trainer", seniorTrainer, "don.welshy@revature.com",
				TrainerRole.ROLE_TRAINER);
		expected.setTrainerId(3);
		log.info("API Testing updateTrainer at baseUrl  " + baseUrl + updateTrainer);
		given().spec(requestSpec).header(auth, accessToken)
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
		Trainer expected = new Trainer("Dan Pickles", leadTrainer, "pjw6193@hotmail.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(2);
		log.info("API Testing makeInactiv at baseUrl  " + baseUrl + makeInactive);
		given().spec(requestSpec).header(auth, accessToken)
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
		log.info("API Testing getAllTrainersTitles at baseUrl  " + baseUrl + getAllTrainersTitles);
		Response titles = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllTrainersTitles).then().assertThat()
				.statusCode(200).extract().response();
		assertTrue("Test titles", titles.asString().contains(seniorTrainer)
				& titles.asString().contains("Senior Technical Manager")
				& titles.asString().contains(leadTrainer)
				& titles.asString().contains("Trainer")
				& titles.asString().contains("Technology Manager"));
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.TrainingController.getAllTrainers()
	 * @throws Exception
	 * revist when we have transient tests to test more specific trainers.
	 */
	@Test
	public void getAllTrainers() throws Exception {
		log.info("API Testing getAllTrainers at baseUrl  " + baseUrl + getAllTrainers);
		Trainer[] trainers = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllTrainers).then().assertThat()
				.statusCode(200).extract().response().as(Trainer[].class);
		assertTrue("Test that some trainers exist", "Patrick Walsh".equals(trainers[0].getName()));
		log.info(" SOME STUFF" + trainers.length + " " + trainers[1].getName());
		
	}
	
	@Test
	public void deleteBatch() throws Exception {
		
		given().spec(requestSpec).header(auth, accessToken).pathParam("id", 2201).contentType(ContentType.JSON).when()
				.delete(baseUrl + deleteBatch).then().assertThat().statusCode(200);
		
	}
	
	@Test
	public void createBatch() throws Exception {
		
		Trainer expectedTrainer = new Trainer("Dan Pickles", seniorTrainer, "dan.pickles@gmail.com", TrainerRole.ROLE_TRAINER);
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

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#createLocation
	 */
	@Test
	public void createLocationTest() {
		Address location = cherryStreetAddress;
		location.setAddressId(20);
		log.info("API Testing createLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(location)
				.when().post(baseUrl + createLocationTest).then().assertThat().statusCode(201);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#updateLocation
	 */
	@Test
	public void updateLocationTest() throws JsonProcessingException {
		Address location = cherryStreetAddress;
		location.setState("PA");
		log.info("API Testing updateLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(location)
				.when().put(baseUrl + updateLocationTest).then().assertThat().statusCode(204);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#getAllLocations
	 */
	@Test
	public void getAllLocationsTest() throws JsonProcessingException {
		Address expect1 = new Address(1, "65-30 Kissena Blvd, CEP Hall 2", "Queens", "NY", "11367",
				"Tech Incubator at Queens College", true);
		Address expect2 = new Address(2, "11730 Plaza America Drive, 2nd Floor", "Reston", "VA", "20190",
				"Revature LLC", true);
		log.info("API Testing updateLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllLocationTest).then().assertThat().statusCode(200)
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expect1)))
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expect2)));
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#removeLocation
	 */
	@Test
	public void removeLocationTest() {
		Address location = cherryStreetAddress;
		location.setActive(false);
		log.info("API Testing removeLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(location)
				.when().delete(baseUrl + removeLocationTest).then().assertThat().statusCode(204);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#reactivateLocation
	 */
	@Test
	public void reactivateLocationTest() {
		log.info("API Testing reactivateLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(cherryStreetAddress)
				.when().put(baseUrl + reactivateLocationTest).then().assertThat().statusCode(204);
	}
}

