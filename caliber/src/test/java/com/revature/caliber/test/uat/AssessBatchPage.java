package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AssessBatchPage {

	private WebDriver driver;

	public AssessBatchPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver) driver;
		this.driver.get("http://localhost:8080/caliber#/vp/assess");
	}

	/**
	 * Function that takes the browser to the assess batch page
	 */
	public void goToPage() {
		driver.get("http://localhost:8080/caliber#/vp/assess");
	}

	/**
	 * Checks to see if the driver is on the assess batch page
	 */
	public void verifyAssessPage() {
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/caliber#/vp/assess");
	}

	/**
	 * Clicks the create Assessment tab to open the modal to open the Create
	 * Assessment modal
	 */
	public void clickCreateAssessment() {
		driver.findElement(By.id("createAssessmentIcon")).click();
	}

	/**
	 * Clicks the specific week tab for the batch
	 */
	public void clickWeekTab() {
		driver.findElement(By.id("weekBtn3")).click();
	}

	/**
	 * Clicks the year dropdown and selects the specific year
	 */
	public void clickYear() {
		driver.findElement(By.id("yearDropdownId")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.id("yearDropdownId2")).click();
	}

	/**
	 * Clicks the batch dropdown and selects the specific batch
	 */
	public void selectBatch() {
		driver.findElement(By.id("batchesDropdownId")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.id("batchesDropdownId0")).click();
	}

	/**
	 * Enters the grade for a specific trainee on an exam
	 * 
	 * @param name
	 * @param number
	 */
	public void enterGrades(String name, String number) {
		driver.findElement(By.id(name + "grade0")).sendKeys(number);
		driver.findElement(By.id(name + "grade1")).sendKeys(number);
	}

	/**
	 * Checks to see if the grade was entered
	 * 
	 * @param traineeName
	 * @param grade
	 */
	public void checkIfGradesWereInput(String traineeName, String grade) {
		assertEquals(driver.findElement(By.id(traineeName + "grade0")).getAttribute("value"), grade);
	}

	/**
	 * Clicks the + icon to open the modal to add a new week for the batch
	 * 
	 * @throws InterruptedException
	 */
	public void clickNewWeek() throws InterruptedException {
		driver.findElement(By.id("addNewWeekIcon")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}

	/**
	 * Clicks yes button in the newWeek modal to officially add a new week to
	 * the batch
	 * 
	 * @throws InterruptedException
	 */
	public void newWeekConfirmButton() throws InterruptedException {
		driver.findElement(By.id("yesBtn")).click();
		driver.switchTo().activeElement();
	}

	/**
	 * Clicks the no button to cancel the add a new week to batch
	 */
	public void newWeekNoButton() {
		driver.findElement(By.id("noBtn")).click();
	}

	/**
	 * Clicks the x button to exit out of the add a new week modal
	 */
	public void closeNewWeekButton() {
		driver.findElement(By.id("closeBtn"));
	}

	/**
	 * Clicks the save button
	 */
	public void saveButton() {
		driver.findElement(By.id("saveBtn")).click();
	}

	/**
	 * Selects the trainer name from the trainer dropdown
	 * 
	 * @param trainerName
	 * @throws InterruptedException
	 */
	public void selectTrainer(String trainerName) throws InterruptedException {
		WebElement dropdown = driver.findElement(By.id("batchesDropdownId"));
		dropdown.click();
		driver.switchTo().activeElement();
		driver.findElement(By.id("batchesDropdownId2")).click();
	}

	/**
	 * Enters the response for the batch performance for the week
	 * 
	 * @param feedback
	 * @throws InterruptedException
	 */
	public void batchNotes(String feedback) throws InterruptedException {
		driver.findElement(By.id("trainerBatchNote")).clear();
		driver.findElement(By.id("trainerBatchNote")).sendKeys(feedback);
	}

	/**
	 * Checks to see if the entered response for batch notes matches the what is
	 * in the text area
	 * 
	 * @param feedback
	 */
	public void batchNotesCheck(String feedback) {
		assertEquals(feedback, driver.findElement(By.id("trainerBatchNote")).getAttribute("value"));
	}

	/**
	 * Selects the Assessment Category from the category dropdown
	 * 
	 * @param feedback
	 */
	public void selectAssessementCategory(String feedback) {
		Select dropdown = new Select(driver.findElement(By.id("category")));
		dropdown.selectByVisibleText(feedback);
	}

	/**
	 * Sets the max amount of points for the New Assessment
	 * 
	 * @param feedback
	 */
	public void maxPoints(String feedback) {
		driver.findElement(By.id("rawScore")).sendKeys(feedback);
	}

	/**
	 * Checks to see if the assessment has been made
	 * 
	 * @param exam
	 * @return
	 */
	public boolean assessmentCheck(String exam) {
		return driver.findElement(By.id(exam + "Exam")).isDisplayed();
	}

	/**
	 * Selects the Assessment type from the type dropdown
	 * 
	 * @param feedback
	 */
	public void selectAssessmentType(String feedback) {
		Select dropdown = new Select(driver.findElement(By.id("assessmentType")));
		dropdown.selectByVisibleText(feedback);
	}

	/**
	 * Function to find out how many weeks a specific b
	 * 
	 * @return
	 */
	public int numberOfWeeks() {
		int i = 1;
		boolean exists = true;

		while (exists) {
			try {
				driver.findElement(By.id("weekBtn" + i));
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				exists = true;
				i++;
			} catch (NoSuchElementException e) {
				exists = false;
			}
		}
		return i;
	}
	
	/**
	 * Clicks the save button inside the Create Assessment modal
	 */
	public void modalSaveButton(){
		driver.findElement(By.id("assessmentSaveBtn")).click();
		driver.switchTo().activeElement();
		//driver.findElement(By.cssSelector("#createAssessmentModal > div > div > form > div.modal-footer > input")).click();
	}


	/**
	 * Clicks the close button inside the Create Assessment modal
	 */
	public void closeButton() {
		driver.findElement(By.id("closeBtn")).click();
	}

	/**
	 * Clicks the x button inside the Create Assessment modal to exit
	 */
	public void xButton() {
		driver.findElement(By.id("xBtn")).click();
	}

	/**
	 * Function to close the driver
	 */
	public void closeDriver() {
		driver.close();
	}

	/**
	 * Checks to see if the Week exists
	 * 
	 * @param weekNumber
	 * @return
	 */
	public boolean doesWeekTabExist(int weekNumber) {
		boolean exists;
		try {
			driver.findElement(By.id("weekBtn" + (weekNumber)));
			exists = true;
		} catch (NoSuchElementException e) {
			exists = false;
		}
		return exists;
	}
}
