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

public class SettingAddingCategoryFeature {

	private WebDriver webdriver;
	private SettingCategoryPage settingCategoryPage;

	@cucumber.api.java.Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
        ChromeOptions options = new ChromeOptions();
         //options.addArguments("--headless");
        options.addArguments("--window-size=1200x600");
        WebDriver driver = new ChromeDriver(options);
		//ChromeDriverSetup setup = new ChromeDriverSetup();
		settingCategoryPage = new SettingCategoryPage(driver);
	}

	@cucumber.api.java.After
	public void teardown() {
		settingCategoryPage.closeDriver();
	}
	
	@Given("^I am on the Settings Category page$")
	public void i_am_on_the_Settings_Category_page() throws InterruptedException {
		settingCategoryPage.gotoSettingCategoryPage();
		settingCategoryPage.verifyCategoryPage();
	}

	@When("^I click on the Create button$")
	public void i_click_on_the_Create_button() throws Throwable {
		settingCategoryPage.clickCreateCategoryBtn();
	}

	@When("^I input \"([^\"]*)\" as a category$")
	public void i_input_as_a_category(String categoryName) throws InterruptedException {
		settingCategoryPage.inputCategoryName(categoryName);
	}
	
	@When("^I click on the Submit button$")
	public void i_click_on_the_Submit_button() throws Throwable {
		settingCategoryPage.clickCategorySaveBtn();
	}

	@Then("^I should get a new category on the page$")
	public void i_should_get_a_new_category_on_the_page() throws Throwable {
		//settingCategoryPage.verifyCategoryAdded();
	}

	@When("^I click on the X button$")
	public void i_click_on_the_X_button() throws Throwable {
		//settingCategoryPage.closeCattegoryWithXButton();
	}

	@Then("^I should be back on the Settings Category page$")
	public void i_should_be_back_on_the_Settings_Category_page() throws Throwable {
		//settingCategoryPage.verifyClosedOutByX();
	}

	@When("^I click on the Cancel button$")
	public void i_click_on_the_Cancel_button() throws Throwable {
		settingCategoryPage.closeCategoryWithCloseButton();
	}

	@Then("^I am back on the Settings Category page$")
	public void i_am_back_on_the_Settings_Category_page() throws Throwable {
		settingCategoryPage.closeCategoryWithCloseButton();
	}
/*
	@Given("^I am on the Settings Category page$")
	public void i_am_on_the_Settings_Category_page() {
		settingCategoryPage.gotoSettingCategoryPage();
		settingCategoryPage.verifyCategoryPage();
	}

	@When("^I click on the Create button$")
	public void i_click_on_the_Create_button() throws InterruptedException {
		settingCategoryPage.clickCreateCategoryBtn();
	}

	@When("^I input \"([^\"]*)\" as a category$")
	public void i_input_as_a_category(String name) {
		settingCategoryPage.inputCategoryName(name);
	}

	@Then("^I should get a new category on the page$")
	public void i_should_get_a_new_category_on_the_page() {
		settingCategoryPage.clickCategorySaveBtn();
	}
*/
}
