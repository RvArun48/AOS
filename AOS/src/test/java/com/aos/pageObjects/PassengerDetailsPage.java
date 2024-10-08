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
	
	@FindBy(xpath = "//label[text()='Passport Information']")
	public WebElement passportInformation;
	
	@FindBy(xpath = "//label[text()='National ID (Only for GCC Nationals)']")
	public WebElement nationalId;
	

	@FindBy(xpath = "(//input[@formcontrolname='DocumentNumber'])[1]")
	public WebElement nationalIdField;
	
	@FindBy(xpath = "//label[text()='Local Id']")
	public WebElement localId;
	
	@FindBy(xpath = "//label[text()='Iqama ID (Saudi Residence for Foreigners)']")
	public WebElement iqamaId;
	
	
	@FindBy(xpath = "//*[@class='btn OptPrefBtn' and contains(text(),' Optional Preference ')]")
	public WebElement optionalPreference;
	
	
	@FindBy(xpath = "//*[text()='Apply']")
	public WebElement clickApply;
	
	@FindBy(xpath = "//*[@class='mdc-checkbox__native-control mdc-checkbox--selected']")
	public WebElement clickAdultOneAddress;
	
	@FindBy(xpath = "//*[@class='mdc-checkbox__native-control mdc-checkbox--selected']")
	public WebElement clickAdultTwoAddress;
	
	@FindBy(xpath = "//*[@formcontrolname='userId']")
	public WebElement userNameb2b;
	
	@FindBy(xpath = "//*[@formcontrolname='password']")
	public WebElement passwordb2b;
	
	@FindBy(xpath = "//span[contains(text(),'Sign in')]")
	public WebElement signInb2b;
	
}
