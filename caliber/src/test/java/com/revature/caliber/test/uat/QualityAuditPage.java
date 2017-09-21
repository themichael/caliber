package com.revature.caliber.test.uat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QualityAuditPage {

	private WebDriver driver;
	
	private File srcFile; 
	/**
	 * takes the screenshot 
	 * remove for integration
	 * @param filename dont include .jpg
	 * @throws IOException
	 */
	private void screenshot(String filename) throws IOException{
		srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("C:/Users/Ryan/Desktop/CaliberPics/" +filename+".jpg"), true);
	}
	
	public QualityAuditPage(WebDriver driver){
		this.driver = driver;
	}
	
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
		screenshot("yearDDBefore");
		
		//clicks on '2017' from the unhidden menu
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[1]/ul/li[2]/a"))
			.click();
		screenshot("yearDDAfter");
		
	}
	
	/**
	 * Select Patrick Walsh - 2/13/17 from the batch by year dropdown, which is the first element
	 */
	public void clickBatchTrainer(){
		//click on batch
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[2]/a"))
			.click();
		//click on 'Patrick Walsh - 2/13/17' from the unhidden menu
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[1]/ul[1]/li[2]/ul/li/a"))
			.click();
	}
	
	/**
	 * Select the week for the assessment from the tab bar
	 */
	public void clickWeeksForBatch(){
		//currently clicks week 8, change last /li[x] where x is the week; will click new week if set to last element in the list
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li[8]"))
			.click();
	}
	
	/**
	 * Select the add week button at the end of the week tab
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void clickAddWeeksForBatchButton() throws IOException, InterruptedException{
		driver.findElement(By.xpath("/html/body/div[1]/ui-view/ui-view/div[1]/div[1]/div/div[2]/ul/li[9]"))
			.click();
		Thread.sleep(500);
		screenshot("batchWeekAddAfter");
		//needs to click on alert popup to confirm
		driver.switchTo().activeElement().findElement(By.xpath("//*[@id='confirmingweeks']/div/div/div[2]/button[2]"))
			.click(); //currently clicking no
		Thread.sleep(500);
		screenshot("batchWeekAddCancel");
	}
	
	/**
	 * Clicks the individual feedback button next to the trainee multiple clicks cycle through good, average, poor, and superstar
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void clickIndividualFeedbackButton() throws InterruptedException, IOException{
		//need to click on the currently displayed button
			//check if it is displayed
		boolean isAvailable;
		int step = 1;
		for(;step <=5; step++){
			isAvailable = driver.findElement(By.xpath("//*[@id='qcTrainees']/div/ul/table/tbody/tr[1]/td[2]/button["+step+"]/i"))
								.isDisplayed();
			if(isAvailable)
				break;
		}
		screenshot("IndvFeedBackClickBefore");
		//finally, clicks needs logic for determining how many clicks to reach desired state
		WebElement button = driver.findElement(By.xpath("//*[@id='qcTrainees']/div/ul/table/tbody/tr[1]/td[2]/button["+step+"]"));
		button.click();
		Thread.sleep(500);
		screenshot("IndvFeedBackClickAfter");
	}
	
	/**
	 * Fills trainee note text area
	 * @param notes
	 * @throws IOException
	 */
	public void setNoteOnTraineeTextArea(String notes) throws IOException{
		screenshot("traineeTextAreaBefore");
		WebElement traineeTextArea = driver.findElement(By.xpath("//*[@id='noteTextArea']"));
		traineeTextArea.clear();
		traineeTextArea.sendKeys(notes);
		screenshot("traineeTextAreaAfter");
	}
	
	/**
	 * Clicks on the Good QC feedback button
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickOverallFeedbackQCButtonGood() throws IOException, InterruptedException{
		screenshot("QCButtonGoodBefore");
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/button[1]"))
			.click();
		Thread.sleep(500);
		screenshot("QCButtonGoodAfter");
	}
	
	/**
	 * Clicks on the Average QC feedback button
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickOverallFeedbackQCButtonAvg() throws IOException, InterruptedException{
		screenshot("QCButtonAvgBefore");
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/button[2]"))
			.click();
		Thread.sleep(500);
		screenshot("QCButtonAvgAfter");
	}
	
	/**
	 * Clicks on the Poor QC feedback button
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickOverallFeedbackQCButtonPoor() throws IOException, InterruptedException{
		screenshot("QCButtonPoorBefore");
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/button[3]"))
			.click();
		Thread.sleep(500);
		screenshot("QCButtonPoorAfter");
	}
	
	/**
	 * Fills the QC feedback text area with text
	 * @param notes
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void setOverallFeedbackQCNotes(String notes) throws IOException, InterruptedException{
		screenshot("QCFeedBackBefore");
		WebElement qcText = driver.findElement(By.xpath("//*[@id='qcBatchNotes']"));
		qcText.clear();
		qcText.sendKeys(notes);
		Thread.sleep(500);
		screenshot("QCFeedBackAfter");
	}
	
	/**
	 * Clicks the save button at the bottom of the page
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void clickSaveButton() throws IOException, InterruptedException{
		screenshot("QCSaveButtonBefore");
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div[2]/div[2]/div/div/a"))
			.click();
		Thread.sleep(500);
		screenshot("QCSaveButtonAfter");
	}
	
	
}
