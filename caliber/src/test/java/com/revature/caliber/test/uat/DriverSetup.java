package com.revature.caliber.test.uat;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DriverSetup {
	
	protected static HtmlUnitDriver driver;
	protected static String URL = "";
	
	public HtmlUnitDriver getDriver() {
		return driver;
	}

	public void setDriver(HtmlUnitDriver driver) {
		DriverSetup.driver = driver;
	}
	 
}
