Feature: Flight Booking
  
  @oneway_search_form
  Scenario: Book a flight with specified details
  	Given I setup the test data with "oneway_search_form"
    Given I want to open the application
    Then I select the trip type
    Then I click on the search button
    Then I enter the source
    Then I enter the destination
    Then I enter the departure date
    Then I select the flight category
  	Then I add passengers
    Then I add advance search options 
    Then I enter the  preferred airlines 
    Then I click on the search button
   