package com.revature.security.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class AuthorizedController {
	
	@RequestMapping("/authorize")
	public ModelAndView authorized(HttpServletRequest request) {
    String preRedirect = request.getHeader("preRedirectRequestUri");
    System.out.println(preRedirect);
		Cookie[] cookies = request.getCookies();
        String role ="";
        if(cookies!=null) {
        for (Cookie c : cookies)
          {
            if(c.getName().equals("role"))
            {
                role=c.getValue();
            }
          }
        }
        
        if(preRedirect.contains("dto/")) {
          return new ModelAndView("redirect:" + preRedirect);
        }
        if(role.equals("")) {
          return new ModelAndView("forward:" + "/");
        }
        if(preRedirect == null) {
          return new ModelAndView("forward" + "/");
        }
        return new ModelAndView("redirect:" + preRedirect); 
    }
}

