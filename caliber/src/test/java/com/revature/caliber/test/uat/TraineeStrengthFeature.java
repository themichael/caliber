package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TraineeStrengthFeature {
	
	private ReportsPage reports;

	@Before // each scenario
	public void setup() {
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reports = new ReportsPage(setup.getDriver());
	}

	@Given("^I am on the reports page$")
	public void iAmOnTheReportsPage() {
		reports.gotoReportsPage();
	}

	@Given("^I have clicked the person icon in the Technical Skills$")
	public void iHaveClickedThePersonIconInTheTechnicalSkills() throws InterruptedException {
		reports.clickTechnicalSkillsModal();
	}

	@Given("^I click \"([^\"]*)\" as a trainee$")
	public void iClickAsATrainee(String trainee) throws InterruptedException {
		reports.chooseTraineeTechSkills(trainee);
	}

	@When("^I tap the close button$")
	public void iTapTheCloseButton() {
		reports.closeTraineeCompModal();
	}

	@Then("^I can see the trainee performance compared to the batch$")
	public void iCanSeeTheTraineePerformanceComparedToTheBatch() {
		reports.checkTechSkillsGraph();
	}
}
