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

	@FindBy(xpath = "(//*[@aria-label='search'])[1]")
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
