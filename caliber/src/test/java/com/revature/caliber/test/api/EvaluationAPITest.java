package com.revature.caliber.test.api;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	private static final String findQCBatchNotes = "qc/note/batch/2201/5";
	private static final String getAllQCTraineeNotes ="qc/note/trainee/2201/5";
	private static final String findAllBatchNotes = "vp/note/batch/2100/2";
	private static final String getAllQCTraineeOverallNotes = "qc/note/trainee/5529";
	private static final String findAllTraineeNotes = "all/notes/trainee/5529";
	private static final String findAllIndividualNotes = "vp/note/trainee/5529/2";
	
	@Test
	@Ignore
	public void createGrade(){
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
	@Ignore
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
	@Ignore
	public void findAll(){
		
	}
	@Test
	@Ignore
	public void findByAssessment(){
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	@Test
	@Ignore
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
	@Ignore
	public void findByBatch(){
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	@Test
	@Ignore
	public void findByCategory(){
		
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	@Test
	@Ignore
	public void findByWeek(){
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 * 
	 */
	@Test
	@Ignore
	public void findByTrainer(){
		
	}
	
	
	@Test
	@Ignore
	public void findQCBatchNotes()  {
		
		log.info("API Testing findQCBatchNotes at baseUrl " + baseUrl );
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		
		//get request for QC BatchNotes
		.get(baseUrl + findQCBatchNotes).then().assertThat().statusCode(200)
		
		//assertions
		.body("week", equalTo(5))
		.body("noteId", equalTo(6438))
		.body("type", equalTo("QC_BATCH"))
		.body("batch.batchId", equalTo(2201));
		
	}
	
	@Test
	@Ignore
	public void getAllQCTraineeNotes() {
		
		log.info("API Testing getAllQCTraineeNotes at baseUrl " + baseUrl);
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		
		//request for get All QC Trainee Notes
		.get(baseUrl + getAllQCTraineeNotes ).then().assertThat().statusCode(200)
		//assertions  to get all Trainee Notes
		.body("get(0).noteId", equalTo(6423))
		.body("get(0).trainee.traineeId", equalTo(5524))
		.body("get(1).trainee.traineeId", equalTo(5525))
		.body("get(2).trainee.traineeId", equalTo(5525))
		.body("get(3).trainee.traineeId", equalTo(5526))
		.body("get(4).trainee.traineeId", equalTo(5527))
		.body("get(5).trainee.traineeId", equalTo(5528))
		.body("get(6).trainee.traineeId", equalTo(5529))
		.body("get(7).trainee.traineeId", equalTo(5530))
		.body("get(8).trainee.traineeId", equalTo(5531))
		.body("get(9).trainee.traineeId", equalTo(5532))
		.body("get(10).trainee.traineeId", equalTo(5533))
		.body("get(11).trainee.traineeId", equalTo(5534))
		.body("get(12).trainee.traineeId", equalTo(5535))
		.body("get(13).trainee.traineeId", equalTo(5536))
		.body("get(14).trainee.traineeId", equalTo(5537))
		.body("get(15).trainee.traineeId", equalTo(5538))
		.body("get(16).trainee.traineeId", equalTo(5539));
		
		
	}
	
	@Test
	@Ignore
	public void getAllQCTraineeOverallNotes(){
		
		log.trace("API Testing getAllQCTraineeOverallNotes at baseUrl" + baseUrl ); 
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		
		//request for get All QC TraineeOverall Notes 
		.get(baseUrl + getAllQCTraineeOverallNotes ).then().assertThat().statusCode(200)
		
		//assertions
		.body("get(0).content", equalTo("Average"))
		.body("get(1).content", equalTo("technically weak on SQL."))
		.body("get(5).content", equalTo("Asking good questions."))
		.body("get(0).type", equalTo("QC_TRAINEE"));
		
	}
	
	@Test
	@Ignore
	public void findAllBatchNotes() {
		
		log.info("API Testing findAllBatchNotes at baseUrl: " + baseUrl);
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		
		//request for get find All Batch Notes
		.get(baseUrl + findAllBatchNotes).then().assertThat().statusCode(200)
		
		//assertions
		
		.body("get(0).type", equalTo("BATCH"))
		.body("get(0).batch.batchId", equalTo(2100)); 
		
	}
	
	@Test
	@Ignore
	public void findAllTraineeNotes() {
		
		log.trace("API Testing findAllTraineeNotes at baseUrl " + baseUrl ); 
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		//request to find all individual notes 
		
		.get(baseUrl + findAllTraineeNotes).then().assertThat().statusCode(200)
		
		//assertions
		.body("get(0).trainee.traineeId", equalTo(5529))
		.body("get(0).trainee.name", equalTo("Montesdeoca, Denise"))	
		.body("get(0).maxVisibility", equalTo("ROLE_TRAINER"))
		.body("get(0).type", equalTo("TRAINEE"));
		
		
		
	}
	
	@Test 
	public void findAllIndividualNotes() {
		
		log.trace("API Testing findAllIndividualNotes at baseUrl" + baseUrl); 
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON).when()
		
		.get(baseUrl + findAllIndividualNotes).then().assertThat().statusCode(200);
		
		System.out.println(baseUrl + findAllIndividualNotes);
		
	}
}
