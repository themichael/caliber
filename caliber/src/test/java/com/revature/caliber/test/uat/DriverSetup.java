package com.revature.caliber.test.uat;

import org.openqa.selenium.WebDriver;

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
