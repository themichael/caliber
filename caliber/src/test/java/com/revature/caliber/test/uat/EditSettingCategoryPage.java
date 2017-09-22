package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class EditSettingCategoryPage {
	
	private WebDriver driver;
	
	public EditSettingCategoryPage(WebDriver driver) {
		super();
		this.driver = (PhantomJSDriver) driver;
	}
	
	public void gotoSettingCategoryPage() {
		driver.get("http://localhost:8080/caliber/#/vp/category");
	}
	
	public void verifyCategoryPage() {
		assertEquals("http://localhost:8080/caliber/#/vp/category", driver.getCurrentUrl());
	}
	
	public void clickOnCategoryName() throws InterruptedException {
		driver.findElement(
				By.cssSelector("#trainer-assess-table > div > div:nth-child(1) > table > tbody > tr:nth-child(1) > td.ng-binding"))
				.click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	public void editCategoryName(String edit) {
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-md-6.col-sm-6 > input"))
				.click();
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-md-6.col-sm-6 > input"))
				.sendKeys(edit);
	}
	
	public void clickSubmitButton() {
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-footer > input"))
				.click();
	}
	
	public void clickActiveCheckBox() {
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-body > div > div.form-group.col-sm-1.col-md-1 > input"))
				.click();
	}
	
	public void clickXButtonToClose() {
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-header > button"))
				.click();
	}
	
	public void clickCloseButtonToClose() {
		driver.findElement(By
				.cssSelector("#editCategoryModal > div > div > div.modal-footer > button"))
				.click();
	}
}
