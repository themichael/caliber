package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QualityAuditPage {

	private WebDriver driver;
	
	public QualityAuditPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Quits the PhantomJS Driver
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
	 * Selects 2017 from year dropdown
	 * @throws IOException 
	 */
	public void clickYearDropdown() throws IOException{
		//opens year dropdown
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[1]/a"))
			.click();
		//clicks on '2017' from the unhidden menu
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[1]/ul/li[2]/a"))
			.click();
	}
	
	public void verifyYear(){
		String year = driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[1]/a"))
				.getText();
		assertEquals("2017", year);
	}
	
	
	/**
	 * Select Patrick Walsh - 2/14/17 from the batch by year dropdown, which is the first element
	 */
	public void clickBatch(){
		//click on batch
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[2]/a"))
			.click();
		//click on 'Patrick Walsh - 2/14/17' from the unhidden menu
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[2]/ul/li/a"))
			.click();
	}
	
	public void verifyBatch(){
		String batch = driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[2]/a"))
				.getText();
		assertEquals("Patrick Walsh - 2/14/17" , batch);
	}
	
	/**
	 * Select the week for the assessment from the tab bar, webpage always loads on the last created week,
	 * @param week as an int from 1
	 */
	public void clickWeeksForBatch(int week){
		//currently clicks week 8, change last /li[x] where x is the week; will click new week if set to last element in the list
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li["+ week +"]"))
			.click();
	}
	
	/**
	 * Verifies the current week selected on the week tab by checking which tab is currently selected
	 * loop constructed on the premise that weeks don't go over 9
	 */
	public void verifyWeekForBatch(){
		String weekTab;
		boolean	selected = false;
		int week = 1;
		for(; week <=9; week++){
			weekTab = driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li["+ week +"]")).getAttribute("class");
			if(weekTab == "active"){
				selected = true;
				break;
			}
		}
		assertTrue(selected = true);
		
	}
	
	/**
	 * Select the add week button at the end of the week tab
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void clickAddWeeksForBatchButton() throws IOException, InterruptedException{
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li[9]"))
			.click();
		// wait for alert
		Thread.sleep(500);
		driver.switchTo().activeElement().findElement(By.xpath("//*[@id='confirmingweeks']/div/div/div[2]/button[2]"))
			.click(); //currently clicking no
		//wait for alert to dissipate
		Thread.sleep(500);
	}
	
	/**
	 * Clicks the individual feedback button next to the trainee. Clicks cycle through good, average, poor, and superstar
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void clickIndividualFeedbackButton() throws InterruptedException, IOException{
		//need to click on the currently displayed button
			//check if it is displayed
		boolean isAvailable;
		int step = 1;
		for(; step <=5; step++){
			isAvailable = driver.findElement(By.xpath("//*[@id='qcTrainees']/div/ul/table/tbody/tr[1]/td[2]/button["+ step +"]/i"))
								.isDisplayed();
			if(isAvailable)
				break;
		}
		//finally, clicks needs logic for determining how many clicks to reach desired state
		WebElement button = driver.findElement(By.xpath("//*[@id='qcTrainees']/div/ul/table/tbody/tr[1]/td[2]/button["+ step +"]"));
		button.click();
	}
	
	/**
	 * Fills trainee note text area
	 * @param notes
	 * @throws IOException
	 */
	public void setNoteOnTraineeTextArea(String notes) throws IOException{
		WebElement traineeTextArea = driver.findElement(By.xpath("//*[@id='noteTextArea']"));
		traineeTextArea.clear();
		traineeTextArea.sendKeys(notes);
	}
	
	/**
	 * Checks that the text area is not empty by checking if the class of the div contains ng-not-empty
	 * Sometimes the method will grab the element before the data has been loaded, recommend to have
	 * some kind of wait until the data is loaded
	 */
	public void verifyTraineeNotes(){
		String notes = driver.findElement(By.xpath("//*[@id='noteTextArea']")).getAttribute("class");
		boolean contains = false;
		contains = notes.contains("ng-not-empty");
		assert(contains == true);
	}
	
	/**
	 * Clicks on the Good QC feedback button
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickOverallFeedbackQCButtonGood() throws IOException, InterruptedException{
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/button[1]"))
			.click();
	}
	
	/**
	 * Clicks on the Average QC feedback button
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickOverallFeedbackQCButtonAvg() throws IOException, InterruptedException{
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/button[2]"))
			.click();
	}
	
	/**
	 * Clicks on the Poor QC feedback button
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickOverallFeedbackQCButtonPoor() throws IOException, InterruptedException{
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/button[3]"))
			.click();
	}
	
	/**
	 * Fills the QC feedback text area with text
	 * @param notes
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void setOverallFeedbackQCNotes(String notes) throws IOException, InterruptedException{
		WebElement qcText = driver.findElement(By.xpath("//*[@id='qcBatchNotes']"));
		qcText.clear();
		qcText.sendKeys(notes);
	}
	
	/**
	 * Checks that the text area is not empty by checking if the class of the div contains ng-not-empty
	 * Sometimes the method will grab the element before the data has been loaded, recommend to have
	 * some kind of wait until the data is loaded
	 */
	public void verifyQCNotes(){
		String notes = driver.findElement(By.xpath("//*[@id='qcBatchNotes']")).getAttribute("class");
		boolean contains = false;
		contains = notes.contains("ng-not-empty");
		assert(contains == true);
	}
	
	/**
	 * Clicks the save button at the bottom of the page
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickSaveButton() throws IOException, InterruptedException{
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/div/div/a"))
			.click();
	}
}