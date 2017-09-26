# feature
@trainee-assessment-compare-to-batch
Feature: View Trainees assessment strengths 
As a user
I can see how strong a trainee is with each assessment type (verbal, exam, etc) and compare that to the batch

  #background
  Background: Logged in
    Given that I am logged in as a User
    And on the Reports page

    Scenario:
    And I have picked a batch
    And I have selected a Trainee
    And I have not picked a Week 
    When I view the Assessment Breakdown table
    Then I can see each trainees strengths compared to the batch
