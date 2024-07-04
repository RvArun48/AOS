package com.aos.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage {

	WebDriver driver;

	@FindBy(xpath = "//*[contains(@class,'empireFlight_listing-card')]")
	public WebElement searchResultCard;
	
	@FindBy(xpath = "(//*[@class='empireFlight_airline-nameWrapper'])[1]")
	public WebElement sourceDestinationResultTitle;
	
}
