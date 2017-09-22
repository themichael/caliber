package com.revature.caliber.test.uat;
//package com.revature.caliber.test.uat;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//
//import cucumber.api.PendingException;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//
//public class ImportBatchFeature {
//	
//	private ManageBatchPage managePage;
//	
//	@Before // each scenario
//	public void setup(){
//		DesiredCapabilities dcaps = new DesiredCapabilities();
//		dcaps.setJavascriptEnabled(true);
//		dcaps.setCapability("phantomjs.binary.path", "C:/phantomjs/phantomjs-2.1.1-windows/bin/phantomjs.exe");
//		WebDriver driver = new PhantomJSDriver(dcaps);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		managePage = new ManageBatchPage((PhantomJSDriver)driver);
//	}
////	@After // each scenario
////	public void teardown(){
////		driver.quit();
////	}
//
//	@Given("^I am on the Manage Batch page$")
//	public void iAmOnTheManageBatchPage() throws Throwable {
//		managePage.gotoManagePage();
//		managePage.verifyManagePage();
//	}
//
//	@Given("^I clicked Import Batch button$")
//	public void iClickedImportBatchButton() throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		throw new PendingException();
//	}
//
//	@Given("^I selected a Batch$")
//	public void iSelectedABatch() throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		throw new PendingException();
//	}
//
//	@Given("^I selected a Location$")
//	public void iSelectedALocation() throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		throw new PendingException();
//	}
//
//	@When("^I click the Import button$")
//	public void iClickTheImportButton() throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		throw new PendingException();
//	}
//
//	@Then("^the new batch appears on the table$")
//	public void theNewBatchAppearsOnTheTable() throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		throw new PendingException();
//	}
//}

