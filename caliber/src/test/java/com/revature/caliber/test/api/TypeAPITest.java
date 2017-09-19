package com.revature.caliber.test.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

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
		
		List<String> expected = new ArrayList<>(Arrays.asList("J2EE", ".NET", "SDET", "BPM", "Other"));
		
		
		String expectedJSON = new ObjectMapper().writeValueAsString(expected);

		log.info("API Testing allSkippTypes at baseUrl  " + baseUrl);
		
		given().header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + allSkillTypes)
		.then().assertThat().statusCode(200)
		.body(matchesJsonSchema(expectedJSON));
	
	}

}
