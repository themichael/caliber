package com.revature.caliber.test.uat;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QualityAuditBatchPerformanceFeature {
	
	public static WebDriver driver;

	public QualityAuditPage qaPage;
	
	@cucumber.api.java.Before
	public void setup(){
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("phantomjs.binary.path", System.getenv("PHANTOM_BIN"));
		caps.setJavascriptEnabled(true);
		driver = new PhantomJSDriver(caps);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		qaPage = new QualityAuditPage(driver);
	}
	
	@cucumber.api.java.After
	public void teardown(){
		qaPage.closeAndQuit();
	}
	
	@Given("^I am on the Quality Audit page$")
	public void i_am_on_the_Quality_Audit_page() throws Throwable {
	    qaPage.goToPage();
	    assertEquals("http://localhost:8080/caliber/#/vp/audit", driver.getCurrentUrl());
	}

	@Given("^I have selected the current year$")
	public void i_have_selected_the_current_year() throws Throwable {
	    qaPage.clickYearDropdown();
	    qaPage.verifyYear();
	}

	@Given("^I have selected a Batch$")
	public void i_have_selected_a_Batch() throws Throwable {
	    qaPage.clickBatch();
	    qaPage.verifyBatch();
	}

	@Given("^I am viewing the most recent week$")
	public void i_am_viewing_the_most_recent_week() throws Throwable {
	    qaPage.verifyWeekForBatch();
	}
	
	@Given("^I click on an overall batch feedback button$")
	public void i_click_on_an_overall_batch_feedback_buttons() throws Throwable {
	    qaPage.clickOverallFeedbackQCButtonPoor();
	    qaPage.clickOverallFeedbackQCButtonAvg();
	    qaPage.clickOverallFeedbackQCButtonGood();
	}

	@Given("^I enter \"([^\"]*)\" in the QC Feedback text area$")
	public void i_enter_in_the_QC_Feedback_text_area(String arg1) throws Throwable {
	    qaPage.setOverallFeedbackQCNotes(arg1);
	}

	@When("^I click the save button$")
	public void i_click_the_save_button() throws Throwable {
	    qaPage.clickSaveButton();
	}

	@Then("^the performance note is saved$")
	public void the_performance_note_is_saved() throws Throwable {
	    qaPage.goToPage();
	    Thread.sleep(10000);
	    qaPage.verifyQCNotes();
	}
	

}
