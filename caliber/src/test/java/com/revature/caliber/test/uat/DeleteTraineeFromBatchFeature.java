package com.revature.caliber.test.uat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteTraineeFromBatchFeature {
	
	ManageBatchPage managePage;

	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		managePage = new ManageBatchPage(setup.getDriver());
	}
	
	@Given("^I am inside the Manage Batch Page$")
	public void i_am_inside_the_Manage_Batch_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.gotoManagePage();
 	}

	@Given("^I have clicked the person icon$")
	public void i_have_clicked_the_person_icon() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.openManageBatchModal(0);
 	}

	@Given("^I have clicked the \"([^\"]*)\" trainee  delete icon$")
	public void i_have_clicked_the_trainee_delete_icon(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.openDeleteTraineeModal(arg1);
 	}
	
	@When("^I have clicked the delete button$")
	public void i_have_clicked_the_delete_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.clickDeleteOnDeleteTraineeModal();
		
 	}

	@Then("^the trainee \"([^\"]*)\" has been removed the trainee$")
	public void the_trainee_has_been_removed_the_trainee(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(!managePage.checkIfTraineeExists(arg1));
	}	
}
