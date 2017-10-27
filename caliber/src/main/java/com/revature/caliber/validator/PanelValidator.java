package com.revature.caliber.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.beans.PanelStatus;
import com.revature.caliber.services.PanelService;

@Component
public class PanelValidator implements ConstraintValidator<ValidPanel, Panel>{

	private static final Logger log = Logger.getLogger(PanelValidator.class);
	
	@Autowired
	private PanelService panelService;

	@Override
	public void initialize(ValidPanel constraintAnnotation) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
	}

	@Override
	public boolean isValid(Panel panel, ConstraintValidatorContext context) {
		log.info("Panel for Trainee: " +panel.getTrainee());
		log.info("Panel Service Bean: " + panelService);
		List<Panel> previousPanels = panelService.findByTraineeId(panel.getTrainee().getTraineeId());
		log.info("Trainee's Previous Panels: " + previousPanels);
		//verifying server side that the panel round field has not been tampered with
		if(previousPanels.size()+1 != panel.getPanelRound()) {
			log.warn("Failed to create panel. Panel round calculation incorrect.");
			return false;
		}
		
		//verifying server side that the overall panel status field has not been tampered with
		boolean pass = true;
		if(panel.getFeedback() != null) {
			for(PanelFeedback feedback : panel.getFeedback()) {
				if(feedback.getStatus().equals(PanelStatus.Repanel)) {
					pass = false;
					break;
				}
			}
		}
		if((pass && !PanelStatus.Pass.equals(panel.getStatus() )) || 
		  (!pass && PanelStatus.Pass.equals(panel.getStatus()))) {
			log.warn("Failed to create panel. Overall Panel Status not as expected.");
			return false;
		}
		
		return true;
	}

}
