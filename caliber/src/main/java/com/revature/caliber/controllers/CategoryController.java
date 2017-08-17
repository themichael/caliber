package com.revature.caliber.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Category;
import com.revature.caliber.services.CategoryService;

@RestController
public class CategoryController {

	private static final Logger log = Logger.getLogger(CategoryController.class);
	private CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(value = "/category/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('VP')")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<Category>> findAllCategories() {
		log.debug("Getting categories");
		List<Category> categories = categoryService.findAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/vp/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP')")
	public ResponseEntity<List<Category>> findAll() {
		log.debug("Getting categories");
		List<Category> categories = categoryService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP')")
	public ResponseEntity<Category> findCategoryById(@PathVariable int id) {
		log.debug("Getting category: " + id);
		Category category = categoryService.findCategory(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/vp/category/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP')")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) {
		categoryService.updateCategory(category);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/vp/category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP')")
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
		categoryService.saveCategory(category);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}
}
