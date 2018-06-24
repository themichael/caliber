package com.revature.caliber.security.impl;

import java.security.InvalidParameterException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.exceptions.NotAuthorizedException;
import com.revature.caliber.security.models.SalesforceUser;

@Controller
public class BasicBootController {

	private static final Logger log = Logger.getLogger(BasicBootController.class);

	@Value("#{systemEnvironment['CALIBER_PROJECT_URL']}")
	private String redirectUrl;

	@Autowired
	private CaliberUserService caliberUserService;

	@RequestMapping(value = "/")
	public String home() {
		return "login";
	}

	@RequestMapping(value = "/authenticated", method = RequestMethod.POST)
	public String authenticate(HttpServletRequest request, HttpServletResponse response) {
		SalesforceUser user = (SalesforceUser) caliberUserService.loadUserByUsername(request.getParameter("username"));
		String rawPassword = request.getParameter("pw");
		if(rawPassword == null || rawPassword.equals("")) {
			return "redirect:/";
		}
		if (user.getCaliberUser().getPassword() == null || user.getCaliberUser().getPassword().equals("")) {
			// 1st time login.. user has not set password yet
			user.getCaliberUser().setPassword(rawPassword);
			caliberUserService.saltAndSave(user.getCaliberUser());
		}
		try {
			authorize(user.getCaliberUser(), user, rawPassword, response);
		}catch(InvalidParameterException e) {
			return "redirect:/";
		}
		return "redirect:" + redirectUrl;
	}

	@RequestMapping(value = "/caliber", method = RequestMethod.GET)
	public String devHomePage(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}

	private void authorize(Trainer trainer, SalesforceUser user, String password, HttpServletResponse servletResponse) {
		// check password hashes
		if(!caliberUserService.checkPassword(password, trainer.getPassword())) {
			// invalid password
			throw new InvalidParameterException();
		}
		log.debug("Logged in user " + user.getEmail() + " now hasRole: " + user.getRole());
		// check if user is active
		if (user.getCaliberUser().getTier().equals(TrainerRole.ROLE_INACTIVE))
			throw new NotAuthorizedException();
		// store custom user Authentication obj in SecurityContext
		Authentication auth = new PreAuthenticatedAuthenticationToken(user, user.getUserId(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		servletResponse.addCookie(new Cookie("role", trainer.getTier().toString()));
		servletResponse.addCookie(new Cookie("id", Integer.toString(user.getCaliberUser().getTrainerId())));
	}

}
