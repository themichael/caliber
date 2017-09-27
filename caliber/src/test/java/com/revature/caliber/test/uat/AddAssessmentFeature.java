package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddAssessmentFeature {
	
	private AssessBatchPage assessBatch;
	
	@Before
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		assessBatch = new AssessBatchPage(setup.getDriver());
	}
	
	@Given("^I am located on the Assess Batch page$")
	public void i_am_located_on_the_Assess_Batch_page() throws Throwable {
	    assessBatch.goToPage();
	    assessBatch.verifyAssessPage();
	}

	@Given("^I have selected a batch$")
	public void i_have_selected_a_batch() throws Throwable {
	    assessBatch.selectBatch();
	}

	@Given("^I have clicked the Week (\\d+) tab$")
	public void i_have_clicked_the_Week_tab(int arg1) throws Throwable {
	    assessBatch.clickWeekTab(1);
	}

	@Given("^I have clicked Create Assessment button$")
	public void i_have_clicked_Create_Assessment_button() throws Throwable {
	    assessBatch.clickCreateAssessment();
	}

	@Given("^I have selected \"([^\"]*)\" as the Category$")
	public void i_have_selected_as_the_Category(String category) throws Throwable {
		Thread.sleep(1000);
	    assessBatch.selectCreateAssessementCategory(category);
	}

	@Given("^I have entered (\\d+) as the Max Points$")
	public void i_have_entered_as_the_Max_Points(int points) throws Throwable {
	    Integer pointsToSend = new Integer(points);
	    assessBatch.maxPoints(pointsToSend.toString());
	}

	@Given("^I have selected \"([^\"]*)\" as the Assessment Type$")
	public void i_have_selected_as_the_Assessment_Type(String type) throws Throwable {
		Thread.sleep(1000);
	    assessBatch.selectCreateAssessmentType(type);
	}

	@When("^I click the Save button$")
	public void i_click_the_Save_button() throws Throwable {
	    assessBatch.modalSaveButton();
	}

	@Then("^the \"([^\"]*)\" Exam appears on the screen$")
	public void the_Exam_appears_on_the_screen(String exam) throws Throwable {
	    boolean actual = assessBatch.assessmentCheck(exam);
		assertEquals(actual, true);
	}
}
