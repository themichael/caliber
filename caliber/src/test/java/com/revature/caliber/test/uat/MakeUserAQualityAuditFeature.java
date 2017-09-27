package com.revature.caliber.test.uat;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MakeUserAQualityAuditFeature {

	private SettingsTrainerPage trainerPage;
	private ChromeDriverSetup setup;
	
	@Before
	public void setup(){
		setup = new ChromeDriverSetup();
		trainerPage = new SettingsTrainerPage(setup.getDriver());
	}
	
	@Given("^I am logged on as VP$")
	public void i_am_logged_on_as_VP() throws Throwable {
	    trainerPage.gotoPage();
	}

	@Given("^I am on the Trainer Settings Pages$")
	public void i_am_on_the_Trainer_Settings_Pages() throws Throwable {
	    trainerPage.verifyPage();
	}

	@Given("^I click the edit Trainer icon for Genesis$")
	public void i_click_the_edit_Trainer_icon_for_Genesis() throws Throwable {
	    trainerPage.updateIcon();
	}
	
	@Given("^change the tier of the trainer to \"([^\"]*)\"$")
	public void change_the_tier_of_the_trainer_to(String tier) throws Throwable {
	    trainerPage.updateTier(tier);
	}

	@When("^I select the update button$")
	public void i_select_the_update_button() throws Throwable {
	    trainerPage.modalUpdateButton();
	}

	@Then("^the user has been changed to a \"([^\"]*)\"$")
	public void the_user_has_been_changed_to_a(String tier) throws Throwable {
	    trainerPage.checkTierChange(tier);
//	    trainerPage.teardown();
	}

}

