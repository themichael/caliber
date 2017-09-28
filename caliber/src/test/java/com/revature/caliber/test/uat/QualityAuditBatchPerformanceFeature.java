package com.revature.caliber.test.uat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QualityAuditBatchPerformanceFeature {

	private QualityAuditPage qaPage;
	
	@cucumber.api.java.Before
	public void setup(){
		ChromeDriverSetup setup = ChromeDriverSetup.getInstance();
		qaPage = new QualityAuditPage(setup.getDriver());
	}
	
	@Given("^I am on the Quality Audit page$")
	public void iAmOnTheQualityAuditPage() {
	    qaPage.goToPage();
	    qaPage.verifyPage();
	}

	@Given("^I have selected the current year$")
	public void iHaveSelectedTheCurrentYear() {
	    qaPage.clickYearDropdown("2017");
	    qaPage.verifyYear("2017");
	}

	@Given("^I have selected a Batch$")
	public void iHaveSelectedABatch() {
	    qaPage.clickBatch("Patrick Walsh - 2/14/17");
	    qaPage.verifyBatch("Patrick Walsh - 2/14/17");
	}

	@Given("^I am viewing the most recent week$")
	public void iAmViewingTheMostRecentWeek() {
		qaPage.clickWeeksForBatch(7);
	    qaPage.verifyWeekForBatch("week7");
	}
	
	@Given("^I click on an overall batch feedback button$")
	public void iClickOnAnOverallBatchFeedbackButtons() {
	    qaPage.clickOverallFeedbackQCButtonPoor();
	    qaPage.clickOverallFeedbackQCButtonAvg();
	    qaPage.clickOverallFeedbackQCButtonGood();
	}

	@Given("^I enter \"([^\"]*)\" in the QC Feedback text area$")
	public void iEnterInTheQCFeedbackTextArea(String arg1) {
	    qaPage.setOverallFeedbackQCNotes(arg1);
	}

	@When("^I click the save button$")
	public void iClickTheSaveButton() throws InterruptedException {
	    qaPage.clickSaveButton();
	}

	@Then("^the performance note is saved$")
	public void thePerformanceNoteIsSaved() throws InterruptedException {
	    qaPage.goToPage();
	    Thread.sleep(10000);
	    qaPage.verifyQCNotes();
	}
	
}
