package com.revature.caliber.test.uat;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddLocationFeature {
	
	private WebDriver webdriver;
	private SettingLocationPage settingLocationPage;
	
	@cucumber.api.java.Before
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		settingLocationPage = new SettingLocationPage(setup.getDriver());
	}
/*	
	@cucumber.api.java.After
	public void teardown() {
		settingLocationPage.closeDriver();
	}
*/
	@Given("^I am on the Settings Locations page$")
	public void i_am_on_the_Settings_Locations_page() {
		settingLocationPage.gotoSettingLocationPage();
		settingLocationPage.verifyLocationPage();
	}

	@When("^I click on the Create Location button$")
	public void i_click_on_the_Create_Location_button() throws InterruptedException {
		settingLocationPage.clickCreateLocationBtn();
	}

	@When("^I enter \"([^\"]*)\" as the Company Name$")
	public void i_enter_as_the_Company_Name(String companyName) {
		settingLocationPage.inputCompanyName(companyName);
	}

	@When("^I enter \"([^\"]*)\" as the Street Address$")
	public void i_enter_as_the_Street_Address(String address) {
		settingLocationPage.inputStreetAddress(address);
	}

	@When("^I enter \"([^\"]*)\" as the City$")
	public void i_enter_as_the_City(String city) {
		settingLocationPage.inputCity(city);
	}

	@When("^I enter \"([^\"]*)\" as the State$")
	public void i_enter_as_the_State(String state) throws InterruptedException {
		settingLocationPage.inputState(state);
	}

	@When("^I enter \"([^\"]*)\" as the Zipcode$")
	public void i_enter_as_the_Zipcode(String zipcode) {
		settingLocationPage.inputZipcode(zipcode);
	}

	@When("^I click the Save Button$")
	public void i_click_the_Save_Button() throws InterruptedException {
		Thread.sleep(3000);
		settingLocationPage.clickSaveButn();
	}

	@Then("^I will have added a location$")
	public void i_will_have_added_a_location() {
	}
	
	@When("^I choose the X button$")
	public void i_choose_the_X_button() throws InterruptedException {
		Thread.sleep(1000);
		settingLocationPage.clickXtoCloseAddLocModalBtn();
	}

	@Then("^I cancel making a new location$")
	public void i_cancel_making_a_new_location() {
	}

	@When("^I click on the Close button$")
	public void i_click_on_the_Close_button() throws InterruptedException {
		Thread.sleep(1000);
		settingLocationPage.clickCloseAddLocModalBtn();
	}

	@Then("^I cancel creating a new location$")
	public void i_cancel_creating_a_new_location() {
	}
	
}
