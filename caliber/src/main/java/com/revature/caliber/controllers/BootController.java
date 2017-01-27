package com.revature.caliber.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The type Boot controller.
 */
@Controller
public class BootController {
    /**
     * Home page string.
     *
     * @return the string
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }
}
