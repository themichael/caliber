package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

public class ReportsPage {

	private PhantomJSDriver driver;
	private String URL;
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public ReportsPage(WebDriver driver){
		this.driver = (PhantomJSDriver)driver;
	}
	
	public void gotoReportsPage(){
		driver.get("http://localhost:8080/caliber/#/vp/manage");
		URL = driver.getCurrentUrl();
		System.out.println("CurrentURL = " + driver.getCurrentUrl());
		System.out.println(driver.getTitle());
	}
	
	public void goToHome(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	public void verifyReportsPage(){
		assertEquals("http://localhost:8080/caliber/#/vp/manage", 
				driver.getCurrentUrl());
	}
	
	public void yearClick(){
		WebElement element = driver.findElement(By.xpath("/html/body/div/ui-view/ui-view/div[1]/div/div/ul/li[1]/a"));
//		Select dropdown = new Select( element);
//      dropdown.selectByVisibleText(Integer.toString(2017));
	}
	
	public void clickDownloadBtn(){
		System.out.println("CurrentURL = " + driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		assertEquals("http://localhost:8080/caliber/#/vp/reports", driver.getCurrentUrl());
		WebElement element = driver.findElement(By.name("randomNamePlease"));
		Select dropdown = new Select(element);
		String text = dropdown.getFirstSelectedOption().getText();
        System.out.println("before: " + text);
		dropdown.selectByVisibleText("Charts");
	}
	
	public void quitDriver()
	{
		driver.quit();
	}
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	
	private File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
}
