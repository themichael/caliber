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
public class TraineeAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(TraineeAPITest.class);
	
	/*
	 * Trainer API endpoints
	 */
	private String findByEmail = "training/trainer/byemail/patrick.walsh@revature.com/";

	@Test
	public void findByEmail() throws Exception {
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(1);
		log.info("API Testing findTrainerByEmail at baseUrl  " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findByEmail).then().assertThat()
				.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	@Test
	public void createLocationTest(){
		Address location = new Address(20,"299 CherryStreet","FruityCity", "FoodState","55555","Revature",true);
		log.info("API Testing createLocation at baseUrl " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
			.when().post(baseUrl + "vp/location/create")
			.then().assertThat().statusCode(201);
	}
	@Test
	public void updateLocationTest() throws JsonProcessingException{
		Address location = new Address(1,"299 CherryStreet","FruityCity", "FoodState","55555","Revature",true);
		log.info("API Testing updateLocation at baseUrl " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
			.when().put(baseUrl + "vp/location/update")
			.then().assertThat().statusCode(204).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(location)));
	}
	@Test
	public void getAllLocationsTest() throws JsonProcessingException{
		List<Address> expect = new ArrayList<>();
		expect.add(new Address(1,"65-30 Kissena Blvd, CEP Hall 2","Queens", "NY","11367","Tech Incubator at Queens College",true));
		expect.add(new Address(2,"11730 Plaza America Drive, 2nd Floor","Reston","VA","20190","Revature LLC",true));
		log.info("API Testing updateLocation at baseUrl " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/location/all")
			.then().assertThat().statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expect)));
	}
	@Test
	public void removeLocationTest(){
		Address location = new Address(1,"299 CherryStreet","FruityCity", "FoodState","55555","Revature",false);
		log.info("API Testing removeLocation at baseUrl " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
			.when().delete(baseUrl + "vp/location/delete")
			.then().assertThat().statusCode(204);
	}
	@Test
	public void reactivateLocationTest(){
		Address location = new Address(1,"299 CherryStreet","FruityCity", "FoodState","55555","Revature",false);
		log.info("API Testing reactivateLocation at baseUrl " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).body(location)
			.when().put(baseUrl + "vp/location/reactivate")
			.then().assertThat().statusCode(204);
	}
	

}
