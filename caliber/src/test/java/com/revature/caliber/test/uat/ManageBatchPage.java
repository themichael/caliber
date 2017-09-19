package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class ManageBatchPage extends DriverSetup{

//	protected driver
//	protected url
//	public void setup(){
//		driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
//		driver.get("http://localhost:8080/caliber/#/vp/manage");
//		URL = driver.getCurrentUrl();
//	}
	public void gotoManagePage(){
		driver.get("http://localhost:8080/caliber/#/vp/manage");
		URL = driver.getCurrentUrl();
	}
	
	public void verifyManagePage(){
		assertEquals("http://localhost:8080/caliber/#/vp/manage", 
				driver.getCurrentUrl());
	}
}
