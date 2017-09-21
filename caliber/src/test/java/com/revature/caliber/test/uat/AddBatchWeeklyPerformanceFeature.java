package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddBatchWeeklyPerformanceFeature {

	private AssessBatchPage assessBatch;
	
	@cucumber.api.java.Before
	public void setup(){
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("phantomjs.binary.path", System.getenv("PHANTOM_BIN"));
		caps.setJavascriptEnabled(true);
		WebDriver driver = new PhantomJSDriver(caps);
		driver.manage().window().setSize(new Dimension(1200, 1000));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assessBatch = new AssessBatchPage(driver);
	}
	
	@Given("^I am in the Assess Batch Page$")
	public void i_am_in_the_Assess_Batch_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assessBatch.gotoPage();
		assessBatch.verifyAssessBatchPage();
	}

	@Given("^I have chosen the Week tab$")
	public void i_have_chosen_the_Week_tab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assessBatch.clickWeekTab();
	}
	
	@Given("^I have entered \"([^\"]*)\" as the Overall Feedback$")
	public void iHaveEnteredAsTheOverallFeedback(String feedback){
		// Write code here that turns the phrase above into concrete actions
		//assessBatch.enterFeedback("kjdkjd");
	}

	@When("^I press the Save button$")
	public void i_press_the_Save_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete action
		//assessBatch.saveButton();
	}

	@Then("^the Feedback is recorded$")
	public void theFeedbackIsRecorded() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	
	}

	@After
	public void teardown(){
		assessBatch.teardown();
	}
}
