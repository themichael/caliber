package com.revature.caliber.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.model.SimplePanel;

@Service
public class PanelFeedbackCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static final String SINGLE_CATEGORY_ROUTING_KEY = "utMPxDus2M9qy9Bh";
	private static final String SINGLE_PANEL_ROUTING_KEY = "B8ptbVDNyVB28mVA";
	private static final String RABBIT_REPO_EXCHANGE = "revature.caliber.repos";
	
	/**
	 * Sends a request for a SimpleCategory to the Category service identified by
	 * a categoryId
	 * @param categoryId The categoryId that identifies the SimpleCategory
	 * @return The SimpleCategory returned by the Category service
	 */
	public SimpleCategory sendSingleSimpleCategoryRequest(Integer categoryId) {
		JsonObject categoryRequest = new JsonObject();
		
		categoryRequest.addProperty("methodName", "findOne");
		categoryRequest.addProperty("categoryId", categoryId);
		
		return (SimpleCategory) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_CATEGORY_ROUTING_KEY, categoryRequest.toString());
	}
	
	/**
	 * Sends a request for a SimplePanel to the Panel service identified by
	 * a panelId
	 * @param panelId The panelId that identifies the SimplePanel
	 * @return The SimplePanel returned by the Panel service
	 */
	public SimplePanel sendSingleSimplePanelRequest(Integer panelId) {
		JsonObject panelRequest = new JsonObject();
		
		panelRequest.addProperty("methodName", "findOne");
		panelRequest.addProperty("panelId", panelId);
		
		return (SimplePanel) rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE, SINGLE_PANEL_ROUTING_KEY, panelRequest.toString());
	}
}
