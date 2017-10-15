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

import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.services.PanelFeedbackService;

@RestController
@PreAuthorize("isAuthenticated()")
public class PanelFeedbackController {

	private static final Logger log = Logger.getLogger(PanelFeedbackController.class);
	private PanelFeedbackService panelFeedbackService;

	@Autowired
	public void setPanelFeedbackService(PanelFeedbackService panelFeedbackService) {
		this.panelFeedbackService = panelFeedbackService;
	}

	@RequestMapping(value = "/panelfeedback/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING','PANEL')")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<PanelFeedback>> findAll() {
		log.debug("Getting all feedback");
		List<PanelFeedback> allFeedbacks = panelFeedbackService.findAllPanelFeedbacks();
		HttpStatus status = allFeedbacks.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<>(allFeedbacks, status);
	}

	@RequestMapping(value = "/panelfeedback/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING','PANEL')")
	public ResponseEntity<PanelFeedback> findPanelFeedbackById(@PathVariable long id) {
		log.debug("Getting category: " + id);
		PanelFeedback panelFeedback = panelFeedbackService.findPanelFeedback(id);
		log.info(panelFeedback);
		if (panelFeedback == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(panelFeedback, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/panelfeedback/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP', 'PANEL')")
	public ResponseEntity<PanelFeedback> updateFeedback(@Valid @RequestBody PanelFeedback panelFeedback) {
		panelFeedbackService.update(panelFeedback);
		return new ResponseEntity<>(panelFeedback, HttpStatus.OK);
	}

	@RequestMapping(value = "/panelfeedback/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP', 'PANEL')")
	public ResponseEntity<PanelFeedback> saveFeedback(@Valid @RequestBody PanelFeedback panelFeedback) {
		panelFeedbackService.save(panelFeedback);
		return new ResponseEntity<>(panelFeedback, HttpStatus.CREATED);
	}

}
