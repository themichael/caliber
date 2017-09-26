# Feature
Feature: Creating a new location
  As a VP
  I can create a new location
  So that I can expand our company

  # Background
  Background: Logged in on Settings page
    Given I am on the Settings Locations page
    When I click on the Create Location button

  # Positive
  Scenario Outline: Add a location
    And I enter <company name> as the Company Name
    And I enter <address> as the Street Address
    And I enter <city> as the City
    And I enter <state> as the State
    And I enter <zipcode> as the Zipcode
    And I click the Save Button
    Then I will have added a location

    Examples: 
      | company name | address               | city          | state    | zipcode |
      | "Opine"      | "11318 Bellflower Rd" | "Kansas City" | "Kansas" | "11345" |
      | ""           | "11330 Bellfield Rd"  | "Akron"       | "Ohio"   | "44330" |

  # Negative
  Scenario: Cancel adding location by X
    And I enter "STEM" as the Company Name
    And I choose the X button
    Then I cancel making a new location

  # Negative
  Scenario: Cancel adding location by Close button
    And I enter "Placeholder" as the Company Name
    And I click on the Close button
    Then I cancel creating a new location
