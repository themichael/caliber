package com.revature.caliber.salesforce;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by louislopez on 1/18/17.
 */


@Controller
public class PreAuthentication {
    Map<String, String> environment = System.getenv();
    private String authURL = environment.get("SALESFORCE_AUTH_URL");
    private String accessTokenURL = environment.get("SALESFORCE_ACCESS_TOKEN_URL");
    private String clientId = environment.get("SALESFORCE_CLIENT_ID");
    private String clientSecret = environment.get("SALESFORCE_CLIENT_SECRET");
    private String redirectUri = environment.get("SALESFORCE_REDIRECT_URI");
    private SalesforceToken salesforceToken;
    private SalesforceUser salesforceUser;
    private HttpClient httpClient;


    @RequestMapping(value = "/hello")
    public void hello() {
        System.out.println("HELLO . . . . ");
    }


    public String generateURL() {
        return authURL + "?response_type=code&client_id="
                + clientId + "&redirect_uri=" + redirectUri;
    }

    @RequestMapping(value = "/access")
    public ModelAndView openAuth() {
        System.out.println("IN AUTH");
        return new ModelAndView("redirect:" + generateURL());
    }

    @RequestMapping(value = "/authenticated")
    public String getCode(@RequestParam(value = "code") String code, HttpServletResponse servletResponse) {
        try {
            httpClient = HttpClientBuilder.create().build();
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
            System.out.println(result.toString());

            Cookie cookie = new Cookie("salesforce_token", result.toString());
            servletResponse.addCookie(cookie);

            setAuthCredentials(result.toString());

            System.out.println("ACCESS TOKEN ID IS " + salesforceToken.getAccess_token());



            /*
                Made SalesforceUser class implement UserDetails(spring security).
                Role is currently hard coded role at the moment but can be fetched from DB.
                Since the response from the salesforce API proves user is authenticated
                I can now authenticate them in the application .. they now have access to any page
                with admin privileges.
             */
            setSalesforceUser(salesforceToken.getId());
            salesforceUser.setRole("ROLE_ADMIN");

            Authentication auth = new PreAuthenticatedAuthenticationToken(salesforceUser, salesforceUser.getUser_id(), salesforceUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);


        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally

        {
            return "forward:/trainer";
        }
    }



    public void setAuthCredentials(String str){
        JSONObject tokenCredentials = new JSONObject(str.toString());
        salesforceToken = new SalesforceToken(
                tokenCredentials.get("access_token").toString(),
                tokenCredentials.get("signature").toString(),
                tokenCredentials.get("scope").toString(),
                tokenCredentials.get("instance_url").toString(),
                tokenCredentials.get("id").toString(),
                tokenCredentials.get("token_type").toString(),
                tokenCredentials.get("issued_at").toString()
        );
    }


    public void setSalesforceUser(String str) throws IOException {
        httpClient = HttpClientBuilder.create().build();
        System.out.println(str);
        HttpGet get = new HttpGet(str+"?access_token="+salesforceToken.getAccess_token());
        HttpResponse response = httpClient.execute(get);
        System.out.println("RESPONSE CODE " + response.getStatusLine().getStatusCode());
        BufferedReader resp = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = resp.readLine();
        while (line != null) {
            result.append(line);
            line = resp.readLine();
        }

        JSONObject userCredentials = new JSONObject(result.toString());
        salesforceUser = new SalesforceUser(userCredentials.getString("user_id"),
                userCredentials.getString("organization_id"),
                userCredentials.getString("username"),
                userCredentials.getString("email"),
                userCredentials.getString("first_name"),
                userCredentials.getString("last_name"));
    }

    public SalesforceUser getSalesforceUser() {
        return salesforceUser;
    }

    public SalesforceToken getSalesforceToken() {
        return salesforceToken;
    }


}
