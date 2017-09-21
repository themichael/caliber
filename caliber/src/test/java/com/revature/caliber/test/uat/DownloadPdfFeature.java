package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

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
	
	private PhantomJSDriver driver;
//	private ReportsPage reportsPage;
	private SettingLocationPage setLocPage;
		
	@Before // each scenario
	public void setup(){
		DesiredCapabilities dcaps = new DesiredCapabilities();
		dcaps.setCapability("phantomjs.binary.path", System.getenv("PHANTOM_BIN"));
		dcaps.setJavascriptEnabled(true);
//		dcaps.setCapability("applicationCacheEnabled", true);
		driver = new PhantomJSDriver(dcaps);
		driver.manage().window().setSize(new Dimension(880, 1080));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//		reportsPage = new ReportsPage(driver);
		setLocPage = new SettingLocationPage(driver);
	}
	@After // each scenario
	public void teardown(){
		setLocPage.quitDriver();
//		reportsPage.quitDriver();
	}
	
	@Given("^I am on the Reports page$")
	public void iAmOnTheReportsPage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		reportsPage.gotoReportsPage();
//		Thread.sleep(10000);
//		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(srcFile, new File("C:/Users/Evan Molinelli/Pictures/reportsPage1.jpg"), true);
//		Thread.sleep(50000);
//		File srcFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(srcFile2, new File("C:/Users/Evan Molinelli/Pictures/reportsPage2.jpg"), true);
//		reportsPage.verifyReportsPage();
		
		setLocPage.gotoSettingLocationPage();
		Thread.sleep(2000);
		setLocPage.verifyLocationPage();
		Thread.sleep(2000);
	}

	@Given("^I have selected the year (\\d+) tab$")
	public void iHaveSelectedTheYearTab(int year) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		reportsPage.yearClick();
//		setLocPage.clickCreateLocationBtn();
//		setLocPage.clickCreateCategoryBtn();
	}

	@Given("^I have selected \"([^\"]*)\" as Trainer$")
	public void iHaveSelectedAsTrainer(String trainer) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		assertEquals("http://localhost:8080/caliber/#/vp/reports", driver.getCurrentUrl());
		//driver.findElementById(("dropdownMenu1"));
//		Select dropdown = new Select( driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div/div/ul/li[2]/a")));
//        dropdown.selectByVisibleText(trainer);
//		setLocPage.inputCompanyName("Nike");
//		setLocPage.inputCategoryName("name");
	}

	@Given("^I have selected all the weeks$")
	public void iHaveSelectedAllTheWeeks() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		setLocPage.inputStreetAddress("200 Guy pl.");
//		setLocPage.clickCategorySaveBtn();
	}

	@Given("^I have selected \"([^\"]*)\" as Trainees$")
	public void iHaveSelectedAsTrainees(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		setLocPage.inputCity("Torrance");
//		setLocPage.inputState(4);
	}
	@When("^I click the download button$")
	public void iClickTheDownloadButton() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // Same for Cumulative Scores, Technical Skills, and Weekly Progress
//		reportsPage.clickDownloadBtn();
//		setLocPage.inputZipcode("90504");
//		setLocPage.clickSaveButn();
	}

	@Then("^a PDF file is downloaded$")
	public void aPDFFileIsDownloaded() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

	}
	

}
