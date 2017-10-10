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
	
	
	public void update(Panel panel) {
		log.debug("Update panel: " + panel);
		panelDAO.update(panel);
	} 
	
	public void createPanel(Panel panel) {
		log.debug("Creating Panel " + panel);
		panelDAO.save(panel);
	}
	
	public void deletePanel(Panel panel) {
		log.debug("Deleting Panel " + panel);
		panelDAO.delete(panel);
	}
	
	

	public List<Panel> findAllPanels() {
		log.debug("Finding all panels");
		return panelDAO.findAll();
	}
	
	public List<Panel> findAllRepanel() {
		log.debug("Finding all panels");
		return panelDAO.findAllRepanel();
	}
	
	public Panel findById(int panelId) {
		log.info("Getting Panel with ID " + panelId);
		Panel panel = panelDAO.findOne(panelId);
		log.info("Got " + panel);
		return panel;
	}
	
	public List<Panel> findByTraineeId(int traineeId) {
		log.info("Getting Panels with trainee ID " + traineeId);
		return panelDAO.findAllByTrainee(traineeId);
	}
	
	/*
	 *******************************************************
	 * PANEL FEEDBACK SERVICES
	 *
	 *******************************************************
	 */

}
