@add-batch-weekly-performance-notes
Feature: Take Notes on Weekly Batch Performance
  As a trainer I can write notes about the batch performance each week

  Scenario: Writing Notes about Batch Weekly Performace
    Given I am in the Assess Batch Page
    And I have chosen the Week tab
    And I have entered "Weekly Responses" as the Overall Feedback
    When I press the Save button 
    Then the Feedback is recorded
