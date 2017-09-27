package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GradeTraineeFeature{
	
	private AssessBatchPage assessBatch;
	
	@Before
	public void setup(){
		ChromeDriverSetup driver = new ChromeDriverSetup();
		assessBatch = new AssessBatchPage(driver.getDriver());
	}

	@Given("^I am on the Assess Batch Page$")
	public void iAmOnTheAssessBatchPage(){
		assessBatch.goToPage();
		assessBatch.verifyAssessPage();
	}

	@Given("^I have picked the Week (\\d+) tab$")
	public void iHavePickedTheWeekTab(int index){
		assessBatch.clickWeekTab(index);
	}

	@Given("^I have submitted \"([^\"]*)\" as the grade for \"([^\"]*)\"$")
	public void i_have_submitted_as_the_grade_for(String grade, String traineeName) throws InterruptedException{
		ZZZ.waitForPageLoad();
		Thread.sleep(2000);
	    assessBatch.enterGrades(traineeName, grade);
	}

	@When("^I refresh the Assess Batch Page$")
	public void i_refresh_the_Assess_Batch_Page() {
		assessBatch.goToPage();
	}

	@Then("^the grades \"([^\"]*)\" for \"([^\"]*)\" are recorded$")
	public void theGradesAreRecorded(String grade, String traineeName) throws InterruptedException{
		assessBatch.goToPage();
		assessBatch.clickWeekTab(1);
		ZZZ.waitForPageLoad();
		Thread.sleep(7000);
		assessBatch.checkIfGradesWereInput(traineeName, grade);
	}

}
