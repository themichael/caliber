package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;

import io.restassured.http.ContentType;

public class PDFAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(PDFAPITest.class);
	
	private String generate = "/report/generate";

	/**
	 * Test API 	privateHttpEntity<byte[]> generate(
	 *      @RequestParam(name = "title", value = "title", defaultValue = "Performance at a Glance") String title,
	 *		@RequestBody String html)
	 *
	 * @throws Exception
	 */
	@Test
	public void generateTest() throws Exception{
		log.info("API testing PDFController.generate()");
		given.header("Authorization", accessToken).header("title", "Test title").contentType()
		
	}

	@Test
	public void findByEmail() throws Exception {
		Trainer expected = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com",
				TrainerRole.ROLE_VP);
		expected.setTrainerId(1);
		log.info("API Testing findTrainerByEmail at baseUrl  " + baseUrl);
		given().header("Authorization", accessToken).contentType(ContentType.JSON).when()
				.get(baseUrl + findByEmail).then().assertThat()
				.statusCode(200).body(matchesJsonSchema(new ObjectMapper().writeValueAsString(expected)));
	}
}
