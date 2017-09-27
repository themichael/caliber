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

//	@After // each scenario
//	public void teardown() {
//		reports.closeDriver();
//	}

	@Given("^on the Reports page$")
	public void on_the_Reports_page() throws Throwable {
		reports.gotoReportsPage();
		reports.verifyReportsPage();
	}

	@Given("^I have clicked the Person Icon in the Technical Skills$")
	public void i_have_clicked_the_Person_Icon_in_the_Technical_Skills() throws Throwable {
		reports.clickTechnicalSkillsModal();
	}

	@Given("^I selected \"([^\"]*)\" as a trainee$")
	public void i_selected_as_a_trainee(String trainee) throws Throwable {
	    reports.chooseTraineeTechSkills(trainee);
	}

	@Given("^I have selected \"([^\"]*)\" as a Trainee$")
	public void i_have_selected_as_a_Trainee(String trainee) throws Throwable {
	    reports.chooseTraineeTechSkills(trainee);
	}

	@When("^I click the close button$")
	public void i_click_the_close_button() throws Throwable {
	    reports.closeTraineeCompModal();
	}

	@Then("^I can see both trainees performances$")
	public void i_can_see_both_trainees_performances() throws Throwable {
	    reports.checkTechSkillsGraph();
//	    reports.closeDriver();
	}
}
