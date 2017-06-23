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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.security.models.SalesforceUser;

@Repository
public class SalesforceDAO {

	private static final Logger log = Logger.getLogger(SalesforceDAO.class);

	// TODO test sample Batch query
	public void getAllBatches() {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			URIBuilder builder = new URIBuilder("https://revature.my.salesforce.com")
					.setPath("/services/data/v39.0/query/").setParameter("q", "select id, name from Training__c");
			HttpGet getRequest = new HttpGet(builder.build());
			getRequest.setHeader("Authorization", "Bearer " + getAccessToken());
			HttpResponse queryResponse = httpClient.execute(getRequest);
			JsonNode queryResults = new ObjectMapper().readValue(queryResponse.getEntity().getContent(), JsonNode.class);
			log.fatal(queryResults);
		} catch (URISyntaxException | IOException e) {
			log.warn("Unable to fetch Salesforce data: cause " + e.getClass() + " " + e.getMessage());
		}

	}

	private String getAccessToken() {
		return ((SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSalesforceToken().getAccessToken();
	}
}
