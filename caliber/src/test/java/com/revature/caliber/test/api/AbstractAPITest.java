package com.revature.caliber.test.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.CaliberTest;
import com.revature.caliber.security.models.SalesforceToken;

public class AbstractAPITest extends CaliberTest {

	protected String baseUrl = System.getenv("CALIBER_SERVER_URL");
	protected String username = System.getenv("CALIBER_API_USERNAME");
	protected String password = System.getenv("CALIBER_API_PASSWORD");
	protected String clientId = System.getenv("SALESFORCE_CLIENT_ID");
	protected String clientSecret = System.getenv("SALESFORCE_CLIENT_SECRET");
	private String accessTokenUrl = "https://test.salesforce.com/services/oauth2/token";

	private static final Logger log = Logger.getLogger(AbstractAPITest.class);

	protected static String accessToken = "Auth ";

	public AbstractAPITest() {
		// only login with Salesforce once
		if (accessToken.equals("Auth ")) {
			try {
				login();
				// I think this makes you "login" to Caliber
				HttpClient httpClient = HttpClientBuilder.create().build();
				HttpGet get = new HttpGet(System.getenv("CALIBER_SERVER_URL"));
				HttpResponse response = httpClient.execute(get);
				
				/* if Salesforce authentication is successful, then you shall GET caliber homepage & 200 OK
				 * instead of a 302 REDIRECT to Salesforce Login page 
				 */
				log.info("Login page returned code: " + response.getStatusLine().getStatusCode());
				String responseBody = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
				assertThat(responseBody, containsString("<title>Caliber | Performance Management</title>"));
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
		accessToken += new ObjectMapper().readValue(response.getEntity().getContent(), SalesforceToken.class)
				.getAccessToken();
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
