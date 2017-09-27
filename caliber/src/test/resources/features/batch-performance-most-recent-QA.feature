#feature
@batch-performance-most-recent-QA
Feature: View most recent quality audits

	As a VP
I can see each batch performance 
in their most recent quality audits
Background:
	Given I am logged in with VP credentials
Scenario:
	When I go to the Home Page
	Then I can view the batch performance from their most recent quality audits

