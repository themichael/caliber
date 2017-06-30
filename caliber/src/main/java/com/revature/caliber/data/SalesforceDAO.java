package com.revature.caliber.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.security.models.SalesforceUser;

@Repository
public class SalesforceDAO {

	private static final Logger log = Logger.getLogger(SalesforceDAO.class);
	
	@Value("#{systemEnvironment['SALESFORCE_INSTANCE_URL']}")
	private String salesforceInstanceUrl;
	@Value("/services/data/v39.0/query/")
	private String salesforceApiUrl;
	
	//////////// SOQL - Salesforce Object Query Language //////////////
	
	/**
	 * Will change as of version 2.0 Salesforce API in August/September 2017 timeframe
	 * Used to populate the dropdown list of importable batches.
		select id, name, batch_start_date__c, batch_end_date__c,
			batch_trainer__r.name, Co_Trainer__r.name, Skill_Type__c,
			Type__c from training__c where batch_start_date__c >= THIS_YEAR
	 */
	@Value("select id, name, batch_start_date__c, batch_end_date__c, batch_trainer__r.name, Co_Trainer__r.name, Skill_Type__c, Type__c from training__c where batch_start_date__c >= THIS_YEAR")
	private String relevantBatches;
	
	/**
	 * Will change as of version 2.0 Salesforce API in August/September 2017 timeframe
	 * Once user selects a batch to import, use this to load all the Trainee details.
	 	select id, name, training_status__c, phone, email, MobilePhone,
			Training_Batch__c , Training_Batch__r.name, 
			Training_Batch__r.batch_start_date__c, 
			Training_Batch__r.batch_end_date__c, 
			Training_Batch__r.batch_trainer__r.name, 
			rnm__Recruiter__r.name, account.name, 
			Training_Batch__r.Co_Trainer__r.name, 
			eintern_current_project_completion_pct__c ,
			Training_Batch__r.Skill_Type__c, 
			Training_Batch__r.Type__c from Contact 
			where training_batch__c = 'a0Yi000000F0b7I'
			
			// 'a0Yi000000F0b7I' is the resourceId
	 */
	@Value("select id, name, training_status__c, phone, email, MobilePhone, Training_Batch__c , Training_Batch__r.name, Training_Batch__r.batch_start_date__c, Training_Batch__r.batch_end_date__c, Training_Batch__r.batch_trainer__r.name, rnm__Recruiter__r.name, account.name, Training_Batch__r.Co_Trainer__r.name, eintern_current_project_completion_pct__c , Training_Batch__r.Skill_Type__c, Training_Batch__r.Type__c from Contact where training_batch__c = ")
	private String batchDetails;
	
	@Value("select id, name from Training__c")
	private String allBatches;
	
	// TODO test sample Batch query
	public void getAllBatches() {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			String url = new URIBuilder(salesforceInstanceUrl).setScheme("https").setHost(salesforceInstanceUrl)
					.setPath(salesforceApiUrl).setParameter("q", allBatches).build().toString();
			HttpGet getRequest = new HttpGet(url);
			getRequest.setHeader("Authorization", "Bearer " + getAccessToken());
			HttpResponse queryResponse = httpClient.execute(getRequest);
			
			// convert to your salesforce beans
			JsonNode queryResults = new ObjectMapper().readValue(queryResponse.getEntity().getContent(), JsonNode.class);
			log.info(queryResults);
			//transform to Caliber bean
			//return the bean
		} catch (URISyntaxException | IOException e) {
			log.warn("Unable to fetch Salesforce data: cause " + e.getClass() + " " + e.getMessage());
		}
	}
	
	/**
	 * TODO implement
	 * Get the batches in the current year and future years.
	 * Access data using the Salesforce REST API
	 * @return
	 */
	public List<Batch> getAllRelevantBatches(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * TODO implement
	 * Get the trainees and contact details for a single batch.
	 * Access data using the Salesforce REST API
	 * @return
	 */
	public Batch getBatchDetails(String resourceId){
		throw new UnsupportedOperationException();
	}

	private String getAccessToken() {
		//return "00D5C0000008jkd!AQYAQMZ6GkPlJIkw2SvF4ip1HGTgx3F_EZa5MBsXwKOEugD0u0_2AelDbDKbPjtn.Ae5vbDRSzpuHuOgIWhlwIwSon1QM16Y";
		return ((SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSalesforceToken().getAccessToken();
	}
}
