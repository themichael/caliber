# feature
@create-location
Feature: Adding Location
	As a VP [create location]
	I can create a new location
	So I can create a new record for location
	
	# positive testing
	Scenario: Adding a location
		Given I am logged in as a VP [create location]
		And I click on the Settings tab [create location]
		And I click Location from the drop-down menu
		And I click the create button [create location]
		And I enter "Opine" as the Company Name
		And I enter "11318 BellGround Rd" as the Street Address
		And I enter "Kansas City" as the City
		And I enter "Kansas" as the State
		And I enter 11215 as the Zipcode
		When I click the Save button [create location]
		Then I will create another Current Locations
		
		