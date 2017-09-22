package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Date;

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
	
	private static final String findQCBatchNotes = "qc/note/batch/2201/5";
	private static final String getAllQCTraineeNotes ="qc/note/trainee/2201/5";
	private static final String findAllBatchNotes = "vp/note/batch/2100/2";
	private static final String getAllQCTraineeOverallNotes = "qc/note/trainee/5529";
	private static final String findAllTraineeNotes = "all/notes/trainee/5529";
	private static final String findAllIndividualNotes = "vp/note/trainee/5529/2";
	
	@Test
	@Ignore
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
	@Ignore
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
	public void findAll() {
		
		String findAll = "all/grades/assessment/3075";
		
		//mock
		log.info("API Testing findAll at baseUrl  " + baseUrl);
		//List<Grade> grades =
		given().spec(requestSpec).header("Authorization",accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+findAll).then().assertThat().statusCode(200)
		.body("get(0).gradeId", equalTo(2908));
	}
	/*Author DanJ
	 * From evaluationController "returns grades for all trainees that took a particular assignment.
	 * Great for finding average/median/highest/lowest grades for a test
	 * ducks exception for writeValueAsString
	 * Stub
	 */
	@Test
	@Ignore
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
	@Ignore
	public void findByTrainee(){
		
		
		
		given().spec(requestSpec).header(authHeader, accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByTrainee)
		.then().assertThat()
		.statusCode(200)
		.body("list.size()", equalTo(18));
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	@Test
	@Ignore
	public void findByBatch(){
		
		given().spec(requestSpec).header(authHeader, accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByBatch)
		.then().assertThat()
		.statusCode(200)
		.body("list.size()", equalTo(260));
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	@Test
	@Ignore
	public void findByCategory(){
		
		given().spec(requestSpec).header(authHeader, accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByCategory)
		.then().assertThat()
		.statusCode(200)
		.body("list.size()", equalTo(164));
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	@Test
	@Ignore
	public void findByWeek(){
		given().spec(requestSpec).header(authHeader, accessToken)
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
	@Ignore
	public void findByTrainer(){
		given().spec(requestSpec).header(authHeader, accessToken)
		.contentType(ContentType.JSON)
		.when().get(baseUrl + findByTrainer)
		.then().assertThat()
		.statusCode(200)
		.body("list.size()", equalTo(1171));
	}
	
	@Test
	@Ignore
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
	 * @see com.revature.caliber.controllers.EvaluationController#findQCBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	
	@Test
	public void findQCBatchNotes()  {
		
		log.info("API Testing findQCBatchNotes at baseUrl " + baseUrl );
		
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
		
		//get request for QC BatchNotes
		.get(baseUrl + findQCBatchNotes).then().assertThat().statusCode(200)
		
		//assertions
		.body("week", equalTo(5))
		.body("noteId", equalTo(6438))
		.body("type", equalTo("QC_BATCH"))
		.body("batch.batchId", equalTo(2201));
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	
	@Test
	public void getAllQCTraineeNotes() {
		
		log.info("API Testing getAllQCTraineeNotes at baseUrl " + baseUrl);
		
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
		
		//request for get All QC Trainee Notes
		.get(baseUrl + getAllQCTraineeNotes ).then().assertThat().statusCode(200)
		//assertions  to get all Trainee Notes
		.body("get(0).noteId", equalTo(6423))
		.body("get(0).trainee.traineeId", equalTo(5524))
		.body("get(1).trainee.traineeId", equalTo(5525))
		.body("get(2).trainee.traineeId", equalTo(5525))
		.body("size()", equalTo(17)); 

	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeOverallNotes(@PathVariable Integer traineeId)
	 */
	
	@Test
	public void getAllQCTraineeOverallNotes(){
		
		log.trace("API Testing getAllQCTraineeOverallNotes at baseUrl" + baseUrl ); 
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
		
		//request for get All QC TraineeOverall Notes 
		.get(baseUrl + getAllQCTraineeOverallNotes ).then().assertThat().statusCode(200)
		
		//assertions
		.body("get(0).content", equalTo("Average"))
		.body("get(1).content", equalTo("technically weak on SQL."))
		.body("get(5).content", equalTo("Asking good questions."))
		.body("get(0).type", equalTo("QC_TRAINEE"));
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	
	@Test
	public void findAllBatchNotes() {
		
		log.info("API Testing findAllBatchNotes at baseUrl: " + baseUrl);
		
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
		
		//request for get find All Batch Notes
		.get(baseUrl + findAllBatchNotes).then().assertThat().statusCode(200)
		
		//assertions
		
		.body("get(0).type", equalTo("BATCH"))
		.body("get(0).batch.batchId", equalTo(2100)); 
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllTraineeNotes(@PathVariable Integer traineeId)
	 */
	
	@Test
	public void findAllTraineeNotes() {
		
		log.trace("API Testing findAllTraineeNotes at baseUrl " + baseUrl ); 
		
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
		//request to find all individual notes 
		
		.get(baseUrl + findAllTraineeNotes).then().assertThat().statusCode(200)
		
		//assertions
		.body("get(0).trainee.traineeId", equalTo(5529))
		.body("get(0).trainee.name", equalTo("Montesdeoca, Denise"))	
		.body("get(0).maxVisibility", equalTo("ROLE_TRAINER"))
		.body("get(0).type", equalTo("TRAINEE"));
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllBatchNotes(@PathVariable Integer traineeId, @PathVariable Integer week)
	 */
	
	@Test 
	public void findAllIndividualNotes() {
		
		log.trace("API Testing findAllIndividualNotes at baseUrl" + baseUrl); 
		
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON).when()
		
		//request to find all Individual Notes
		.get(baseUrl + findAllIndividualNotes).then().assertThat().statusCode(200)
		
		//assertions
		.body("get(0).trainee.traineeId",equalTo(5529))
		.body("get(0).week",equalTo(2))
		.body("get(0).trainee.name", equalTo("Montesdeoca, Denise"))
		.body("get(0).type",equalTo("TRAINEE")); 
		
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
