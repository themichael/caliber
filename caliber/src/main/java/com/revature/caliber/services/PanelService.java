package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;
import com.revature.caliber.data.TraineeDAO;

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
	private PanelFeedbackDAO panelFeedbackDAO;
	private TraineeDAO traineeDAO;

	@Autowired
	public void setPanelDAO(PanelDAO panelDAO) {
		this.panelDAO = panelDAO;
	}
	
	@Autowired
	public void setTraineeDAO(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}
	
	@Autowired
	public void setPanelFeedBackDAO(PanelFeedbackDAO panelFeedbackDAO) {
		this.panelFeedbackDAO = panelFeedbackDAO;
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
	
	public List<Map<String, String>> getBatchPanels(Integer batchId) {
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		List<Panel> panels = new ArrayList<>();
		trainees.forEach(a -> panels.add(panelDAO.findAllByTrainee(a.getTraineeId()).get(0)));
		List<Map<String, String>> panelDto = utilAllTraineePanels(panels);
		return panelDto;
	}
	
	//Utility methods
	
	/**
	 * Takes a List of panels for a batch and returns a Map of labels with information
	 * needed for batch overall panel table (Trainee Name, Panel Status, Repanel Topics)
	 * @param panels
	 * @return
	 */
	private List<Map<String, String>> utilAllTraineePanels(List<Panel> panels) {
		Map<String, String> panelInfo;
		List<Map<String, String>> batchPanels = new ArrayList<>();
		for(Panel p : panels) {
			panelInfo = new HashMap<>();
			panelInfo.put("trainee", p.getTrainee().getName());
			String status = p.getStatus().toString();
			panelInfo.put("status", status);
			if(status.equalsIgnoreCase("Repanel")) {
				String topics = "";
				for(PanelFeedback pf: panelFeedbackDAO.findFailedFeedbackByPanel(p)) {
						if(topics.length()>0) {topics += ", ";}
						topics += pf.getTechnology().getSkillCategory();
				}
				panelInfo.put("topics", topics);
			}
			batchPanels.add(panelInfo);
		}
		return batchPanels;
	}
	
	
}
	

