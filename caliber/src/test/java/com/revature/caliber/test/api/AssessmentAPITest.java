package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
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
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findAssessment, 2200, 1).then().assertThat().statusCode(200).and().body("size()", is(3));
	}

	/**
	 * Tests creation. Dependent on the transient testing state fix
	 */
	@Test
	public void testCreate201() {
		Assessment assessment = new Assessment("This title doesn't matter", batchDAO.findOne(2200), 100,
				AssessmentType.Verbal, 100, catDAO.findOne(1));
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(assessment).when()
				.post(baseUrl + createAssessment).then().assertThat().statusCode(201);
	}

	/**
	 * Tests update. Dependent on the transient testing state fix
	 */
	@Test
	public void testUpdate() {
		log.info(assessmentService.findAssessmentByWeek(2050, 100));
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).body(assessmentService).when()
				.put(baseUrl + updateAssessment).then().assertThat().statusCode(201);
	}

	/**
	 * Tests delete. Dependent on the transient testing state fix
	 */
	@Test
	public void testDelete() {
		given().spec(requestSpec).header(auth, accessToken).when().delete(baseUrl + deleteAssessment, 156000).then()
				.assertThat().statusCode(204);
	}
}
