package com.revature.caliber.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.Helper;
import com.revature.caliber.models.SalesforceToken;
import com.revature.caliber.models.SalesforceUser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@SessionAttributes("token")
public class BootController extends Helper {
    SalesforceToken token;
    HttpClient httpClient;

    public BootController() {
        httpClient = HttpClientBuilder.create().build();
    }

    @RequestMapping(value = "/")
    public String getHomePage(HttpServletRequest servletRequest) throws IOException, URISyntaxException {
        Cookie [] cookies = servletRequest.getCookies();
        SalesforceToken salesforceToken = null;
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("token")) {
                salesforceToken = new ObjectMapper().readValue(URLDecoder.decode(cookie.getValue(),"UTF-8"),SalesforceToken.class);
                break;
            }
        }
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("localhost")
                .setPort(8080)
                .setPath("/getSalesforceUser/")
                .setParameter("endpoint",salesforceToken.getId())
                .setParameter("accessToken",salesforceToken.getAccessToken());
        URI uri = uriBuilder.build();
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = httpClient.execute(httpGet);
        String user = toJsonString(response.getEntity().getContent());
        SalesforceUser salesforceUser = new ObjectMapper().readValue(user,SalesforceUser.class);
        salesforceUser.setSalesforceToken(salesforceToken);
        salesforceUser.setRole("ROLE_TRAINER");
        Authentication auth = new PreAuthenticatedAuthenticationToken(salesforceUser, salesforceUser.getUser_id(), salesforceUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "index";
    }
}
