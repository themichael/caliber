package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.beans.InterviewFormat;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PanelFeedbackAPITest extends AbstractAPITest {
	private static final Logger log = Logger.getLogger(PanelFeedbackAPITest.class);

	private static final String GET_ALL_PANEL_FEEDBACK_URL = baseUrl + "panelfeedback/all";
	private static final String GET_PANEL_FEEDBACK_BY_ID_URL = baseUrl + "panelfeedback/{panelId}";
	private static final String UPDATE_PANEL_FEEDBACK_URL = baseUrl + "panel/update";
	private static final String CREATE_PANEL_FEEDBACK_URL = baseUrl + "panel/create";

	@Autowired
	private PanelDAO panelDAO;
	@Autowired
	private PanelFeedbackDAO pfDAO;
	@Autowired
	private CategoryDAO catDAO;
	@Autowired
	private TraineeDAO traineeDAO;
	@Autowired
	private TrainerDAO trainerDAO;
	
	@BeforeClass
	public static void logIfValidationFails() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
	@Test
	public void testGetAllPanelFeedbacks200() {
		log.info("Get all panel feedbacks");
		int expected = pfDAO.findAll().size();
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_ALL_PANEL_FEEDBACK_URL).
		then().assertThat().
			statusCode(HttpStatus.OK_200).
			body("size()", is(expected));
		log.info("testGetAllPanelFeedbacks200 succeeded!!!");		
	}
	
	@Test
	public void testGetPanelFeedbackById200() {
		log.info("Get panel feedback by id");
		PanelFeedback pf = pfDAO.findAll().get(0);
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
		when().
			get(GET_PANEL_FEEDBACK_BY_ID_URL, pf.getId()).
		then().assertThat().
			statusCode(HttpStatus.OK_200).
			body("id", is((int) pf.getId()));
		log.info("testGetPanelFeedbackById200 succeeded!!!");		
	}
	
	@Test
	public void testUpdatePanelFeedback200() {
		log.info("Update panel feedback");
		PanelFeedback pf = pfDAO.findAll().get(0);
		pf.setComment("test comment");
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
			body(pf).
		when().
			put(UPDATE_PANEL_FEEDBACK_URL).
		then().assertThat().
			statusCode(HttpStatus.OK_200).
			body("id", is((int) pf.getId())).
			body("comment", is(pf.getComment()));
		log.info("testUpdatePanelFeedback200 succeeded!!!");
	}
	
	@Test
	public void testCreatePanelFeedback201() {
		log.info("Create panel feedback");
		PanelFeedback pf = new PanelFeedback();
		pf.setTechnology(catDAO.findAllActive().get(0));
		pf.setStatus(PanelStatus.Pass);
		pf.setResult(10);
		Panel p = new Panel();
		p.setTrainee(traineeDAO.findAll().get(0));
		p.setFormat(InterviewFormat.Phone);
		p.setPanelRound(1);
		p.setStatus(PanelStatus.Pass);
		p.setPanelist(trainerDAO.findAll().get(0));
		pf.setPanel(p);
		pf.setComment("test comment");
		
		given().
			spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).
			body(pf).
		when().
			post(CREATE_PANEL_FEEDBACK_URL).
		then().assertThat().
			statusCode(HttpStatus.CREATED_201);
		log.info("testCreatePanelFeedback201 succeeded!!!");
	}
}
