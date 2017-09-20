package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.CaliberTest;

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
public abstract class AbstractAPITest extends CaliberTest {

	/**
	 * Salesforce access token to be used in Authorization HTTP header
	 */
	protected static String accessToken = "Auth ";
	protected static final String authHeader = "Authorization";
	protected static String jsessionid;
	protected static RequestSpecification requestSpec;
	
	protected String baseUrl = System.getenv("CALIBER_SERVER_URL");
	private String username = System.getenv("CALIBER_API_USERNAME");
	private String password = System.getenv("CALIBER_API_PASSWORD");
	private String clientId = System.getenv("SALESFORCE_CLIENT_ID");
	private String clientSecret = System.getenv("SALESFORCE_CLIENT_SECRET");
	private String accessTokenUrl = "https://test.salesforce.com/services/oauth2/token";
	
	private static final Logger log = Logger.getLogger(AbstractAPITest.class);

	public AbstractAPITest() {
		// only login with Salesforce once
		if (accessToken.equals("Auth ")) {
			try {
				login();
				log.info("Logging into Caliber for API testing");
				Response response = given().redirects().allowCircular(true).get(baseUrl + "caliber/");
                String sessionCookie = response.getCookie("JSESSIONID");
                String roleCookie = response.getCookie("role");
                requestSpec = new RequestSpecBuilder().addCookie("JSESSIONID", sessionCookie ).addCookie("role", roleCookie).build();
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	private void login() throws Exception {
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
		log.info("Generating Salesforce token using clientId " + clientId);
		HttpResponse response = httpClient.execute(post);
		accessToken += new ObjectMapper().readValue(response.getEntity().getContent(), 
				JsonNode.class);
				//SalesforceToken.class).getAccessToken(); 
		log.info("Accessing Salesforce API using token:  " + accessToken);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

}
