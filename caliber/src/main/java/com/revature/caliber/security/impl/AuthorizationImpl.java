package com.revature.caliber.security.impl;

import com.revature.caliber.security.Authorization;
import com.revature.caliber.security.models.SalesforceUser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by louislopez on 1/18/17.
 */

@Controller
@Scope("prototype")
public class AuthorizationImpl extends Helper implements Authorization {
	@Value("https://login.salesforce.com/services/oauth2/authorize")
	private String authURL;
	@Value("https://login.salesforce.com/services/oauth2/token")
	private String accessTokenURL;
	@Value("#{systemEnvironment['SALESFORCE_CLIENT_ID']}")
	private String clientId;
	@Value("#{systemEnvironment['SALESFORCE_CLIENT_SECRET']}")
	private String clientSecret;
	@Value("#{systemEnvironment['SALESFORCE_REDIRECT_URI']}")
	private String redirectUri;
	@Value("#{systemEnvironment['CALIBER_PROJECT_URL']}")
	private String redirectUrl;
	@Value("https://login.salesforce.com/services/oauth2/revoke")
	private String revokeUrl;

	private HttpClient httpClient;
	private HttpResponse response;
	private static final Logger log = Logger.getLogger(AuthorizationImpl.class);

	public AuthorizationImpl() {
		httpClient = HttpClientBuilder.create().build();
	}

	/**
	 * ------------------------DEVELOPMENT ONLY------------------------ Pretends
	 * to redirect to Salesforce for authentication.
	 * 
	 * TODO remove @RequestMapping at go-live
	 */
	//@RequestMapping("/")
	public ModelAndView dummyAuth() {
		return new ModelAndView("redirect:" + redirectUrl);
	}

	/**
	 * ------------------------PRODUCTION ONLY------------------------ Redirects
	 * to Salesforce for authentication.
	 * 
	 * TODO enable at go-live
	 */
	@RequestMapping("/")
	public ModelAndView openAuthURI() {
		return new ModelAndView(
				"redirect:" + authURL + "?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri);
	}

	/**
	 * ------------------------PRODUCTION ONLY------------------------ Retrieves
	 * Salesforce authentication token.
	 * 
	 */
	@RequestMapping("/authenticated")
	public ModelAndView generateSalesforceToken(@RequestParam(value = "code") String code,
			HttpServletResponse servletResponse) throws IOException {
		HttpPost post = new HttpPost(accessTokenURL);
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
		parameters.add(new BasicNameValuePair("client_secret", clientSecret));
		parameters.add(new BasicNameValuePair("client_id", clientId));
		parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
		parameters.add(new BasicNameValuePair("code", code));
		post.setEntity(new UrlEncodedFormEntity(parameters));
		log.info("Generating Salesforce token");
		response = httpClient.execute(post);
		String token = URLEncoder.encode(toJsonString(response.getEntity().getContent()), "UTF-8");
		servletResponse.addCookie(new Cookie("token", token));
		return new ModelAndView("redirect:" + redirectUrl);
	}

	/**
	 * Needs further testing and experimentation to revoke all tokens and logout
	 * of connected app
	 * 
	 * @param auth
	 * @param session
	 * @return
	 * @throws IOException
	 */
	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView revoke(Authentication auth, HttpSession session) throws IOException {
		if (auth != null) {
			String token = ((SalesforceUser) auth.getPrincipal()).getSalesforceToken().getRefreshToken();
			log.info("Revoking token: " + token);
			HttpPost post = new HttpPost(revokeUrl);
			List<NameValuePair> parameters = new ArrayList<>();
			parameters.add(new BasicNameValuePair("token", token));
			post.setEntity(new UrlEncodedFormEntity(parameters));
			httpClient.execute(post);
		}
		session.invalidate();
		return new ModelAndView("redirect:revoke");
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
