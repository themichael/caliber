package com.revature.security.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("isAuthenticated()")
public class AuthorizedController {
	
	@RequestMapping("/authorize")
	public ModelAndView authorized(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
        String role ="";
        for(Cookie c : cookies)
        {
            if(c.getName().equals("role"))
            {
                role=c.getValue();
            }
        }
        
		//if(c.getValue!= )
        
        return new ModelAndView("forward: " + request.getRequestURI());
	}
}

