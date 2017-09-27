@grade-trainee
Feature: Grade Trainee on a particular Assessment
  As a trainer I can grade my trainees on a particular assessment

  Scenario: Grading a Trainee Assessment
    Given I am on the Assess Batch Page
    And I have picked the Week 1 tab
    And I have submitted "50" as the grade for "Ahmed, Sadat"
    When I refresh the Assess Batch Page
    Then the grades "50" for "Ahmed, Sadat" are recorded
