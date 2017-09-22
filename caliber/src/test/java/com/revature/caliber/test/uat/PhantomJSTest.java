package com.revature.caliber.test.uat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJSTest {
	/*
	@Test
	public void workingPhantomJSTest() {
		DesiredCapabilities descap = new DesiredCapabilities();
		descap.setCapability("phantomjs.binary.path", System.getenv("PHANTOM_BIN"));
		//System.setProperty("phantomjs.binary.path",
		//		"C:\\PhantomJS\\bin\\phantomjs\\phantomjs-1.9.6-windows\\phantomjs.exe");
		descap.setJavascriptEnabled(true);
		WebDriver webdriver = new PhantomJSDriver(descap);
		webdriver.manage().window().setSize(new Dimension(1200, 1200));
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.get("http://www.facebook.com");
		System.out.println(webdriver.getCurrentUrl());
		
		webdriver.quit();
	}
	*/
}
