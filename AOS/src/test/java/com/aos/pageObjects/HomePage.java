package com.aos.pageObjects;

import java.time.Duration;

import javax.xml.xpath.XPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	@FindBy(xpath = "(//*[@class='empireF_searchFromTo ng-star-inserted'])[1]")
	public WebElement fromToContainer_1;

	@FindBy(xpath = "(//*[@class='empireF_searchFromTo ng-star-inserted'])[2]")
	public WebElement fromToContainer_2;

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

	@FindBy(xpath = "//*[@aria-label='search']")
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

	@FindBy(xpath = "//li[contains(text(),'round-trip')]")
	public WebElement roundTripElementGroup;

	@FindBy(xpath = "//li[contains(text(),'One Way')]")
	public WebElement oneWayElementGroup;

	@FindBy(xpath = "//*[@id='multicity']")
	public WebElement multiCityElementGroup;

	@FindBy(xpath = "//li[@id='custom-search']")
	public WebElement multiCitySegmentElementGroup;

	@FindBy(xpath = "//*[contains(text(),' Advanced search options ')]")
	public WebElement advanceSearchElementGroup;

	@FindBy(xpath = "//*[text()='Baggage only']")
	public WebElement baggageOnlyElementGroup;

	@FindBy(xpath = "//*[text()='Direct Flights']")
	public WebElement directFlightsElementGroup;

	@FindBy(xpath = "//*[text()='Refundable']")
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

	@FindBy(xpath = "(//input[@formcontrolname='DepartureCode'])[2]")
	public WebElement fromsegmenttwo;

	@FindBy(xpath = "(//input[@formcontrolname='DepartureCode'])[3]")
	public WebElement fromsegmentthree;

	@FindBy(xpath = "(//input[@formcontrolname='DepartureCode'])[4]")
	public WebElement fromsegmentfour;

	@FindBy(xpath = "(//input[@formcontrolname='DepartureCode'])[5]")
	public WebElement fromsegmentfive;

	@FindBy(xpath = "(//input[@formcontrolname='DepartureCode'])[6]")
	public WebElement fromsegmentsix;

	@FindBy(xpath = "(//input[@id='ToDropDown1'])[1]")
	public WebElement toSegmentTwo;

	@FindBy(xpath = "(//input[@id='ToDropDown2'])[1]")
	public WebElement tosegmentthree;

	@FindBy(xpath = "(//input[@formcontrolname='ArrivalCode'])[4]")
	public WebElement tosegmentfour;

	@FindBy(xpath = "(//input[@formcontrolname='ArrivalCode'])[5]")
	public WebElement tosegmentfive;

	@FindBy(xpath = "(//input[@formcontrolname='ArrivalCode'])[6]")
	public WebElement tosegmentsix;

	@FindBy(xpath = "(//*[@class='empireF_searchLabel empireF_departureIcone'])[2]")
	public WebElement datesegmenttwo;

	@FindBy(xpath = "(//*[@class='empireF_searchLabel empireF_departureIcone'])[3]")
	public WebElement datesegmentthree;

	@FindBy(xpath = "(//*[@class='empireF_searchLabel empireF_departureIcone'])[4]")
	public WebElement datesegmentfour;

	@FindBy(xpath = "(//*[@class='empireF_searchLabel empireF_departureIcone'])[5]")
	public WebElement datesegmentfive;

	@FindBy(xpath = "(//*[@class='empireF_searchLabel empireF_departureIcone'])[6]")
	public WebElement datesegmentsix;

	@FindBy(xpath = "//span[contains(text(),'Add Another City ')]")
	public WebElement addAnotherCity;

	@FindBy(xpath = "(//input[@id='umrahFare'])[1]")
	public WebElement umrahFare;

	@FindBy(xpath = "//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Flexible dates ± 3 days')]")
	public WebElement flxiableDateElementGroup;

	@FindBy(xpath = "(//h4[contains(text(),' Return ')])[1]")
	public WebElement returnCalander;

	@FindBy(xpath = "//button[@class='editBTN comFlexmody']")
	public WebElement editSearch;

	@FindBy(xpath = "(//span[contains(text(),'Departure')])[1]")
	public WebElement flexibleDateValidation;

	@FindBy(xpath = "(//span[contains(text(),'Direct')])[2]")
	public WebElement directFligfhtValidation;

	@FindBy(xpath = "(//div[contains(text(),'Refundable')])[1]")
	public WebElement refundableValidation;

	@FindBy(xpath = "(//h4[contains(text(),'Emirates ')])[2]")
	public WebElement preferrAirlinesValidation;

	@FindBy(xpath = "(//span[contains(text(),'Time')])[1]")
	public WebElement timeFunctionality;

	@FindBy(xpath = "(//span[contains(text(),'Price')])[1]")
	public WebElement priceFunctionality;

	@FindBy(xpath = "//*[@id='dropdownStops']")
	public WebElement stopFunctionality;

	@FindBy(xpath = "(//span[contains(text(),'Duration')])[1]")
	public WebElement durationFunctionality;

	@FindBy(xpath = "(//span[contains(text(),'Refundable')])[1]")
	public WebElement refundableFunctionality;

	@FindBy(xpath = "(//a[contains(text(),'More Filters')])[1]")
	public WebElement moreFilters;

	@FindBy(xpath = "(//button[contains(text(),'Reset')])[1]")
	public WebElement reset;

	@FindBy(xpath = "//*[@class='empireF_installmentwrap ng-star-inserted']")
	public WebElement installmentClick;

	@FindBy(xpath = "(//*[@class='btn-primary btn' and contains(text(),' Awesome ')])[1]")
	public WebElement CloseInstallmentClick;

	@FindBy(xpath = "(//*[@class='showmore' and contains(text(),'more flight options')])[1]")
	public WebElement showMoreOption;

	@FindBy(xpath = "(//*[@class='hidemore' and contains(text(),'Hide more options')])[1]")
	public WebElement hideMoreOption;

	@FindBy(xpath = "//*[@class='mat-mdc-radio-touch-target']")
	public WebElement selectButton;

	@FindBy(xpath = "//*[@id='mat-radio-4-input']")
	public WebElement selectButtonTwo;

	@FindBy(xpath = "//input[@type='checkbox' and @class='mdc-checkbox__native-control' and @id='mat-mdc-checkbox-31-input']")
	public WebElement nonStop;

	@FindBy(xpath = "//input[@id='mat-mdc-checkbox-34-input']")
	public WebElement twoStop;

	@FindBy(xpath = "//input[@id='mat-mdc-checkbox-90-input']")
	public WebElement oneStop;

	@FindBy(xpath = "//input[@id='mat-mdc-checkbox-3-input']")
	public WebElement selectRefundable;

	@FindBy(xpath = "//input[@id='mat-mdc-checkbox-4-input']")
	public WebElement selectNotRefundable;

	@FindBy(xpath = "(//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted' and contains(text(),'Direct')])[1]")
	public WebElement stopValidation;

	@FindBy(xpath = "(//*[@class='empireFlight_refund-text ref' and contains(text(),'Refundable')])[1]")
	public WebElement refundableValidationOne;

	@FindBy(xpath = "(//span[@class='empireF_PCsvgIcon'])[1]")
	public WebElement rightFlixibleCalander;

	@FindBy(xpath = "(//span[@class='empireF_PCsvgIconLeft'])[1]")
	public WebElement leftFlixibleCalander;

	@FindBy(xpath = "(//button[@class='btn-primary btn'])[2]")
	public WebElement applyMoreFilter;

	@FindBy(xpath = "(//button[@class='btn-primary btn'])")
	public WebElement applyMoreFilterSix;

	@FindBy(xpath = "//h4[contains(text(),'Cheapest')]")
	public WebElement cheapestFare;

	@FindBy(xpath = "//h4[contains(text(),'Fastest')]")
	public WebElement fastestFare;

	@FindBy(xpath = "//h4[contains(text(),'Best Value')]")
	public WebElement bestValueFare;

	@FindBy(xpath = "(//*[@class='empireFlight_amount ng-star-inserted'])[1]")
	public WebElement currencyPriceValidation;

	@FindBy(xpath = "//*[@class='empireFlight_amountWrapper']/h2")
	public WebElement currencyPriceValidationSix;

	@FindBy(xpath = "(//*[@class='empireFlight_SortByOption'])[1]/h4[2]")
	public WebElement cheapestFareAmount;

	@FindBy(xpath = "(//*[@class='empireFlight_SortByOption'])[2]/h4[2]")
	public WebElement fastestFareAmount;

	@FindBy(xpath = "(//*[@class='empireFlight_SortByOption'])[3]/h4[2]")
	public WebElement bestValueFareAmount;

	@FindBy(xpath = "(//*[@class='empireFlight_amount'])[1]")
	public WebElement currencyPriceValidationMultiThree;

	@FindBy(xpath = "(//h4[contains(text(),'Flight details')])[1]")

	public WebElement clickFlightDetails;

	@FindBy(xpath = "(//*[@class='fareoption_rate'])[2]/p")

	public WebElement clickFareOption;

	@FindBy(xpath = "//*[@class='empireFlight_amountWrapper']/h2")
	public WebElement currencyFareOption;
	
	
	@FindBy(xpath = "//span[contains(text(),'Flight Itinerary')]")
	public WebElement flightLtinerary;
	
	@FindBy(xpath = "//span[contains(text(),'Baggage')]")
	public WebElement clickBaggage;
	
	
	@FindBy(xpath = "(//*[@class='empireFlight_confirmBagTableData'])[1]")
	public WebElement cabinBaggage;
	

	@FindBy(xpath = "//*[@class='empireFlight_confirmBagTableData ng-star-inserted']")
	public WebElement checkedBaggage;
	
	
	
	@FindBy(xpath = "//span[contains(text(),'Fare Options')]")
	public WebElement clickFareOptionVal;
	
	@FindBy(xpath = "//span[contains(text(),'Fare Breakup')]")
	public WebElement clickFarebreakup;
	
	
	@FindBy(xpath = "//*[@class='empireFlight_FOBtnWrapper ng-star-inserted']")
	public WebElement clickReturnFareoption;

	
	
	
	
	
	
	
	
	
	
	

	public WebElement getElementByXpath(WebDriver driver, String locator, String data) throws InterruptedException {
		return getElementByXpath(driver, locator, data, null);
	}

	public WebElement getElementByXpath(WebDriver driver, String locator, String data, WebElement containerElement)
			throws InterruptedException {
		locator = locator.replace("${token}", data);

		int i = 0;
		while (i < 10) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(locator))));

				if (containerElement == null) {
					driver.findElement(By.xpath(locator)).click();
					// executor.executeScript("arguments[0].click();",
					// driver.findElement(By.xpath(locator)));
				} else {
					// containerElement.findElement(By.xpath(locator)).click();
					executor.executeScript("arguments[0].click();", containerElement.findElement(By.xpath(locator)));
				}

				i = 10;
			} catch (StaleElementReferenceException e) {
				i++;
				logger.info("stale element:" + locator);
				Thread.sleep(1500);
				continue;
			} catch (Exception e) {
//				logger.info("Exception occurred at getElementByXpath()->" + e.toString());
			}
		}
		return driver.findElement(By.xpath(locator));
	}
}
