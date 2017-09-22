package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class SettingCategoryPage {
	
	//private PhantomJSDriver driver;
	private WebDriver driver;
	
	public SettingCategoryPage(WebDriver driver) {
		super();
		this.driver = (PhantomJSDriver) driver;
	}
	
    public void gotoSettingCategoryPage() {
        driver.get("http://localhost:8080/caliber/#/vp/category");
        //System.out.println("CurrentURL = " + driver.getCurrentUrl());
        //System.out.println(driver.getTitle());
    }
    public void verifyCategoryPage() {
        assertEquals("http://localhost:8080/caliber/#/vp/category", driver.getCurrentUrl());
    }
    public void clickCreateCategoryBtn() throws InterruptedException {
        driver.findElement(
                By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div > div > ul > li > a"))
                .click();
        Thread.sleep(500);
        driver.switchTo().activeElement();
    }
    public void inputCategoryName(String name) {
        driver.findElement(By.id("categoryName")).sendKeys(name);
    }
    public void clickCategorySaveBtn() {
        driver.findElement(
                By.cssSelector("#addCategoryModal > div > div > div.modal-body > div > div.modal-footer > input"))
                .click();
    }
    
    public void verifyCategoryAdded() throws IOException {
    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(srcFile,
    			new File("~/Desktop/addedCategory.jpg"), true);
    }
    
    public void closeCattegoryWithXButton() {
    	driver.findElement(
    			By.cssSelector("#addCategoryModal > div > div > div.modal-header > button"))
    			.click();
    }
    
    public void closeCategoryWithCloseButton() {
    	driver.findElement(
    			By.cssSelector("#addCategoryModal > div > div > div.modal-body > div > div.modal-footer > button"))
    			.click();
    }
    
    public void verifyClosedOutByX() throws IOException {
    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(srcFile,
    			new File("~/Desktop/xOutCategoryNotCreated.jpg"), true);
    }
    
    public void verifyClosedOutByCloseButton() throws IOException {
    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(srcFile,
    			new File("~/Desktop/closeButtonCategoryNotCreated.jpg"), true);
    }
    
    public void quitDriver() {
        driver.quit();
    }
}

