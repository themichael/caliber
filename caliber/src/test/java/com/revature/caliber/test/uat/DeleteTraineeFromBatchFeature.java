package com.revature.caliber.test.uat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteTraineeFromBatchFeature {
	
	ManageBatchPage managePage;

	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = ChromeDriverSetup.getInstance();
		managePage = new ManageBatchPage(setup.getDriver());
	}
	
	@Given("^I am inside the Manage Batch Page$")
	public void iAmInsideTheManageBatchPage(){
	    // Write code here that turns the phrase above into concrete actions
		managePage.gotoManagePage();
 	}

	@Given("^I have clicked the person icon$")
	public void iHaveClickedThePersonIcon() throws InterruptedException, IOException{
 		managePage.openManageBatchModal(0);
 	}

	@Given("^I have clicked the \"([^\"]*)\" trainee  delete icon$")
	public void iHaveClickedTheTraineeDeleteIcon(String arg1) throws InterruptedException{
 		managePage.openDeleteTraineeModal(arg1);
 	}
	
	@When("^I have clicked the delete button$")
	public void iHaveClickedTheDeleteButton() throws InterruptedException{
 		managePage.clickDeleteOnDeleteTraineeModal();
		
 	}

	@Then("^the trainee \"([^\"]*)\" has been removed the trainee$")
	public void theTraineeHasBeenRemovedTheTrainee(String arg1){
 		assertTrue(!managePage.checkIfTraineeExists(arg1));
	}	
}
