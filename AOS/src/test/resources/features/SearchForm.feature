Feature: Flight Booking
   
   @oneway_search_form
  Scenario: Book a flight with specified details
  	Given I setup the test data with "oneway_search_form"
    Given I want to open the application
     #Then I change the currency type
     #Then I change the language
#	    Then I click the flexible data
#	    Then I click the umrah fare
	    Then I select the trip type
	  	Then I enter the source
	    Then I enter the destination
	    Then I enter the departure date
#	    Then I select the flight category
#	  	Then I add passengers
#	    Then I add advance search options 
#	    Then I enter the  preferred airlines 
#	    Then I click on the search button
    
     @roundtrip_search_form
  Scenario: Book a flight with specified details
  	Given I setup the test data with "roundtrip_search_form"
    Given I want to open the application
     #Then I change the currency type
     #Then I change the language
     Then I click the flexible data
     Then I click the umrah fare
    Then I select the trip type
  
    Then I enter the source
    Then I enter the destination
    Then I enter the departure date
    
    Then I enter the return date
    Then I select the flight category
  	Then I add passengers
    Then I add advance search options 
    Then I enter the  preferred airlines 
    Then I click on the search button
    
   
      @multi_city_search_form
    Scenario: Book a flight with specified details
  	Given I setup the test data with "multi_city_search_form"
    Given I want to open the application
     #Then I change the currency type
     #Then I change the language
     Then I select the trip type
     Then I enter the source
    Then I enter the destination
    Then I enter the departure date
    Then I enter the segment two
    Then I click the add another city
    #Then I enter the segment three
    #Then I enter the segment four
    #Then I enter the segment five
    #Then I enter the segment six
    #Then I select the flight category
    #Then I add passengers
    #Then I add advance search options 
    #Then I enter the  preferred airlines 
    #Then I click on the search button
    
    
    @oneway_flight_listing
   Scenario: Book a flight with specified details
  	Given I setup the test data with "oneway_flight_listing"
    Given I want to open the application
     #Then I change the currency type
     #Then I change the language
     Then I click the flexible data
     #Then I click the umrah fare
    Then I select the trip type
    Then I enter the source
    Then I enter the destination
    Then I enter the departure date
    
    #Then I select the flight category
  #	Then I add passengers
    #Then I add advance search options 
    #Then I enter the  preferred airlines 
    Then I click on the search button
    # Then I click on the edit search
     Then I need to validate advance search
     
     #Given I setup the edit data with "oneway_edit_flight_listing"
     
      #Then I change the currency type
     #Then I change the language
     #Then I click the flexible data
     #Then I click the umrah fare
    #Then I select the trip type
    #Then I enter the source
    #Then I enter the destination
    #Then I enter the departure date
    #Then I select the flight category
   #Then I add passengers
    #Then I add advance search options 
    #Then I enter the  preferred airlines 
    #Then I click on the search button
    #Then I need to validate advance search
    
    