package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AssessBatchPage {

	private HtmlUnitDriver driver;
	
	public AssessBatchPage(HtmlUnitDriver driver){
		this.driver = driver;
	}
	
	public void gotoPage(){
		driver.navigate().to("http://localhost:8080/caliber#/vp/assess");
	}
	
	public void verifyAssessBatchPage(){
		assertEquals("http://localhost:8080/caliber#/vp/assess", driver.getCurrentUrl());
	}
	public void enterFeedback(String response){
		driver.findElement(By.id("tBatchNotes")).sendKeys(response);
	}
	public void saveButton(){
//		driver.findElement(By.xpath("//*[@id=\"trainer-assess-table\"]/div/div/ul/ul/div[3]/div/a")).click();
//		driver.findElement(By.cssSelector("#trainer-assess-table > div > div > ul > ul > div.form-group.col-lg-12.col-md-12.col-sm-12.overall-feedback > div > a")).click();
		driver.findElement(By.className("save-button fade-in")).click();
	}
}
