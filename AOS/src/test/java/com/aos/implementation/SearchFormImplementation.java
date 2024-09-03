package com.aos.implementation;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.base.TestRunner;
import com.aos.model.BookTicketDTO;
import com.aos.model.CommonDTO;
import com.aos.model.PassengerDetailsDTO;
import com.aos.pageObjects.HomePage;
import com.aos.pageObjects.PassengerDetailsPage;
import com.aos.pageObjects.SearchResultsPage;
import com.aos.specification.AosSpecification;
import com.aos.utils.CommonUtils;
import com.aos.utils.DateAndTimeUtil;
import com.aos.utils.GenericActions;
import com.aos.utils.JsonToGson;
import com.aos.utils.LogEvent;
import com.aos.utils.StringUtils;
import com.aventstack.extentreports.Status;
import com.google.common.reflect.TypeToken;

public class SearchFormImplementation extends TestRunner {

	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	SearchResultsPage searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
	PassengerDetailsPage passengerDetailsPage = PageFactory.initElements(driver, PassengerDetailsPage.class);

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	public static final Logger logger = LogManager.getLogger(SearchFormImplementation.class);

	JavascriptExecutor executor = (JavascriptExecutor) driver;
	public Object I_click;

	public void i_enter_the_source_as() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			logger.info("Enter the origin input: " + bookTicketDTO.getFrom());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.from));
			homePage.from.click();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.fromInput));
			homePage.fromInput.sendKeys(bookTicketDTO.getFrom());
			homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getFrom(), homePage.fromToContainer_1);

		} catch (Exception e) {
			logger.info("Exception occurred at i_enter_the_source_as() -> " + e.toString());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, e.getLocalizedMessage(), driver,
					getScenarioName());
		}

	}

	public void i_enter_the_destination_as() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.to));
			homePage.to.click();

//			logger.info("Enter the Destination input: " + bookTicketDTO.getTo());
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.toInput));
//			homePage.toInput.sendKeys(bookTicketDTO.getTo());
//			homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
//					bookTicketDTO.getTo());

			logger.info("Enter the Destination input: " + bookTicketDTO.getTo());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ToDropDown0']"))));
			driver.findElement(By.xpath("//input[@id='ToDropDown0']")).sendKeys(bookTicketDTO.getTo());
			Thread.sleep(1500);
			homePage.getElementByXpath(driver, "(//input[@id='ToDropDown0']//following::*[text()='${token}'])[1]",
					bookTicketDTO.getTo());

		} catch (Exception e) {
//			logger.info("Exception occurred at i_enter_the_destination_as() -> " + e.getMessage());
//			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, e.getLocalizedMessage(), driver, getScenarioName());
		}

	}

	public void i_enter_the_departure_date_as() {

		try {

			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, 0);");
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(homePage.departureCalendar));
			homePage.departureCalendar.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: "
					+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
					DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "d"));
			executor.executeScript("window.scrollTo(0, -350);");
			Thread.sleep(5000);

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
			} else if (bookTicketDTO.getTripSelect().equalsIgnoreCase("Multi City")) {
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

			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Apply')]"))));
			driver.findElement(By.xpath("//button[contains(text(),'Apply')]"));

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
//			
			String[] preferredAirLines = bookTicketDTO.getPreferredAirlines().split(",");
			for (String preferredAirLine : preferredAirLines) {
				GenericActions.sendKeys(homePage.preferredAirlineElementGroup, preferredAirLine.trim(),
						"Entering preferred_airlines:" + preferredAirLine, logger);
				homePage.getElementByXpath(driver, "(//*[@role='listbox']//child::span[text()='${token}'])[1]",
						preferredAirLine.trim());

				logger.info("Checking the Preferred Airlines as per search");
				if (!searchResultsPage.airlinePreferanceValidation.getText()
						.contains(bookTicketDTO.getRelevantKeywordPreferredAirlines())) {
					LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL,
							"Search result Preferred Airlines is not relevant to the search term", driver,
							getScenarioName());
					Assert.assertTrue("Search result Preferred Airlines is not relevant to the search term: ", false);
				} else {
					LogEvent.logEventWithScreenshot(getExtentTest(), Status.PASS,
							"Checkpoint -1: Search result Preferred Airlines is relevant to the search term", driver,
							getScenarioName());
				}

			}

			Thread.sleep(10000);

		} catch (Exception e) {
			logger.info("i_enter_the_preferred_airlines() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_change_the_currency_type() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			GenericActions.clickElement(homePage.currencyIdElementGroup, "Clicking on Currency", logger);
			// Thread.sleep(5000);

			homePage.getElementByXpath(driver,
					"//h4[text()='Currency']//following::div[contains(@class,'dropdown-item empire_dropdownItem') and contains(text(),'${token}')]",
					bookTicketDTO.getCurrency());

		} catch (Exception e) {
			logger.info("I_change_the_currency_type() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_change_the_language() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			GenericActions.clickElement(homePage.languageElementGroup, "Clicking on Language", logger);

			homePage.getElementByXpath(driver,
					"//h4[text()='Language']//following::button[contains(@class,'dropdown-item empire_dropdownItem') and contains(text(),'${token}')]",
					bookTicketDTO.getLanguage());

		} catch (Exception e) {
			logger.info("I_change_the_currency_type() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_enter_the_segment_two() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			logger.info("Enter the origin input: " + bookTicketDTO.getFromTwo());

//			wait.until(ExpectedConditions.elementToBeClickable(homePage.fromsegmenttwo));
//			homePage.fromsegmenttwo.click();
//
//			homePage.fromsegmenttwo.clear();
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.fromsegmenttwo));
//			homePage.fromsegmenttwo.sendKeys(bookTicketDTO.getFromTwo());
//			homePage.getElementByXpath(driver, "(//*[@class='dropdown-item fs_menuItem ng-star-inserted']/child::*[contains(text(),'${token}')])[1]",
//					bookTicketDTO.getFromTwo());

			logger.info("Enter the Destination input: " + bookTicketDTO.getToTwo());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ToDropDown1']"))));
			driver.findElement(By.xpath("//input[@id='ToDropDown1']")).sendKeys(bookTicketDTO.getToTwo());
			Thread.sleep(4000);
			homePage.getElementByXpath(driver, "(//input[@id='ToDropDown1']//following::*[text()='${token}'])[1]",
					bookTicketDTO.getToTwo());

//			try {
//				homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
//						bookTicketDTO.getToTwo());
//			} catch (Exception e) {
//				// TODO: handle exception
//			}

			wait.until(ExpectedConditions.elementToBeClickable(homePage.datesegmenttwo));
			homePage.datesegmenttwo.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateTwo()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: " + DateAndTimeUtil
					.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateTwo()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
					DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateTwo()), "d"));
