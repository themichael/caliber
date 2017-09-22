package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.itextpdf.text.log.SysoCounter;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DownloadPdfFeature {

	private WebDriver driver;
	private ReportsPage reportsPage;

	@Before // each scenario
	public void setup(){
		System.setProperty("webdriver.chrome.driver","C:\\selenium\\chromedriver.exe");
     	ChromeOptions options = new ChromeOptions();
     	 options.addArguments("--headless");
         options.addArguments("--window-size=1200x600");
         
     	WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		reportsPage = new ReportsPage(driver);
	}

	@After // each scenario
	public void teardown() {
		// setLocPage.quitDriver();
		reportsPage.quitDriver();
	}

	@Given("^I am on the Reports page$")
	public void iAmOnTheReportsPage() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		reportsPage.gotoReportsPage();
		Thread.sleep(10000);
//        TakesScreenshot ts = (TakesScreenshot)driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        String dest = "C:/Users/Evan Molinelli/Pictures/reportsPage2.jpg";
//        File destination = new File(dest);
//        FileUtils.copyFile(source, destination);
//        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(srcFile, new File("C:/Users/Evan Molinelli/Pictures/reportsPage2.jpg"), true);
		reportsPage.verifyReportsPage();

	}

	@Given("^I have selected the year (\\d+) tab$")
	public void iHaveSelectedTheYearTab(int year) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		reportsPage.clickReportYear("2017");
	}

	@Given("^I have selected \"([^\"]*)\" as Trainer$")
	public void iHaveSelectedAsTrainer(String trainer) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Given("^I have selected all the weeks$")
	public void iHaveSelectedAllTheWeeks() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
	}

	@Given("^I have selected \"([^\"]*)\" as Trainees$")
	public void iHaveSelectedAsTrainees(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// setLocPage.inputCity("Torrance");
		// setLocPage.inputState(4);
	}

	@When("^I click the download button$")
	public void iClickTheDownloadButton() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Same for Cumulative Scores, Technical Skills, and Weekly Progress
		 reportsPage.clickChartDropdownPdf();
		 reportsPage.clickChartFeedbackDownloadPdf();
		 //or reportsPage.clickChartDownloadPdf();
		 
	}

	@Then("^a PDF file is downloaded$")
	public void aPDFFileIsDownloaded() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		File f1 = reportsPage.getLatestFilefromDir("C:\\Users\\Evan Molinelli\\Downloads");
		Thread.sleep(10000);
		assertTrue(reportsPage.isFileDownloaded("C:\\Users\\Evan Molinelli\\Downloads", "Trainee.pdf"));
	}

}
