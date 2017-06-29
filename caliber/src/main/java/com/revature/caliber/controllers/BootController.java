package com.revature.caliber.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.SalesforceDAO;
import com.revature.caliber.security.impl.Helper;
import com.revature.caliber.exceptions.NotAuthorizedException;
import com.revature.caliber.exceptions.ServiceNotAvailableException;
import com.revature.caliber.security.models.SalesforceToken;
import com.revature.caliber.security.models.SalesforceUser;

/**
 * The type Boot controller.
 */
@Controller
@SessionAttributes("token")
public class BootController extends Helper {

	private final static Logger log = Logger.getLogger(BootController.class);

	/**
	 * Instantiates a new Boot controller.
	 */
	public BootController() {

	}

	/**
	 * ------------------------DEVELOPMENT ONLY------------------------ Pretends
	 * to do login. Defaults to login pjw6193@hotmail.com
	 * 
	 * Forwards to the landing page.
	 *
	 * TODO remove @RequestMapping at go-live
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @param servletResponse
	 *            the servlet response
	 * @return the home page
	 * @throws IOException
	 *             the io exception
	 * @throws URISyntaxException
	 *             the uri syntax exception
	 */
	@RequestMapping(value = "/caliber")
	public String devHomePage(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws IOException, URISyntaxException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		// fake Salesforce User
		SalesforceUser salesforceUser = new SalesforceUser();
		salesforceUser.setEmail("pjw6193@hotmail.com");
		// Http request to the training module to get the caliber user
		String email = salesforceUser.getEmail();
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(servletRequest.getScheme()).setHost(servletRequest.getServerName())
				.setPort(servletRequest.getServerPort()).setPath("/training/trainer/byemail/" + email + "/");
		URI uri = uriBuilder.build();
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = httpClient.execute(httpGet);
		String jsonString = toJsonString(response.getEntity().getContent());
		// check if we actually got back JSON object from the Salesforce
		if (!jsonString.contains(email)) {
			log.fatal("Training API returned: " + jsonString);
			throw new NotAuthorizedException();
		}
		log.info(jsonString);
		JSONObject jsonObject = new JSONObject(jsonString);
		if (jsonObject.getString("email").equals(salesforceUser.getEmail())) {
			log.info("Logged in user " + jsonObject.getString("email") + " now hasRole: "
					+ jsonObject.getString("tier"));
			salesforceUser.setRole(jsonObject.getString("tier"));
			salesforceUser.setCaliberUser(new ObjectMapper().readValue(jsonString, Trainer.class));
		} else {
			throw new NotAuthorizedException();
		}
		// store custom user Authentication obj in SecurityContext
		Authentication auth = new PreAuthenticatedAuthenticationToken(salesforceUser, salesforceUser.getUser_id(),
				salesforceUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		servletResponse.addCookie(new Cookie("role", jsonObject.getString("tier")));
		return "index";
	}

	/**
	 * ------------------------PRODUCTION ONLY------------------------
	 * Salesforce authentication controller (AuthenticationImpl) forwards the
	 * OAuth token to this controller method to login into the Caliber
	 * applications. Adds the SalesforceUser to the Security Context.
	 *
	 * Forwards to the landing page.
	 *
	 * TODO enable at go-live
	 *
	 * @param servletRequest
	 *            the servlet request
	 * @param servletResponse
	 *            the servlet response
	 * @return the home page
	 * @throws IOException
	 *             the io exception
	 * @throws URISyntaxException
	 *             the uri syntax exception
	 */
	// @RequestMapping(value = "/caliber")
	public String getHomePage(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws IOException, URISyntaxException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		// get Salesforce token from cookie
		Cookie[] cookies = servletRequest.getCookies();
		SalesforceToken salesforceToken = null;
		for (Cookie cookie : cookies) {
			if (("token").equals(cookie.getName())) {
				log.debug("Parse salesforce token: " + cookie.getValue());
				salesforceToken = new ObjectMapper().readValue(URLDecoder.decode(cookie.getValue(), "UTF-8"),
						SalesforceToken.class);
				break;
			}
		}
		if (salesforceToken == null)
			throw new ServiceNotAvailableException();
		// Http request to the salesforce module to get the salesforce user
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(servletRequest.getScheme()).setHost(servletRequest.getServerName())
				.setPort(servletRequest.getServerPort()).setPath("/getSalesforceUser/")
				.setParameter("endpoint", salesforceToken.getId())
				.setParameter("accessToken", salesforceToken.getAccessToken());
		URI uri = uriBuilder.build();
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = httpClient.execute(httpGet);
		String user = toJsonString(response.getEntity().getContent());
		SalesforceUser salesforceUser = new ObjectMapper().readValue(user, SalesforceUser.class);
		salesforceUser.setSalesforceToken(salesforceToken);

		// Http request to the training module to get the caliber user
		String email = salesforceUser.getEmail();
		uriBuilder = new URIBuilder();
		uriBuilder.setScheme(servletRequest.getScheme()).setHost(servletRequest.getServerName())
				.setPort(servletRequest.getServerPort()).setPath("/training/trainer/byemail/" + email + "/");
		uri = uriBuilder.build();
		httpGet = new HttpGet(uri);
		response = httpClient.execute(httpGet);
		String jsonString = toJsonString(response.getEntity().getContent());
		// check if we actually got back JSON object from the Salesforce
		if (!jsonString.contains(email)) {
			log.fatal("Training API returned: " + jsonString);
			throw new NotAuthorizedException();
		}
		JSONObject jsonObject = new JSONObject(jsonString);
		if (jsonObject.getString("email").equals(salesforceUser.getEmail())) {
			log.info("Logged in user " + jsonObject.getString("email") + " now hasRole: "
					+ jsonObject.getString("tier"));
			salesforceUser.setRole(jsonObject.getString("tier"));
			salesforceUser.setCaliberUser(new ObjectMapper().readValue(jsonString, Trainer.class));
		} else {
			throw new NotAuthorizedException();
		}
		// store custom user Authentication obj in SecurityContext
		Authentication auth = new PreAuthenticatedAuthenticationToken(salesforceUser, salesforceUser.getUser_id(),
				salesforceUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		servletResponse.addCookie(new Cookie("role", jsonObject.getString("tier")));
		return "index";
	}

}
