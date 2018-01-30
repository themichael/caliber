package com.revature.caliber.dto.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import com.revature.caliber.dto.beans.Trainer;
import com.revature.caliber.dto.beans.TrainerRole;

/**
 * Services requests for Trainer, Trainee, and Batch information
 *
 * @author Patrick Walsh
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class TrainingController {

	/*
	 *******************************************************

	 * LOCATION SERVICES
	 *
	 *******************************************************
	 */
	/**
	 * Create location
	 *
	 * @param location
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/cookieTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trainer> findTrainer(HttpServletRequest request, HttpServletResponse response) {

    Cookie [] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      System.out.println(cookie.getName());
         if ("role".equals(cookie.getName())) {
           System.out.println(cookie.getValue());
         }
    }
		Trainer trainer = new Trainer();
    trainer.setTrainerId(1);
    trainer.setName("Mehrab");
    trainer.setTitle("Boss");
    trainer.setEmail("mehrab@gmail.com");
    trainer.setTier(TrainerRole.ROLE_VP);
    response.addCookie(new Cookie("role", trainer.getTier().name()));
		return new ResponseEntity<>(trainer, HttpStatus.OK);
	}
}
