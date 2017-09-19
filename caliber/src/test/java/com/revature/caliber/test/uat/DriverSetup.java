package com.revature.caliber.test.uat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DriverSetup {
	
	protected static WebDriver driver;
	protected static String URL = "";
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		DriverSetup.driver = driver;
	}
	 
}
