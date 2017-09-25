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

public class QualityAuditTraineePerfromanceFeature {

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
	
	@Given("^I am on the Quality Audit Page$")
	public void iAmOnTheQualityAuditPage() throws Throwable {
		qaPage.goToPage();
	    assertEquals("http://localhost:8080/caliber/#/vp/audit", driver.getCurrentUrl());
	}

	@Given("^I have selected the current year for year$")
	public void iHaveSelectedCurrentYear() throws Throwable {
		qaPage.clickYearDropdown();
	    qaPage.verifyYear();
	}
	
	@Given("^I have selected the current Batch$")
	public void iHaveSelectedTheCurrentBatch() throws Throwable {
		qaPage.clickBatch();
	    qaPage.verifyBatch();
	}

	@Given("^I am on the most current week$")
	public void iAmOnTheMostCurrentWeek() throws Throwable {
		qaPage.verifyWeekForBatch();
	}

	@Given("^have entered \"([^\"]*)\" in Trainees note area$")
	public void haveEnteredInTraineesNoteArea(String arg1) throws Throwable {
	    qaPage.setNoteOnTraineeTextArea(arg1);
	}

	@Given("^I click on the individual feedback button to the desried state$")
	public void iClickOnTheIndividualFeedbackButtonToTheDesriedState() throws Throwable {
	    qaPage.clickIndividualFeedbackButton();
	}

	@When("^I click the save button at the bottom of the page$")
	public void iClickTheSaveButtonAtTheBottomOfThePage() throws Throwable {
	    qaPage.clickSaveButton();
	}

	@Then("^the performance notes will be saved$")
	public void thePerformanceNotesWillBeSaved() throws Throwable {
	    qaPage.goToPage();
	    Thread.sleep(10000);
	    qaPage.verifyTraineeNotes();
	}
}
