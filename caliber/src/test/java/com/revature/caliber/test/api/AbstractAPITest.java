package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.fasterxml.jackson.core.JsonParseException;
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
public abstract class AbstractAPITest extends CaliberTest implements InitializingBean{

	/**
	 * Salesforce access token to be used in Authorization HTTP header
	 */
	protected static String accessToken = "Auth ";
	protected static final String auth = "Authorization";
	protected static RequestSpecification requestSpec;
	
	protected static String baseUrl = System.getenv("CALIBER_SERVER_URL");
	private static String username = System.getenv("CALIBER_API_USERNAME");
	private static String password = System.getenv("CALIBER_API_PASSWORD");
	private static String clientId = System.getenv("SALESFORCE_CLIENT_ID");
	private static String clientSecret = System.getenv("SALESFORCE_CLIENT_SECRET");
	private static String accessTokenUrl = "https://test.salesforce.com/services/oauth2/token";
	protected static String authHeader = "Authorization";
	
	static{
		SpringApplication.run(Tomcat.class);
	}
	
	@Autowired
	public SessionFactory sessionFactory;
	
	private static final Logger log = Logger.getLogger(AbstractAPITest.class);

	public AbstractAPITest() {
		
	}
	
	public void afterPropertiesSet(){
		// only login with Salesforce once
				if ("Auth ".equals(accessToken)) {
					try {
						populateDatabase();
						login();
						log.info("Logging into Caliber for API testing");
						Response response = given().body("salestoken="+accessToken).redirects().allowCircular(true).get(baseUrl + "caliber/");
		                String sessionCookie = response.getCookie("JSESSIONID");
		                String roleCookie = response.getCookie("role");
		                requestSpec = new RequestSpecBuilder().addCookie("JSESSIONID", sessionCookie ).addCookie("role", roleCookie).build();
		                tearDownDatabase();
					} catch (Exception e) {
						log.error(e);
					}
				}
	}

	private static void login() throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
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
	
	private void populateDatabase() throws SQLException{
		log.info("Populating database with setup.sql");
		Connection con = jdbcTemplate.getDataSource().getConnection();
		EncodedResource resource = new EncodedResource(new FileSystemResource(new File("src/test/resources/setup.sql")));
		log.info("Rescource encoded, executing sql script");
		ScriptUtils.executeSqlScript(con,resource);
		log.info("Sql script executed");
		con.close();
	}
	
	private void tearDownDatabase() throws SQLException{
		log.info("Tearingdown database with teardown.sql");
		Connection con = jdbcTemplate.getDataSource().getConnection();
		EncodedResource resource = new EncodedResource(new FileSystemResource(new File("src/test/resources/teardown.sql")));
		
		ScriptUtils.executeSqlScript(con,resource);
		log.info("Sql script executed");
		con.close();
	}
}
