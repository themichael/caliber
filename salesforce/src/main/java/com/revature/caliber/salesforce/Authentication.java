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

/**
 * Created by louislopez on 1/18/17.
 */


@Controller
@PropertySource("classpath:dev_salesforce.properties")
public class Authentication {


    @Value("${authURL}")
    private String authURL;
    @Value("${accessTokenURL}")
    private String accessTokenURL;
    @Value("${clientId}")
    private String clientId;
    @Value("${clientSecret}")
    private String clientSecret;
    @Value("${redirectUri}")
    private String redirectUri;
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    public String generateURL(){
        return authURL+"response_type=code&client_id="
                +clientId+"&redirect_uri="+redirectUri;
    }


    @RequestMapping(value = "/auth")
    public ModelAndView openAuth() {
        System.out.println("IN AUTH");
        return new ModelAndView("redirect:"+generateURL());
    }

    @RequestMapping(value = "/token")
    public String getToken() {
        System.out.println("Got token");
        return "TO";

    }

    @ResponseBody
    @RequestMapping(value = "test")
    public String test(){
        return "TEST :)";
    }

}
