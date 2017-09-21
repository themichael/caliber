#feature
@qa-trainee-performance
Feature: QA Trainee Performance
	As a quality auditor
	I can write notes about the trainee
	performance each week
	
Scenario:
	Given I am on the Quality Audit page
	And I have selected current year
	And I have selected a Batch
	And I have created the current week using the add week button
	And have entered "Trainee Notes" in Trainees note area
	When I click the save button at the bottom of the page
	Then the performance notes will be saved