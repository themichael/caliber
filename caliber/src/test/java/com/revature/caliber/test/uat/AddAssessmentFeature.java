package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddAssessmentFeature {
	
	AssessBatchPage assessBatch;
	
	@Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		assessBatch = new AssessBatchPage(setup.getDriver());
	}
	
	@Given("^I am on the Assess Batch page$")
	public void iAmOnTheAssessBatchPage() {
		assessBatch.verifyAssessPage();
	}

	@Given("^I have selected a batch$")
	public void iHaveSelectedABatch() {
		assessBatch.selectBatch();
	}

	@Given("^I have clicked the Week (\\d+) tab$")
	public void iHaveClickedTheWeekTab() {
		assessBatch.clickWeekTab();
	}

	@Given("^I have clicked Create Assessment button$")
	public void iHaveClickedCreateAssessmentButton() {
		assessBatch.clickCreateAssessment();
	}

	@Given("^I have selected \"([^\"]*)\" as the Category$")
	public void iHaveSelectedAsTheCategory(String category) {
		assessBatch.selectAssessementCategory(category);
	}

	@Given("^I have entered (\\d+) as the Max Points$")
	public void iHaveEnteredAsTheMaxPoints(int points) {
		Integer pointsToSend = new Integer(points);
		assessBatch.maxPoints(pointsToSend.toString());
	}

	@Given("^I have selected \"([^\"]*)\" as the Assessment Type$")
	public void iHaveSelectedAsTheAssessmentType(String type) {
		assessBatch.selectAssessmentType(type);
	}

	@When("^I click the Save button$")
	public void iClickTheSaveButton() {
		assessBatch.saveButton();
	}

	@Then("^the \"([^\"]*)\" Exam appears on the screen$")
	public void theJavaExamAppearsOnTheScreen(String type) {
		boolean actual = assessBatch.assessmentCheck(type);
		assertEquals(actual, true);
	}
	@After
	public void teardown(){
		assessBatch.closeDriver();
	}
}
