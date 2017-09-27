package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
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
	public void i_have_not_selected_a_Trainee() throws Throwable {
		//Ensuring that 'All Trainees' are selected which is by default
		reports.clickTraineeDropdown();
		reports.chooseTraineeReport("trainee All");
	}

	@Given("^I have not selected a Week$")
	public void i_have_not_selected_a_Week() throws Throwable {
		//Ensuring that 'All Weeks' are selected which is by default
		reports.clickWeekDropdown();
		reports.chooseWeekReport("week All");
	}

	@When("^I have selected \"([^\"]*)\" as a batch$")
	public void i_have_selected_as_a_batch(String batch) throws Throwable {
		reports.clickBatchDropdown();
		reports.chooseBatch(batch);
	}
	
	@Then("^I can see each trainee's scores and rankings from strongest to weakest$")
	public void i_can_see_each_trainee_s_scores_and_rankings_from_strongest_to_weakest() throws Throwable {
	    reports.checkCumulativeScoresChart();
	}
}
