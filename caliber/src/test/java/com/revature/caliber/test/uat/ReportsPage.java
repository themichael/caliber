package com.revature.caliber.test.uat;


import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.digest;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

public class ReportsPage {

	/***
	 * @author Evan Molinelli
	 * 
	 */
	private WebDriver driver;

	public ReportsPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver)driver;
		this.driver.get("http://localhost:8080/caliber#/vp/reports");
	}

	public void gotoReportsPage() {
		driver.get("http://localhost:8080/caliber#/vp/reports");
	}

	public void goToHome() {
		driver.get("http://localhost:8080/caliber#/vp/home");
	}

	/***
	 * Verifying if you're on the reports page
	 */
	public void verifyReportsPage() {
		assertEquals("http://localhost:8080/caliber#/vp/reports", driver.getCurrentUrl());
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
	 *  Click the batch dropdown 'Patrick Walsh - 2/13/17'
	 */
	public void clickBatchDropdown() {
		driver.findElement(By.id("currentBatchTrainer")).click();
		driver.switchTo().activeElement();
	}

	/**
	 *  Click on the specific batch in the dropdown
	 */
	public void chooseBatch() {
		// Default is one batch 'Patrick Walsh - 2/13/17' and there's only one
		// choice
		driver.findElement(By.id("Patrick Walsh - 2/14/17")).click();
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
		driver.findElement(By.id("week 1")).click();
	}

	/***
	 *  Click the trainee dropdown
	 */
	public void clickTraineeDropdown() {
		// Default trainee is 'All' if Trainee dropdown is not clicked
		driver.findElement(By.id("reportTrainer")).click();
		driver.switchTo().activeElement();
	}

	/***
	 *  Click on a specific Trainee
	 * @param trainee
	 */
	public void chooseTraineeReport(String trainee) {
		// id = 'lastname, firstname' so String trainee = "lastname, firstname";
		// 'Ali, Fareed' for testing purposes
		driver.findElement(By.id("Ali, Fareed")).click();
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
	 */
	public void clickTechnicalSkillsModal() {
		// Opens modal for 'Trainee Comparison'
		driver.findElement(By.id("traineeCompGlyphBtnModal")).click();
		driver.switchTo().activeElement();
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
//		Thread.sleep(1000);
		//Uses cssSelector because 'id' was not able to click the specific checkbox
		driver.findElement(By.cssSelector("#insert-trainee > div > div > div.modal-body > div > table > tbody > tr:nth-child(2) > th > input")).click();
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
		driver.quit();
	}

	/**
	 * Never used because can't validate/confirm if a file has downloaded
	 * @param downloadPath
	 * @param fileName
	 * @return
	 */
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
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