package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
		qaPage.verifyWeekForBatch();
	}
}
