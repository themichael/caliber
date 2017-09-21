package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
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

	private static final int testTraineeId = 5537;
	private static final int testWeek = 1;
    
	private static final String findByTrainee = "all/grade/trainee/5529";
	private static final String findByBatch = "all/grade/batch/2150";
	private static final String findByCategory = "all/grade/category/12";
	private static final String findByWeek = "all/grade/batch/2150/week/7";
	private static final String findByTrainer = "all/grade/trainer/1";
	private static final String findTraineeNote = "trainer/note/trainee/";
	private static final String findQCTraineeNote = "qc/note/trainee/";


	//fetch not needed?
	//private String createGrade = "training/trainer/byemail/patrick.walsh@revature.com/";
	//fetch not needed?
	//private String updateGrade = "training/trainer/byemail/patrick.walsh@revature.com/";

	@Ignore
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
	@Ignore
	@Test
	public void findAll(){
		
	}
	@Ignore
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
	
	
	/**
	 * Uses RESTAssured to test the findTraineeNote method in Evaluation Controller
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findTraineeNote(Integer traineeId, Integer week)
	 */
	@Test
	public void findTraineeNote(){
		given().spec(requestSpec).header("Authorization", accessToken) // authorization
			.contentType(ContentType.JSON) // data expected
					// /trainer/note/trainee/{traineeId}/for/{week}
			.when().get(baseUrl + findTraineeNote + testTraineeId + "/for/" + testWeek)
			.then().assertThat()
				.statusCode(200) // should return status of OK
				// make sure note recieved is the one expected
				.body("noteId", equalTo(6252))
				.body("content", equalTo("Technically good. A little quiet and less aware of appearance. A little disheveled during interview."))
				.body("type", equalTo("TRAINEE"));
	}
	
	/**
	 * Uses RESTAssured to test findQCTraineeNote in the Evaluation Controller
	 * @see com.revature.caliber.controllers.EvaluationController#findQCTraineeNote(Integer traineeId, Integer week)
	 */
	@Test
	public void findQCTraineeNote(){
		given().spec(requestSpec).header("Authorization", accessToken) // authorization
		.contentType(ContentType.JSON) // data expected
				// /qc/note/trainee/{traineeId}/for/{week}
		.when().get(baseUrl + findQCTraineeNote + testTraineeId + "/for/" + testWeek)
		.then().assertThat()
			.statusCode(200) // should return status of OK
			// make sure note recieved is the one expected
			.body("noteId", equalTo(6359))
			.body("content", equalTo("Weak"))
			.body("qcStatus", equalTo("Poor"))
			.body("type", equalTo("QC_TRAINEE"));
	}

	
}
