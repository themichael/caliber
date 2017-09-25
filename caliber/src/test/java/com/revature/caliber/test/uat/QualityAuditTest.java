package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Test;

public class QualityAuditTest {
	
	private static WebDriver driver;

	@BeforeClass
	public static void setup(){
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1200x600");
        driver = new ChromeDriver(options);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterClass
	public static void teardown(){
		driver.quit();
	}
	
	@Test
	public void test() throws IOException, InterruptedException{
		QualityAuditPage qaPage = new QualityAuditPage(driver);
		qaPage.goToPage();
		qaPage.clickYearDropdown();
		qaPage.clickBatch();
		qaPage.clickAddWeeksForBatchButton();
		qaPage.clickIndividualFeedbackButton();
		qaPage.setNoteOnTraineeTextArea("Test string");
		qaPage.clickOverallFeedbackQCButtonGood();
		qaPage.clickOverallFeedbackQCButtonAvg();
		qaPage.clickOverallFeedbackQCButtonPoor();
		qaPage.setOverallFeedbackQCNotes("Test String");
		qaPage.clickSaveButton();
	}
}
