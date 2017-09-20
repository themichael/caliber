package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;






public class AssessBatchPage{

	private PhantomJSDriver driver;
	private String URL;
	
	
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public AssessBatchPage(WebDriver driver){
		this.driver = (PhantomJSDriver)driver;
	}
	
	public void gotoPage() throws IOException, InterruptedException{
		driver.get("http://localhost:8080/caliber#/vp/category");
		
		URL = driver.getCurrentUrl();
		System.out.println("CurrentURL = " + driver.getCurrentUrl());
		System.out.println(driver.getTitle());
	}
	
	public void verifyAssessBatchPage() throws IOException, InterruptedException{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("C:/Users/KoredeA/Desktop/gitworkspace/pencilBefore.jpg"), true);
		assertEquals("http://localhost:8080/caliber#/vp/category", driver.getCurrentUrl());
		Thread.sleep(1000);
		File srcFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile1, new File("C:/Users/KoredeA/Desktop/gitworkspace/pencilAfter.jpg"), true);
	}
	
	public void enterFeedback(String response){
		System.out.println(driver.getCurrentUrl());
		WebElement textarea = driver.findElement(By.name("asdfjkl;"));
		textarea.sendKeys(response);
	}
	public void clickWeekTab(){
		System.out.println(driver.getCurrentUrl());
		
		WebElement tab = driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div/div[3]/ul/li[1]/a"));
		tab.click();
	}
	public void saveButton(){
		WebElement button = driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(2) > div > div > ul > ul > div.form-group.col-lg-12.col-md-12.col-sm-12.overall-feedback > div"));
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
	public void teardown(){
		driver.close();
	}
}
