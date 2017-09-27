package com.revature.caliber.test.uat;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddWeekToBatchFeature {

	private AssessBatchPage assessBatchPage;
	private ChromeDriverSetup setup;
	private int numberOfWeeks;

	@Before
	public void setup(){
		setup = new ChromeDriverSetup();
		assessBatchPage = new AssessBatchPage(setup.getDriver());
	}

	@Given("^I am on Assess Batch page$")
	public void iAmOnAssessBatchPage() {
	    assessBatchPage.goToPage();
	    assessBatchPage.verifyAssessPage();
	}

	@Given("^I have chose the year (\\d+) tab$")
	public void iHaveChoseTheYearTab(int arg1){
	    assessBatchPage.clickYear();
	}

	@Given("^I have chose \"([^\"]*)\" as Trainer$")
	public void iHaveChoseAsTrainer(String trainerName) throws InterruptedException {
	    assessBatchPage.selectTrainer(trainerName);
	}

	@Given("^I have clicked the add week button$")
	public void iHaveClickedTheAddWeekButton() throws InterruptedException {
		numberOfWeeks = assessBatchPage.numberOfWeeks();
	    assessBatchPage.clickNewWeek();
	}

	@When("^I have clicked yes on the modal$")
	public void iHaveClickedYesOnTheModal() throws InterruptedException {
	    assessBatchPage.newWeekConfirmButton();
	}

	@Then("^the new week appears on the page\\.$")
	public void theNewWeekAppearsOnThePage() {
	    assertTrue(assessBatchPage.doesWeekTabExist(numberOfWeeks));
	}

}
