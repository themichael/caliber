package com.revature.caliber.test.uat;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GradeTraineeFeature extends ChromeDriverSetup{
	
	private AssessBatchPage assessBatch;
	
	@Before
	public void setup(){
		ChromeDriverSetup driver = new ChromeDriverSetup();
		assessBatch = new AssessBatchPage(driver.getDriver());
	}
//	
//	@After
//	public void teardown(){
//		assessBatch.closeDriver();
//	}
	
	@Given("^I am on the Assess Batch Page$")
	public void iAmOnTheAssessBatchPage(){
		assessBatch.goToPage();
		assessBatch.verifyAssessPage();
	}

	@Given("^I have picked the Week (\\d+) tab$")
	public void iHavePickedTheWeekTab(int arg1){
		assessBatch.clickWeekTab();
	}

	@Given("^I have submitted \"([^\"]*)\" as the grade for \"([^\"]*)\"$")
	public void i_have_submitted_as_the_grade_for(String grade, String traineeName) throws Throwable {
	   assessBatch.enterGrades(traineeName, grade);
	}

	@When("^I hit the Save button$")
	public void iHitTheSaveButton(){
		assessBatch.saveButton();
	}

	@Then("^the grades \"([^\"]*)\" for \"([^\"]*)\" are recorded$")
	public void theGradesAreRecorded(String grade, String traineeName) throws Throwable{
		assessBatch.goToPage();
		assessBatch.clickWeekTab();
		Thread.sleep(7000);
		assessBatch.checkIfGradesWereInput(traineeName, grade);
	}

}
