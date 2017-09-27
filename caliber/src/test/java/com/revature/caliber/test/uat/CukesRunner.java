package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//import com.github.mkolisnyk.cucumber.runner.AfterSuite;
//import com.github.mkolisnyk.cucumber.runner.BeforeSuite;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hook class to kick off the E2E tests using Cucumber/Selenium.
 * This test must be iacgnored in the initial build until the
 * app is deployed into the test environment.
 * 
 * All Cucumber features must end in the word Feature.
 * 
 * 
 * @author Patrick Walsh
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features/edit-trainees-as-a-user.feature"})
public class CukesRunner {
//	
//	protected static WebDriver driver;
//	private static boolean initialized = false;
//	
//	@BeforeSuite
//	public static void initialize(){
//		if(!initialized){
//			System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
//		    ChromeOptions options = new ChromeOptions();
//		    options.addArguments("--headless");
//		    options.addArguments("--window-size=1200x600");
//			driver = new ChromeDriver(options);
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			initialized = true;
//			}
//	}
//	
//	@AfterSuite
//	public static void teardown(){
//		driver.quit();
//	}
//
//	public static WebDriver getDriver() {
//		return driver;
//	}
//
//	public static void setDriver(WebDriver driver) {
//		CukesRunner.driver = driver;
//	}
//	
}
