package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class ManageBatchPage{

//	protected driver
//	protected url
//	public void setup(){
//		driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
//		driver.get("http://localhost:8080/caliber/#/vp/manage");
//		URL = driver.getCurrentUrl();
//	}
	private WebDriver driver;
	
	
	public ManageBatchPage(WebDriver driver) {
	super();
	this.driver = driver;
	}

	public void checkLoggedIn(){
		WebDriver yeah = new HtmlUnitDriver(BrowserVersion.CHROME, true);
		yeah.get("http://localhost:8080/caliber#/vp/home");
	}
	
	public void goToHome(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	public void gotoManagePage(){
		driver.navigate().to("http://localhost:8080/caliber/#/vp/manage");
		
	}
	
	public void verifyManagePage(){
		assertEquals("http://localhost:8080/caliber/#/vp/manage", 
				driver.getCurrentUrl());
	}
}
