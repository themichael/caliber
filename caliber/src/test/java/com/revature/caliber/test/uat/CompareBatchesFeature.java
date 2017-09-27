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
	public void iAmLoggedInAsTheVP() throws InterruptedException{
 		homePage.goToPage("home");
 	}

	@When("^I go to Home Page$")
	public void iGoToHomePage(){
 		homePage.assertHomePage();
 	}

	@Then("^I can see and compare batch performance to each other$")
	public void iCanSeeAndCompareBatchPerformanceToEachOther() throws InterruptedException{
 		ZZZ.waitForPageLoad();
		Thread.sleep(3000);
		homePage.selectLineStateDropdown("NY");
		homePage.isSelectLineCityDisplayed();
 	}
}
