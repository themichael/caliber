package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AssessBatchPage{

	private WebDriver driver;
	private String URL;
	
	
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public AssessBatchPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void gotoPage(){
		driver.get("https://yoraikun.wordpress.com/2015/09/28/sevens-35/");
		URL = driver.getCurrentUrl();
		System.out.println("CurrentURL = " + driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@id='nav-above']/div[2]/a")).click();
	}
	
	public void verifyAssessBatchPage(){
		assertEquals("http://localhost:8080/caliber#/vp/assess", driver.getCurrentUrl());
	}
	
	public void enterFeedback(String response){
		System.out.println(driver.getCurrentUrl());
		WebElement textarea = driver.findElement(By.xpath("//*[@id='tBatchNotes']"));
		textarea.sendKeys(response);
	}
	public void clickWeekTab(){
		System.out.println(driver.getCurrentUrl());
		WebElement tab = driver.findElement(By.xpath("/html/body/div/ui-view/nav/div/ul[1]/li/a/img"));
		tab.click();
	}
	public void saveButton(){
		WebElement button = driver.findElement(By.xpath("//*[@id='trainer-assess-table]/div/div/ul/ul/div[3]/div/a/span"));
		button.click();
	}
	public void enterGrade(int grade){
		WebElement tab = driver.findElement(By.xpath("dfd"));
		tab.sendKeys(Integer.toString(grade));
	}
	public void enterTraineeNotes(String response){
		WebElement textarea = driver.findElement(By.xpath("//*[@id='tBatchNotes']"));
		textarea.sendKeys(response);
	}
}
