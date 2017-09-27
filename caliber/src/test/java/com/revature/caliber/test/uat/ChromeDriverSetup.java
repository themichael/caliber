package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.revature.caliber.test.api.AbstractAPITest;


public class ChromeDriverSetup extends AbstractAPITest {
	
	private SalesforceLoginPage loginPage;
	protected static WebDriver driver;
	private static boolean initialized = false;
	private static boolean isLoggedIn = false;

	public ChromeDriverSetup(){
		if(!initialized){
		System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_EXE"));
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--headless");
	    options.addArguments("--window-size=1600x900");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		initialized = true;
		}
		if(!isLoggedIn){
<<<<<<< HEAD
//			loginPage.setUsername(System.getenv("CALIBER_API_USERNAME"));
//			loginPage.setPassword(System.getenv("CALIBER_API_PASSWORD"));
//			loginPage.clickLogin();
=======
			loginPage.setUsername(username);
			loginPage.setPassword(password);
			loginPage.clickLogin();
>>>>>>> c5fa196479a23f7aeaaf1f4ef737d7dfbdccf243
			isLoggedIn = true;
		}
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
