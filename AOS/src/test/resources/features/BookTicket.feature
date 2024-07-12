Feature: Book Ticket
  I want to use this feature file to test ticket booking scenarios

  @Smoke
  Scenario: Book ticket for Abudhabi to London using Smoke
  Given I want to open the application
  And book a flight ticket for "abudhabi_to_london"
  
  @Regression
  Scenario: Book ticket for Dubai to Doha using Regression
  Given I want to open the application
  And book a flight ticket for "dubai_to_doha"
  
  @Sanity
  Scenario: Book ticket for Dubai to Doha using Sanity
  Given I want to open the application
  And book a flight ticket for "dubai_to_doha"
  
   