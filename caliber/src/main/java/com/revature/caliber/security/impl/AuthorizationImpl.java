package com.revature.caliber.security.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.revature.caliber.security.Authorization;
import com.revature.caliber.security.models.SalesforceUser;

/**
 * Created by louislopez on 1/18/17.
 */

@Controller
@Scope("prototype")
public class AuthorizationImpl extends Helper implements Authorization {
	@Value("#{systemEnvironment['SALESFORCE_LOGIN_URL']}")
	private String loginURL;
	@Value("services/oauth2/authorize")
	private String authURL;
	@Value("services/oauth2/token")
	private String accessTokenURL;
	@Value("#{systemEnvironment['SALESFORCE_CLIENT_ID']}")
	private String clientId;
	@Value("#{systemEnvironment['SALESFORCE_CLIENT_SECRET']}")
	private String clientSecret;
	@Value("#{systemEnvironment['SALESFORCE_REDIRECT_URI']}")
	private String redirectUri;
	@Value("#{systemEnvironment['CALIBER_PROJECT_URL']}")
	private String redirectUrl;
	@Value("services/oauth2/revoke")
	private String revokeUrl;

	private static final Logger log = Logger.getLogger(AuthorizationImpl.class);

	@Value("#{systemEnvironment['CALIBER_DEV_MODE']}")
	private boolean debug;
	private static final String REDIRECT = "redirect:";
	private static final String FORWARD = "forward::";
	private static final String REVATURE = "http://www.revature.com/";

	public AuthorizationImpl() {
		super();
	}

	/**
	 * Redirects the request to perform authentication.
	 * 
	 */
	@RequestMapping("/")
	public ModelAndView openAuthURI() {
		if (debug) {
			return new ModelAndView(REDIRECT + redirectUrl);
		}

		return new ModelAndView(REDIRECT + loginURL + authURL + "?response_type=code&client_id=" + clientId
				+ "&redirect_uri=" + redirectUri);
	}

	/**
	 * Retrieves Salesforce authentication token from Salesforce REST API
	 * 
	 * @param code
	 * @param servletResponse
	 */
	@RequestMapping("/authenticated")
	public ModelAndView generateSalesforceToken(@RequestParam(value = "code") String code,
			HttpServletResponse servletResponse, HttpServletRequest request) throws IOException {

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(loginURL + accessTokenURL);
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
		parameters.add(new BasicNameValuePair("client_secret", clientSecret));
		parameters.add(new BasicNameValuePair("client_id", clientId));
		parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
		parameters.add(new BasicNameValuePair("code", code));
		post.setEntity(new UrlEncodedFormEntity(parameters));
		log.info("Generating Salesforce token");
		HttpResponse response = httpClient.execute(post);
		request.setAttribute("salestoken", toJsonString(response.getEntity().getContent()));
		return new ModelAndView(FORWARD + redirectUrl);

	}

	/**
	 * Clears session information and logout the user.
	 * 
	 * Note: Still retrieving 302 on access-token and null refresh-token
	 * 
	 * @param auth
	 * @param session
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/revoke", method = RequestMethod.GET)
	public ModelAndView revoke(Authentication auth, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws IOException, ServletException {
		if (auth == null)
			return new ModelAndView(REDIRECT + REVATURE);
		if (!debug) {
			// revoke all tokens from the Salesforce
			String accessToken = ((SalesforceUser) auth.getPrincipal()).getSalesforceToken().getAccessToken();
			revokeToken(accessToken);
		}

		// logout and clear Spring Security Context
		servletRequest.logout();
		SecurityContextHolder.clearContext();

		log.info("User has logged out");
		return new ModelAndView(REDIRECT + REVATURE);
	}

	private void revokeToken(String token) throws ClientProtocolException, IOException {
		log.info("POST " + loginURL + revokeUrl);
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(loginURL + revokeUrl);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("token", token));
		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = httpClient.execute(post);
		log.info("Revoke token : " + response.getStatusLine().getStatusCode() + " "
				+ response.getStatusLine().getReasonPhrase());
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
}
