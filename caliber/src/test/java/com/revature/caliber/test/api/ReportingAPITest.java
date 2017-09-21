package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import org.json.JSONArray;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReportingAPITest extends AbstractAPITest{
	
	private static final Logger log = Logger.getLogger(ReportingAPITest.class);
	private static String traineeReports = "all/reports/trainee/";
	private static String radarTraineeOverall = "/radar-trainee-overall/";
	private static String batchReports= "all/reports/batch/";
	private static String radarBatchOverall = "/overall/radar-batch-overall";
	private static String batchAverage = "all/assessments/average/";
	private static String batchAssessmentCategories = "all/assessments/categories/batch/";
	
	/**
	 * 
	 * (unfinished)
	 * 
	 * */
	@Test
	public void testGetBatchComparisonAvg() throws Exception {
		log.info("TESTING getBatchComparisonAvg");
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
				.when().get(baseUrl + "all/reports/compare/skill/Java"
						+ "/training/University"
						+ "/date/14-NOV-16")
				.then().assertThat().statusCode(200)
					.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(new HashMap<Double,Double>())));
	}
	
	@Test
	public void testGetBatchWeekPieChart() throws JsonProcessingException {
		log.info("TESTING getBatchWeekPieChart");
		Map<QCStatus, Integer> expected = new HashMap<>();
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2200/week/1/pie")
			.then().assertThat().statusCode(200)
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
		
	}

	@Test
	public void testGetPieChartCurrentWeekQCStatus() {
		log.info("TESTING getPieChartCurrentWeekQCStatus");
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/654654654/pie")
			.then().assertThat().statusCode(200);
	}
	@Test
	public void testGetAllBatchesCurrentWeekQCStackedBarChart() {
		log.info("TESTING getAllBatchesCurrentWeekQCStackedBarChart");
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/week/stacked-bar-current-week")
			.then().assertThat().statusCode(200);
	}
	
	@Test
	public void testGetBatchWeekAvgBarChart() {
		log.info("TESTING getBatchWeekAvgBarChart");
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2200/week/1/bar-batch-week-avg")
			.then().assertThat().statusCode(200);
		//Bad Batch number in the uri (223300) should return empty JSON object
		String actual = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/223300/week/1/bar-batch-week-avg")
			.thenReturn().body().asString();
		String expected = "{}";
		assertEquals(expected, actual);
	}
	@Test
	public void testGetBatchWeekSortedBarChart() {
		log.info("TESTING getBatchWeekSortedBarChart");
		log.info("TESTING getBatchWeekAvgBarChart");
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2200/week/1/bar-batch-weekly-sorted")
			.then().assertThat().statusCode(200);
		//Bad Batch number in the uri (223300) should return empty JSON object
		String actual = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/223300/week/1/bar-batch-weekly-sorted")
			.thenReturn().body().asString();
		String expected = "{}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void getTraineeOverallRadarChart() throws Exception{
		log.info("Validate trainee's overall radar chart");
		Map<String, Double> expected = new HashMap<String, Double>();
		given().
			spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
		when().
			get(baseUrl + traineeReports + 5465 + radarTraineeOverall).
		then().
			assertThat().statusCode(200).
		and().
			body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	@Test
	public void getBatchOverallRadarChart() throws Exception{
		log.info("Validate batch's overall radar chart");
		Map<String, Double> expected = new HashMap<String, Double>();
		given().
			spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
		when().
			get(baseUrl + batchReports + 2150 + radarBatchOverall).
		then().
			assertThat().statusCode(200).
		and().
			body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	@Test
	public void getBatchAllTraineesRadarChart() throws Exception{
		log.info("Validate batch's overall radar chart");
		Map<String, Map<String, Double>> expected = new HashMap<String, Map<String, Double>>();
		given().
			spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
		when().
			get(baseUrl + batchReports + 2150 + radarBatchOverall).
		then().
			assertThat().statusCode(200).
		and().
			body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	@Test
	public void getBatchWeekAverageValue() throws Exception{
		log.info("Validate retrieval of batch's overall average in a week");
		
		Integer week = new Integer(1);
		Double expected = new Double(80.26d);
		Double actual = 
			given().
				spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
			when().
				get(baseUrl + batchAverage + 2150 + "/" + week).
			then().
				assertThat().statusCode(200).
			and().
				extract().as(Double.class);
		
		assertEquals(expected, actual, 0.01d);
	}
	
	@Test
	public void getTechnologiesForTheWeek(){
		log.info("Validate retrieval of batch's technologies learned in a week");
		
		Integer week = new Integer(6);
		Set<String> expected = new HashSet<String>();
		expected.add("Spring");
		
		Response actual = 
			given().
				spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
			when().
				get(baseUrl + batchAssessmentCategories + 2150 + "/" + week).
			then().
				assertThat().statusCode(200).
			and().
				extract().response();
		
		log.info("HEYYYY " + actual.path("category").toString());
		String test = actual.path("category").toString();
		log.info("HEY: " + test);
		
		assertEquals(expected, actual);
	}
	
}
