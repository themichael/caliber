package com.revature.caliber.test.uat;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditTraineesFeature {
	
	private ManageBatchPage manageBatchPage;

	@Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		manageBatchPage = new ManageBatchPage(setup.getDriver());
	}
	
//	@After
//	public void teardown(){
//		manageBatchPage.closeDriver();
//	}
	
	@Given("^that I am logged in as a User$")
	public void that_I_am_logged_in_as_a_User() throws Throwable {
		manageBatchPage.goToHome();
	}

	@Given("^on the Manage Batch page,$")
	public void on_the_Manage_Batch_page() throws Throwable {
	    manageBatchPage.gotoManagePage();
	    manageBatchPage.verifyPage("manage");
	}

	@Given("^I have clicked on the person icon corresponding to a batch,$")
	public void i_have_clicked_on_the_person_icon_corresponding_to_a_batch() throws Throwable {
		manageBatchPage.openManageBatchModal(0);
	}

	@Given("^I have clicked on the pencil icon corresponding to a trainee$")
	public void i_have_clicked_on_the_pencil_icon_corresponding_to_a_trainee() throws Throwable {
		manageBatchPage.openUpdateTraineeModal(0);
	}

	@Given("^I have edited one or more of the form boxes,$")
	public void i_have_edited_one_or_more_of_the_form_boxes() throws Throwable {
	    manageBatchPage.editName("Patrick Muldoon");
	}

	@When("^I click the update button$")
	public void i_click_the_update_button() throws Throwable {
		//manageBatchPage.gotoManagePage();
		manageBatchPage.clickUpdateAddTraineeModal();
	}

	@Then("^the trainees information will be changed and saved into the db\\.$")
	public void the_trainees_information_will_be_changed_and_saved_into_the_db() throws Throwable {
		manageBatchPage.verifyEditTraineeModal();
	}

	@Given("^I have entered an invalid email address,$")
	public void i_have_entered_an_invalid_email_address() throws Throwable {
	    manageBatchPage.editEmailField("randomstuff");
	}

	@When("^I click on the update button$")
	public void i_click_on_the_update_button() throws Throwable {
	    manageBatchPage.clickUpdateAddTraineeModal();
	}

	@Then("^I should get an error stating the email address I input is invalid\\.$")
	public void i_should_get_an_error_stating_the_email_address_I_input_is_invalid() throws Throwable {
		manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyRequiredInputField();
	}

	@Given("^I have entered an invalid value as a percentage,$")
	public void i_have_entered_an_invalid_value_as_a_percentage() throws Throwable {
	    manageBatchPage.editProjectCompletionField("-1");
	}

	@Then("^I should get an error stating the percentage is invalid\\.$")
	public void i_should_get_an_error_stating_the_percentage_is_invalid() throws Throwable {
		manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyInvalidInputField();
	}

	@Given("^I currently have no information entered for the email address,$")
	public void i_currently_have_no_information_entered_for_the_email_address() throws Throwable {
	    manageBatchPage.editEmailField("");
	}

	@When("^I press the update button$")
	public void i_press_the_update_button() throws Throwable {
	    manageBatchPage.clickUpdateAddTraineeModal();
	}

	@Then("^I should get an error asking me to fill out this field\\.$")
	public void i_should_get_an_error_asking_me_to_fill_out_this_field() throws Throwable {
	    manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyRequiredInputField();
	}

	@Given("^I have left their name field blank,$")
	public void i_have_left_their_name_field_blank() throws Throwable {
	    manageBatchPage.editName("");
	}

	@When("^I go and click the update button$")
	public void i_go_and_click_the_update_button() throws Throwable {
	    manageBatchPage.clickUpdateAddTraineeModal();
	}

	@Then("^I should get an error stating the field cannot be blank\\.$")
	public void i_should_get_an_error_stating_the_field_cannot_be_blank() throws Throwable {
	    manageBatchPage.verifyEditTraineeModal();
	    manageBatchPage.verifyRequiredInputField();
	}
	
}

