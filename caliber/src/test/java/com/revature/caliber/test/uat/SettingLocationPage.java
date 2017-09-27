package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
public class SettingLocationPage {

	private WebDriver driver;
	private static final String BASE_URL = "CALIBER_API_SERVER";
	private static final String VP_LOCATIONS = "caliber/#/vp/locations";
	private static final String VALUE = "value";
	private static final Logger log = Logger.getLogger(SettingLocationPage.class);

	public SettingLocationPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver) driver;
		this.driver.get(System.getenv(BASE_URL)+VP_LOCATIONS);
	}

	//Closing the driver
	public void closeDriver() {
		driver.quit();
	}
	
	/**
	 * Go to the Locations page in caliber
	 */
	public void gotoSettingLocationPage() {
		driver.get(System.getenv(BASE_URL)+VP_LOCATIONS);
	}
	
	/**
	 * Go to the home page in Caliber
	 */
	public void goToHome() {
		driver.get(System.getenv(BASE_URL)+"caliber/#/vp/home");
	}

	/**
	 * Verify the user is on the Location page
	 */
	public void verifyLocationPage() {
		assertEquals(System.getenv(BASE_URL)+VP_LOCATIONS, driver.getCurrentUrl());
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
		driver.findElement(By.id("createLocationCompany")).clear();
		driver.findElement(By.id("createLocationCompany")).sendKeys(companyName);
	}
	
	/**
	 * Inputs the 'Street Address' in the street address field
	 * in the create location modal
	 * @param address
	 */
	public void inputStreetAddress(String address) {
		driver.findElement(By.id("createLocationAddress")).clear();
		driver.findElement(By.id("createLocationAddress")).sendKeys(address);
	}

	/**
	 * Inputs 'City' in the city field
	 * in the create location modal
	 * @param city
	 */
	public void inputCity(String city) {
		driver.findElement(By.id("createLocationCity")).clear();
		driver.findElement(By.id("createLocationCity")).sendKeys(city);
	}

	/**
	 * Inputs the 'State' in the state drop-down menu
	 * in the create location modal
	 * @param state
	 * @throws InterruptedException
	 */
	public void inputState(String state) throws InterruptedException {
		Select dropdown = new Select(driver.findElement(By.id("createLocationState")));
		Thread.sleep(1000);
		dropdown.selectByVisibleText(state);
	}
	
	/**
	 * Inputs the 'Zipcode' in the zipcode field
	 * in the create location modal
	 * @param zipcode
	 */
	public void inputZipcode(String zipcode){
		driver.findElement(By.id("createLocationZipCode")).clear();
		driver.findElement(By.id("createLocationZipCode")).sendKeys(zipcode);
	}
	
	/**
	 * Clicks the 'Submit' button in the create location modal
	 * in order to successfully create the new location
	 */
	public void clickSaveButn(){
		driver.findElement(By.id("createLocationSubmitBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the 'Close button on the create location modal
	 * in order to exit out of the modal
	 * without creating a new location
	 */
	public void clickCloseAddLocModalBtn(){
		driver.findElement(By.id("createLocationCloseBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the 'X' button on the create location modal
	 * in order to exit out of the modal
	 * without creating a new location
	 */
	public void clickXtoCloseAddLocModalBtn(){
		driver.findElement(By.id("createLocationXBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks on the 'Pencil' glyphicon button
	 * in order to open the update location modal
	 * to start making updates to the location
	 * @throws InterruptedException
	 */
	public void clickUpdatePencilBtn(int index) throws InterruptedException{
		driver.findElement(By.id("editLocationModal"+index)).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Inputs the Company Name into the company name input field
	 * into the update modal
	 * @param company
	 */
	public void updateEditInputCompanyName(String company) {
		//driver.findElement(By.cssSelector("#editLocationModal > div > div > div.modal-body > div > div:nth-child(1) > div:nth-child(1) > input")).sendKeys(company);;
		driver.findElement(By.id("editLocationCompany")).clear();
		driver.findElement(By.id("editLocationCompany")).sendKeys(company);
	}

	/**
	 * Inputs the street address in the street address input field
	 * in the update location modal
	 * @param address
	 */
	public void updateEditInputStreetAddress(String address) {
		driver.findElement(By.id("editLocationStreet")).clear();
		driver.findElement(By.id("editLocationStreet")).sendKeys(address);
	}

	/**
	 * Inputs the city in the city input field in the update location modal
	 * @param city
	 */
	public void updateEditInputCity(String city) {
		driver.findElement(By.id("editLocationCity")).clear();
		driver.findElement(By.id("editLocationCity")).sendKeys(city);
	}

	/**
	 * Inputs the state in the state input field in the update location modal
	 * @param state
	 * @throws InterruptedException
	 */
	public void updateEditInputState(String state) throws InterruptedException {
		Select dropdown = new Select(driver.findElement(By.id("editLocationState")));
		Thread.sleep(1000);
		dropdown.selectByVisibleText(state);
	}
	
	/**
	 * Inputs a zipcode in the zipcode input field in the update location modal
	 * @param zipcode
	 */
	public void updateEditInputZipcode(String zipcode){
		driver.findElement(By.id("editLocationZipCode")).clear();
		driver.findElement(By.id("editLocationZipCode")).sendKeys(zipcode);
	}
	
	/**
	 * Clicks the 'Update" button in the update modal
	 * in order to successfully update a location
	 */
	public void clickUpdateModalBtn(){
		driver.findElement(By.id("editLocationSubmitBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the update modal using the 'Close' button
	 * in order to exit the modal without making any edits to the location
	 */
	public void clickCloseUpdateModalBtn(){
		driver.findElement(By.id("editLocationCloseBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the 'x' on the top right corner of the update model
	 * in order to close the modal
	 * and cancel updating the location
	 */
	public void clickXCloseUpdateModal(){
		driver.findElement(By.id("editLocationXBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the 'x' deactivate glyphicon button next to the locations
	 * to the start the process of deactiving a location
	 * @throws InterruptedException 
	 */
	public void clickXDeleteBtn(int index) throws InterruptedException{
		driver.findElement(By.id("deleteLocationModal"+index)).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * This checks whether the location is active/inactive
	 */
	public boolean verifyLocationIsActive(int index) {
		String actual = driver.findElement(By.id("locationActiveGlyphicon"+index)).getAttribute(VALUE).toString();
    	return (actual.equals("true")) ? true : false;
	}
	
	public boolean verifyLocationIsInActive(int index) {
		String actual = driver.findElement(By.id("locationInactiveGlyphicon"+index)).getAttribute(VALUE).toString();
    	return (actual.equals("false")) ? true : false;
	}
	
	/**
	 * This clicks on the Deactivate button on the deactivate modal
	 * in order to deactivate the targeted location
	 * @throws InterruptedException 
	 */
	public void deactivateLocationBtn(int index) throws InterruptedException{
		driver.findElement(By.id("deleteLocationModal"+index)).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks on the 'Cancel' button the Deactivate modal
	 * in order to exit the modal
	 * without activating/deactivating the location
	 */
	public void clickDeactivateInDeactivateModalBtn(){
		driver.findElement(By.id("deleteLocationDeactivateBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks on the 'X' button on the Deactivate modal
	 * in order to exit the modal
	 * without activating/deactivating the location
	 */
	public void clickXCloseActivationDeactivateModal(){
		driver.findElement(By.id("deleteLocationXBtn")).click();
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the check button
	 * to open up the add location modal
	 * to reactive the targeted location
	 * @throws InterruptedException 
	 */
	public void clickCheckReactivateLocationBtn(int index) throws InterruptedException {
		driver.findElement(By.id("addLocationModal"+index)).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks the Reactivation button
	 * to confirm the reactivation of an inactive location
	 */
	public void clickReactivateLocationBtn() {
		driver.findElement(By.id("addLocationSubmitBtn")).click();
		driver.switchTo().activeElement();
	}
	
	public boolean isLocationUpdated(String location, String company, int index){
		String actualCompany = driver.findElement(By.id("locationCompany"+index)).getAttribute(VALUE).toString();
		String actualDetails = driver.findElement(By.id("locationDetails"+index)).getAttribute(VALUE).toString();
		log.debug(actualCompany);
		log.debug(actualDetails);
		return actualCompany.equals(company) && actualDetails.equals(location);
	}
	
}
