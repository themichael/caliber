package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BatchPerformanceMostRecentQAFeature {

	HomePage homePage;
	
	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		homePage = new HomePage(setup.getDriver());
	}
	
	@Given("^I am logged in as VP$")
	public void i_am_logged_in_as_VP() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		homePage.goToPage("home");
 	}

	@When("^I go to the Home Page$")
	public void i_go_to_the_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		homePage.assertHomePage();
 	}

	@Then("^I can view the batch performance from their most recent quality audits$")
	public void i_can_view_the_batch_performance_from_their_most_recent_quality_audits() throws Throwable {
		homePage.selectBarChartStateDropdown("HI");
		homePage.isSelectBarCityDisplayed();
 	}


}
