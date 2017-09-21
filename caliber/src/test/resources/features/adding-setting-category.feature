# Feature
Feature: Adding a category
	As a VP
	I can add a category to the curriculum
	So I can update the database

# Background
Background: Logged in on the Settings Category Page
	Given I am on the Settings Category page
	When I click on the Create button

# positive
Scenario: Adding a new category
And I input "Android" as a category
And I click on the Submit button
Then I should get a new category on the page

# negative
Scenario: x-ing out of the modal
And I click on the X button
Then I should be back on the Settings Category page

# negative
Scenario: Using the close button to close the modal
And I click on the Cancel button
Then I am back on the Settings Category page
