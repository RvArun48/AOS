package com.aos.implementation;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.base.TestRunner;
import com.aos.pageObjects.HomePage;
import com.aos.utils.CommonUtils;
import com.aos.utils.GenericActions;
import com.aos.utils.LogEvent;
import com.aos.utils.RequestUtils;
import com.aos.utils.StringUtils;
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
					new WebElementInfo(By.xpath("//*[contains(text(),'+971 12345678')]"), "Contact For Support"),

					new WebElementInfo(By.xpath("//*[@class='btn btn-primary'][contains(text(),'Sign in')]"),
							"Sign In Option"),
					new WebElementInfo(
							By.xpath("//*[@class='empire_productName']/child::*[contains(text(),'Retrieve Booking')]"),
							"Retrieve Booking Link"),
////////////////////////////////////////////////////////////////////////////////////////////////////					
					new WebElementInfo(By.xpath("//*[contains(text(),'Why Book With Us? ')]"), "Why Book With Us? "),
//					new WebElementInfo(By.xpath("//*[@alt='Keep It Simple']"), "Image for Keep It Simple "),
					new WebElementInfo(By.xpath("//*[contains(text(),'Keep It Simple')]"), "Keep It Simple "),

					new WebElementInfo(By.xpath("//*[contains(text(),'Plan With Confidence')]"),
							"Plan With Confidence "),

					new WebElementInfo(By.xpath("//*[contains(text(),'Ready When You Are')]"), "Ready When You Are "),

					new WebElementInfo(By.xpath("//*[contains(text(),'Terms and conditions')]"),
							"Terms and conditions "),
					new WebElementInfo(By.xpath("(//*[contains(text(),'Career')])[1]"), "Career "),
					new WebElementInfo(By.xpath("(//*[contains(text(),'Contact')])[1]"), "Contact "),
					new WebElementInfo(By.xpath("(//*[contains(text(),'Follow Us')])[1]"), "Follow us ")

