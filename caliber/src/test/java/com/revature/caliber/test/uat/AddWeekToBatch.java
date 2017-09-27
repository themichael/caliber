package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddWeekToBatch {

	private AssessBatchPage assessBatchPage;
	private ChromeDriverSetup setup;
	private int numberOfWeeks;

	@Before
	public void setup(){
		setup = new ChromeDriverSetup();
		assessBatchPage = new AssessBatchPage(setup.getDriver());
	}

	@Given("^I am on Assess Batch page$")
	public void i_am_on_Assess_Batch_page() throws Throwable {
	    assessBatchPage.goToPage();
	    assessBatchPage.verifyAssessPage();
	}

	@Given("^I have chose the year (\\d+) tab$")
	public void i_have_chose_the_year_tab(int arg1) throws Throwable {
	    assessBatchPage.clickYear();
	}

	@Given("^I have chose \"([^\"]*)\" as Trainer$")
	public void i_have_chose_as_Trainer(String trainerName) throws Throwable {
	    assessBatchPage.selectTrainer(trainerName);
	}

	@Given("^I have clicked the add week button$")
	public void i_have_clicked_the_add_week_button() throws Throwable {
		numberOfWeeks = assessBatchPage.numberOfWeeks();
	    assessBatchPage.clickNewWeek();
	}

	@When("^I have clicked yes on the modal$")
	public void i_have_clicked_yes_on_the_modal() throws Throwable {
	    assessBatchPage.newWeekConfirmButton();
	}

	@Then("^the new week appears on the page\\.$")
	public void the_new_week_appears_on_the_page() throws Throwable {
	    assertTrue(assessBatchPage.doesWeekTabExist(numberOfWeeks));
	    assessBatchPage.closeDriver();
	}

}
