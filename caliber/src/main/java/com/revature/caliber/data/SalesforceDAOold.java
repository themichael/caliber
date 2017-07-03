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
import com.revature.caliber.salesforce.beans.SalesforceBatch;
import com.revature.caliber.security.models.SalesforceUser;

@Repository
public class SalesforceDAO {

	private static final Logger log = Logger.getLogger(SalesforceDAO.class);
	
	@Value("#{systemEnvironment['SALESFORCE_INSTANCE_URL']}")
	private String salesforceInstanceUrl;
	@Value("/services/data/v39.0/query/")
	private String salesforceApiUrl;
	
	//////////// SOQL - Salesforce Object Query Language //////////////
	@Value("")
	private String allBatchesByYear;
	@Value("")
	private String batchById;
	@Value("select id, name from Training__c")
	private String allBatches;
	
	// TODO test sample Batch query
	public List<Batch> getAllBatches() {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			String url = new URIBuilder(salesforceInstanceUrl).setScheme("https").setHost(salesforceInstanceUrl)
					.setPath(salesforceApiUrl).setParameter("q", allBatches).build().toString();
			HttpGet getRequest = new HttpGet(url);
			getRequest.setHeader("Authorization", "Bearer " + getAccessToken());
			HttpResponse queryResponse = httpClient.execute(getRequest);
			JsonNode queryResults = new ObjectMapper().readValue(queryResponse.getEntity().getContent(), JsonNode.class);
			log.info(queryResults);
		} catch (URISyntaxException | IOException e) {
			log.warn("Unable to fetch Salesforce data: cause " + e.getClass() + " " + e.getMessage());
		}
		return null;

	}
	
	public List<Batch> getAllBatchesByYear(int year){
		throw new UnsupportedOperationException();
	}
	
	public Batch getBatchByResourceId(String resourceId){
		throw new UnsupportedOperationException();
	}

	private String getAccessToken() {
		//return "00D5C0000008jkd!AQYAQMZ6GkPlJIkw2SvF4ip1HGTgx3F_EZa5MBsXwKOEugD0u0_2AelDbDKbPjtn.Ae5vbDRSzpuHuOgIWhlwIwSon1QM16Y";
		return ((SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSalesforceToken().getAccessToken();
	}
}
