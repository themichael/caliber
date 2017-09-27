package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BatchStrengthFeature {

	ReportsPage reportsPage;
	
	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reportsPage = new ReportsPage(setup.getDriver());
	}
	
	@Given("^that I am logged in as a user$")
	public void thatIAmLoggedInAsAUser()  {
		reportsPage.gotoReportsPage();
 	}

	@Given("^inside the Reports page$")
	public void insideTheReportsPage() {
		reportsPage.verifyReportsPage();
 	}

	@Given("^I have selected the year$")
	public void iHaveSelectedTheYear() throws InterruptedException {
		Thread.sleep(500);
		reportsPage.clickReportYear("year");
 	}

	@When("^I have selected the Batch$")
	public void iHaveSelectedTheBatch() {
		reportsPage.clickBatchDropdown();
		reportsPage.chooseBatch("Patrick Walsh - 2/14/17");
 	}

	@Then("^I am able to report the Batch performance$")
	public void iAmAbleToReportTheBatchPerformance()  {
		reportsPage.checkTechSkillsGraph();
 	}
}
