package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditTrainees{
	
	private ManageBatchPage manageBatchPage;
	private boolean loggedIn;
	
	
	@Before
	public void setup(){
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
		driver.get("http://localhost:8080/caliber#/vp/home");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		System.out.println(driver.getCurrentUrl());
		manageBatchPage = new ManageBatchPage(driver);
		
	}
	
	@Given("^that I am logged in as a User$")
	public void that_I_am_logged_in_as_a_User() throws Throwable {
		manageBatchPage.goToHome();
	}

	@Given("^on the Manage Batch page,$")
	public void on_the_Manage_Batch_page() throws Throwable {
	    manageBatchPage.gotoManagePage();
	    manageBatchPage.verifyManagePage();
	}

	@Given("^I have clicked on the person icon corresponding to a batch,$")
	public void i_have_clicked_on_the_person_icon_corresponding_to_a_batch() throws Throwable {
	    //driver.findElement(By.xpath("//*[@id=/'manage']/div[2]/div/div/table/tbody/tr/td[11]/a/span")).click();
	}

	@Given("^I have clicked on the pencil icon corresponding to a trainee$")
	public void i_have_clicked_on_the_pencil_icon_corresponding_to_a_trainee() throws Throwable {
	    //driver.findElement(By.xpath("//*[@id='viewTraineeModal']/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[13]/a/span"));
	}

	@Given("^I have edited one or more of the form boxes,$")
	public void i_have_edited_one_or_more_of_the_form_boxes() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click the update button$")
	public void i_click_the_update_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the trainees information will be changed and saved into the db\\.$")
	public void the_trainees_information_will_be_changed_and_saved_into_the_db() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I have entered an invalid email address,$")
	public void i_have_entered_an_invalid_email_address() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click on the update button$")
	public void i_click_on_the_update_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should get an error stating the email address I input is invalid\\.$")
	public void i_should_get_an_error_stating_the_email_address_I_input_is_invalid() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I have entered an invalid value as a percentage,$")
	public void i_have_entered_an_invalid_value_as_a_percentage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should get an error stating the percentage is invalid\\.$")
	public void i_should_get_an_error_stating_the_percentage_is_invalid() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I currently have no information entered for the email address,$")
	public void i_currently_have_no_information_entered_for_the_email_address() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I press the update button$")
	public void i_press_the_update_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should get an error asking me to fill out this field\\.$")
	public void i_should_get_an_error_asking_me_to_fill_out_this_field() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I have left their name field blank,$")
	public void i_have_left_their_name_field_blank() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I go and click the update button$")
	public void i_go_and_click_the_update_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should get an error stating the field cannot be blank\\.$")
	public void i_should_get_an_error_stating_the_field_cannot_be_blank() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
