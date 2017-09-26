
@add-batch
Feature: Add Batch
	As a QA,trainer, or VP 
	I can add a new batch

#positive testing
Scenario: Create new batch
Given I am inside the Manage Batch page
	And I have clicked Create Batch
	And I have entered "1611 Jul11 Java" as the Training name
	And I have selected "Revature" as the Training Type
	And I have selected "J2EE" as the Skill type
	And I have selected 1 as the Location
	And I have selected "Patrick Walsh" as the Trainer
	And I have selected "August Duet" as the Co-Trainer
	And I have selected "2017-02-02" as the Start Date
	And I have selected "2017-06-02" as the End Date
	And I have entered "80" as the Good Grade
	And I have entered "60" as the Passing Grade
	When I click the Create Batch Save button
	Then the "1611 Jul11 Java" Batch appears

