package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
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
	
//	@After // each scenario
//	public void teardown() {
//		reports.closeDriver();
//	}
	
	@Given("^I have picked \"([^\"]*)\" as a batch$")
	public void i_have_picked_as_a_batch(String batch) throws Throwable {
		reports.gotoReportsPage();
		reports.clickBatchDropdown();
		reports.chooseBatch(batch);
	}

	@When("^I have selected \"([^\"]*)\" as a trainee$")
	public void i_have_selected_as_a_trainee(String trainee) throws Throwable {
		reports.clickTraineeDropdown();
		reports.chooseTraineeReport(trainee);
	}

	@Then("^I can see each trainees strengths compared to the batch in Assessment Breakdown table$")
	public void i_can_see_each_trainees_strengths_compared_to_the_batch_in_Assessment_Breakdown_table() throws Throwable {
		reports.checkAssessmentBreakdownChart();
	}
}
