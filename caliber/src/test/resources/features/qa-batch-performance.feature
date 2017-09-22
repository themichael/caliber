#feature
@qa-batch-performance
Feature: QA Batch Performance
	As a quality auditor
	I can write notes about the batch
	performance each week

Scenario:
	Given I am on the Quality Audit page
	And I have selected the current year
	And I have selected a Batch
	And I am viewing the most recent week
	And I enter "Batch Notes" in the QC Feedback text area
	And I click on an overall batch feedback button
	When I click the save button
	Then the performance note is saved