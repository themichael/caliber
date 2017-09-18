# feature
@add-week-to-batch
Feature: Add a new week to a Batch
  As a trainer or VP or QC
  I can start a new week
  for a batch

  Scenario: Adding a new week to a Batch
  Given I am on Assess Batch page
  And I have chose the year 2016 tab
  And I have chose "Patrick Walsh - 4/25/16" as Trainer
  When I have clicked the Add button
  Then A modal pops up to confirm the process
