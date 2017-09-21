# Feature
Feature: Editing Categories
  As a VP
  I can edit all the categories in our list
  So I can update the categories in the db

  # Background
  Background: Logged in
    Given I am on the settings category page
    When I click on the Category Name

  # positive
  Scenario: Edit the name of the category
    And I add "hello" to the current name field
    And I click the Submit button
    Then I should get the category with a different name

  # positive
  Scenario: Make the category inactive
    And I click on the Active checkbox
    And I click on the Submit Button
    Then I changed the category to an inactive state

  # negative
  Scenario: Exit the edit category modal by x-ing out
    And I click on the X-out button
    Then I exited the Edit Category modal without editing

	# negative
  Scenario: Exit the edit category modal by Close button
  	And I click the Modal Close button
  	Then I exited the edit Category modal without editing
