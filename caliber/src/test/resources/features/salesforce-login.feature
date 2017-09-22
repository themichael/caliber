# feature
@salesorce-login
Feature: Login to Salesforce
	As a user
	I can login with my
	Salesforce account
	
# positive testing 

Scenario: Salesforce Valid Login
	Given I am on the Salesforce Login Page
	And I have entered "username" as the username
	And I have entered "password" as the password
	When I click the Login Button
	Then I am on the Home Page

# negative testing
Scenario: Salesorce Invalid Login
	Given I am on the Salesforce Login Page
	And I have entered "jdlkjdlj" as the username
	And I have entered "djklsjljd" as the password
	When I click the Login Button
	Then I am on on the Login Page 
	