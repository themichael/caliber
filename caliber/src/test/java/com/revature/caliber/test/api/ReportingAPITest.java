package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;

import io.restassured.http.ContentType;

public class ReportingAPITest extends AbstractAPITest{
	private static final Logger log = Logger.getLogger(ReportingAPITest.class);
	
	
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
			.when().get(baseUrl + "all/reports/batch/2200/pie")
			.then().assertThat().statusCode(404);
	}
	
	/**
	 *
	 *
	 *
	 **/
	@Test
	public void testGetAllBatchesCurrentWeekQCStackedBarChart() {
		log.info("TESTING getAllBatchesCurrentWeekQCStackedBarChart");
		System.out.println(
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/week/stacked-bar-current-week")
			.thenReturn().body().asString()
			);//().assertThat().statusCode(200)
	}
	
	/**
	 * 
	 * Test to see a valid request returns a 200.
	 * Test to see an invalid batch returns an empty json object
	 * 
	 * 
	 * */
	
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
	
	
	/**
	 * 
	 * Test to see a valid request returns a 200.
	 * Test to see an invalid batch returns an empty json object
	 *
	 **/
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
	
}
