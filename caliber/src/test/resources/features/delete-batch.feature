# feature
@delete-batch
Feature: Delete a Batch
	As a user
	I can delete a batch

# positive testing
Scenario: Deleting a batch
	Given I am on the Manage Batch Page
	And I click the delete batch icon
	When I click the Delete button in the confirm batch delete modal
	Then the batch has been deleted and I am on the Manage Batch Page
	
