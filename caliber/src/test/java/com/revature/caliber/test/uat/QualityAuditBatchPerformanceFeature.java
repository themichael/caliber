package com.revature.caliber.test.uat;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		qaPage = new QualityAuditPage(driver);
	}
	
	@cucumber.api.java.After
	public void teardown(){
		qaPage.closeDriver();
	}
	
	@Given("^I am on the Quality Audit page$")
	public void iAmOnTheQualityAuditPage() throws Throwable {
	    qaPage.goToPage();
	    assertEquals("http://localhost:8080/caliber/#/vp/audit", driver.getCurrentUrl());
	}

	@Given("^I have selected the current year$")
	public void iHaveSelectedTheCurrentYear() throws Throwable {
	    qaPage.clickYearDropdown();
	    qaPage.verifyYear();
	}

	@Given("^I have selected a Batch$")
	public void iHaveSelectedABatch() throws Throwable {
	    qaPage.clickBatch();
	    qaPage.verifyBatch();
	}

	@Given("^I am viewing the most recent week$")
	public void iAmViewingTheMostRecentWeek() throws Throwable {
	    qaPage.verifyWeekForBatch();
	}
	
	@Given("^I click on an overall batch feedback button$")
	public void iClickOnAnOverallBatchFeedbackButtons() throws Throwable {
	    qaPage.clickOverallFeedbackQCButtonPoor();
	    qaPage.clickOverallFeedbackQCButtonAvg();
	    qaPage.clickOverallFeedbackQCButtonGood();
	}

	@Given("^I enter \"([^\"]*)\" in the QC Feedback text area$")
	public void iEnterInTheQCFeedbackTextArea(String arg1) throws Throwable {
	    qaPage.setOverallFeedbackQCNotes(arg1);
	}

	@When("^I click the save button$")
	public void iClickTheSaveButton() throws Throwable {
	    qaPage.clickSaveButton();
	}

	@Then("^the performance note is saved$")
	public void thePerformanceNoteIsSaved() throws Throwable {
	    qaPage.goToPage();
	    Thread.sleep(10000);
	    qaPage.verifyQCNotes();
	}
	
}
