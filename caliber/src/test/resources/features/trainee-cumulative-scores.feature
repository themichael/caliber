# feature
@trainee-cumulative-scores
Feature: View Trainee's cumulative scores and rankings 
As a user
I can see each trainee's cumulative average score and rankings from strongest to weakest

  #background
  Background: Logged in
    Given that I am logged in as a User
    And on the Reports page

    Scenario:
    And I have selected a specific batch
    And I have not selected a Trainee
    And I have not selected a Week 
    When I view the cumulative scores table
    Then I can see each trainee's scores and rankings from strongest to weakest
