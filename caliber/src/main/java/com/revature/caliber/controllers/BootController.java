
package com.revature.caliber.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.exceptions.NotAuthorizedException;
import com.revature.caliber.security.impl.Helper;
import com.revature.caliber.security.models.SalesforceToken;
import com.revature.caliber.security.models.SalesforceUser;

/**
 * The type Boot controller.
 */
@Controller
@SessionAttributes("token")
public class BootController extends Helper {

	private static final Logger log = Logger.getLogger(BootController.class);

	@Value("#{systemEnvironment['CALIBER_DEV_MODE']}")
	private boolean debug;
	private static final String DEBUG_USER_LOGIN = "patrick.walsh@revature.com";

	/**
	 * Instantiates a new Boot controller.
	 */
	public BootController() {
		super();
	}

	/**
	 * Gathers Salesforce user data, authorizes user to access Caliber. Forwards
	 * to the landing page according to the user's role.
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
		if (debug) {
			// fake Salesforce User
			SalesforceUser salesforceUser = new SalesforceUser();
			salesforceUser.setEmail(DEBUG_USER_LOGIN);
			String email = salesforceUser.getEmail();

			// Http request to the training module to get the caliber user
			String jsonString = getCaliberTrainer(servletRequest, email);

			// authorize user
			authorize(jsonString, salesforceUser, servletResponse);
			return "index";
		}

		// get Salesforce token from cookie
		try {
			SalesforceToken salesforceToken = getSalesforceToken(servletRequest);
			// Http request to the salesforce module to get the Salesforce user
			SalesforceUser salesforceUser = getSalesforceUserDetails(servletRequest, salesforceToken);
			String email = salesforceUser.getEmail();

			// Http request to the training module to get the caliber user
			String jsonString = getCaliberTrainer(servletRequest, email);

			// authorize user
			authorize(jsonString, salesforceUser, servletResponse);
			return "index";
		} catch (AuthenticationCredentialsNotFoundException e) {
			log.debug(e);
			return "redirect:/";
		}
	}

	/**
	 * Retrieve the salesforce access_token from the provided cookie
	 * 
	 * @param servletRequest
	 * @return
	 * @throws IOException
	 */
	private SalesforceToken getSalesforceToken(HttpServletRequest servletRequest) throws IOException {

		HttpSession session = servletRequest.getSession();
		if(session!=null){
			String token = (String) session.getAttribute("salestoken");
			log.debug("Parse salesforce token from HttpSession: " + token);
			return new ObjectMapper().readValue(token,SalesforceToken.class);
		}
//		Cookie[] cookies = servletRequest.getCookies();
//		for (Cookie cookie : cookies) {
//			if (("token").equals(cookie.getName())) {
//				log.debug("Parse salesforce token: " + cookie.getValue());
//				return new ObjectMapper().readValue(URLDecoder.decode(cookie.getValue(), "UTF-8"),
//						SalesforceToken.class);
//			}
//		}
		throw new AuthenticationCredentialsNotFoundException("Salesforce token expired.");
	}

	/**
	 * Makes a request to Salesforce REST API to retrieve the authenticated
	 * user's details
	 * 
	 * @param servletRequest
	 * @param salesforceToken
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private SalesforceUser getSalesforceUserDetails(HttpServletRequest servletRequest, SalesforceToken salesforceToken)
			throws IOException, URISyntaxException {
		HttpClient httpClient = HttpClientBuilder.create().build();
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
		return salesforceUser;
	}

	/**
	 * Gets Caliber user from database (TRAINER table) and validates if provided
	 * email is authorized to user Caliber. All authorized Caliber users must
	 * exist as a TRAINER record with email matching that of Salesforce user
	 * email.
	 * 
	 * @param servletRequest
	 * @param email
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private String getCaliberTrainer(HttpServletRequest servletRequest, String email)
			throws URISyntaxException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
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
		return jsonString;
	}

	/**
	 * Parses a Json String containing TRAINER bean. Authorize the user with
	 * Caliber and store their PreAuthenticatedAuthenticationToken in session.
	 * Adds convenience 'role' cookie for AngularJS consumption.
	 * 
	 * @param jsonString
	 * @param salesforceUser
	 * @param servletResponse
	 * @throws IOException
	 */
	private void authorize(String jsonString, SalesforceUser salesforceUser, HttpServletResponse servletResponse)
			throws IOException {
		JSONObject jsonObject = new JSONObject(jsonString);
		if (jsonObject.getString("email").equals(salesforceUser.getEmail())) {
			log.info("Logged in user " + jsonObject.getString("email") + " now hasRole: "
					+ jsonObject.getString("tier"));
			salesforceUser.setRole(jsonObject.getString("tier"));
			salesforceUser.setCaliberUser(new ObjectMapper().readValue(jsonString, Trainer.class));
			
			// check if user is active
			if(salesforceUser.getCaliberUser().getTier().equals(TrainerRole.ROLE_INACTIVE))
				throw new NotAuthorizedException();
		} else {
			throw new NotAuthorizedException();
		}
		// store custom user Authentication obj in SecurityContext
		Authentication auth = new PreAuthenticatedAuthenticationToken(salesforceUser, salesforceUser.getUserId(),
				salesforceUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		servletResponse.addCookie(new Cookie("role", jsonObject.getString("tier")));
	}

}