//			executor.executeScript("window.scrollTo(0, -350);");
			Thread.sleep(2000);

		} catch (Exception e) {
			logger.info("Exception occurred at i_enter_the_source_as() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	private Object getElementByXPath(String string, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public void I_enter_the_segment_three() throws InterruptedException {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			JavascriptExecutor js = (JavascriptExecutor) driver;

			Thread.sleep(2000);
//		
//		homePage.fromsegmentthree.clear();
//		wait.until(ExpectedConditions.elementToBeClickable(homePage.fromsegmentthree));
//		homePage.fromsegmenttwo.sendKeys(bookTicketDTO.getFromThree());
//
//		homePage.getElementByXpath(driver, "(//*[@class='dropdown-item fs_menuItem ng-star-inserted']/child::*[contains(text(),'${token}')])[2]",
//				bookTicketDTO.getFromThree());

			wait.until(ExpectedConditions.elementToBeClickable(homePage.datesegmentthree));
			homePage.datesegmentthree.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateThree()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: " + DateAndTimeUtil
					.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateThree()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
					DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateThree()), "d"));

			Thread.sleep(2000);
			executor.executeScript("window.scrollTo(0, -100);");

			logger.info("Enter the Destination input: " + bookTicketDTO.getToTwo());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ToDropDown2']"))));
			driver.findElement(By.xpath("//input[@id='ToDropDown2']")).sendKeys(bookTicketDTO.getToThree());
			Thread.sleep(5000);
			homePage.getElementByXpath(driver, "(//input[@id='ToDropDown2']//following::*[text()='${token}'])[1]",
					bookTicketDTO.getToThree());

			Thread.sleep(5000);

		} catch (Exception e) {
			logger.info("Exception occurred at I_enter_the_Date_segment_three() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_enter_the_segment_four() throws InterruptedException {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(homePage.datesegmentfour));
			homePage.datesegmentfour.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateFour()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: " + DateAndTimeUtil
					.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateFour()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
					DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateFour()), "d"));

			logger.info("Enter the Destination input: " + bookTicketDTO.getToFour());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ToDropDown3']"))));
			driver.findElement(By.xpath("//input[@id='ToDropDown3']")).sendKeys(bookTicketDTO.getToFour());

			homePage.getElementByXpath(driver, "(//input[@id='ToDropDown3']//following::*[text()='${token}'])[1]",
					bookTicketDTO.getToFour());
			Thread.sleep(2000);

		} catch (Exception e) {
			logger.info("Exception occurred at I_enter_the_Date_segment_three() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_enter_the_segment_five() throws InterruptedException {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(homePage.datesegmentfive));
			homePage.datesegmentfive.click();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateFive()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: " + DateAndTimeUtil
					.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateFive()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
					DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateFive()), "d"));

			logger.info("Enter the Destination input: " + bookTicketDTO.getToFive());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ToDropDown4']"))));
			driver.findElement(By.xpath("//input[@id='ToDropDown4']")).sendKeys(bookTicketDTO.getToFive());

			homePage.getElementByXpath(driver, "(//input[@id='ToDropDown4']//following::*[text()='${token}'])[1]",
					bookTicketDTO.getToFive());
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.info("Exception occurred at I_enter_the_Date_segment_three() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_enter_the_segment_six() throws InterruptedException {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, 0);");

			Thread.sleep(2000);

			wait.until(ExpectedConditions.elementToBeClickable(homePage.datesegmentsix));
			homePage.datesegmentsix.click();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateSix()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: " + DateAndTimeUtil
					.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateSix()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
					DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateSix()), "d"));

			logger.info("Enter the Destination input: " + bookTicketDTO.getToSix());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ToDropDown5']"))));
			driver.findElement(By.xpath("//input[@id='ToDropDown5']")).sendKeys(bookTicketDTO.getToSix());

			homePage.getElementByXpath(driver, "(//input[@id='ToDropDown5']//following::*[text()='${token}'])[1]",
					bookTicketDTO.getToSix());
			Thread.sleep(5000);

			executor.executeScript("window.scrollTo(0, -450);");
		} catch (Exception e) {
			logger.info("Exception occurred at I_enter_the_Date_segment_three() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_click_the_add_another_city() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(homePage.addAnotherCity));
			homePage.addAnotherCity.click();
		} catch (Exception e) {
			logger.info("Exception occurred at I_click_the_add_another_city() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_click_the_flexible_data() {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			if (bookTicketDTO.getFlexibleDate()) {
				logger.info("clicking on Flexible Date");
				homePage.flxiableDateElementGroup.click();
			}

		} catch (Exception e) {
			logger.info("I_click_the_flexible_data() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_click_the_umrah_fare() {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			bookTicketDTO.getUmrahFare();
			logger.info("clicking on Umrah Fare");
			homePage.umrahFare.click();

		} catch (Exception e) {
			logger.info("I_click_the_flexible_data() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_enter_the_return_date() {
		try {

			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.returnCalander));
			homePage.returnCalander.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getReturnDate()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
					getScenarioName());
			logger.info("Selecting the Departure date: " + DateAndTimeUtil
					.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getReturnDate()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
					DateAndTimeUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getReturnDate()), "d"));

		} catch (Exception e) {
			logger.info("Exception occurred at i_enter_the_departure_date_as() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_edit_search() {
		try {

			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.editSearch));
			homePage.editSearch.click();

		} catch (Exception e) {
			logger.info("Exception occurred at I_click_on_the_edit_search() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_advance_search() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			CommonUtils.scrollDownAndUp(driver);
			List<WebElement> mainFlightListContainer = driver
					.findElements(By.xpath("//*[@class='empireFlight_listing-body']"));
			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());

			int validationLimitCount = 0;

			for (WebElement card : mainFlightListContainer) {

				if (validationLimitCount > 0) {
					break;
				}

				softly.assertThat(
						CommonUtils.checkImageLoad(driver.findElement(By.cssSelector("img[alt='Airline Logo']"))))
						.as("Checking the image is loaded - Airline Logo").isTrue();
				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']")).getText().length())
						.isGreaterThan(0);
				logger.info(
						"Fight Name " + card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']")).getText());
				try {
					softly.assertThat(
							card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText().length())
							.isGreaterThan(0);
					logger.info("LCC "
							+ card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText().length())
						.isGreaterThan(0);
				logger.info("Flight Start Time "
						+ card.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText());

				CommonDTO.getInstance().setFlightStartTime(StringUtils
						.extractTime(card.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText()));

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_airline-nameWrapper']")).getText().length())
						.isGreaterThan(0);
				logger.info("From and To "
						+ card.findElement(By.xpath("//*[@class='empireFlight_airline-nameWrapper']")).getText());

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_airline-date']")).getText().length())
						.isGreaterThan(0);
				logger.info("Date " + card.findElement(By.xpath("//*[@class='empireFlight_airline-date']")).getText());

				try {
					softly.assertThat(card
							.findElement(By
									.xpath("//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
							.getText().length()).isGreaterThan(0);
					logger.info("Stop " + card
							.findElement(By
									.xpath("//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
							.getText());

				} catch (Exception e) {
					// TODO: handle exception
				}

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_FlightCode']")).getText().length())
						.isGreaterThan(0);

				logger.info("Source: " + card.findElement(By.xpath("//*[@class='empireFlight_FlightCode']")).getText());

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_time include']")).getText().length())
						.isGreaterThan(0);
				logger.info("Time: " + card.findElement(By.xpath("//*[@class='empireFlight_time include']")).getText());

				softly.assertThat(card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
						.getText().length()).isGreaterThan(0);
				logger.info("Baggage Details: " + card
						.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']")).getText());

				softly.assertThat(card.findElement(By.xpath("//*[@class='empireFlight_Rbd include ng-star-inserted']"))
						.getText().length()).isGreaterThan(0);
				logger.info("Passenger Class: " + card
						.findElement(By.xpath("//*[@class='empireFlight_Rbd include ng-star-inserted']")).getText());
				try {
					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Available seat: " + card
							.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']")).getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

				softly.assertThat(card.findElement(By.xpath("//*[@class='empireFlight_amount ng-star-inserted']"))
						.getText().length()).isGreaterThan(0);
				logger.info("Currency and Amount: "
						+ card.findElement(By.xpath("//*[@class='empireFlight_amount ng-star-inserted']")).getText());

				softly.assertThat(card.findElement(By.xpath("//*[@class='empireF_installmentwrap ng-star-inserted']"))
						.getText().length()).isGreaterThan(0);
				logger.info("Installments: " + card
						.findElement(By.xpath("//*[@class='empireF_installmentwrap ng-star-inserted']")).getText());

				softly.assertThat(card
						.findElement(By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
						.getText().length()).isGreaterThan(0);
				logger.info("End Time: " + card
						.findElement(By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
						.getText());
				CommonDTO.getInstance().setFlightEndTime(StringUtils.extractTime(card
						.findElement(By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
						.getText()));

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_FlightCode empireFlight_DepartCode']"))
								.getText().length())
						.isGreaterThan(0);
				logger.info("Destinaton: "
						+ card.findElement(By.xpath("//*[@class='empireFlight_FlightCode empireFlight_DepartCode']"))
								.getText());

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='FareTypeBox ng-star-inserted']")).getText().length())
						.isGreaterThan(0);
				logger.info("Fare Option: "
						+ card.findElement(By.xpath("//*[@class='FareTypeBox ng-star-inserted']")).getText());
				try {
					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText().length())
							.isGreaterThan(0);
					logger.info("Refundable: "
							+ card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_details-text']")).getText().length())
						.isGreaterThan(0);
				logger.info("Flight Details: "
						+ card.findElement(By.xpath("//*[@class='empireFlight_details-text']")).getText());
				try {
					softly.assertThat(
							card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText().length())
							.isGreaterThan(0);
					logger.info("Departure Calander: "
							+ card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText());

				} catch (Exception e) {
					// TODO: handle exception
				}

				if (CommonDTO.getInstance().getBookTicketDTO().getIsDirectFlight()) {
					softly.assertThat(card
							.findElement(By
									.xpath("//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
							.getText().trim()).isEqualTo("Direct");
					logger.info("Verified that the Direct Flight details are in Direct");

				}
				if (CommonDTO.getInstance().getBookTicketDTO().getIsRefundable()) {
					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText().trim())
							.isEqualTo("Refundable");
					logger.info("Verified that the Refundable details are in Refundable");

				}

				if (CommonDTO.getInstance().getBookTicketDTO().getBaggageOnly()) {

					if (card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']")).getText()
							.trim().contains("Piece")) {
						logger.info("Verified that the baggage details are in Pieces");
					} else if (card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
							.getText().trim().contains("KG")) {
						logger.info("Verified that the baggage details are in KG");

					} else {
						softly.assertThat("Expected KG/Piece").isEqualTo(
								card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
										.getText());
						logger.info("Baggage details not found");
					}

				}
				validationLimitCount++;

			}

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_advance_search()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_flight_listing() {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			CommonUtils.scrollDownAndUp(driver);
			Thread.sleep(10000);
			List<WebElement> mainFlightListContainer = driver
					.findElements(By.xpath("//*[@class='empireFlight_main-box-listing ng-star-inserted']"));
			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());
			int validationLimitCount = 0;

			for (WebElement card : mainFlightListContainer) {

				if (validationLimitCount > 0) {
					break;
				}
				WebElement roundTripDetailsContainer = card
						.findElement(By.xpath("//*[@class='empireFlight-cardboxroundTrip']"));
				WebElement roundTripHeaderContainer = card
						.findElement(By.xpath("//*[@class='empireFlight-roundTripsubhead ng-star-inserted']"));

				// This loop validates Round trip header

				card.findElement(By.xpath("//*[@class='empireFlight_amount ng-star-inserted']"));

				softly.assertThat(card.findElement(By.xpath("(//*[@class='empireFlight_amount ng-star-inserted'])"))
						.getText().length()).isGreaterThan(0);
				logger.info("Currency and Amount "
						+ card.findElement(By.xpath("//*[@class='empireFlight_amount ng-star-inserted']")).getText());

				card.findElement(By.xpath("//*[@class='empireF_installmentwrap ng-star-inserted']"));

				softly.assertThat(card.findElement(By.xpath("(//*[@class='empireF_installmentwrap ng-star-inserted'])"))
						.getText().length()).isGreaterThan(0);
				logger.info("Installments " + " -> " + card
						.findElement(By.xpath("//*[@class='empireF_installmentwrap ng-star-inserted']")).getText());

				try {
					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref ng-star-inserted']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Refundable " + " -> "
							+ card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref ng-star-inserted']"))
									.getText());

				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_refund-text no_ref ng-star-inserted']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Not Refundable " + " -> "
							+ card.findElement(
									By.xpath("//*[@class='empireFlight_refund-text no_ref ng-star-inserted']"))
									.getText());

				} catch (Exception e) {
					// TODO: handle exception
				}

				softly.assertThat(card.findElement(By.xpath("//*[@class='empireFlight_details-text ng-star-inserted']"))
						.getText().length()).isGreaterThan(0);
				logger.info("Flight Details: " + card
						.findElement(By.xpath("//*[@class='empireFlight_details-text ng-star-inserted']")).getText());

				for (int i = 0; i <= 1; i++) {
					softly.assertThat(roundTripHeaderContainer
							.findElement(By.xpath("(//*[@class='empireFlight_airline-name'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Trip detail: " + (i + 1) + " -> "
							+ roundTripHeaderContainer
									.findElement(By.xpath("(//*[@class='empireFlight_airline-name'])[" + (i + 1) + "]"))
									.getText());

					if (i == 0) {
						roundTripHeaderContainer.findElement(By.xpath("//*[@class='empireFlight_origin']"));
						softly.assertThat(roundTripHeaderContainer
								.findElement(By.xpath("//*[@class='empireFlight_origin']")).getText().length())
								.isGreaterThan(0);
						logger.info("Label: " + roundTripHeaderContainer
								.findElement(By.xpath("//*[@class='empireFlight_origin']")).getText());
					}
					if (i == 1) {
						roundTripHeaderContainer.findElement(By.xpath("//*[@class='empireFlight_depart']"));
						softly.assertThat(roundTripHeaderContainer
								.findElement(By.xpath("//*[@class='empireFlight_depart']")).getText().length())
								.isGreaterThan(0);
						logger.info("Label: " + roundTripHeaderContainer
								.findElement(By.xpath("//*[@class='empireFlight_depart']")).getText());
					}

				}

				// This loop validates Round trip details

				for (int i = 0; i <= 1; i++) {
					WebElement tripContainer = roundTripDetailsContainer
							.findElement(By.xpath("(//*[@class='empireFlight_cardbox'])[" + (i + 1) + "]"));

					tripContainer.findElement(By.xpath("//*[@class='empireFlight_FlightNames']"));

					softly.assertThat(tripContainer
							.findElement(By.xpath("(//*[@class='empireFlight_FlightNames'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Flight Name Trip " + (i + 1) + " -> "
							+ tripContainer
									.findElement(By.xpath("(//*[@class='empireFlight_FlightNames'])[" + (i + 1) + "]"))
									.getText());
					/////////////////////////////////////////////////////////////////////////////////////////////////
					softly.assertThat(CommonUtils.checkImageLoad(
							driver.findElement(By.xpath("(//img[@alt='Airline Logo'])[" + (i + 1) + "]"))))
							.as("Checking the image is loaded - Airline Logo").isTrue();

					tripContainer.findElement(By.xpath("//*[@class='empireFlight_FlightTime']"));
					softly.assertThat(tripContainer
							.findElement(By.xpath("(//*[@class='empireFlight_FlightTime'])[" + (i + 1) + "]")).getText()
							.length()).isGreaterThan(0);
					logger.info("Flight Start Time " + (i + 1) + " -> "
							+ tripContainer.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText());

					CommonDTO.getInstance().setFlightStartTime(StringUtils.extractTime(
							card.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText()));

					tripContainer.findElement(By.xpath("//*[@class ='empireFlight_CityName']"));

					softly.assertThat(tripContainer
							.findElement(By.xpath("(//*[@class ='empireFlight_CityName'])[" + (i + 1) + "]")).getText()
							.length()).isGreaterThan(0);
					logger.info("From and To " + (i + 1) + " -> "
							+ tripContainer.findElement(By.xpath("//*[@class ='empireFlight_CityName']")).getText());

					tripContainer.findElement(By.xpath("//*[@class='empireFlight_airline-date']"));

					softly.assertThat(tripContainer
							.findElement(By.xpath("(//*[@class='empireFlight_airline-date'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Date " + (i + 1) + " -> "
							+ tripContainer.findElement(By.xpath("//*[@class='empireFlight_airline-date']")).getText());

					tripContainer.findElement(
							By.xpath("//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"));

					softly.assertThat(tripContainer.findElement(
							By.xpath("(//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted'])["
									+ (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Stop " + (i + 1) + " -> "
							+ tripContainer
									.findElement(By.xpath(
											"//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
									.getText());

					tripContainer.findElement(By.xpath("//*[@class='empireFlight_FlightCode']"));

					softly.assertThat(tripContainer
							.findElement(By.xpath("(//*[@class='empireFlight_FlightCode'])[" + (i + 1) + "]")).getText()
							.length()).isGreaterThan(0);
					logger.info("Source " + (i + 1) + " -> "
							+ tripContainer.findElement(By.xpath("//*[@class='empireFlight_FlightCode']")).getText());

					tripContainer.findElement(By.xpath("//*[@class='empireFlight_time include']"));

					softly.assertThat(tripContainer
							.findElement(By.xpath("(//*[@class='empireFlight_time include'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Time " + (i + 1) + " -> "
							+ tripContainer.findElement(By.xpath("//*[@class='empireFlight_time include']")).getText());

					try {
						tripContainer.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"));

						softly.assertThat(tripContainer
								.findElement(By.xpath(
										"(//*[@class='empireFlight_time include ng-star-inserted'])[" + (i + 1) + "]"))
								.getText().length()).isGreaterThan(0);
						logger.info("Baggage Details " + (i + 1) + " -> "
								+ tripContainer
										.findElement(
												By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
										.getText());

					} catch (Exception e) {
						// TODO: handle exception
					}

					tripContainer.findElement(By.xpath("//*[@class='empireFlight_Rbd include ng-star-inserted']"));

					softly.assertThat(tripContainer
							.findElement(By.xpath(
									"(//*[@class='empireFlight_Rbd include ng-star-inserted'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Passenger Class " + (i + 1) + " -> "
							+ tripContainer
									.findElement(By.xpath("//*[@class='empireFlight_Rbd include ng-star-inserted']"))
									.getText());

					tripContainer.findElement(
							By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"));

					softly.assertThat(tripContainer.findElement(By.xpath(
							"(//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("End Time " + (i + 1) + " -> " + tripContainer
							.findElement(
									By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
							.getText());
					CommonDTO.getInstance().setFlightStartTime(StringUtils.extractTime(card
							.findElement(
									By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
							.getText()));

					tripContainer
							.findElement(By.xpath("//*[@class='empireFlight_FlightCode empireFlight_DepartCode']"));
					softly.assertThat(tripContainer
							.findElement(By.xpath(
									"(//*[@class='empireFlight_FlightCode empireFlight_DepartCode'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Destinaton " + (i + 1) + " -> "
							+ tripContainer
									.findElement(
											By.xpath("//*[@class='empireFlight_FlightCode empireFlight_DepartCode']"))
									.getText());

					tripContainer.findElement(By.xpath("//*[@class='FareTypeBox ng-star-inserted']"));
					softly.assertThat(tripContainer
							.findElement(By.xpath("(//*[@class='FareTypeBox ng-star-inserted'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Fare Option " + (i + 1) + " -> " + tripContainer
							.findElement(By.xpath("//*[@class='FareTypeBox ng-star-inserted']")).getText());

					/////////////////////////////////////////////////////////////////////////////////////////////////

				}

				try {

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText().length())
							.isGreaterThan(0);
					logger.info("LCC "
							+ card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText());

				} catch (Exception e) {
					// TODO: handle exception
				}

				try {

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Available seat: " + card
							.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']")).getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {

					softly.assertThat(
							card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText().length())
							.isGreaterThan(0);
					logger.info("Departure Calander: "
							+ card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText());

				} catch (Exception e) {
					// TODO: handle exception
				}

				if (CommonDTO.getInstance().getBookTicketDTO().getIsDirectFlight()) {
					softly.assertThat(card
							.findElement(By
									.xpath("//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
							.getText().trim()).isEqualTo("Direct");
					logger.info("Verified that the Direct Flight details are in Direct");

				}

				if (CommonDTO.getInstance().getBookTicketDTO().getIsRefundable()) {
					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref ng-star-inserted']"))
									.getText().trim())
							.isEqualTo("Refundable");
					logger.info("Verified that the Refundable details are in Refundable");

				}

				if (CommonDTO.getInstance().getBookTicketDTO().getBaggageOnly()) {

					if (card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']")).getText()
							.trim().contains("Piece")) {
						logger.info("Verified that the baggage details are in Pieces");
					} else if (card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
							.getText().trim().contains("KG")) {
						logger.info("Verified that the baggage details are in KG");

					} else {
						softly.assertThat("Expected KG/Piece").isEqualTo(
								card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
										.getText());
						logger.info("Baggage details not found");
					}

				}
				validationLimitCount++;
			}

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_advance_search()->" + e.toString());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_flight_listing_multi_segment_three() {

		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();
			Thread.sleep(10000);
			CommonUtils.scrollDownAndUp(driver);
			Thread.sleep(10000);
			List<WebElement> mainFlightListContainer = driver
					.findElements(By.xpath("//*[@class='empireF_MClistingBoxwrap']"));
			Thread.sleep(5000);
			List<WebElement> tabs = driver
					.findElements(By.xpath("//*[@class='empireF_McTabs ng-star-inserted']//child::li"));
			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());

			for (WebElement tab : tabs) {
				int validationLimitCount = 1;
				wait.until(ExpectedConditions.elementToBeClickable(tab));
				tab.click();
//				CommonUtils.scrollDownAndUp(driver);
				for (WebElement card : mainFlightListContainer) {

					if (validationLimitCount > 1) {
						break;
					}

					softly.assertThat(
							CommonUtils.checkImageLoad(driver.findElement(By.cssSelector("img[alt='Airline Logo']"))))
							.as("Checking the image is loaded - Airline Logo").isTrue();

					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']"))));
						CommonUtils.handleStaleElement(
								card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']")));
						softly.assertThat(
								card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']")).getText().length())
								.isGreaterThan(0);
						logger.info("Fight Name "
								+ card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']")).getText());
					} catch (Exception e) {

					}

					try {
						softly.assertThat(card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']"))
								.getText().length()).isGreaterThan(0);
						logger.info("LCC "
								+ card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText());

					} catch (Exception e) {
						// TODO: handle exception
					}

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText().length())
							.isGreaterThan(0);
					logger.info("Flight Start Time "
							+ card.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText());

					CommonDTO.getInstance().setFlightStartTime(StringUtils.extractTime(
							card.findElement(By.xpath("//*[@class='empireFlight_FlightTime']")).getText()));
					try {
						softly.assertThat(card
								.findElement(By.xpath(
										"//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
								.getText().length()).isGreaterThan(0);
						logger.info("Stop " + card
								.findElement(By.xpath(
										"//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
								.getText());
					} catch (Exception e) {
						softly.assertThat(
								card.findElement(By.xpath("//*[@class='empireFlight_stopvia ng-star-inserted']"))
										.getText().length())
								.isGreaterThan(0);
						logger.info("Stop "
								+ card.findElement(By.xpath("//*[@class='empireFlight_stopvia ng-star-inserted']"))
										.getText());
					}

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_FlightCode']")).getText().length())
							.isGreaterThan(0);

					logger.info(
							"Source: " + card.findElement(By.xpath("//*[@class='empireFlight_FlightCode']")).getText());

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_time include']")).getText().length())
							.isGreaterThan(0);
					logger.info(
							"Time: " + card.findElement(By.xpath("//*[@class='empireFlight_time include']")).getText());

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Baggage Details: "
							+ card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
									.getText());

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_Rbd include ng-star-inserted']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Passenger Class: "
							+ card.findElement(By.xpath("//*[@class='empireFlight_Rbd include ng-star-inserted']"))
									.getText());

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_amount']")).getText().length())
							.isGreaterThan(0);
					logger.info("Currency and Amount: "
							+ card.findElement(By.xpath("//*[@class='empireFlight_amount']")).getText());

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireF_installmentwrap ng-star-inserted']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Installments: " + card
							.findElement(By.xpath("//*[@class='empireF_installmentwrap ng-star-inserted']")).getText());

					softly.assertThat(card
							.findElement(
									By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
							.getText().length()).isGreaterThan(0);
					logger.info("End Time: " + card
							.findElement(
									By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
							.getText());
					CommonDTO.getInstance().setFlightEndTime(StringUtils.extractTime(card
							.findElement(
									By.xpath("//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList']"))
							.getText()));

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='empireFlight_FlightCode empireFlight_DepartCode']"))
									.getText().length())
							.isGreaterThan(0);
					logger.info("Destinaton: " + card
							.findElement(By.xpath("//*[@class='empireFlight_FlightCode empireFlight_DepartCode']"))
							.getText());

					softly.assertThat(
							card.findElement(By.xpath("//*[@class='FareTypeBox ng-star-inserted']")).getText().length())
							.isGreaterThan(0);
					logger.info("Fare Option: "
							+ card.findElement(By.xpath("//*[@class='FareTypeBox ng-star-inserted']")).getText());

//				softly.assertThat(
//						card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText().length())
//						.isGreaterThan(0);
//				logger.info("Refundable: "
//						+ card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText());

					if (CommonDTO.getInstance().getBookTicketDTO().getIsDirectFlight()) {
						softly.assertThat(card
								.findElement(By.xpath(
										"//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
								.getText().trim()).isEqualTo("Direct");
						logger.info("Verified that the Direct Flight details are in Direct");

					}

					if (CommonDTO.getInstance().getBookTicketDTO().getBaggageOnly()) {

						if (card.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
								.getText().trim().contains("Piece")) {
							logger.info("Verified that the baggage details are in Pieces");
						} else if (card
								.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
								.getText().trim().contains("KG")) {
							logger.info("Verified that the baggage details are in KG");

						} else {
							softly.assertThat("Expected KG/Piece").isEqualTo(card
									.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
									.getText());
							logger.info("Baggage details not found");
						}

					}
					validationLimitCount++;
				}

			}

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_advance_search()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_flight_listing_multi_segment_six() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();
			Thread.sleep(10000);
			CommonUtils.scrollDownAndUp(driver);
			Thread.sleep(10000);

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

			// Retrieve main flight list containers
			List<WebElement> mainFlightListContainer = (List<WebElement>) jsExecutor
					.executeScript("return Array.from(document.querySelectorAll('.empireFlight_multicityBody'))");

			Thread.sleep(5000);

			// Retrieve tabs
			List<WebElement> tabs = (List<WebElement>) executor.executeScript(
					"return Array.from(document.querySelectorAll('.empireFlight_multicityCardMob.ng-star-inserted'))");

			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());

			for (WebElement mainFlight : mainFlightListContainer) {
				wait.until(ExpectedConditions.elementToBeClickable(mainFlight));

				for (WebElement tab : tabs) {
					// Check if image is loaded
					Boolean isImageLoaded = (Boolean) executor.executeScript(
							"return document.querySelector(\"img[alt='Airline Logo']\") != null && document.querySelector(\"img[alt='Airline Logo']\").complete && document.querySelector(\"img[alt='Airline Logo']\").naturalHeight > 0");
					softly.assertThat(isImageLoaded).as("Checking the image is loaded - Airline Logo").isTrue();

					// Get text and validate for various elements
					String flightName = (String) executor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityLogoNameContent').textContent.trim();",
							tab);
					softly.assertThat(flightName.length()).isGreaterThan(0);
					logger.info("Flight Name " + flightName);

					String lcc = "";
					try {
						lcc = (String) executor.executeScript(
								"return arguments[0].querySelector('.LCC_Wrapper.ng-star-inserted').textContent.trim();",
								tab);
						softly.assertThat(lcc.length()).isGreaterThan(0);
						logger.info("LCC " + lcc);
					} catch (Exception e) {
						// Handle exception
					}

					String flightStartTime = (String) executor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemTime').textContent.trim();",
							tab);
					softly.assertThat(flightStartTime.length()).isGreaterThan(0);
					logger.info("Flight Start Time " + flightStartTime);

					String tripContent = (String) executor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityCardTripContent').textContent.trim();",
							tab);
					softly.assertThat(tripContent.length()).isGreaterThan(0);
					logger.info("From and To  " + tripContent);

					String stop = "";

					try {
//						stop = (String) executor.executeScript(
//								"return arguments[0].querySelector('.empireFlight_stopvia.empireF_directionTxt.ng-star-inserted').textContent.trim();",
//								tab);
//						softly.assertThat(stop.length()).isGreaterThan(0);
//						logger.info("Stop " + stop);
					} catch (Exception e) {
						stop = (String) executor.executeScript(
								"return arguments[0].querySelector('.empireFlight_stopvia.ng-star-inserted').textContent.trim();",
								tab);
						softly.assertThat(stop.length()).isGreaterThan(0);
						logger.info("Stop " + stop);
					}

					String cityName = (String) executor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemCityName').textContent.trim();",
							tab);
					softly.assertThat(cityName.length()).isGreaterThan(0);
					logger.info("Source: " + cityName);

					String time = (String) executor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityTime').textContent.trim();",
							tab);
					softly.assertThat(time.length()).isGreaterThan(0);
					logger.info("Time: " + time);

//
//					String amountText = (String) executor.executeScript(
//						    "const element = arguments[0].querySelector('.empireFlight_amountWrapper > h2');" +
//						    "if (element) {" +
//						    "    return element.textContent.trim();" +
//						    "} else {" +
//						    "    console.warn('Element not found: .empireFlight_amountWrapper > h2');" +
//						    "    return '';" +
//						    "}", tab);
//						softly.assertThat(amountText.length()).isGreaterThan(0);
//						logger.info("Currency and Amount: " + amountText);

//
//					String installmentWrap = (String) executor.executeScript(
//							"return arguments[0].querySelector('.empireF_installmentwrap.ng-star-inserted').textContent.trim();",
//							tab);
//					softly.assertThat(installmentWrap.length()).isGreaterThan(0);
//					logger.info("Installments: " + installmentWrap);

					String endTime = (String) executor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemTime').textContent.trim();",
							tab);
					softly.assertThat(endTime.length()).isGreaterThan(0);
					logger.info("End Time: " + endTime);

					String destination = (String) executor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemCityName').textContent.trim();",
							tab);
					softly.assertThat(destination.length()).isGreaterThan(0);
					logger.info("Destination: " + destination);

//					String refundable = (String) executor.executeScript(
//							"return arguments[0].querySelector('.empireFlight_multicityRefundTxt.ref.ng-star-inserted').textContent.trim();",
//							tab);
//					softly.assertThat(refundable.length()).isGreaterThan(0);
//					logger.info("Refundable: " + refundable);
//
//					String flightDetails = (String) executor.executeScript(
//							"return arguments[0].querySelector('.btn-secondary.empireFlight_details-text').textContent.trim();",
//							tab);
//					softly.assertThat(flightDetails.length()).isGreaterThan(0);
//					logger.info("Flight Details: " + flightDetails);

//					if (CommonDTO.getInstance().getBookTicketDTO().getIsRefundable()) {
//						softly.assertThat(refundable.trim()).isEqualTo("Refundable");
//						logger.info("Verified that the Refundable details are in Refundable");
//					}
				}
			}

			Actions actions = new Actions(driver);

//				wait.until(ExpectedConditions.elementToBeClickable(homePage.installmentClick));
//				homePage.installmentClick.click();
//
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Click to Close Installment Clicked",
//						driver, getScenarioName());
//				Thread.sleep(2000);
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.CloseInstallmentClick));
//				homePage.CloseInstallmentClick.click();

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.stopFunctionality));
				homePage.stopFunctionality.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getStop());

//					softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			} catch (Exception e) {
				logger.info("Stop validation() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.refundableFunctionality));
				homePage.refundableFunctionality.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getSelectRefundable());

//					softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			} catch (Exception e) {
				logger.info("i_add_advance_search_options() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			try {

				logger.info("Selecting the filter for Airlines: " + bookTicketDTO.getFilterAirlines());
				wait.until(ExpectedConditions.elementToBeClickable(homePage.filterAirlines));
				homePage.filterAirlines.click();

				wait.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("(//*[@class='mdc-label' and contains(text(),'"
								+ bookTicketDTO.getFilterAirlines() + "')])[1]"))));
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
						"Select the airline filter" + bookTicketDTO.getFilterAirlines() + "", driver,
						getScenarioName());

				executor.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("(//*[@class='mdc-label' and contains(text(),'"
								+ bookTicketDTO.getFilterAirlines() + "')])[1]")));

			} catch (Exception e) {
				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

//				

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.moreFilters));
				homePage.moreFilters.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getAircraftTypes());

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getDeprtureStop());

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getDepartureAirport());

				wait.until(ExpectedConditions.elementToBeClickable(homePage.applyMoreFilterSix));
				homePage.applyMoreFilterSix.click();

				Thread.sleep(2000);
			} catch (Exception e) {
				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.cheapestFare));
				homePage.cheapestFare.click();
				softly.assertThat(homePage.cheapestFareAmount.getText().trim())
						.isEqualTo(homePage.currencyPriceValidationSix.getText().trim());
				Thread.sleep(1500);
				wait.until(ExpectedConditions.elementToBeClickable(homePage.fastestFare));
				homePage.fastestFare.click();
				softly.assertThat(homePage.fastestFareAmount.getText().trim())
						.isEqualTo(homePage.currencyPriceValidationSix.getText().trim());
				Thread.sleep(1500);
				wait.until(ExpectedConditions.elementToBeClickable(homePage.bestValueFare));
				homePage.bestValueFare.click();
				softly.assertThat(homePage.bestValueFareAmount.getText().trim())
						.isEqualTo(homePage.currencyPriceValidationSix.getText().trim());
				Thread.sleep(1500);

			} catch (Exception e) {
				logger.info("I checking the fare option() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			// checking low to high price
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
			driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
			driver.findElement(
					By.xpath("//*[contains(@class,'dropdown-item') and contains(text(),'Price - Low To High')]"))
					.click();

			List<WebElement> flightPrices = driver
					.findElements(By.xpath("//h2[@class='empireFlight_amount ng-star-inserted']"));
			List<Double> priceList = new ArrayList<>();

			for (WebElement element : flightPrices) {
				priceList.add(StringUtils.ConvertStringToDouble(element.getText()));
			}

			softly.assertThat(CommonUtils.isAscendingOrder(priceList)).info
					.description("Checking Price from Low to High");
			logger.info("is low to high->" + CommonUtils.isAscendingOrder(priceList));
			Thread.sleep(1500);

			// checking high to low price
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
			driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
			driver.findElement(
					By.xpath("//*[contains(@class,'dropdown-item') and contains(text(),'Price - High To Low')]"))
					.click();

			flightPrices = driver.findElements(By.xpath("//h2[@class='empireFlight_amount ng-star-inserted']"));
			priceList = new ArrayList<>();

			for (WebElement element : flightPrices) {
				priceList.add(StringUtils.ConvertStringToDouble(element.getText()));
			}

			softly.assertThat(CommonUtils.isAscendingOrder(priceList)).info
					.description("Checking Price from High to Low");
			logger.info("is high to low->" + CommonUtils.isDescendingOrder(priceList));
			Thread.sleep(2000);
//				checking Depart Duration
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
			driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
			driver.findElement(By.xpath("//button[contains(text(),' Depart - Duration Shortest')]")).click();

			List<WebElement> departDurationElements = driver
					.findElements(By.xpath("//span[@class='empireFlight_time include']"));
			List<Double> durationList = new ArrayList<>();

			// Extract text from each WebElement, convert to Double, and add to durationList
			for (WebElement element : departDurationElements) {
				try {
					String text = element.getText().trim();
					Double duration = Double.parseDouble(text);
					durationList.add(duration);
				} catch (NumberFormatException e) {
					// Handle the case where the text cannot be parsed into a Double
					System.err.println("Invalid number format: " + element.getText());
				}
			}
			logger.info("Depart - Duration Shortest->" + CommonUtils.isDepartDuration(durationList));
			Thread.sleep(2000);
//				checking Depart Time

			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
			driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
			driver.findElement(By.xpath("//button[contains(text(),' Depart - Time Earliest')]")).click();

			List<WebElement> departTimeElements = driver
					.findElements(By.xpath("//span[@class='empireFlight_time include']"));
			List<Double> timeList = new ArrayList<>();

			// Extract text from each WebElement, convert to Double, and add to durationList
			for (WebElement element : departTimeElements) {
				try {
					String text = element.getText().trim();
					Double time = Double.parseDouble(text);
					timeList.add(time);
					// softly.assertThat(true).isEqualTo(CommonUtils.isDepartDuration(durationList));
				} catch (NumberFormatException e) {
					// Handle the case where the text cannot be parsed into a Double
					System.err.println("Invalid number format: " + element.getText());
				}
			}
			logger.info("is depart low to high->" + CommonUtils.isDepartTime(timeList));

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_advance_search()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_flexi_calander() {
		try {

			SoftAssertions softly = new SoftAssertions();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='FFC-box ng-star-inserted']"))));

			softly.assertThat(driver.findElement(By.xpath("//*[@class='FFC-search-fare-price']")).getText().length())
					.isGreaterThan(0);
			logger.info(
					"Flight Price " + driver.findElement(By.xpath("//*[@class='FFC-search-fare-price']")).getText());
			WebElement containerElement = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("return document.querySelector('.empireF_PricingCalenRetuWrapper')");

			// Use JavaScript Executor to find the child element within the container
			WebElement flexiCalendar = (WebElement) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].querySelector('.FFC-search-fare-amount.FFC-search-fare-active-amount')",
					containerElement);

//			// Perform the mouse hover action using Actions class
			Actions actions = new Actions(driver);
			actions.moveToElement(flexiCalendar).perform();
			Thread.sleep(1000);
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Hovering over the calendar", driver,
					getScenarioName());

			flexiCalendar.click();

			Thread.sleep(5000);
		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_flexi_calander()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}
	}

	public void I_need_to_validate_installments() {
		try {

			SoftAssertions softly = new SoftAssertions();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.installmentClick));
			homePage.installmentClick.click();

			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Click to Close Installment Clicked", driver,
					getScenarioName());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.CloseInstallmentClick));
			homePage.CloseInstallmentClick.click();

			Thread.sleep(3000);

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_installments()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}
	}

	public void I_need_to_validate_time() {
		try {
			SoftAssertions softly = new SoftAssertions();
			Actions actions = new Actions(driver);

			homePage.timeFunctionality.click();
			Thread.sleep(5000);
			// Locate the slider element
			WebElement slider = driver.findElement(
					By.xpath("(//*[@class='ngx-slider-span ngx-slider-bar-wrapper ngx-slider-full-bar'])[1]"));
			// WebElement sliderRight =
			// driver.findElement(By.xpath("(//*[@class='ngx-slider-span
			// ngx-slider-bar-wrapper ngx-slider-right-out-selection'])[1]"));

			WebElement minPointer = driver.findElement(
					By.xpath("(//*[@class='ngx-slider-span ngx-slider-pointer ngx-slider-pointer-min'])[1]"));
			WebElement maxPointer = driver.findElement(
					By.xpath("(//*[@class='ngx-slider-span ngx-slider-pointer ngx-slider-pointer-max'])[1]"));

			// Determine the width of the slider
			int sliderWidth = slider.getSize().getWidth();

			// Define the desired minimum and maximum values
			int desiredMinValue = 25000; // Example minimum value
			int desiredMaxValue = 35000; // Example maximum value
			int minValue = 600; // Minimum possible value of the slider
			int maxValue = 84900; // Maximum possible value of the slider
			int sliderRange = maxValue - minValue;

			// Calculate the offset for the minimum pointer
			// Calculate the offset for the minimum pointer
			int minOffset = (desiredMinValue - minValue) * sliderWidth / sliderRange;

			// Move the minimum pointer
			// Actions actions = new Actions(driver);
			actions.clickAndHold(minPointer).moveByOffset(minOffset, 0).release().perform();

			// Recalculate the width of the slider after moving the minimum pointer
			sliderWidth = slider.getSize().getWidth();

			// Calculate the offset for the maximum pointer relative to its current position
			int maxOffset = (desiredMaxValue - minValue) * sliderWidth / sliderRange;
			int currentMaxPosition = maxPointer.getLocation().getX();
			int currentMinPosition = minPointer.getLocation().getX();
			int maxMoveOffset = maxOffset - (currentMaxPosition - currentMinPosition);

			Thread.sleep(1500);
			// Move the maximum pointer
			actions.clickAndHold(maxPointer).moveByOffset(maxMoveOffset, 0).release().perform();
			Thread.sleep(1000);
			LocalTime sliderMinTime = LocalTime.parse(driver
					.findElement(
							By.xpath("(//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-value'])[1]"))
					.getText());
			LocalTime sliderMaxTime = LocalTime.parse(driver
					.findElement(By.xpath("(//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-high'])[1]"))
					.getText());

			logger.info("sliderMinTime->" + sliderMinTime);
			logger.info("sliderMaxTime->" + sliderMaxTime);

			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-value'])[1]
			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-high'])[1]
			softly.assertThat(DateAndTimeUtil.compareTimes(CommonDTO.getInstance().getFlightStartTime(), sliderMinTime,
					CommonDTO.getInstance().getFlightEndTime(), sliderMaxTime));

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_time()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}
	}

	public void I_need_to_validate_price() {
		try {
			SoftAssertions softly = new SoftAssertions();
			Actions actions = new Actions(driver);

			homePage.priceFunctionality.click();
			Thread.sleep(1000);

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_price()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_stop() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			Actions actions = new Actions(driver);

			wait.until(ExpectedConditions.elementToBeClickable(homePage.stopFunctionality));
			homePage.stopFunctionality.click();

			homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getStop());
////		
//			if (CommonDTO.getInstance().getBookTicketDTO().getStop().equalsIgnoreCase("")) {
//
//				if (card.findElement(By.xpath("(//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted'])[1]"))
//						.getText().trim().contains("Direct")) {
//					logger.info("Verified that the Stop details are in Direct");
//				} else if (card
//						.findElement(By.xpath("(//*[@class='empireFlight_stopvia ng-star-inserted']/span)[1]"))
//						.getText().trim().contains("1")) {
//					logger.info("Verified that the Stop details are in 1stop");
//
//				}else if (card
//						.findElement(By.xpath("(//*[@class='empireFlight_stopvia ng-star-inserted']/span)[1]"))
//						.getText().trim().contains("2")) {
//					logger.info("Verified that the Stop details are in 2stop");
//
//				}
//				
////				
//			}

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_stop()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_duration() {
		try {
			SoftAssertions softly = new SoftAssertions();
			Actions actions = new Actions(driver);

			homePage.durationFunctionality.click();
			Thread.sleep(1000);

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_duration()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_airline() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			logger.info("Selecting the filter for Airlines: " + bookTicketDTO.getFilterAirlines());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.filterAirlines));
			homePage.filterAirlines.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]"))));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
					"Select the airline filter" + bookTicketDTO.getFilterAirlines() + "", driver, getScenarioName());

			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]")));

		} catch (Exception e) {
			logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_refundable() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.refundableFunctionality));
			homePage.refundableFunctionality.click();

			homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getSelectRefundable());

