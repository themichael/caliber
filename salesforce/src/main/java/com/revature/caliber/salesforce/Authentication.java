package com.revature.caliber.salesforce;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by louislopez on 1/18/17.
 */


@Controller
public class Authentication {
    Map<String, String> environment = System.getenv();
    private String authURL = environment.get("SALESFORCE_AUTH_URL");
    private String accessTokenURL = environment.get("SALESFORCE_ACCESS_TOKEN_URL");
    private String clientId = environment.get("SALESFORCE_CLIENT_ID");
    private String clientSecret = environment.get("SALESFORCE_CLIENT_SECRET");
    private String redirectUri = environment.get("SALESFORCE_REDIRECT_URI");


    public String generateURL() {
        return authURL + "?response_type=code&client_id="
                + clientId + "&redirect_uri=" + redirectUri;
    }

    @RequestMapping(value = "/auth")
    public ModelAndView openAuth() {
        System.out.println("IN AUTH");
        return new ModelAndView("redirect:" + generateURL());
    }

    @RequestMapping(value = "/authenticated")
    public String getCode(@RequestParam(value = "code") String code, HttpServletResponse servletResponse) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(accessTokenURL);
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
            parameters.add(new BasicNameValuePair("client_secret", clientSecret));
            parameters.add(new BasicNameValuePair("client_id", clientId));
            parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
            parameters.add(new BasicNameValuePair("code", code));
            post.setEntity(new UrlEncodedFormEntity(parameters));
            HttpResponse response = httpClient.execute(post);

            System.out.println("RESPONSE CODE " + response.getStatusLine().getStatusCode());

            BufferedReader resp = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = resp.readLine();

            while (line != null) {
                result.append(line);
                line = resp.readLine();
            }
            Cookie cookie = new Cookie("salesforce_token",result.toString());
            servletResponse.addCookie(cookie);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return "forward:/trainer";
        }
    }


    @RequestMapping(value = "/trainer")
    public void test(HttpServletRequest request){
        
    }


}
