package com.aos.pageObjects;

import java.time.Duration;

import javax.xml.xpath.XPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.implementation.SearchFormImplementation;

public class HomePage {
	public static final Logger logger = LogManager.getLogger(HomePage.class);

	WebDriver driver;

	@FindBy(xpath = "//li[contains(text(),'One Way')]")
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

	@FindBy(xpath = "//li[contains(text(),'One Way')]")
	public WebElement oneWayElementGroup;

	@FindBy(xpath = "//*[@id='multicity']")
	public WebElement multiCityElementGroup;

	@FindBy(xpath = "//li[@id='custom-search']")
	public WebElement multiCitySegmentElementGroup;

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

	@FindBy(xpath = "(//*[contains(text(),'Preferred Airline')]//following::input)[1]")
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

	@FindBy(xpath = "//*[@class='empire_productName']/child::*[contains(text(),'Retrieve Booking')]")
	public WebElement retrieveBookingElementGroup;

	@FindBy(xpath = "//*[@formcontrolname='email']")
	public WebElement signUpValidationElementGroup;

	@FindBy(xpath = "//*[@class='empire_logo']")
	public WebElement logoElementGroup;

	@FindBy(xpath = "//*[@class='ng-star-inserted']/child::*[contains(text(),'English')]")
	public WebElement selectEnglishLanguageElementGroup;

	@FindBy(xpath = "//*[text()='Create an account']")
	public WebElement createAccountSignup;

	@FindBy(xpath = "//*[text()='Create Account']")
	public WebElement createAccount;

	@FindBy(xpath = "//*[text()=' Email is required ']")
	public WebElement warningAlertEmail;

	@FindBy(xpath = "//*[text()=' Password is required ']")
	public WebElement warningAlertPassword;

	@FindBy(xpath = "//*[@formcontrolname='email']")
	public WebElement createAccountSignUpUserName;

	@FindBy(xpath = "//*[@formcontrolname='password']")
	public WebElement createAccountSignUpPassword;

	@FindBy(xpath = "//*[@class='signIn_forgotPassLink']/child::*[contains(text(),'Forgot password?')]")
	public WebElement forgotPassword;

	@FindBy(xpath = "//*[@formcontrolname='email']")
	public WebElement forgotPasswordEmail;

	@FindBy(xpath = "//*[text()='Continue']")
	public WebElement forgotPasswordContinue;

	@FindBy(xpath = "//*[@formcontrolname='email']")
	public WebElement signInUserName;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement signInPassword;

	@FindBy(xpath = "(//button[text()='Sign in'])[2]")
	public WebElement popupsigninButton;

	@FindBy(xpath = "//span[text()='My Account']")
	public WebElement loginSuccessValidation;

	@FindBy(xpath = "//*[text()=' Email Sent ']")
	public WebElement forgotPasswordValidation;

	@FindBy(xpath = "//small[text()=' Please enter a valid password ']")
	public WebElement paswValidation;

	@FindBy(xpath = "//form[@class='ng-dirty ng-touched ng-submitted ng-invalid']//div[@class='ng-star-inserted']")
	public WebElement emailValidation;

	@FindBy(xpath = "//*[@class='footerlinks_head ng-star-inserted']/child::*[contains(text(),'Terms and conditions')]")
	public WebElement termsCondition;

	@FindBy(xpath = "//*[@class='footerlinks_text ng-star-inserted']/child::*[contains(text(),'Career')]")
	public WebElement career;

	@FindBy(xpath = "//a[contains(@href,'https://www.instagram.com')]")
	public WebElement instagram;

	@FindBy(xpath = "//a[contains(@href,'https://x.com/?lang=en')]")
	public WebElement x;

	@FindBy(xpath = "//a[contains(@href,'https://www.facebook.com')]")
	public WebElement facebook;

	@FindBy(xpath = "//*[@class='mobHeading']")
	public WebElement whatsapp;
	
	@FindBy(xpath = "//*[@class='dropdown-toggle empireF_travelerCard']")
	public WebElement passangerClass;
	
	//*[@role='listbox']//child::span[text()='Airline Utair Ukraine']
	
	
	
	
	//*[@class='dropdown-toggle empireF_travelerCard']

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
				logger.info("stale element:" + locator);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					logger.info(e.toString());
				}
			}

		}
		return driver.findElement(By.xpath(locator));
	}
}
