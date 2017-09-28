package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditSettingCategoryFeature {
	
	private SettingCategoryPage settingCategoryPage;
	
	@cucumber.api.java.Before
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		settingCategoryPage = new SettingCategoryPage(setup.getDriver());
	}

	@Given("^I am on the settings category page$")
	public void iAmOnTheSettingsCategoryPage() throws InterruptedException {
	    settingCategoryPage.gotoSettingCategoryPage();
	    settingCategoryPage.verifyCategoryPage();
	    }

	@Given("^I click on the \"([^\"]*)\" Category Name$")
	public void iClickOnTheCategoryName(String name) throws InterruptedException {
	    settingCategoryPage.clickCategoryName(name);
	}

	@Given("^I add \"([^\"]*)\" to the current name field$")
	public void iAddToTheCurrentNameField(String name) {
	   settingCategoryPage.inputEditCategoryName(name);
	}

	@When("^I click the Submit button$")
	public void iClickTheSubmitButton() {
	    settingCategoryPage.editCategorySaveButton();
	}

	@Then("^the new category should be \"([^\"]*)\"$")
	public void theNewCategoryShouldBe(String name) throws IOException  {
	    assertTrue(settingCategoryPage.verifyCategoryAdded(name));
	}

	@Given("^I click on the \"([^\"]*)\" Category$")
	public void iClickOnTheCategory(String name) throws InterruptedException {
	    settingCategoryPage.clickCategoryName(name);
	}

	@Given("^I click on the Active checkbox$")
	public void iClickOnTheActiveCheckbox() {
	    settingCategoryPage.editCategoryClickCheckbox();
	}

	@When("^I click on the Submit Button$")
	public void iClickOnTheSubmitButton() {
	    settingCategoryPage.editCategorySaveButton();
	}

	@Then("^the category \"([^\"]*)\" should be inactive$")
	public void theCategoryShouldBeInactive(String name) {
	    assertFalse(settingCategoryPage.checkIfCategoryIsActive(name));
	}

	@Given("^I click on the \"([^\"]*)\" Category box$")
	public void iClickOnTheCategoryBox(String name) throws InterruptedException{
	    settingCategoryPage.clickCategoryName(name);
	}

	@When("^I click on the X-out button$")
	public void iClickOnTheXOutButton() throws Throwable {
	    settingCategoryPage.editCategoryXButton();
	}

	@Then("^I exited the Edit Category modal without editing$")
	public void iExitedTheEditCategoryModalWithoutEditing() {
	    settingCategoryPage.verifyCategoryPage();
	}

	@Given("^I click on the \"([^\"]*)\" Category link$")
	public void iClickOnTheCategoryLink(String name) throws InterruptedException {
	    settingCategoryPage.clickCategoryName(name);
	}

	@When("^I click the Modal Close button$")
	public void iClickTheModalCloseButton(){
	    settingCategoryPage.editCategoryCloseButton();
	}

	@Then("^I exit the edit Category modal without editing$")
	public void iExitTheEditCategoryModalWithoutEditing(){
	    settingCategoryPage.verifyCategoryPage();
	}
}
