package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import io.restassured.http.ContentType;

public class ReportingAPITest extends AbstractAPITest{
	private static final Logger log = Logger.getLogger(ReportingAPITest.class);
	
	public void testGetBatchComparisonAvg() {
		given().header("Authorization", accessToken).contentType(ContentType.JSON)
			.when().get(baseUrl + "/all/reports/compare/skill/(All)"
					+ "/training/(All)"
					+ "/date/Mon Sep 19 13:50:43 EDT 2016")
			.then();
	}
	
}
