#feature
Feature: Adding Category

#Background
Background: Logged In
	Given I am currently on the settings category page
	And I click the create category button

#positive
Scenario: Correct category input
	And I input "Java" as a category
	When I click on the submit button
	Then A new Category "Java" should be on the page
	
#negative
Scenario: Close out before saving
	And I input "BostonStrong" as a category
	When I click the X button
	Then the category "BostonStrong" will not be created
	