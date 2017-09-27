package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ActivateDeactivateLocationFeature {
	
	private SettingLocationPage settingLocationPage;
	
	@cucumber.api.java.Before
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		settingLocationPage = new SettingLocationPage(setup.getDriver());
	}

	@Given("^I am on the Setting Locations Page$")
	public void i_am_on_the_Setting_Locations_Page() throws Throwable {
	    settingLocationPage.gotoSettingLocationPage();
	    settingLocationPage.verifyLocationPage();
	}

	@Given("^the button in row (\\d+) in an X$")
	public void the_button_in_row_in_an_X(int index) throws Throwable {
	    assertTrue(settingLocationPage.verifyLocationIsActive(index));
	}

	@Given("^I click on the X-button to start the deactivation process in row (\\d+)$")
	public void i_click_on_the_X_button_to_start_the_deactivation_process_in_row(int index) throws Throwable {
	    settingLocationPage.deactivateLocationBtn(index);
	}

	@When("^I click on the Cancel button$")
	public void i_click_on_the_Cancel_button() throws Throwable {
	    settingLocationPage.clickDeactivateInDeactivateModalBtn();
	}

	@Then("^I will be back on the location setting page$")
	public void i_will_be_back_on_the_location_setting_page() throws Throwable {
	    settingLocationPage.verifyLocationPage();
	}

	@Given("^the button in row (\\d+) in a check mark$")
	public void the_button_in_row_in_a_check_mark(int index) throws Throwable {
	    assertTrue(settingLocationPage.verifyLocationIsInActive(index));
	}

	@Given("^I click on the X-button to start reactivating the location in row (\\d+)$")
	public void i_click_on_the_X_button_to_start_deactivating_the_location_in_row(int index) throws Throwable {
	    settingLocationPage.clickCheckReactivateLocationBtn(index);
	}

	@When("^I click on the x-button to cancel$")
	public void i_click_on_the_x_button_to_cancel() throws Throwable {
	    settingLocationPage.clickXCloseActivationDeactivateModal();
	}

	@Then("^I will be back on the location page$")
	public void i_will_be_back_on_the_location_page() throws Throwable {
	    settingLocationPage.verifyLocationPage();
	}

	@Given("^I click on the X-button to begin the deactivation process in row (\\d+)$")
	public void i_click_on_the_X_button_to_begin_the_deactivation_process_in_row(int index) throws Throwable {
	    settingLocationPage.deactivateLocationBtn(index);
	}

	@When("^I click on the Deactivation button$")
	public void i_click_on_the_Deactivation_button() throws Throwable {
	    settingLocationPage.clickDeactivateInDeactivateModalBtn();
	}

	@Then("^I will change the location status of (\\d+) to inactive$")
	public void i_will_change_the_location_status_to_inactive(int index) throws Throwable {
	    assertTrue(settingLocationPage.verifyLocationIsInActive(index));
	}

	@Given("^I click on the check-button to begin the reactivation process in row (\\d+)$")
	public void i_click_on_the_check_button_to_begin_the_reactivation_process_in_row(int index) throws Throwable {
	    settingLocationPage.clickCheckReactivateLocationBtn(index);
	}

	@When("^I click on the Reactivate button$")
	public void i_click_on_the_Reactivate_button() throws Throwable {
	    settingLocationPage.clickReactivateLocationBtn();
	}

	@Then("^I will change the location status of (\\d+) to active$")
	public void i_will_change_the_location_status_to_active(int index) throws Throwable {
	    assertTrue(settingLocationPage.verifyLocationIsActive(index));
	}

}
