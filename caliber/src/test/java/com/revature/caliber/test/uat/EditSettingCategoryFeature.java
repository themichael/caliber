package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;
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
	public void i_am_on_the_settings_category_page() throws Throwable {
	    settingCategoryPage.gotoSettingCategoryPage();
	    settingCategoryPage.verifyCategoryPage();
	    }

	@Given("^I click on the \"([^\"]*)\" Category Name$")
	public void i_click_on_the_Category_Name(String name) throws Throwable {
	    settingCategoryPage.clickCategoryName(name);
	}

	@Given("^I add \"([^\"]*)\" to the current name field$")
	public void i_add_to_the_current_name_field(String name) throws Throwable {
	   settingCategoryPage.inputEditCategoryName(name);
	}

	@When("^I click the Submit button$")
	public void i_click_the_Submit_button() throws Throwable {
	    settingCategoryPage.editCategorySaveButton();
	}

	@Then("^the new category should be \"([^\"]*)\"$")
	public void the_new_category_should_be(String name) throws Throwable {
	    assertTrue(settingCategoryPage.verifyCategoryAdded(name));
	}

	@Given("^I click on the \"([^\"]*)\" Category$")
	public void i_click_on_the_Category(String name) throws Throwable {
	    settingCategoryPage.clickCategoryName(name);
	}

	@Given("^I click on the Active checkbox$")
	public void i_click_on_the_Active_checkbox() throws Throwable {
	    settingCategoryPage.editCategoryClickCheckbox();
	}

	@When("^I click on the Submit Button$")
	public void i_click_on_the_Submit_Button() throws Throwable {
	    settingCategoryPage.editCategorySaveButton();
	}

	@Then("^the category \"([^\"]*)\" should be inactive$")
	public void the_category_should_be_inactive(String name) throws Throwable {
	    assertFalse(settingCategoryPage.checkIfCategoryIsActive(name));
	}

	@Given("^I click on the \"([^\"]*)\" Category box$")
	public void i_click_on_the_Category_box(String name) throws Throwable {
	    settingCategoryPage.clickCategoryName(name);
	}

	@When("^I click on the X-out button$")
	public void i_click_on_the_X_out_button() throws Throwable {
	    settingCategoryPage.editCategoryXButton();
	}

	@Then("^I exited the Edit Category modal without editing$")
	public void i_exited_the_Edit_Category_modal_without_editing() throws Throwable {
	    settingCategoryPage.verifyCategoryPage();
	}

	@Given("^I click on the \"([^\"]*)\" Category link$")
	public void i_click_on_the_Category_link(String name) throws Throwable {
	    settingCategoryPage.clickCategoryName(name);
	}

	@When("^I click the Modal Close button$")
	public void i_click_the_Modal_Close_button() throws Throwable {
	    settingCategoryPage.editCategoryCloseButton();
	}

	@Then("^I exited the edit Category modal without editing$")
	public void i_exited_the_edit_Category_modal_without_editing() throws Throwable {
	    settingCategoryPage.verifyCategoryPage();
	}
}
