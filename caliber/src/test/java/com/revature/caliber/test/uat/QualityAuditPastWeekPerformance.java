package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QualityAuditPastWeekPerformance {
	
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
	
	@Given("^I have navigated to the quality audit page$")
	public void iHaveNavigatedToTheQualityAuditPage() throws Throwable {
	   qaPage.goToPage();
	}

	@Given("^I have selected the year to view$")
	public void iHaveSelectedTheYearToView() throws Throwable {
	    qaPage.clickYearDropdown("2017");
	    qaPage.verifyYear("2017");
	}

	@Given("^I have selected the batch to view$")
	public void iHaveSelectedTheBatchToView() throws Throwable {
	    qaPage.clickBatch("Patrick Walsh - 2/14/17");
	    qaPage.verifyBatch("Patrick Walsh - 2/14/17");
	}

	@When("^I click on a previous week tab$")
	public void iClickOnAPreviousWeekTab() throws Throwable {
	    for(int i=8; i>= 1; i--){
	    	qaPage.clickWeeksForBatch(i);
	    	qaPage.verifyWeekForBatch("week"+i);
	    }
	}

	@Then("^I will be able to see the previous performance for that week$")
	public void iWillBeAbleToSeeThePreviousPerformanceForThatWeek() throws Throwable {
	    qaPage.verifyQCNotes();
	}

	@Given("^I am on the quality audit page$")
	public void iAmOnTheQualityAuditPage() throws Throwable {
	    qaPage.goToPage();
	}

	@When("^I try to click on a previous week tab$")
	public void iTryToClickOnAPreviousWeekTab() throws Throwable {
	    qaPage.verifyWeekForBatch("week1");
	}

	@Then("^I will not be able to see the previous weeks since there are none$")
	public void iWillNotBeAbleToSeeThePreviousWeeksSinceThereAreNone() throws Throwable {
	    qaPage.verifyWeekForBatch("week1");
	}

}
