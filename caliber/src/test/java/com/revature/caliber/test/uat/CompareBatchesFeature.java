package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CompareBatchesFeature {

	HomePage homePage;

	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		homePage = new HomePage(setup.getDriver());
	}

	@Given("^I am logged in as the VP$")
	public void i_am_logged_in_as_the_VP() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		homePage.goToPage("home");
 	}

	@When("^I go to Home Page$")
	public void i_go_to_Home_Page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		homePage.assertHomePage();
 	}

	@Then("^I can see and compare batch performance to each other$")
	public void i_can_see_and_compare_batch_performance_to_each_other() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		ZZZ.waitForPageLoad();
		Thread.sleep(3000);
		homePage.selectLineStateDropdown("NY");
		homePage.isSelectLineCityDisplayed();
 	}
}
