package com.aos.implementation;

import java.io.IOException;
import java.util.List;
import javax.xml.xpath.XPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aos.base.TestRunner;
import com.aos.utils.LogEvent;
import com.aos.utils.StringUtils;
import com.aventstack.extentreports.Status;

public class CmsImplementation extends TestRunner {

	public static final Logger logger = LogManager.getLogger(CmsImplementation.class);

	public void validatePackage() {
		try {
			SoftAssertions softly = new SoftAssertions();
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Locate the package container elements using JavaScript
			List<WebElement> packageContainer = (List<WebElement>) js
					.executeScript("return document.querySelectorAll('.hybridContent');");
			WebElement durationContainer = (WebElement) js
					.executeScript("return document.querySelector('.hybridDuration');");

			for (WebElement card : packageContainer) {
				// Log package title
				String packageTitle = (String) js.executeScript("return arguments[0].querySelector('h4').innerText;",
						card);
				logger.info("Package title: " + packageTitle);

				// Create an Actions object
				Actions actions = new Actions(driver);

				// Perform the hover action
				WebElement priceElement = (WebElement) js
						.executeScript("return document.evaluate(\"//*[contains(text(), 'AED')]\","
								+ " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");
				actions.moveToElement(priceElement).perform();
				Thread.sleep(1000);

				LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Validating cards for Package", driver,
						getScenarioName());

				// Validating description
				softly.assertThat(packageTitle.length()).isGreaterThan(0).as("Validating if there's description");

				// Checking Day and Night icons
				Long svgCount = (Long) js.executeScript("return arguments[0].querySelectorAll('svg').length;",
						durationContainer);
				softly.assertThat(svgCount).isEqualTo(2).as("Checking Day and Night icons are there");

				// Validating hybridDesc element
				String hybridDescText = (String) js.executeScript(
						"return arguments[0].querySelector('.hybridDesc.ng-star-inserted span:nth-child(2)').innerText;",
						card);
				softly.assertThat(hybridDescText.length()).isGreaterThan(0);

				// Validating hybridHotAdd elements
				Long hotAddCount = (Long) js
						.executeScript("return arguments[0].querySelectorAll('.hybridHotAdd').length;", card);
				softly.assertThat(hotAddCount).isGreaterThan(0);

				// Validating Amenities
				List<WebElement> amenities = (List<WebElement>) js.executeScript(
						"return arguments[0].querySelector('.hybridList.ng-star-inserted').querySelectorAll('li');",
						card);
				for (WebElement element : amenities) {
					String amenityText = (String) js.executeScript("return arguments[0].innerText;", element);
					softly.assertThat(amenityText.length()).isGreaterThan(1)
							.as("Validating Amenities - " + amenityText);
				}

				// Checking price
				String priceText = (String) js
						.executeScript("return arguments[0].querySelector('.hybridCardFoot h3').innerText;", card);
				softly.assertThat(StringUtils.ConvertStringToDouble(priceText)).isGreaterThan(0)
						.as("Checking price is greater than 0");

				// Per person info validation
				String perPersonInfo = (String) js
						.executeScript("return arguments[0].querySelector('.hybridCardFoot p').innerText;", card);
				softly.assertThat(perPersonInfo.length()).isGreaterThan(0).as("Per person info validation");
			}

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}
		} catch (Exception e) {
			logger.info("Exception occurred at validatePackage() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}
	}

	public void validatePopularHotels() throws InterruptedException {
		try {

			SoftAssertions softly = new SoftAssertions();
			// Locate the package container elements
			JavascriptExecutor js = (JavascriptExecutor) driver;
			List<WebElement> packageContainer = ((List<WebElement>) js
					.executeScript("return document.querySelectorAll('.popGridWrap .popCard.ng-star-inserted');"));

			// Loop through each package container and validate
			for (int i = 0; i < packageContainer.size(); i++) {
				WebElement currentPackage = packageContainer.get(i);
				WebElement location = (WebElement) js.executeScript("return arguments[0].querySelector('.location');",
						currentPackage);
				WebElement price = (WebElement) js.executeScript("return arguments[0].querySelector('.price');",
						currentPackage);
				WebElement hotelName = (WebElement) js.executeScript("return arguments[0].querySelector('.hotelName');",
						currentPackage);
				WebElement stay = (WebElement) js.executeScript("return arguments[0].querySelector('.stay');",
						currentPackage);
				Long svgCount = (Long) js.executeScript(
						"return arguments[0].querySelectorAll('.rating.ng-star-inserted svg').length;", currentPackage);

				softly.assertThat(location.getText().length()).isGreaterThan(0)
						.as("Validating if there's location in package " + (i + 1));

				// Create an Actions object
				Actions actions = new Actions(driver);

				// Perform the hover action
				actions.moveToElement(currentPackage.findElement(By.xpath("//*[@class='rating ng-star-inserted']")))
						.perform();
				if (i % 4 == 0) {
					Thread.sleep(1000);
				}

				LogEvent.logEventWithScreenshot(
						getExtentTest(), Status.INFO, "Validating cards for Destination - " + location.getText()
								+ " and Price - " + StringUtils.ConvertStringToDouble(price.getText()),
						driver, getScenarioName());
				softly.assertThat(hotelName.getText().length()).isGreaterThan(0).as("Validating if there's Hotel Name");

				softly.assertThat(svgCount).isGreaterThan(0)
						.as("Validating if there is at least 1 star in package " + (i + 1));

				softly.assertThat(StringUtils.ConvertStringToDouble(price.getText())).isGreaterThan(0)
						.as("Checking price is greater than 0");
				softly.assertThat(stay.getText().length()).isGreaterThan(0).as("Per person info validation");

			}

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

		} catch (AssertionError e) {
			logger.info("Exception occured at validateTopDestination()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}
/////////////////////////////////////////////////////////

	public void validateTopDestination() throws InterruptedException {
		try {

			SoftAssertions softly = new SoftAssertions();
			// Locate the package container elements
			List<WebElement> packageContainer = driver
					.findElements(By.xpath(".//*[@class='tripIdea2_card ng-star-inserted']"));

			// Loop through each package container and validate
			for (int i = 0; i < packageContainer.size(); i++) {

				WebElement currentPackage = packageContainer.get(i);
				// Create an Actions object
				Actions actions = new Actions(driver);

				// Perform the hover action
				actions.moveToElement(currentPackage.findElement(By.xpath(".//*[@class='tripIdea2_cardLoc']")))
						.perform();
				if (i % 4 == 0) {
					Thread.sleep(1000);
				}
				softly.assertThat(currentPackage.findElement(By.xpath(".//*[@class='tripIdea2_cardTripType']"))
						.getText().length()).isGreaterThan(0).as("Validating if there's trip type in package");
				softly.assertThat(
						currentPackage.findElement(By.xpath(".//*[@class='tripIdea2_cardLoc']")).getText().length())
						.isGreaterThan(0).as("Validating if there's location in package " + (i + 1));
				

				LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
						"Validating cards for Location - " + currentPackage
								.findElement(By.xpath("//*[@class='tripIdea2_locationText']")).getText(),
						driver, getScenarioName());

			}

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

		} catch (AssertionError e) {
			logger.info("Exception occured at validateTopDestination()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

}