//			softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

		} catch (Exception e) {
			logger.info("I_need_to_validate_refundable() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_mor_filter() {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(homePage.moreFilters));
			homePage.moreFilters.click();

			Thread.sleep(2000);
		} catch (Exception e) {
			logger.info("I_click_on_the_mor_filter() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_departure_airport() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getDepartureAirport());

			Thread.sleep(2000);
		} catch (Exception e) {
			logger.info("I_need_to_validate_departure_airport() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_arrival_airport() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getDepartureAirport());

			Thread.sleep(2000);
		} catch (Exception e) {
			logger.info("I_need_to_validate_departure_airport() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_departure_stopover() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getDeprtureStop());

			Thread.sleep(2000);
		} catch (Exception e) {
			logger.info("I_need_to_validate_departure_airport() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_aircraft_types() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getAircraftTypes());

			Thread.sleep(2000);
		} catch (Exception e) {
			logger.info("I_need_to_validate_aircraft_types() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}
	}

	public void I_click_on_the_apply_button() {
		try {
			SoftAssertions softly = new SoftAssertions();
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			wait.until(ExpectedConditions.elementToBeClickable(homePage.applyMoreFilter));
			homePage.applyMoreFilter.click();

			Thread.sleep(2000);
		} catch (Exception e) {
			logger.info("I_click_on_the_apply_button() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_fare_option() {
		try {
			SoftAssertions softly = new SoftAssertions();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.cheapestFare));
			homePage.cheapestFare.click();
			softly.assertThat(homePage.cheapestFareAmount.getText().trim())
					.isEqualTo(homePage.currencyPriceValidation.getText().trim());
			Thread.sleep(1500);
			wait.until(ExpectedConditions.elementToBeClickable(homePage.fastestFare));
			homePage.fastestFare.click();
			softly.assertThat(homePage.fastestFareAmount.getText().trim())
					.isEqualTo(homePage.currencyPriceValidation.getText().trim());
			Thread.sleep(1500);
			wait.until(ExpectedConditions.elementToBeClickable(homePage.bestValueFare));
			homePage.bestValueFare.click();
			softly.assertThat(homePage.bestValueFareAmount.getText().trim())
					.isEqualTo(homePage.currencyPriceValidation.getText().trim());
			Thread.sleep(1500);

		} catch (Exception e) {
			logger.info("I checking the fare option() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_sortby_filter() throws InterruptedException {
		// checking low to high price
		SoftAssertions softly = new SoftAssertions();
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
		driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
		driver.findElement(By.xpath("//*[contains(@class,'dropdown-item') and contains(text(),'Price - Low To High')]"))
				.click();

		List<WebElement> flightPrices = driver
				.findElements(By.xpath("//h2[@class='empireFlight_amount ng-star-inserted']"));
		List<Double> priceList = new ArrayList<>();

		for (WebElement element : flightPrices) {
			priceList.add(StringUtils.ConvertStringToDouble(element.getText()));
		}

		softly.assertThat(CommonUtils.isAscendingOrder(priceList)).info.description("Checking Price from Low to High");
		logger.info("is low to high->" + CommonUtils.isAscendingOrder(priceList));

		// checking high to low price
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
		driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
		driver.findElement(By.xpath("//*[contains(@class,'dropdown-item') and contains(text(),'Price - High To Low')]"))
				.click();

		flightPrices = driver.findElements(By.xpath("//h2[@class='empireFlight_amount ng-star-inserted']"));
		priceList = new ArrayList<>();

		for (WebElement element : flightPrices) {
			priceList.add(StringUtils.ConvertStringToDouble(element.getText()));
		}

		softly.assertThat(CommonUtils.isAscendingOrder(priceList)).info.description("Checking Price from High to Low");
		logger.info("is high to low->" + CommonUtils.isDescendingOrder(priceList));

//		checking Depart Duration
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
		driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
		driver.findElement(
				By.xpath("//*[contains(@class,'dropdown-item') and contains(text(),' Depart - Duration Shortest ')]"))
				.click();

		List<WebElement> departDurationElements = driver
				.findElements(By.xpath("//span[@class='empireFlight_time include']"));
		List<Double> durationList = new ArrayList<>();

		// Extract text from each WebElement, convert to Double, and add to durationList
		for (WebElement element : departDurationElements) {
			try {
				String text = element.getText().trim();
				Double duration = Double.parseDouble(text);
				durationList.add(duration);
			} catch (NumberFormatException e) {
				// Handle the case where the text cannot be parsed into a Double
//				System.err.println("Invalid number format: " + element.getText());
			}
		}
		logger.info("Depart - Duration Shortest->" + CommonUtils.isDepartDuration(durationList));

//		checking Depart Time

		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
		driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
		driver.findElement(By.xpath("//button[contains(text(),' Depart - Time Earliest')]")).click();

		List<WebElement> departTimeElements = driver
				.findElements(By.xpath("//span[@class='empireFlight_time include']"));
		List<Double> timeList = new ArrayList<>();

		// Extract text from each WebElement, convert to Double, and add to durationList
		for (WebElement element : departTimeElements) {
			try {
				String text = element.getText().trim();
				Double time = Double.parseDouble(text);
				timeList.add(time);
				// softly.assertThat(true).isEqualTo(CommonUtils.isDepartDuration(durationList));
			} catch (NumberFormatException e) {
				// Handle the case where the text cannot be parsed into a Double
//			System.err.println("Invalid number format: " + element.getText());
			}
		}
		logger.info("is depart low to high->" + CommonUtils.isDepartTime(timeList));

		Thread.sleep(5000);

		try {
			softly.assertAll();
		} catch (AssertionError e) {
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_need_to_validate_more_fligt_option() {
		try {

//			Thread.sleep(5000);
//			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//
//			// Retrieve the list of flight elements
//			List<WebElement> flightsList = (List<WebElement>) executor.executeScript(
//			    "return Array.from(document.querySelectorAll('[role=\"radiogroup\"]'))");
//
//			// Log the number of flight cards
//			logger.info("card count->" + flightsList.size());
//
//			// Get the first card
//			WebElement firstCard = flightsList.get(0);
//
//			// Check if the "more flight options" button is visible and clickable
//			Boolean isMoreOptionsVisible = (Boolean) executor.executeScript(
//			    "const showMoreBtn = arguments[0].querySelector('.showmore');" +
//			    "return showMoreBtn && showMoreBtn.textContent.includes('more flight options') && window.getComputedStyle(showMoreBtn).display !== 'none';", firstCard);
//
//			if (isMoreOptionsVisible) {
//			    // Click the "more flight options" button
//			    executor.executeScript(
//			        "const showMoreBtn = arguments[0].querySelector('.showmore');" +
//			        "if (showMoreBtn) { showMoreBtn.click(); }", firstCard);
//
//			    Thread.sleep(3000); // Wait for the content to load
//
//			    // Retrieve the radio button list
//			    List<WebElement> radioButtonList = (List<WebElement>) executor.executeScript(
//			        "return Array.from(arguments[0].querySelectorAll('.mat-mdc-radio-touch-target'));", firstCard);
//
//			    // Check if the radio button with id 'mat-radio-3-input' is visible
//			    Boolean isRadioButtonVisible = (Boolean) executor.executeScript(
//			        "const radioButton = document.getElementById('mat-radio-3-input');" +
//			        "return radioButton && window.getComputedStyle(radioButton).display !== 'none';");
//
//			    if (isRadioButtonVisible) {
//			        // Click the radio button
//			        executor.executeScript(
//			            "const radioButton = document.getElementById('mat-radio-3-input');" +
//			            "if (radioButton) { radioButton.click(); }");
//
//			        // Wait for the "selectAirlinesbooking" element to be clickable
//			        WebElement selectAirlinesbooking = (WebElement) executor.executeScript(
//			            "return document.querySelector('#selectAirlinesbooking');");
//
//			        if (selectAirlinesbooking != null) {
//			            wait.until(ExpectedConditions.elementToBeClickable(selectAirlinesbooking));
//			            selectAirlinesbooking.click();
//			            Thread.sleep(1000);
//			        }
//			    }
//			}
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

			// Find elements with role 'radiogroup' using JavaScript
			List<WebElement> flightsList = (List<WebElement>) jsExecutor
					.executeScript("return Array.from(document.querySelectorAll('[role=\"radiogroup\"]'));");

			logger.info("card count->" + flightsList.size());

			WebElement firstCard = flightsList.get(0);

			// Check and click 'more flight options' button
			executor.executeScript("const firstCard = arguments[0];"
					+ "const showMoreButton = Array.from(firstCard.querySelectorAll('.showmore')).find(el => el.textContent.includes('more flight options'));"
					+ "if (showMoreButton && showMoreButton.style.display !== 'none' && showMoreButton.disabled !== true) {"
					+ "    showMoreButton.click();" + "}", firstCard);

			// Wait for the new elements to be available
			Thread.sleep(3000); // Consider replacing with a more robust wait if necessary

			// Find and click the second radio button
			executor.executeScript(
					"const radioButtons = Array.from(document.querySelectorAll('input.mdc-radio__native-control'));"
							+ "const secondRadioButton = radioButtons[1];"
							+ "if (secondRadioButton && secondRadioButton.style.display !== 'none' && secondRadioButton.disabled !== true) {"
							+ "    secondRadioButton.click();" + "}");

			// Wait for the 'selectAirlinesbooking' element to be clickable and click it
			executor.executeScript("const selectAirlinesbooking = document.querySelector('.selectAirlinesbooking');"
					+ "if (selectAirlinesbooking && selectAirlinesbooking.style.display !== 'none' && selectAirlinesbooking.disabled !== true) {"
					+ "    selectAirlinesbooking.click();" + "}");

			// Optional sleep if necessary
			Thread.sleep(1000);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void I_need_to_validate_flexi_calander_round_trip() throws InterruptedException {

		SoftAssertions softly = new SoftAssertions();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//*[@class='empireF_fareWrap hover_box ng-star-inserted']"))));

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_srchFarePrice']"))));

		softly.assertThat(driver.findElement(By.xpath("//*[@class='empireF_srchFarePrice']")).getText().length())
				.isGreaterThan(0);

		logger.info("Flight Price " + driver.findElement(By.xpath("//*[@class='empireF_srchFarePrice']")).getText());
		WebElement containerElement = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelectorAll('.empireF_PCRetuWrap')[1]");

		WebElement flexiCalendar = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return document.querySelector('.empireF_fareWrap.hover_box.ng-star-inserted');");

		// Perform the mouse hover action using Actions class
		Actions actions = new Actions(driver);
		actions.moveToElement(flexiCalendar).perform();
		Thread.sleep(1000);
		LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Hovering over the calendar", driver,
				getScenarioName());

		flexiCalendar.click();

		Thread.sleep(5000);

	}

	public void I_need_to_validate_more_fligt_option_round_trip() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		// Find elements with class 'empireFlight_main-box-listing ng-star-inserted'
		List<WebElement> flightsList = (List<WebElement>) executor.executeScript(
				"return Array.from(document.querySelectorAll('.empireFlight_main-box-listing.ng-star-inserted'));");

		logger.info("card count->" + flightsList.size());

		WebElement firstCard = flightsList.get(0);

		// Check and click 'more flight options' button
		executor.executeScript("const firstCard = arguments[0];"
				+ "const showMoreButton = Array.from(firstCard.querySelectorAll('.showmore')).find(el => el.textContent.includes('more flight options'));"
				+ "if (showMoreButton && showMoreButton.style.display !== 'none' && showMoreButton.disabled !== true) {"
				+ "    showMoreButton.click();" + "}", firstCard);

		// Wait for the new elements to be available
		try {
			Thread.sleep(3000); // Consider replacing with a more robust wait if necessary
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Find and click the third radio button
		executor.executeScript(
				"const radioButtons = Array.from(document.querySelectorAll('input.mdc-radio__native-control'));"
						+ "const thirdRadioButton = radioButtons[2];" + // Indexing starts from 0
						"if (thirdRadioButton && thirdRadioButton.style.display !== 'none' && thirdRadioButton.disabled !== true) {"
						+ "    thirdRadioButton.click();" + "}");

	}

	public void I_need_to_validate_fare_option_three_segment() {

		try {
			SoftAssertions softly = new SoftAssertions();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.cheapestFare));
			homePage.cheapestFare.click();
			softly.assertThat(homePage.cheapestFareAmount.getText().trim())
					.isEqualTo(homePage.currencyPriceValidationMultiThree.getText().trim());
			Thread.sleep(1500);
			wait.until(ExpectedConditions.elementToBeClickable(homePage.fastestFare));
			homePage.fastestFare.click();
			softly.assertThat(homePage.fastestFareAmount.getText().trim())
					.isEqualTo(homePage.currencyPriceValidationMultiThree.getText().trim());
			Thread.sleep(1500);
			wait.until(ExpectedConditions.elementToBeClickable(homePage.bestValueFare));
			homePage.bestValueFare.click();
			softly.assertThat(homePage.bestValueFareAmount.getText().trim())
					.isEqualTo(homePage.currencyPriceValidationMultiThree.getText().trim());
			Thread.sleep(1500);

		} catch (Exception e) {
			logger.info("I checking the fare option() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}
	}

	public void I_click_on_the_flight_details() throws InterruptedException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			logger.info("click flight details");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.clickFlightDetails));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click flight details", driver,
					getScenarioName());
			homePage.clickFlightDetails.click();
//			driver.get(driver.getCurrentUrl());
			executor.executeScript("window.performance.mark('panelStart');" + "let panelLoaded = false;"
					+ "const observer = new MutationObserver((mutations) => {" + "    mutations.forEach((mutation) => {"
					+ "        if (document.querySelector('.empireFlight_ItWrapper')) {"
					+ "            if (!panelLoaded) {" + "                panelLoaded = true;"
					+ "                window.performance.mark('panelEnd');" + "                observer.disconnect();"
					+ "                console.log('Panel loaded');" + "            }" + "        }" + "    });" + "});"
					+ "observer.observe(document.body, { childList: true, subtree: true });" + "return panelLoaded;");

			// Wait for the panel to be visible
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='empireFlight_ItWrapper']")));

			// Retrieve the panel load time
			Long loadTime = ((Double) executor
					.executeScript("const measures = window.performance.getEntriesByType('mark');"
							+ "const start = measures.find(mark => mark.name === 'panelStart');"
							+ "const end = measures.find(mark => mark.name === 'panelEnd');"
							+ "console.log('Start Time:', start ? start.startTime : 'Not Found');"
							+ "console.log('End Time:', end ? end.startTime : 'Not Found');"
							+ "return end && start ? end.startTime - start.startTime : -1;"))
					.longValue();

			logger.info("Page Load Time is " + loadTime + " milliseconds.");
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
					"Flight Details verification<b>(Page load time: " + loadTime + " ms)</b>", driver,
					AosSpecification.scenarioName);

			Thread.sleep(3000);
		} catch (Exception e) {
			logger.error("exception occur at I_click_on_the_flight_details" + e.toString());
			logger.info(e.getLocalizedMessage());
		}
	}

	public void I_need_to_validate_flight_details() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();
			int maxIndex = 1;
			if (bookTicketDTO.getTripSelect().toLowerCase().contains("round")) {
				maxIndex = 2;
			}

			Thread.sleep(10000);
			WebElement mainFlightListContainer = driver.findElement(
					By.xpath("(//*[contains(@class,'temp3Flight-roundTripsubhead fareOptionCardMobile')])"));

			int index = 1; // Or set it dynamically based on your logic

			// Check if index is within bounds of the list
			while (index <= maxIndex) {
				softly.assertThat(CommonUtils
						.checkImageLoad(driver.findElement(By.xpath("(//img[@alt='Airline Logo'])[" + index + "]"))))
						.as("Checking the image is loaded - Airline Logo").isTrue();

				softly.assertThat(mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_FlightNames'])[" + index + "]")).getText()
						.length()).isGreaterThan(0);
				logger.info("Flight Name " + mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_FlightNames'])[" + index + "]")).getText());

				try {
					softly.assertThat(mainFlightListContainer
							.findElement(By.xpath("(.//*[@class='LCC_Wrapper ng-star-inserted'])[" + index + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("LCC " + mainFlightListContainer
							.findElement(By.xpath("(.//*[@class='LCC_Wrapper ng-star-inserted'])[" + index + "]"))
							.getText());
				} catch (Exception e) {
					// Handle exception
				}

				softly.assertThat(mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_FlightTime'])[" + index + "]")).getText()
						.length()).isGreaterThan(0);
				logger.info("Flight Start Time " + mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_FlightTime'])[" + index + "]")).getText());

//			        softly.assertThat(
//			            mainFlightListContainer.findElement(By.xpath(".//*[@class='originName'][" + index + "]")).getText().length()
//			        ).isGreaterThan(0);
//			        logger.info("From and To " +
//			            mainFlightListContainer.findElement(By.xpath(".//*[@class='originName'][" + index + "]")).getText()
//			        );
//
//			        softly.assertThat(
//			            mainFlightListContainer.findElement(By.xpath(".//*[@class='empireFlight_airline-date'][" + index + "]")).getText().length()
//			        ).isGreaterThan(0);
//			        logger.info("Date " +
//			            mainFlightListContainer.findElement(By.xpath(".//*[@class='empireFlight_airline-date'][" + index + "]")).getText()
//			        );

				try {
					softly.assertThat(mainFlightListContainer.findElement(
							By.xpath("(.//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted'])["
									+ index + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Stop " + mainFlightListContainer.findElement(
							By.xpath("(.//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted'])["
									+ index + "]"))
							.getText());
				} catch (Exception e) {
					softly.assertThat(mainFlightListContainer
							.findElement(
									By.xpath("(.//*[@class='empireFlight_stopvia ng-star-inserted'])[" + index + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Stop " + mainFlightListContainer
							.findElement(
									By.xpath("(.//*[@class='empireFlight_stopvia ng-star-inserted'])[" + index + "]"))
							.getText());
				}

				softly.assertThat(mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_FlightCode'])[" + index + "]")).getText()
						.length()).isGreaterThan(0);
				logger.info("Source: " + mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_FlightCode'])[" + index + "]")).getText());

				softly.assertThat(mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_time include'])[" + index + "]")).getText()
						.length()).isGreaterThan(0);
				logger.info("Time: " + mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='empireFlight_time include'])[" + index + "]")).getText());

				softly.assertThat(mainFlightListContainer
						.findElement(
								By.xpath("(.//*[@class='empireFlight_Rbd include ng-star-inserted'])[" + index + "]"))
						.getText().length()).isGreaterThan(0);
				logger.info("Passenger Class: " + mainFlightListContainer
						.findElement(
								By.xpath("(.//*[@class='empireFlight_Rbd include ng-star-inserted'])[" + index + "]"))
						.getText());

				softly.assertThat(mainFlightListContainer.findElement(By.xpath(
						"(.//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList'])[" + index + "]"))
						.getText().length()).isGreaterThan(0);
				logger.info("End Time: " + mainFlightListContainer.findElement(By.xpath(
						"(.//*[@class='empireFlight_FlightTime empireFlight_additionalTimeList'])[" + index + "]"))
						.getText());

				softly.assertThat(mainFlightListContainer
						.findElement(By.xpath(
								"(.//*[@class='empireFlight_FlightCode empireFlight_DepartCode'])[" + index + "]"))
						.getText().length()).isGreaterThan(0);
				logger.info("Destination: " + mainFlightListContainer
						.findElement(By.xpath(
								"(.//*[@class='empireFlight_FlightCode empireFlight_DepartCode'])[" + index + "]"))
						.getText());

				softly.assertThat(mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='FareTypeBox ng-star-inserted'])[" + index + "]")).getText()
						.length()).isGreaterThan(0);
				logger.info("Fare Option: " + mainFlightListContainer
						.findElement(By.xpath("(.//*[@class='FareTypeBox ng-star-inserted'])[" + index + "]"))
						.getText());
				index++;
			}

//			 softly.assertThat(
//			            mainFlightListContainer.findElement(By.xpath("//*[@class='originName']")).getText().length()
//			        ).isGreaterThan(0);
//			        logger.info("From and To " +
//			            mainFlightListContainer.findElement(By.xpath(".//*[@class='originName']")).getText()
//			        );
//
//			        softly.assertThat(
//			            mainFlightListContainer.findElement(By.xpath(".//*[@class='empireFlight_airline-date']")).getText().length()
//			        ).isGreaterThan(0);
//			        logger.info("Date " +
//			            mainFlightListContainer.findElement(By.xpath(".//*[@class='empireFlight_airline-date']")).getText()
//			        );

			// try {
//					softly.assertThat(
//							card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText().length())
//							.isGreaterThan(0);
//					logger.info("Refundable: "
//							+ card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText());
//				} catch (Exception e) {
//					// TODO: handle exception
//				}

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_flight_details()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_fare_option_card() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

			int validationLimitCount = 0;

			List<WebElement> mainFlightListContainer = driver
					.findElements(By.xpath("//*[@class='empireF_foCardWrapper']"));
			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());

			for (WebElement card : mainFlightListContainer) {

				if (validationLimitCount > 3) {
					break;
				}

				softly.assertThat(card.findElement(By.xpath("(//*[@class='temp3flightfareoptionheadingWrap']/p)[1]"))
						.getText().length()).isGreaterThan(0);
				logger.info("Fare option " + card
						.findElement(By.xpath("(//*[@class='temp3flightfareoptionheadingWrap']/p)[1]")).getText());

				softly.assertThat(card
						.findElement(By.xpath("(//*[@class='fareoption_rate']/p)[" + (validationLimitCount + 1) + "]"))
						.getText().length()).isGreaterThan(0);
				logger.info("currency and price " + card
						.findElement(By.xpath("(//*[@class='fareoption_rate']/p)[" + (validationLimitCount + 1) + "]"))
						.getText());

				softly.assertThat(card
						.findElement(By.xpath("(//*[@class='fareoption_rate']/h4)[" + (validationLimitCount + 1) + "]"))
						.getText().length()).isGreaterThan(0);
				logger.info(
						"Frae option type " + card.findElement(By.xpath("//*[@class='fareoption_rate']/h4")).getText());

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail']/h4")).getText().length())
						.isGreaterThan(0);
				logger.info(
						"Baggege  " + card.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail']/h4")).getText());
				softly.assertThat(
						card.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail ng-star-inserted']/h4"))
								.getText().length())
						.isGreaterThan(0);
				logger.info("Included  " + card
						.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail ng-star-inserted']/h4")).getText());

				validationLimitCount++;
			}
			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.clickFareOption));
				homePage.clickFareOption.click();
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click fare option", driver,
						getScenarioName());
				logger.info("Click fare option -> " + homePage.clickFareOption.getText());
				logger.info("Currency fare option -> " + homePage.currencyFareOption.getText());
				softly.assertThat(homePage.clickFareOption.getText().trim())
						.isEqualTo(homePage.currencyFareOption.getText().trim());

//				String cabinBaggage = "//*[@class='cabinBaageFareoption ng-star-inserted']/p";
//				String checkedBaggage = "//span[@class='ng-star-inserted'][contains(text(), 'KG')]";
//
//				Thread.sleep(20000);
//				logger.info("click baggage");
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.clickBaggage));
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click baggage", driver,
//						getScenarioName());
//				homePage.clickBaggage.click();
//				Thread.sleep(3000);
//
//				softly.assertThat(homePage.cabinBaggage.getText().trim()).isEqualTo(cabinBaggage);
//				softly.assertThat(homePage.checkedBaggage.getText().trim()).isEqualTo(checkedBaggage);
//
//				Thread.sleep(1500);
//
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.clickFareOptionVal));
//
//				homePage.clickFareOptionVal.click();
//				Thread.sleep(3000);

			} catch (Exception e) {
				logger.info("I checking the fare option() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			try {

				homePage.getElementByXpath(driver, "//h4[contains(text(),'${token}')]", bookTicketDTO.getFareOption());

				Thread.sleep(2000);

			} catch (Exception e) {
				
			}

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_advance_search()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_flight_ltinerary() {

		try {

			Thread.sleep(20000);
			logger.info("click flight ltinerary");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.flightLtinerary));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click flight ltinerary", driver,
					getScenarioName());
			homePage.flightLtinerary.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void I_need_to_validate_flight_ltinerary() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);
			List<WebElement> mainFlightPanelContainer = driver
					.findElements(By.xpath("//*[@class='empireFlight_ItAllContent']"));
			// List<WebElement> mainFlightListContainer = null;

			// logger.info("mainFlightPanelContainer size -> " +
			// mainFlightPanelContainer.size());
			int containerIndex = 1;
			// for (WebElement container : mainFlightPanelContainer) {
			List<WebElement> mainFlightListContainer = (List<WebElement>) executor
					.executeScript("return Array.from(document.querySelectorAll('.empireFlight_ItBody'))");

			int cardIndex = 1;
			for (WebElement card : mainFlightListContainer) {
				softly.assertThat(CommonUtils.checkImageLoad(
						driver.findElement(By.xpath("(//img[@alt='Airline Logo'])[" + cardIndex + "]"))))
						.as("Checking the image is loaded - Airline Logo").isTrue();

				String flightName = (String) executor
						.executeScript("return document.querySelectorAll('.empireFlight_FlightNames')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(flightName.length()).isGreaterThan(0);
				logger.info("Flight Name " + flightName);

				try {
					String lcc = (String) executor
							.executeScript("return document.querySelectorAll('.LCC_Wrapper.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(lcc.length()).isGreaterThan(0);
					logger.info("LCC " + lcc);
				} catch (Exception e) {
					// Handle exception
				}

				String flightTime = (String) executor
						.executeScript("return document.querySelectorAll('.empireFlight_FlightTime')[" + (cardIndex - 1)
								+ "].innerText");
				softly.assertThat(flightTime.length()).isGreaterThan(0);
				logger.info("Flight Start Time " + flightTime);

				try {
					String fromAndTo = (String) executor
							.executeScript("return document.querySelectorAll('.empireF_ItineraryTabTitle')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(fromAndTo.length()).isGreaterThan(0);
					logger.info("From and To " + fromAndTo);
					CommonDTO.setFromAndTo(fromAndTo);

				} catch (Exception e) {
					// Handle exception
				}
				
try {
	String date = (String) executor
			.executeScript("return document.querySelectorAll('.empireFlight_ItDate empire_dateSpace')["
					+ (cardIndex - 1) + "].innerText");
	softly.assertThat(date.length()).isGreaterThan(0);
	logger.info("Date " + date);

}catch (Exception e) {
	// TODO: handle exception
}
				
				try {
					String stop = (String) executor.executeScript(
							"return document.querySelectorAll('.empireFlight_stopvia.empireF_directionTxt.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(stop.length()).isGreaterThan(0);
					logger.info("Stop " + stop);
				} catch (Exception e) {
					String stopFallback = (String) executor
							.executeScript("return document.querySelectorAll('.empireFlight_stopvia.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(stopFallback.length()).isGreaterThan(0);
					logger.info("Stop " + stopFallback);
				}

				String flightCode = (String) executor
						.executeScript("return document.querySelectorAll('.empireFlight_FlightCode')[" + (cardIndex - 1)
								+ "].innerText");
				softly.assertThat(flightCode.length()).isGreaterThan(0);
				logger.info("Source: " + flightCode);

				String time = (String) executor
						.executeScript("return document.querySelectorAll('.empireFlight_time.include')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(time.length()).isGreaterThan(0);
				logger.info("Time: " + time);

				String passengerClass = (String) executor
						.executeScript("return document.querySelectorAll('.empireFlight_Rbd.include.ng-star-inserted')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(passengerClass.length()).isGreaterThan(0);
				logger.info("Passenger Class: " + passengerClass);
try {

	String installments = (String) executor
			.executeScript("return document.querySelectorAll('.empireF_installmentwrap.ng-star-inserted')["
					+ (cardIndex - 1) + "].innerText");
	softly.assertThat(installments.length()).isGreaterThan(0);
	logger.info("Installments: " + installments);
	
}catch (Exception e) {
	// TODO: handle exception
}

				String endTime = (String) executor.executeScript(
						"return document.querySelectorAll('.empireFlight_FlightTime.empireFlight_additionalTimeList')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(endTime.length()).isGreaterThan(0);
				logger.info("End Time: " + endTime);
				CommonDTO.getInstance().setFlightEndTime(StringUtils.extractTime(endTime));

				String destination = (String) executor.executeScript(
						"return document.querySelectorAll('.empireFlight_FlightCode.empireFlight_DepartCode')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(destination.length()).isGreaterThan(0);
				logger.info("Destination: " + destination);

				String fareOption = (String) executor
						.executeScript("return document.querySelectorAll('.FareTypeBox.ng-star-inserted')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(fareOption.length()).isGreaterThan(0);
				logger.info("Fare Option: " + fareOption);

				cardIndex++;
			}
			// containerIndex++;
			// }

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_advance_search()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_baggage() {

		try {

			Thread.sleep(20000);
			logger.info("click baggage");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.clickBaggage));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click baggage", driver, getScenarioName());
			homePage.clickBaggage.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void I_need_to_validate_baggage() throws Exception {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);
			List<WebElement> mainFlightListContainer = driver
					.findElements(By.xpath("//*[@class='empireFlight_DetailBagInfo ng-star-inserted']"));
			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());

			int validationLimitCount = 0;

			for (WebElement card : mainFlightListContainer) {

				if (validationLimitCount > 6) {
					break;
				}

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireF_ItineraryTabTitle']")).getText().length())
						.isGreaterThan(0);
				logger.info("From and To "
						+ card.findElement(By.xpath("//*[@class='empireF_ItineraryTabTitle']")).getText());
//
//				softly.assertThat(card
//						.findElement(By.xpath("//*[@class='//*[@class='empireF_bagTripText empireF_bagTripDate']/p"))
//						.getText().length()).isGreaterThan(0);
//				logger.info("Date " + card
//						.findElement(By.xpath("//*[@class='empireF_bagTripText empireF_bagTripDate']/p")).getText());

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_confirmBagSubTilte']")).getText().length())
						.isGreaterThan(0);
				logger.info("From and To card "
						+ card.findElement(By.xpath("//*[@class='empireFlight_confirmBagSubTilte']")).getText());

				softly.assertThat(card.findElement(By.xpath("//*[contains(text(),'Check in')]")).getText().length())
						.isGreaterThan(0);
				logger.info("checkin" + card.findElement(By.xpath("//*[contains(text(),'Checkin ')]")).getText());

				softly.assertThat(card.findElement(By.xpath("//*[contains(text(),'Cabin')]")).getText().length())
						.isGreaterThan(0);
				logger.info("Cabin va" + card.findElement(By.xpath("//*[contains(text(),'Cabin')]")).getText());
				try {

					softly.assertThat(card
							.findElement(By.xpath(
									"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'KG')]"))
							.getText().length()).isGreaterThan(0);
					logger.info("checkin " + card
							.findElement(By.xpath(
									"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'KG')]"))
							.getText());

					CommonDTO.setCheckinBaggageData(card
							.findElement(By.xpath(
									"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'KG')]"))
							.getText());

				} catch (Exception e) {
				}

				try {
					softly.assertThat(card.findElement(By.xpath(
							"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'Not Included')]"))
							.getText().length()).isGreaterThan(0);
					logger.info("checkin " + card.findElement(By.xpath(
							"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'Not Included')]"))
							.getText());
					CommonDTO.setCheckinBaggageData(card.findElement(By.xpath(
							"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'Not Included')]"))
							.getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					softly.assertThat(card.findElement(By.xpath(
							"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'Piece')]"))
							.getText().length()).isGreaterThan(0);
					logger.info("checkin " + card.findElement(By.xpath(
							"//*[@class='empireFlight_confirmBagTableData']/child::*[contains(text(), 'Piece')]"))
							.getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

				softly.assertThat(card.findElement(By.xpath(
						"//*[@class='empireFlight_confirmBagTableData ng-star-inserted']/child::*[contains(text(), 'KG')]"))
						.getText().length()).isGreaterThan(0);
				logger.info("Cabin KG" + card.findElement(By.xpath(
						"//*[@class='empireFlight_confirmBagTableData ng-star-inserted']/child::*[contains(text(), 'KG')]"))
						.getText());
				CommonDTO.setCabinBaggageData(card.findElement(By.xpath(
						"//*[@class='empireFlight_confirmBagTableData ng-star-inserted']/child::*[contains(text(), 'KG')]"))
						.getText());

			}
			validationLimitCount++;

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_baggage()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}

	public void I_click_on_the_fare_breakup() {
		try {

			Thread.sleep(20000);
			logger.info("click the fare breakup");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.clickFarebreakup));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click the fare breakup", driver,
					getScenarioName());
			homePage.clickFarebreakup.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void I_need_to_validate_fare_breakup() throws Exception {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);
			WebElement mainFlightListContainer = driver.findElement(By.xpath("//*[@class='empireF_fareBreakGrid']"));

			int validationLimitCount = 0;
			int totalPassengerCategoryCount = 0;

			if (bookTicketDTO.getAdultCount() > 0) {
				totalPassengerCategoryCount += 1;
			}
			if (bookTicketDTO.getChildCount() > 0) {
				totalPassengerCategoryCount += 1;
			}
			if (bookTicketDTO.getInfantCount() > 0) {
				totalPassengerCategoryCount += 1;
			}

			softly.assertThat(mainFlightListContainer
					.findElement(By.xpath("//tr[@class='empireF_fareBreakHead']//th[1]")).getText().length())
					.isGreaterThan(0);
			logger.info("Summary: " + mainFlightListContainer
					.findElement(By.xpath("//tr[@class='empireF_fareBreakHead']//th[1]")).getText());

			softly.assertThat(
					mainFlightListContainer.findElement(By.xpath("//h4[text()='Base Fare']")).getText().length())
					.isGreaterThan(0);
			logger.info("Base Fare: "
					+ mainFlightListContainer.findElement(By.xpath("//h4[text()='Base Fare']")).getText());

			softly.assertThat(
					mainFlightListContainer.findElement(By.xpath("//h4[text()='Fee & Tax']")).getText().length())
					.isGreaterThan(0);
			logger.info(
					"Fee & Tax:" + mainFlightListContainer.findElement(By.xpath("//h4[text()='Fee & Tax']")).getText());

			softly.assertThat(mainFlightListContainer.findElement(By.xpath("//h4[text()='VAT']")).getText().length())
					.isGreaterThan(0);
			logger.info("VAT:" + mainFlightListContainer.findElement(By.xpath("//h4[text()='VAT']")).getText());

			softly.assertThat(
					mainFlightListContainer.findElement(By.xpath("//h4[text()='No. Of Pax']")).getText().length())
					.isGreaterThan(0);
			logger.info("No. Of Pax:"
					+ mainFlightListContainer.findElement(By.xpath("//h4[text()='No. Of Pax']")).getText());

			softly.assertThat(
					mainFlightListContainer.findElement(By.xpath("//h4[text()='Total Fare']")).getText().length())
					.isGreaterThan(0);
			logger.info(
					"Total :" + mainFlightListContainer.findElement(By.xpath("//h4[text()='Total Fare']")).getText());

			softly.assertThat(
					mainFlightListContainer.findElement(By.xpath("//h4[text()='Total Per Pax']")).getText().length())
					.isGreaterThan(0);
			logger.info("Total Per Pax:"
					+ mainFlightListContainer.findElement(By.xpath("//h4[text()='Total Per Pax']")).getText());

			int i = 1;
			String passengerType = "Adult";
			boolean childFlag = true;
			boolean infantFlag = bookTicketDTO.getInfantCount() > 0;
			List<WebElement> cards = (List<WebElement>) executor.executeScript("return document.querySelectorAll('.empireF_fareBreakUPBodys');");
			for (WebElement card : cards) {

				if (card.findElements(By.xpath("//*[contains(@class,'empireF_fareBreakUPBodys')]"))
						.size() == totalPassengerCategoryCount) {
					if (i > 1 && bookTicketDTO.getChildCount() > 0 && childFlag) {
						passengerType = "Child";
						childFlag = false;
						
					}
					if (i > 1 && bookTicketDTO.getInfantCount() > 0 && infantFlag) {
						passengerType = "Infant";
					}

					i++;
					
				 
			       
					softly.assertThat(
							card.findElement(By.xpath("//*[text()='" + passengerType + "']")).getText().length())
							.isGreaterThan(0);
					logger.info("Traveller: "
							+ card.findElement(By.xpath("//*[text()='" + passengerType + "']")).getText());					
					

					// Safely extract and log text content for each field
					String baseFareText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(1)");
					softly.assertThat(baseFareText.length()).isGreaterThan(0);
					logger.info("Base Fare: " + baseFareText);

					String feeAndTaxText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(2)");
					softly.assertThat(feeAndTaxText.length()).isGreaterThan(0);
					logger.info("Fee & Tax: " + feeAndTaxText);

					String transactionFeeText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(3)");
					softly.assertThat(transactionFeeText.length()).isGreaterThan(0);
					logger.info("Transaction Fee: " + transactionFeeText);

					String discountText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(4)");
					softly.assertThat(discountText.length()).isGreaterThan(0);
					logger.info("Discount (-): " + discountText);

					String vatText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(5)");
					softly.assertThat(vatText.length()).isGreaterThan(0);
					logger.info("Vat: " + vatText);

					String numberOfPaxText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(6)");
					softly.assertThat(numberOfPaxText.length()).isGreaterThan(0);
					logger.info("No. Of Pax: " + numberOfPaxText);

					String totalPerPaxText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(7)");
					softly.assertThat(totalPerPaxText.length()).isGreaterThan(0);
					logger.info("Total per pax: " + totalPerPaxText);

					String totalFareText = getTextContent(card, ".empireF_fareBreakGrid td:nth-child(8)");
					softly.assertThat(totalFareText.length()).isGreaterThan(0);
					logger.info("Total fare: " + totalFareText);
					
				   
				} else {
					softly.assertThat(card.findElements(By.tagName("td")).size()).isEqualTo(totalPassengerCategoryCount);
				}
			}
		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_fare_breakup()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

	}
	String getTextContent(WebElement element, String selector) {
	    return (String) executor.executeScript(
	        "let el = arguments[0].querySelector(arguments[1]); return el ? el.textContent.trim() : '';", 
	        element, selector
	    );
	}

	public void I_need_to_validate_fare_option_card_for_round_trip() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);

			// Find the round trip tabs using querySelectorAll
			List<WebElement> roundTripTabs = (List<WebElement>) executor
					.executeScript("return document.querySelectorAll('.empireFlight_tabwrapperText')");

			// Initialize variables
			int tabCounter = 0;

			List<WebElement> fareOptionsMainContainer = (List<WebElement>) executor
					.executeScript("return document.querySelectorAll('.empireF_multifareWrapper')");

			List<WebElement> mainFlightListContainer = null;

			for (int containerIndex = 0; containerIndex < fareOptionsMainContainer.size(); containerIndex++) {
				int i = 0;
				WebElement container = fareOptionsMainContainer.get(containerIndex);
				// Click the round trip tab
				executor.executeScript("arguments[0].click();", roundTripTabs.get(tabCounter));

				// Find elements inside container using querySelectorAll
				mainFlightListContainer = container
						.findElements(By.cssSelector(".fareoptioncardnew:not([style*='display: none;'])"));

				logger.info("mainFlightListContainer size->" + mainFlightListContainer.size());
				if (tabCounter == 0) {
					for (int cardIndex = 0; cardIndex < mainFlightListContainer.size(); cardIndex++) {
						WebElement card = mainFlightListContainer.get(cardIndex);
						logger.info("validating card - " + (i + 1));

						softly.assertThat(
								container.findElement(By.xpath("(//*[@class='temp3flightfareoptionheadingWrap']/p)[1]"))
										.getText().length())
								.isGreaterThan(0);
						logger.info("Fare option "
								+ card.findElement(By.xpath("(//*[@class='temp3flightfareoptionheadingWrap']/p)[1]"))
										.getText());

						softly.assertThat(
								card.findElement(By.xpath("(//*[@class='fareoption_rate']/p)[" + (i + 1) + "]"))
										.getText().length())
								.isGreaterThan(0);
						logger.info("currency and price " + card
								.findElement(By.xpath("(//*[@class='fareoption_rate']/p)[" + (i + 1) + "]")).getText());

						wait.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("(//*[@class='fareoption_rate'])[" + (i + 1) + "]/p"))));
						driver.findElement(By.xpath("(//*[@class='fareoption_rate'])[" + (i + 1) + "]/p")).click();
						LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click fare option", driver,
								getScenarioName());
						logger.info("Click fare option -> " + driver
								.findElement(By.xpath("(//*[@class='fareoption_rate'])[" + (i + 1) + "]/p")).getText());
						logger.info("Currency fare option -> " + homePage.currencyFareOption.getText());
						softly.assertThat(
								driver.findElement(By.xpath("(//*[@class='fareoption_rate'])[" + (i + 1) + "]/p"))
										.getText().trim())
								.isEqualTo(homePage.currencyFareOption.getText().trim());

						softly.assertThat(
								card.findElement(By.xpath("(//*[@class='fareoption_rate']/h4)[" + (i + 1) + "]"))
										.getText().length())
								.isGreaterThan(0);
						logger.info("Fare option type "
								+ card.findElement(By.xpath("(//*[@class='fareoption_rate']/h4)[" + (i + 1) + "]"))
										.getText());

						softly.assertThat(card.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail']/h4"))
								.getText().length()).isGreaterThan(0);
						logger.info("Baggage  "
								+ card.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail']/h4")).getText());
						softly.assertThat(
								card.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail ng-star-inserted']/h4"))
										.getText().length())
								.isGreaterThan(0);
						logger.info("Included  " + card
								.findElement(By.xpath("//*[@class='Fareoption_Baggegdetail ng-star-inserted']/h4"))
								.getText());
						i++;
					}
				}

				tabCounter++;
			}
		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_advance_search()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

		try {

			Thread.sleep(20000);
			logger.info("click the Return");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.clickReturnFareoption));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click the fare breakup", driver,
					getScenarioName());
			homePage.clickReturnFareoption.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void I_need_to_add_the_traveller_details() {
		try {

			Type pd = new TypeToken<PassengerDetailsDTO>() {
				private static final long serialVersionUID = -7767108171943612798L;

			}.getType();

			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

			List<PassengerDetailsDTO> adultPassengerDTOList = new ArrayList<PassengerDetailsDTO>();
			List<PassengerDetailsDTO> childPassengerDTOList = new ArrayList<PassengerDetailsDTO>();
			List<PassengerDetailsDTO> infantPassengerDTOList = new ArrayList<PassengerDetailsDTO>();

			if (bookTicketDTO.getAdultCount() > 0) {
				for (int i = 0; i < bookTicketDTO.getAdultCount(); i++) {
					adultPassengerDTOList.add(
							(PassengerDetailsDTO) JsonToGson.convertToObjFromArray("adult_booking_details", i, pd));
				}
			}
			if (bookTicketDTO.getChildCount() > 0) {
				for (int i = 0; i < bookTicketDTO.getChildCount(); i++) {
					childPassengerDTOList.add(
							(PassengerDetailsDTO) JsonToGson.convertToObjFromArray("child_booking_details", i, pd));
				}
			}
			if (bookTicketDTO.getInfantCount() > 0) {
				for (int i = 0; i < bookTicketDTO.getInfantCount(); i++) {
					infantPassengerDTOList.add(
							(PassengerDetailsDTO) JsonToGson.convertToObjFromArray("infant_booking_details", i, pd));
				}
			}

			int adultPassengersCount = adultPassengerDTOList.size();
			int childPassengersCount = childPassengerDTOList.size();
			int infantPassengersCount = infantPassengerDTOList.size();
			List<PassengerDetailsDTO> allPassengerDTOList = new ArrayList<>();

			allPassengerDTOList.addAll(adultPassengerDTOList);
			allPassengerDTOList.addAll(childPassengerDTOList);
			allPassengerDTOList.addAll(infantPassengerDTOList);

			CommonDTO.getInstance().setAllPassengerDTOList(allPassengerDTOList);
			int i = 0;
			int dataIndex = 0;
			List<WebElement> passengerDetailsContainer = driver
					.findElements(By.xpath("//*[@formarrayname='clientDetails']"));
			logger.info("passengerDetailsContainer->" + passengerDetailsContainer.size());
			Actions actions = new Actions(driver);
			while (i < adultPassengersCount) {
				logger.info("adultPassengerDTOList->" + adultPassengerDTOList.size());
				AosImplementation.addPassengerDetails(executor, wait, adultPassengerDTOList, passengerDetailsContainer,
						actions, i, dataIndex);
				dataIndex++;
				i++;
			}
			dataIndex = 0;
			while (dataIndex < childPassengersCount) {
				logger.info("childPassengerDTOList->" + childPassengerDTOList.size());
				AosImplementation.addPassengerDetails(executor, wait, childPassengerDTOList, passengerDetailsContainer,
						actions, i, dataIndex);
				i++;
				dataIndex++;
			}
			dataIndex = 0;
			while (dataIndex < infantPassengersCount) {
				logger.info("infantPassengerDTOList->" + infantPassengerDTOList.size());
				AosImplementation.addPassengerDetails(executor, wait, infantPassengerDTOList, passengerDetailsContainer,
						actions, i, dataIndex);
				i++;
				dataIndex++;
			}

		} catch (Exception e) {
			logger.info("Exception occured at addPassengerDetails()->" + e.getMessage());
		}

	}

	public void I_click_on_the_booknow() throws InterruptedException {
		logger.info("Click the Booknow Button");
		wait.until(ExpectedConditions.elementToBeClickable(homePage.Booknow));
		homePage.Booknow.click();
		LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Click the Booknow button", driver,
				getScenarioName());

		Thread.sleep(10000);

	}

	public void I_need_to_validate_flight_Summary() {
		// TODO Auto-generated method stub
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);
			List<WebElement> mainFlightPanelContainer = driver.findElements(By.xpath("//*[@class='empireF_BSCard']"));
			// List<WebElement> mainFlightListContainer = null;

			// logger.info("mainFlightPanelContainer size -> " +
			// mainFlightPanelContainer.size());
			int containerIndex = 1;
			// for (WebElement container : mainFlightPanelContainer) {
			List<WebElement> mainFlightListContainer = (List<WebElement>) executor
					.executeScript("return Array.from(document.querySelectorAll('.empireF_BSCard'))");
			logger.info("mainFlightPanelContainer->" + mainFlightPanelContainer.size());
			int cardIndex = 1;
			for (WebElement card : mainFlightPanelContainer) {

				try {
					String fromAndTo = (String) executor
							.executeScript("return document.querySelectorAll('.empireF_BSTitle > h4 > span')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(fromAndTo.length()).isGreaterThan(0);
					logger.info("From and To " + fromAndTo);
				} catch (Exception e) {
					// Handle exception
				}
				try {

					String date = (String) executor
							.executeScript("return document.querySelectorAll('.empireF_BSTitle > h4 > div')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(date.length()).isGreaterThan(0);
					logger.info("Date " + date);
				} catch (Exception e) {
					// TODO: handle exception
				}
				softly.assertThat(CommonUtils.checkImageLoad(
						driver.findElement(By.xpath("(//img[@class='empireF_BSImg'])[" + cardIndex + "]"))))
						.as("Checking the image is loaded - Airline Logo").isTrue();

				String source = (String) executor.executeScript(
						"return document.querySelectorAll('.empireF_FlightCode')[" + (cardIndex - 1) + "].innerText");
				softly.assertThat(source.length()).isGreaterThan(0);
				logger.info("Source " + source);

				try {
					String lcc = (String) executor
							.executeScript("return document.querySelectorAll('.LCC_Wrapper.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(lcc.length()).isGreaterThan(0);
					logger.info("LCC " + lcc);
				} catch (Exception e) {
					// Handle exception
				}

				String flightTime = (String) executor.executeScript(
						"return document.querySelectorAll('.empireF_FlightTime')[" + (cardIndex - 1) + "].innerText");
				softly.assertThat(flightTime.length()).isGreaterThan(0);
				logger.info("Flight Start Time " + flightTime);

				try {
					String stop = (String) executor.executeScript(
							"return document.querySelectorAll('.empireFlight_stopvia.empireF_directionTxt.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(stop.length()).isGreaterThan(0);
					logger.info("Stop " + stop);
				} catch (Exception e) {
					String stopFallback = (String) executor.executeScript(
							"return document.querySelectorAll('.empireF_BSStops')[" + (cardIndex - 1) + "].innerText");
					softly.assertThat(stopFallback.length()).isGreaterThan(0);
					logger.info("Stop " + stopFallback);
				}

//				String flightCode = (String) executor
//						.executeScript("return document.querySelectorAll('.empireFlight_FlightCode')[" + (cardIndex - 1)
//								+ "].innerText");
//				softly.assertThat(flightCode.length()).isGreaterThan(0);
//				logger.info("Source: " + flightCode);

				String time = (String) executor.executeScript(
						"return document.querySelectorAll('.empireF_BSStops > h5')[" + (cardIndex - 1) + "].innerText");
				softly.assertThat(time.length()).isGreaterThan(0);
				logger.info("Time: " + time);
				try {
					String passengerClass = (String) executor.executeScript(
							"return document.querySelectorAll('.empireFlight_Rbd.include.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(passengerClass.length()).isGreaterThan(0);
					logger.info("Passenger Class: " + passengerClass);
				} catch (Exception e) {
					// TODO: handle exception
				}

				String endTime = (String) executor
						.executeScript("return document.querySelectorAll('.empireF_BSDepart > h2')[" + (cardIndex - 1)
								+ "].innerText");
				softly.assertThat(endTime.length()).isGreaterThan(0);
				logger.info("End Time: " + endTime);
				CommonDTO.getInstance().setFlightEndTime(StringUtils.extractTime(endTime));

				String destination = (String) executor
						.executeScript("return document.querySelectorAll('.empireFlight_FlightCode')[" + (cardIndex - 1)
								+ "].innerText");
				softly.assertThat(destination.length()).isGreaterThan(0);
				logger.info("Destination: " + destination);

				String fareOption = (String) executor
						.executeScript("return document.querySelectorAll('.empireF_travellerWrapper')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(fareOption.length()).isGreaterThan(0);
				logger.info("Fare Option: " + fareOption);

				cardIndex++;
			}
			// containerIndex++;
			// }

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_flight_Summary()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

		try {
			SoftAssertions softly = new SoftAssertions();

			logger.info("click flight details");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.clickFlightDetails));

			homePage.clickFlightDetails.click();
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click flight details", driver,
					getScenarioName());
			softly.assertThat(homePage.valiFlightDetails.getAttribute("type")).isEqualTo("Total Duration");

			wait.until(ExpectedConditions.elementToBeClickable(homePage.closeFlightDetails));
			homePage.closeFlightDetails.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			SoftAssertions softly = new SoftAssertions();

			logger.info("click fare option");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.travellerClickFareOption));

			homePage.travellerClickFareOption.click();
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click fareoption", driver,
					getScenarioName());
			softly.assertThat(homePage.valiClickFareOption.getAttribute("type")).isEqualTo("Included");

			wait.until(ExpectedConditions.elementToBeClickable(homePage.closeClickFareOption));
			homePage.closeClickFareOption.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			SoftAssertions softly = new SoftAssertions();

			logger.info("click fare rule");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.valiClickFareRule));

			homePage.valiClickFareRule.click();
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click fare rule", driver, getScenarioName());
			softly.assertThat(homePage.clickBaggage.getAttribute("type")).isEqualTo("Included");

			wait.until(ExpectedConditions.elementToBeClickable(homePage.closeClickFareRule));
			homePage.closeClickFareRule.click();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void I_click_on_payment_continue() {

		logger.info("Click the continue To Payment Button");
		wait.until(ExpectedConditions.elementToBeClickable(homePage.continueToPayment));
		LogEvent.logEventWithScreenshot(

				getExtentTest(), Status.INFO, "Click to continue payment", driver, getScenarioName());
		homePage.continueToPayment.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(homePage.freeContinuePayment));
			homePage.freeContinuePayment.click();

			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void I_need_to_add_the_traveller_common_details() {

		BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

		logger.info("Enter the Email: " + bookTicketDTO.getEmail());
		wait.until(ExpectedConditions.elementToBeClickable(homePage.email));
		homePage.email.sendKeys(bookTicketDTO.getEmail());

		logger.info("Selecting the Country Mobile: " + bookTicketDTO.getCountryMobile());
		wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.countryMobile));
		passengerDetailsPage.countryMobile.click();
		executor.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
						+ bookTicketDTO.getCountryMobile() + "'])[1]//parent::div")));

		logger.info("Enter the Mobile No: " + bookTicketDTO.getMobileNo());
		wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.mobileNo));
		passengerDetailsPage.mobileNo.sendKeys(bookTicketDTO.getMobileNo());

	}

	public void I_need_to_validate_payment_gateway() throws InterruptedException {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);
			WebElement gatewayContainer = driver.findElement(By.xpath("//*[@class='empireFlight_PaymentBody']"));

			logger.info("Enter the Payment gateway: " + bookTicketDTO.getPaymentGateway());
			homePage.getElementByXpath(driver,
					"//*[@class='payment-tabSVGWrapper']//parent::button[contains(text(),'${token}')]",
					bookTicketDTO.getPaymentGateway());

			if (bookTicketDTO.getPaymentGateway().equalsIgnoreCase("TAP API LATEST")) {

				logger.info("Enter the Card Number: " + bookTicketDTO.getTapapiCardnumber());
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='number']"))));
				WebElement cardNumber = driver.findElement(By.xpath("//input[@formcontrolname='number']"));
				cardNumber.sendKeys(bookTicketDTO.getTapapiCardnumber());

				logger.info("Enter the Card Name: " + bookTicketDTO.getTapapiCardname());

				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='name']"))));

				WebElement cardName = driver.findElement(By.xpath("//input[@formcontrolname='name']"));
				cardName.sendKeys(bookTicketDTO.getTapapiCardname());

				logger.info("Enter the exp month: " + bookTicketDTO.getTapapiExprimonth());
