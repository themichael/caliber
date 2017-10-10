package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.data.PanelFeedbackDAO;

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
	public void save(PanelFeedback panelf) {
		log.debug("Saving PanelFeedback " + panelf);
		panelFeedDAO.save(panelf);
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
	public PanelFeedback update(PanelFeedback panelf) {
		log.info("Updating PanelFeedback " + panelf);
		
		panelFeedDAO.update(panelf);
		return panelf;
	}

	/**
	 * DELETE PanelFeedback
	 * @param PanelFeedback
	 */
	public void delete(PanelFeedback panelf) {
		log.debug("Deleting PanelFeedback " + panelf);
		//load PanelFeedback into persistent state
		PanelFeedback record = panelFeedDAO.findOne(panelf.getId());
		panelFeedDAO.delete(record);
	}
}
