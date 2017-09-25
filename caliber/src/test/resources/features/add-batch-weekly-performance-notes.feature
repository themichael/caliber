@add-batch-weekly-performance-notes
Feature: Take Notes on Weekly Batch Performance
  As a trainer I can write notes about the batch performance each week

#background 
Background: Logged in
	Given that I am logged in as a trainer
	And on the Assess Batch Page
	And I have chosen the Batch
	And I have chosen the Week
	
  Scenario Outline: Writing Notes about Batch Weekly Performace
    And I have entered <response> as the Overall Feedback
    When I press the Save button 
    Then the Feedback is recorded
