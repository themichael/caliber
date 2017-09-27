#feature
@qa-trainee-performance
Feature: QA Trainee Performance
	As a quality auditor
	I can write notes about the trainee
	performance each week
	
Scenario: Filling out the individual trainee assessment
	Given I am on the Quality Audit Page
	And I have selected the current year for year
	And I have selected the current Batch
	And I am on the most current week 
	And have entered "Trainee Notes" in Trainees note area
	And I click on the individual feedback button to the desried state
	When I refresh the page
	Then the performance notes will be saved