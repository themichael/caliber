package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.GradeDAO;

import io.restassured.http.ContentType;

public class EvaluationAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(EvaluationAPITest.class);
	
	@Test
	public void createGrade(Grade grade){
		GradeDAO gradeDAO = new GradeDAO();
		log.info("");
	}
	@Test
	public void updateGrade(Grade grade){
		
	}
	@Test
	public void findAll(){
		
	}
	@Test
	public void findByAssessment(Long assessmentId){
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	@Test
	public void findByTrainee(){
		
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	@Test
	public void findByBatch(){
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	@Test
	public void findByCategory(){
		
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	@Test
	public void findByWeek(){
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 * 
	 */
	@Test
	public void findByTrainer(){
		
	}
	
	@Test
	public void createNote() throws Exception {
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(1);
		
		log.info("API Testing findTrainerByEmail at baseUrl  " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findByEmail).then().assertThat()
				.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
}
