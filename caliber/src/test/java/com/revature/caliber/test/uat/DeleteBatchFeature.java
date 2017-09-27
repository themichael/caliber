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
	public void iAmInTheManageBatchPage() {
 		managePage.gotoManagePage();
		managePage.verifyPage("manage");
		ZZZ.waitForPageLoad();
 	}

	@Given("^I click the delete batch icon$")
	public void iClickTheDeleteBatchIcon() throws InterruptedException{
 		managePage.clickDelectBatchIcon(0);
 	}

	@When("^I click the Delete button in the confirm batch delete modal$")
	public void iClickTheDeleteButtonInTheConfirmBatchDeleteModal() throws InterruptedException{
 		managePage.clickDeleteOnDeleteBatchModal();
 	}

	@Then("^the batch has been deleted and I am on the Manage Batch Page$")
	public void theBatchHasBeenDeletedAndIAmOnTheManageBatchPage(){
 		managePage.verifyPage("manage");
 	}
}
