package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SettingCategoryPage {

	private WebDriver driver;
	
	public SettingCategoryPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver)driver;
		this.driver.get(System.getenv("CALIBER_API_SERVER")+"caliber/#/vp/category");
	}
	
	/**
	 * Navigates to the Setting's Category page
	 * @throws InterruptedException 
	 */
    public void gotoSettingCategoryPage() throws InterruptedException {
        driver.get(System.getenv("CALIBER_API_SERVER")+"caliber/#/vp/category");
        Thread.sleep(1000);
        driver.switchTo().activeElement();
        //System.out.println("CurrentURL = " + driver.getCurrentUrl());
        //System.out.println(driver.getTitle());
    }
    
    /**
     * Verifies that we are located on the Setting's Category page
     */
    public void verifyCategoryPage() {
        assertEquals(System.getenv("CALIBER_API_SERVER")+"caliber/#/vp/category", driver.getCurrentUrl());
    }
    
    /**
     * Clicks on the Create button to create a new category
     * @throws InterruptedException
     */
    public void clickCreateCategoryBtn() throws InterruptedException {
    	driver.findElement(By
    			.id("createCategoryModalX")).click();
    	Thread.sleep(1000);
    	driver.switchTo().activeElement();
    }
    
    /**
     * Finds the Category name field, and edits it by adding the new name
     * @param name
     * @throws InterruptedException 
     */

    public void inputAddCategoryName(String name) throws InterruptedException {
    	//driver.findElement(By.cssSelector("#addCategoryModal > div > div > div.modal-body > div > div.row > div > input")).sendKeys(name);
    	driver.findElement(By.id("addCategoryName")).sendKeys(name);
    }
    
    public void inputEditCategoryName(String name){
    	WebElement category = driver.findElement(By.id("editCategoryName"));
    	category.clear();
    	category.sendKeys(name);
    }
    
    public void editCategorySaveButton(){
    	driver.findElement(By.id("editCategorySubmitBtn")).click();
    	driver.switchTo().activeElement();
    }
    
    public void editCategoryClickCheckbox(){
    	driver.findElement(By.id("categoryIsActiveCheckBox")).click();
    }
    
    public void editCategoryXButton(){
    	driver.findElement(By.id("editCategoryXBtn")).click();
    	driver.switchTo().activeElement();
    }
    
    public void editCategoryCloseButton(){
    	driver.findElement(By.id("editCategoryCloseBtn")).click();
    	driver.switchTo().activeElement();
    }
    
    public boolean checkIfCategoryIsActive(String name){
    	String actual = driver.findElement(By.id(name)).getAttribute("value").toString();
    	return (actual.equals("true")) ? true : false;
    }
    
    public void inputCategoryName(String name) throws InterruptedException {
    	driver.findElement(By.cssSelector("#addCategoryModal > div > div > div.modal-body > div > div.row > div > input")).sendKeys(name);
    }
    
    /**
     * Clicks on the save button to save the new category
     */
    public void clickCategorySaveBtn() {
    	driver.findElement(By.id("addCategorySubmitBtn")).click();
    	driver.switchTo().activeElement();
    }
    
    /**
     * Creates a jpg file of the screenshot to check that we created the category
     * @throws IOException
     */
    public boolean verifyCategoryAdded(String name) throws IOException {
		boolean exists;
		try{
			driver.findElement(By.id(name));
			exists = true;
		}catch(NoSuchElementException e){
			exists = false;
		}
		return exists;
    }
    
    /**
     * Clicks on the x-button to close out of the Create Category modal
     */
    public void closeCattegoryWithXButton() {
    	driver.findElement(
    			By.cssSelector("#addCategoryModal > div > div > div.modal-header > button"))
    			.click();

    }
    
    /**
     * Closes the Create Category modal by clicking the Close button
     */
    public void closeCategoryWithCloseButton() {
    	driver.findElement(
    			By.cssSelector("#deleteLocationModal > div > div > div.modal-footer > button"))
    			.click();
    }
    
    /**
     * Verifies that the modal was closed using the x-button
     * @throws IOException
     */
    public void verifyClosedOutByX() throws IOException {
    	/*
    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(srcFile,
    			new File("~/Desktop/xOutCategoryNotCreated.jpg"), true);
    	*/
    }
    
    /**
     * Verifies that the modal was closed using the Close button
     * @throws IOException
     */
    public void verifyClosedOutByCloseButton() throws IOException {
    	/*
    	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(srcFile,
    			new File("~/Desktop/closeButtonCategoryNotCreated.jpg"), true);
    	*/
    }
    
    public void clickCategoryName(String name) throws InterruptedException{
    	driver.findElement(By.id(name)).click();
    	Thread.sleep(500);
    	driver.switchTo().activeElement();
    }
    
    public void closeDriver() {
        driver.quit();
    }
}

