package com.revature.caliber.salesforce.interfaces;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Martino on 1/25/2017.
 */
public interface Authorization {

    /**
     * Generates authURI with provided parameters
     * @return view of the generated URI
     */
    ModelAndView openAuthURI();

    /**
     * Creates salesforce token in memory
     * @param code the string returned from the authURI required for getting token from salesforce
     * @throws IOException
     */
    String generateSalesforceToken(String code, HttpServletResponse httpServletResponse) throws IOException;
}
