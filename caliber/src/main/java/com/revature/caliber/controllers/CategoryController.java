package com.revature.caliber.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Category;
import com.revature.caliber.security.models.SalesforceUser;
import com.revature.caliber.services.CategoryService;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private final static Logger log = Logger.getLogger(CategoryController.class);
	private CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * TODO :: read me:: 
	 * 	Access user details through SecurityContext by injecting Authentication into Controller method.
	 * 	Use @PreAuthorize with Spring Expression Language (SpEL) to send 403 forbidden 
	 * 		http://docs.spring.io/spring-security/site/docs/current/reference/html/el-access.html
	 * @param auth
	 * @return
	 */
	@RequestMapping(value="/category/all", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<List<Category>> findAll(Authentication auth){
		log.debug("Getting categories for trainer: " + ((SalesforceUser)auth).getCaliberId());
		List<Category> categories = categoryService.findAll();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
}
