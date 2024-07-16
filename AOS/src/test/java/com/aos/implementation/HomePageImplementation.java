package com.aos.implementation;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
					new WebElementInfo(By.xpath("//*[@id='currencyId']"), "Currency Link"),
					new WebElementInfo(By.xpath("//*[@id='langId']"), "Language Link"),
					new WebElementInfo(By.xpath("//*[contains(text(),'+971 50423989')]"), "Contact For Support"),

					new WebElementInfo(By.xpath("//*[@class='btn btn-primary'][contains(text(),'Sign in')]"),
							"Sign In Option"),
					new WebElementInfo(
							By.xpath("//*[@class='empire_productName']/child::*[contains(text(),'Retrieve Booking')]"),
							"Retrieve Booking Link"));

					
			SoftAssertions softly = new SoftAssertions();
			String elementValidationLog = "";
			int count = 0;
			for (WebElementInfo elementInfo : webElements) {
				elementValidationLog += "" + (++count) + ". Validating presence of: <b>" + elementInfo.getDescription()
						+ "</b><br>";
				logger.info("Validating presence of: " + elementInfo.getDescription());
				List<WebElement> elements = driver.findElements(elementInfo.getLocator());
				softly.assertThat(elements).as("Checking presence of: " + elementInfo.getDescription()).isNotEmpty();
			}
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, elementValidationLog, driver,
					getScenarioName());

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
				// Optionally, you can log each individual assertion failure
				/*
				 * for (Throwable t : e.getSuppressed()) { test.log(Status.FAIL,
				 * t.getMessage()); }
				 */
			}

		} catch (Exception e) {
			logger.info("Exception occured at verifyPresenceOfHeaderElements()->" + e.getMessage());
		}
	}

	public void verifyElementFunctionality() {
		try {
			SoftAssertions softly = new SoftAssertions();
			
			
			
			String url = driver.getCurrentUrl();
			logger.info("Current url is: " + url);
			clickElement(homePage.logoElementGroup, "Clicking on logo");
			logger.info("After click  url is: " + url);
			softly.assertThat(url).isEqualTo(driver.getCurrentUrl());
			
			// Verifying home page headerlink
			url = driver.getCurrentUrl();
			logger.info("Current url is: " + url);
			clickElement(homePage.homeElementGroup, "Clicking on home page headerlink");
			logger.info("Current url is: " + url);
			softly.assertThat(url).isEqualTo(driver.getCurrentUrl());
			//////////////////////////////////////////////////////////////////////////////////////////////////

			String currency = homePage.currencyIdElementGroup.getText();
			logger.info("Current Currency Value is: " + currency);
			clickElement(homePage.currencyIdElementGroup, "Clicking on Currency");
			clickElement(homePage.selectCurrencyElementGroup, "Selecting the Currency");
			logger.info("Updated Currency Value is: " + homePage.currencyIdElementGroup.getText());

			softly.assertThat("USD").isEqualTo(homePage.currencyIdElementGroup.getText());

			String language = homePage.languageElementGroup.getText();
			logger.info("Current language is: " + language);
			clickElement(homePage.languageElementGroup, "Clicking on Language");
			clickElement(homePage.selectLanguageElementGroup, "Selecting the Language");
			logger.info("Updated language is: " + homePage.languageElementGroup.getText());
			
			
			softly.assertThat("Georgian").isEqualTo(homePage.languageElementGroup.getText());
			

			url = driver.getCurrentUrl();
			logger.info("Current url is: " + url);
			clickElement(homePage.retrieveBookingElementGroup, "Clicking the Retrieve Booking");
			logger.info("updated url is: " + driver.getCurrentUrl());
			
			softly.assertThat(url).contains("retrievebooking/login");

			clickElement(homePage.SigninElementGroup, "Clicking the SignIn");
			logger.info("attribute value is: " + homePage.signUpValidationElementGroup.getAttribute("type"));
			
			softly.assertThat(homePage.signUpValidationElementGroup.getAttribute("type")).isEqualTo("email");
			

			

			//////////////////////////////////////////////////////////////////////////////////////////////////////

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}
		} catch (Exception e) {
			logger.info("Exception occured at verifyElementFunctionality()->" + e.getMessage());
		}

	}

	public void clickElement(WebElement element, String message) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			logger.info(message);
			element.click();
			Thread.sleep(1500);
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, message, driver, getScenarioName());
		} catch (Exception e) {
			logger.info("Exception occured at clickElement()->" + e.getMessage());
		}

	}

}
