package com.revature.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.revature.beans.Panel;
import com.revature.beans.PanelFeedback;
import com.revature.beans.PanelStatus;
import com.revature.exceptions.MalformedRequestException;
import com.revature.services.PanelService;

@Component
public class PanelValidator implements ConstraintValidator<ValidPanel, Panel>, InitializingBean, ApplicationContextAware{

	private static final Logger log = Logger.getLogger(PanelValidator.class);
	
	private PanelService panelService;

	private ApplicationContext applicationContext;
	
	public void setPanelService(PanelService panelService) {
		this.panelService = panelService;
	}

	@Override
	public void initialize(ValidPanel constraintAnnotation) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
	}

	@Override
	public boolean isValid(Panel panel, ConstraintValidatorContext context) {
		
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

	@Override
	public void afterPropertiesSet() throws Exception {
		panelService = applicationContext.getBean(PanelService.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
