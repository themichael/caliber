package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Address;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.PanelDAO;

/**
 * Provides logic concerning Panel and PanelFeedback data. Application logic
 * has no business being in a DAO nor in a Controller. This is the ideal place
 * for calculations
 *
 * @author Connor Monson
 * @author Matt Prass
 *
 */
@Service
public class PanelService {
	
	private static final Logger log = Logger.getLogger(PanelService.class);
	private PanelDAO panelDAO;

	@Autowired
	public void setPanelDAO(PanelDAO panelDAO) {
		this.panelDAO = panelDAO;
	}
	
	/*
	 *******************************************************
	 * PANEL SERVICES
	 *
	 *******************************************************
	 */
	
	/* UNDER TESTING
	public void update(Panel panel) {
		log.debug("Update panel: " + panel);
		panelDAO.update(panel);
	} 
	
	public void createPanel(Panel panel) {
		log.debug("Creating Panel " + panel);
		panelDAO.save(panel);
		;
	}
	
	*/

	public List<Panel> findAllPanels() {
		log.debug("Finding all panels");
		return panelDAO.findAll();
	}
	
	public Panel findById(int traineeId) {
		log.info("Getting Panel with ID " + traineeId);
		Panel panel = panelDAO.findOne(traineeId);
		log.info("Got " + panel);
		return panel;
	}
	
	/*
	 *******************************************************
	 * PANEL FEEDBACK SERVICES
	 *
	 *******************************************************
	 */

}
