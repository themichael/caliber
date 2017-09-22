package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

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
	
	private static final String createGrade = "trainer/grade/create";
	private static final String updateGrade = "trainer/grade/update";
	private static final String findAll="vp/grade/all";
	private static final String findByAssessment = "all/grades/assessment/3075";
	
	/**
	 * testing createGrade - create a new grade and check for HTTP 200 success
	 * @Author DanielJordan
	 */
	@Test
	public void createGrade() throws Exception{

		log.info("API Testing createGrade at baseUrl  " + baseUrl);
		Trainer trainer = new Trainer("Joseph, Alex", "Trainer", "testemail@mail.com", TrainerRole.ROLE_VP);
		trainerDAO.save(trainer);
		Date start = new Date();
		Date end = new Date(System.currentTimeMillis()+10000);
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
		
		given().header("Authorization", accessToken).spec(requestSpec)
		.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected)).when()
		.post(baseUrl + createGrade).then().assertThat().statusCode(201);
	}
	/**
	 * testing updateGrade - create a new grade and check for HTTP 204 no_content
	 * @Author DanielJordan
	 */
	@Test
	public void updateGrade() throws Exception{
		Trainer trainer = new Trainer("Joseph, Alex", "Trainer", "testemail@mail.com", TrainerRole.ROLE_VP);
		trainerDAO.save(trainer);
		Date start = new Date();
		Date end = new Date(System.currentTimeMillis()+10000);
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
		
		log.info("API Testing updateGrade at baseUrl  " + baseUrl);
		
		given().header("Authorization",accessToken).spec(requestSpec)
		.contentType(ContentType.JSON).body(new ObjectMapper()
		.writeValueAsString(expected)).when().post(baseUrl+updateGrade).then()
		.assertThat().statusCode(204);
	}
	/**
	 * testing findAll - assert that some expected values exist, and that the expected 
	 * number of values is returned
	 * @Author DanielJordan
	 */
	@Test
	public void findAll() throws Exception{
		log.info("API Testing findAll at baseUrl  " + baseUrl);
		given().spec(requestSpec).header("Authorization",accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+findAll).then().assertThat().statusCode(200)
		.body("get(0).gradeId",equalTo(1077))
		.body("get(1).gradeId",equalTo(1078))
		.body("get(2).gradeId",equalTo(1079))
		.body("size()",is(1329));
	}
	/**
	 * testing findByAssessment - assert that some expected values exist and
	 * that the expected number of values is returned
	 * @Author DanielJordan
	 */
	@Test
	public void findByAssessment() throws Exception{
		log.info("API Testing findByAssessment at baseUrl  " + baseUrl);
		//List<Grade> grades =
		//given statement needs to be modified for a findByAssessment
		//String expected = "";
		given().spec(requestSpec).header("Authorization",accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+findByAssessment).then().assertThat().statusCode(200)
		.body("get(0).gradeId",equalTo(2908))
		.body("get(1).gradeId",equalTo(2912))
		.body("get(2).gradeId",equalTo(2926))
		.body("size()",equalTo(16));
		
		given().spec(requestSpec).header("Authorization",accessToken).contentType(ContentType.JSON)
				.when().get(baseUrl+findByAssessment).then().assertThat().statusCode(200)
				.body("get(0).gradeId",equalTo(2908))
				.body("get(1).gradeId",equalTo(2912))
				.body("get(2).gradeId",equalTo(2926))
				.body("size()",equalTo(16))
				;
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
		
		Note expected = new Note();
		expected.setContent("This is a test note");
		expected.setWeek((short) 2);
		expected.setBatch(null);
		expected.setType(NoteType.TRAINEE);
		expected.setQcStatus(QCStatus.Poor);
		expected.setMaxVisibility(TrainerRole.ROLE_TRAINER);
		expected.setTrainee(null);
		
		log.info("API Testing createNote at " + baseUrl + createNote);
		given().spec(requestSpec).header(auth, accessToken)
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
		given().spec(requestSpec).header(auth, accessToken)
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
		given().spec(requestSpec).header(auth, accessToken)
		.contentType(ContentType.JSON).when()
				.get(baseUrl + findIndividualNotes).then().assertThat().statusCode(200);
	}
	
	
	

	
}
