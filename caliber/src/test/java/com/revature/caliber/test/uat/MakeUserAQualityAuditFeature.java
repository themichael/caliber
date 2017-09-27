package com.revature.caliber.test.uat;

import java.io.IOException;
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
	public void iAmLoggedOnAsVP(){
	    trainerPage.gotoPage();
	}

	@Given("^I am on the Trainer Settings Pages$")
	public void iAmOnTheTrainerSettingsPages() throws Throwable {
	    trainerPage.verifyPage();
	}

	@Given("^I click the edit Trainer icon for Genesis$")
	public void iClickTheEditTrainerIconForGenesis() throws InterruptedException{
	    trainerPage.updateIcon();
	}
	
	@Given("^change the tier of the trainer to \"([^\"]*)\"$")
	public void changeTheTierOfTheTrainerTo(String tier) throws InterruptedException{
	    trainerPage.updateTier(tier);
	}

	@When("^I select the update button$")
	public void iSelectTheUpdateButton() throws IOException, InterruptedException{
	    trainerPage.modalUpdateButton();
	}

	@Then("^the user has been changed to a \"([^\"]*)\"$")
	public void theUserHasBeenChangedToA(String tier){
	    trainerPage.checkTierChange(tier);
	}

}

