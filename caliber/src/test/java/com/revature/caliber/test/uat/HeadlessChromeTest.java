package com.revature.caliber.test.uat;

import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessChromeTest {
	
	@Test
	public void headlessChromeTest() {
		/* start the chromeless
		System.setProperty("webdriver.chrome.driver",
				"C:\\ChromeDriver\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("window-size=1400,800");
		options.addArguments("headless");
		WebDriver webdriver = new ChromeDriver(options);
		
		webdriver.get("http://localhost:8080");
		everything uptil this point is part of the chromeless
		*/
		//System.out.println(webdriver.findElement(By.id("j_id0:j_id6:username")));
		//System.out.println(webdriver.findElement(By.cssSelector("input[type='password']")));
		//System.out.println(webdriver.findElement(By.id("j_jd0:j_id6:password")));
		//System.out.println(webdriver.findElement(By.xpath("//*[@id=\"j_id0:j_id6\"]/fieldset/input")));
		
		/*
		File srcFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("~/Desktop/revatureSite.jpg"), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/* This is part of headless chrome browser
		webdriver.findElement(By.xpath("/html/body/div/ui-view/nav/div/ul[2]/li[6]/a")).click();
		webdriver.findElement(By.xpath("/html/body/div/ui-view/nav/div/ul[2]/li[6]/ul/li[1]/a")).click();
		webdriver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div/div[2]/div/div/table/tbody/tr[1]/td[5]/a")).click();
		webdriver.findElement(By.xpath("//*[@id=\"trainerName\"]")).click();
		webdriver.findElement(By.xpath("//*[@id=\"trainerName\"]")).sendKeys("a");
		webdriver.findElement(By.xpath("//*[@id=\"editTrainerModal\"]/div/div/div[2]/div[2]/input")).click();
		webdriver.quit();
		
		*/
	}

}
