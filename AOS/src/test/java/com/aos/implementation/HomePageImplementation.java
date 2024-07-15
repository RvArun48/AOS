package com.aos.implementation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aos.base.TestRunner;
import com.aos.pageObjects.HomePage;
import com.aos.utils.LogEvent;
import com.aos.utils.WebElementInfo;
import com.aventstack.extentreports.Status;

public class HomePageImplementation extends TestRunner {

	public WebDriver driver = getDriver();
	public static final Logger logger = LogManager.getLogger(HomePageImplementation.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);

	public void verifyPresenceOfHeaderElements() {

		try {
			List<WebElementInfo> webElements = Arrays.asList(
					new WebElementInfo(By.xpath("//a[contains(text(),'Home')]"), "Header Home Link"),
					new WebElementInfo(By.xpath("//a[contains(text(),'Holiday Package')]"), "Holiday Packages Link"),
					new WebElementInfo(By.xpath("//a[contains(text(),'Events ')]"), "Events Link"),
					new WebElementInfo(By.xpath("//a[contains(text(),'Hotels')]"), "Hotels Link"),
					new WebElementInfo(By.xpath("//a[contains(text(),'Flights')]"), "Flights Link"),
					new WebElementInfo(By.xpath("//*[@id='countryNgb']"), "Country Flag Link"),
					new WebElementInfo(By.xpath("//*[@id='currencyId']"), "Currency Link"),
					new WebElementInfo(By.xpath("//*[@id='langId']"), "Language Link"),
					// new WebElementInfo(By.xpath("//*[contains(text(),'+961 1760555')]"), "Contact
					// For Support"),
					new WebElementInfo(By.xpath("//button[normalize-space()='Sign In']"), "Sign In Option"),
					new WebElementInfo(By.xpath("//*[@id='round-trip']"), "Round Trip Option"),
					new WebElementInfo(By.xpath("//*[@id='one-way']"), "One Way Option"),
					new WebElementInfo(By.xpath("//*[@id='multicity']"), "Multi City Option"),
					new WebElementInfo(By.xpath(
							"//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Flexible dates ± 3 days')]"),
							"Flexible dates Option"),
					new WebElementInfo(By.xpath("//*[contains(text(),' Advanced search options ')]"),
							"Advanced search options"),
					new WebElementInfo(By.xpath(
							"//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Baggage only')]"),
							"Baggage only Option"),
					new WebElementInfo(
							By.xpath("//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Flights')]"),
							"Direct Flights options"),
					new WebElementInfo(By.xpath(
							"//*[@class='checkboxWrap checkboxDefault']/child::*[contains(text(),'Refundable')]"),
							"Refundable Option"),
					new WebElementInfo(
							By.xpath("//*[@class='ng-value-container']/child::*[contains(text(),'Preferred Airline')]"),
							"Preferred Airline Option"),
					new WebElementInfo(
							By.xpath("//*[@class='active ng-star-inserted']/child::*[contains(text(),'Flights')]"),
							"Flights Option"),
					new WebElementInfo(By.xpath("//*[@class='ng-star-inserted']/child::*[contains(text(),'Holidays')]"),
							"Holidays Option"),
					new WebElementInfo(By.xpath("//*[@class='ng-star-inserted']/child::*[contains(text(),'Hotels')]"),
							"Hotels Option"),
					new WebElementInfo(By.xpath("//*[@class='ng-star-inserted']/child::*[contains(text(),'Events')]"),
							"Events Option")

			);

			SoftAssertions softly = new SoftAssertions();
			String elementValidationLog = "";
			int count = 0;
			for (WebElementInfo elementInfo : webElements) {
				elementValidationLog += ""+(++count) + ". Validating presence of: " + elementInfo.getDescription()+"<br>";
				logger.info("Validating presence of: " + elementInfo.getDescription());
				List<WebElement> elements = driver.findElements(elementInfo.getLocator());
				softly.assertThat(elements).as("Checking presence of: " + elementInfo.getDescription()).isNotEmpty();
			}
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, elementValidationLog, driver,
					getScenarioName());

			softly.assertAll();

		} catch (Exception e) {
			logger.info("Exception occured at verifyPresenceOfHeaderElements()->" + e.getMessage());
		}
	}

}
