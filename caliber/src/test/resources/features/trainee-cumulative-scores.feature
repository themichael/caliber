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
    And I have selected the year 2017 tab
    And I have not selected a Trainee
    And I have not selected a Week 
    When I have selected "Patrick Walsh - 2/14/17" as a batch
    Then I can see each trainee's scores and rankings from strongest to weakest
