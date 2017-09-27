package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	public SettingLocationPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver) driver;
		this.driver.get("http://localhost:8080/caliber#/vp/locations");
	}

	//Closing the driver
	public void closeDriver() {
		driver.quit();
	}
	
	/**
	 * Go to the Locations page in caliber
	 */
	public void gotoSettingLocationPage() {
		driver.get("http://localhost:8080/caliber/#/vp/locations");
	}
	
	/**
	 * Go to the home page in Caliber
	 */
	public void goToHome() {
		driver.get("http://localhost:8080/caliber#/vp/home");
	}

	/**
	 * Verify the user is on the Location page
	 */
	public void verifyLocationPage() {
		assertEquals("http://localhost:8080/caliber/#/vp/locations", driver.getCurrentUrl());
	}

	/**
	 * Clicks the 'Create location' button
	 * in order to open up the Create location modal
	 * in order to start the process of creating a location
	 * @throws InterruptedException
	 */
	public void clickCreateLocationBtn() throws InterruptedException {
		//driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div/div[1]/div/div/ul/li/a")).click();
		driver.findElement(By.id("createLocationBtn")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}

	/**
	 * Inputs 'Company Name' in the company name field
	 *  in the create location modal
	 * @param companyName
	 */
	public void inputCompanyName(String companyName) {
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")).sendKeys(companyName);
		driver.findElement(By.id("locationCompany")).sendKeys(companyName);
		//driver.findElement(By.xpath("//*[@id=\"locationCompany\"]")).sendKeys(companyName);
		//driver.findElement(By.id("locationCompany")).sendKeys(companyName);//*[@id="locationCompany"]
	}
	
	/**
	 * Inputs the 'Street Address' in the street address field
	 * in the create location modal
	 * @param address
	 */
	public void inputStreetAddress(String address) {
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input")).sendKeys(address);
		driver.findElement(By.id("locationStreet")).sendKeys(address);
	}

	/**
	 * Inputs 'City' in the city field
	 * in the create location modal
	 * @param city
	 */
	public void inputCity(String city) {
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > input")).sendKeys(city);
		driver.findElement(By.id("locationCity")).sendKeys(city);
	}

	/**
	 * Inputs the 'State' in the state drop-down menu
	 * in the create location modal
	 * @param state
	 * @throws InterruptedException
	 */
	public void inputState(String state) throws InterruptedException {
		//Select dropdown = new Select(driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > select")));
		
		//Select dropdown = new Select(driver.findElement(By.id("locationState")).click()));
		Select dropdown = new Select(driver.findElement(By.id("locationState")));
		Thread.sleep(1000);
		dropdown.selectByVisibleText(state);
	}
	
	/**
	 * Inputs the 'Zipcode' in the zipcode field
	 * in the create location modal
	 * @param zipcode
	 */
	public void inputZipcode(String zipcode){
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(3) > div > input")).sendKeys(zipcode);
		driver.findElement(By.id("locationZipCode")).sendKeys(zipcode);
	}
	
	/**
	 * Clicks the 'Submit' button in the create location modal
	 * in order to successfully create the new location
	 */
	public void clickSaveButn(){
		//driver.findElement(By.xpath("//*[@id=\"createLocationModal\"]/div/div/div[2]/div[2]/input")).click();;
		driver.findElement(By.id("submintBtn")).click();
	}
	
	/**
	 * Clicks the 'Close button on the create location modal
	 * in order to exit out of the modal
	 * without creating a new location
	 */
	public void clickCloseAddLocModalBtn(){
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-body > div.modal-footer > button")).click();
		driver.findElement(By.id("closeBtn")).click();
	}
	
	/**
	 * Clicks the 'X' button on the create location modal
	 * in order to exit out of the modal
	 * without creating a new location
	 */
	public void clickXtoCloseAddLocModalBtn(){
		//driver.findElement(By.cssSelector("#createLocationModal > div > div > div.modal-header > button")).click();
		driver.findElement(By.id("XBtn")).click();
	}
	
	/**
	 * Clicks on the 'Pencil' glyphicon button
	 * in order to open the update location modal
	 * to start making updates to the location
	 * @throws InterruptedException
	 */
	public void clickUpdatePencilBtn() throws InterruptedException{
		driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > a")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Inputs the Company Name into the company name input field
	 * into the update modal
	 * @param company
	 */
	public void updateInputCompanyName(String company) {
		//driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(1) > input")).clear();
		//driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(1) > input")).click();
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(1) > input")).sendKeys(company);;
		//driver.findElement(By.id("locationCompany")).click();
		///WebElement companyNameField = driver.findElement(By.cssSelector("#locationCompany"));									
		///companyNameField.sendKeys(company);
		//driver.findElement(By.id("locationCompany")).sendKeys(company);
	}

	/**
	 * Inputs the street address in the street address input field
	 * in the update location modal
	 * @param address
	 */
	public void updateInputStreetAddress(String address) {
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(2) > input")).sendKeys(address);
	}

	/**
	 * Inputs the city in the city input field in the update location modal
	 * @param city
	 */
	public void updateInputCity(String city) {
		//driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(2) > div:nth-child(1) > input")).clear();
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(2) > div:nth-child(1) > input")).sendKeys(city);
		//driver.findElement(By.id("locationCity")).click();
		//driver.findElement(By.id("locationCity")).sendKeys(city);
	}

	/**
	 * Inputs the state in the state input field in the update location modal
	 * @param state
	 * @throws InterruptedException
	 */
	public void updateInputState(String state) throws InterruptedException {
		Select dropdown = new Select(driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(2) > div:nth-child(2) > select")));
		//Select dropdown = new Select(driver.findElement(By.id("locationState")));
		Thread.sleep(1000);
		dropdown.selectByVisibleText(state);
	}
	
	/**
	 * Inputs a zipcode in the zipcode input field in the update location modal
	 * @param zipcode
	 */
	public void updateInputZipcode(String zipcode){
		//driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(3) > div > input")).clear();
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(3) > div > input")).sendKeys(zipcode);
		//driver.findElement(By.id("locationZipCode")).sendKeys(zipcode);
	}
	
	/**
	 * Clicks the 'Update" button in the update modal
	 * in order to successfully update a location
	 */
	public void clickUpdateModalBtn(){
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-footer > input")).click();
		//driver.findElement(By.id("submitBtn")).click();
	}
	
	/**
	 * Clicks the update modal using the 'Close' button
	 * in order to exit the modal without making any edits to the location
	 */
	public void clickCloseUpdateModalBtn(){
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-footer > button")).click();
		//driver.findElement(By.id("closeBtn")).click();
	}
	
	/**
	 * Clicks the 'x' on the top right corner of the update model
	 * in order to close the modal
	 * and cancel updating the location
	 */
	public void clickXCloseUpdateModal(){
		driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-header > button")).click();
		//driver.findElement(By.id("XBtn")).click();
	}
	
	/**
	 * Clicks the 'x' deactivate glyphicon button next to the locations
	 * to the start the process of deactiving a location
	 * @throws InterruptedException 
	 */
	public void clickXDeleteBtn() throws InterruptedException{
		driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * This checks whether the location is active
	 */
	public void verifyLocationIsActive() {
		assertEquals("glyphicon glyphicon-remove",
				driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a > span")).getAttribute("class"));
	}
	
	/**
	 * This checks whether the location is inactive
	 */
	public void verifyLocationIsInactive() {
		assertEquals("glyphicon glyphicon-ok",
				driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a > span")).getAttribute("class"));
	}
	
	/**
	 * This checks if we see a check or x button to reactivate/deactivate the location, respectively
	 */
	public void verifyCheckOrXLocation() {
		assertTrue(
				driver.findElement(By.
						cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a > span"))
						.getAttribute("class")
						.equals("glyphicon glyphicon-remove") ||
				driver.findElement(By.
						cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a > span"))
				.getAttribute("class")
				.equals("glyphicon glyphicon-ok")
				);
	}
	
	/**
	 * This clicks on the Deactivate button on the deactivate modal
	 * in order to deactivate the targeted location
	 * @throws InterruptedException 
	 */
	public void deactivateLocationBtn() throws InterruptedException{
		driver.findElement(By.cssSelector("#deleteLocationModal > div > div > div.modal-footer > input")).click();
		Thread.sleep(1000);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks on the 'Cancel' button the Deactivate modal
	 * in order to exit the modal
	 * without activating/deactivating the location
	 */
	public void clickCancelToCloseActivationDeactivateModalBtn(){
		driver.findElement(By.cssSelector("#deleteLocationModal > div > div > div.modal-footer > button")).click();
	}
	
	/**
	 * Clicks on the 'X' button on the Deactivate modal
	 * in order to exit the modal
	 * without activating/deactivating the location
	 */
	public void clickXCloseActivationDeactivateModal(){
		driver.findElement(By.cssSelector("#deleteLocationModal > div > div > div.modal-header > button")).click();
	}
	
	/**
	 * Clicks the check button
	 * to open up the add location modal
	 * to reactive the targeted location
	 * @throws InterruptedException 
	 */
	public void clickCheckReactivateLocationBtn() throws InterruptedException {
		driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(5) > a")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the Reactivation button
	 * to confirm the reactivation of an inactive location
	 */
	public void clickReactivateLocationBtn() {
		driver.findElement(By.cssSelector("#addLocationModal > div > div > div.modal-footer > input")).click();
	}
}
