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
	
	
	@Given("^I have selected a specific batch$")
	public void iHaveSelectedASpecificBatch() throws Throwable {
	    
	}

	@Given("^I have not selected a Trainee$")
	public void iHaveNotSelectedATrainee() throws Throwable {
	    
	}

	@Given("^I have not selected a Week$")
	public void iHaveNotSelectedAWeek() throws Throwable {
	    
	}

	@When("^I view the cumulative scores table$")
	public void iViewTheCumulativeScoresTable() throws Throwable {
	    
	}
	
	@Then("^I can see each trainee's scores and rankings from strongest to weakest$")
	public void i_can_see_each_trainee_s_scores_and_rankings_from_strongest_to_weakest() throws Throwable {
	    reports.checkCumulativeScoresChart();
	}
}
