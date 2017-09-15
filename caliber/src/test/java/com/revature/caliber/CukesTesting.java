/*package com.revature.caliber;

import org.apache.log4j.Logger;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CukesTesting {

	private static final Logger log = Logger.getLogger(CukesE2E.class);
	
	@Before
	@Sql(scripts = "/setup.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void setup(){
		log.info("Preparing test data for Cucumber Scenario");
	}
	
	@After
	@Sql(scripts = "/teardown.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void teardown(){
		log.info("Tearing down test data for next Cucumber Scenario");
	}
	
	@Given("^I am on the Assess Batch page$")
	public void i_am_on_the_Assess_Batch_page() throws Throwable {

	}

	@Given("^I have selected a batch$")
	public void i_have_selected_a_batch() throws Throwable {

	}

	@Given("^I have clicked the Week (\\d+) tab$")
	public void i_have_clicked_the_Week_tab(int arg1) throws Throwable {

	}

	@Given("^I have clicked Create Assessment button$")
	public void i_have_clicked_Create_Assessment_button() throws Throwable {

	}

	@Given("^I have selected \"([^\"]*)\" as the Category$")
	public void i_have_selected_as_the_Category(String arg1) throws Throwable {

	}

	@Given("^I have entered (\\d+) as the Max Points$")
	public void i_have_entered_as_the_Max_Points(int arg1) throws Throwable {

	}

	@Given("^I have selected \"([^\"]*)\" as the Assessment Type$")
	public void i_have_selected_as_the_Assessment_Type(String arg1) throws Throwable {

	}

	@When("^I click the Save button$")
	public void i_click_the_Save_button() throws Throwable {

	}

	@Then("^the Java Exam appears on the screen$")
	public void the_Java_Exam_appears_on_the_screen() throws Throwable {

	}
}
*/