//				
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.tapapiExprimonth));
//				homePage.tapapiExprimonth.click();
//				homePage.getElementByXpath(driver,
//						"//*[@formcontrolname='exp_month']//following::span[@class='ng-option-label ng-star-inserted' and text()='${token}']",
//						bookTicketDTO.getTapapiExprimonth());
//				
//				
//				Thread.sleep(3000);
//				logger.info("Enter the exp year: " + bookTicketDTO.getTapapiExpriyear());
//				
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.tapapiExpriYear));
//				homePage.tapapiExpriYear.click();
//				homePage.getElementByXpath(driver,
//						"//*[@formcontrolname='exp_year']//following::span[@class='ng-option-label ng-star-inserted' and text()='${token}']",
//						bookTicketDTO.getTapapiExpriyear());

				logger.info("Enter the Cvv: " + bookTicketDTO.getTapapiCardcvv());
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='cvc']"))));

				WebElement cvv = driver.findElement(By.xpath("//input[@formcontrolname='cvc']"));
				cvv.sendKeys(bookTicketDTO.getTapapiCardcvv());

				logger.info("Enter the Add1: " + bookTicketDTO.getTapapiAddressOne());
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='line1']"))));
				WebElement add = driver.findElement(By.xpath("//input[@formcontrolname='line1']"));
				add.sendKeys(bookTicketDTO.getTapapiAddressOne());

				logger.info("Enter the Add2: " + bookTicketDTO.getTapapiAddressTwo());
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='line2']"))));
				WebElement addt = driver.findElement(By.xpath("//input[@formcontrolname='line2']"));
				addt.sendKeys(bookTicketDTO.getTapapiAddressTwo());

