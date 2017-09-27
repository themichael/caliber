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
	public void iAmOnTheSettingsLocationPages(){
	    settingLocationPage.gotoSettingLocationPage();
	    settingLocationPage.verifyLocationPage();
	}

	@Given("^the button in row (\\d+) in an X$")
	public void theButtonInRowInAnX(int index) {
	    assertTrue(settingLocationPage.verifyLocationIsActive(index));
	}

	@Given("^I click on the X-button to start the deactivation process in row (\\d+)$")
	public void iClickOnTheXButtonToStartTheDeactivationProcessInRow(int index) throws InterruptedException{
	    settingLocationPage.deactivateLocationBtn(index);
	}

	@When("^I click on the Cancel button$")
	public void iClickOnTheCancelButton() {
	    settingLocationPage.clickDeactivateInDeactivateModalBtn();
	}

	@Then("^I will be back on the location setting page$")
	public void iWillBeBackOnTheLocationSettingPage() {
	    settingLocationPage.verifyLocationPage();
	}

	@Given("^the button in row (\\d+) in a check mark$")
	public void theButtonInRowInACheckMark(int index) {
	    assertTrue(settingLocationPage.verifyLocationIsInActive(index));
	}

	@Given("^I click on the X-button to start reactivating the location in row (\\d+)$")
	public void iClickOnTheXButtonToStartDeactivatingTheLocationInRow(int index) throws InterruptedException {
	    settingLocationPage.clickCheckReactivateLocationBtn(index);
	}

	@When("^I click on the x-button to cancel$")
	public void iClickOnTheXButtonToCancel() {
	    settingLocationPage.clickXCloseActivationDeactivateModal();
	}

	@Then("^I will be back on the location page$")
	public void iWillBeBackOnTheLocationPage() {
	    settingLocationPage.verifyLocationPage();
	}

	@Given("^I click on the X-button to begin the deactivation process in row (\\d+)$")
	public void iClickOnTheXButtonToBeginTheDeactivationProcessInRow(int index) throws InterruptedException {
	    settingLocationPage.deactivateLocationBtn(index);
	}

	@When("^I click on the Deactivation button$")
	public void iClickOnTheDeactivationButton()  {
	    settingLocationPage.clickDeactivateInDeactivateModalBtn();
	}

	@Then("^I will change the location status of (\\d+) to inactive$")
	public void iWillChangeTheLocationStatusToInactive(int index) {
	    assertTrue(settingLocationPage.verifyLocationIsInActive(index));
	}

	@Given("^I click on the check-button to begin the reactivation process in row (\\d+)$")
	public void iClickOnTheCheckButtonToBeginTheReactivationProcessInRow(int index) throws InterruptedException {
	    settingLocationPage.clickCheckReactivateLocationBtn(index);
	}

	@When("^I click on the Reactivate button$")
	public void iClickOnTheReactivateButton() {
	    settingLocationPage.clickReactivateLocationBtn();
	}

	@Then("^I will change the location status of (\\d+) to active$")
	public void iWillChangeTheLocationStatusToActive(int index) {
	    assertTrue(settingLocationPage.verifyLocationIsActive(index));
	}

}
