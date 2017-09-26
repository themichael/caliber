# feature
@add-assessment
Feature: Download PDF Reports
  As a user
  I can download PDF reports 
  to distribute to clients, trainees, and stakeholders

  # positive testing
  Scenario: Download PDF Reports
    Given I am on the Reports page
    And I have selected the year 2017 tab
    And I have selected "Patrick Walsh - 4/25/16" as Trainer
    And I have selected all the weeks
    And I have selected "All" as Trainees
    When I click the download button
    Then a PDF file is downloaded

