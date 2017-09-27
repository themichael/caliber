package com.revature.caliber.test.uat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Project: caliber
 *
 * @author d4k1d23
 * @date 1/25/17
 */
public class SalesforceLoginPage {
	
    private WebDriver driver;
    
    public SalesforceLoginPage(WebDriver driver){
    	super();
        this.driver = (ChromeDriver)driver;
        driver.get("http://localhost:8080/");
    }

    /**
     * Sends String username credentials to Salesforce
     * username box.
     * @param username
     */
    public void setUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
        implicitwait();
    }

    /**
     * Sends String password credentials to Salesforce
     * password box.
     * @param password
     */
    public void setPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
        implicitwait();
    }

    /**
     * Clicks login button on Salesforce login page.
     */
    public void clickLogin(){
        driver.findElement(By.id("Login")).click();
        implicitwait();
    }

    /**
     * Implicitly waits for page if needed up to 5 seconds.
     */
    private void implicitwait(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}