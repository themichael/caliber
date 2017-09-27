# feature
@delete-trainee-from-batch

Feature: Delete a trainee from a Batch
	As a user 
	I can delete trainees
	from a batch
	
Scenario: Deleting a trainee from a batch
	Given I am inside the Manage Batch Page
	And I have clicked the person icon 
	And I have clicked the trainee 1 delete icon
	When I have clicked the delete button
	Then the trainee "Liu, Daniel" has been removed the trainee 
	