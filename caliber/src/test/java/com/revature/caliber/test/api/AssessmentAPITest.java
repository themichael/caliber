package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.services.AssessmentService;

import io.restassured.http.ContentType;

public class AssessmentAPITest extends AbstractAPITest {

	private static final Logger log = Logger.getLogger(AssessmentAPITest.class);
	private String createAssessment = "trainer/assessment/create";
	private String deleteAssessment = "trainer/assessment/delete/{assessment}";
	private String updateAssessment = "trainer/assessment/update";
	private String findAssessment = "trainer/assessment/{batchId}/{week}";
	private BatchDAO batchDAO;
	private AssessmentService assessmentService;
	private CategoryDAO catDAO;

	@Autowired
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	@Autowired
	public void setCatDAO(CategoryDAO catDAO) {
		this.catDAO = catDAO;
	}

	@Autowired
	public void setAssessmentDAO(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}

	/**
	 * Tests the find uri for a batch that does not exist and ensures that the
	 * expected amount of assessments are returned
	 */
	@Test
	public void testFind404() {
		log.info("Finding two batches that don't exist");
		given().spec(requestSpec).header(auth, accessToken).when().get(baseUrl + findAssessment, 1, 1).then()
				.assertThat().statusCode(404);
		given().spec(requestSpec).header(auth, accessToken).when().get(baseUrl + findAssessment, 2200, 10).then()
				.assertThat().statusCode(404);
	}

	/**
	 * Tests the find uri for a batch that does exist and ensures that the
	 * expected amount of assessments are returned
	 */
	@Test
	public void testFind200() {
		log.info("Finding two batches that does exist");
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findAssessment, 2200, 1).then().assertThat().statusCode(200).and().body("size()", is(3));
	}

	/**
	 * Tests creation. Dependent on the transient testing state fix
	 */
	@Test
	public void testCreate201() {
		log.info("Creating a new Assessment type");
		Assessment assessment = new Assessment("This title doesn't matter", batchDAO.findOne(2200), 100,
				AssessmentType.Verbal, 100, catDAO.findOne(1));
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(assessment).when()
				.post(baseUrl + createAssessment).then().assertThat().statusCode(201);
	}

	/**
	 * Tests updating an existing assessment. Dependent on the transient testing state fix
	 */
	@Test
	public void testUpdate() {
		log.info("Updating an assessment");
		Batch batch = batchDAO.findOne(2200);
		Category category = catDAO.findOne(1);
		Assessment assessment = new Assessment(null, batch, 100, AssessmentType.Exam, 1, category);
		assessment.setAssessmentId(2057);
		assessment.setRawScore(100000);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(assessment).when()
				.put(baseUrl + updateAssessment).then().assertThat().statusCode(200);
	}

	/**
	 * Tests delete. Dependent on the transient testing state fix
	 */
	@Test
	public void testDelete() {
		log.info("Deleting an assessment");
		given().spec(requestSpec).header(auth, accessToken).when().delete(baseUrl + deleteAssessment, 2050).then()
				.assertThat().statusCode(204);
	}
}
