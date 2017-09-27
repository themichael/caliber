# Feature
Feature: Editing Location
  As a VP
  I can edit a location from our list
  So I can remove any mistakes/make updates to our locations

  # Scenarios
  #Positive
  Scenario: Update location
    Given I am located on the settings location page
    And I click on the Pencil Edit button
    And I add "a" to the end of the company name
    And I add "a" to the end of the address
    And I add "a" to the end of the city name
    And I add "New Jersey" to the state field
    And I add "1" to the zipcode field
    When I click on the Update button
    Then I will have updated the location
