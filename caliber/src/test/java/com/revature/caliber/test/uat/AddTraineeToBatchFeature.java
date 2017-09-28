package com.revature.caliber.test.uat;

import java.io.IOException;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddTraineeToBatchFeature {
	
	private ManageBatchPage managePage;
	
	@Before
	public void setup(){
		ChromeDriverSetup setup = ChromeDriverSetup.getInstance();
		managePage = new ManageBatchPage(setup.getDriver());
	}
	
	@Given("^I am on the Manage Batch Page$")
	public void iAmOnTheManageBatchPage()  {
		managePage.gotoManagePage();
		managePage.verifyPage("manage");
	}

	@Given("^I have clicked the Person Icon for a specific Batch$")
	public void iHaveClickedThePersonIconForASpecificBatch() throws IOException, InterruptedException {
		managePage.openManageBatchModal(0);
	}

	@Given("^I have clicked the add Trainee tab$")
	public void iHaveClickedTheAddTraineeTab() throws InterruptedException {
		managePage.openAddTraineeModal();
	}

	@Given("^I have entered \"([^\"]*)\" as the full name$")
	public void iHaveEnteredAsTheFullName(String arg1) {
		managePage.editName(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the email$")
	public void iHaveEnteredAsTheEmail(String arg1) {
		managePage.editEmailField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Skype Id$")
	public void iHaveEnteredAsTheSkypeId(String arg1) {
		managePage.editSkypeIDField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Phone Number$")
	public void iHaveEnteredAsThePhoneNumber(String arg1) {
		managePage.editPhoneField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the College$")
	public void iHaveEnteredAsTheCollege(String arg1) {
		managePage.editCollegeField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Degree$")
	public void iHaveEnteredAsTheDegree(String arg1) {
		managePage.editDegreeField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Major$")
	public void iHaveEnteredAsTheMajor(String arg1) {
		managePage.editMajorField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Recriter Name$")
	public void iHaveEnteredAsTheRecriterName(String arg1) {
		managePage.editRecruiterNameField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Tech Screener Name$")
	public void iHaveEnteredAsTheTechScreenerName(String arg1) {
		managePage.editTechScreenerNameField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Project Completion$")
	public void iHaveEnteredAsTheProjectCompletion(String arg1) {
		managePage.editProjectCompletionField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Profile URL$")
	public void iHaveEnteredAsTheProfileURL(String arg1) {
		managePage.editProfileURLField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the status$")
	public void iHaveSelectedAsTheStatus(String arg1) throws InterruptedException {
		managePage.editTrainingStatusField(arg1);
	}

	@When("^I click the Save Button in the add trainee modal$")
	public void iClickTheSaveButtonInTheAddTraineeModal() throws InterruptedException{
		managePage.clickAddTraineeModal();
	}

	@Then("^\"([^\"]*)\" has been added$")
	public void hasBeenAdded(String arg1) {
	    managePage.checkIfTraineeExists(arg1);
	}

}