//				logger.info("Enter the country: " + bookTicketDTO.getTapapiCountry());
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.tapapiCountry));
//				homePage.tapapiCountry.click();
//				homePage.getElementByXpath(driver,
//						"//*[@formcontrolname='country']//following::span[@class='ng-option-label ng-star-inserted' and text()='${token}']",
//						bookTicketDTO.getTapapiCountry());

				logger.info("Enter the State: " + bookTicketDTO.getTapapiState());
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='state']"))));
				WebElement state = driver.findElement(By.xpath("//input[@formcontrolname='state']"));
				state.sendKeys(bookTicketDTO.getTapapiState());

				logger.info("Enter the City: " + bookTicketDTO.getTapapiCity());
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='city']"))));
				WebElement city = driver.findElement(By.xpath("//input[@formcontrolname='city']"));
				city.sendKeys(bookTicketDTO.getTapapiCity());

				logger.info("Enter the Pin: " + bookTicketDTO.getTapapiCardname());
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.xpath("//input[@formcontrolname='zip_code']"))));
				WebElement pin = driver.findElement(By.xpath("//input[@formcontrolname='zip_code']"));
				pin.sendKeys(bookTicketDTO.getTapapiCardnumber());

			}

//			gatewayContainer.findElement(By.partialLinkText("privacy policy")).click();
			//
