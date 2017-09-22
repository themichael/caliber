# feature
@add-trainee-to-batch
Feature: Add a trainee to a Batch
  As a user
  I can add new trainees
  to a batch
  
  #positive testing
Scenario: Adding new Trainees to a Batch
	Given I am on the Manage Batch Page
	And I have clicked the Person Icon for a specific Batch
	And I have clicked the add Trainee tab
	And I have entered "John Adams" as the full name
	And I have entered "ja@gmail.com" as the email
	And I have entered "jackfrost" as the Skype Id
	And I have entered "835-756-9874" as the Phone Number
	And I have entered "Arizona State" as the College
	And I have entered "Bachelors of Science" as the Degree
	And I have entered " Computer Science" as the Major
	And I have entered "Michael Scott" as the Recriter Name
	And I have entered "Thomas Smith" as the Tech Screener Name
	And I have entered 50 as the Project Completion
	And I have entered "http://www.anyonesgame.com/revature" as the Profile URL
	And I have selected "Training" as the status
	When I click the Save Button in the add trainee modal
	Then the new trainee has been added and I am in the trainee batch modal
	
	#negative testing
	Scenario: Unsuccessfully adding new Trainee to the batch
	Given I am on the Manage Batch Page
	And I have clicked the Person Icon for a specific Batch
	And I have clicked the add Trainee tab
	And I have entered "John Adams" as the full name
	And I have entered "ja@gmail.com" as the email
	And I have entered "jackfrost" as the Skype Id
	And I have entered 4568748464 as the Phone Number
	And I have entered "Arizona State" as the College
	And I have entered "Bachelors of Science" as the Degree
	And I have entered " Computer Science" as the Major
	And I have entered "Michael Scott" as the Recriter Name
	And I have entered "Thomas Smith" as the Tech Screener Name
	And I have entered "jkdjd" as the Project Completion
	And I have entered "http://www.anyonesgame.com/revature" as the Profile URL
	And I have selected "Training" as the status
	When I click the Save Button in the add trainee modal
	Then the new trainee could not be added and I am in the add trainee modal
