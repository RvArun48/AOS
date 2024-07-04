package com.aos.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassengerDetailsPage {

	WebDriver driver;

	@FindBy(xpath = "//*[@class='ng-option-label ng-star-inserted' and text()='Mr']")
	public WebElement titleMr;
	
	@FindBy(xpath = "//*[@class='ng-option-label ng-star-inserted' and text()='Ms']")
	public WebElement titleMs;
	
	@FindBy(xpath = "//*[@class='ng-option-label ng-star-inserted' and text()='Mrs']")
	public WebElement titleMrs;
	
	@FindBy(xpath = "(//*[@class='empireF_travelerBtn'])[1]")
	public WebElement addAdultCount;
	
	@FindBy(xpath = "(//*[@class='empireF_travelerBtn'])[2]")
	public WebElement addChildCount;
	
	@FindBy(xpath = "(//*[@class='empireF_travelerBtn'])[5]")
	public WebElement addInfantCount;
		
	
	@FindBy(xpath = "//*[@formcontrolname='phne_code']")
	public WebElement countryMobile;
	
	
	@FindBy(xpath = "//input[@formcontrolname='MobileNo']")
	public WebElement mobileNo;
	
	@FindBy(xpath = "//*[@id='cardNoInput']")
	public WebElement cardNo;
	
	@FindBy(xpath = "//*[@id='expDateInput']")
	public WebElement expDate;
	
	@FindBy(xpath = "//*[@id='cvvInput']")
	public WebElement cvv;

	
	@FindBy(xpath = "//*[@id='chNameInput']")
	public WebElement cardHolderName;
	
	@FindBy(xpath = "//*[@id='submitBtn']")
	public WebElement pay;
	
	
}
