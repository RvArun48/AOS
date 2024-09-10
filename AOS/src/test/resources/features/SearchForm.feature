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
    Then I click the add another city
    Then I click the add another city
    Then I click the add another city
    Then I click the add another city
   
    
    Then I enter the segment two
    
    Then I enter the segment three
    Then I enter the segment four
    Then I enter the segment five
    Then I enter the segment six
    #Then I select the flight category
    #Then I add passengers
    #Then I add advance search options 
    #Then I enter the  preferred airlines 
    Then I click on the search button
    
    
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
    Then I select the flight category
    Then I add passengers
    #Then I add advance search options 
    #Then I enter the  preferred airlines 
    Then I click on the search button
     #Then I click on the edit search
     #Then I need to validate advance search
     #Then I need to validate flexi calander
      #Then I need to validate installments
      #Then I need to validate time
      #Then I need to validate price
      #Then I need to validate stop
      #Then I need to validate duration
       #Then I need to validate airline
      #Then I need to validate refundable
      #Then I click on the more filter 
      #Then I need to validate departure airport
       #Then I need to validate arrival airport
        #Then I need to validate departure stopover
        #Then I need to validate aircraft types
         #Then I click on the apply button 
       #Then I need to validate fare option
       #Then I need to validate sortby filter
       #Then I need to validate more fligt option
        Then I click on the flight details   
        #Then  I need to validate flight details 
   
        #Then  I need to validate fare option card 
         Then I click on the baggage
             Then  I need to validate baggage
         Then I click on the flight ltinerary
          Then  I need to validate flight ltinerary
           
            #Then I click on the fare breakup
             #Then  I need to validate fare breakup
             Then I click on the booknow
             Then I need to add the traveller details
              Then I need to add the traveller common details
              #Then I enter the promo code
             #Then  I need to validate flight Summary 
             Then I click on payment continue 
             Then I need to validate flight Summary payment
              Then I need to validate payment gateway
              Then I enter the card details
               Then I need to validate confirmation page
              
              
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
    
     @roundtrip_flight_listing
   Scenario: Book a flight with specified details
  	Given I setup the test data with "roundtrip_flight_listing"
    Given I want to open the application
     Then I change the currency type
     #Then I change the language
     Then I click the flexible data
     #Then I click the umrah fare
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
     #Then I click on the edit search
     #Then I need to validate flight listing
    
     #Then I need to validate flexi calander round trip
      #Then I need to validate installments
      #Then I need to validate time
      #Then I need to validate price
      #Then I need to validate stop
      #Then I need to validate duration
       #Then I need to validate airline
      #Then I need to validate refundable
      #Then I click on the more filter 
      #Then I need to validate departure airport
       #Then I need to validate arrival airport
        #Then I need to validate departure stopover
        #Then I need to validate aircraft types
         #Then I click on the apply button 
       #Then I need to validate fare option
       #Then I need to validate sortby filter 
       #Then I need to validate more fligt option round trip
        Then I click on the flight details 
        #Then  I need to validate flight details 
          Then I click on the baggage
             Then  I need to validate baggage
         Then I click on the flight ltinerary
          Then  I need to validate flight ltinerary
          
            Then I click on the fare breakup
             Then  I need to validate fare breakup
              Then I click on the booknow
            Then I need to add the traveller details
              Then I need to add the traveller common details
              Then I enter the promo code
             Then  I need to validate flight Summary 
             Then I click on payment continue 
             Then I need to validate flight Summary payment
              Then I need to validate payment gateway
              Then I enter the card details
               Then I need to validate confirmation page
             
             
       
   
       
       
       
     
     
     
     @multi_city_flight_listing
    Scenario: Book a flight with specified details
  	Given I setup the test data with "multi_city_flight_listing"
    Given I want to open the application
     #Then I change the currency type
     #Then I change the language
     Then I select the trip type
     Then I enter the source
    Then I enter the destination
    Then I enter the departure date
    Then I click the add another city
    Then I enter the segment two
    Then I enter the segment three
   Then I select the flight category
    Then I add passengers
    #Then I add advance search options 
    #Then I enter the  preferred airlines 
    Then I click on the search button
    #Then I need to validate flight listing multi segment three
      #Then I need to validate installments
      #Then I need to validate time
      #Then I need to validate price
      #Then I need to validate stop
      #Then I need to validate duration
       #Then I need to validate airline
      #Then I need to validate refundable
      #Then I click on the more filter 
      #Then I need to validate departure airport
       #Then I need to validate arrival airport
        #Then I need to validate departure stopover
        #Then I need to validate aircraft types
         #Then I click on the apply button 
       #Then I need to validate fare option three segment
       #Then I need to validate sortby filter 
       
       
        Then I click on the flight details 
        
        #Then  I need to validate fare option card for round trip
         Then I click on the flight ltinerary
          Then  I need to validate flight ltinerary 
            #Then I click on the baggage
             #Then  I need to validate baggage
            #Then I click on the fare breakup
             #Then  I need to validate fare breakup
             #Then I click on the booknow
            #Then I need to add the traveller details
              #Then I need to add the traveller common details
              #Then I enter the promo code
             #Then  I need to validate flight Summary 
             #Then I click on payment continue 
             #Then I need to validate flight Summary payment
              #Then I need to validate payment gateway
              #Then I enter the card details
               #Then I need to validate confirmation page
             
             
             
             
             
             
    
    
     @multi_city_flight_listing_six
    Scenario: Book a flight with specified details
  	Given I setup the test data with "multi_city_flight_listing_six"
    Given I want to open the application
     #Then I change the currency type
     #Then I change the language
     Then I select the trip type
     Then I enter the source
    Then I enter the destination
    Then I enter the departure date
    Then I click the add another city
    Then I enter the segment two
    Then I enter the segment three
   Then I select the flight category
    #Then I add passengers
    #Then I add advance search options 
    #Then I enter the  preferred airlines 
    Then I click on the search button
    Then I need to validate flight listing multi segment six
    