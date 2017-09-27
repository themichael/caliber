package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateBatchFeature {
	
	private ManageBatchPage managePage;
	
	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		managePage = new ManageBatchPage(setup.getDriver());
	}

	@Given("^I am currently on the Manage Batch Page$")
	public void iAmOnTheManageBatchPage() {
	    managePage.gotoManagePage();
	    managePage.verifyPage("manage");
	}

	@Given("^I have clicked the update Batch icon$")
	public void iHaveClickedTheUpdateBatchIcon() throws InterruptedException{
	    managePage.clickUpdateBatchIcon(0);
	}

	@Given("^I have entered \"([^\"]*)\" as the Training Name$")
	public void iHaveEnteredAsTheTrainingName(String trainerName) throws InterruptedException{
	    managePage.editTrainingNameField(trainerName);
	}

	@Given("^I have entered \"([^\"]*)\" as the Training Type$")
	public void iHaveEnteredAsTheTrainingType(String trainingType) throws InterruptedException {
	    managePage.editTrainingTypeField(trainingType);
	}

	@Given("^I have entered \"([^\"]*)\" as the skill Type$")
	public void iHaveEnteredAsTheSkillType(String skillType) throws InterruptedException {
	    managePage.editSkillTypeField(skillType);
	}

	@Given("^I have entered \"([^\"]*)\" as the Location$")
	public void iHaveEnteredAsTheLocation(String index) throws InterruptedException {
	    managePage.editLocationField(1);
	}

	@Given("^I have chosen \"([^\"]*)\" as the Trainer$")
	public void iHaveChosenAsTheTrainer(String trainer) throws InterruptedException {
	    managePage.editTrainerField(trainer);
	}

	@Given("^I have chosen \"([^\"]*)\" as the Co-Trainer$")
	public void iHaveChosenAsTheCoTrainer(String coTrainer) throws InterruptedException {
	    managePage.editCoTrainerField(coTrainer);
	}

	@Given("^I have entered \"([^\"]*)\" as the Start Date$")
	public void iHaveEnteredAsTheStartDate(String startDate) {
	    managePage.editStartDateField(startDate);
	}

	@Given("^I have entered \"([^\"]*)\" as the End Date$")
	public void iHaveEnteredAsTheEndDate(String endDate) {
	    managePage.editEndDateField(endDate);
	}

	@Given("^I have chosen (\\d+) as the Good Grade$")
	public void iHaveChosenAsTheGoodGrade(int arg1) {
	    managePage.editGoodGradeField("75");
	}

	@Given("^I have chosen (\\d+) as the Passing Grade$")
	public void iHaveChosenAsThePassingGrade(int arg1) {
	    managePage.editPassingGradeField("80");
	}

	@When("^I click the Update button$")
	public void iClickTheUpdateButton() throws InterruptedException {
	    managePage.clickSaveOnCreateBatchModal();
	}

	@Then("^I am back on the Manage Batch Page$")
	public void iAmBackOnTheManageBatchPage() {
		managePage.verifyPage("manage");
	}
}
