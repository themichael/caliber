//package com.revature.caliber.test.uat;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//
//import com.gargoylesoftware.htmlunit.BrowserVersion;
//
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//
//public class GradeTraineeFeature {
//	
//	private PhantomJSDriver driver;
//	private AssessBatchPage assessBatch;
//	
////	@cucumber.api.java.Before
////	public void setup(){
////		driver = new HtmlUnitDriver(BrowserVersion.CHROME);
////		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
////		assessBatch = new AssessBatchPage(driver);
////	}
//	
//	
//	@Given("^I am on the Assess Batch Page$")
//	public void iAmOnTheAssessBatchPage(){
//		// Write code here that turns the phrase above into concrete actions
////		assessBatch.gotoPage();
////		assessBatch.verifyAssessBatchPage();
//	}
//
//	@Given("^I have picked the Week (\\d+) tab$")
//	public void iHavePickedTheWeekTab(int arg1){
//		// Write code here that turns the phrase above into concrete actions
//	}
//
//	@Given("^I have submitted (\\d+) as the grade$")
//	public void iHaveSubmittedAsTheGrade(int arg1){
//		// Write code here that turns the phrase above into concrete actions
//	}
//
//	@When("^I hit the Save button$")
//	public void iHitTheSaveButton(){
//		// Write code here that turns the phrase above into concrete actions
//		
//	}
//
//	@Then("^the grades are recorded$")
//	public void theGradesAreRecorded(){
//		// Write code here that turns the phrase above into concrete actions
//	}
//
//}