//					 (//h1[text()="What's new"])[1]
//					//*[contains(text(),'City Break')]
//					(//*[contains(text(),'Top Destination For You')])[1]
//							(//*[contains(text(),'Popular Hotels ')])[1]
//									(//*[contains(text(),'Hot Deals this Week')])[1]
//											(//*[contains(text(),'Popular hotel offering')])[1]
//													(//*[contains(text(),'We will always help you to find your dream vacation come true')])[1]
//															(//*[contains(text(),'Book Your Vacation')])[1]
//																	(//*[@class='empire_homeHeader']/child::*[contains(text(),'Trending Packages')])[1]
//																			(//*[@class='empire_homeHeader']/child::*[contains(text(),'Eco Tourism Packages')])[1]			
			);

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
			GenericActions.clickElement(homePage.logoElementGroup, "Clicking on logo", logger);
			logger.info("After click  url is: " + url);
			softly.assertThat(url).isEqualTo(driver.getCurrentUrl());

			// Verifying home page headerlink
			url = driver.getCurrentUrl();
			logger.info("Current url is: " + url);
			GenericActions.clickElement(homePage.homeElementGroup, "Clicking on home page headerlink", logger);
			logger.info("Current url is: " + url);
			softly.assertThat(url).isEqualTo(driver.getCurrentUrl());

			String currency = homePage.currencyIdElementGroup.getText();
			logger.info("Current Currency Value is: " + currency);
			GenericActions.clickElement(homePage.currencyIdElementGroup, "Clicking on Currency", logger);
			GenericActions.clickElement(homePage.selectCurrencyElementGroup, "Selecting the Currency", logger);
			logger.info("Updated Currency Value is: " + homePage.currencyIdElementGroup.getText());

			softly.assertThat("USD").isEqualTo(homePage.currencyIdElementGroup.getText());

			String language = homePage.languageElementGroup.getText();
			logger.info("Current language is: " + language);
			GenericActions.clickElement(homePage.languageElementGroup, "Clicking on Language", logger);
			GenericActions.clickElement(homePage.selectLanguageElementGroup, "Selecting the Language", logger);
			logger.info("Updated language is: " + homePage.languageElementGroup.getText());
			softly.assertThat("Georgian").isEqualTo(homePage.languageElementGroup.getText());
			logger.info("updated url is: " + driver.getCurrentUrl());

			softly.assertThat(driver.getCurrentUrl()).contains("lc=GE")
					.withFailMessage("Expected string to contain '%s', but it did not.", "lc=GE");

			GenericActions.clickElement(homePage.languageElementGroup, "Clicking on Language", logger);
			GenericActions.clickElement(homePage.selectEnglishLanguageElementGroup, "Selecting the Language", logger);

			url = driver.getCurrentUrl();
			logger.info("Current url is: " + url);
			GenericActions.clickElement(homePage.retrieveBookingElementGroup, "Clicking the Retrieve Booking", logger);
			logger.info("updated url is: " + driver.getCurrentUrl());

			softly.assertThat(driver.getCurrentUrl()).contains("retrievebooking/login")
					.withFailMessage("Expected string to contain '%s', but it did not.", "retrievebooking/login");

			GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
			logger.info("attribute value is: " + homePage.signUpValidationElementGroup.getAttribute("type"));
			softly.assertThat(homePage.signUpValidationElementGroup.getAttribute("type")).isEqualTo("email");

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

	public void verifyImageLoad() throws IOException {
		try {
			SoftAssertions softly = new SoftAssertions();

			softly.assertThat(
					new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector("img[alt='Keep It Simple']"))))
					.as("Checking the image is loaded - Keep It Simple").isTrue();
			softly.assertThat(new CommonUtils()
					.checkImageLoad(driver.findElement(By.cssSelector("div[aria-label='2'] img[alt='Slider Image']"))))
					.as("Checking the image is loaded - Slider Image").isTrue();

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

		} catch (Exception e) {
			logger.info("Exception occured at verifyImageLoad()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}
	}

	public void verifyWhatsappLink() {
		try {
			SoftAssertions softly = new SoftAssertions();

			WebElement element = driver.findElement(By.xpath("//*[@class='mobHeading']"));
			element.click();

			// Get the current window handle
			String mainWindowHandle = driver.getWindowHandle();

			// Wait for the new tab to open (a brief sleep may be necessary)
			Thread.sleep(3000);

			// Get all window handles
			Set<String> allWindowHandles = driver.getWindowHandles();
			ArrayList<String> tabs = new ArrayList<>(allWindowHandles);

			// Switch to the new tab (the last one in the list)
			for (String handle : tabs) {
				if (!handle.equals(mainWindowHandle)) {
					driver.switchTo().window(handle);
					break;
				}
			}

			// Capture the URL of the new tab
			String newTabUrl = driver.getCurrentUrl();

			int responseCode = RequestUtils.getResponseCode(newTabUrl);

			softly.assertThat(responseCode).isEqualTo(200).as("Validating if whatsapp url is up or not");

			// Print the URL in the console
			logger.info("Whatsapp URL: " + newTabUrl + " with Response code:" + responseCode);
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
					"Whatsapp URL: " + newTabUrl + " with Response code:" + responseCode, driver, getScenarioName());

			// Close the new tab and switch back to the main window
			driver.close();
			driver.switchTo().window(mainWindowHandle);

			assertAll(softly);

		} catch (Exception e) {
			logger.info("Exception occurred at validateTopDestination()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	private void assertAll(SoftAssertions softly) {
		try {
			softly.assertAll();
		} catch (AssertionError e) {
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}
	}

	public void verifyIFooterFunctionality() throws IOException {
		try {
			SoftAssertions softly = new SoftAssertions();
			String url = driver.getCurrentUrl();

			softly.assertThat(driver.findElement(By.cssSelector("img[class='temp3-logo-img']")).isDisplayed())
					.as("Checking the logo is loaded - Company logo").isTrue();
			softly.assertThat(new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector(
					"img[src='https://aosedge.b-cdn.net/prod/ThemeImages/2105311837100000031/2202081436563699070/Icons/2312231116374533428.png']"))))
					.as("Checking the logo is loaded - ssl").isTrue();
			softly.assertThat(new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector(
					"img[src='https://aosedge.b-cdn.net/prod/ThemeImages/2105311837100000031/2202081436563699070/Icons/2312231116374532466.png']"))))
					.as("Checking the logo is loaded - Paypal").isTrue();
			softly.assertThat(new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector(
					"img[src='https://aosedge.b-cdn.net/prod/ThemeImages/2105311837100000031/2202081436563699070/Icons/2312231116374532466.png']"))))
					.as("Checking the logo is loaded - Secure").isTrue();
			softly.assertThat(new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector(
					"img[src='https://edge.amadeusonlinesuite.com/prod/ThemeImages/2105311837100000031/2202081436563699070/sm/1721635050451.jpg']"))))
					.as("Checking the logo is loaded - X").isTrue();

			softly.assertThat(new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector(
					"img[src='https://edge.amadeusonlinesuite.com/prod/ThemeImages/2105311837100000031/2202081436563699070/sm/1721634755269.png']"))))
					.as("Checking the logo is loaded - Facebook").isTrue();

			softly.assertThat(new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector(
					"img[src='https://edge.amadeusonlinesuite.com/prod/ThemeImages/2105311837100000031/2202081436563699070/sm/1721635573027.png']"))))
					.as("Checking the logo is loaded - Instagram").isTrue();

			logger.info("Current url is: " + url);
			GenericActions.clickElement(homePage.termsCondition, "Clicking the Terms and condition", logger);
			logger.info("updated url is: " + driver.getCurrentUrl());
			softly.assertThat(driver.getCurrentUrl()).contains("Content/termsandconditions")
					.withFailMessage("Expected string to contain '%s', but it did not.", "Content/termsandconditions");
			driver.navigate().back();

			logger.info("Current url is: " + url);
			GenericActions.clickElement(homePage.instagram, "Clicking the instagram", logger);
			logger.info("updated url is: " + driver.getCurrentUrl());
			driver.navigate().back();

			logger.info("Current url is: " + url);
			GenericActions.clickElement(homePage.facebook, "Clicking the Facebook", logger);
			logger.info("updated url is: " + driver.getCurrentUrl());
			driver.navigate().back();

			logger.info("Current url is: " + url);
			GenericActions.clickElement(homePage.x, "Clicking the x", logger);
			logger.info("updated url is: " + driver.getCurrentUrl());

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

		} catch (Exception e) {
			logger.info("Exception occured at verifyIFooterFunctionality()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}
	}

}
