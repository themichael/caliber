package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class ManageBatchPage{

//	protected driver
//	protected url
//	public void setup(){
//		driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
//		driver.get("http://localhost:8080/caliber/#/vp/manage");
//		URL = driver.getCurrentUrl();
//	}
	private WebDriver driver;
	
	
	public ManageBatchPage(WebDriver driver) {
	super();
	this.driver = (PhantomJSDriver)driver;
	}

	public void checkLoggedIn(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	public void goToHome(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	public void gotoManagePage(){
		driver.navigate().to("http://localhost:8080/caliber/#/vp/manage");
		
	}
	
	public void verifyManagePage(){
		assertEquals("http://localhost:8080/caliber/#/vp/manage", 
				driver.getCurrentUrl());
	}
	
	public void clickOnPersonIcon() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("~/Desktop/personBefore.jpg"), true);
		WebElement editBatchModal = driver.findElement(By.cssSelector("#manage > div:nth-child(2) > div > div > table > tbody > tr > td:nth-child(11) > a"));
		editBatchModal.click();
		driver.findElement(By.cssSelector("#viewTraineeModal > div > div > div.modal-body.only-top-padding > div.container.modal-widest > h3"));
		//driver.switchTo().frame(editBatchModal);
		Thread.sleep(3000);
		File srcFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile2, new File("~/Desktop/personAfter.jpg"), true);
		
	}
	
	public void clickOnEditIcon() throws IOException, InterruptedException{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("~/Desktop/pencilBefore.jpg"), true);
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("#viewTraineeModal > div > div > div.modal-body.only-top-padding > div.col-md-12.col-lg-12 > div > table > tbody > tr:nth-child(1) > td:nth-child(13) > a")).click();
		Thread.sleep(3000);
		File srcFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile2, new File("~/Desktop/pencilAfter.jpg"), true);
	}
	
	public void editName(String name) throws IOException{
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("#viewTraineeModal > div > div > div.modal-body.only-top-padding > div.container.modal-widest"));
		driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")).clear();
		driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")).sendKeys(name);
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("~/Desktop/name.jpg"), true);
	}
	
	public void editEmailField(String email){
		driver.switchTo().activeElement();
		WebElement emailField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input"));
		emailField.clear();
		emailField.sendKeys(email);
	}
	
	public void editSkypeIDField(String skypeID){
		driver.switchTo().activeElement();
		WebElement skypeIDField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > input"));
		skypeIDField.clear();
		skypeIDField.sendKeys(skypeID);
	}
	
	public void editPhoneField(String phoneNumber){
		driver.switchTo().activeElement();
		WebElement phoneField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > input"));
		phoneField.clear();
		phoneField.sendKeys(phoneNumber);
	}
	
	public void editCollegeField(String college){
		driver.switchTo().activeElement();
		WebElement collegeField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > input"));
		collegeField.clear();
		collegeField.sendKeys(college);
	}
	
	public void editDegreeField(String degree){
		driver.switchTo().activeElement();
		WebElement degreeField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(3) > div:nth-child(2) > input"));
		degreeField.clear();
		degreeField.sendKeys(degree);
	}
	
	public void editMajorField(String major){
		driver.switchTo().activeElement();
		WebElement majorField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > input"));
		majorField.clear();
		majorField.sendKeys(major);
	}
	
	public void editRecruiterNameField(String recruiterName){
		driver.switchTo().activeElement();
		WebElement recruiterNameField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(4) > div:nth-child(2) > input"));
		recruiterNameField.clear();
		recruiterNameField.sendKeys(recruiterName);
	}
	
	public void editTechScreenerNameField(String techScreenerName){
		driver.switchTo().activeElement();
		WebElement techScreenerNameField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(5) > div:nth-child(1) > input"));
		techScreenerNameField.clear();
		techScreenerNameField.sendKeys(techScreenerName);
	}
	
	public void editProfileURLField(String profileURL){
		driver.switchTo().activeElement();
		WebElement profileURLField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(5) > div:nth-child(2) > input"));
		profileURLField.clear();
		profileURLField.sendKeys(profileURL);
	}
	
	public void editTrainingStatusField(){
		//still needs to be implemented
	}
	
	public void editProjectCompletionField(String percentage){
		driver.switchTo().activeElement();
		WebElement percentageField = driver.findElement(By.cssSelector("#addTraineeModal > div > div > div.modal-body > div:nth-child(2) > div:nth-child(5) > div:nth-child(2) > input"));
		percentageField.clear();
		percentageField.sendKeys(percentage);
	}
	
	public void clickUpdate(){
		driver.findElement(By.xpath("//*[@id='addTraineeModal']/div/div/div[2]/div[2]/input[2]")).click();
	}
	
	public void verifyEditTraineeModal(){
		driver.findElement(By.cssSelector("#viewTraineeModal > div > div > div.modal-body.only-top-padding > div.container.modal-widest > h3"));
	}
	
	public void verifyRequiredInputField(){
		driver.findElement(By.cssSelector("input:required"));
	}
	
	public void verifyInvalidInputField(){
		driver.findElement(By.cssSelector("input:invalid"));
	}
	
	public void destroy(){
		driver.quit();
	}
}
