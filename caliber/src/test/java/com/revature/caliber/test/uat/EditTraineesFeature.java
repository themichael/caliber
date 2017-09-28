package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditTraineesFeature{
	
	private ManageBatchPage manageBatchPage;

	@Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		manageBatchPage = new ManageBatchPage(setup.getDriver());
	}
	
	@Given("^that I am logged in as a User$")
	public void thatIAmLoggedInAsAUser(){
		manageBatchPage.goToHome();
	}

	@Given("^on the Manage Batch page,$")
	public void onTheManageBatchPage(){
	    manageBatchPage.gotoManagePage();
	    manageBatchPage.verifyPage("manage");
	}

	@Given("^I have clicked on the person icon corresponding to a batch,$")
	public void iHaveClickedOnThePersonIconCorrespondingToABatch() throws InterruptedException{
		manageBatchPage.openManageBatchModal(0);
	}

	@Given("^I have clicked on the pencil icon corresponding to a trainee$")
	public void iHaveClickedOnThePencilIconCorrespondingToATrainee() throws InterruptedException{
		manageBatchPage.openUpdateTraineeModal(0);
	}

	@Given("^I have edited one or more of the form boxes,$")
	public void iHaveEditedOneOrMoreOfTheFormBoxes(){
	    manageBatchPage.editName("Patrick Muldoon");
	}

	@When("^I click the update button$")
	public void iClickTheUpdateButton() throws InterruptedException{
		manageBatchPage.clickUpdateTraineeModal();
	}

	@Then("^the trainees information will be changed and saved into the db\\.$")
	public void theTraineesInformationWillBeChangedAndSavedIntoTheDb() {
		manageBatchPage.verifyEditTraineeModal();
	}

	@Given("^I have entered an invalid email address,$")
	public void iHaveEnteredAnInvalidEmailAddress(){
	    manageBatchPage.editEmailField("randomstuff");
	}

	@When("^I click on the update button$")
	public void iClickOnTheUpdateButton() throws InterruptedException {
	    manageBatchPage.clickUpdateTraineeModal();
	}

	@Then("^I should get an error stating the email address I input is invalid\\.$")
	public void iShouldGetAnErrorStatingTheEmailAddressIInputIsInvalid() {
		manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyRequiredInputField();
	}

	@Given("^I have entered an invalid value as a percentage,$")
	public void iHaveEnteredAnInvalidValueAsAPercentage(){
	    manageBatchPage.editProjectCompletionField("-1");
	}

	@Then("^I should get an error stating the percentage is invalid\\.$")
	public void iShouldGetAnErrorStatingThePercentageIsInvalid(){
		manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyInvalidInputField();
	}

	@Given("^I currently have no information entered for the email address,$")
	public void iCurrentlyHaveNoInformationEnteredForTheEmailAddress(){
	    manageBatchPage.editEmailField("");
	}

	@When("^I press the update button$")
	public void iPressTheUpdateButton() throws InterruptedException{
	    manageBatchPage.clickUpdateTraineeModal();
	}

	@Then("^I should get an error asking me to fill out this field\\.$")
	public void iShouldGetAnErrorAskingMeToFillOutThisField(){
	    manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyRequiredInputField();
	}

	@Given("^I have left their name field blank,$")
	public void iHaveLeftTheirNameFieldBlank() {
	    manageBatchPage.editName("");
	}

	@When("^I go and click the update button$")
	public void iGoAndClickTheUpdateButton() throws InterruptedException{
	    manageBatchPage.clickUpdateTraineeModal();
	}

	@Then("^I should get an error stating the field cannot be blank\\.$")
	public void iShouldGetAnErrorStatingTheFieldCannotBeBlank() {
	    manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyRequiredInputField();
	}
	
}

