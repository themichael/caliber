#feature
@update-batch
Feature: Update Batch
	As a user
	I can update a batch
	
# positive testing
Scenario: Update existing batch
Given I am on the Manage Batch Page
And I have clicked the update Batch icon
And I have entered "1702" as the Training Name
And I have entered "Revature" as the Training Type
And I have entered "J2EE" as the skill Type
And I have entered "Reston, VA" as the Location
And I have chosen "Patrick Walsh" as the Trainer
And I have chosen "Dan Pickles" as the Co-Trainer
And I have entered "2017-02-13" as the Start Date
And I have entered "2017-04-21" as the End Date
And I have chosen 75 as the Good Grade
And I have chosen 80 as the Passing Grade
When I click the Update button 
Then I am back on the Manage Batch Page

