Feature: Home Page Validation
  I want to use this feature file to validate elements in the homepage

  @HomePage
  Scenario: I validate all the elements in the header of the home page
  Given I want to open the application
  Then I verify header elements present in the home page
  And I verify the functionality of the header elements
  
  
  @Regression
  Scenario: I validate all the elements in the mid region of the home page
  Given I want to open the application
  
  
  @Sanity
  Scenario: I validate all the elements in the footer of the home page
  Given I want to open the application
  
  
   