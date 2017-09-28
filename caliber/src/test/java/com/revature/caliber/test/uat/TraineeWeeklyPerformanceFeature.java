package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TraineeWeeklyPerformanceFeature {

	private ReportsPage reports;
	
	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = ChromeDriverSetup.getInstance();
		reports = new ReportsPage(setup.getDriver());
	}
	
	@Given("^I select a year$")
	public void iSelectAYear(){
	    reports.clickReportYear("2017");
	}

	@Given("^I have chosen \"([^\"]*)\" as a batch$")
	public void iHaveChosenAsABatch(String batch){
		reports.clickBatchDropdown();
		reports.chooseBatch(batch);
	}

	@When("^I select \"([^\"]*)\" as a trainee$")
	public void iSelectAsATrainee(String trainee) throws InterruptedException{
		Thread.sleep(1000);
		reports.clickTraineeDropdown();
		Thread.sleep(1000);
		reports.chooseTraineeReport(trainee);
	}

	@Then("^I can compare the Trainee performance to the batch performance$")
	public void iCanCompareTheTraineePerformanceToTheBatchPerformance() {
	    reports.checkTraineeWeeklyProgressChart();
	}
}
