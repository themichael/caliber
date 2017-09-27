package com.revature.caliber.controllers;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.caliber.security.impl.AbstractSalesforceSecurityHelper;
/**
 * The type Boot controller.
 */
@Controller
public class BootController extends AbstractSalesforceSecurityHelper {
    private static final Logger log = Logger.getLogger(BootController.class);
    private static final String INDEX = "index";
    /**
     * Instantiates a new Boot controller.
     */
    public BootController() {
        super();
    }
    /**
     * Gathers Salesforce user data, authorizes user to access Caliber. Forwards to
     * the landing page according to the user's role.
     */
    @RequestMapping(value = "/caliber")
    public String devHomePage(){
        log.info("Returning index");
        return INDEX;
    }
}