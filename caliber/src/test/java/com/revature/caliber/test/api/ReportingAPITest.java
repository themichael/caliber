package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;

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

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
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

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

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
	private static int[] traineeValue = {2150, 5500, 1};
	
	
	/**
	 * 
	 * (unfinished)
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
		Map<QCStatus, Integer> expected = new HashMap<>();
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2200/week/1/pie")
			.then().assertThat().statusCode(200)
				.body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
		
	}

	@Test
	public void testGetPieChartCurrentWeekQCStatus() {
		log.info("TESTING getPieChartCurrentWeekQCStatus");
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/654654654/pie")
			.then().assertThat().statusCode(200);
	}
	@Test
	public void testGetAllBatchesCurrentWeekQCStackedBarChart() {
		log.info("TESTING getAllBatchesCurrentWeekQCStackedBarChart");
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/week/stacked-bar-current-week")
			.then().assertThat().statusCode(200);
	}
	
	@Test
	public void testGetBatchWeekAvgBarChart() {
		log.info("TESTING getBatchWeekAvgBarChart");
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON)
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
		given().spec(requestSpec).header(authHeader, accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/2200/week/1/bar-batch-weekly-sorted")
			.then().assertThat().statusCode(200);
		//Bad Batch number in the uri (223300) should return empty JSON object
		String actual = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "all/reports/batch/223300/week/1/bar-batch-weekly-sorted")
			.thenReturn().body().asString();
		String expected = "{}";
		assertEquals(expected, actual);
	}
	
	//Param Integer batchId, Integer traineeId
	@Test
	public void testGetBatchOverallTraineeBarChart() throws Exception{
		log.info("GetBatchOverallTraineeBarChart Test");
		Map<String, Double> expected = new HashMap<String, Double>();
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee", 
				traineeValue[0], traineeValue[1]).
		then().assertThat().statusCode(200).
		and().body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	//Param  Integer batchId
	@Test
	public void testGetBatchOverallBarChart() throws Exception{
		log.info("GetBatchOverallBarChart Test");
		Map<String, Double> expected = new HashMap<String, Double>();
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "/all/reports/batch/{batchId}/overall/bar-batch-overall", traineeValue[0])
		.then().and().body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
	
	
	
	
	//Param  Integer batchId,Integer weekId,  Integer traineeId
	public void testGetBatchWeekTraineeBarChart() {
		log.info("GetBatchWeekTraineeBarChart Test");

		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "/all/reports/batch/{batchId}/week"
				+ "/{weekId}/trainee/{traineeId}/bar-batch-week-trainee")
		.then();
	}
	
	//Param  int batchId, int week,  int traineeId
	public void testGetTraineeUpToWeekLineChart() {
		log.info("GetTraineeUpToWeekLineChart Test");
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "/all/reports/batch/{batchId}/week"
				+ "/{week}/trainee/{traineeId}/line-trainee-up-to-week")
		.then();
	}
	
	//Param  Integer batchId, Integer traineeId
	public void testGetTraineeOverallLineChart() {
		log.info("GetTraineeOverallLineChart Test");
		
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl + "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall")
		.then();
	}
	*/
	//Tests if the returned JSON matches the expected values returned from a Map
	@Ignore
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
		
		Response response = given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+"all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall",batchId[random],traineeId[random]);
		JSONObject responseJson = new JSONObject(response.getBody().asString());;
		for(int i =1; i <= responseJson.length(); i++){
			JSONArray values = responseJson.getJSONArray(Integer.toString(i));
			for(int j = 0; j < values.length(); j++) {
				double value = values.getDouble(j);
				assertEquals(Math.round(value*1000d)/1000d, Math.round(targetList.get(i-1)[j]*1000d)/1000d);
			}
		}
	}
	//This test takes a skill All, a training All, and the date. This date can be any date before this instance of time, and 1970.
	//If the date doesn't match up i.e changing 2015 to 2016 the day and weekday would be off giving 83.70232555860807
	//79.56691875000001 is the default number if it works correctly.
	@Test
	public void testGetBatchComparisonAvg(){
		String date = "Wed Sep 21 15:48:45 EDT 2016";
		log.info("Here it is testGetBatchComparisonAvg Test");
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+"all/reports/compare/skill/(All)/training/(All)/date/"+date)
		.then().assertThat().statusCode(200);
	}
}

