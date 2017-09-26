package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class EditSettingCategoryPage {
	
	private WebDriver driver;
	
	public EditSettingCategoryPage(WebDriver driver) {
		super();
<<<<<<< HEAD
		this.driver = driver;
=======
		this.driver = (ChromeDriver)driver;
		this.driver.get("http://localhost:8080/caliber/#/vp/category");
>>>>>>> e43553b34d08be3f14826f0e99ccca75cae8b541
	}
	
	/**
	 * Navigates to the Setting's Category page
	 */
	public void gotoSettingCategoryPage() {
		driver.get("http://localhost:8080/caliber/#/vp/category");
	}
	
	/**
     * Verifies that we are located on the Setting's Category page
     */
	public void verifyCategoryPage() {
		assertEquals("http://localhost:8080/caliber/#/vp/category", driver.getCurrentUrl());
	}
	
	/**
	 * Clicks on the category name and opens the modal so we can edit the category
	 * @throws InterruptedException
	 */
	public void clickOnCategoryName() throws InterruptedException {
		/* Headless Chrome
		driver.findElement(By
				.id("")).click();
		Thread.sleep(300);
		driver.switchTo().activeElement();
		*/
		
		driver.findElement(
				By.cssSelector("#trainer-assess-table > div > div:nth-child(1) > table > tbody > tr:nth-child(1) > td.ng-binding"))
				.click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Selects the Category Name field, and inputs the new string to what already exists in the field
	 * @param edit
	 */
	public void editCategoryName(String edit) {
		driver.findElement(By
				.id("categoryName")).click();
		driver.findElement(By
				.id("categoryName")).sendKeys(edit);
		
		/*
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-md-6.col-sm-6 > input"))
				.click();
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-md-6.col-sm-6 > input"))
				.sendKeys(edit);
		*/
	}
	
	/**
	 * Clicks on the Submit button in the modal to submit changes to the name field
	 */
	public void clickSubmitButton() {
		driver.findElement(By
				.id("submitBtn")).click();
		
		/*
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-footer > input"))
				.click();
		*/
	}
	
	/**
	 * Clicks on the checkbox in the modal to change the status (Active/Not Active) of the Category
	 */
	public void clickActiveCheckBox() {
		driver.findElement(By
				.id("active")).click();
		
		/*
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-sm-1.col-md-1 > input"))
				.click();
		*/
	}
	
	/**
	 * Clicks on the x-button to close the modal
	 */
	public void clickXButtonToClose() {
		driver.findElement(By
				.id("XBtn")).click();
		
		/*
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-header > button"))
				.click();
		*/
	}
	
	/**
	 * Clicks on the Close button to close the modal
	 */
	public void clickCloseButtonToClose() {
		driver.findElement(By
				.id("closeBtn")).click();
		
		/*
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-footer > button"))
				.click();
		*/
	}
	
	public void closeDriver() {
		driver.quit();
	}
}
