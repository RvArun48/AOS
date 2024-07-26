package com.aos.implementation;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.base.TestRunner;
import com.aos.model.BookTicketDTO;
import com.aos.model.CommonDTO;
import com.aos.pageObjects.HomePage;
import com.aos.pageObjects.PassengerDetailsPage;
import com.aos.pageObjects.SearchResultsPage;
import com.aos.specification.AosSpecification;
import com.aos.utils.DateUtil;
import com.aos.utils.GenericActions;
import com.aos.utils.LogEvent;
import com.aventstack.extentreports.Status;

public class SearchFormImplementation extends TestRunner {

	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	SearchResultsPage searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
	PassengerDetailsPage passengerDetailsPage = PageFactory.initElements(driver, PassengerDetailsPage.class);

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	public static final Logger logger = LogManager.getLogger(SearchFormImplementation.class);

	JavascriptExecutor executor = (JavascriptExecutor) driver;

	public void i_enter_the_source_as() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			logger.info("Enter the origin input: " + bookTicketDTO.getFrom());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.from));
			homePage.from.click();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.fromInput));
			homePage.fromInput.sendKeys(bookTicketDTO.getFrom());
			homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getFrom());
		} catch (Exception e) {
			logger.info("Exception occurred at i_enter_the_source_as() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void i_enter_the_destination_as() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.to));
			homePage.to.click();

			logger.info("Enter the Destination input: " + bookTicketDTO.getTo());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.toInput));
			homePage.toInput.sendKeys(bookTicketDTO.getTo());

			homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getTo());

		} catch (Exception e) {
			logger.info("Exception occurred at i_enter_the_destination_as() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void i_enter_the_departure_date_as() {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.departureCalendar));
			homePage.departureCalendar.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: "
					+ DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",

					DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "d"));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Select the departure date", driver,
					getScenarioName());

		} catch (Exception e) {
			logger.info("Exception occurred at i_enter_the_departure_date_as() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}
	}

	public void i_select_the_flight_category_as() {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			logger.info("Selecting the class: " + bookTicketDTO.getPassengerClass());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.passangerClass));
			homePage.passangerClass.click();

			if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("Economy")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.passengerClass_Economy));
				homePage.passengerClass_Economy.click();
			} else if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("Premimum Economy")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.passengerClass_PremiumEconomy));
				homePage.passengerClass_PremiumEconomy.click();
			} else if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("Business Class")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.passengerClass_BusinessClass));
				homePage.passengerClass_BusinessClass.click();
			} else if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("First class")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.passengerClass_FirstClass));
				homePage.passengerClass_FirstClass.click();
			}
		} catch (Exception e) {
			logger.info("Exception occurred at i_select_the_flight_category_as() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}
	}

	public void i_click_on_the_search_button() {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(homePage.searcFlightButton));
			homePage.searcFlightButton.click();
			Thread.sleep(10000);

		} catch (Exception e) {
			logger.info("Exception occurred at i_click_on_the_search_button() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_select_the_trip_as() throws IOException {

		try {

			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			if (bookTicketDTO.getTripSelect().equalsIgnoreCase("One Way")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.oneWayElementGroup));
				homePage.oneWayElementGroup.click();
			} else if (bookTicketDTO.getTripSelect().equalsIgnoreCase("Round Trip")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.roundTripElementGroup));
				homePage.roundTripElementGroup.click();
			} else if (bookTicketDTO.getTripSelect().equalsIgnoreCase("multicity")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.multiCityElementGroup));
				homePage.multiCityElementGroup.click();
			} else if (bookTicketDTO.getTripSelect().equalsIgnoreCase("Multi City (3 Segment)")) {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.multiCitySegmentElementGroup));
				homePage.multiCitySegmentElementGroup.click();
			}
		} catch (Exception e) {
			logger.info("Exception occurred at I_select_the_trip_as() -> " + e.toString());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}
	}

	public void I_add_passengers() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			int count = 0;
			while (count < bookTicketDTO.getAdultCount() - 1) {

				wait.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("(//*[@class='empireF_travelerCountCard']//child::button)[2]"))));

				executor.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("(//*[@class='empireF_travelerCountCard']//child::button)[2]")));

				count++;
			}

			count = 0;
			while (count < bookTicketDTO.getChildCount()) {

				wait.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("(//*[@class='empireF_travelerCountCard']//child::button)[4]"))));

				executor.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("(//*[@class='empireF_travelerCountCard']//child::button)[4]")));

				count++;
			}
			count = 0;
			while (count < bookTicketDTO.getInfantCount()) {

				wait.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("(//*[@class='empireF_travelerCountCard']//child::button)[6]"))));

				executor.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("(//*[@class='empireF_travelerCountCard']//child::button)[6]")));
				count++;
			}
		} catch (Exception e) {
			logger.info("Exception occurred at I_add_passengers() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void i_add_advance_search_options() {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.advanceSearchElementGroup));
			homePage.advanceSearchElementGroup.click();

			if (bookTicketDTO.getBaggageOnly()) {
				logger.info("clicking on baggage only");
				homePage.baggageOnlyElementGroup.click();
			}
			if (bookTicketDTO.getIsDirectFlight()) {
				logger.info("clicking on Direct flight");
				homePage.directFlightsElementGroup.click();
			}
			if (bookTicketDTO.getIsRefundable()) {
				logger.info("clicking on Refundable");
				homePage.refundableElementGroup.click();
			}

			

		} catch (Exception e) {
			logger.info("i_add_advance_search_options() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void i_enter_the_preferred_airlines() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.advanceSearchElementGroup));
//			homePage.advanceSearchElementGroup.click();
			String[] preferredAirLines = bookTicketDTO.getPreferredAirlines().split(",");
			for (String preferredAirLine : preferredAirLines) {
				GenericActions.sendKeys(homePage.preferredAirlineElementGroup, preferredAirLine.trim(),
						"Entering preferred_airlines:" + preferredAirLine, logger);
				homePage.getElementByXpath(driver, "(//*[@role='listbox']//child::span[text()='${token}'])[1]",
						preferredAirLine.trim());

			}

			

			Thread.sleep(10000);

		} catch (Exception e) {
			logger.info("i_enter_the_preferred_airlines() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

}
