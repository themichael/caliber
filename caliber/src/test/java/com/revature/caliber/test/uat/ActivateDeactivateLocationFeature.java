package com.revature.caliber.test.uat;

import org.openqa.selenium.WebDriver;

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
	public void i_am_on_the_Setting_Locations_Page() {
		settingLocationPage.gotoSettingLocationPage();
		settingLocationPage.verifyLocationPage();
	}

	@Given("^I check if the button is a check or an x$")
	public void i_check_if_the_button_is_a_check_or_an_x() {
		settingLocationPage.verifyCheckOrXLocation();
	}

	@When("^I click on the X-button to start the deactivation process$")
	public void i_click_on_the_X_button_start_the_deactivation_process() throws InterruptedException {
		settingLocationPage.clickXDeleteBtn();
	}

	@When("^I click on the Cancel button$")
	public void i_click_on_the_Cancel_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		settingLocationPage.clickCancelToCloseActivationDeactivateModalBtn();
	}

	@Then("^I will be back on the location setting page$")
	public void i_will_be_back_on_the_location_setting_page() {
		settingLocationPage.verifyLocationIsActive();
	}

	
	@When("I click on the X-button to start deactivating the location$")
	public void i_click_on_the_X_button_to_start_deactivating_the_location() throws InterruptedException {
		settingLocationPage.clickXDeleteBtn();
	}
	
	@When("^I click on the x-button to cancel$")
	public void i_click_on_the_x_button_to_cancel() {
		settingLocationPage.clickXCloseActivationDeactivateModal();
	}

	@Then("^I will be back on the location page$")
	public void i_will_be_back_on_the_location_page() {
		settingLocationPage.verifyLocationIsActive();
	}

	@When("^I click on the X-button to begin the deactivation process$")
	public void i_click_on_the_X_button_to_begin_the_deactivation_process() throws InterruptedException {
		settingLocationPage.clickXDeleteBtn();
	}
	
	@When("^I click on the Deactivation button$")
	public void i_click_on_the_Deactivation_button() throws InterruptedException {
		settingLocationPage.deactivateLocationBtn();
	}

	@Then("^I will change the location status to inactive$")
	public void i_will_change_the_location_status_to_inactive() {
		settingLocationPage.verifyLocationIsInactive();
	}
	
	@When("^I click on the check-button to begin the reactivation process")
	public void i_click_on_the_check_button_to_begin_the_reactivation_process() throws InterruptedException {
		settingLocationPage.clickCheckReactivateLocationBtn();
	}
	
	@When("^I click on the Reactivate button$")
	public void i_click_on_the_Reactivate_button() {
		settingLocationPage.clickReactivateLocationBtn();
	}
	
	@Then("^I will change the location status to active$")
	public void i_will_change_the_location_status_to_active() {
		settingLocationPage.verifyLocationIsActive();
	}
}
