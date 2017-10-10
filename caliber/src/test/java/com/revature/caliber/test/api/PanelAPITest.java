package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;

public class PanelAPITest extends AbstractAPITest {
	
	private static final Logger log = Logger.getLogger(PanelAPITest.class);
	
	private static final String GET_ALL_PANELS = baseUrl + "/panel/all";
	private static final String GET_PANEL_BY_ID = baseUrl + "/panel/{panelId}";
	private static final String GET_TRAINEE_PANELS = baseUrl + "/panel/trainee/{traineeId}";
	private static final String GET_ALL_REPANELS = baseUrl + "/panel/repanel/all";
	
	@Autowired
	private PanelDAO panelDAO;
	@Autowired
	private PanelFeedbackDAO pfDAO;
	
	@Test
	public void testGetAllPanelsNoContent() {
		log.info("Get all panels when none exist");
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_ALL_PANELS).
		then().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}
	
	@Test
	public void testGetAllPanelsOK() {
		log.info("Get all panels");
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_ALL_PANELS).
		then().
			assertThat().statusCode(HttpStatus.OK_200);
	}
	
	@Test
	public void testGetPanelByIdNoContent() {
		log.info("Get panel which does not exist");
		// TODO: Use panelId which does not exist in database
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_PANEL_BY_ID, -1).
		then().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}
	
	@Test
	public void testPanelByIdOK() {
		log.info("Get all panels");
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_PANEL_BY_ID, 1).
		then().
			assertThat().statusCode(HttpStatus.OK_200);
	}
	
	@Test
	public void testGetPanelsByTraineeNoContent() {
		log.info("Get panels when trainee has none");
		// TODO: Save new trainee to database, don't add panels
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_TRAINEE_PANELS, 1).
		then().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}
	
	@Test
	public void testGetPanelsByTraineeOK() {
		log.info("Get all panels");
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_TRAINEE_PANELS, 1).
		then().
			assertThat().statusCode(HttpStatus.OK_200);
	}
	
	@Test
	public void testGetAllRepanelsNoContent() {
		log.info("Get all repanels when none exist");
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_ALL_REPANELS).
		then().
			assertThat().statusCode(HttpStatus.NO_CONTENT_204);
	}
	
	@Test
	public void testGetAllRepanelsOK() {
		log.info("Get all panels");
		given().
			spec(requestSpec).header(AUTH, accessToken).
		when().
			get(GET_ALL_REPANELS).
		then().
			assertThat().statusCode(HttpStatus.OK_200);
	}
}
