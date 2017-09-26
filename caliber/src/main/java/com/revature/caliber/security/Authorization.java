package com.revature.caliber.security;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Martino on 1/25/2017.
 */
public interface Authorization {

	/**
	 * Generates authURI with provided parameters
	 * 
	 * @return view of the generated URI
	 */
	public ModelAndView openAuthURI();

	/**
	 * Creates salesforce token and saves it as a session cookie
	 * 
	 * @param code
	 *            the string returned from the authURI required for getting
	 *            token from salesforce
	 * @param
	 * @return back to the application
	 * @throws IOException
	 */
	public void generateSalesforceToken(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "code") String code) throws IOException, URISyntaxException;
}
