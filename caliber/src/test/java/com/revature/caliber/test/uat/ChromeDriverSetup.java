package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeDriverSetup {
	
	protected static WebDriver driver;
	private static boolean initialized = false;

	
	public ChromeDriverSetup(){
		if(!initialized){
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--headless");
	    options.addArguments("--window-size=1200x600");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		initialized = true;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driverSetup) {
		driver = driverSetup;
	}
	
	
}
