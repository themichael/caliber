package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
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

	private static final int testTraineeId = 5537;
	private static final int testWeek = 1;
	
	private static final String findByTrainee = "all/grade/trainee/5529";
	private static final String findByBatch = "all/grade/batch/2150";
	private static final String findByCategory = "all/grade/category/12";
	private static final String findByWeek = "all/grade/batch/2150/week/7";
	private static final String findByTrainer = "all/grade/trainer/1";
	private static final String findTraineeNote = "trainer/note/trainee/";
	private static final String findQCTraineeNote = "qc/note/trainee/";
	private static final String createGrade = "trainer/grade/create";
	private static final String updateGrade = "trainer/grade/update";
	private static final String findAll="vp/grade/all";
	private static final String findByAssessment = "all/grades/assessment/3075";
	private static final String findQCBatchNotes = "qc/note/batch/2201/5";
	private static final String getAllQCTraineeNotes ="qc/note/trainee/2201/5";
	private static final String findAllBatchNotes = "vp/note/batch/2100/2";
	private static final String getAllQCTraineeOverallNotes = "qc/note/trainee/5529";
	private static final String findAllTraineeNotes = "all/notes/trainee/5529";
	private static final String findAllIndividualNotes = "vp/note/trainee/5529/2";
	private static final String findBatchNotes = "trainer/note/batch/2100/2";
	
	/**
	 * Testing createGrade - create a new grade and check for HTTP 200 success
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#createGrade(Grade)
	 */
	@Test
	@Ignore
	public void createGrade() throws JsonProcessingException{
		log.trace("API Testing createGrade");
		
		// make an assessment to store the grade in
		Category category = categoryDAO.findAllCategories().get(0);
		Trainee trainee = traineeDAO.findOne(testTraineeId);
		Assessment assessment = new Assessment("Testing Test", trainee.getBatch(), 200, AssessmentType.Exam, testWeek, category);
		assessmentDAO.save(assessment);
		
		//setting the expected value as a grade 
		Grade expected = new Grade(assessment, trainee, new Date(), 99.99);
		
		given().header("Authorization", accessToken).spec(requestSpec)
				.contentType(ContentType.JSON).body(new ObjectMapper()
				.writeValueAsString(expected))
			.when().post(baseUrl + createGrade)
			.then().assertThat().statusCode(201);

		assertTrue(gradeDAO.findByAssessment(assessment.getAssessmentId()).size() > 0);
	}
	
	/**
	 * testing updateGrade - create a new grade and check for HTTP 204 no_content
	 * @see com.revature.caliber.controllers.EvaluationController#updateGrade(Grade)
	 */
	@Test
	@Ignore
	public void updateGrade() throws Exception{
		log.trace("API Testing updateGrade");
		
		//	get expected value as a grade
		Grade expected = gradeDAO.findByTrainee(testTraineeId).get(0);
		
		// change grade
		expected.setScore(55.55);
				
		given().header("Authorization",accessToken).spec(requestSpec)
				.contentType(ContentType.JSON).body(new ObjectMapper()
				.writeValueAsString(expected))
			.when().post(baseUrl+updateGrade)
			.then().assertThat().statusCode(204);
		
		assertEquals(expected, gradeDAO.findByTrainee(testTraineeId).get(0));
	}
	
	/**
	 * testing findAll - assert that some number of values is returned
	 * @see com.revature.caliber.controllers.EvaluationController#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findAll() throws Exception{
		log.trace("API Testing findAll");
		
		// make list to store grades in
		List<Grade> grades = new ArrayList<Grade>();
		grades = given().spec(requestSpec).header("Authorization",accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl+findAll) // find all grades
			.then().assertThat().statusCode(200)
				.extract().body().as(grades.getClass()); // extract body of response as a List<Grade>
		
		// check that some grades were returned
		assertTrue(grades.size() > 0);
	}
	
	/**
	 * testing findByAssessment - assert that some expected values exist and
	 * that the expected number of values is returned
	 * @see com.revature.caliber.controllers.EvaluationController#findByAssessment(Long)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findByAssessment() throws Exception{
		log.trace("API Testing findByAssessment");

		List<Grade> grades = new ArrayList<Grade>();
		grades = given().spec(requestSpec).header("Authorization",accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl+findByAssessment) // find grade by assessment
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(grades.getClass()); // extract body of response as a List<Grade>

		// check that some grades were returned
		assertTrue(grades.size() > 0);
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findByTrainee(){
		log.trace("API test findByTrainee");
		
		List<Grade> grades = new ArrayList<Grade>();
		grades = given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when().get(baseUrl + findByTrainee) // find grade by Trainee
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(grades.getClass()); // extract body of response as a List<Grade>

		// check that some grades were returned
		assertTrue(grades.size() > 0);
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findByBatch(){
		log.trace("API test findByBatch");
		
		List<Grade> grades = new ArrayList<Grade>();
		grades = given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when().get(baseUrl + findByBatch) // find grade by batch
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(grades.getClass()); // extract body of response as a List<Grade>

		// check that some grades were returned
		assertTrue(grades.size() > 0);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findByCategory(){
		log.trace("API test findByCategory");
		
		List<Grade> grades = new ArrayList<Grade>();
		grades = given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when().get(baseUrl + findByCategory) // find grade by Category
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(grades.getClass()); // extract body of response as a List<Grade>

		// check that some grades were returned
		assertTrue(grades.size() > 0);
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	@Test
	public void findByWeek(){
		log.trace("API test findByWeek");
		
		given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when().get(baseUrl + findByWeek) // find grades by week
			.then().assertThat()
				.statusCode(200);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findByTrainer(){
		log.trace("API test findByTrainer");
		
		List<Grade> grades = new ArrayList<Grade>();
		grades = given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when().get(baseUrl + findByTrainer) // find grades by Trainer
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(grades.getClass()); // extract body of response as a List<Grade>
		
		// check that some grades were returned
		assertTrue(grades.size() > 0);
	}
	

	/**
	 * Create note 
	 * @see com.revature.caliber.controllers.EvaluationController#createNote(@Valid @RequestBody Note note)
	 * 
	 */
	@Ignore
	@Test
	public void createNote() throws Exception {
		log.trace("API test createNote");
		
		// create a new Note object
		String createNote = "note/create";
		Note expected = new Note();
		expected.setContent("This is a test note");
		expected.setWeek((short) 2);
		expected.setBatch(null);
		expected.setType(NoteType.TRAINEE);
		expected.setQcStatus(QCStatus.Poor);
		expected.setMaxVisibility(TrainerRole.ROLE_TRAINER);
		expected.setTrainee(null);
		
		given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected))
			.when().post(baseUrl + createNote)
			.then().assertThat().statusCode(201);
		
		// check that new note is in database
		assertTrue(noteDAO.findAllQCTraineeNotes(null, 2).contains(expected));
	}
	
	/**
	 * Test by finding note, setting note content, and then posting new note
	 * @see com.revature.caliber.controllers.EvaluationController#updateNote(@Valid @RequestBody Note note)
	 * 
	 */
	@Test
	@Ignore
	public void updateNote() throws Exception{
		log.trace("API test updateNote");
		
		// find and change value of note to update
		String updateNote = "note/update";
		Note expected = noteDAO.findQCIndividualNotes(5529, 2).get(0);
		expected.setContent("This is a test notes");
		
		given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON).body(new ObjectMapper().writeValueAsString(expected))
				.when().post(baseUrl + updateNote)
				.then().assertThat().statusCode(201);
		
		// check that note was updated in database
		assertTrue(noteDAO.findQCIndividualNotes(5529, 2).contains(expected));
	}
	
	/**
	 * Find batch notes for trainee using batchId = 2100 and week 2
	 * @see com.revature.caliber.controllers.EvaluationController#findBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findBatchNotes() {
		log.trace("API test findBatchNotes");

		List<Note> notes = new ArrayList<Note>();
		notes = given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when()
				.get(baseUrl + findBatchNotes)
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(notes.getClass()); // extract body of response as a List<Note>
		
		// check that some notes were returned
		assertTrue(notes.size() > 0);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findQCBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	@Test
	public void findQCBatchNotes()  {
		log.trace("API Testing findQCBatchNotes");
		
		Note note = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
		.when()
			//get request for QC BatchNotes
			.get(baseUrl + findQCBatchNotes)
		.then().assertThat()
			//assertions
			.statusCode(200)
			.extract().body().as(Note.class); // extract body of response as a Note
			
		// check that right note was returned
		assertEquals(6438, note.getNoteId());
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getAllQCTraineeNotes() {
		log.info("API Testing getAllQCTraineeNotes");
		
		List<Note> notes = new ArrayList<Note>();
		notes = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when()
			//request for get All QC Trainee Notes
			.get(baseUrl + getAllQCTraineeNotes )
		.then().assertThat()
			//assertions  to get all Trainee Notes
			.statusCode(200)
			.extract().body().as(notes.getClass()); // extract body of response as a List<Note>
		
			// check that some notes were returned
			assertTrue(notes.size() > 0);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeOverallNotes(@PathVariable Integer traineeId)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getAllQCTraineeOverallNotes(){
		log.trace("API Testing getAllQCTraineeOverallNotes"); 
		
		List<Note> notes = new ArrayList<Note>();
		notes = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when()
				//request for get All QC TraineeOverall Notes 
				.get(baseUrl + getAllQCTraineeOverallNotes )
			.then().assertThat()
				//assertions
				.statusCode(200)
				.extract().body().as(notes.getClass()); // extract body of response as a List<Note>
		
		// check that some notes were returned
		assertTrue(notes.size() > 0);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findAllBatchNotes() {
		log.trace("API Testing findAllBatchNotes");
		
		List<Note> notes = new ArrayList<Note>();
		notes = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when()
				//request for get find All Batch Notes
				.get(baseUrl + findAllBatchNotes)
			.then().assertThat()
				//assertions
				.statusCode(200)
				.extract().body().as(notes.getClass()); // extract body of response as a List<Note>
		
		// check that some notes were returned
		assertTrue(notes.size() > 0);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllTraineeNotes(@PathVariable Integer traineeId)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findAllTraineeNotes() {
		log.trace("API Testing findAllTraineeNotes"); 
		
		List<Note> notes = new ArrayList<Note>();
		notes = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when()
				//request to find all individual notes 
				.get(baseUrl + findAllTraineeNotes)
			.then().assertThat()
				//assertions
				.statusCode(200)
				.statusCode(200)
				.extract().body().as(notes.getClass()); // extract body of response as a List<Note>
		
		// check that some notes were returned
		assertTrue(notes.size() > 0);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllBatchNotes(@PathVariable Integer traineeId, @PathVariable Integer week)
	 */
	@SuppressWarnings("unchecked")
	@Test 
	public void findAllIndividualNotes() {
		log.trace("API Testing findAllIndividualNotes"); 
		
		List<Note> notes = new ArrayList<Note>();
		notes = given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when()
				//request to find all Individual Notes
				.get(baseUrl + findAllIndividualNotes)
			.then().assertThat()
				//assertions
				.statusCode(200)
				.statusCode(200)
				.extract().body().as(notes.getClass()); // extract body of response as a List<Note>
		
		// check that some notes were returned
		assertTrue(notes.size() > 0);
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findIndividualNotes(@PathVariable Integer batchId, @PathVariable Integer week)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findIndividualNotes(){
		log.trace("API test findIndividualNotes");
		
		String findIndividualNotes = "trainer/note/trainee/2201/6";
		List<Note> notes = new ArrayList<Note>();
		notes = given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when()
				.get(baseUrl + findIndividualNotes)
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(notes.getClass()); // extract body of response as a List<Note>
			
		// check that some notes were returned
		assertTrue(notes.size() > 0);
		
		findIndividualNotes = "trainer/note/trainee/2200/3";
		notes = given().spec(requestSpec).header(auth, accessToken)
				.contentType(ContentType.JSON)
			.when()
				.get(baseUrl + findIndividualNotes)
			.then().assertThat()
				.statusCode(200)
				.extract().body().as(notes.getClass()); // extract body of response as a Note
		
		// check that some notes were returned
		assertTrue(notes.size() > 0);
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
