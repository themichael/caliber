package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.revature.caliber.CaliberTest;


public class ChromeDriverSetup extends CaliberTest{
	
	protected static WebDriver driver;
	private static boolean initialized = false;

	public ChromeDriverSetup(){
		if(!initialized){
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--headless");
	    options.addArguments("--window-size=1500x1000");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		initialized = true;
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driverSetup) {
		driver = driverSetup;
	}
	
	
}
