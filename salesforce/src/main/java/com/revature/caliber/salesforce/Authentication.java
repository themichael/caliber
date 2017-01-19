package com.revature.caliber.salesforce;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * Created by louislopez on 1/18/17.
 */


@Controller
public class Authentication {
    Map<String, String> environment = System.getenv();
    private String authURL = environment.get("SALESFORCE_AUTH_URL");
    private String accessTokenURL= environment.get("SALESFORCE_ACCESS_TOKEN_URL");
    private String clientId = environment.get("SALESFORCE_CLIENT_ID");
    private String clientSecret = environment.get("SALESFORCE_CLIENT_SECRET");
    private String redirectUri = environment.get("SALESFORCE_REDIRECT_URI");

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public String generateURL() {
        return authURL + "?response_type=code&client_id="
                + clientId + "&redirect_uri=" + redirectUri;
    }


    @RequestMapping(value = "/auth")
    public ModelAndView openAuth() {
        System.out.println("IN AUTH");
        return new ModelAndView("redirect:" + generateURL());
    }

    @RequestMapping(value = "/code")
    public String getToken() {
        System.out.println("Got token");
        return "TO";

    }
}
