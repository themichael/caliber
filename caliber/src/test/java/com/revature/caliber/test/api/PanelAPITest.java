package com.revature.caliber.test.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class PanelAPITest extends AbstractAPITest {
	
	private static final Logger log = Logger.getLogger(PanelAPITest.class);
	@Autowired
	private PanelDAO panelDAO;
	@Autowired
	private PanelFeedbackDAO pfDAO;
	
	
}
