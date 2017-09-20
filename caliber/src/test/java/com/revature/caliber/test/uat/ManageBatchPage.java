package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class ManageBatchPage{

	private PhantomJSDriver driver;
	private String URL;
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public ManageBatchPage(WebDriver driver){
		this.driver = (PhantomJSDriver)driver;
	}
	
	public void gotoManagePage(){
		driver.get("http://localhost:8080/caliber/#/vp/manage");
		URL = driver.getCurrentUrl();
		System.out.println("CurrentURL = " + driver.getCurrentUrl());
		System.out.println(driver.getTitle());
	}
	
	public void goToHome(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	public void verifyManagePage(){
		assertEquals("http://localhost:8080/caliber/#/vp/manage", 
				driver.getCurrentUrl());
	}
}
