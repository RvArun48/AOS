package com.aos.pageObjects;

import java.time.Duration;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//li[text()='One Way ']")
	public WebElement oneWayTab;

	@FindBy(xpath = "(//*[@placeholder='Origin'])[1]")
	public WebElement from;

	@FindBy(xpath = "//input[@formcontrolname='DepartureCode']")
	public WebElement fromInput;

	@FindBy(xpath = "//*[text()=' Dubai International Airport']")
	public WebElement fromResult;

	@FindBy(xpath = "//*[text()=' London All Airports']")
	public WebElement toResult;

	@FindBy(xpath = "(//*[@placeholder='Destination'])[1]")
	public WebElement to;

	@FindBy(xpath = "//input[@formcontrolname='ArrivalCode']")
	public WebElement toInput;

	@FindBy(xpath = "(//h4[contains(text(),'Departure')])[1]")
	public WebElement departureCalendar;

	@FindBy(xpath = "//*[@class='empireF_travelerCount']")
	public WebElement passengerCountDropDown;

	@FindBy(xpath = "(//h4[contains(text(),'Economy')])[1]")
	public WebElement passengerClass_Economy;

	@FindBy(xpath = "(//h4[contains(text(),'Premium Economy')])[1]")
	public WebElement passengerClass_PremiumEconomy;

	@FindBy(xpath = "(//h4[contains(text(),'Business Class')])[1]")
	public WebElement passengerClass_BusinessClass;

	@FindBy(xpath = "(//h4[contains(text(),'First Class')])[1]")
	public WebElement passengerClass_FirstClass;
	
	
	@FindBy(xpath = "(//*[@class='empireF_travelerBtn'])[1]")
	public WebElement passengerType_Adult;

	@FindBy(xpath = "(//*[@class='empireF_travelerBtn'])[2]")
	public WebElement passengerType_Child;

	@FindBy(xpath = "(//*[@class='empireF_travelerBtn'])[3]")
	public WebElement passengerType_Infant;

	// ----- add apply bytton later

	@FindBy(xpath = "//*[@class='empireF_btnWrapper']")
	public WebElement searcFlightButton;

	@FindBy(xpath = "(//*[@id='dropdownAirlines'])")
	public WebElement selectAirlines;

	@FindBy(xpath = "//*[text()=' Qatar Airways']")
	public WebElement selectAirlinescountry;

	@FindBy(xpath = "(//*[@class='ng-star-inserted' and contains(text(),'Select')])[1]")
	public WebElement selectAirlinesbooking;

	@FindBy(xpath = "//*[text()=' Continue ']")
	public WebElement continueBooking;

	@FindBy(xpath = "//span[@class='flightDetailText']")
	public WebElement Booknow;
	
	@FindBy(xpath = "//input[@formcontrolname='FirstName']")
	public WebElement firstName;
	
	@FindBy(xpath = "//input[@formcontrolname='LastName']")
	public WebElement lastName;
	
	@FindBy(xpath = "//input[@formcontrolname='DocumentNumber']")
	public WebElement passportNo;
	
	@FindBy(xpath = "//input[@formcontrolname='EmailAddress']")
	public WebElement email;
	
	
	@FindBy(xpath = "//*[@class='btn-primary btn ng-star-inserted']")
	public WebElement continueToPayment;
	
	@FindBy(xpath = "//*[@class='empireFlight-PaymentPriceBtnWrapper']")
	public WebElement processToPay;


	@FindBy(xpath = "//*[@id='dropdownAirlines']")
	public WebElement filterAirlines;
	
	@FindBy(xpath = "//a[contains(text(),'Home')]")
	public WebElement homeElementGroup;
	
	@FindBy(xpath = "//a[contains(text(),'Holiday Packages')]")
	public WebElement holidayPackagesElementGroup;
	
	@FindBy(xpath = "//a[contains(text(),'Events ')]")
	public WebElement eventsElementGroup;
	
	@FindBy(xpath = "//a[contains(text(),'Hotels')]")
	public WebElement hotelsElementGroup;
	
	@FindBy(xpath = "//a[contains(text(),'Flights')]")
	public WebElement flightsElementGroup;
	
	@FindBy(xpath = "//*[@id='countryNgb']")
	public WebElement countryFlagElementGroup;
	
	
	@FindBy(xpath = "//*[@id='currencyId']")
	public WebElement currencyIdElementGroup;
	
	@FindBy(xpath = "//*[@id='langId']")
	public WebElement languageElementGroup;
	
	@FindBy(xpath = "//*[contains(text(),'+961 1760555')]")
	public WebElement numberElementGroup;
	
	@FindBy(xpath = "//*[@class='btn btn-primary'][contains(text(),'Sign in')]")
	public WebElement SigninElementGroup;
	
	@FindBy(xpath = "//*[@id='round-trip']")
	public WebElement roundTripElementGroup;
	
	@FindBy(xpath = "//*[@id='one-way']")
	public WebElement oneWayElementGroup;
	
	@FindBy(xpath = "//*[@id='multicity']")
	public WebElement multiCityElementGroup;
	
	@FindBy(xpath = "//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Flexible dates ± 3 days')]")
	public WebElement flxiableDateElementGroup;
	
	@FindBy(xpath = "//*[contains(text(),' Advanced search options ')]")
	public WebElement advanceSearchElementGroup;
	
	@FindBy(xpath = "//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Baggage only')]")
	public WebElement baggageOnlyElementGroup;
	

	@FindBy(xpath = "//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Flights')]")
	public WebElement directFlightsElementGroup;
	
	@FindBy(xpath = "//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Refundable')]")
	public WebElement refundableElementGroup;
	
	@FindBy(xpath = "//*[@class='ng-value-container']/child::*[contains(text(),'Preferred Airline')]")
	public WebElement preferredAirlineElementGroup;
	
	@FindBy(xpath = "//*[@class='active ng-star-inserted']/child::*[contains(text(),'Flights')]")
	public WebElement flightSelectAirlineElementGroup;
	
	@FindBy(xpath = "//*[@class='ng-star-inserted']/child::*[contains(text(),'Hotels')]")
	public WebElement hotelselectAirlineElementGroup;
	
	@FindBy(xpath = "//*[@class='ng-star-inserted']/child::*[contains(text(),'Events')]")
	public WebElement eventsselectAirlineElementGroup;
	
	@FindBy(xpath = "//*[@class='ng-star-inserted']/child::*[contains(text(),'Holidays')]")
	public WebElement holidaysselectAirlineElementGroup;
	
	@FindBy(xpath = " //div[normalize-space()='USD']")
	public WebElement selectCurrencyElementGroup;
	
	@FindBy(xpath = "//*[@class='ng-star-inserted']/child::*[contains(text(),'Georgian')]")
	public WebElement selectLanguageElementGroup;
	
	@FindBy(xpath = "//*[@class='empire_productName']/child::*[contains(text(),'ჯავშნის აღდგენა?')]")
	public WebElement retrieveBookingElementGroup;
	
	@FindBy(xpath = "//*[@formcontrolname='email']")
	public WebElement signUpValidationElementGroup;
	
	@FindBy(xpath = "//*[@class='empire_logo']")
	public WebElement logoElementGroup;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public WebElement getElementByXpath(WebDriver driver, String locator, String data) {
		locator = locator.replace("${token}", data);
		int i = 0;
		while (i < 10) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(locator))));
				driver.findElement(By.xpath(locator)).click();
				i = 10;
			} catch (Exception e) {
				i++;
				System.out.println("stale element:"+locator);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return driver.findElement(By.xpath(locator));
	}

}
