package com.revature.caliber.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Panel;
import com.revature.caliber.services.PanelService;

/**
 * @author Connor Monson
 */
@RestController
@PreAuthorize("isAuthenticated()")
public class PanelController {
	
	private static final Logger log = Logger.getLogger(PanelController.class);

	@Autowired
	private PanelService panelService;

	public void setSalesforceService(PanelService panelService) {
		this.panelService = panelService;
	}
	
	// FIXME change the roles
	
	@RequestMapping(value = "/panel/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING')")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<Panel>> findAll() {
		log.debug("Getting all feedback");
		List<Panel> feedback = panelService.findAllPanels();
		return new ResponseEntity<>(feedback, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/panel/{panelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING')")
	public ResponseEntity<Panel> findPanelById(@PathVariable int panelId) {
		log.debug("Getting category: " + panelId);
		Panel panel = panelService.findById(panelId);
		log.info(panel.toString());
		return new ResponseEntity<>(panel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/panel/trainee/{traineeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING')")
	public ResponseEntity<List<Panel>> findPanelByTraineeId(@PathVariable int traineeId) {
		log.debug("Getting category: " + traineeId);
		List<Panel> panels = panelService.findByTraineeId(traineeId);
		log.info(panels.toString());
		return new ResponseEntity<>(panels, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/panel/repanel/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING')")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<Panel>> findAllRepanel() {
		log.debug("Getting all panels with repanel");
		List<Panel> feedback = panelService.findAllRepanel();
		return new ResponseEntity<>(feedback, HttpStatus.OK);
	}
	
	/* UNDER TESTING
	
	@RequestMapping(value = "/panel/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP')")
	public ResponseEntity<Panel> updateFeedback(@Valid @RequestBody Panel panelf) {
		panelService.update(panelf);
		return new ResponseEntity<>(panelf, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/panel/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasRole('VP')")
	public ResponseEntity<Panel> saveFeedback(@Valid @RequestBody Panel panelf) {
		panelService.save(panelf);
		return new ResponseEntity<>(panelf, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/panel/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'TRAINER')")
	public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
		log.info("Deleting assessment: " + id);
		Panel panel = new Panel();
		panel.setId(id);
		panelService.delete(panel);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	*/

}
