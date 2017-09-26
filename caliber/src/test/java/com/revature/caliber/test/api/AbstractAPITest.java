package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.CaliberTest;
import com.revature.caliber.security.models.SalesforceToken;
import com.revature.caliber.test.unit.Tomcat;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Abstract class used to be extended to API testing classes.
 * Initializes by authenticating with the Salesforce API so
 * developers need only place accessToken variable as Authorization
 * header in their HTTP requests.
 * 
 * Requires appropriate credentials to be stored as environmental
 * variables. The credentials must also match a user in the Caliber database.
 * 
 * @author Patrick Walsh
 *
 */
public abstract class AbstractAPITest extends CaliberTest implements InitializingBean {

	/**
	 * Salesforce access token to be used in Authorization HTTP header
	 */
	protected static String accessToken = "Auth ";
	protected static SalesforceToken accessTokenJson;
	protected static final String AUTH = "Authorization";
	protected static String jsessionid;
	protected static RequestSpecification requestSpec;

	protected static String baseUrl = System.getenv("CALIBER_API_SERVER");
	private static String username = System.getenv("CALIBER_API_USERNAME");
	private static String password = System.getenv("CALIBER_API_PASSWORD");
	private static String clientId = System.getenv("SALESFORCE_CLIENT_ID");
	private static String clientSecret = System.getenv("SALESFORCE_CLIENT_SECRET");
	private static String accessTokenUrl = "https://test.salesforce.com/services/oauth2/token";
	protected static String authHeader = "Authorization";
	
	private static final Logger log = Logger.getLogger(AbstractAPITest.class);

	static {
		SpringApplication.run(Tomcat.class);
	}

	public void afterPropertiesSet() {
		// only login with Salesforce once
		if ("Auth ".equals(accessToken)) {
			try {
				populateDatabase();
				login();
				log.info("Logging into Caliber for API testing");
				Response response = given().param("salestoken", accessTokenJson).redirects().allowCircular(true)
						.get(baseUrl + "caliber/");
				String sessionCookie = response.getSessionId();
				String roleCookie = response.getCookie("role");
				log.info("JSESSIONID: " + sessionCookie + "\nRole: " + roleCookie);
				requestSpec = new RequestSpecBuilder().addCookie("JSESSIONID", sessionCookie)
						.addCookie("role", roleCookie).build();
				tearDownDatabase();
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	private static void login() throws JsonMappingException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		log.info("logging into URL   " + accessTokenUrl );
		HttpPost post = new HttpPost(accessTokenUrl);
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("grant_type", "password"));
		parameters.add(new BasicNameValuePair("client_secret", clientSecret));
		parameters.add(new BasicNameValuePair("client_id", clientId));
		parameters.add(new BasicNameValuePair("username", username));
		parameters.add(new BasicNameValuePair("password", password));
		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = httpClient.execute(post);
		accessToken += new ObjectMapper().readValue(response.getEntity().getContent(), 
				//JsonNode.class); // test
				SalesforceToken.class).getAccessToken(); // actual
		log.info("Accessing Salesforce API using token:  " + accessToken);
	}

	private void populateDatabase() throws SQLException {
		log.info("Populating database with setup.sql");
		Connection con = jdbcTemplate.getDataSource().getConnection();
		EncodedResource resource = new EncodedResource(
				new FileSystemResource(new File("src/test/resources/setup.sql")));

		ScriptUtils.executeSqlScript(con, resource);
		log.info("Sql script executed");
		con.close();
	}

	private void tearDownDatabase() throws SQLException {
		log.info("Tearingdown database with teardown.sql");
		Connection con = jdbcTemplate.getDataSource().getConnection();
		EncodedResource resource = new EncodedResource(
				new FileSystemResource(new File("src/test/resources/teardown.sql")));

		ScriptUtils.executeSqlScript(con, resource);
		log.info("Sql script executed");
		con.close();
	}
}
