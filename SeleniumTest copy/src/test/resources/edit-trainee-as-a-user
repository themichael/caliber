# feature
@edit-trainee-as-a-user
Feature: Edit Trainee as a User
	As a trainer, QA, or VP,
	I can edit trainees with a batch,
	such as their first and last name, college,
	email, major, tech screener name, phone number,
	degree, recruiter name, project completion, profile
	URL, and training status,
	so I can fix errors that may have been initially made
	when inputting their information into caliber.
	
	
#background
Background: Logged in
	Given that I am logged in as a User
	And on the Manage Batch page,
	And I have clicked on the person icon corresponding to a batch,
	And I have clicked on the pencil icon corresponding to a trainee
	
#positive testing
Scenario: Changing Trainee Information
	And I have edited one or more of the form boxes,
	When I click the update button
	Then the trainees information will be changed and saved into the db.
	
#negative testing
Scenario: Incorrect Email Address
	And I have entered an invalid email address,
	When I click on the update button
	Then I should get an error stating the email address I input is invalid.
	
Scenario: Invalid Project completion
	And I have entered an invalid value as a percentage,
	When I click the update button
	Then I should get an error stating the percentage is invalid.
	
Scenario: Blank Email Address
	And I currently have no information entered for the email address,
	When I press the update button
	Then I should get an error asking me to fill out this field.
	
Scenario: Blank Full Name
	And I have left their name field blank,
	When I go and click the update button
	Then I should get an error stating the field cannot be blank.

	