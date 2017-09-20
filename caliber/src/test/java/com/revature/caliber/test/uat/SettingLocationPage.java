package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

public class SettingLocationPage {

	private PhantomJSDriver driver;
	private String URL;
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public SettingLocationPage(WebDriver driver){
		this.driver = (PhantomJSDriver)driver;
	}
	
	public void gotoSettingLocationPage(){
		driver.get("http://localhost:8080/caliber/#/vp/locations");
		URL = driver.getCurrentUrl();
		System.out.println("CurrentURL = " + driver.getCurrentUrl());
		System.out.println(driver.getTitle());
	}
	
	public void goToHome(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	public void verifyLocationPage(){
		assertEquals("http://localhost:8080/caliber/#/vp/locations", 
				driver.getCurrentUrl());
	}
	
	public void clickCreateLocationBtn(){
		driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div/div[1]/div/div/ul/li/a"));
	}
	
	public void quitDriver()
	{
		driver.quit();
	}
}
