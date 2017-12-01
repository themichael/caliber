package com.revature.caliber.tasks;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.security.models.SalesforceToken;
import com.revature.caliber.security.models.SalesforceUser;

@Service
public class SalesforceAuth {
	
	protected static String accessToken = "Auth ";
	protected static SalesforceToken accessTokenJson;
	protected static final String AUTH = "Authorization";
	protected static String jsessionid;

	protected static String baseUrl = System.getenv("CALIBER_API_SERVER");
	private static String username = System.getenv("CALIBER_API_USERNAME");
	private static String password = System.getenv("CALIBER_API_PASSWORD");
	private static String clientId = System.getenv("SALESFORCE_CLIENT_ID");
	private static String clientSecret = System.getenv("SALESFORCE_CLIENT_SECRET");
	private static String accessTokenUrl = "https://test.salesforce.com/services/oauth2/token";
	protected static String authHeader = "Authorization";
	
	private static final String PASS = "password";
	
	private static Logger log = Logger.getLogger(SalesforceAuth.class);
	
	public void setUser() {
		
		try {
			log.info("Logging into Salesforce "+clientId+" "+clientSecret);
			log.info("User: "+username+" Pass: "+password);
			login();
			SalesforceUser salesforceUser = new SalesforceUser();
			salesforceUser.setUserId("patrick@revature.com");
			Trainer trainer = new Trainer("Patrick Walsh", "Lead Trainer", "patrick.walsh@revature.com", TrainerRole.ROLE_VP);
			trainer.setTrainerId(1);
			salesforceUser.setCaliberUser(trainer);
			salesforceUser.setSalesforceToken(accessTokenJson);
			
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**
	 * Logs into salesforce to obtain an accessToken
	 * 
	 * @throws JsonMappingException
	 * @throws IOException
	 * @author Patrick Walsh
	 */
	private static void login() throws JsonMappingException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		log.info("logging into URL   " + accessTokenUrl);
		HttpPost post = new HttpPost(accessTokenUrl);
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("grant_type", PASS));
		parameters.add(new BasicNameValuePair("client_secret", clientSecret));
		parameters.add(new BasicNameValuePair("client_id", clientId));
		parameters.add(new BasicNameValuePair("username", username));
		parameters.add(new BasicNameValuePair(PASS, password));
		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = httpClient.execute(post);
		try{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
			accessTokenJson = mapper.readValue(response.getEntity().getContent(),
					SalesforceToken.class); // actual
			accessToken += accessTokenJson.getAccessToken();
			log.info("Accessing Salesforce API using token:  " + accessToken);
		}catch(Exception e){
			log.error(e);
			httpClient = HttpClientBuilder.create().build();
			log.info("logging into URL   " + accessTokenUrl);
			post = new HttpPost(accessTokenUrl);
			parameters = new ArrayList<>();
			parameters.add(new BasicNameValuePair("grant_type", PASS));
			parameters.add(new BasicNameValuePair("client_secret", clientSecret));
			parameters.add(new BasicNameValuePair("client_id", clientId));
			parameters.add(new BasicNameValuePair("username", username));
			parameters.add(new BasicNameValuePair(PASS, password));
			post.setEntity(new UrlEncodedFormEntity(parameters));
			response = httpClient.execute(post);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
			log.error(mapper.readValue(response.getEntity().getContent(),
					JsonNode.class)); 
		}
	}
}
