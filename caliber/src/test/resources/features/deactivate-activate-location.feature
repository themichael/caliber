# Feature
Feature: Deactivate and Reactivate location
  As a VP
  I can disable and enable a location
  So that the company knows what facilities are in use
  in which location

  Background: I am on the Setting Location Page
    Given I am on the Setting Locations Page
    And I check if the button is a check or an x

  # Scenarios
  
  # Positive
  Scenario: Cancel activity by Cancel Button
    When I click on the X-button to start the deactivation process
    And I click on the Cancel button
    Then I will be back on the location setting page

  # Positive
  Scenario: Cancel activity by X-button
    When I click on the X-button to start deactivating the location
    And I click on the x-button to cancel
    Then I will be back on the location page

  # Positive
  Scenario: Deactive the location
    When I click on the X-button to begin the deactivation process
    And I click on the Deactivation button
    Then I will change the location status to inactive
    
	# Positive
	Scenario: Reactivate the location
		When I click on the check-button to begin the reactivation process
		And I click on the Reactivate button
		Then I will change the location status to active
