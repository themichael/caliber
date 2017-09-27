package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TraineeWeeklyPerformanceFeature {

	private ReportsPage reports;
	
	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reports = new ReportsPage(setup.getDriver());
	}

//	@After // each scenario
//	public void teardown() {
//		reports.closeDriver();
//	}
	
	@Given("^I select a year$")
	public void iSelectAYear() throws Throwable {
//		Thread.sleep(500);
	    reports.clickReportYear("2017");
	}

	@Given("^I have chosen a batch$")
	public void iHaveChosenABatch() throws Throwable {
	    reports.clickBatchDropdown();
	    reports.chooseBatch("Patrick Walsh - 2/14/17");
	}
	
	@When("^I select a Trainee$")
	public void iSelectATrainee() throws Throwable {
	    reports.clickTraineeDropdown();
	    reports.chooseTraineeReport("Ali, Fareed");
	}

	@Then("^I can compare the Trainee performance to the batch performance$")
	public void iCanCompareTheTraineePerformanceToTheBatchPerformance() throws Throwable {
	    reports.checkTraineeWeeklyProgressChart();
	}
}
