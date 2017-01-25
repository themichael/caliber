package com.revature.caliber.salesforce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.salesforce.models.SalesforceToken;
import com.revature.caliber.salesforce.models.SalesforceUser;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by louislopez on 1/18/17.
 */


@RestController
public class SalesforceAPI implements Salesforce {
    private String authURL;
    private String accessTokenURL;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private SalesforceToken salesforceToken;
    private SalesforceUser salesforceUser;
    private HttpClient httpClient;

    public SalesforceAPI() {
        httpClient = HttpClientBuilder.create().build();
    }

    public void setAuthURL(String authURL) {
        this.authURL = authURL;
    }

    public void setAccessTokenURL(String accessTokenURL) {
        this.accessTokenURL = accessTokenURL;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @RequestMapping(value = "/")
    public ModelAndView openAuthURI() {
        return new ModelAndView("redirect:" + authURL + "?response_type=code&client_id="
                + clientId + "&redirect_uri=" + redirectUri);
    }

    @RequestMapping(value = "/authenticated")
    public void authenticate(@RequestParam(value = "code") String code) throws IOException {
            HttpPost post = new HttpPost(accessTokenURL);
            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
            parameters.add(new BasicNameValuePair("client_secret", clientSecret));
            parameters.add(new BasicNameValuePair("client_id", clientId));
            parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
            parameters.add(new BasicNameValuePair("code", code));
            post.setEntity(new UrlEncodedFormEntity(parameters));
            HttpResponse response = httpClient.execute(post);
            salesforceToken = new ObjectMapper().readValue(response.getEntity().getContent(), SalesforceToken.class);
            setSalesforceUser(salesforceToken.getId());
    }

    public void setSalesforceUser(String str) throws IOException {
        HttpGet get = new HttpGet(str + "?access_token=" + salesforceToken.getAccess_token());
        HttpResponse response = httpClient.execute(get);
        salesforceUser = new ObjectMapper().readValue(response.getEntity().getContent(),SalesforceUser.class);
        salesforceUser.setSalesforceToken(salesforceToken);
    }

}
