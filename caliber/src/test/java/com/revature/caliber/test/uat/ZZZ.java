package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Extendible base class for implementing a wait such that the page is allowed to load JS
 * @author Ryan
 *
 */
public class ZZZ {
	
	public void waitForPageLoad(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if(!((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")){
			System.out.println("Page Not Loaded");
			wait.pollingEvery(1000, TimeUnit.MILLISECONDS);
		}
	}

}
