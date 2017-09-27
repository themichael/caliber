package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WeeklyBatchProgressFeature {

	private ReportsPage reportsPage;
	
	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reportsPage = new ReportsPage(setup.getDriver());
	}
/*
	@After // each scenario
	public void teardown() {
		reportsPage.closeDriver();
	}
*/
	@When("^I select a batch$")
	public void iSelectABatch() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    reportsPage.gotoReportsPage();
	    reportsPage.verifyReportsPage();
	    reportsPage.clickBatchDropdown();
	    reportsPage.chooseBatch();
	}

	@Then("^I can note the weekly progress for a batch$")
	public void iCanNoteTheWeeklyProgressForABatch() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    reportsPage.checkWeeklyProgressChart();
	}
}
