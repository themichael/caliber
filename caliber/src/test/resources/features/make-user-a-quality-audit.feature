
@make-user-a-quality-audit
Feature: Change a user to be a Quality Audit
	As a VP I can assign a user as a quality auditor
	for a batch, thus allowing a trainer to perform quality audits

Background:
	Given I am logged in as VP
	And I am on the Trainer Settings Pages
Scenario: Edit a user to be a Quality Audit
	And I click the edit Trainer icon
	And change the tier of the trainer to VP
	When I select the update button
	Then the user has been changed to a QA
	

