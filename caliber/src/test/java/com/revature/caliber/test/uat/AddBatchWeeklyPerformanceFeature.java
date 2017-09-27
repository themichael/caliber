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
	public void that_I_am_logged_in_as_a_trainer() throws Throwable {
	    assessBatchPage.goToPage();
	}

	@Given("^on the Assess Batch Page$")
	public void on_the_Assess_Batch_Page() throws Throwable {
	   assessBatchPage.verifyAssessPage();
	}

	@Given("^I have chosen the Batch$")
	public void i_have_chosen_the_Batch() throws Throwable {
	    assessBatchPage.selectBatch();
	}

	@Given("^I have chosen the Week$")
	public void i_have_chosen_the_Week() throws Throwable {
	    assessBatchPage.clickWeekTab();
	}

	@Given("^I have entered \"([^\"]*)\" as the Overall Feedback$")
	public void i_have_entered_as_the_Overall_Feedback(String feedback) throws Throwable {
		Thread.sleep(500);
	    assessBatchPage.batchNotes(feedback);
	}

	@When("^I press the Save button$")
	public void i_press_the_Save_button() throws Throwable {
	    assessBatchPage.saveButton();
	}

	@Then("^the Feedback \"([^\"]*)\" is recorded$")
	public void the_Feedback_is_recorded(String feedback) throws Throwable {
		assessBatchPage.goToPage();
		assessBatchPage.selectBatch();
		assessBatchPage.clickWeekTab();
		Thread.sleep(5000);
	    assessBatchPage.batchNotesCheck(feedback);
	}

}
