package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.InterviewFormat;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.TraineeDAO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Nathan Koszuta
 * @author Connor Monson
 */

public class PanelAPITest extends AbstractAPITest {

	private static final Logger log = Logger.getLogger(PanelAPITest.class);

	private static final String GET_ALL_PANELS = baseUrl + "panel/all";
	private static final String GET_PANEL_BY_ID = baseUrl + "panel/{panelId}";
	private static final String GET_TRAINEE_PANELS = baseUrl + "panel/trainee/{traineeId}";
	private static final String GET_ALL_REPANELS = baseUrl + "panel/repanel/all";
	private static final String CREATE_PANEL = baseUrl + "panel/create";
	private static final String DELETE_PANEL = baseUrl + "panel/delete";
	private static final String UPDATE_PANEL = baseUrl + "panel/update";

	@Autowired
	private PanelDAO panelDAO;
	@Autowired
	private PanelFeedbackDAO pfDAO;
	
	@Autowired
	private TrainerDAO trDao;

	private BatchDAO batchDAO;
	@Autowired
	private TraineeDAO traineeDAO;
	
	/**
	 * Tests creation. Asserts that the status code 201 is returned meaning the
	 * creation was successful
	 */
	@Test
	public void testCreate201() {
		log.info("Creating a new Panel type");
		Panel panel = new Panel();
		panel.setFormat(InterviewFormat.Phone);
		panel.setPanelRound(1);
		panel.setStatus(PanelStatus.Pass);
		panel.setTrainee(traineeDAO.findOne(1));
		panel.setPanelist(trDao.findOne(1));
		given().spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).body(panel).when()
				.post(CREATE_PANEL).then().assertThat().statusCode(HttpStatus.CREATED_201);
	}

	/**
	 * Tests updating an existing panel by changing the duration. Asserts what is
	 * returned is the same as what we sent in the request
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testUpdate() throws Exception {
		log.info("Updating an panel");
		Panel expected = new Panel();
		expected.setId(2057);
		expected.setDuration("100 hours");
		Panel actual = new ObjectMapper().readValue(given().spec(requestSpec).header(AUTH, accessToken)
				.contentType(ContentType.JSON).body(expected).when().put(UPDATE_PANEL).then()
				.contentType(ContentType.JSON).assertThat().statusCode(HttpStatus.OK_200).and().extract().response().asString(),
				new TypeReference<Panel>() {
				});
		assertEquals(expected.getDuration(), actual.getDuration());
	}

	/**
	 * Tests delete and asserts the appropriate status code is returned (204)
	 */
	@Test
	public void testDelete() {
		log.info("Deleting an panel");
		given().spec(requestSpec).header(AUTH, accessToken).when().delete(DELETE_PANEL + "/2050").then()
				.assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllPanels204() {
		log.info("Get all panels when none exist");
		List<Panel> allPanels = panelDAO.findAll();
		for (Panel p : allPanels) {
			panelDAO.delete(p.getId());
		}
		int expected = panelDAO.findAll().size();
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_PANELS).
		then().assertThat().
			body("size()", is(expected)).
			statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllPanels200() {
		log.info("Get all panels");
		
		int expected = panelDAO.findAll().size();
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_PANELS).
		then().assertThat().
			body("size()", is(expected)).
			statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetPanelById204() {
		log.info("Get panel by id, no content");
		// TODO: Use panelId which does not exist in database
		Response response = (Response)
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_ID, -1).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
		assertNull(response);
	}

	@Test
	public void testPanelById200() {
		log.info("Get panel by id, OK");
		Panel p = panelDAO.findAll().get(0);
		log.info("panel= " + p);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_ID, p.getId()).
		then().assertThat().
			body("id", is(p.getId())).
			statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetPanelsByTrainee204() {
		log.info("Get all trainee panels, no content");
		Trainee t = new Trainee("Test", null, "test@test.com", batchDAO.findAll().get(0));
		traineeDAO.save(t);
		int expected = panelDAO.findAllByTrainee(t.getTraineeId()).size();
		log.info("expected= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_TRAINEE_PANELS, t.getTraineeId()).
		then().assertThat().
			body("size()", is(expected)).
			statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetPanelsByTrainee200() {
		log.info("Get all trainee panels, OK");
		List<Trainee> trainees = traineeDAO.findAll();
		int traineeId = -1;
		if (!trainees.isEmpty()) {
			traineeId = trainees.get(0).getTraineeId();
		}
		else {
			log.warn("No trainee found in testGetPanelsByTrainee200");
		}
		int expected = panelDAO.findAllByTrainee(traineeId).size();
		log.info("expected= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_TRAINEE_PANELS, traineeId).
		then().assertThat().
			body("size()", is(expected)).
			statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetAllRepanels204() {
		log.info("Get all repanels, no content");
		// Save panels and delete from database
		List<Panel> repanels = panelDAO.findAllRepanel();
		for (Panel p : repanels) {
			panelDAO.delete(p.getId());
		}
		int expected = panelDAO.findAllRepanel().size();
		log.info("expected= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_REPANELS).
		then().assertThat().
			body("size()", is(expected)).
			statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllRepanels200() {
		log.info("Get all repanels, OK");
		int expected = panelDAO.findAllRepanel().size();
		log.info("expected= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_REPANELS).
		then().assertThat().
			body("size()", is(expected)).
			statusCode(HttpStatus.OK_200);
	}
}
