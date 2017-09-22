package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.concurrent.ThreadLocalRandom;

import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.xpath.operations.Equals;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsEqual;import org.hamcrest.text.IsEmptyString;
import org.hibernate.mapping.Array;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.ReportingService;
import com.revature.caliber.test.integration.ReportingServiceTest;

import antlr.collections.List;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ReportingAPITest extends AbstractAPITest{
	
	private static final Logger log = Logger.getLogger(ReportingAPITest.class);
	ReportingService service;
	BatchDAO dao;
	
	@Autowired
	public void setDao(BatchDAO dao) {
		this.dao = dao;
	}
	@Autowired
	public void setService(ReportingService service) {
		this.service = service;
	}
	
	//{int BatchId, int TraineeId, int Week}
	private static int[] traineeValue = {2200, 5503, 1};

	

	private static String traineeReports = "all/reports/trainee/";
	private static String radarTraineeOverall = "/radar-trainee-overall/";
	private static String batchReports= "all/reports/batch/";
	private static String radarBatchOverall = "/overall/radar-batch-overall";
	private static String batchAverage = "all/assessments/average/";
	private static String batchAssessmentCategories = "all/assessments/categories/batch/";


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

	@SuppressWarnings("deprecation")
	/*@Test
	public void testGetBatchComparisonAvg() throws Exception {
		log.info("TESTING getBatchComparisonAvg");
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON)
				.when().get(baseUrl + "all/reports/compare/skill/Java"
						+ "/training/University"
						+ "/date/14-NOV-16")
				.then().assertThat().statusCode(200)
					.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(new HashMap<Double,Double>())));
	}
	

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

	
	//Tests if the returned JSON matches the expected values returned from a Map

	@SuppressWarnings("deprecation")
	@Test
	public void testgetTraineeOverallLineChart(){
		log.info("testgetTraineeOverallLineChart Test");
		//The arrays are set up so the nth elemnt in batchId matches the nth element in traineeId
		Integer[] batchId = new Integer[]{2200,2050,2150};
		Integer[] traineeId = new Integer[]{5503,5350,5467};
		int random = ThreadLocalRandom.current().nextInt(0, 3);
		//Gets the maps from the service
		Map<Integer, Double[]> theMap = service.getTraineeOverallLineChart(batchId[random],traineeId[random]);
		//Maps the double array values to an ArrayList
		ArrayList<Double[]> targetList = new ArrayList<Double[]>(theMap.values());
		//Stores the array body as a string 
		Response response = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+"all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall",batchId[random],traineeId[random]);
		JSONObject responseJson = new JSONObject(response.getBody().asString());;
		//For the length of the body finds the double array and compares the 1st and second values of the double array
		for(int i =1; i <= responseJson.length(); i++){
			JSONArray values = responseJson.getJSONArray(Integer.toString(i));
			for(int j = 0; j < values.length(); j++) {
				double value = values.getDouble(j);
				assertEquals(Math.round(value*1000d)/1000d, Math.round(targetList.get(i-1)[j]*1000d)/1000d);
			}
		}
	}
	/**
	* This test takes a skill All, a training All, and the date. This date can be any date before this instance of time, and 1970.
	* If the date doesn't match up i.e changing 2015 to 2016 the day and weekday would be off giving 83 ish.But because the Web app
	* always calls that second it is valid and returns the Avg number.
	*/
	@Test
	public void testGetBatchComparisonAvg(){
		String date = "Wed Sep 21 15:48:45 EDT 2016";
		log.info("Here it is testGetBatchComparisonAvg Test");
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+"all/reports/compare/skill/(All)/training/(All)/date/"+date).then()
		.assertThat().statusCode(200);
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

