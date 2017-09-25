# feature
@delete-trainee-from-batch

Feature: Delete a trainee from a Batch
	As a user 
	I can delete trainees
	from a batch
	
Scenario: Deleting a trainee from a batch
	Given I am on the Manage Batch Page
	And I have clicked the person icon 
	And I have clicked the delete icon
	When I have clicked the delete button
	Then the trainee has been removed the trainee and I am on the add Trainee modal
	