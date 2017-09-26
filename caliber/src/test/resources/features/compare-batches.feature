#feature
@compare-batches
Feature: View and Compare Batches
	As a VP
	I can see how all the current batches 
	are doing and compare their performance
Background: 
	Given I am logged in as VP
Scenario:
	When I go to the Home Page
	Then I can see and compare batch performance to each other


	
