package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
	private static final String CREATE_PANEL = baseUrl + "panel/create";
	private static final String DELETE_PANEL = baseUrl + "panel/delete";
	private static final String UPDATE_PANEL = baseUrl + "panel/update";

	@Autowired
	private PanelDAO panelDAO;
	@Autowired
	private PanelFeedbackDAO pfDAO;

	@Autowired
	private TraineeDAO traineeDao;
	
	@Autowired
	private TrainerDAO trDao;

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
		panel.setTrainee(traineeDao.findOne(1));
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
	public void testGetAllPanelsNoContent() {
		log.info("Get all panels when none exist");
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_ALL_PANELS).then().assertThat()
				.statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllPanelsOK() {
		log.info("Get all panels");
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_ALL_PANELS).then().assertThat()
				.statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetPanelByIdNoContent() {
		log.info("Get panel which does not exist");
		// TODO: Use panelId which does not exist in database
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_PANEL_BY_ID, -1).then().assertThat()
				.statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testPanelByIdOK() {
		log.info("Get all panels");
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_PANEL_BY_ID, 1).then().assertThat()
				.statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetPanelsByTraineeNoContent() {
		log.info("Get panels when trainee has none");
		// TODO: Save new trainee to database, don't add panels
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_TRAINEE_PANELS, 1).then().assertThat()
				.statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetPanelsByTraineeOK() {
		log.info("Get all panels");
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_TRAINEE_PANELS, 1).then().assertThat()
				.statusCode(HttpStatus.OK_200);
	}

	@Test
	public void testGetAllRepanelsNoContent() {
		log.info("Get all repanels when none exist");
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_ALL_REPANELS).then().assertThat()
				.statusCode(HttpStatus.NO_CONTENT_204);
	}

	@Test
	public void testGetAllRepanelsOK() {
		log.info("Get all panels");
		given().spec(requestSpec).header(AUTH, accessToken).when().get(GET_ALL_REPANELS).then().assertThat()
				.statusCode(HttpStatus.OK_200);

	}
}
