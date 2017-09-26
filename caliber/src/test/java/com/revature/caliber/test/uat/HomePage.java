package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver)driver;
	}
	
	/**
	 * Asserts that the driver is currently on the home page 
	 * using assertEquals and driver.getCurrentUrl()
	 */
	public void assertHomePage(){
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/caliber#/vp/home");
	}
	
	/**
	 * Takes in a String and can send you to a page based on
	 * the string you send. For example, if you would like to
	 * visit the manage page you would send a string 'vp/manage'.
	 * @param page
	 * @throws InterruptedException 
	 */
	public void goToPage(String page) throws InterruptedException{
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	/**
	 * Takes in a string state and chooses from the dropdown menu for the bar graph
	 * based on the string given. If the string given does not match
	 * an option from the dropdown menu, this function will not work.
	 * 
	 * @param state
	 * @throws InterruptedException
	 */
	public void selectBarChartStateDropdown(String state) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("selectedBarStateDropdown")));
		Thread.sleep(125);
		dropdown.selectByVisibleText(state);
	}
	
	/**
	 * Returns a boolean as to whether or not the Select City dropdown
	 * menu for the bar graph is currently displayed on the page. It will not be displayed if 
	 * the state has not already been selected.
	 * @return
	 */
	public boolean isSelectBarCityDisplayed(){
		return driver.findElement(By.id("selectBarCityDropdown")).isDisplayed();
	}
	
	/**
	 * Sends a string to the city dropdown menu for the bar graph
	 * and selects an item from that dropdown menu if an options matches
	 * the string that was sent. If the string doesn't match any option,
	 * the function will not work properly.
	 * @param city
	 * @throws InterruptedException
	 */
	public void selectBarChartCityDropdown(String city) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("selectBarCityDropdown")));
		Thread.sleep(125);
		dropdown.selectByVisibleText(city);
	}
	
	
	/**
	 * Sends a string to the line graph select a state dropdown
	 * menu and matches to the string to an option if one exists.
	 * If a match doesn't exist, the function will not work properly.
	 * @param state
	 * @throws InterruptedException
	 */
	public void selectLineStateDropdown(String state) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("selectLineState")));
		Thread.sleep(125);
		dropdown.selectByVisibleText(state);
	}
	
	/**
	 * Returns a boolean as to whether or not the select city dropdown
	 * menu for the line graph is displayed or not. In order for this menu
	 * to be displayed, the state for the line graph must already be chosen.
	 * @return
	 */
	public boolean isSelectLineCityDisplayed(){
		return driver.findElement(By.id("selectLineCity")).isDisplayed();
	}
	
	/**
	 * Sends a string to the select city dropdown menu for the line graph on
	 * the home page. If the string sent matches one of the options from the 
	 * menu, that option will be chosen. If the string has no matches, then
	 * the function will not work properly.
	 * @param city
	 * @throws InterruptedException
	 */
	public void selectLineCityDropdown(String city) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("selectLineCity")));
		Thread.sleep(125);
		dropdown.selectByVisibleText(city);
	}
	
}
