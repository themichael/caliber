package com.revature.caliber.test.uat;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddBatchWeeklyPerformanceFeature {

	private AssessBatchPage assessBatchPage;
	private SettingsTrainerPage trainerPage;
	
	@Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		assessBatchPage = new AssessBatchPage(setup.getDriver());
	}
	
	@Given("^that I am logged in as a trainer$")
	public void thatIAmLoggedInAsATrainer() {
	    assessBatchPage.goToPage();
	}

	@Given("^on the Assess Batch Page$")
	public void onTheAssessBatchPage() {
	   assessBatchPage.verifyAssessPage();
	}

	@Given("^I have chosen the Batch$")
	public void iHaveChosenTheBatch() {
	    assessBatchPage.selectBatch();
	}

	@Given("^I have chosen the Week$")
	public void iHaveChosenTheWeek() {
	    assessBatchPage.clickWeekTab(1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Overall Feedback$")
	public void iHaveEnteredAsTheOverallFeedback(String feedback) throws InterruptedException {
		Thread.sleep(500);
	    assessBatchPage.batchNotes(feedback);
	}

	@When("^I press the Save button$")
	public void iPressTheSaveButton() {
	    assessBatchPage.saveButton();
	}

	@Then("^the Feedback \"([^\"]*)\" is recorded$")
	public void theFeedbackIsRecorded(String feedback) throws InterruptedException {
		assessBatchPage.goToPage();
		assessBatchPage.selectBatch();
		assessBatchPage.clickWeekTab(1);
		ZZZ.waitForPageLoad();
		Thread.sleep(5000);
	    assessBatchPage.batchNotesCheck(feedback);
	}

}
