package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
import cucumber.api.java.After;
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

//	@After // each scenario
//	public void teardown() {
//		managePage.closeDriver();
//	}

	@Given("^I am on the Manage Batch Page$")
	public void iAmOnTheManageBatchPage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.gotoManagePage();
	    managePage.verifyPage("manage");
	}

	@Given("^I have clicked the update Batch icon$")
	public void iHaveClickedTheUpdateBatchIcon() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.clickUpdateBatchIcon(0);
	}

	@Given("^I have entered \"([^\"]*)\" as the Training Name$")
	public void iHaveEnteredAsTheTrainingName(String trainerName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editTrainingNameField(trainerName);
	}

	@Given("^I have entered \"([^\"]*)\" as the Training Type$")
	public void iHaveEnteredAsTheTrainingType(String trainingType) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editTrainingTypeField(trainingType);
	}

	@Given("^I have entered \"([^\"]*)\" as the skill Type$")
	public void iHaveEnteredAsTheSkillType(String skillType) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editSkillTypeField(skillType);
	}

	@Given("^I have entered \"([^\"]*)\" as the Location$")
	public void iHaveEnteredAsTheLocation(String index) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editLocationField(1);
	}

	@Given("^I have chosen \"([^\"]*)\" as the Trainer$")
	public void iHaveChosenAsTheTrainer(String trainer) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editTrainerField(trainer);
	}

	@Given("^I have chosen \"([^\"]*)\" as the Co-Trainer$")
	public void iHaveChosenAsTheCoTrainer(String coTrainer) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editCoTrainerField(coTrainer);
	}

	@Given("^I have entered \"([^\"]*)\" as the Start Date$")
	public void iHaveEnteredAsTheStartDate(String startDate) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editStartDateField(startDate);
	}

	@Given("^I have entered \"([^\"]*)\" as the End Date$")
	public void iHaveEnteredAsTheEndDate(String endDate) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editEndDateField(endDate);
	}

	@Given("^I have chosen (\\d+) as the Good Grade$")
	public void iHaveChosenAsTheGoodGrade(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editGoodGradeField("75");;
	}

	@Given("^I have chosen (\\d+) as the Passing Grade$")
	public void iHaveChosenAsThePassingGrade(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editPassingGradeField("80");
	}

	@When("^I click the Update button$")
	public void iClickTheUpdateButton() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.clickUpdateAddTraineeModal();
	}

	@Then("^I am back on the Manage Batch Page$")
	public void iAmBackOnTheManageBatchPage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.verifyPage("vp/manage");
	}
}
