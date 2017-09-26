package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditSettingCategoryFeature {
	
	private WebDriver webdriver;
	private EditSettingCategoryPage editSettingCategoryPage;
	
	@cucumber.api.java.Before
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		editSettingCategoryPage = new EditSettingCategoryPage(setup.getDriver());
	}

//	@cucumber.api.java.After
//	public void teardown() {
//		editSettingCategoryPage.closeDriver();
//	}
	
	@Given("^I am on the settings category page$")
	public void i_am_on_the_settings_category_page() {
		editSettingCategoryPage.gotoSettingCategoryPage();
		editSettingCategoryPage.verifyCategoryPage();
	}

	@When("^I click on the Category Name$")
	public void i_click_on_the_Category_Name() throws InterruptedException {
		editSettingCategoryPage.clickOnCategoryName();
	}

	@When("^I add \"([^\"]*)\" to the current name field$")
	public void i_add_to_the_current_name_field(String edit) {
		editSettingCategoryPage.editCategoryName(edit);
	}

	@When("^I click the Submit button$")
	public void i_click_the_Submit_button() {
		editSettingCategoryPage.clickSubmitButton();
	}

	@Then("^I should get the category with a different name$")
	public void i_should_get_the_category_with_a_different_name() {
		//editSettingCategoryPage
	}

	@When("^I click on the Active checkbox$")
	public void i_click_on_the_Active_checkbox() {
		editSettingCategoryPage.clickActiveCheckBox();
	}

	@When("^I click on the Submit Button$")
	public void i_click_on_the_Submit_Button() {
		editSettingCategoryPage.clickSubmitButton();
	}

	@Then("^I changed the category to an inactive state$")
	public void i_changed_the_category_to_an_inactive_state() {
		//editSettingCategoryPage
	}

	@When("^I click on the X-out button$")
	public void i_click_on_the_X_out_button() {
		editSettingCategoryPage.clickXButtonToClose();
	}

	@Then("^I exited the Edit Category modal without editing$")
	public void i_exited_the_Edit_Category_modal_without_editing() {
		//editSettingCategoryPage
	}

	@When("^I click the Modal Close button$")
	public void i_click_the_Modal_Close_button() {
		editSettingCategoryPage.clickCloseButtonToClose();
	}

	@Then("^I exited the edit Category modal without editing$")
	public void i_exited_the_edit_Category_modal_without_editing() {
		//editSettingCategoryPage
	}

}
