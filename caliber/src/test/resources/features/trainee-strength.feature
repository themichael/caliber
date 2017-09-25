# feature
@trainee-strength
Feature: View trainee strength 
  As a user
  I can see how strong a trainee is in each technology
  and compare that to the batch

  Scenario: View Trainee strength
  Given I am on the reports page
  And I have clicked the Person Icon in the Technical Skills
  And I click a trainee
  When I click the close button
  Then I can see the trainee performance compared to the batch