package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddBatchFeature {

	ManageBatchPage managePage;
	
	@Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		managePage = new ManageBatchPage(setup.getDriver());
	}

	@Given("^I am inside the Manage Batch page$")
	public void iAmInsideTheManageBatchpage() {
		managePage.gotoManagePage();
	    managePage.verifyPage("manage");
	}

	@Given("^I have clicked Create Batch$")
	public void iHaveClickedCreateBatch() throws InterruptedException {
		managePage.openCreateBatchModal();
	}

	@Given("^I have entered \"([^\"]*)\" as the Training name$")
	public void iHaveEnteredAsTheTrainingName(String arg1) throws InterruptedException {
	    managePage.editTrainingNameField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Training Type$")
	public void iHaveSelectedAsTheTrainingType(String arg1) throws InterruptedException {
	    managePage.editTrainingTypeField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Skill type$")
	public void iHaveSelectedAsTheSkillType(String arg1) throws InterruptedException {
		managePage.editSkillTypeField(arg1);
	}

	@Given("^I have selected (\\d+) as the Location$")
	public void iHaveSelectedAsTheLocation(int arg1) throws InterruptedException {
		managePage.editLocationField(arg1);
	}
	
	@Given("^I have selected \"([^\"]*)\" as the Trainer$")
	public void iHaveSelectedAsTheTrainer(String arg1) throws InterruptedException  {
	    managePage.editTrainerField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Co-Trainer$")
	public void iHaveSelectedAsTheCoTrainer(String arg1) throws InterruptedException {
		managePage.editCoTrainerField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Start Date$")
	public void iHaveSelectedAsTheStartDate(String arg1) {
		managePage.editStartDateField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the End Date$")
	public void iHaveSelectedAsTheEndDate(String arg1){
	    managePage.editEndDateField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Good Grade$")
	public void iHaveEnteredAsTheGoodGrade(String arg1) {
		managePage.editGoodGradeField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Passing Grade$")
	public void iHaveEnteredAsThePassingGrade(String arg1) {
		managePage.editPassingGradeField(arg1);
	}

	@When("^I click the Create Batch Save button$")
	public void iClickTheCreateBatchSaveButton() throws InterruptedException {
	    managePage.clickSaveOnCreateBatchModal();
	}

	@Then("^the \"([^\"]*)\" Batch appears$")
	public void theBatchAppears(String arg1)  {
	    // Write code here that turns the phrase above into concrete actions
		managePage.checkIfBatchExitst(arg1);
	}

}
