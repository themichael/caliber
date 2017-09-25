# Feature
Feature: Creating a new location
	As a VP
	I can create a new location
	So that I can expand our company

# Background
Background: Logged in on Settings page
	Given I am on the Settings Locations page
	When I click on the Create Location button

# Positive
Scenario: Add a location
	And I enter "Opine" as the Company Name
	And I enter "11318 BellGround Rd" as the Street Address
	And I enter "Kansas City" as the City
	And I enter "Kansas" as the State
	And I enter "11345" as the Zipcode
	And I click the Save Button
	Then I will have added a location
	
# Negative
Scenario Outline: 
	
