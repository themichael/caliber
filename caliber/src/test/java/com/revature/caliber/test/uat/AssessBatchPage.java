package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;






public class AssessBatchPage{

	private WebDriver driver;
	
	public AssessBatchPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver)driver;
	}

	public void goToPage(String page){
		driver.get("http://localhost:8080/caliber#/"+ page);
	}
	
	public void verifyAssessPage(){
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/caliber#/vp/assess");
	}
	
	public void clickCreateAssessment(){
		driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.pull-right > a")).click();
		driver.findElement(By.id("myModalLabel"));
	}
	
	public void teardown(){
		driver.quit();
	}
}
