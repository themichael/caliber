package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DownloadPdfFeature extends DriverSetup{
/*	
	@Before // each scenario
	public void setup(){
		driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.setJavascriptEnabled(true);
	}
	@After // each scenario
	public void teardown(){
		driver.quit();
	}
	
	@Given("^I am on the Reports page$")
	public void iAmOnTheReportsPage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://localhost:8080/caliber/#/vp/reports");
		assertEquals("http://localhost:8080/caliber/#/vp/reports", driver.getCurrentUrl());
	}

	@Given("^I have selected the year (\\d+) tab$")
	public void iHaveSelectedTheYearTab(int year) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		Select dropdown = new Select( driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div/div/ul/li[1]/a")));
//        dropdown.selectByVisibleText(Integer.toString(year));
	}

	@Given("^I have selected \"([^\"]*)\" as Trainer$")
	public void iHaveSelectedAsTrainer(String trainer) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals("http://localhost:8080/caliber/#/vp/reports", driver.getCurrentUrl());
		driver.findElementById(("dropdownMenu1"));
//		Select dropdown = new Select( driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div/div/ul/li[2]/a")));
//        dropdown.selectByVisibleText(trainer);
	}

	@Given("^I have selected all the weeks$")
	public void iHaveSelectedAllTheWeeks() throws Throwable {
	    // Write code here that turns the phrase above into concrete actionss
	}

	@Given("^I have selected \"([^\"]*)\" as Trainees$")
	public void iHaveSelectedAsTrainees(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}
	@When("^I click the download button$")
	public void iClickTheDownloadButton() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // Same for Cumulative Scores, Technical Skills, and Weekly Progress
		assertEquals("http://localhost:8080/caliber/#/vp/reports", driver.getCurrentUrl());
		Select dropdown = new Select(driver.findElement(By.id("dropdownMenu1")));
		dropdown.selectByVisibleText("Charts");
	}

	@Then("^a PDF file is downloaded$")
	public void aPDFFileIsDownloaded() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		driver.findElement(By.linkText("mailmerge.xls")).click();
		    File getLatestFile = getLatestFilefromDir("C:/Users/Evan Molinelli/Downloads");
		    String fileName = getLatestFile.getName();
		    assertEquals(fileName.equals("Trainee.pdf"), "Downloaded file name is not matching with expected file name");
	}
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	
	private File getLatestFilefromDir(String dirPath){
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
*/
}
