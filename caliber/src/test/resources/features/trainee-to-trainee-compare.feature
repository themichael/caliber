# feature
@trainee-to-trainee-compare
Feature: View trainee strengths compared to other Trainees
  As a user
  I can see how strong a trainee is in each technology
  and compare that to other trainees

  #background
  Background: Logged in
    Given that I am logged in as a User
    And on the Reports page 

  Scenario: Compare Trainees to other Trainees
    And I have clicked the Person Icon in the Technical Skills
    And I selected a trainee
    And I clicked the Person Icon in the Technical Skills again
    And I have selected a different Trainee
    When I click the close button
    Then I can see both trainees performances
