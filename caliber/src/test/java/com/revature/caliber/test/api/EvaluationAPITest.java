package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Category;

import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;



import io.restassured.http.ContentType;


public class EvaluationAPITest extends AbstractAPITest{

	@Autowired
	GradeDAO gradeDAO;
	@Autowired
	TraineeDAO traineeDAO;
	@Autowired
	BatchDAO batchDAO;
	@Autowired
	TrainerDAO trainerDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	AssessmentDAO assessmentDAO;
	private static final Logger log = Logger.getLogger(EvaluationAPITest.class);

	
    
	private static final String findByTrainee = "all/grade/trainee/5529";
	private static final String findByBatch = "all/grade/batch/2150";
	private static final String findByCategory = "all/grade/category/12";
	private static final String findByWeek = "all/grade/batch/2150/week/7";
	private static final String findByTrainer = "all/grade/trainer/1";
	
	private String createGrade = "trainer/grade/create";
	private String updateGrade = "trainer/grade/update";
	private String findAll="vp/grade/all";
	//unknownAssessmentID from findByAssessment
	private String findByAssessment = "/all/grades/assessment/{assessmentId}";
	
	/*Author DanJ
	 * ducked an exception for writeValueAsString
	 */
	@Test
	public void createGrade() throws Exception{
		GradeDAO gradeDAO = new GradeDAO();
		log.info("API Testing createGrade at baseUrl  " + baseUrl);
		Trainer trainer = new Trainer("Joseph, Alex", "Trainer", "testemail@mail.com", TrainerRole.ROLE_VP);
		trainerDAO.save(trainer);
		Date start = new Date();
		//possibly modify 2nd date - depreciated type?
		Date end = new Date();
		Batch batch = new Batch("45567 Microservices", trainer, start, end, "Revature LLC, Reston VA 20190");
		batchDAO.save(batch);
		Category category = new Category("Java", true);
		categoryDAO.save(category);
		Assessment assessment = new Assessment("", batch, 200, AssessmentType.Exam, 5, category);
		assessmentDAO.save(assessment);
		Trainee trainee = new Trainee("Joseph, Jones", "", "testemail@mail.com", batch);
		traineeDAO.save(trainee);
		Date date = new Date();
		//setting the expected value as a grade 
		Grade expected = new Grade(assessment, trainee, date, 99.99);
		
		//we are posting the grade, not saving it
		//gradeDAO.save(grade);
		
		//we are setting the expected here, and then comparing it
		//not sure about spec(requestspec)
		//not sure about calling contentType(ContentType.JSON).body jsontype before/after
		
		given().header("Authorization", accessToken).spec(requestSpec)
		.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()
		.post(baseUrl + createGrade).then().assertThat().statusCode(201);
	}
	/*
	 * Author DanJ
	 */
	@Test
	public void updateGrade() throws Exception{
		Trainer trainer = new Trainer("Joseph, Alex", "Trainer", "testemail@mail.com", TrainerRole.ROLE_VP);
		trainerDAO.save(trainer);
		Date start = new Date();
		Date end = new Date();
		Batch batch = new Batch("45567 Microservices", trainer, start, end, "Revature LLC, Reston VA 20190");
		batchDAO.save(batch);
		Category category = new Category("Java", true);
		categoryDAO.save(category);
		Assessment assessment = new Assessment("", batch, 200, AssessmentType.Exam, 5, category);
		assessmentDAO.save(assessment);
		Trainee trainee = new Trainee("Joseph, Jones", "", "testemail@mail.com", batch);
		traineeDAO.save(trainee);
		Date date = new Date();
		//set expected value as a grade
		Grade expected = new Grade(assessment, trainee, date, 99.99);
		
		//gradeDAO.save(grade);
		//grade.setScore(100);
		//gradeDAO.update(grade);
		/*List<Grade> grades= gradeDAO.findByTrainee(200);
		if(grades.size()>0){
			log.info("star: " + gradeDAO.findByTrainee(200).get(0));
		}
		*/
		//assertEquals(100, gradeDAO.findByTrainee(200).get(0));
		
		log.info("API Testing updateGrade at baseUrl  " + baseUrl);
		
		given().header("Authorization",accessToken).spec(requestSpec)
		.contentType(ContentType.JSON).body(new ObjectMapper()
		.writeValueAsString(expected)).when().post(baseUrl+updateGrade).then()
		.assertThat().statusCode(201);
	}
	/*
	 * Author DanJ
	 * "all grades"
	 */
	@Test
	public void findAll() throws Exception{
		
		/*
		 * exists
		 * count
		 * sample of valid data
		 * ??? something else
		 */
		
		//mock
		
		log.info("API Testing findAll at baseUrl  " + baseUrl);
		//List<Grade> grades =
		String expected = ""; 
		given().header("Authorization",accessToken).contentType(ContentType.JSON)
		.when().get("baseUrl"+findAll).then().assertThat().statusCode(200)
		.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	/*Author DanJ
	 * From evaluationController "returns grades for all trainees that took a particular assignment.
	 * Great for finding average/median/highest/lowest grades for a test
	 * ducks exception for writeValueAsString
	 * Stub
	 */
	@Test
	public void findByAssessment() throws Exception{
		log.info("API Testing findByAssessment at baseUrl  " + baseUrl);
		//List<Grade> grades =
		//given statement needs to be modified for a findByAssessment
		String expected = "";
		given().header("Authorization",accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+findByAssessment).then().assertThat().statusCode(200)
		.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	@Test
	public void findByTrainee(){
		
		
		
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByTrainee)
		.then().assertThat()
		.statusCode(200);
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	@Test
	public void findByBatch(){
		
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByBatch)
		.then().assertThat()
		.statusCode(200);
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	@Test
	public void findByCategory(){
		
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByCategory)
		.then().assertThat()
		.statusCode(200);
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	@Test
	public void findByWeek(){
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByWeek)
		.then().assertThat()
		.statusCode(200);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 * 
	 */
	@Test
	public void findByTrainer(){
		given().spec(requestSpec).header("Authorization", accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByTrainer)
		.then().assertThat()
		.statusCode(200);
	}
	
	@Test
	public void createNote() throws Exception {
		
		String createNote = "note/create";
		Batch batch = batchDAO.findOne(2200);
		
		
		Note expected = new Note();
		expected.setContent("This is a test note");
		expected.setWeek((short) 2);
		expected.setBatch(null);
		expected.setType(NoteType.TRAINEE);
		expected.setQcStatus(QCStatus.Poor);
		expected.setMaxVisibility(TrainerRole.ROLE_TRAINER);
		expected.setTrainee(null);
		
		log.info("API Testing createNote at " + baseUrl + createNote);
		given().spec(requestSpec).header(authHeader, accessToken)
				.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()
				.post(baseUrl + createNote).then().assertThat().statusCode(201);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	@Test
	public void findBatchNotes() {
		
		String findBatchNotes = "trainer/note/batch/2100/2";
		log.info("API Testing findBatchNotes at " + baseUrl + findBatchNotes);
		given().spec(requestSpec).header(authHeader, accessToken)
		.contentType(ContentType.JSON).when()
				.get(baseUrl + findBatchNotes).then().assertThat().statusCode(200)
				.body("get(0).noteId", equalTo(5133));
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findIndividualNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	@Test
	public void findIndividualNotes(){
		String findIndividualNotes = "trainer/note/trainee/2201/6";
		log.info("API Testing findIndividualNotes at " + baseUrl + findIndividualNotes);
		given().spec(requestSpec).header(authHeader, accessToken)
		.contentType(ContentType.JSON).when()
				.get(baseUrl + findIndividualNotes).then().assertThat().statusCode(200);
	}
	
	
	

	
}
