package com.revature.caliber.test.uat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DownloadPdfFeature {

	private ReportsPage reportsPage;

	@Before // each scenario
	public void setup(){
		ChromeDriverSetup setup = new ChromeDriverSetup();
		reportsPage = new ReportsPage(setup.getDriver());
	}
	
	@Given("^I am on the Reports page$")
	public void iAmOnTheReportsPage()  {
		reportsPage.gotoReportsPage();
		reportsPage.verifyReportsPage();

	}

	@Given("^I have selected the year (\\d+) tab$")
	public void iHaveSelectedTheYearTab(){
 		reportsPage.clickReportYear("2017");
	}

	@Given("^I have selected \"([^\"]*)\" as Trainer$")
	public void iHaveSelectedAsTrainer(String trainer) {
		reportsPage.clickBatchDropdown();
		reportsPage.chooseBatch(trainer);
	}

	@Given("^I have selected all the weeks$")
	public void iHaveSelectedAllTheWeeks(){
		reportsPage.clickWeekDropdown();
		reportsPage.chooseWeekReport("week All");
	}

	@Given("^I have selected \"([^\"]*)\" as Trainees$")
	public void iHaveSelectedAsTrainees(String trainee) throws InterruptedException {
		reportsPage.clickTraineeDropdown();
		reportsPage.chooseTraineeReport(trainee);
	}

	@When("^I click the download button$")
	public void iClickTheDownloadButton(){
 		 reportsPage.clickChartDropdownPdf();
		 
	}

	@Then("^a PDF file is downloaded$")
	public void aPDFFileIsDownloaded() throws InterruptedException{
		reportsPage.clickChartFeedbackDownloadPdf();
 	}

}
