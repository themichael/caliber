package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.*;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Address;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.beans.TrainingStatus;

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
	private String findAllDroppedTrainees = "all/trainee/dropped";
	private String findAllTraineesInBatch = "all/trainee";
	private String getAllBatches = "qc/batch/all";
	private String getAllCurrentBatches = "vp/batch/all/current";
	private String updateBatch = "all/batch/update";
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



	@Test
	public void findByEmail() throws Exception {
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(1);
		log.info("API Testing findTrainerByEmail at baseUrl  " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when().get(baseUrl + findByEmail)
				.then().assertThat().statusCode(200)
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}

	/**
	 * findAllDroppedByBatch(@RequestParam(required = true) Integer batch)
	 * @throws Exception 
	 */
	@Test
	public void findAllDroppedByBatchTest() throws Exception{
		
		log.info("API Testing findAllDroppedByBatchTest at baseUrl  " + baseUrl);
		Response actual = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).queryParam("batch", "2050").when()
				.get(baseUrl + findAllDroppedTrainees).then().assertThat().statusCode(200).extract().response();
		Trainee[] resultSet = actual.as(Trainee[].class);
		boolean success = true;
		for(Trainee dude : resultSet){
			if(dude.getTrainingStatus() != TrainingStatus.Dropped){
				success = false;
			}
		}
		assertTrue(success);
	}
	
	/**
	 * findAllByBatch(@RequestParam(required = true) Integer batch)
	 * @throws Exception 
	 */
	@Test
	public void findAllByBatchTest(){
		log.info("API Testing findAllByBatchTest at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).queryParam("batch", "2050").when()
				.get(baseUrl + findAllTraineesInBatch).then().assertThat().statusCode(200);
	}
	
	/**
	 * getAllBatches()
	 */
	@Test
	public void getAllBatchesTest(){
		log.info("API Testing getAllBatchesTest at baseUrl  " + baseUrl);
		Response actual = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllBatches).then().assertThat().statusCode(200).extract().response();
		Batch[] resultSet = actual.as(Batch[].class);
		boolean success = false;
		if (resultSet.length > 0){
			success = true;
		}
		assertTrue(success);
	}
	
	/**
	 * getAllCurrentBatches()
	 */
	@Test
	public void getAllCurrentBatchesTest(){
		log.info("API Testing getAllCurrentBatchesTest at baseUrl  " + baseUrl);
		Response actual = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllCurrentBatches).then().assertThat().statusCode(200).extract().response();
		Batch[] resultSet = actual.as(Batch[].class);
		boolean success = false;
		if (resultSet.length > 0){
			success = true;
		}
		assertTrue(success);
	}
	
	/**
	 * updateBatch(@Valid @RequestBody Batch batch)
	 */
	@Test
	public void updateBatchTest(){
		//Pull a batch from the database…
		Response resultBatchSet = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllBatches).then().extract().response();
		Batch[] resultSet = resultBatchSet.as(Batch[].class);
		Batch holderBatch = resultSet[0];
		//Save original location…
		String original = holderBatch.getLocation();
		//Change to a test location…
		holderBatch.setLocation("In the testing zone!");
		//Try to update the batch…
		log.info("API Testing updateBatch at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(holderBatch).when()
				.put(baseUrl + updateBatch).then().assertThat().statusCode(200);
		//See if it actually changed in the database…
		resultBatchSet = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + getAllBatches).then().extract().response();
		resultSet = resultBatchSet.as(Batch[].class);
		boolean success = false;
		for(Batch batch : resultSet){
			if(batch.getLocation().equals("In the testing zone!")){
				success = true;
			}
		}
		assertTrue(success);
		//Change it back…
		holderBatch.setLocation(original);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(holderBatch).when()
		.put(baseUrl + updateBatch).then().assertThat().statusCode(200);
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

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#createLocation
	 */
	@Test
	public void createLocationTest() {
		Address location = new Address(20, "299 CherryStreet", "FruityCity", "FL", "55555", "Revature", true);
		log.info("API Testing createLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
				.when().post(baseUrl + createLocationTest).then().assertThat().statusCode(201);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#updateLocation
	 */
	@Test
	public void updateLocationTest() throws JsonProcessingException {
		Address location = new Address(1, "299 CherryStreet", "FruityCity", "FL", "55555", "Revature", true);
		log.info("API Testing updateLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
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
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
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
		Address location = new Address(1, "299 CherryStreet", "FruityCity", "FL", "55555", "Revature", false);
		log.info("API Testing removeLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
				.when().delete(baseUrl + removeLocationTest).then().assertThat().statusCode(204);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.controllers.TrainingController#reactivateLocation
	 */
	@Test
	public void reactivateLocationTest() {
		Address location = new Address(1, "299 CherryStreet", "FruityCity", "FL", "55555", "Revature", false);
		log.info("API Testing reactivateLocation at baseUrl " + baseUrl);
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
				.when().put(baseUrl + reactivateLocationTest).then().assertThat().statusCode(204);
	}
}

