package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.GradeDAO;

import io.restassured.http.ContentType;

public class EvaluationAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(EvaluationAPITest.class);
	
	@Test
	public void createGrade(){
		GradeDAO gradeDAO = new GradeDAO();
		log.info("");
	}
	@Test
	public void updateGrade(){
		
	}
	@Test
	public void findAll(){
		
	}
	@Test
	public void findByAssessment(){
		
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
		
		String createNote = "trainer/grade/create";
		Batch batch = null;
		
		Note expected = new Note();
		expected.setContent("This is a test note");
		expected.setWeek((short) 2);
		expected.setBatch(batch);
		expected.setType(NoteType.TRAINEE);
		expected.setQcStatus(QCStatus.Poor);
		
		log.info("API Testing createNote at " + baseUrl + createNote);
		given().spec(requestSpec).header(authHeader, accessToken)
				.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()
				.post(baseUrl + createNote).then().assertThat().statusCode(201);
	}
}
