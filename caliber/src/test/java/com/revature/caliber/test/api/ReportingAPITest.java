package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.hasItems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.ReportingService;
import com.revature.caliber.test.integration.ReportingServiceTest;

import io.restassured.http.ContentType;

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
	/*
	//{int BatchId, int TraineeId, int Week}
	private static int[] traineeValue = {2150, 5500, 1};
	
	@Test
	@Ignore
	public void testGetBatchComparisonAvg() {
		given().header("Authorization", accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "/all/reports/compare/skill/(All)"
					+ "/training/(All)"
					+ "/date/Mon Sep 19 13:50:43 EDT 2016")
			.then();
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
	}*/
	@Test
	public void testgetTraineeOverallLineChart(){
		log.info("testgetTraineeOverallLineChart Test");
		int batchId = 2200;
		int traineeId = 5503;
		log.info("LOOK AT  "+service.getTraineeOverallLineChart(2200, 5503).toString());;
		Batch batch = new Batch();
		batch = dao.findOneWithTraineesAndGrades(batchId);
		log.info("THIS IS THE DAO findOneWithTraineesAndGrades"+ batch.toString());
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		.when().get(baseUrl+"all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall",batchId,traineeId).peek().then()
		.assertThat().statusCode(200).body("findAll { it }.collect { it.batchavg.en }",  hasItems("batchavg 85", "traineeavg 77 "));
	}
}
