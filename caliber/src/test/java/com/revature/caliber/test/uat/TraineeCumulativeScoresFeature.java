package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TraineeCumulativeScoresFeature {
	
	private ReportsPage reports;
	
	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reports = new ReportsPage(setup.getDriver());
	}
	
	@Given("^I have not selected a Trainee$")
	public void iHaveNotSelectedATrainee() throws InterruptedException{
		//Ensuring that 'All Trainees' are selected which is by default
		reports.clickTraineeDropdown();
		reports.chooseTraineeReport("All");
	}

	@Given("^I have not selected a Week$")
	public void iHaveNotSelectedAWeek(){
		//Ensuring that 'All Weeks' are selected which is by default
		reports.clickWeekDropdown();
		reports.chooseWeekReport("week All");
	}

	@When("^I have selected \"([^\"]*)\" as a batch$")
	public void iHaveSelectedAsABatch(String batch){
		reports.clickBatchDropdown();
		reports.chooseBatch(batch);
	}
	
	@Then("^I can see each trainee's scores and rankings from strongest to weakest$")
	public void iCanSeeEachTraineesAcoresAndRankingsFromStrongestToWeakest() {
	    reports.checkCumulativeScoresChart();
	}
}
