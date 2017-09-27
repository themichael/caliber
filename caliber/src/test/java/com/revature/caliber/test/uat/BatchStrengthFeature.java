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
	public void that_I_am_logged_in_as_a_user() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		reportsPage.gotoReportsPage();
 	}

	@Given("^inside the Reports page$")
	public void inside_the_Reports_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		reportsPage.verifyReportsPage();
 	}

	@Given("^I have selected the year$")
	public void i_have_selected_the_year() throws Throwable {
		Thread.sleep(500);
		reportsPage.clickReportYear("year");
 	}

	@When("^I have selected the Batch$")
	public void i_have_selected_the_Batch() throws Throwable {
		reportsPage.clickBatchDropdown();
		reportsPage.chooseBatch("Patrick Walsh - 2/14/17");
 	}

	@Then("^I am able to report the Batch performance$")
	public void i_am_able_to_report_the_Batch_performance() throws Throwable {
		reportsPage.checkTechSkillsGraph();
		reportsPage.closeDriver();
 	}
}
