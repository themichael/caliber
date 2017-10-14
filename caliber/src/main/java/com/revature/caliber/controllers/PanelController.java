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
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING','PANEL')")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<Panel>> findAll() {
		log.debug("Getting all feedback");
		List<Panel> feedback = panelService.findAllPanels();
		HttpStatus status = (feedback.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<>(feedback, status);
	}
	
	@RequestMapping(value = "/panel/{panelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING','PANEL')")
	public ResponseEntity<Panel> findPanelById(@PathVariable int panelId) {
		log.debug("Getting category: " + panelId);
		Panel panel = panelService.findById(panelId);
		HttpStatus status = (panel == null) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		log.info(panel);
		return new ResponseEntity<>(panel, status);
	}
	
	@RequestMapping(value = "/panel/trainee/{traineeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING','PANEL')")
	public ResponseEntity<List<Panel>> findPanelByTraineeId(@PathVariable int traineeId) {
		log.debug("Getting category: " + traineeId);
		List<Panel> panels = panelService.findByTraineeId(traineeId);
		HttpStatus status = (panels.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		log.info(panels);
		return new ResponseEntity<>(panels, status);
	}
	
	@RequestMapping(value = "/panel/repanel/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING','PANEL')")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<Panel>> findAllRepanel() {
		log.debug("Getting all panels with repanel");
		List<Panel> feedback = panelService.findAllRepanel();
		HttpStatus status = (feedback.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
		return new ResponseEntity<>(feedback, status);
	}

	@RequestMapping(value = "/panel/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP','PANEL')")
	public ResponseEntity<Panel> updatePanel(@Valid @RequestBody Panel panel) {
		panelService.update(panel);
		return new ResponseEntity<>(panel, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/panel/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP','PANEL')")
	public ResponseEntity<Panel> saveFeedback(@Valid @RequestBody Panel panelf) {
		panelService.createPanel((panelf));
		return new ResponseEntity<>(panelf, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/panel/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'TRAINER','PANEL')")
	public ResponseEntity<Void> deleteAssessment(@PathVariable Panel p) {
		log.info("Deleting panel: " + p);
		panelService.deletePanel(p);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
