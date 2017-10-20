package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.data.PanelFeedbackDAO;


/**
 * @author Connor Monson
 * @author Matt 'Spring Data' Prass
 */

@Service
public class PanelFeedbackService {

	private static final Logger log = Logger.getLogger(PanelFeedbackService.class);
	private PanelFeedbackDAO panelFeedDAO;

	@Autowired
	public void setPanelFeedbackDAO(PanelFeedbackDAO panelFeedDAO) {
		this.panelFeedDAO = panelFeedDAO;
	}

	/*
	 *******************************************************
	 *	PanelFeedback SERVICES
	 *
	 *******************************************************
	 */
	
	/**
	 * SAVE PanelFeedback
	 * @param PanelFeedback
	 */
	public void save(PanelFeedback panelFeedback) {
		log.debug("Saving PanelFeedback " + panelFeedback);
		panelFeedDAO.save(panelFeedback);
	}

	/**
	 * FIND PanelFeedback BY ID
	 * @param PanelFeedbackId
	 * @return
	 */
	public PanelFeedback findPanelFeedback(long panelFeedbackId) {
		log.debug("Finding one PanelFeedback " + panelFeedbackId);
		return panelFeedDAO.findOne(panelFeedbackId);
	}

	/**
	 * FIND ALL PanelFeedbackS
	 * @return
	 */
	public List<PanelFeedback> findAllPanelFeedbacks() {
		log.debug("Find all PanelFeedbacks");
		return panelFeedDAO.findAll();
	}
	
	/**
	 * UPDATE PanelFeedback
	 * @param PanelFeedback
	 */
	public PanelFeedback update(PanelFeedback panelFeedback) {
		log.info("Updating PanelFeedback " + panelFeedback);
		
		panelFeedDAO.update(panelFeedback);
		return panelFeedback;
	}

}
