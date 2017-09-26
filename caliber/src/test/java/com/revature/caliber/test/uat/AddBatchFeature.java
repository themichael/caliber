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
	public void i_am_inside_the_Manage_Batch_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.gotoManagePage();
	    managePage.verifyPage("manage");
	}

	@Given("^I have clicked Create Batch$")
	public void i_have_clicked_Create_Batch() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.openCreateBatchModal();
	}

	@Given("^I have entered \"([^\"]*)\" as the Training name$")
	public void i_have_entered_as_the_Training_name(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editTrainingNameField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Training Type$")
	public void i_have_selected_as_the_Training_Type(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editTrainingTypeField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Skill type$")
	public void i_have_selected_as_the_Skill_type(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editSkillTypeField(arg1);
	}

	@Given("^I have selected (\\d+) as the Location$")
	public void i_have_selected_as_the_Location(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editLocationField(arg1);
	}
	
	@Given("^I have selected \"([^\"]*)\" as the Trainer$")
	public void i_have_selected_as_the_Trainer(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editTrainerField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Co-Trainer$")
	public void i_have_selected_as_the_Co_Trainer(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editCoTrainerField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the Start Date$")
	public void i_have_selected_as_the_Start_Date(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editStartDateField(arg1);
	}

	@Given("^I have selected \"([^\"]*)\" as the End Date$")
	public void i_have_selected_as_the_End_Date(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.editEndDateField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Good Grade$")
	public void i_have_entered_as_the_Good_Grade(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editGoodGradeField(arg1);
	}

	@Given("^I have entered \"([^\"]*)\" as the Passing Grade$")
	public void i_have_entered_as_the_Passing_Grade(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.editPassingGradeField(arg1);
	}

	@When("^I click the Create Batch Save button$")
	public void i_click_the_Create_Batch_Save_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    managePage.clickSaveOnCreateBatchModal();
	}

	@Then("^the \"([^\"]*)\" Batch appears$")
	public void the_Batch_appears(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		managePage.newBatchCreated(arg1);
	}

}
