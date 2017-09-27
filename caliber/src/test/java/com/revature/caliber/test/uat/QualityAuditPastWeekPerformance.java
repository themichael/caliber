package com.revature.caliber.test.uat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QualityAuditPastWeekPerformance {
	
	public QualityAuditPage qaPage;
	
	@cucumber.api.java.Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		qaPage = new QualityAuditPage(setup.getDriver());
	}
	
	/*@cucumber.api.java.After
	public void teardown(){
		qaPage.closeDriver();
	}
	*/
	@Given("^I have navigated to the quality audit page$")
	public void iHaveNavigatedToTheQualityAuditPage() throws Throwable {
	   qaPage.goToPage();
	   qaPage.verifyPage();
	}

	@Given("^I have selected the year to view$")
	public void iHaveSelectedTheYearToView() throws Throwable {
	    qaPage.clickYearDropdown("2017");
	    qaPage.verifyYear("2017");
	}

	@Given("^I have selected the batch to view$")
	public void iHaveSelectedTheBatchToView() throws Throwable {
	    qaPage.clickBatch("Patrick Walsh - 2/14/17");
	    qaPage.verifyBatch("Patrick Walsh - 2/14/17");
	}

	@When("^I click on a previous week tab$")
	public void iClickOnAPreviousWeekTab() throws Throwable {
	    for(int i=7; i>= 1; i--){
	    	qaPage.clickWeeksForBatch(i);
	    	qaPage.verifyWeekForBatch("week"+i);
	    }
	}

	@Then("^I will be able to see the previous performance for that week$")
	public void iWillBeAbleToSeeThePreviousPerformanceForThatWeek() throws Throwable {
	    qaPage.verifyQCNotes();
	}

}
