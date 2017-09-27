package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;
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
	public void i_am_located_on_the_settings_location_page() throws Throwable {
	    settingLocationPage.gotoSettingLocationPage();
	    settingLocationPage.verifyLocationPage();
	}

	@Given("^I click on the Pencil Edit button in row (\\d+)$")
	public void i_click_on_the_Pencil_Edit_button_in_row(int index) throws Throwable {
	    settingLocationPage.clickUpdatePencilBtn(index);
	}

	@Given("^I add \"([^\"]*)\" to the end of the company name$")
	public void i_add_to_the_end_of_the_company_name(String company) throws Throwable {
	    settingLocationPage.updateEditInputCompanyName(company);
	}

	@Given("^I add \"([^\"]*)\" to the end of the address$")
	public void i_add_to_the_end_of_the_address(String address) throws Throwable {
	    settingLocationPage.updateEditInputStreetAddress(address);
	}

	@Given("^I add \"([^\"]*)\" to the end of the city name$")
	public void i_add_to_the_end_of_the_city_name(String city) throws Throwable {
		settingLocationPage.updateEditInputCity(city);
	}

	@Given("^I add \"([^\"]*)\" to the state field$")
	public void i_add_to_the_state_field(String state) throws Throwable {
	    settingLocationPage.updateEditInputState(state);
	}

	@Given("^I add \"([^\"]*)\" to the zipcode field$")
	public void i_add_to_the_zipcode_field(String zipcode) throws Throwable {
	    settingLocationPage.updateEditInputZipcode(zipcode);
	}

	@When("^I click on the Update button$")
	public void i_click_on_the_Update_button() throws Throwable {
	    settingLocationPage.clickUpdateModalBtn();
	}

	@Then("^the location at row (\\d+) will be \"([^\"]*)\" \"([^\"]*)\"$")
	public void the_location_at_row_will_be(int index, String company, String location) throws Throwable {
	    assertTrue(settingLocationPage.isLocationUpdated(location, company, index));
	}
}
