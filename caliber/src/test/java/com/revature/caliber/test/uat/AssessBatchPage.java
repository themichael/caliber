package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AssessBatchPage{

	private WebDriver driver;
	
	public AssessBatchPage(WebDriver driver) {
		super();
		this.driver = (ChromeDriver)driver;
		this.driver.get("http://localhost:8080/caliber#/vp/assess");
	}

	public void goToPage(){
		driver.get("http://localhost:8080/caliber#/vp/assess");
	}
	
	public void verifyAssessPage(){
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/caliber#/vp/assess");
	}
	
	public void clickCreateAssessment(){
		driver.findElement(By.id("createAssessmentIcon")).click();
	}
	public void clickWeekTab(){
		driver.findElement(By.id("weekBtn3")).click();
	}
	
	public void clickYear(){
		driver.findElement(By.id("yearDropdownId")).click();
		driver.findElement(By.id("yearDropdownId0")).click();
	}
	public void selectBatch(){
		driver.findElement(By.id("batchesDropdownId")).click();
		driver.findElement(By.id("batchesDropdownId0")).click();
	}
	public void enterGrades(String number){
		driver.findElement(By.id("Ahmed, Sadatgrade0")).sendKeys(number);
		driver.findElement(By.id("Ahmed, Sadatgrade1")).sendKeys(number);
		driver.findElement(By.id("Ahmed, Sadatgrade2")).sendKeys(number);	
	}
	
	public void clickNewWeek(){
		driver.findElement(By.id("addNewWeekIcon")).click();
	}
	public void newWeekConfirmButton(){
		driver.findElement(By.id("yesBtn")).click();
	}
	
	public void newWeekNoButton(){
		driver.findElement(By.id("noBtn")).click();
	}
	
	public void closeNewWeekButton(){
		driver.findElement(By.id("closeBtn"));
	}
	
	public void saveButton(){
		driver.findElement(By.id("saveBtn")).click();
	}
	
	public void batchNotes(String feedback){
		driver.findElement(By.id("trainerBatchNote")).sendKeys(feedback);
	}

	public void batchNotesCheck(String feedback){
		assertEquals(feedback, driver.findElement(By.id("trainerBatchNote")).getText());
	}
	
	public void selectAssessementCategory(String feedback){
		Select dropdown = new Select(driver.findElement(By.id("category")));
		dropdown.selectByVisibleText(feedback);
	}
	public void maxPoints(String feedback){
		driver.findElement(By.id("rawScore")).sendKeys(feedback);
	}
	public boolean assessmentCheck(String exam){
		return driver.findElement(By.id(exam+"Exam")).isDisplayed();
	}
	public void selectAssessmentType(String feedback){
		Select dropdown = new Select(driver.findElement(By.id("assessmentType")));
		dropdown.selectByVisibleText(feedback);
	}
	public void modalSaveButton(){
		driver.findElement(By.id("saveBtn")).click();
	}
	public void closeButton(){
		driver.findElement(By.id("closeBtn")).click();
	}
	public void xButton(){
		driver.findElement(By.id("xBtn")).click();
	}
	public void closeDriver(){
		driver.quit();
	}
}
