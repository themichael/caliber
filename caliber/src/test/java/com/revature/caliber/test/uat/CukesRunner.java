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
@CucumberOptions(features={"src/test/resources/features/edit-setting-category.feature"})
public class CukesRunner {}

