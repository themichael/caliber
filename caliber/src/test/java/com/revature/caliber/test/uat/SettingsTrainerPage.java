package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author KoredeA 
 * 		   Page Object Model for the Settings Trainer Page In this Page
 *         the user is able to delete, update and Create a trainer Use the
 *         methods below, you are able to do every possible action allowed on
 *         the Settings Trainer page
 *
 */
public class SettingsTrainerPage {

	private WebDriver driver;
	private static final String BASE_URL = "CALIBER_API_SERVER";
	private static final String VP_TRAINERS = "caliber/#/vp/trainers";  
	private static final String TRAINER_NAME = "trainerName";
	private static final String TRAINER_EMAIL = "trainerEmail";
	private static final String TITLE = "Title";
	
	public SettingsTrainerPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver) driver;
		this.driver.get(System.getenv(BASE_URL)+VP_TRAINERS);
	}

	/**
	 * Sends driver to the specified page
	 */
	public void gotoPage(){
		driver.get(System.getenv(BASE_URL)+VP_TRAINERS);
	}

	/**
	 * Checks to see if the driver is currently on the specified page
	 */
	public void verifyPage() {
		assertEquals(System.getenv(BASE_URL)+VP_TRAINERS, driver.getCurrentUrl());
	}

	/**
	 * Clicks the CreateTrainer button to open create Trainer modal
	 * @throws InterruptedException
	 */
	public void createTrainerTab() throws InterruptedException {
		driver.findElement(
				By.id("createTrainerBtn"))
				.click();
		driver.switchTo().activeElement();
	}

	/**
	 * Enters name into the Trainer name input field
	 * @param name
	 */
	public void enterName(String name) {
		driver.findElement(By.id(TRAINER_NAME))
				.sendKeys(name);
	}

	/**
	 * Enters email into the Email input field
	 * @param email
	 */
	public void enterEmail(String email) {
		driver.findElement(By.id(TRAINER_EMAIL))
				.sendKeys(email);
	}

	/**
	 * Enters name of Title into the title input field
	 * @param title
	 */
	public void selectTitle(String title) {
		driver.findElement(By.id(TITLE))
				.sendKeys(title);

	}

	/**
	 * Selects the Tier option from the dropdown box
	 * @param tier
	 * @throws InterruptedException
	 */
	public void selectTier(String tier) throws InterruptedException {
		Select dropdown = new Select(driver.findElement(By.id("trainerTier")));
		Thread.sleep(500);
		dropdown.selectByVisibleText(tier);
	}

	/**
	 * clicks the save button in the Modal of the Create Trainer
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void createTrainerModalSaveButton() throws IOException, InterruptedException {
		driver.findElement(
				By.id("saveBtn")).click();
	}

	/**
	 * clicks the close button in the Modal of the Create Trainer
	 */
	public void createTrainerCloseButton() {
		driver.findElement(
				By.id("closeBtn")).click();
	}
	
	/**
	 * clicks the x button to close modal
	 */
	public void xCloseButton(){
		driver.findElement(By.id("XBtn")).click();
	}

	/**
	 * clicks the pencil glyphicon which brings up the update Trainer modal
	 * @throws InterruptedException
	 */
	public void updateIcon() throws InterruptedException {
		driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(2) > td:nth-child(5) > a")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}

	/**
	 * clears the input field for the Trainer name then updates the name
	 * @param name
	 */
	public void updateTrainerName(String name) {
		driver.switchTo().activeElement();
		driver.findElement(By.id(TRAINER_NAME))
				.clear();
		driver.findElement(By.id(TRAINER_NAME))
				.sendKeys(name);
		;
	}

	/**
	 * clears the input field for the Trainer email then updates the email
	 * @param email
	 */
	public void updateEmail(String email) {
		driver.findElement(By.id(TRAINER_EMAIL))
				.clear();
		driver.findElement(By.id(TRAINER_EMAIL))
				.sendKeys(email);

	}

	/**
	 * clears the input field for the Trainer title then updates the title
	 * @param title
	 */
	public void updateTitle(String title) {
		driver.findElement(By.id(TITLE))
				.clear();
		driver.findElement(By.id(TITLE))
				.sendKeys(title);
	}

	/**
	 * Selects a tier from the dropdown
	 * @param tier
	 * @throws InterruptedException
	 */
	public void updateTier(String tier) throws InterruptedException {
		Select dropdown = new Select(driver.findElement(By.cssSelector("#editTrainerModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > select")));
		Thread.sleep(500);
		dropdown.selectByVisibleText(tier);
	}

	/**
	 * clicks the update button inside the update modal
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void modalUpdateButton() throws IOException, InterruptedException {
		driver.findElement(By.cssSelector("#editTrainerModal > div > div > div.modal-body > div.modal-footer > input")).click();
		driver.switchTo().activeElement();
	}

	/**
	 * clicks the close button inside the update modal
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void updateModalCloseButton() throws InterruptedException, IOException {
		driver.findElement(By.id("closeBtn"))
				.click();
		driver.switchTo().activeElement();
	}

	/**
	 * clicks the x glyphicon which brings up the delete Trainer modal to the screen
	 * @throws InterruptedException
	 */
	public void deleteIcon() throws InterruptedException {
		driver.findElement(By.id("deleteIcon"))
				.click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}

	/**
	 * clicks the close button inside the delete modal
	 */
	public void deleteModalCloseButton() {
		driver.findElement(By.id("cancelBtn")).click();
		driver.switchTo().activeElement();
	}

	/**
	 * clicks the delete button inside the delete modal
	 */
	public void modalDeleteButton() {
		driver.findElement(By.id("deleteBtn")).click();
		driver.switchTo().activeElement();
	}

	public void teardown(){
		driver.close();
	}
	
	public void checkTierChange(String tier){
		driver.get(driver.getCurrentUrl());
		String actual = driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div > div:nth-child(2) > div > div > table > tbody > tr:nth-child(2) > td:nth-child(4)")).getAttribute("value").toString();
		assertEquals(tier, actual);
	}

}
