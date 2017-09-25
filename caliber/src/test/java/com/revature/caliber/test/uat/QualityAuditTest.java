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
		driver.findElement(By.id("yearDropDownButton"));
		driver.findElement(By.id("2017"));
		driver.findElement(By.id("batchDropDown"));
		driver.findElement(By.id("Patrick Walsh - 2/14/17"));
		driver.findElement(By.id("week8"));
		driver.findElement(By.id("addWeekButton"));
		driver.findElement(By.id("indvFeedback-goodButton-0"));
		driver.findElement(By.id("indvFeedback-questionButton-1"));
		driver.findElement(By.id("noteTextArea-0"));
		driver.findElement(By.id("noteTextArea-1"));
		driver.findElement(By.id("good-QCButton"));
		driver.findElement(By.id("fair-QCButton"));
		driver.findElement(By.id("poor-QCButton"));
		driver.findElement(By.id("qcBatchNotes"));
		driver.findElement(By.id("saveButton"));
	}
}
