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
    And I have picked "Patrick Walsh - 2/14/17" as a batch
    When I have selected "Patrick Muldoon" as a trainee
    Then I can see each trainees strengths compared to the batch in Assessment Breakdown table
