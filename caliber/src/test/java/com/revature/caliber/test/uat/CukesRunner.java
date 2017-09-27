package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


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
@CucumberOptions(features={"src/test/resources/features"})
public class CukesRunner {
	
	@AfterClass
	public static void teardown(){
		ChromeDriverSetup.driver.quit();
	}
}

