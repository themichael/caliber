package com.revature.caliber.test.uat;

import cucumber.api.java.After;
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
	
//	@After // each scenario
//	public void teardown() {
//		reports.closeDriver();
//	}

	@Given("^I am on the reports page$")
	public void i_am_on_the_reports_page() throws Throwable {
		reports.gotoReportsPage();
	}

	@Given("^I have clicked the person icon in the Technical Skills$")
	public void i_have_clicked_the_person_icon_in_the_Technical_Skills() throws Throwable {
		reports.clickTechnicalSkillsModal();
	}

	@Given("^I click \"([^\"]*)\" as a trainee$")
	public void i_click_as_a_trainee(String trainee) throws Throwable {
		reports.chooseTraineeTechSkills(trainee);
	}

	@When("^I tap the close button$")
	public void i_tap_the_close_button() throws Throwable {
		reports.closeTraineeCompModal();
	}

	@Then("^I can see the trainee performance compared to the batch$")
	public void i_can_see_the_trainee_performance_compared_to_the_batch() throws Throwable {
		reports.checkTechSkillsGraph();
<<<<<<< HEAD
//		reports.closeDriver();
=======
>>>>>>> 850d2153f07c6a0c27843e66a8635c5895c80d27
	}
}