//			softly.assertThat(driver.findElement(By.xpath("//h2[text()='Privacy Policy']")).getText())
//					.isEqualTo("Privacy Policy");
			//
//			driver.findElement(By.xpath("//*[@class='mdc-button mat-mdc-button mat-unthemed mat-mdc-button-base']"))
//					.click();

			softly.assertThat(StringUtils.ConvertStringToDouble(
					driver.findElement(By.xpath("//*[@class='empireF_ProceedPrice']")).getText()))
					.isEqualTo(StringUtils.ConvertStringToDouble(
							driver.findElement(By.xpath("//*[@class='empireF_umrahwarp']")).getText()));

//			wait.until(ExpectedConditions.elementToBeClickable(homePage.processToPayment));
//			
//			Thread.sleep(10000);
//			
//			new Actions(driver).moveToElement(homePage.processToPayment).click().perform();
//			//executor.executeScript("arguments[0].click();", homePage.processToPayment);

			logger.info("Click the process To Pay Button");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.processToPay));
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Click to Process to pay", driver,
					getScenarioName());
			homePage.processToPay.click();

			Thread.sleep(15000);

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_payment_gateway: " + e.toString());
		}
	}

	public void I_enter_the_promo_code() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			logger.info("Enter the promo code: " + bookTicketDTO.getPromoCode());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_promoInputForm']/input"))));
			WebElement promoCode = driver.findElement(By.xpath("//*[@class='empireF_promoInputForm']/input"));
			promoCode.sendKeys(bookTicketDTO.getPromoCode());
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.clickApply));
			passengerDetailsPage.clickApply.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void I_need_to_validate_flight_Summary_payment() {
		try {
			BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
			SoftAssertions softly = new SoftAssertions();

			Thread.sleep(10000);
			List<WebElement> mainFlightPanelContainer = driver.findElements(By.xpath("//*[@class='empireF_BSCard']"));
			// List<WebElement> mainFlightListContainer = null;

			// logger.info("mainFlightPanelContainer size -> " +
			// mainFlightPanelContainer.size());
			int containerIndex = 1;
			// for (WebElement container : mainFlightPanelContainer) {
			List<WebElement> mainFlightListContainer = (List<WebElement>) executor
					.executeScript("return Array.from(document.querySelectorAll('.empireF_BSCard'))");
			logger.info("mainFlightPanelContainer->" + mainFlightPanelContainer.size());
			int cardIndex = 1;
			for (WebElement card : mainFlightPanelContainer) {

				try {
					String fromAndTo = (String) executor
							.executeScript("return document.querySelectorAll('.empireF_BSTitle > h4 > span')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(fromAndTo.length()).isGreaterThan(0);
					logger.info("From and To " + fromAndTo);
				} catch (Exception e) {
					// Handle exception
				}
				try {

					String date = (String) executor
							.executeScript("return document.querySelectorAll('.empireF_BSTitle > h4 > div')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(date.length()).isGreaterThan(0);
					logger.info("Date " + date);
				} catch (Exception e) {
					// TODO: handle exception
				}
				softly.assertThat(CommonUtils
						.checkImageLoad(driver.findElement(By.xpath("(//img[@alt='flight book'])[" + cardIndex + "]"))))
						.as("Checking the image is loaded - Airline Logo").isTrue();

				String source = (String) executor.executeScript(
						"return document.querySelectorAll('.empireF_FlightCode')[" + (cardIndex - 1) + "].innerText");
				softly.assertThat(source.length()).isGreaterThan(0);
				logger.info("Source " + source);

				try {
					String lcc = (String) executor
							.executeScript("return document.querySelectorAll('.LCC_Wrapper.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(lcc.length()).isGreaterThan(0);
					logger.info("LCC " + lcc);
				} catch (Exception e) {
					// Handle exception
				}

				String flightTime = (String) executor.executeScript(
						"return document.querySelectorAll('.empireF_FlightTime')[" + (cardIndex - 1) + "].innerText");
				softly.assertThat(flightTime.length()).isGreaterThan(0);
				logger.info("Flight Start Time " + flightTime);

				try {
					String stop = (String) executor.executeScript(
							"return document.querySelectorAll('.empireFlight_stopvia.empireF_directionTxt.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(stop.length()).isGreaterThan(0);
					logger.info("Stop " + stop);
				} catch (Exception e) {
					String stopFallback = (String) executor.executeScript(
							"return document.querySelectorAll('.empireF_BSStops')[" + (cardIndex - 1) + "].innerText");
					softly.assertThat(stopFallback.length()).isGreaterThan(0);
					logger.info("Stop " + stopFallback);
				}

//				String flightCode = (String) executor
//						.executeScript("return document.querySelectorAll('.empireFlight_FlightCode')[" + (cardIndex - 1)
//								+ "].innerText");
//				softly.assertThat(flightCode.length()).isGreaterThan(0);
//				logger.info("Source: " + flightCode);

				String time = (String) executor.executeScript(
						"return document.querySelectorAll('.empireF_BSStops > h5')[" + (cardIndex - 1) + "].innerText");
				softly.assertThat(time.length()).isGreaterThan(0);
				logger.info("Time: " + time);
				try {
					String passengerClass = (String) executor.executeScript(
							"return document.querySelectorAll('.empireFlight_Rbd.include.ng-star-inserted')["
									+ (cardIndex - 1) + "].innerText");
					softly.assertThat(passengerClass.length()).isGreaterThan(0);
					logger.info("Passenger Class: " + passengerClass);
				} catch (Exception e) {
					// TODO: handle exception
				}

				String endTime = (String) executor
						.executeScript("return document.querySelectorAll('.empireF_BSDepart > h2')[" + (cardIndex - 1)
								+ "].innerText");
				softly.assertThat(endTime.length()).isGreaterThan(0);
				logger.info("End Time: " + endTime);
				CommonDTO.getInstance().setFlightEndTime(StringUtils.extractTime(endTime));

				String destination = (String) executor.executeScript(
						"return document.querySelectorAll('.empireF_FlightCode')[" + (cardIndex - 1) + "].innerText");
				softly.assertThat(destination.length()).isGreaterThan(0);
				logger.info("Destination: " + destination);

				String fareOption = (String) executor
						.executeScript("return document.querySelectorAll('.empireF_travellerWrapper')["
								+ (cardIndex - 1) + "].innerText");
				softly.assertThat(fareOption.length()).isGreaterThan(0);
				logger.info("Fare Option: " + fareOption);

				cardIndex++;
			}
			// containerIndex++;
			// }

		} catch (Exception e) {
			logger.info("Exception occured at I_need_to_validate_flight_Summary_payment()->" + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver, getScenarioName());
		}

		try {
			SoftAssertions softly = new SoftAssertions();

			logger.info("click flight details");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.clickFlightDetails));

			homePage.clickFlightDetails.click();
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click flight details", driver,
					getScenarioName());
			softly.assertThat(homePage.valiFlightDetails.getAttribute("type")).isEqualTo("Total Duration");

			wait.until(ExpectedConditions.elementToBeClickable(homePage.closeFlightDetails));
			homePage.closeFlightDetails.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			SoftAssertions softly = new SoftAssertions();

			logger.info("click fare option");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.travellerClickFareOption));

			homePage.travellerClickFareOption.click();
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click fareoption", driver,
					getScenarioName());
			softly.assertThat(homePage.valiClickFareOption.getAttribute("type")).isEqualTo("Included");

			wait.until(ExpectedConditions.elementToBeClickable(homePage.closeClickFareOption));
			homePage.closeClickFareOption.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			SoftAssertions softly = new SoftAssertions();

			logger.info("click fare rule");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.valiClickFareRule));

			homePage.valiClickFareRule.click();
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "click fare rule", driver, getScenarioName());
			softly.assertThat(homePage.clickBaggage.getAttribute("type")).isEqualTo("Included");

			wait.until(ExpectedConditions.elementToBeClickable(homePage.closeClickFareRule));
			homePage.closeClickFareRule.click();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void I_enter_the_card_details() throws InterruptedException {

		BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
		SoftAssertions softly = new SoftAssertions();
		logger.info("Enter the card No: " + bookTicketDTO.getCardNo());
		wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.cardNo));
		LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Card Detail Page", driver, getScenarioName());
		passengerDetailsPage.cardNo.sendKeys(bookTicketDTO.getCardNo());

		logger.info("Enter card Expiry Date: " + bookTicketDTO.getExpDate());
		wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.expDate));
		passengerDetailsPage.expDate.sendKeys(bookTicketDTO.getExpDate());

		logger.info("Enter card Cvv: " + bookTicketDTO.getCvv());
		wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.cvv));
		passengerDetailsPage.cvv.sendKeys(bookTicketDTO.getCvv());

		logger.info("Enter card Holder Name: " + bookTicketDTO.getCardHolderName());
		wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.cardHolderName));
		passengerDetailsPage.cardHolderName.sendKeys(bookTicketDTO.getCardHolderName());

		logger.info("Click the Pay Button");
		wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.pay));
		passengerDetailsPage.pay.click();

		Thread.sleep(150000);
	}

	public void I_need_to_validate_confirmation_page() {
		BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();
		SoftAssertions softly = new SoftAssertions();

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
		
		logger.info(
				"Trip Id->" + findElement(By.xpath("//*[@class='empireFlight_confirmPaytxt ng-star-inserted']//child::*[contains(text(),'Trip Id: ')]/span")).getText());
		softly.assertThat(
				findElement(By.xpath("//*[@class='empireFlight_confirmPaytxt ng-star-inserted']//child::*[contains(text(),'Trip Id: ')]/span")).getText().trim().length())
				.isGreaterThan(0);
		
		List<WebElement> baggageContainer = driver.findElements(
				By.xpath("//*[@class='empireFlight_confirmTripBaggageTripText empireF_baggageHead ng-star-inserted']"));
		int i = 0;
		for (WebElement container : baggageContainer)
		
		
		{
			
			

			logger.info("From and To: " + container.findElement(By.tagName("h4")).getText().trim());
			// softly.assertThat(container.findElement(By.tagName("h4")).getText().trim())
			// .isEqualTo(CommonDTO.getFromAndTo().get(i));
			
			
			softly.assertThat(
					container.findElement(By.xpath("//*[@class='empireFlight_confirmBagTableData']")).getText().trim())
					.isEqualTo(CommonDTO.getCheckinBaggageData().get(i));

			softly.assertThat(
					container.findElement(By.xpath("//*[@class='empireFlight_confirmBagTableData ng-star-inserted']"))
							.getText().trim())
					.isEqualTo(CommonDTO.getCabinBaggageData().get(i));
			i++;
		}
		// CommonDTO.clearFromAndToList();

		WebElement travellerTable = driver.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']"));

		List<WebElement> passengersList = travellerTable.findElements(By.tagName("tr"));

		int passengerIndex = 1;
		for (WebElement passenger : passengersList) {

//			logger.info("Passenger Name with title->"
//					+ passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[1]")).getText());
			softly.assertThat(
					passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[1]")).getText().trim())
					.isEqualTo(CommonDTO.getInstance().getAllPassengerDTOList().get(passengerIndex).getTitle() + " "
							+ CommonDTO.getInstance().getAllPassengerDTOList().get(passengerIndex).getFirstName() + " "
							+ CommonDTO.getInstance().getAllPassengerDTOList().get(passengerIndex).getLastName());

			logger.info("Passenger Name with title->"
					+ CommonDTO.getInstance().getAllPassengerDTOList().get(passengerIndex).getTitle() + " "
					+ CommonDTO.getInstance().getAllPassengerDTOList().get(passengerIndex).getFirstName() + " "
					+ CommonDTO.getInstance().getAllPassengerDTOList().get(passengerIndex).getLastName());

			logger.info(
					"Sector->" + passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[2]")).getText());
			softly.assertThat(
					passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[2]")).getText().trim().length())
					.isGreaterThan(0);
			
			logger.info(
					"PNR No->" + passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[3]")).getText());
			softly.assertThat(
					passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[3]")).getText().trim().length())
					.isGreaterThan(0);
			
			
			

			logger.info("Airline PNR->"
					+ passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[4]")).getText());
			softly.assertThat(
					passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[4]")).getText().trim().length())
					.isGreaterThan(0);

			logger.info("Ticket No->"
					+ passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[5]")).getText());
			softly.assertThat(
					passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[5]")).getText().trim().length())
					.isGreaterThan(0);

			logger.info("Ticket Status->"
					+ passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[6]")).getText());
			softly.assertThat(passenger.findElement(By.xpath("//*[@class='empireFlight_confirmPnrDetail']//tr[@class='ng-star-inserted']/td[6]")).getText()
					.trim().length()).isGreaterThan(0);
			i++;
		}

	
		try {
			// confirmed

			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Confirmed')])[1]")));
			if (driver.findElement(By.xpath(
					"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Confirmed')])[1]"))
					.getText().contains("Your booking")) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.PASS,
						"Ticket booking status is: <b><u>" + driver.findElement(By.xpath(
								"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking')])[1]"))
								.getText() + "</u></b>",
						driver, AosSpecification.scenarioName);
				logger.info("Your booking is Confirmed");
			}

		} catch (Exception e) {
			try {
				// pending

				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
						"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Pending')])[1]")));
				if (driver.findElement(By.xpath(
						"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Pending')])[1]"))
						.getText().contains("Your booking")) {
					LogEvent.logEventWithScreenshot(getExtentTest(), Status.PASS,
							"Ticket booking status is: <b><u>" + driver.findElement(By.xpath(
									"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking')])[1]"))
									.getText() + "</u></b>",
							driver, AosSpecification.scenarioName);
					logger.info("Your booking is Pending");
				}

			} catch (Exception e1) {
				try {
					// on hold

					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
							"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is On hold')])[1]")));
					if (driver.findElement(By.xpath(
							"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is On hold')])[1]"))
							.getText().contains("Your booking")) {
						LogEvent.logEventWithScreenshot(getExtentTest(), Status.PASS,
								"Ticket booking status is: <b><u>" + driver.findElement(By.xpath(
										"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking')])[1]"))
										.getText() + "</u></b>",
								driver, AosSpecification.scenarioName);
						logger.info("Your booking is On hold");
					}

				} catch (Exception e3) {
					logger.info("Ticket booking is unsuccessful");
					LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, "Ticket booking is unsuccessful",
							driver, AosSpecification.scenarioName);
				}
			}

		}

	}

	private WebElement findElement(By xpath) {
		// TODO Auto-generated method stub
		return null;
	}
}
