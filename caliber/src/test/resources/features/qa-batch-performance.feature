#feature
@qa-batch-performance
Feature: QA Batch Performance
	As a quality auditor
	I can write notes about the batch
	performance each week

Scenario:
	Given I am on the Quality Audit page
	And I have selected 2017 as the year
	And I have selected "Patrick Walsh - 2/13/17" as Trainer
	And I am viewing the most recent week
	And I enter "Batch Notes" in the QC Feedback text area
	When I click the save button
	Then the performance note is saved