#feature
@compare-batches
Feature: View and Compare Batches
	As a VP
	I can see how all the current batches 
	are doing and compare their performance
Background: 
	Given I am logged in as the VP
Scenario:
	When I go to Home Page
	Then I can see and compare batch performance to each other


	
