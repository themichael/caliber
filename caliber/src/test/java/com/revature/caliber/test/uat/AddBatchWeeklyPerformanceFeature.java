package com.revature.caliber.test.uat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddBatchWeeklyPerformanceFeature {

	private AssessBatchPage assessBatchPage;
	private SettingsTrainerPage trainerPage;
	
	@Before
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1200x600");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assessBatchPage = new AssessBatchPage(driver);
	}
	
	@Given("^I am in the Assess Batch Page$")
	public void i_am_in_the_Assess_Batch_Page() throws Throwable {
		assessBatchPage.goToPage();
		assessBatchPage.verifyAssessPage();
		assessBatchPage.clickCreateAssessment();
	}

	@Given("^I have chosen the Week tab$")
	public void i_have_chosen_the_Week_tab() throws Throwable {

	}
	
	@Given("^I have entered \"([^\"]*)\" as the Overall Feedback$")
	public void iHaveEnteredAsTheOverallFeedback(String feedback) throws InterruptedException, IOException{
		//assessBatch.enterFeedback("kjdkjd");	
	}

	@When("^I press the Save button$")
	public void i_press_the_Save_button() throws Throwable {
		//assessBatch.saveButton();	
	}

	@Then("^the Feedback is recorded$")
	public void theFeedbackIsRecorded() throws Throwable {
	
	}

	@After
	public void teardown(){
		assessBatchPage.teardown();
	}

}
