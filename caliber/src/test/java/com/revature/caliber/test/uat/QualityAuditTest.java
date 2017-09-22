package com.revature.caliber.test.uat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Test;

public class QualityAuditTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setup(){
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("phantomjs.binary.path", System.getenv("PHANTOM_BIN"));
		caps.setJavascriptEnabled(true);
		driver = new PhantomJSDriver(caps);
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
		System.out.println(driver.getCurrentUrl());
		qaPage.clickYearDropdown();
		qaPage.clickBatchTrainer();
		qaPage.clickWeeksForBatch();
		qaPage.clickAddWeeksForBatchButton();
		qaPage.clickIndividualFeedbackButton();
		qaPage.setNoteOnTraineeTextArea("");
		qaPage.clickOverallFeedbackQCButtonGood();
		qaPage.clickOverallFeedbackQCButtonAvg();
		qaPage.clickOverallFeedbackQCButtonPoor();
		qaPage.setOverallFeedbackQCNotes("");
		qaPage.clickSaveButton();
	}
}
