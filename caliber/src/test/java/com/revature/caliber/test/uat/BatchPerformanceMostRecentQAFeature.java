package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BatchPerformanceMostRecentQAFeature {

	private HomePage homePage;
	
	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		homePage = new HomePage(setup.getDriver());
	}
	

	@Given("^I am logged in with VP credentials $")
	public void iAmLoggedInAsVP() throws InterruptedException {
		homePage.goToPage("home");
 	}

	@When("^I go to the Home Page$")
	public void iGoToTheHomePage() throws InterruptedException {
		homePage.assertHomePage();
 	}

	@Then("^I can view the batch performance from their most recent quality audits$")
	public void iCanViewTheBatchPerformanceFromTheirMostRecentQualityAudits() throws InterruptedException {
		homePage.selectBarChartStateDropdown("VA");
		homePage.isSelectBarCityDisplayed();
 	}
	
}
