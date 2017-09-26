package com.revature.caliber.test.uat;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ManageBatchPage{

	private WebDriver driver;
	
	public ManageBatchPage(WebDriver driver) {
	super();
	this.driver = (ChromeDriver)driver;
	this.driver.get("http://localhost:8080/caliber#/vp/manage");
	}

	/**
	 * Functions to go to different pages as well as verify that you are on the 
	 * correct page/the page you want to be on
	 */
	public void checkLoggedIn(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	/**
	 * Takes driver to the home page
	 */
	public void goToHome(){
		driver.get("http://localhost:8080/caliber#/vp/home");
	}
	
	/**
	 * Takes driver to the manage page
	 */
	public void gotoManagePage(){
		driver.navigate().to("http://localhost:8080/caliber/#/vp/manage");	
	}
	
	/**
	 * Takes a string and verifies you made it to the page
	 * you want to be on. For example for manage send "manage"
	 * @param page
	 */
	public void verifyPage(String page){
		assertEquals(("http://localhost:8080/caliber/#/vp/"+page), 
				driver.getCurrentUrl());
	}
	
	/**
	 * Clicks on import batch icon, opens up the modal, switches
	 * control to the modal and asserts that it made it to right the modal.
	 * This feature currently doesn't work as of 09-20-2017, so this is a placeholder
	 * @throws InterruptedException
	 */
	public void clickImportBatchIcon() throws InterruptedException{
		driver.findElement(By.id("importTab"));
		Thread.sleep(500);
		driver.switchTo().activeElement();
		String id = driver.findElement(By.id("batchModalLabel")).getAttribute("id").toString();
		assertEquals(id, "batchModalLabel");
	}
	
	/**
	 * Selects item from dropdown in the import batch
	 * modal menu based on the index given.
	 * This feature currently as of 09-20-2017 does not work properly,
	 * so this is a placeholder
	 * @param index
	 * @throws InterruptedException
	 */
	public void editBatchField(int index) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.cssSelector("#importBatchModal > div > div > div.modal-body > div:nth-child(1) > div > select")));
		Thread.sleep(500);
		dropdown.selectByIndex(index);
	}
	/**
	 * These functions directly interact with the Manage Batch Modal
	 * From here you can choose to edit a single trainee, add a trainee,
	 * open a profileURL or make a trainee inactive
	 * @throws IOException
	 * @throws InterruptedException
	 */
	/**
	 * Opens Manage Batch modal to do edits to an entire batch.
	 * This modal is the same for Update/Create Batch
	 */
	public void openManageBatchModal(int index) throws IOException, InterruptedException {
		driver.findElement(By.id("view/addTrainees"+index)).click();
		driver.findElement(By.id("traineeModalLabel"));
		Thread.sleep(3000);
		driver.switchTo().activeElement();
//		File srcFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(srcFile2, new File("~/Desktop/personAfter.jpg"), true);
		
	}
	
	/**
	 * Toggle between Active and Dropped Trainees within a batch . If the batch is 
	 * currently set to show active trainees this function will switch and show 
	 * dropped trainees. If the button is already showing dropped trainees, it will 
	 * switch and show active trainees
	 **/
	public void toggleBetweenActiveAndDroppedTrainees(){
		WebElement toggleActive = driver.findElement(By.id("active"));
		WebElement toggleDropped = driver.findElement(By.id("inActive"));
		WebElement toggle = toggleActive.isDisplayed() ? toggleActive : toggleDropped;
		toggle.click();
	}

	/**
	 * Opens up add trainee Modal by clicking on the + icon in the Manage
	 * Batch Modal. It is important to note that this modal is identical to
	 * the edit trainee modal, so when testing you must use the same methods 
	 * as the edit trainee methods defined later on. 
	 */
	public void openAddTraineeModal() throws InterruptedException{
		driver.findElement(By.id("addTrainee")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("traineeModalLabel"));
	}
	
	/**
	 * Opens the edit/update trainee modal by clicking on the pencil icon
	 * located in the manage batch modal by the employee name. These methods
	 * can be used with either the edit trainee modal or the add trainee
	 * modal, they are the same. Once the icon is clicked, the function
	 * waits till the modal is loaded, and switches to the modal for further
	 * editing 
	 * @throws InterruptedException
	 */
	public void openUpdateTraineeModal(int index) throws InterruptedException{
		driver.findElement(By.id("editTrainee"+index)).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("traineeModalLabel"));
	}
	
	/**
	 * Sends string to name field in the edit/update trainee
	 * modal. This field is required so the modal
	 * will throw a form required error if a blank name is given.
	 * @param name
	 */
	public void editName(String name){
		WebElement fullName = driver.findElement(By.id("traineeName"));
		fullName.clear();
		fullName.sendKeys(name);
	}
	
	/**
	 * Sends string to email field of the edit/update trainee modal. 
	 * This field is required and requires
	 * an invalid email input, so a form required error will occur if an
	 * invalid email is given
	 * @param email
	 */
	public void editEmailField(String email){
		WebElement emailField = driver.findElement(By.id("traineeEmail"));
		emailField.clear();
		emailField.sendKeys(email);
	}
	
	/**
	 * Sends string to skypeID field of the edit/update trainee modal. This field is not required
	 * @param skypeID
	 */
	public void editSkypeIDField(String skypeID){
		WebElement skypeIDField = driver.findElement(By.id("traineeSkype"));
		skypeIDField.clear();
		skypeIDField.sendKeys(skypeID);
	}
	
	/**
	 * Sends string to phone number field to the edit/udpate trainee modal. This field is not required
	 * @param phoneNumber
	 */
	public void editPhoneField(String phoneNumber){
		WebElement phoneField = driver.findElement(By.id("traineePhone"));
		phoneField.clear();
		phoneField.sendKeys(phoneNumber);
	}
	
	/**
	 * Sends string to college field to the edit/update trainee modal. This field is not required
	 * @param college
	 */
	public void editCollegeField(String college){
		WebElement collegeField = driver.findElement(By.id("traineeCollege"));
		collegeField.clear();
		collegeField.sendKeys(college);
	}
	
	/**
	 * Sends string to degree field in the edit/update trainee modal, this field is not required
	 * @param degree
	 */
	public void editDegreeField(String degree){
		WebElement degreeField = driver.findElement(By.id("traineeDegree"));
		degreeField.clear();
		degreeField.sendKeys(degree);
	}
	
	/**
	 * Sends string to major field in the edit/update trainee modal, this field is not required
	 * @param major
	 */
	public void editMajorField(String major){
		WebElement majorField = driver.findElement(By.id("traineeMajor"));
		majorField.clear();
		majorField.sendKeys(major);
	}
	
	/**
	 * Sends string to recruiter name field in the edit/update trainee modal, this field is not required
	 * @param recruiterName
	 */
	public void editRecruiterNameField(String recruiterName){
		WebElement recruiterNameField = driver.findElement(By.id("traineeRecruiterName"));
		recruiterNameField.clear();
		recruiterNameField.sendKeys(recruiterName);
	}
	
	/**
	 * Sends string to tech screener name field in the edit/update trainee modal, this field is not required
	 * @param techScreenerName
	 */
	public void editTechScreenerNameField(String techScreenerName){
		WebElement techScreenerNameField = driver.findElement(By.id("traineeTechScreenerName"));
		techScreenerNameField.clear();
		techScreenerNameField.sendKeys(techScreenerName);
	}
	
	/**
	 * Sends string to profileURL field in the edit/update trainee modal, this field is not required
	 * @param profileURL
	 */
	public void editProfileURLField(String profileURL){
		WebElement profileURLField = driver.findElement(By.id("traineeProfileUrl"));
		profileURLField.clear();
		profileURLField.sendKeys(profileURL);
	}
	
	/**
	 * Takes in a string status and matches it to one of the options
	 * from the edit/update modal training status box menu. If the string
	 * passed in doesn't exist in the menu, the function does not work 
	 * properly.
	 * @param status
	 * @throws InterruptedException
	 */
	public void editTrainingStatusField(String status) throws InterruptedException{
		Select trainingStatus = new Select(driver.findElement(By.id("traineeStatus")));
		Thread.sleep(125);
		trainingStatus.selectByVisibleText(status);
	}
	
	/**
	 * Sends string to Project completion field in the edit/update trainee modal, this field is not required.
	 * If an invalid percentage is given, the form will give an invalid form
	 * error
	 * @param percentage
	 */
	public void editProjectCompletionField(String percentage){
		WebElement percentageField = driver.findElement(By.id("traineeProjectCompletion"));
		percentageField.clear();
		percentageField.sendKeys(percentage);
	}
	
	/**
	 * clicks on trainee profile url and asserts that you made it to their
	 * profile. The driver will create a list of window handles which is 
	 * just a list of all tabs open in the browser which should be 2 at 
	 * this point. The driver switches to the newly opened tab
	 * their url
	 */
	public void clickAddTraineeProfileURLIcon(int index){
		driver.findElement(By.id("profileURL"+index)).click();
		List<String> browsertabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(browsertabs.get(1));
		assertEquals("https://app.revature.com/profile/Synac/254a7187dfc32f6f50710a56bd8112f6", driver.getCurrentUrl());
		driver.close();
		driver.switchTo().window(browsertabs.get(0));
	}
	
	/**
	 * Press delete icon on designated employee, opens the modal and
	 * switches to driver to look at that modal. Close that modal to return
	 * back to the manage batch modal
	 * @throws InterruptedException
	 */
	public void openDeleteTraineeModal(int index) throws InterruptedException{
		driver.findElement(By.id("removeBatch"+index)).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("deleteBatchButton"));
	}
	
	/**
	 * clicks delete on delete trainee modal and returns to manage
	 * batch modal
	 * @throws InterruptedException
	 */
	public void clickDeleteOnDeleteTraineeModal() throws InterruptedException{
		driver.findElement(By.id("deleteBatchButton")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * clicks cancel button on delete trainee modal and brings
	 * you back to the mangage batch modal
	 * @throws InterruptedException
	 */
	public void clickCancelOnDeleteTraineeModal() throws InterruptedException{
		driver.findElement(By.id("closeDeleteBatchButton")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * clicks the X button in the top right corner of the delete trainee
	 * modal and then brings you back to the manage batch modal
	 * @throws InterruptedException
	 */
	public void closeOutDeleteTraineeModal() throws InterruptedException{
		driver.findElement(By.id("XBtn")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks update button on the Add/Update trainee Modal 
	 * then brings you back to the manage batch modal 
	 * @throws InterruptedException 
	 */
	public void clickUpdateAddTraineeModal() throws InterruptedException{
		driver.findElement(By.id("saveButton")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks close button for both Add/Update trainee Modal then
	 * brings you back to the manage batch modal
	 * @throws InterruptedException 
	 */
	public void clickCloseAddTraineeModal() throws InterruptedException{
		driver.findElement(By.id("cancelButton"));
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Clicks X button in top right corner of Add/Update trainee Modal
	 * then brings you back to the manage batch modal
	 * @throws InterruptedException
	 */
	public void closeOutAddTraineeModal() throws InterruptedException{
		driver.findElement(By.id("XBtn"));
		Thread.sleep(500);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Verifies that the driver made it successfully to the
	 * trainee modal by locating the Modal Header and asserting
	 * that it is the correct header
	 */
	public void verifyEditTraineeModal(){
		driver.findElement(By.id("traineeModalLabel"));
	}
	
	/**
	 * Send an index and a String year to the dropdown menu on the page that
	 * chooses which year to view batches from. The year will be chosen
	 * based on the index given where 1 is the most recent year, 2 is the year
	 * prior etc. The string you send should match year returned by the index.
	 * For example at 1 the current year is 2017, send the string "2017" to verify
	 * the page changed
	 * @param year
	 * @throws InterruptedException
	 */
	public void changeYear(int index, String year) throws InterruptedException {
		driver.findElement(By.id("yearDropdown")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id(year)).click();
//		String yearActual = driver.findElement(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li.dropdown > a > label")).getAttribute("id").toString();
//		assertEquals(yearActual, year);
	}
	
	/**
	 * Clicks on the create batch + icon. It is important to note
	 * that this modal is the same one you use for the update Batch modal,
	 * so every function listed in the this group can be used to test updating
	 * the batch as well
	 * @throws InterruptedException
	 */
	public void openCreateBatchModal() throws InterruptedException{
		driver.findElement(By.id("createBatchTab")).click();
		Thread.sleep(1000);
		driver.switchTo().activeElement();
	}
	
	/**
	 * Sends a string to the Training Name field, this is required
	 * @param trainingName
	 * @throws InterruptedException 
	 */
	public void editTrainingNameField(String trainingName) throws InterruptedException{
		Thread.sleep(500);
		WebElement trainingNameField = driver.findElement(By.id("trainingName"));
		trainingNameField.click();
		trainingNameField.clear();										
		trainingNameField.sendKeys(trainingName);
	}
	
	/**
	 * Send a string to the training type field.  This is a drop
	 * down select menu, so the string sent has to match the exact
	 * string, otherwise it will default to other. This field is required
	 * @param trainingType
	 * @throws InterruptedException
	 */
	public void editTrainingTypeField(String trainingType) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("trainingType")));
		Thread.sleep(500);
		dropdown.selectByVisibleText(trainingType);
	}
	
	/**
	 * Sends a string to a dropdown menu of skill types. The string
	 * must match a skill type, otherwise it will default to other.
	 * This field is required
	 * @param skillType
	 * @throws InterruptedException
	 */
	public void editSkillTypeField(String skillType) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("skillType")));
		Thread.sleep(500);
		dropdown.selectByVisibleText(skillType);
	}
	
	/**
	 * Sends an int index to a dropdown menu. As of right now there are only two
	 * locations where 1 is NY and 2 is Reston, VA. This field is required
	 * @param index
	 * @throws InterruptedException
	 */
	public void editLocationField(int index) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("location")));
		Thread.sleep(500);
		dropdown.selectByIndex(index);
	}
	
	/**
	 * Sends a string to a dropdown menu of trainer. The string
	 * must match a trainer otherwise it will not work. This field
	 * is required
	 * @param skillType
	 * @throws InterruptedException
	 */
	public void editTrainerField(String trainer) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("trainer")));
		Thread.sleep(500);
		dropdown.selectByVisibleText(trainer);
	}
	
	/**
	 * This field is not required. It sends a string to the co-trainer
	 * dropdown menu, and must match a person from the list, otherwise
	 * it will not work
	 * @param coTrainer
	 * @throws InterruptedException
	 */
	public void editCoTrainerField(String coTrainer) throws InterruptedException{
		Select dropdown = new Select(driver.findElement(By.id("co-trainer")));
		Thread.sleep(500 );
		dropdown.selectByVisibleText(coTrainer);
	}
	
	/**
	 * Sends a string to a dropdown menu of start date. The string
	 * must match a date in this format YYYY-MM-DD. If not done in 
	 * this format you will get odd results. This field is required
	 * @param skillType
	 * @throws InterruptedException
	 */
	public void editStartDateField(String startDate){
		WebElement startDateField = driver.findElement(By.id("startDateInput"));
		startDateField.clear();
		startDateField.sendKeys(startDate);
	}
	
	/**
	 * Same as start date ^
	 * @param skillType
	 * @throws InterruptedException
	 */
	public void editEndDateField(String endDate){
		WebElement endDateField = driver.findElement(By.id("endDateInput"));
		endDateField.clear();
		endDateField.sendKeys(endDate);
	}
	
	/**
	 * Sends a string to the good grade field. If the string is not
	 * within 0-100, the form will throw a field error. This field
	 * is required
	 * @param goodGrade
	 */
	public void editGoodGradeField(String goodGrade){
		WebElement goodGradeField = driver.findElement(By.id("goodGrade"));
		goodGradeField.clear();
		goodGradeField.sendKeys(goodGrade);
	}
	
	/**
	 * same as good grade ^
	 * @param passingGrade
	 */
	public void editPassingGradeField(String passingGrade){
		WebElement passingGradeField = driver.findElement(By.id("borderlineGrade"));
		passingGradeField.clear();
		passingGradeField.sendKeys(passingGrade);
	}
	
	/**
	 * Clicks save on the Create/Update Batch Modal and returns
	 * the driver back to the manage Batch page
	 * @throws InterruptedException
	 */
	public void clickSaveOnCreateBatchModal() throws InterruptedException{
		driver.findElement(By.id("saveEditBatchButton"));
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("importTab"));
	}
	
	/**
	 * Clicks the close button on the Update/Create Batch modal
	 * and returns the test back to the manage Batch page
	 * @throws InterruptedException
	 */
	public void clickCloseOnCreateBatchModal() throws InterruptedException{
		driver.findElement(By.id("closeEditBatchButton"));
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("importTab"));
	}
	
	/**
	 * Clicks the X in the top right corner of the Update/Create
	 * Batch Modal and returns the test back to the Manage Batch Page
	 * @throws InterruptedException
	 */
	public void closeCreateBatchModal() throws InterruptedException{
		driver.findElement(By.id("XBtn"));
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("importTab"));
	}
	
	/**
	 * Opens the Update Batch Modal. This is the same as the 
	 * create modal, so you use the same functions to update that you
	 * do to create
	 * @throws InterruptedException
	 */
	public void clickUpdateBatchIcon(int index) throws InterruptedException{
		driver.findElement(By.id("updateBatch"+index)).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("batchModalLabel"));
	}
	
	/**
	 * Clicks on the X button for the batch in the corresponding row 
	 * and opens up the Delete Batch modal.
	 * @throws InterruptedException
	 */
	public void clickDelectBatchIcon(int index) throws InterruptedException{
		driver.findElement(By.id("removeBatch"+index)).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("batchModalLabel"));
	}
	
	/**
	 * Clicks the delete button with the delete batch modal,
	 * then returns control to the manage batch page
	 * @throws InterruptedException
	 */
	public void clickDeleteOnDeleteBatchModal() throws InterruptedException{
		driver.findElement(By.id("deleteBatchButton")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("importTab"));
	}
	
	/**
	 * Clicks on the cancel button in the delete batch modal, then
	 * returns control back to the manage Batch page
	 * @throws InterruptedException
	 */
	public void clickCancelOnDeleteBatchModal() throws InterruptedException{
		driver.findElement(By.id("closeDeleteBatchButton")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("importTab"));
	}
	
	/**
	 * Clicks the X in the top Right corner of the Delete Batch modal,
	 * then returns control to the Manage batch page
	 * @throws InterruptedException
	 */
	public void closeOutDeleteBatchModal() throws InterruptedException{
		driver.findElement(By.id("XBtn")).click();
		Thread.sleep(500);
		driver.switchTo().activeElement();
		driver.findElement(By.id("importTab"));
	}
	
	/**
	 * This functions checks if all required fields are filled
	 * out properly. Use this with negative testing, when testing
	 * an incorrect input
	 */
	public void verifyRequiredInputField(){
		driver.findElement(By.cssSelector("input:required"));
	}
	
	/**
	 * Returns true if the batch was created. Takes in a string
	 * batchName and looks on the page to see if that batch exists
	 * on the page.
	 * @param batchName
	 * @return
	 */
	public boolean checkIfBatchExitst(String batchName){
		boolean exists;
		try{
			driver.findElement(By.id(batchName));
			exists = true;
		}catch(NoSuchElementException e){
			exists = false;
		}
		return exists;
	}
	
	/**
	 * Returns true if the trainee passed in exists on the page.
	 * The function takes a String traineeName in and checks to
	 * see if on the view trainee modal if the trainee is there.
	 * @param traineeName
	 * @return
	 */
	public boolean checkIfTraineeExists(String traineeName){
		boolean exists;
		try{
		driver.findElement(By.id(traineeName));
			exists = true;
		}catch(NoSuchElementException e){
			exists = false;
		}
		return exists;
	}
	
	/**
	 * This function checks to see if any fields have an invalid input.
	 * Use this with negative testing (for example giving a negative number)
	 */
	public void verifyInvalidInputField(){
		driver.findElement(By.cssSelector("input:invalid"));
	}
	
	/**
	 * Closes driver completely. Calls driver.quit()
	 */
	public void closeDriver(){
		driver.close();
	}
}
