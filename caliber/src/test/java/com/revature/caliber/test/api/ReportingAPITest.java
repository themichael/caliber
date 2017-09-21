package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

public class ReportingAPITest extends AbstractAPITest{
	
	private static final Logger log = Logger.getLogger(ReportingAPITest.class);
	//{int BatchId, int TraineeId, int Week}
	private static int[] traineeValue = {2200, 5503, 1};
	private static String traineeReports = "all/reports/trainee/{traineeId}/radar-trainee-overall";
	private static String batchReports= "all/reports/batch/{batchId}/overall/radar-batch-overall";
	private static String batchAverage = "all/assessments/average/{batchId}/{week}";
	private static String batchAssessmentCategories = "all/assessments/categories/batch/{batchId}/week/{week}";
	
	/**
	 * Tested Method:
	 * 		com.revature.caliber.controllers.ReportingController.getBatchWeekPieChart(Integer batchId, Integer weekId)
	 * Test to make sure that the method returns 200
	 * Tested method only needs to and only can return 200 since any invalid params need 
	 * 			to return something readable by the front end
	 * 
	 * */
	@Test
	public void testGetBatchWeekPieChart() throws JsonProcessingException {
		log.info("TESTING getBatchWeekPieChart");		
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2200/week/1/pie")
			.then().assertThat().statusCode(200);
		
	}
	
	/**
	 * 
	 * Tested Method:
	 * 		com.revature.caliber.controllers.ReportingController.getPieChartCurrentWeekQCStatus(Integer batchId)
	 * Check that a good batch returns 200
	 * Check that a batch with no notes returns a 404
	 * 
	 * */
	@Test
	public void testGetPieChartCurrentWeekQCStatus() {
		log.info("TESTING getPieChartCurrentWeekQCStatus");
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2201/pie")
			.then().assertThat().statusCode(200);
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2200/pie")
			.then().assertThat().statusCode(404);
	}
	
	/**
	 *
	 *Tested Method:
	 * 		com.revature.caliber.controllers.ReportingController.getAllBatchesCurrentWeekQCStackedBarChart()
	 *Tests to make sure response returns a 200
	 *
	 **/
	@Test
	public void testGetAllBatchesCurrentWeekQCStackedBarChart() {
		log.info("TESTING getAllBatchesCurrentWeekQCStackedBarChart");	
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/week/stacked-bar-current-week")
			.then().assertThat().statusCode(200);
	}
	
	/**
	 * 
	 * Tested Method:
	 * 		com.revature.caliber.controllers.ReportingController.getBatchWeekAvgBarChart(int batchId, int week)
	 * Test to see a valid request returns a 200
	 * Test to see an invalid batch returns an empty json object
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
	 * Tested Method:
	 * 		com.revature.caliber.controllers.ReportingController.getBatchWeekSortedBarChart(int batchId, int week)
	 * Test to see a valid request returns a 200
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
	
	/**
	 *  Validates the existence of the trainee's overall radar chart,
	 *  given the trainee's ID. 
	 * @throws Exception
	 */
	@Test
	public void getTraineeOverallRadarChart() throws Exception{
		log.info("Validate trainee's overall radar chart");
		Map<String, Double> expected = new HashMap<String, Double>();
		given().
			spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
		when().
			get(baseUrl + traineeReports, 5465).
		then().
			assertThat().statusCode(200).
		and().
			body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}

