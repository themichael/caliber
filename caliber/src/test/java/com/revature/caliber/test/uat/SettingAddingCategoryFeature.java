package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SettingAddingCategoryFeature {

	private SettingCategoryPage settingCategoryPage;

	@cucumber.api.java.Before
	public void setup() {
		ChromeDriverSetup driver = new ChromeDriverSetup();
		settingCategoryPage = new SettingCategoryPage(driver.getDriver());
	}

	@Given("^I am currently on the settings category page$")
	public void iAmOnTheSettingsCategoryPage() throws InterruptedException{
		settingCategoryPage.gotoSettingCategoryPage();
		settingCategoryPage.verifyCategoryPage();
	}
	
	@Given("^I click the create category button$")
	public void iClickTheCreateCategoryButton() throws InterruptedException {
	    settingCategoryPage.clickCreateCategoryBtn();
	}

	@Given("^I input \"([^\"]*)\" as a category$")
	public void iInputAsACategory(String name) throws InterruptedException {
	    settingCategoryPage.inputAddCategoryName(name);
	}

	@When("^I click on the submit button$")
	public void iClickOnTheSubmitButton() {
	    settingCategoryPage.clickCategorySaveBtn();
	}
	
	@Then("^A new Category \"([^\"]*)\" should be on the page$")
	public void aNewCategoryShouldBeOnThePage(String name) throws IOException{
	    assertTrue(settingCategoryPage.verifyCategoryAdded(name));
	}

	@When("^I click the X button$")
	public void iClickTheXButton() {
	    settingCategoryPage.closeCattegoryWithXButton();
	}

	@Then("^the category \"([^\"]*)\" will not be created$")
	public void theCategoryWillNotBeCreated(String name) throws IOException  {
	    assertFalse(settingCategoryPage.verifyCategoryAdded(name));
	}

}
