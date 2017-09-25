package com.revature.caliber.test.uat;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QualityAuditTraineePerfromanceFeature {

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
	
	@Given("^I am on the Quality Audit Page$")
	public void i_am_on_the_Quality_Audit_Page() throws Throwable {
		qaPage.goToPage();
	    assertEquals("http://localhost:8080/caliber/#/vp/audit", driver.getCurrentUrl());
	}

	@Given("^I have selected the current year for year$")
	public void i_have_selected_current_year() throws Throwable {
		qaPage.clickYearDropdown();
	    qaPage.verifyYear();
	}
	
	@Given("^I have selected the current Batch$")
	public void i_have_selected_the_current_Batch() throws Throwable {
		qaPage.clickBatch();
	    qaPage.verifyBatch();
	}

	@Given("^I am on the most current week$")
	public void i_am_on_the_most_current_week() throws Throwable {
		qaPage.verifyWeekForBatch();
	}

	@Given("^have entered \"([^\"]*)\" in Trainees note area$")
	public void have_entered_in_Trainees_note_area(String arg1) throws Throwable {
	    qaPage.setNoteOnTraineeTextArea(arg1);
	}

	@Given("^I click on the individual feedback button to the desried state$")
	public void i_click_on_the_individual_feedback_button_to_the_desried_state() throws Throwable {
	    qaPage.clickIndividualFeedbackButton();
	}

	@When("^I click the save button at the bottom of the page$")
	public void i_click_the_save_button_at_the_bottom_of_the_page() throws Throwable {
	    qaPage.clickSaveButton();
	}

	@Then("^the performance notes will be saved$")
	public void the_performance_notes_will_be_saved() throws Throwable {
	    qaPage.goToPage();
	    Thread.sleep(10000);
	    qaPage.verifyTraineeNotes();
	}
}
