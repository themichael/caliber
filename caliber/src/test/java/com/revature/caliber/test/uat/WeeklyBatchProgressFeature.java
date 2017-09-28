package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WeeklyBatchProgressFeature {

	private ChromeDriverSetup setup;
	private ReportsPage reportsPage;
	
	@Before // each scenario
	public void setup(){
		setup = ChromeDriverSetup.getInstance();
		reportsPage = new ReportsPage(setup.getDriver());
	}

	@When("^I select a batch$")
	public void iSelectABatch() throws Throwable {
	    reportsPage.gotoReportsPage();
	    reportsPage.verifyReportsPage();
	    reportsPage.clickBatchDropdown();
	    reportsPage.chooseBatch("Patrick Walsh - 2/14/17");
	}

	@Then("^I can note the weekly progress for a batch$")
	public void iCanNoteTheWeeklyProgressForABatch() throws Throwable {
	    reportsPage.checkWeeklyProgressChart();
	}
}
