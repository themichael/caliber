package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.beans.InterviewFormat;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * @author Nathan Koszuta
 * @author Connor Monson
 * @author Allan Jones
 */

public class PanelAPITest extends AbstractAPITest {

	private static final Logger log = Logger.getLogger(PanelAPITest.class);

	private static final String GET_ALL_PANELS_URL = baseUrl + "panel/all";
	private static final String GET_PANEL_BY_ID_URL = baseUrl + "panel/{panelId}";
	private static final String GET_TRAINEE_PANELS_URL = baseUrl + "panel/trainee/{traineeId}";
	private static final String GET_ALL_REPANELS_URL = baseUrl + "panel/repanel/all";
	private static final String CREATE_PANEL_URL = baseUrl + "panel/create";
	private static final String DELETE_PANEL_URL = baseUrl + "panel/delete/{panelId}";
	private static final String UPDATE_PANEL_URL = baseUrl + "panel/update";
	private static final String GET_PANEL_BY_BATCH_URL = baseUrl + "all/reports/batch/{batchId}/panel-batch-all-trainees";

	@Autowired
	private PanelDAO panelDAO;
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private TraineeDAO traineeDAO;
	@Autowired
	private TrainerDAO trDao;
	
	@BeforeClass
	public static void logIfValidationFails() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
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
		
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
			body(panel).
		when()
			.post(CREATE_PANEL_URL).
		then().assertThat().
			statusCode(HttpStatus.CREATED_201);
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
		Panel expected = panelDAO.findOne(40);
		expected.setDuration("100 hours");
		
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
			body(expected).
		when().
			put(UPDATE_PANEL_URL).
		then().assertThat().
			body("id", is(expected.getId())).
			body("duration", is(expected.getDuration())).
			statusCode(HttpStatus.OK_200);
	}

	/**
	 * Tests delete and asserts the appropriate status code is returned (204)
	 */
	@Test
	public void testDelete() {
		log.info("Deleting an panel");
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			delete(DELETE_PANEL_URL, 2050).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllPanels204() {
		log.info("Get all panels, no content...");
		List<Panel> allPanels = panelDAO.findAll();
		for (Panel p : allPanels) {
			panelDAO.delete(p.getId());
		}
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_PANELS_URL).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
		log.info("testGetAllPanels204 succeeded!!!");
	}

	@Test
	public void testGetAllPanels200() {
		log.info("Get all panels, OK...");
		
		int expected = panelDAO.findAll().size();
		
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_PANELS_URL).
		then().assertThat().
			statusCode(HttpStatus.OK_200).
			body("size()", is(expected));
		log.info("testGetAllPanels200 succeeded!!!");
	}

	@Test
	public void testGetPanelById204() {
		log.info("Get panel by id, no content...");
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_ID_URL, -1).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
		log.info("testGetPanelById204 succeeded!!!");
	}

	@Test
	public void testPanelById200() {
		log.info("Get panel by id, OK...");
		Panel p = panelDAO.findAll().get(0);
		log.info("panel= " + p);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_ID_URL, p.getId()).
		then().assertThat().
			statusCode(HttpStatus.OK_200).
			body("id", is(p.getId()));
		log.info("testPanelById200 succeeded!!!");
	}

	@Test
	public void testGetPanelsByTrainee204() {
		log.info("Get all trainee panels, no content...");
		Trainee t = new Trainee("Test", null, "test@test.com", batchDAO.findAll().get(0));
		traineeDAO.save(t);
		int expected = panelDAO.findAllByTrainee(t.getTraineeId()).size();
		log.info("expected= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_TRAINEE_PANELS_URL, t.getTraineeId()).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
		log.info("testGetPanelsByTrainee204 succeeded!!!");
	}

	@Test
	public void testGetPanelsByTrainee200() {
		log.info("Get all trainee panels, OK...");
		List<Panel> panels = panelDAO.findAll();
		int traineeId = -1;
		if (!panels.isEmpty()) {
			traineeId = panels.get(0).getTrainee().getTraineeId();
		}
		int expected = panelDAO.findAllByTrainee(traineeId).size();
		log.info("expected= " + expected + ", for trainee id " + traineeId);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_TRAINEE_PANELS_URL, traineeId).
		then().assertThat().
			body("size()", is(expected)).
			statusCode(HttpStatus.OK_200);
		log.info("testGetPanelsByTrainee200 succeeded!!!");
	}

	@Test
	public void testGetAllRepanels204() {
		log.info("Get all repanels, no content...");
		for (Panel p : panelDAO.findAllRepanel()) {
			panelDAO.delete(p.getId());
		}
		int expected = panelDAO.findAllRepanel().size();
		log.info("expected= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_REPANELS_URL).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
		log.info("testGetAllRepanels204 succeeded!!!");
	}

	@Test
	public void testGetAllRepanels200() {
		log.info("Get all repanels, OK...");
		int expected = panelDAO.findAllRepanel().size();
		log.info("expected= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_REPANELS_URL).
		then().assertThat().
			statusCode(HttpStatus.OK_200).
			body("size()", is(expected));
		log.info("testGetAllRepanels200 succeeded!!!");
	}

	@Test
	public void testGetPanelByBatch200() {
		log.info("Get all trainee panels by batch, OK...");
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_BATCH_URL, 2050).
		then().assertThat().
			statusCode(HttpStatus.OK_200);
		log.info("testGetPanelByBatch200 succeeded!!!");
	}

/*	
    @Test
	public void testGetPanelByTraineeID204() {
		log.info("Get a list of panels based on trainee, no content...");
		List<Panel> allPanels = panelDAO.findAllByTrainee(5510);
		for (Panel p : allPanels) {
			panelDAO.delete(p.getId());
		}
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_TRAINEE_PANELS_URL, 5510).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
		log.info("testGetPanelByTraineeID204 succeeded!!!");
	}
*/
/*
	@Test
	public void testGetPanelByBatch204() {
		log.info("Get a list of trainees based on batchId, no content...");
		List<Trainee> allTraineesByBatch = panelDAO.findAllTraineesAndPanels(2050);
		for (Trainee t : allTraineesByBatch) {
			traineeDAO.delete(t);
		}
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_BATCH_URL, 2050).
		then().assertThat().
			statusCode(HttpStatus.NO_CONTENT_204);
		log.info("testGetPanelByBatch204 succeeded!!!");
	}
*/
}
