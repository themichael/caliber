package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.revature.caliber.test.api.AbstractAPITest;


public class ChromeDriverSetup extends AbstractAPITest {
	
	protected static WebDriver driver;
	private static volatile boolean initialized = false;
	private static boolean isLoggedIn = false;
	private static ChromeDriverSetup instance;

	private ChromeDriverSetup(){
		if(!initialized){
			System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("--headless");
		    options.addArguments("--window-size=1600x900");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			initialized = true;
		}
		/*if(!isLoggedIn){
			loginPage.setUsername(username);
			loginPage.setPassword(password);
			loginPage.clickLogin();
			isLoggedIn = true;
		}*/
	}
	
	public static synchronized ChromeDriverSetup getInstance(){
		if(instance == null)
			instance = new ChromeDriverSetup();
		return instance;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driverSetup) {
		driver = driverSetup;
	}

	public static boolean isLoggedIn() {
		return isLoggedIn;
	}

	public static void setLoggedIn(boolean isLoggedIn) {
		ChromeDriverSetup.isLoggedIn = isLoggedIn;
	}

}
