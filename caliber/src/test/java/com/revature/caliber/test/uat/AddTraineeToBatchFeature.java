package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddTraineeToBatchFeature {
	ManageBatchPage managePage;
	
	
	@Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		managePage = new ManageBatchPage(setup.getDriver());
	}
	
//	@Given("^I am on the Manage Batch Page$")
//	public void i_am_on_the_Manage_Batch_Page() throws Throwable {
//	    // Write code here that turns the phrase above into concrete actions
//		managePage.gotoManagePage();
//		managePage.verifyPage("manage");
//	}

	@Given("^I have clicked the Person Icon for a specific Batch$")
	public void i_have_clicked_the_Person_Icon_for_a_specific_Batch() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.openManageBatchModal(0);
	}

	@Given("^I have clicked the add Trainee tab$")
	public void i_have_clicked_the_add_Trainee_tab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.openAddTraineeModal();
	}

	@Given("^I have entered \"([^\"]*)\" as the full name$")
	public void i_have_entered_as_the_full_name(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editName(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the email$")
	public void i_have_entered_as_the_email(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editEmailField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Skype Id$")
	public void i_have_entered_as_the_Skype_Id(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editSkypeIDField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Phone Number$")
	public void i_have_entered_as_the_Phone_Number(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editPhoneField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the College$")
	public void i_have_entered_as_the_College(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editCollegeField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Degree$")
	public void i_have_entered_as_the_Degree(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editDegreeField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Major$")
	public void i_have_entered_as_the_Major(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editMajorField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Recriter Name$")
	public void i_have_entered_as_the_Recriter_Name(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editRecruiterNameField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Tech Screener Name$")
	public void i_have_entered_as_the_Tech_Screener_Name(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editTechScreenerNameField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Project Completion$")
	public void i_have_entered_as_the_Project_Completion(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editProjectCompletionField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Profile URL$")
	public void i_have_entered_as_the_Profile_URL(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editProfileURLField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the status$")
	public void i_have_selected_as_the_status(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editTrainingStatusField(arg1);
	}

	@When("^I click the Save Button in the add trainee modal$")
	public void i_click_the_Save_Button_in_the_add_trainee_modal() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.clickUpdateAddTraineeModal();
	}

	@Then("^\"([^\"]*)\" has been added$")
	public void has_been_added(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.checkIfTraineeExists(arg1);
	}

}
