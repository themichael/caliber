package com.revature.caliber.security;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView generateSalesforceToken(@RequestParam(value = "code") String code,
			RedirectAttributes redirectAttributes, Model model) throws IOException;
}