	/**
	  * Test method:
	  * 	com.revature.caliber.controllers.ReportingController
	  * 		.getBatchOverallTraineeBarChart()(Integer batchId, Integer traineeId)
	  * 
	  * @Result ResponseEntity is returned with a body that is not null and a status code 200
	  */
	@Test
	public void testGetBatchOverallTraineeBarChart() throws Exception{
		log.info("GetBatchOverallTraineeBarChart Test");
		
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee", 
				traineeValue[0], traineeValue[1])
		.then().assertThat().statusCode(200);
	}
	
	/**
	  * Tests method:
	  * 	com.revature.caliber.controllers.ReportingController
	  * 		.getBatchOverallBarChart()(Integer batchId)
	  * 
	  * @Result ResponseEntity is returned with a body that is not null and a status code 200
	  */ 
	@Test
	public void testGetBatchOverallBarChart() throws Exception{
		log.info("GetBatchOverallBarChart Test");
		
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "all/reports/batch/{batchId}/overall/bar-batch-overall", traineeValue[0])
		.then().assertThat().statusCode(200);
	}
	
	/**
	 *  Validates the existence of the batch overall radar chart,
	 *  given the batch ID. 
	 * @throws Exception
	 */
	@Test
	public void getBatchOverallRadarChart() throws Exception{
		log.info("Validate batch's overall radar chart");
		Map<String, Double> expected = new HashMap<String, Double>();
		given().
			spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
		when().
			get(baseUrl + batchReports, 2150).
		then().
			assertThat().statusCode(200).
		and().
			body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	/**
	 *  Validates the existence of the radar chart for all trainees
	 *  in a batch, given the batch ID. 
	 * @throws Exception
	 */
	@Test
	public void getBatchAllTraineesRadarChart() throws Exception{
		log.info("Validate batch's overall radar chart");
		Map<String, Map<String, Double>> expected = new HashMap<String, Map<String, Double>>();
		given().
			spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
		when().
			get(baseUrl + batchReports, 2150).
		then().
			assertThat().statusCode(200).
		and().
			body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	/**
	 *  Validates the value of the batch average for the week,
	 *  given the batch ID and the week number
	 *  @throws Exception
	 */
	@Test
	public void getBatchWeekAverageValue() throws Exception{
		log.info("Validate retrieval of batch's overall average in a week");
		
		Double expected = new Double(80.26d);
		Double actual = 
			given().
				spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
			when().
				get(baseUrl + batchAverage, 2150, 1).
			then().
				assertThat().statusCode(200).
			and().
				extract().as(Double.class);
		
		assertEquals(expected, actual, 0.01d);
	}
	
	/**
	 *  Validates the value(s) of the technologies the batch learned
	 *  for the week, given the batch ID and the week number
	 *  @throws Exception
	 */
	@Test
	public void getTechnologiesForTheWeek() throws Exception{
		log.info("Validate retrieval of batch's technologies learned in a week");
		
		Set<String> expected = new HashSet<String>();
		expected.add("AWS");
		expected.add("Hibernate");
		
		Set<String> actual = new ObjectMapper().readValue(
			given().
				spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON).
			when().
				get(baseUrl + batchAssessmentCategories, 2201, 5).
			then().
				contentType(ContentType.JSON).assertThat().statusCode(200).
			and().
				extract().response().asString(), new TypeReference<Set<String>>() {});
		
		assertEquals(expected, actual);
	}
	
	/**
	  * Tests method:
	  * 	com.revature.caliber.controllers.ReportingController
	  * 	.getBatchWeekTraineeBarChart()(Integer batchId,Integer weekId,  Integer traineeId)
	  * 
	  * @Result ResponseEntity is returned with a body that is not null and a status code 200
	  */ 
	@Test
	public void testGetBatchWeekTraineeBarChart() throws Exception {
		log.info("GetBatchWeekTraineeBarChart Test");
		
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee", 
				traineeValue[0], traineeValue[1])
		.then().assertThat().statusCode(200);
	}
	
	/**
	  * Test method:
	  * 	com.revature.caliber.controllers.ReportingController
	  * 	.getTraineeUpToWeekLineChart()(int batchId, int Week, int traineeId)
	  * 
	  * @Result ResponseEntity is returned with a body that is not null and a status code 200
	  */  
	@Test
	public void testGetTraineeUpToWeekLineChart() throws Exception {
		log.info("GetTraineeUpToWeekLineChart Test");
		
		given().spec(requestSpec).header(auth, accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "all/reports/batch/{batchId}/week"
				+ "/{week}/trainee/{traineeId}/line-trainee-up-to-week", 
				traineeValue[0], traineeValue[2], traineeValue[1])
		.then().assertThat().statusCode(200);
	}
}
