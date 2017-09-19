package com.revature.caliber.test.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

public class TypeAPITest extends AbstractAPITest {

	private static final Logger log = Logger.getLogger(TypeAPITest.class);

	/*
	 * Type API endpoints
	 */
	
	private static final String allSkillTypes = "types/skill/all";
	private static final String allTrainingTypes = "types/training/all";
	private static final String allTrainingStatus = "types/trainingstatus/all";
	private static final String allNoteTypes = "types/note/all";
	private static final String allQCStatusTypes = "types/qcstatus/all";
	private static final String allAssessmentTypes = "types/assessment/all";
	private static final String allTrainerRoles = "types/trainer/role/all";


	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.TypeController#allSkillTypes()
	 */
	@Test
	public void allSkillTypes() throws Exception {

		log.info("API Testing allSkillTypes at baseUrl  " + baseUrl);

		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all skills
		.when().get(baseUrl + allSkillTypes)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("J2EE", ".NET", "SDET", "BPM", "Other"))
			.body("$", not(hasItems("Cukes", "Parfait")));
	
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.TypeController#allTrainingTypes()
	 */
	@Test
	public void allTrainingTypes(){
		
		log.info("API Testing allTrainingTypes at baseUrl  " + baseUrl);

		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all training types
		.when().get(baseUrl + allTrainingTypes)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("Revature", "Corporate", "University", "Other"))
			.body("$", not(hasItems("Gherkins", "Parfait")));
		
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.TypeController#allTrainingStatusTypes()
	 */
	@Test
	public void allTrainingStatusTypes(){
		
		log.info("API Testing allTrainingStatusTypes at baseUrl  " + baseUrl);

		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all training status
		.when().get(baseUrl + allTrainingStatus)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("Signed", "Selected", "Training",
					"Marketing", "Confirmed", "Employed", "Dropped"))
			.body("$", not(hasItems("Gherkins", "Tofu Steak")));
		
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.TypeController#allNoteTypes()
	 */
	@Test
	public void allNoteTypes(){
		
		log.info("API Testing allNoteTypes at baseUrl  " + baseUrl);

		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all note types
		.when().get(baseUrl + allNoteTypes)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("TRAINEE", "BATCH", "QC_TRAINEE","QC_BATCH"))
			.body("$", not(hasItems("Pudding a la mode", "Tofu Steak")));
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.TypeController#allQCStatusTypes()
	 */
	@Test
	public void allQCStatusTypes(){
		
		log.info("API Testing allQCStatusTypes at baseUrl  " + baseUrl);

		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all QCStatusTypes
		.when().get(baseUrl + allQCStatusTypes)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("Superstar", "Good", "Average","Poor"))
			.body("$", not(hasItems("Pudding a la mode", "Crepe")));
		
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.TypeController#allAssessmentTypes()
	 */
	@Test
	public void allAssessmentTypes(){
		
		log.info("API Testing allAssessmentTypes at baseUrl  " + baseUrl);

		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all assessment types
		.when().get(baseUrl + allAssessmentTypes)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("Exam", "Verbal", "Project","Other"))
			.body("$", not(hasItems("Sundae", "Mousse")));
		
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.TypeController#allTrainerRoles()
	 */
	@Test
	public void allTrainerRoles(){
		
		
		log.info("API Testing allTrainingRoles at baseUrl  " + baseUrl);

		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all trainer roles
		.when().get(baseUrl + allTrainerRoles)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("ROLE_VP", "ROLE_QC", "ROLE_TRAINER","ROLE_STAGING", "ROLE_INACTIVE"))
			.body("$", not(hasItems("Che", "Mousse")));
		
		
	}
}
