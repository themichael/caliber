package com.revature.caliber.salesforce.controllers;

import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Martino on 1/25/2017.
 */
public interface Salesforce {

    ModelAndView openAuthURI();
    void authenticate(String code) throws IOException;
}
