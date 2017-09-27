package com.revature.caliber.test.uat;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditLocationFeature {


	private WebDriver webdriver;
	private SettingLocationPage settingLocationPage;
	
	@cucumber.api.java.Before
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		settingLocationPage = new SettingLocationPage(setup.getDriver());
	}
	
	@Given("^I am located on the settings location page$")
	public void i_am_located_on_the_settings_location_page() {
		settingLocationPage.gotoSettingLocationPage();
		settingLocationPage.verifyLocationPage();
	}

	@Given("^I click on the Pencil Edit button$")
	public void i_click_on_the_Pencil_Edit_button() throws InterruptedException {
		settingLocationPage.gotoSettingLocationPage();
		settingLocationPage.clickUpdatePencilBtn();
	}

	@Given("^I add \"([^\"]*)\" to the end of the company name$")
	public void i_add_to_the_end_of_the_company_name(String company) {
		settingLocationPage.updateInputCompanyName(company);
	}
	
	@Given("^I add \"([^\"]*)\" to the end of the address$")
	public void i_add_to_the_end_of_the_address(String address) {
		settingLocationPage.updateInputStreetAddress(address);
	}

	@Given("^I add \"([^\"]*)\" to the end of the city name$")
	public void i_add_to_the_end_of_the_city_name(String city) {
		settingLocationPage.updateInputCity(city);
	}

	@Given("^I add \"([^\"]*)\" to the state field$")
	public void i_add_to_the_state_field(String state) throws InterruptedException {
		settingLocationPage.updateInputState(state);
	}
	@Given("^I add \"([^\"]*)\" to the zipcode field$")
	public void i_add_to_the_zipcode_field(String zipcode) {
		settingLocationPage.updateInputZipcode(zipcode);
	}

	@When("^I click on the Update button$")
	public void i_click_on_the_Update_button() {
		settingLocationPage.clickUpdateModalBtn();
	}

	@Then("^I will have updated the location$")
	public void i_will_have_updated_the_location() {
	}
}
