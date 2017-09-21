package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.junit.Ignore;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
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
	@Autowired
	NoteDAO noteDAO;
	private static final Logger log = Logger.getLogger(EvaluationAPITest.class);

	
    
	private static final String findByTrainee = "all/grade/trainee/5529";
	private static final String findByBatch = "all/grade/batch/2150";
	private static final String findByCategory = "all/grade/category/12";
	private static final String findByWeek = "all/grade/batch/2150/week/7";
	private static final String findByTrainer = "all/grade/trainer/1";

	//fetch not needed?
	//private String createGrade = "training/trainer/byemail/patrick.walsh@revature.com/";
	//fetch not needed?
	//private String updateGrade = "training/trainer/byemail/patrick.walsh@revature.com/";

	@Test
	public void createGrade(){

		GradeDAO gradeDAO = new GradeDAO();
		log.info("");
		log.info("API Testing createGrade at baseUrl  " + baseUrl);
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
		
		Grade grade = new Grade(assessment, trainee, date, 99.99);
		gradeDAO.save(grade);
		
		
		/*given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + createGrade).then().assertThat()
		.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
		*/

	}
	
	@Test
	public void updateGrade(){

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
		Grade grade = new Grade(assessment, trainee, date, 99.99);
		
		gradeDAO.save(grade);
		grade.setScore(100);
		gradeDAO.update(grade);
		List<Grade> grades= gradeDAO.findByTrainee(200);
		if(grades.size()>0){
			log.info("star: " + gradeDAO.findByTrainee(200).get(0));
		}
		//assertEquals(100, gradeDAO.findByTrainee(200).get(0));
		
		/*log.info("API Testing updateGrade at baseUrl  " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
		.get(baseUrl + updateGrade).then().assertThat()
		.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
		*/
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
	
	/**
	 * Create note 
	 * @see com.revature.caliber.controllers.EvaluationController#createNote(@Valid @RequestBody Note note)
	 * 
	 */
	@Ignore
	@Test
	public void createNote() throws Exception {
		
		String createNote = "note/create";
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
	 * Test by finding note, setting note content, and then posting new note
	 * @see com.revature.caliber.controllers.EvaluationController#updateNote(@Valid @RequestBody Note note)
	 * 
	 */
	
	@Ignore
	@Test
	public void updateNote() throws Exception{
		
		String updateNote = "note/update";
		List<Note> notes = noteDAO.findQCIndividualNotes(5529, 2);
		notes.get(0).setContent("This is a test note");
		
		
		log.info("API Testing updateNote at " + baseUrl + updateNote);
		given().spec(requestSpec).header(authHeader, accessToken)
				.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(notes.get(0))).when()
				.post(baseUrl + updateNote).then().assertThat().statusCode(201);
		
		
		
		
		
	}
	
	/**
	 * Find batch notes for trainee using batchId = 2100 and week 2
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
				.get(baseUrl + findIndividualNotes).then().assertThat().statusCode(200)
				.body("size()", is(16))
				.body("get(0).noteId", equalTo(6321));
		
		findIndividualNotes = "trainer/note/trainee/2200/3";
		log.info("API Testing findIndividualNotes at " + baseUrl + findIndividualNotes);
		given().spec(requestSpec).header(authHeader, accessToken)
		.contentType(ContentType.JSON).when()
				.get(baseUrl + findIndividualNotes).then().assertThat().statusCode(200)
				.body("size()", is(15))
				.body("get(0).noteId", equalTo(6128));
	}
	
	
	

	
}
