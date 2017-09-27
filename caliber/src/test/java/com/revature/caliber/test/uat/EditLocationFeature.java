package com.revature.caliber.test.uat;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditLocationFeature {

	private SettingLocationPage settingLocationPage;
	
	@cucumber.api.java.Before
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		settingLocationPage = new SettingLocationPage(setup.getDriver());
	}
	
	@Given("^I am located on the settings location page$")
	public void iAmLocatedOnTheSettingsLocationPage() {
		settingLocationPage.gotoSettingLocationPage();
		settingLocationPage.verifyLocationPage();
	}

	@Given("^I click on the Pencil Edit button$")
	public void iClickOnThePencilEditButton() throws InterruptedException {
		settingLocationPage.gotoSettingLocationPage();
		settingLocationPage.clickUpdatePencilBtn();
	}

	@Given("^I add \"([^\"]*)\" to the end of the company name$")
	public void iAddToTheEndOfTheCompanyName(String company) {
		settingLocationPage.updateInputCompanyName(company);
	}
	
	@Given("^I add \"([^\"]*)\" to the end of the address$")
	public void iAddToTheEndOfTheAddress(String address) {
		settingLocationPage.updateInputStreetAddress(address);
	}

	@Given("^I add \"([^\"]*)\" to the end of the city name$")
	public void iAddToTheEndOfTheCityName(String city) {
		settingLocationPage.updateInputCity(city);
	}

	@Given("^I add \"([^\"]*)\" to the state field$")
	public void iAddToTheStateField(String state) throws InterruptedException {
		settingLocationPage.updateInputState(state);
	}
	@Given("^I add \"([^\"]*)\" to the zipcode field$")
	public void iAddToTheZipcodeField(String zipcode) {
		settingLocationPage.updateInputZipcode(zipcode);
	}

	@When("^I click on the Update button$")
	public void iClickOnTheUpdateButton() {
		settingLocationPage.clickUpdateModalBtn();
	}

	@Then("^I will have updated the location$")
	public void iWillHaveUpdateTheLocation() {
	}
}
