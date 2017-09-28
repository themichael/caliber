package com.revature.caliber.test.uat;


import static org.junit.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/***
 * @author Evan Molinelli
 * 
 */
public class ReportsPage {

	private WebDriver driver;
	private static final String BASE_URL = "CALIBER_API_SERVER";

	public ReportsPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver)driver;
		this.driver.get(System.getenv(BASE_URL)+"caliber/#/vp/reports");
	}

	public void gotoReportsPage() {
		driver.get(System.getenv(BASE_URL)+"caliber/#/vp/reports");
	}

	public void goToHome() {
		driver.get(System.getenv(BASE_URL)+"caliber/#/vp/home");
	}

	/***
	 * Verifying if you're on the reports page
	 */
	public void verifyReportsPage() {
		assertEquals(System.getenv(BASE_URL)+"caliber/#/vp/reports", driver.getCurrentUrl());
	}

	/***
	 * Clicking the 'year' dropdown and then the precise year.
	 * @param year
	 */
	public void clickReportYear(String year) {
		// Default year is '2017'
		driver.findElement(By.id("reportYear")).click();
		driver.switchTo().activeElement();
		
		switch (year) {
		case "2018":
			driver.findElement(By.id(year)).click();
			break;
		case "2017":
			driver.findElement(By.id(year)).click();
			break;
		case "2016":
			driver.findElement(By.id(year)).click();
			break;
		case "2015":
			driver.findElement(By.id(year)).click();
			break;
		default:
			driver.findElement(By.id("2017")).click();
			break;

		}
	}

	/***
	 *  Click the batch dropdown 'Patrick Walsh - 2/14/17'
	 */
	public void clickBatchDropdown() {
		driver.findElement(By.id("currentBatchTrainer")).click();
		driver.switchTo().activeElement();
	}

	/**
	 *  Click on the specific batch in the dropdown
	 */
	public void chooseBatch(String batch) {
		// Default is one batch 'Patrick Walsh - 2/14/17' and there's only one
		// choice
		driver.findElement(By.id(batch)).click();
	}

	/***
	 *  Click the week dropdown
	 */
	public void clickWeekDropdown() {
		driver.findElement(By.id("reportWeek")).click();
		driver.switchTo().activeElement();
	}

	/***
	 *  Clicking on the week to choose, 'All' is the default if week is not chosen
	 * @param week
	 */
	public void chooseWeekReport(String week) {
		// id = "week #" so String week = "week #" also
		//hardcoded 'week 1'
		driver.findElement(By.id(week)).click();
	}

	/***
	 *  Click the trainee dropdown
	 */
	public void clickTraineeDropdown() {
		// Default trainee is 'All' if Trainee dropdown is not clicked
		driver.findElement(By.id("traineeReport")).click();
		driver.switchTo().activeElement();
	}

	/***
	 *  Click on a specific Trainee in the Trainee dropdown
	 * @param trainee
	 * @throws InterruptedException 
	 */
	public void chooseTraineeReport(String trainee) throws InterruptedException {
		// id = 'lastname, firstname' so String trainee = "lastname, firstname";
		Thread.sleep(1000);
		driver.findElement(By.id("trainee "+trainee)).click();
	}

	/***
	 *  Click the chart glyphicon for dropdown
	 */
	public void clickChartDropdownPdf() {
		driver.findElement(By.id("dropdownReportsMenu")).click();
		driver.switchTo().activeElement();
	}

	/***
	 *  Click the chart glyphicon dropdown and choosing 'Charts'
	 * @throws InterruptedException
	 */
	public void clickChartDownloadPdf() throws InterruptedException {
		driver.findElement(By.id("chartsDownloadPdf")).click();
		// Thread.sleep(1000);
	}

	/***
	 *  Click the chart glyphicon dropdown and choosing 'Charts + Feedback'
	 * @throws InterruptedException
	 */
	public void clickChartFeedbackDownloadPdf() throws InterruptedException {
		driver.findElement(By.id("chartsFeedbackDownloadPdf")).click();
		// Thread.sleep(1000);
	}

	/***
	 *  Click the down arrow glyphicon next to 'Cumulative Scores' to download chart
	 */
	public void clickCumulativeScoreGlyph() {
		driver.findElement(By.id("cumulativeScoreDownload")).click();
	}

	/***
	 *  Click the down arrow glyphicon next to 'Technical Skills' to download chart
	 */
	public void clickTechnicalSkillGlyph() {
		driver.findElement(By.id("technicalSkillDownload")).click();
	}

	/***
	 *  Click the right corner glychicon in the 'Technical Skills' block (person glyphicon)
	 * @throws InterruptedException 
	 */
	public void clickTechnicalSkillsModal() throws InterruptedException {
		// Opens modal for 'Trainee Comparison'
		driver.findElement(By.id("traineeCompGlyphBtnModal")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.className("modal-title"));
	}

	/***
	 *  Closes the 'Trainee Comparison' modal
	 */
	public void closeTraineeCompModal() {
		driver.findElement(By.id("traineeComparisonCloseX")).click();
	}

	/**
	 *  Select a specific Trainee by checking the box in the modal
	 * @param trainee
	 * @throws InterruptedException
	 */
	public void chooseTraineeTechSkills(String trainee) throws InterruptedException {
		// id = 'lastname, firstname' so String trainee = "lastname, firstname";
		// 'Ali, Fareed' for testing purposes
		//Uses cssSelector because 'id' was not able to click the specific checkbox
		switch (trainee) {
		case "Ali, Fareed":
			driver.findElement(By.cssSelector("#insert-trainee > div > div > div.modal-body > div > table > tbody > tr:nth-child(14) > th > input")).click();
			break;
		case "Duong, Jack":
			driver.findElement(By.cssSelector(
					"#insert-trainee > div > div > div.modal-body > div > table > tbody > tr:nth-child(8) > th > input"))
					.click();
			break;
		default:
			//"Liu, Daniel" is the default
			driver.findElement(By.cssSelector("#insert-trainee > div > div > div.modal-body > div > table > tbody > tr:nth-child(4) > th > input")).click();
		}

	}
	
	public void checkTechSkillsGraph(){
		driver.findElement(By.id("radarBatchOverall"));
	}

	/**
	 *  Click the down arrow glyphicon next to 'Weekly Progress' to download chart
	 */
	public void clickWeeklyProgressGlyph() {
		driver.findElement(By.id("weeklyProgressDownload")).click();
	}

	/**
	 * Quits the driver.
	 */
	public void closeDriver() {
		driver.close();
	}
	
	/**
	 * Checks if the weekly progress chart is displayed
	 */
	public void checkWeeklyProgressChart(){
		driver.findElement(By.id("line1")).isDisplayed();
	}

	/**
	 * Checks if the weekly progress chart after choosing a specific Trainee.
	 * (Week has to be 'All' for it to work)
	 */
	public void checkTraineeWeeklyProgressChart(){
		driver.findElement(By.id("line9"));
	}
	
	/**
	 * Checks if the Cumulative Scores chart is displayed
	 */
	public void checkCumulativeScoresChart(){
		driver.findElement(By.id("line6"));
	}
	
	/**
	 * Checks if the Assessment Breakdown of a specific trainee
	 */
	public void checkAssessmentBreakdownChart(){
		driver.findElement(By.id("bar"));
	}
	
	/**
	 * Never used because can't validate/confirm if a file has downloaded
	 * @param downloadPath
	 * @param fileName
	 * @return
	 */
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName))
				return true;
		}
		return false;
	}

	/**
	 * Never used, but to check file directory to get latest file downloaded
	 * @param dirPath
	 * @return
	 */
	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

}