package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Category;
import com.revature.caliber.model.Note;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.Panel;
import com.revature.caliber.model.PanelFeedback;
import com.revature.caliber.model.PanelStatus;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.model.SimplePanel;
import com.revature.caliber.model.SimplePanelFeedback;
import com.revature.caliber.model.Trainee;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.repository.PanelFeedbackRepository;


public class PanelFeedbackCompositionService {

	@Autowired
	private PanelFeedbackRepository panelFeedbackRepository;
	@Autowired
	private PanelFeedbackCompositionMessagingService panelFeedbackCompositionMessagingService;
	
	/**. 
	 * Finds one simplified bean from the service database and
	 * composes it into a complex bean required by the front end.
	 *
	 * @param long the id of a Panel Feedback
	 * @return A PanelFeedback object
	 */
	public PanelFeedback findOne(Long panelFeedbackId) {
		SimplePanelFeedback basis = panelFeedbackRepository.findOne(panelFeedbackId);
		PanelFeedback result = composePanelFeedback(basis);
		
		return result;
	}
	
	/**. 
	 * Finds all simplified beans from the service database and
	 * composes them into complex beans required by the front end.
	 *
	 *
	 * @param long the id of a Panel Feedback
	 * @return A List of PanelFeedback objects
	 */
	public List<PanelFeedback> findAll() {
		List<SimplePanelFeedback> basis = panelFeedbackRepository.findAll();
		List<PanelFeedback> result = composeListOfPanelFeedback(basis);
		
		return result;
	}
	
	/**. 
	 * Finds all children Panel Feedbacks for a given Panel
	 *
	 * @param int the id of a Panel
	 * @return A List of Panel Feedback objects
	 */
	public List<PanelFeedback> findAllForPanel(int panelId) {
		List<SimplePanelFeedback> basis = panelFeedbackRepository.findByPanelId(panelId);
		List<PanelFeedback> result = composeListOfPanelFeedback(basis);
		
		return result;
	}
	
	/**. 
	 * Finds all child Panel Feedbacks for a given Panel
	 * where the panel status is Repanel.
	 *
	 * @param int the id of a Panel 
	 * @return A List of Panel Feedback objects
	 */
	public List<PanelFeedback> findFailedFeedbackByPanel(int panelId) {
		List<SimplePanelFeedback> basis = panelFeedbackRepository.findByPanelIdAndStatus(panelId, PanelStatus.Repanel);
		List<PanelFeedback> result = composeListOfPanelFeedback(basis);
		
		return result;
	}
	
	/**. 
	 * Given a Panel Feedback create a simple version
	 * and save it
	 *
	 * @param A Panel Feedback object to save
	 */
	public void save(PanelFeedback panelFeedback) {
		SimplePanelFeedback toSave = new SimplePanelFeedback(panelFeedback);
		panelFeedbackRepository.save(toSave);
	}

	/**. 
	 * Given a Panel Feedback create a simple version
	 * and use it to update
	 *
	 * @param A Panel Feedback object to update
	 */
	public void update(PanelFeedback panelFeedback) {
		SimplePanelFeedback toUpdate = new SimplePanelFeedback(panelFeedback);
		panelFeedbackRepository.save(toUpdate);
	}
	
	/**. 
	 * Delete a Panel Feedback.
	 *
	 * @param long the id of a Panel Feedback
	 */
	public void delete(long panelFeedbackId) {
		 panelFeedbackRepository.delete(panelFeedbackId);
	 }
	
	/**. 
	 * Cascading delete, if a panel is deleted all the children Panel Feedbacks
	 * should be removed as well.
	 *
	 * @param int the id of a Panel that was deleted
	 */
	 public void delete(int panelId) {
		 panelFeedbackRepository.deleteByPanelId(panelId);
	 }
	 
	/**
	 * Composes a list of simplified Panel Feedbacks into
	 * the complex bean required by the front end. 
	 * 
	 * @param A List of Simple Panel Feedback objects
	 * @return A List of Panel Feedback objects
	 */
	private List<PanelFeedback> composeListOfPanelFeedback(List<SimplePanelFeedback> src) {
		List<PanelFeedback> dest = new LinkedList<PanelFeedback>();
		
		for(SimplePanelFeedback curr : src) {
			PanelFeedback panelFeedback = new PanelFeedback(curr);
			dest.add(panelFeedback);
		}
		
		return dest;
	}
	
	/**
	 * Composes a simplified Panel Feedback into
	 * the complex bean required by the front end.
	 * 
	 * This process requires contacting the Category and Panel service
	 * to get those dependent objects.
	 * 
	 * @param A Simple Panel Feedback object
	 * @return A Panel Feedback object
	 */
	private PanelFeedback composePanelFeedback(SimplePanelFeedback src) {
		SimpleCategory simpleCategory = panelFeedbackCompositionMessagingService.sendSingleSimpleCategoryRequest(src.getCategoryId());
		SimplePanel simplePanel = panelFeedbackCompositionMessagingService.sendSingleSimplePanelRequest(src.getPanelId());
		Category category = new Category(simpleCategory);
		Panel panel = new Panel(simplePanel);
		PanelFeedback dest = new PanelFeedback(src);
		
		dest.setTechnology(category);
		dest.setPanel(panel);
		
		return dest;
	}
}
