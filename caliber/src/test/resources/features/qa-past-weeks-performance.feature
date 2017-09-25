#feature
@qa-past-weeks-performance
Feature: QA previous weeks performance
	As a User
	I can see how each trainee and the batch
	performed on their weekly quality audits
	
Scenario:
	Given I have navigated to the quality audit page
	And I have selected the year to view
	And I have selected the batch to view
	When I click on a previous week tab
	Then I will be able to see the previous performance for that week
	
#negative test
#test against new batch on week 1 with no previous week
#Scenario:
#	Given I am on the quality audit page
#	And I have selected the year to view
#	And I have selected the batch to view
#	When I try to click on a previous week tab
#	Then I will not be able to see the previous weeks since there are none