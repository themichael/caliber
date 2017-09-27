package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WeeklyBatchProgressFeature {

	private ChromeDriverSetup setup;
	private ReportsPage reportsPage;
	
	@Before // each scenario
	public void setup(){
		setup = new ChromeDriverSetup();
		reportsPage = new ReportsPage(setup.getDriver());
	}

/*	@After // each scenario
	public void teardown() {
		reportsPage.closeDriver();
	}*/

	@When("^I select a batch$")
	public void iSelectABatch() throws Throwable {
	    reportsPage.gotoReportsPage();
	    reportsPage.verifyReportsPage();
	    reportsPage.clickBatchDropdown();
	    reportsPage.chooseBatch("Patrick Walsh - 2/13/17");
	}

	@Then("^I can note the weekly progress for a batch$")
	public void iCanNoteTheWeeklyProgressForABatch() throws Throwable {
	    reportsPage.checkWeeklyProgressChart();
	}
}
