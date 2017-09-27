package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
		driver.switchTo().activeElement();
		driver.findElement(By.id("yearDropdownId2")).click();
	}
	
	public void selectBatch(){
		driver.findElement(By.id("batchesDropdownId")).click();
		driver.findElement(By.id("batchesDropdownId0")).click();
	}
	
	public void enterGrades(String name, String number){
		driver.findElement(By.id(name+"grade0")).sendKeys(number);
		driver.findElement(By.id(name+"grade1")).sendKeys(number);
	}
	
	public void checkIfGradesWereInput(String traineeName, String grade){
		assertEquals(driver.findElement(By.id(traineeName+"grade0")).getAttribute("value"), grade);
	}
	
	public void clickNewWeek() throws InterruptedException{
		driver.findElement(By.id("addNewWeekIcon")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	public void newWeekConfirmButton() throws InterruptedException{
		driver.findElement(By.id("yesBtn")).click();
		driver.switchTo().activeElement();
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
	
	public void selectTrainer(String trainerName) throws InterruptedException{
		WebElement dropdown = driver.findElement(By.id("batchesDropdownId"));
		dropdown.click();
		driver.switchTo().activeElement();
		driver.findElement(By.id("batchesDropdownId2")).click();
	}
	
	public void batchNotes(String feedback){
		driver.findElement(By.id("trainerBatchNote")).sendKeys(feedback);
	}

	public void batchNotesCheck(String feedback){
		assertEquals(feedback, driver.findElement(By.id("trainerBatchNote")).getAttribute("value"));
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
	
	public int numberOfWeeks(){
		int i = 1;
		boolean exists = true;
		
		while(exists){
			try{
				driver.findElement(By.id("weekBtn"+i));
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				exists = true;
				i++;
			}catch(NoSuchElementException e){
				exists = false;
			}
		}
		return i;
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
		driver.close();
	}
	
	
	public boolean doesWeekTabExist(int weekNumber){
		boolean exists;
		try{
			driver.findElement(By.id("weekBtn"+(weekNumber)));
			exists = true;
		}catch(NoSuchElementException e){
			exists = false;
		}
		return exists;
	}
}
