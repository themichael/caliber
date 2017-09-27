package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TraineeAssessmentCompareToBatchFeature {

	private ReportsPage reports;

	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reports = new ReportsPage(setup.getDriver());
	}
	
	@Given("^I have picked \"([^\"]*)\" as a batch$")
	public void iHavePickedAsABatch(String batch) {
		reports.gotoReportsPage();
		reports.clickBatchDropdown();
		reports.chooseBatch(batch);
	}

	@When("^I have selected \"([^\"]*)\" as a trainee$")
	public void iHaveSelectedAsATrainee(String trainee) throws InterruptedException {
		reports.clickTraineeDropdown();
		reports.chooseTraineeReport(trainee);
	}

	@Then("^I can see each trainees strengths compared to the batch in Assessment Breakdown table$")
	public void iCanSeeEachTraineesStrengthsComparedToTheBatchInAssessmentBreakdownTable()  {
		reports.checkAssessmentBreakdownChart();
	}
}
