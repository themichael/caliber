package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QualityAuditPage {

	private WebDriver driver;
	
	public QualityAuditPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Quits the current Driver
	 */
	public void closeAndQuit(){
		driver.quit();
	}
	
	/**
	 * Navigate current driver to http://localhost:8080/caliber/#/vp/audit
	 */
	public void goToPage(){
		driver.get("http://localhost:8080/caliber/#/vp/audit");
	}
	
	/**
	 * Clicks the Year dropdown and selects 2017 as the year
	 */
	public void clickYearDropdown(){
		//opens year dropdown
		driver.findElement(By.id("yearDropDownButton")).click();
		//clicks on '2017' from the unhidden menu
		driver.findElement(By.id("2017")).click();
	}
	
	public void verifyYear(){
		String year = driver.findElement(By.id("yearDropDownButton"))
				.getText();
		assertEquals("2017", year);
	}
	
	
	/**
	 * Clicks on the Batch dropdown and selects Patrick Walsh - 2/14/17 from the batch by year dropdown, which is the first element
	 */
	public void clickBatch(){
		//click on batch
		driver.findElement(By.id("batchDropDown")).click();
		//click on 'Patrick Walsh - 2/14/17' from the unhidden menu
		driver.findElement(By.id("Patrick Walsh - 2/14/17")).click();
	}
	
	public void verifyBatch(){
		String batch = driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[2]/a"))
				.getText();
		assertEquals("Patrick Walsh - 2/14/17" , batch);
	}
	
	/**
	 * Select the week for the assessment from the tab bar, webpage always loads on the last created week
	 * @param week as an int from 1
	 */
	public void clickWeeksForBatch(int week){
		//currently clicks week 8, change last /li[x] where x is the week; will click new week if set to last element in the list
		driver.findElement(By.id("week" + week))
			.click();
	}
	
	/**
	 * Verifies the current week selected on the week tab by checking which tab is currently selected.
	 */
	public void verifyWeekForBatch(){
		String weekTab;
		boolean	selected;
		int week = 1;
		// Loop constructed on the premise that weeks don't go over 9
		for(; week <=9; week++){
			weekTab = driver.findElement(By.id("week" + week)).getAttribute("class");
			if(weekTab == "active"){
				selected = true;
				break;
			}
		}
		assertTrue(selected = true);
		
	}
	
	/**
	 * Select the add week button at the end of the week tab
	 * @throws InterruptedException 
	 */
	public void clickAddWeeksForBatchButton() throws InterruptedException{
		driver.findElement(By.id("addWeekButton")).click();
		// wait for alert
		Thread.sleep(500);
		driver.switchTo().activeElement().findElement(By.id("noBtn")).click(); //currently clicking no
		//wait for alert to dissipate
		Thread.sleep(500);
	}
	
	/**
	 * Clicks the individual feedback button next to the trainee once. Clicks cycle through good, average, poor, and superstar
	 */
	public void clickIndividualFeedbackButton(){
		String[] qcBtns = { "questionBtn", "starBtn", "goodBtn", "fairBtn", "poorBtn"};
		//need to click on the currently displayed button
			//check if it is displayed
		boolean isAvailable;
		int step = 0;
		for(; step<=4; step++){
			isAvailable = driver.findElement(By.id("indvFeedback-"+ qcBtns[step] + "-0" )).isDisplayed();
			if(isAvailable)
				break;
		}
		//finally, clicks needs logic for determining how many clicks to reach desired state
		driver.findElement(By.id("indvFeedback-"+ qcBtns[step]+"-0")).click();
	}
	
	/**
	 * Fills trainee note text area with the passed string
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
	 * before any content has been loaded
	 * @throws InterruptedException 
	 */
	public void verifyTraineeNotes() throws InterruptedException{
		Thread.sleep(1000);
		String notes = driver.findElement(By.id("noteTextArea-0")).getAttribute("class");
		boolean contains = false;
		contains = notes.contains("ng-not-empty");
		assert(contains == true);
	}
	
	/**
	 * Clicks on the Good QC feedback button at the bottom of the QA Page
	 */
	public void clickOverallFeedbackQCButtonGood(){
		driver.findElement(By.id("good-QCButton"))
			.click();
	}
	
	/**
	 * Clicks on the Average QC feedback button at the bottom of the QA Page
	 */
	public void clickOverallFeedbackQCButtonAvg(){
		driver.findElement(By.id("fair-QCButton"))
			.click();
	}
	
	/**
	 * Clicks on the Poor QC feedback button at the bottom of the QA Page
	 */
	public void clickOverallFeedbackQCButtonPoor(){
		driver.findElement(By.id("poor-QCButton"))
			.click();
	}
	
	/**
	 * Fills the QC feedback text area with text
	 * @param notes
	 */
	public void setOverallFeedbackQCNotes(String notes){
		WebElement qcText = driver.findElement(By.id("qcBatchNotes"));
		qcText.clear();
		qcText.sendKeys(notes);
	}
	
	/**
	 * Checks that the text area is not empty by checking if the class of the div contains ng-not-empty
	 * Contains a wait to hold the thread until the text area is populated. Will fail if method finds the text area
	 * before any content has been loaded
	 * @throws InterruptedException 
	 */
	public void verifyQCNotes() throws InterruptedException{
		Thread.sleep(1000);
		String notes = driver.findElement(By.id("qcBatchNotes")).getAttribute("class");
		boolean contains = false;
		contains = notes.contains("ng-not-empty");
		assert(contains == true);
	}
	
	/**
	 * Clicks the save button at the bottom of the page
	 */
	public void clickSaveButton(){
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/div/div/a"))
			.click();
	}
}