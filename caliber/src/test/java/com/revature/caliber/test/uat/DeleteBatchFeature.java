package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteBatchFeature {

	ManageBatchPage managePage;

	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		managePage = new ManageBatchPage(setup.getDriver());
	}

	@Given("^I am in the Manage Batch Page$")
	public void i_am_in_the_Manage_Batch_Page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		managePage.gotoManagePage();
		managePage.verifyPage("manage");
 	}

	@Given("^I click the delete batch icon$")
	public void i_click_the_delete_batch_icon() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		managePage.openManageBatchModal(0);
 	}

	@When("^I click the Delete button in the confirm batch delete modal$")
	public void i_click_the_Delete_button_in_the_confirm_batch_delete_modal() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		managePage.clickDeleteOnDeleteBatchModal();
 	}

	@Then("^the batch has been deleted and I am on the Manage Batch Page$")
	public void the_batch_has_been_deleted_and_I_am_on_the_Manage_Batch_Page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		managePage.verifyPage("manage");
 	}
}
