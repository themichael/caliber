package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.TraineeDAO;

import io.restassured.http.ContentType;

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

	private String createPanel = "panel/create";
	private String deletePanel = "panel/delete/{panel}";
	private String updatePanel = "panel/update";

	@Autowired
	private PanelDAO panelDAO;
	@Autowired
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
		given().spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).body(panel).when()
				.post(baseUrl + createPanel).then().assertThat().statusCode(HttpStatus.CREATED_201);
	}

	/**
	 * Tests updating an existing panel by changing the duration. Asserts whats
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
				.contentType(ContentType.JSON).body(expected).when().put(baseUrl + updatePanel).then()
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
		given().spec(requestSpec).header(AUTH, accessToken).when().delete(baseUrl + deletePanel, 2050).then()
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
		then().
			body("size()", is(expected)).
		and().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllPanels200() {
		log.info("Get all panels");
		
		int expected = panelDAO.findAll().size();
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_PANELS).
		then().
			body("size()", is(expected)).
		and().
			assertThat().statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetPanelById204() {
		log.info("Get panel which does not exist");
		// TODO: Use panelId which does not exist in database
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_ID, -1).
		then().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testPanelById200() {
		log.info("Get all panels");
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_BY_ID, 1).
		then().
			assertThat().statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetPanelsByTrainee204() {
		log.info("Get panels when trainee has none");
		// TODO: Save new trainee to database, don't add panels
		List<Batch> batches = batchDAO.findAll();
		Batch b = batches.get(0);
		Trainee t = new Trainee("Test", null, "test@test.com", b);
		traineeDAO.save(t);
		int expected = panelDAO.findAllByTrainee(t.getTraineeId()).size();
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_TRAINEE_PANELS, t.getTraineeId()).
		then().
			body("size()", is(expected)).
		and().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetPanelsByTrainee200() {
		log.info("Get all panels");
		final int traineeId = 1;
		int expected = panelDAO.findAllByTrainee(traineeId).size();
		log.info("Expected panels for trainee " + traineeId + "= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_TRAINEE_PANELS, traineeId).
		then().
			assertThat().statusCode(HttpStatus.OK_200).
		and().
			body("size()", is(expected));
	}

	@Test
	public void testGetAllRepanels204() {
		log.info("Get all repanels when none exist");
		// Save panels and delete from database
		List<Panel> repanels = panelDAO.findAllRepanel();
		for (Panel p : repanels) {
			panelDAO.delete(p.getId());
		}
		int expected = panelDAO.findAllRepanel().size();
		log.info("Number of repanels= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_REPANELS).
		then().
			body("size()", is(expected)).
		and().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllRepanels200() {
		log.info("Get all repanels");
		int expected = panelDAO.findAllRepanel().size();
		log.info("Number of repanels= " + expected);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_REPANELS).
		then().
			body("size()", is(expected)).
		and().
			assertThat().statusCode(HttpStatus.OK_200);
	}
}
