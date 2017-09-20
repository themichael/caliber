package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Address;
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
public class TrainingAPITest extends AbstractAPITest {

	private static final Logger log = Logger.getLogger(TrainingAPITest.class);

	/*
	 * Training API endpoints
	 */
	private String findByEmail = "training/trainer/byemail/patrick.walsh@revature.com/";
	private String createTrainer = "vp/trainer/create";
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
		log.info("API Testing findTrainerByEmail at " + baseUrl + findByEmail);
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findByEmail).then().assertThat().statusCode(200)
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}

	@Test
	public void createTrainer() throws Exception {
		Trainer expected = new Trainer("Randolph Scott", "Senior Trainer", "randolph.scott@revature.com",
				TrainerRole.ROLE_TRAINER);
		log.info("API Testing createTrainer at " + baseUrl + createTrainer);
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON)
				.body(new ObjectMapper().writeValueAsString(expected)).when().post(baseUrl + createTrainer).then()
				.assertThat().statusCode(201);
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
