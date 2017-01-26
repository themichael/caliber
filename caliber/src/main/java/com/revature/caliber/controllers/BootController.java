package com.revature.caliber.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.models.SalesforceToken;
import com.revature.caliber.models.SalesforceUser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class BootController extends Helper{
    SalesforceToken token;
    HttpClient httpClient;

    public BootController() {
        httpClient = HttpClientBuilder.create().build();
    }

    @RequestMapping(
            value = {"/"},
            method = {RequestMethod.GET}
    )
    public String homePage() {
        return "index";
    }

    @RequestMapping(value = {"/getToken"}, produces = {"application/json"})
    @ResponseBody
    public String getToken(HttpServletRequest request) throws IOException, URISyntaxException {
        HttpGet httpGet = new HttpGet("http://localhost:8080/salesforce/getSalesforceToken");
        HttpResponse response = httpClient.execute(httpGet);
        token = new ObjectMapper().readValue(response.getEntity().getContent(),SalesforceToken.class);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("localhost")
                .setPort(8080)
                .setPath("/salesforce/getSalesforceUser/")
                .setParameter("endpoint",token.getId())
                .setParameter("accessToken",token.getAccess_token());
        URI uri = uriBuilder.build();
        httpGet = new HttpGet(uri);
        response = httpClient.execute(httpGet);
        String user = toJsonString(response.getEntity().getContent());
        SalesforceUser salesforceUser = new ObjectMapper().readValue(user,SalesforceUser.class);
        salesforceUser.setSalesforceToken(token);
        salesforceUser.setRole("ROLE_TRAINER");
        Authentication auth = new PreAuthenticatedAuthenticationToken(salesforceUser, salesforceUser.getUser_id(), salesforceUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return user.toString();
    }
}
