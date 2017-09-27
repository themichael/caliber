# feature
@trainee-strength
Feature: View trainee strength 
  As a user
  I can see how strong a trainee is in each technology
  and compare that to the batch

  Scenario: View Trainee strength
  Given I am on the reports page
  And I have clicked the person icon in the Technical Skills
  And I click "Ali, Fareed" as a trainee
  When I tap the close button
  Then I can see the trainee performance compared to the batch