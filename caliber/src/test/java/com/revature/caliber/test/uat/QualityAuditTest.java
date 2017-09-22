package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.junit.Test;

public class QualityAuditTest {
	
	private static WebDriver driver;
	
	public void clickWeeksForBatch(int week){
		//currently clicks week 8, change last /li[x] where x is the week; will click new week if set to last element in the list
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li["+ week +"]"))
			.click();
	}
	
	public void verifyWeekForBatch(){
		String weekTab;
		boolean	selected = false;
		int week = 1;
		for(; week <=9; week++){
			weekTab = driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li["+ week +"]")).getAttribute("class");
			if(weekTab == "active"){
				selected = true;
				break;
			}
		}
		assertTrue(selected = true);
		
	}
	
	@BeforeClass
	public static void setup(){
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("phantomjs.binary.path", System.getenv("PHANTOM_BIN"));
		caps.setJavascriptEnabled(true);
		driver = new PhantomJSDriver(caps);
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
		driver.get("http://localhost:8080/caliber/#/vp/assess");
		System.out.println(driver.getCurrentUrl());
		clickWeeksForBatch(5);
		verifyWeekForBatch();
		System.out.println( driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li[5]")).getAttribute("class"));
		clickWeeksForBatch(6);
		verifyWeekForBatch();
		System.out.println( driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li[6]")).getAttribute("class"));
		
		
		
	}
}
