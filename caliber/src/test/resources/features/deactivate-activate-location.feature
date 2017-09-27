# Feature
Feature: Deactivate and Reactivate location
  As a VP
  I can disable and enable a location
  So that the company knows what facilities are in use
  in which location

  Background: I am on the Setting Location Page
    Given I am on the Setting Locations Page

  # Scenarios
  
  # Positive
  Scenario: Cancel activity by Cancel Button
  	And the button in row 0 in an X
    And I click on the X-button to start the deactivation process in row 0
    When I click on the Cancel button
    Then I will be back on the location setting page

  # Positive
  Scenario: Cancel activity by X-button
  	And the button in row 1 in a check mark
    And I click on the X-button to start reactivating the location in row 1
    When I click on the x-button to cancel
    Then I will be back on the location page

  # Positive
  Scenario: Deactive the location
    And I click on the X-button to begin the deactivation process in row 0
    When I click on the Deactivation button
    Then I will change the location status of 0 to inactive
    
	# Positive
Scenario: Reactivate the location
	And I click on the check-button to begin the reactivation process in row 0
	When I click on the Reactivate button
	Then I will change the location status of 0 to active
