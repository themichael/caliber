package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QualityAuditPage {

	private WebDriver driver;
	private static final String clazz = "class";
	
	public QualityAuditPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Quits the current Driver.
	 */
	public void closeDriver(){
		driver.close();
	}
	
	/**
	 * Navigate current driver
	 */
	public void goToPage(){
		driver.get(System.getenv("CALIBER_API_SERVER")+"caliber/#/vp/audit");
	}
	
	/**
	 * Verifies the page using assertEquals
	 */
	public void verifyPage(){
		assertEquals(System.getenv("CALIBER_API_SERVER")+"caliber/#/vp/audit", driver.getCurrentUrl());
	}
	
	/**
	 * Clicks the Year dropdown and selects the year inputed for the year.
	 * @param year to select (2016-2018)
	 */
	public void clickYearDropdown(String year){
		driver.findElement(By.id("yearDropDownButton"))
			.click();
		driver.findElement(By.id(year))
			.click();
	}
	
	/**
	 * Checks that the year is the same as the inputed year.
	 * @param checkYear year to check
	 */
	public void verifyYear(String checkYear){
		String year = driver.findElement(By.id("yearDropDownButton"))
				.getText();
		assertEquals(checkYear, year);
	}
	
	
	/**
	 * Clicks on the Batch dropdown and selects the batchID inputed from the batch dropdown.
	 * @param batchID batch to select (follows "[trainerName] - [date]" format)
	 */
	public void clickBatch(String batchID){
		driver.findElement(By.id("batchDropDown"))
			.click();
		driver.findElement(By.id(batchID))
			.click();
	}
	
	/**
	 * Verifies the current batch selected against the input.
	 * @param checkBatch batch to check against (follows "[trainerName] - [date]" format)
	 */
	public void verifyBatch(String checkBatch){
		String batch = driver.findElement(By.id("batchDropDown"))
				.getText();
		assertEquals(checkBatch , batch);
	}
	
	/**
	 * Select the week for the assessment from the tab bar, webpage always loads on the last created week.
	 * @param week as an int from 1
	 */
	public void clickWeeksForBatch(int week){
		driver.findElement(By.id("week" + week))
			.click();
	}
	
	/**
	 * Verifies the current week selected on the week tab.
	 * Checks if the class of the parent is active.
	 * @param checkWeek as the week to check (format as weekX where X is the number, no space between them at all)
	 */
	public void verifyWeekForBatch(String checkWeek){
		String weekTab;
		int week = 1;
		for(; week <=9; week++){
			WebElement child = driver.findElement(By.id("week" + week));
			WebElement parent = child.findElement(By.xpath(".."));
			weekTab = parent.getAttribute(clazz);
			if("active".equals(weekTab)){
				break;
			}
		}
		assertEquals(checkWeek, "week"+week);
		
	}
	
	/**
	 * Select the add week button at the end of the week tab.
	 * @throws InterruptedException 
	 */
	public void clickAddWeeksForBatchButton() throws InterruptedException{
		driver.findElement(By.id("addWeekButton"))
			.click();
		Thread.sleep(500);
		driver.switchTo().activeElement().findElement(By.id("noBtn"))
			.click();
		Thread.sleep(500);
	}
	
	/**
	 * Clicks the individual feedback button next to the trainee once. Clicks cycle through good, average, poor, and superstar.
	 * @throws InterruptedException 
	 */
	public void clickIndividualFeedbackButton() throws InterruptedException{
		String[] qcBtns = { "questionBtn", "starBtn", "goodBtn", "fairBtn", "poorBtn"};
		boolean isAvailable;
		int step = 0;
		for(; step<=4; step++){
			isAvailable = driver.findElement(By.id("indvFeedback-"+ qcBtns[step] + "-0" ))
					.isDisplayed();
			if(isAvailable)
				break;
		}
		driver.findElement(By.id("indvFeedback-"+ qcBtns[step]+"-0")).click();
	}
	
	/**
	 * Fills trainee note text area with the passed string.
	 * @param notes 
	 */
	public void setNoteOnTraineeTextArea(String notes){
		WebElement traineeTextArea = driver.findElement(By.id("noteTextArea-0"));
		traineeTextArea.clear();
		traineeTextArea.sendKeys(notes);
	}
	
	/**
	 * Checks that the text area is not empty by checking if the class of the div contains ng-not-empty.
	 * Contains a wait to hold the thread until the text area is populated. Will fail if method finds the text area
	 * before any content has been loaded.
	 * @throws InterruptedException 
	 */
	public void verifyTraineeNotes() throws InterruptedException{
		Thread.sleep(3000);
		String notes = driver.findElement(By.id("noteTextArea-0")).getAttribute(clazz);
		assert(notes.contains("ng-not-empty") == true);
	}
	
	/**
	 * Clicks on the Good QC feedback button at the bottom of the QA Page.
	 */
	public void clickOverallFeedbackQCButtonGood(){
		driver.findElement(By.id("good-QCButton"))
			.click();
	}
	
	/**
	 * Clicks on the Average QC feedback button at the bottom of the QA Page.
	 */
	public void clickOverallFeedbackQCButtonAvg(){
		driver.findElement(By.id("fair-QCButton"))
			.click();
	}
	
	/**
	 * Clicks on the Poor QC feedback button at the bottom of the QA Page.
	 */
	public void clickOverallFeedbackQCButtonPoor(){
		driver.findElement(By.id("poor-QCButton"))
			.click();
	}
	
	/**
	 * Fills the QC feedback text area with text with the passed string.
	 * @param notes
	 */
	public void setOverallFeedbackQCNotes(String notes){
		WebElement qcText = driver.findElement(By.id("qcBatchNotes"));
		qcText.clear();
		qcText.sendKeys(notes);
	}
	
	/**
	 * Checks that the text area is not empty by checking if the class of the div contains ng-not-empty.
	 * Contains a wait to hold the thread until the text area is populated. Will fail if method finds the text area
	 * before any content has been loaded.
	 * @throws InterruptedException 
	 */
	public void verifyQCNotes() throws InterruptedException{
		Thread.sleep(1000);
		String notes = driver.findElement(By.id("qcBatchNotes")).getAttribute(clazz);
		assert(notes.contains("ng-not-empty") == true);
	}
	
	/**
	 * Clicks the save button at the bottom of the page.
	 * @throws InterruptedException 
	 */
	public void clickSaveButton() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(By.id("QASaveBtn"))
			.click();
	}
}