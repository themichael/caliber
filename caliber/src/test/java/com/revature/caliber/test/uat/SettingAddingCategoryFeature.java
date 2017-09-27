package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;
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
	public void i_am_on_the_settings_category_page() throws InterruptedException{
		settingCategoryPage.gotoSettingCategoryPage();
		settingCategoryPage.verifyCategoryPage();
	}
	
	@Given("^I click the create category button$")
	public void i_click_the_create_category_button() throws Throwable {
	    settingCategoryPage.clickCreateCategoryBtn();
	}

	@Given("^I input \"([^\"]*)\" as a category$")
	public void i_input_as_a_category(String name) throws Throwable {
	    settingCategoryPage.inputAddCategoryName(name);
	}

	@When("^I click on the submit button$")
	public void i_click_on_the_submit_button() throws Throwable {
	    settingCategoryPage.clickCategorySaveBtn();
	}

	@Then("^A new Category \"([^\"]*)\" should be on the page$")
	public void a_new_Category_should_be_on_the_page(String name) throws Throwable {
	    assertTrue(settingCategoryPage.verifyCategoryAdded(name));
	}

	@When("^I click the X button$")
	public void i_click_the_X_button() throws Throwable {
	    settingCategoryPage.closeCattegoryWithXButton();
	}

	@Then("^the category \"([^\"]*)\" will not be created$")
	public void the_category_will_not_be_created(String name) throws Throwable {
	    assertFalse(settingCategoryPage.verifyCategoryAdded(name));
	}

}
