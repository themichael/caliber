package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

public class SettingLocationPage {
	
	/**
	 * @author Evan Molinelli created Page Object Model 
	 * for the Setting Location Page of Caliber.
	 * The methods defined below are the actions being executed
	 * in the Locations page of caliber. Only the VP's are allowed to 
	 * add/update locations.
	 * 
	 * 
	 * @author Davis Zabiyaka modified the Page Object Mode
	 * to fit the criterea for headless chrome browser
	 * instead of phantomjs
	 */

	private WebDriver driver;

	//Initialize phantomjs driver to page
	public SettingLocationPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver) driver;
//		this.driver.get("http://localhost:8080/caliber#/vp/locations");
	}

	//Closing the driver
	public void closeDriver() {
		driver.quit();
	}
	
	//Go to the Locations page in Caliber
	public void gotoSettingLocationPage() {
		driver.get("http://localhost:8080/caliber/#/vp/locations");
	}
	
	//Go to home page in Caliber
	public void goToHome() {
		driver.get("http://localhost:8080/caliber#/vp/home");
	}

	//Verify that user is on the Location page
	public void verifyLocationPage() {
		assertEquals("http://localhost:8080/caliber/#/vp/locations", driver.getCurrentUrl());
	}

	//Clicks the 'Create Location' button 
	public void clickCreateLocationBtn() throws InterruptedException {
		//driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div/div[1]/div/div/ul/li/a")).click();
		driver.findElement(By.id("createLocationBtn")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}

	//Input 'Company Name' in the Add Location modal.
	public void inputCompanyName(String companyName) {
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")).sendKeys(company);;
		driver.findElement(By.id("locationCompany")).sendKeys(companyName);
	}
	
	//Input 'Street Address' in the Add Location modal.
	public void inputStreetAddress(String address) {
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input")).sendKeys(address);
		driver.findElement(By.id("locationStreet")).sendKeys(address);
	}

	//Input 'City' in the Add Location modal.
	public void inputCity(String city) {
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > input")).sendKeys(city);
		driver.findElement(By.id("locationCity")).sendKeys(city);
	}

	//Input 'State' in the Add Location modal.
	public void inputState(String state) throws InterruptedException {
		//Select dropdown = new Select(driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > select")));
		
		//Select dropdown = new Select(driver.findElement(By.id("locationState")).click()));
		Select dropdown = new Select(driver.findElement(By.id("locationState")));
		Thread.sleep(1000);
		dropdown.selectByVisibleText(state);
	}
	
	//Input 'Zipcode' in the Add Location modal.
	public void inputZipcode(String zipcode){
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(3) > div > input")).sendKeys(zipcode);
		driver.findElement(By.id("locationZipCode")).sendKeys(zipcode);
	}
	
	//Save location in 'Add Location' modal.
	public void clickSaveButn(){
		//driver.findElement(By.xpath("//*[@id=\"createLocationModal\"]/div/div/div[2]/div[2]/input")).click();;
		driver.findElement(By.id("submintBtn")).click();
	}
	
	//click the 'close' button to close 
	public void clickCloseAddLocModalBtn(){
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div.modal-footer > button")).click();
		driver.findElement(By.id("closeBtn")).click();
	}
	
	//click the 'x' to close the add location modal
	public void clickXtoCloseAddLocModalBtn(){
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-header > button")).click();
		driver.findElement(By.id("XBtn")).click();
	}
	
	//Click the 'pencil' glyphicon next to the locations open the update location modal.
	public void clickUpdatePencilBtn(){
		driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > a")).click();
		driver.switchTo().activeElement();
	}
	
	//inputting Company name in the company name input field in the update modal.
	public void updateInputCompanyName(String company) {
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(1) > input")).clear();
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(1) > input")).sendKeys(company);;
	}

	//inputting street address in the street address input field in the update modal.
	public void updateInputStreetAddress(String address) {
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(2) > input")).clear();
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(2) > input")).sendKeys(address);
	}

	//inputting city in the city input field in the update modal.
	public void updateInputCity(String city) {
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(2) > div:nth-child(1) > input")).clear();
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(2) > div:nth-child(1) > input")).sendKeys(city);
	}

	//inputting state in the state input field in the update modal.
	public void updateInputState(String state) throws InterruptedException {
		Select dropdown = new Select(driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(2) > div:nth-child(2) > select")));
		Thread.sleep(1000);
		dropdown.selectByVisibleText(state);
	}
	
	//inputting zipcode in the zipcode input field in the update modal.
	public void updateInputZipcode(String zipcode){
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(3) > div > input")).clear();
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(3) > div > input")).sendKeys(zipcode);
		
	}
	
	//clicking the 'update' button in the update modal
	public void clickUpdateModalBtn(){
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-footer > input")).click();
	}
	
	//Clicking the 'close' in the update modal
	public void clickCloseUpdateModalBtn(){
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-footer > button")).click();
	}
	
	//Clicking the 'X' in the top right corner of update modal to close.
	public void clickXCloseUpdateModal(){
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-header > button")).click();
	}
	
	//Clicking the 'x' deactivate glyphicon button next to the locations.
	public void clickXDeleteBtn(){
		driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a")).click();
		driver.switchTo().activeElement();
	}
	
	//Clicking the 'Deactivate' button in the deactivate modal.
	public void deactivateLocationBtn(){
		driver.findElement(By.cssSelector("#deleteLocationModal > div > div > div.modal-footer > input")).click();
	}
	
	//Clicking the 'close' Deactivate Modal Button.
	public void closeDeactivateModalBtn(){
		driver.findElement(By.cssSelector("#deleteLocationModal > div > div > div.modal-footer > button")).click();
	}
	
	//Clicking the 'X' in the top right corner of deactivate modal to close.
		public void clickXCloseDeactivateModal(){
			driver.findElement(By.cssSelector("#deleteLocationModal > div > div > div.modal-header > button")).click();
		}

}
