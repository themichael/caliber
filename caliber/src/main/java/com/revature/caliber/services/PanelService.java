package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.data.PanelDAO;
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
	private TraineeDAO traineeDAO;
	
	@Autowired
	public void setPanelDAO(PanelDAO panelDAO) {
		this.panelDAO = panelDAO;
	}
	
	@Autowired
	public void setTraineeDAO(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
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
	
	public void deletePanel(int panelId) {
		log.debug("Deleting Panel " + panelId);
		panelDAO.delete(panelId);
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
	
	/**
	 * FIND ALL PANELS WHERE THE TRAINEE'S LAST PANEL HAD STATUS REPANEL
	 */
	public List<Panel> findAllRecentRepanel() {
		log.debug("Find all trainees whose last panel had status Repanel");
		List<Trainee> trainees = traineeDAO.findAll();
		List<Panel> result = new ArrayList<>();
		for (Trainee t : trainees) {
			if ((t.getTrainingStatus() != TrainingStatus.Dropped)) {
				List<Panel> panels = panelDAO.findAllByTrainee(t.getTraineeId());
				if (panels != null && !panels.isEmpty()) {
					Panel p = mostRecentPanel(panels);
					if (p.getStatus() == PanelStatus.Repanel) {
						result.add(p);
						continue;
					}
				}
			}
		}
		return result;
	}
	
	private Panel mostRecentPanel(List<Panel> panels) {
		int max = -1;
		Panel result = null;
		for (Panel p : panels)
			if (max < p.getPanelRound()) {
				max = p.getPanelRound();
				result = p;
			}
		return result;
	}
}
