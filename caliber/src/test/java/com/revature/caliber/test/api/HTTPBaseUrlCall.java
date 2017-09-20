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
	private String username = System.getenv("CALIBER_API_USERNAME");
	private String password = System.getenv("CALIBER_API_PASSWORD");
	private String clientId = System.getenv("SALESFORCE_CLIENT_ID");
	private String clientSecret = System.getenv("SALESFORCE_CLIENT_SECRET");
	private String accessTokenUrl = "https://test.salesforce.com/services/oauth2/token";
	private static final String allSkillTypes = "types/skill/all";

	
	@Test
	public  void stuff() {
		System.out.println(baseUrl + "caliber/");
		
		Response response = given().redirects().allowCircular(true).get(baseUrl + "caliber/");
		String sessionCookie = response.getCookie("JSESSIONID");
		String roleCookie = response.getCookie("role");
		System.out.println(response.getCookie("JSESSIONID"));
		System.out.println(response.getCookie("role"));
		 RequestSpecification requestSpec = new RequestSpecBuilder().addCookie("JSESSIONID", sessionCookie ).addCookie("role", roleCookie).build();
		given().spec(requestSpec).header("Authorization", accessToken).contentType(ContentType.JSON)
		//get request for all skills
		.when().get(baseUrl + allSkillTypes)
		//assertions
		.then().assertThat().statusCode(200)
			.body("$", hasItems("J2EE", ".NET", "SDET", "BPM", "Other"))
			.body("$", not(hasItems("Cukes", "Parfait")));
		
	}
}
