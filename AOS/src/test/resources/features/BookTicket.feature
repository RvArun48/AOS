Feature: Book Ticket
  I want to use this feature file to test ticket booking scenarios

  @Test
  Scenario: Book ticket for Abudhabi to London
  Given I want to open the application
  And book a flight ticket for "abudhabi_to_london"
  
  @Test1
  Scenario: Book ticket for Dubai to Doha
  Given I want to open the application
  And book a flight ticket for "dubai_to_doha"
  
  
   