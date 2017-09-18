# feature
@import-batch
Feature: Import Batch
  As a trainer or VP or QC,
  I can import a new batch,
  so I can analyze more trainees.

  #Positive Test
  Scenario: Importing a Batch
    Given I am on the Manage Batch page
    And I clicked Import Batch button
    And I selected a Batch
    And I selected a Location
    When I click the Import button
    Then the new batch appears on the table

    
    