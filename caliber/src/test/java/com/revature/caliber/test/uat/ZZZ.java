package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Extendible base class/ static method call for implementing a wait such that the page is allowed to load JS
 * @author Ryan
 *
 */
public class ZZZ {
	
	public static void waitForPageLoad(){
		WebDriverWait wait = new WebDriverWait(ChromeDriverSetup.driver, 30);
		if(!"complete".equals(((JavascriptExecutor)ChromeDriverSetup.driver).executeScript("return document.readyState"))){
			wait.pollingEvery(1000, TimeUnit.MILLISECONDS);
		}
	}

}
