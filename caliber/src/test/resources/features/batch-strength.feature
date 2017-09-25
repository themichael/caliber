
# feature
@batch-strength
Feature: View Batch strength in each technology
	As a user 
	I can report how strong the batch 
	is in each technology

#background
Background: Logged in
	Given that I am logged in as a user
	And on the Reports page
	And I have selected the year
	
	
Scenario: 
	When I have selected the Batch
	Then I am able to report the Batch performance
