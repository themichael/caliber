# feature
@trainee-strength
Feature: View trainee strengths compared to other Trainees
  As a user
  I can see how strong a trainee is in each technology
  and compare that to other trainees

  #background
  Background: Logged in
    Given that I am logged in as a User
    And on the Reports page 

  Scenario: Compare Trainees to other Trainees
    Given I am on the reports page
    And I have clicked the Person Icon in the Technical Skills
    And I click a trainee
    When I click the close button
    Then I can see the trainee performance compared to the batch
