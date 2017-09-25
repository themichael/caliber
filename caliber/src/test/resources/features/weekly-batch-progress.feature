# feature
@weekly-batch-performance
Feature: View a batch's weekly progress
  As a user
  I can see weekly progress for the batch 
  to note when their performance improves or declines

  #background
  Background: Logged in
    Given that I am logged in as a User
    And on the Reports page

  Scenario: 
    And I select a Batch
    And I don't select a specific week
    And I don't select a specific Trainee
    When I go down to the Weekly Progress
    Then I can note the weekly progress for a batch
