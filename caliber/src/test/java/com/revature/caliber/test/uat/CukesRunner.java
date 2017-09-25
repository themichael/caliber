package com.revature.caliber.test.uat;

import org.junit.runner.RunWith;

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
<<<<<<< HEAD
@CucumberOptions(features={"src/test/resources/features/qa-batch-performance.feature"})
=======
@CucumberOptions(features={"src/test/resources/features/download-pdf.feature"})
>>>>>>> 9c9c9d4cbedc19f9a75ff34b3b12a79e38f5163f
public class CukesRunner {}
