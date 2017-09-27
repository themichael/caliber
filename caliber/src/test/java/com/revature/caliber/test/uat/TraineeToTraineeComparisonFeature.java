package com.revature.caliber.test.uat;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TraineeToTraineeComparisonFeature {

	private ReportsPage reports;

	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reports = new ReportsPage(setup.getDriver());
	}

	@Given("^on the Reports page$")
	public void onTheReportsPage(){
		reports.gotoReportsPage();
		reports.verifyReportsPage();
	}

	@Given("^I have clicked the Person Icon in the Technical Skills$")
	public void iHaveClickedThePersonIconInTheTechnical_Skills() throws InterruptedException{
		reports.clickTechnicalSkillsModal();
	}

	@Given("^I selected \"([^\"]*)\" as a trainee$")
	public void iSelectedAsTTrainee(String trainee) throws InterruptedException {
	    reports.chooseTraineeTechSkills(trainee);
	}

	@Given("^I have selected \"([^\"]*)\" as a Trainee$")
	public void iHaveSelectedAsATrainee(String trainee) throws InterruptedException {
	    reports.chooseTraineeTechSkills(trainee);
	}

	@When("^I click the close button$")
	public void iClickTheCloseButton(){
	    reports.closeTraineeCompModal();
	}

	@Then("^I can see both trainees performances$")
	public void iCanSeeBothTraineesPerformances(){
	    reports.checkTechSkillsGraph();
	}
}
