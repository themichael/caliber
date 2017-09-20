package com.revature.caliber.test.api;

import javax.annotation.MatchesPattern;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.data.GradeDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;

public class EvaluationAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(EvaluationAPITest.class);
	
	private String findQCBatchNotes = "/qc/note/batch/2201/5";
	
	@Test
	@Ignore
	public void createGrade(Grade grade){
		GradeDAO gradeDAO = new GradeDAO();
		log.info("");
	}
	@Test
	@Ignore
	public void updateGrade(Grade grade){
		
	}
	@Test
	@Ignore
	public void findAll(){
		
	}
	@Test
	@Ignore
	public void findByAssessment(Long assessmentId){
		
	}
	
	//Alvin's 
	
	@Test
	public void findQCBatchNotes() throws JsonProcessingException {
		
		String expected = "Covered: Unix, AWS, DevOps, Hibernate Cucumber and Selenium were covered but are not in curriculum. "
				+ "Morale good. Knowledge of Unix is limited to basic commands. Did not know what a build server was. Weak in hbm.xml.";
		log.info("API Testing findQCBatchNotes at baseUrl " + baseUrl );
		given().header("Authorizatoin", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + findQCBatchNotes).then().assertThat().statusCode(200)
		.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected))); 
		
		 
		
	}
	
	@Test
	@Ignore
	public void getAllQCTraineeNotes() {
		
	}
	
	@Test
	@Ignore
	public void getAllQCTraineeOverallNotes(){
		
	}
	
	@Test
	@Ignore
	public void findAllBatchNotes() {
		
	}
	
	@Test
	@Ignore
	public void findAllIndividualNotes() {
		
	}
	
	@Test 
	@Ignore
	public void findAllTraineeNotes() {
		
	}
}
