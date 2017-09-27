# Feature
Feature: Editing Categories
  As a VP
  I can edit all the categories in our list
  So I can update the categories in the db

  # Background
  Background: Logged in
    Given I am on the settings category page
    
  # positive
  Scenario: Edit the name of the category
  	And I click on the "Java" Category Name
    And I add "hello" to the current name field
    When I click the Submit button
    Then the new category should be "hello"

  # positive
  Scenario: Edit the name of the category
  	And I click on the "hello" Category Name
    And I add "Java" to the current name field
    When I click the Submit button
    Then the new category should be "Java"

  # positive
  Scenario: Make the category inactive
  	And I click on the "Hello" Category
    And I click on the Active checkbox
    When I click on the Submit Button
    Then the category "Hello" should be inactive

  # negative
  Scenario: Exit the edit category modal by x-ing out
  	And I click on the "hello" Category box
    When I click on the X-out button
    Then I exited the Edit Category modal without editing

	# negative
  Scenario: Exit the edit category modal by Close button
  	And I click on the "hello" Category link
  	When I click the Modal Close button
  	Then I exited the edit Category modal without editing
