package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.junit.Test;

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
public class TrainerAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(TrainerAPITest.class);
	
	/*
	 * Trainer API endpoints
	 */
	private String findByEmail = "training/trainer/byemail/patrick.walsh@revature.com/";
	private String createTrainee ="/all/trainee/create";
	private String updateTrainee = "/all/trainee/update";
	private String deleteTrainee = "/all/trainee/delete/5468";
	private String retreiveTraineeByEmail = "/all/trainee/getByEmail/starrv2011@gmail.com";

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
	public void createTraineeTest(){
		
	}
	@Test
	public void updateTraineeTest(){
		
	}
	@Test
	public void deleteTraineeTest(){
		
	}
	@Test
	public void retreiveTraineeByEmailTest(){
		
	}	
	
}
