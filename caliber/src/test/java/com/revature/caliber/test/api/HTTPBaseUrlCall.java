package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HTTPBaseUrlCall {
	protected static String baseUrl = System.getenv("CALIBER_SERVER_URL");
	protected static String accessToken = "Auth ";
	private static final String ALL_SKILL_TYPES = "types/skill/all";
	private static final String JSESSION_ID = "JSESSIONID";

	
	@Test
	public  void stuff() {
		
		
		Response response = given().redirects().allowCircular(true).get(baseUrl + "caliber/");
		String sessionCookie = response.getCookie(JSESSION_ID);
		String roleCookie = response.getCookie("role");
		 RequestSpecification requestSpec = new RequestSpecBuilder().addCookie(JSESSION_ID, sessionCookie ).addCookie("role", roleCookie).build();
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all skills
		.when().get(baseUrl + ALL_SKILL_TYPES)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("J2EE", ".NET", "SDET", "BPM", "Other"))
			.body("$", not(hasItems("Cukes", "Parfait")));
		
	}
}
