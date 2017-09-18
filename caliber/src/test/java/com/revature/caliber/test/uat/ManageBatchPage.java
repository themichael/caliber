package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ManageBatchPage {

	private HtmlUnitDriver driver;

	public ManageBatchPage(HtmlUnitDriver driver) {
		this.driver = driver;
	}
	
	public void gotoManagePage(){
		driver.navigate().to("http://localhost:8080/caliber/#/vp/manage");
	}
	
	public void verifyManagePage(){
		assertEquals("http://localhost:8080/caliber/#/vp/manage", 
				driver.getCurrentUrl());
	}
}
