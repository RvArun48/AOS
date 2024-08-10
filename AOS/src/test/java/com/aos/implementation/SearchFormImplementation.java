package com.aos.implementation;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.aos.utils.CommonUtils;
import com.aos.utils.DateAndTimeUtil;
import com.aos.utils.GenericActions;
import com.aos.utils.LogEvent;
import com.aos.utils.StringUtils;
import com.aventstack.extentreports.Status;

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
			js.executeScript("window.scrollTo(0, 0);");
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
			js.executeScript("window.scrollTo(0, -350);");
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
//			js.executeScript("window.scrollTo(0, -350);");
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
			js.executeScript("window.scrollTo(0, -100);");

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
			js.executeScript("window.scrollTo(0, 0);");

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

			js.executeScript("window.scrollTo(0, -450);");
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
			for (WebElement card : mainFlightListContainer) {
				softly.assertThat(
						new CommonUtils().checkImageLoad(driver.findElement(By.cssSelector("img[alt='Airline Logo']"))))
						.as("Checking the image is loaded - Airline Logo").isTrue();
				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']")).getText().length())
						.isGreaterThan(0);
				logger.info(
						"Fight Name " + card.findElement(By.xpath("//*[@class='empireFlight_FlightNames']")).getText());

//				softly.assertThat(
//						card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText().length())
//						.isGreaterThan(0);
//				logger.info(
//						"LCC " + card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText());

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

				softly.assertThat(card
						.findElement(
								By.xpath("//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
						.getText().length()).isGreaterThan(0);
				logger.info("Stop " + card
						.findElement(
								By.xpath("//*[@class='empireFlight_stopvia empireF_directionTxt ng-star-inserted']"))
						.getText());

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

				softly.assertThat(card.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']"))
						.getText().length()).isGreaterThan(0);
				logger.info("Available seat: " + card
						.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']")).getText());

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

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText().length())
						.isGreaterThan(0);
				logger.info("Refundable: "
						+ card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref']")).getText());

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_details-text']")).getText().length())
						.isGreaterThan(0);
				logger.info("Flight Details: "
						+ card.findElement(By.xpath("//*[@class='empireFlight_details-text']")).getText());

				softly.assertThat(
						card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText().length())
						.isGreaterThan(0);
				logger.info("Departure Calander: "
						+ card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText());

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

			}

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

			// Perform the mouse hover action using Actions class
			Actions actions = new Actions(driver);
			actions.moveToElement(flexiCalendar).perform();
			Thread.sleep(1000);
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Hovering over the calendar", driver,
					getScenarioName());

			flexiCalendar.click();

			Thread.sleep(5000);

			wait.until(ExpectedConditions.elementToBeClickable(homePage.installmentClick));
			homePage.installmentClick.click();

			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Click to Close Installment Clicked", driver,
					getScenarioName());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.CloseInstallmentClick));
			homePage.CloseInstallmentClick.click();

			Thread.sleep(5000);

//					wait.until(ExpectedConditions.elementToBeClickable(homePage.showMoreOption));
//					homePage.showMoreOption.click();
//					Thread.sleep(3000);
//					
//					wait.until(ExpectedConditions.elementToBeClickable(homePage.selectButtonOne));
//					homePage.selectButtonOne.click();
//					Thread.sleep(3000);
//					
//					
//					
//					wait.until(ExpectedConditions.elementToBeClickable(homePage.selectButtonTwo));
//					homePage.selectButtonTwo.click();
//					Thread.sleep(3000);
//					
//					wait.until(ExpectedConditions.elementToBeClickable(homePage.hideMoreOption));
//					homePage.hideMoreOption.click();

//				logger.info("Exception occurred at Installment() -> " + e.getMessage());
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());

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

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.stopFunctionality));
				homePage.stopFunctionality.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getStop());

//				softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			} catch (Exception e) {
				logger.info("Stop validation() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.refundableFunctionality));
				homePage.refundableFunctionality.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getSelectRefundable());

//				softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			} catch (Exception e) {
				logger.info("i_add_advance_search_options() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

//			try {
//			
//			logger.info("Selecting the filter for Airlines: " + bookTicketDTO.getFilterAirlines());
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.filterAirlines));
//			homePage.filterAirlines.click();
//
//			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
//					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]"))));
//			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
//					"Select the airline filter" + bookTicketDTO.getFilterAirlines() + "", driver, getScenarioName());
//
//			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
//					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]")));
//
//			} catch (Exception e) {
//				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
//			}

//			

//			try {
//
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.moreFilters));
//				homePage.moreFilters.click();
//
//				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
//						bookTicketDTO.getAircraftTypes());
//
//				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
//						bookTicketDTO.getDeprtureStop());
//
//				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
//						bookTicketDTO.getDepartureAirport());
//
//				wait.until(ExpectedConditions.elementToBeClickable(homePage.applyMoreFilter));
//				homePage.applyMoreFilter.click();
//
//				Thread.sleep(2000);
//			} catch (Exception e) {
//				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
//			}

			try {

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

			//
//			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(120));
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.rightFlixibleCalander));
//			homePage.rightFlixibleCalander.click();
//			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(120));
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.leftFlixibleCalander));
//			homePage.leftFlixibleCalander.click();
//			Thread.sleep(20000);

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
					///////////////////////////////////////////////////////////////////////////////////////////////

					///////////////////////////////////////////////////////////////////////////////////////////////

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
//
//				
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

//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
//			wait.until(ExpectedConditions.elementToBeClickable(
//					driver.findElement(By.xpath("//*[@class='empireF_fareWrap hover_box ng-star-inserted']"))));
//
//			wait.until(ExpectedConditions.elementToBeClickable(
//					driver.findElement(By.xpath("//*[@class='empireF_srchFarePrice']"))));
//			
//			softly.assertThat(driver.findElement(By.xpath("//*[@class='empireF_srchFarePrice']")).getText().length())
//					.isGreaterThan(0);
//			
//			logger.info(
//					"Flight Price " + driver.findElement(By.xpath("//*[@class='empireF_srchFarePrice']")).getText());
//			WebElement containerElement = (WebElement) ((JavascriptExecutor) driver)
//			        .executeScript("return document.querySelectorAll('.empireF_PCRetuWrap')[1]");
//
//
//			
//			WebElement flexiCalendar = (WebElement) ((JavascriptExecutor) driver)
//			        .executeScript("return document.querySelector('.empireF_fareWrap.hover_box.ng-star-inserted');");
//

			// Perform the mouse hover action using Actions class
			Actions actions = new Actions(driver);
//			actions.moveToElement(flexiCalendar).perform();
//			Thread.sleep(1000);
//			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Hovering over the calendar", driver,
//					getScenarioName());
//
//			flexiCalendar.click();
//
//			Thread.sleep(5000);

			wait.until(ExpectedConditions.elementToBeClickable(homePage.installmentClick));
			homePage.installmentClick.click();

			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Click to Close Installment Clicked", driver,
					getScenarioName());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.CloseInstallmentClick));
			homePage.CloseInstallmentClick.click();

			Thread.sleep(5000);

//					wait.until(ExpectedConditions.elementToBeClickable(homePage.showMoreOption));
//					homePage.showMoreOption.click();
//					Thread.sleep(3000);
//					
//					wait.until(ExpectedConditions.elementToBeClickable(homePage.selectButtonOne));
//					homePage.selectButtonOne.click();
//					Thread.sleep(3000);
//					
//					
//					
//					wait.until(ExpectedConditions.elementToBeClickable(homePage.selectButtonTwo));
//					homePage.selectButtonTwo.click();
//					Thread.sleep(3000);
//					
//					wait.until(ExpectedConditions.elementToBeClickable(homePage.hideMoreOption));
//					homePage.hideMoreOption.click();

//				logger.info("Exception occurred at Installment() -> " + e.getMessage());
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());

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
			int minValue = 10800; // Minimum possible value of the slider
			int maxValue = 80100; // Maximum possible value of the slider
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
			Thread.sleep(2000);

			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-value'])[1]
			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-high'])[1]
			softly.assertThat(DateAndTimeUtil.compareTimes(CommonDTO.getInstance().getFlightStartTime(), sliderMinTime,
					CommonDTO.getInstance().getFlightEndTime(), sliderMaxTime));

//			WebElement slider1 = driver.findElement(
//					By.xpath("(//*[@class='ngx-slider-span ngx-slider-bar-wrapper ngx-slider-full-bar'])[2]"));
//			// WebElement sliderRight =
//			// driver.findElement(By.xpath("(//*[@class='ngx-slider-span
//			// ngx-slider-bar-wrapper ngx-slider-right-out-selection'])[1]"));
//
//			WebElement minPointer1 = driver.findElement(
//					By.xpath("(//*[@class='ngx-slider-span ngx-slider-pointer ngx-slider-pointer-min'])[2]"));
//			WebElement maxPointer1 = driver.findElement(
//					By.xpath("(//*[@class='ngx-slider-span ngx-slider-pointer ngx-slider-pointer-max'])[2]"));
//
//			// Determine the width of the slider
//			int sliderWidth1 = slider.getSize().getWidth();
//
//			// Define the desired minimum and maximum values
//			int desiredMinValue1 = 15000; // Example minimum value
//			int desiredMaxValue1 = 25000; // Example maximum value
//			int minValue1 = 30900; // Minimum possible value of the slider
//			int maxValue1 = 72900; // Maximum possible value of the slider
//			int sliderRange1 = maxValue - minValue;
//
//			// Calculate the offset for the minimum pointer
//			// Calculate the offset for the minimum pointer
//			int minOffset1 = (desiredMinValue - minValue) * sliderWidth / sliderRange;
//
//			// Move the minimum pointer
//			// Actions actions = new Actions(driver);
//			actions.clickAndHold(minPointer).moveByOffset(minOffset, 0).release().perform();
//
//			// Recalculate the width of the slider after moving the minimum pointer
//			sliderWidth = slider.getSize().getWidth();
//
//			// Calculate the offset for the maximum pointer relative to its current position
//			int maxOffset1 = (desiredMaxValue - minValue) * sliderWidth / sliderRange;
//			int currentMaxPosition1 = maxPointer.getLocation().getX();
//			int currentMinPosition1 = minPointer.getLocation().getX();
//			int maxMoveOffset1 = maxOffset - (currentMaxPosition - currentMinPosition);
//
//			Thread.sleep(1500);
//			// Move the maximum pointer
//			actions.clickAndHold(maxPointer).moveByOffset(maxMoveOffset, 0).release().perform();
//			Thread.sleep(1000);
//			LocalTime sliderMinTime1 = LocalTime.parse(driver
//					.findElement(
//							By.xpath("(//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-value'])[2]"))
//					.getText());
//			LocalTime sliderMaxTime1 = LocalTime.parse(driver
//					.findElement(By.xpath("(//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-high'])[2]"))
//					.getText());
//
//			logger.info("sliderMinTime->" + sliderMinTime);
//			logger.info("sliderMaxTime->" + sliderMaxTime);
//
//			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-value'])[1]
//			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-high'])[1]
//			softly.assertThat(DateAndTimeUtil.compareTimes(CommonDTO.getInstance().getFlightStartTime(), sliderMinTime,
//					CommonDTO.getInstance().getFlightEndTime(), sliderMaxTime));

//			homePage.durationFunctionality.click();

			wait.until(ExpectedConditions.elementToBeClickable(homePage.stopFunctionality));
			homePage.stopFunctionality.click();

			homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getStop());

//				softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.refundableFunctionality));
				homePage.refundableFunctionality.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getSelectRefundable());

//				softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			} catch (Exception e) {
				logger.info("i_add_advance_search_options() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

//			try {
//			
//			logger.info("Selecting the filter for Airlines: " + bookTicketDTO.getFilterAirlines());
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.filterAirlines));
//			homePage.filterAirlines.click();
//
//			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
//					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]"))));
//			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
//					"Select the airline filter" + bookTicketDTO.getFilterAirlines() + "", driver, getScenarioName());
//
//			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
//					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]")));
//
//			} catch (Exception e) {
//				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
//			}
			wait.until(ExpectedConditions.elementToBeClickable(homePage.moreFilters));
			homePage.moreFilters.click();

			try {

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getAircraftTypes());

				Thread.sleep(2000);
			} catch (Exception e) {
				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}
			try {

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getDeprtureStop());

				Thread.sleep(2000);
			} catch (Exception e) {
				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}
//			try {
//
//				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
//						bookTicketDTO.getDepartureAirport());
//				
//				Thread.sleep(2000);
//			} catch (Exception e) {
//				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
//			}

			wait.until(ExpectedConditions.elementToBeClickable(homePage.applyMoreFilter));
			homePage.applyMoreFilter.click();

			try {

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
			softly.assertThat(CommonUtils.isDescendingOrder(priceList)).info
					.description("Checking Price from high to low");
			logger.info("is high to low->" + CommonUtils.isDescendingOrder(priceList));

//			checking Depart Duration
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
			driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
			driver.findElement(By
					.xpath("//*[contains(@class,'dropdown-item') and contains(text(),' Depart - Duration Shortest ')]"))
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
					System.err.println("Invalid number format: " + element.getText());
				}
			}
			logger.info("is depart low to high->" + CommonUtils.isDepartDuration(durationList));

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
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

					if (validationLimitCount > 0) {
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

			Actions actions = new Actions(driver);

			wait.until(ExpectedConditions.elementToBeClickable(homePage.installmentClick));
			homePage.installmentClick.click();

			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Click to Close Installment Clicked", driver,
					getScenarioName());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.CloseInstallmentClick));
			homePage.CloseInstallmentClick.click();

			Thread.sleep(5000);

			homePage.timeFunctionality.click();
			Thread.sleep(3000);
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
			int desiredMinValue = 700; // Example minimum value
			int desiredMaxValue = 1000; // Example maximum value
			int minValue = 542; // Minimum possible value of the slider
			int maxValue = 1766; // Maximum possible value of the slider
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
			Thread.sleep(3000);

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.stopFunctionality));
				homePage.stopFunctionality.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getStop());

//				softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			} catch (Exception e) {
				logger.info("Stop validation() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.refundableFunctionality));
				homePage.refundableFunctionality.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getSelectRefundable());

//				softly.assertThat(homePage.stopValidation.getText()).isEqualTo(	bookTicketDTO.getStop());

			} catch (Exception e) {
				logger.info("i_add_advance_search_options() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

//			try {
//			
//			logger.info("Selecting the filter for Airlines: " + bookTicketDTO.getFilterAirlines());
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.filterAirlines));
//			homePage.filterAirlines.click();
//
//			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
//					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]"))));
//			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO,
//					"Select the airline filter" + bookTicketDTO.getFilterAirlines() + "", driver, getScenarioName());
//
//			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
//					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]")));
//
//			} catch (Exception e) {
//				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
//				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
//			}

//			

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.moreFilters));
				homePage.moreFilters.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getAircraftTypes());

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getDeprtureStop());

//				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
//						bookTicketDTO.getDepartureAirport());

				wait.until(ExpectedConditions.elementToBeClickable(homePage.applyMoreFilter));
				homePage.applyMoreFilter.click();

				Thread.sleep(2000);
			} catch (Exception e) {
				logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
			}

			try {

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

//			checking Depart Duration
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
			driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
			driver.findElement(By
					.xpath("//*[contains(@class,'dropdown-item') and contains(text(),' Depart - Duration Shortest ')]"))
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
//					System.err.println("Invalid number format: " + element.getText());
				}
			}
			logger.info("Depart - Duration Shortest->" + CommonUtils.isDepartDuration(durationList));

//			checking Depart Time

			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='empireF_SortBy']"))));
			driver.findElement(By.xpath("//*[@class='empireF_SortBy']")).click();
			driver.findElement(
					By.xpath("//*[contains(@class,'dropdown-item') and contains(text(),' Depart - Time Earliest ')]"))
					.click();

			List<WebElement> departTimeElements = driver
					.findElements(By.xpath("//span[@class='empireFlight_time include']"));
			List<Double> timeList = new ArrayList<>();

			// Extract text from each WebElement, convert to Double, and add to durationList
			for (WebElement element : departTimeElements) {
				try {
					String text = element.getText().trim();
					Double time = Double.parseDouble(text);
					timeList.add(time);
				} catch (NumberFormatException e) {
					// Handle the case where the text cannot be parsed into a Double
//					System.err.println("Invalid number format: " + element.getText());
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
			List<WebElement> tabs = (List<WebElement>) jsExecutor.executeScript(
					"return Array.from(document.querySelectorAll('.empireFlight_multicityCardMob.ng-star-inserted'))");

			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());

			for (WebElement mainFlight : mainFlightListContainer) {
				wait.until(ExpectedConditions.elementToBeClickable(mainFlight));

				for (WebElement tab : tabs) {
					// Check if image is loaded
					Boolean isImageLoaded = (Boolean) jsExecutor.executeScript(
							"return document.querySelector(\"img[alt='Airline Logo']\") != null && document.querySelector(\"img[alt='Airline Logo']\").complete && document.querySelector(\"img[alt='Airline Logo']\").naturalHeight > 0");
					softly.assertThat(isImageLoaded).as("Checking the image is loaded - Airline Logo").isTrue();

					// Get text and validate for various elements
					String flightName = (String) jsExecutor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityLogoNameContent').textContent.trim();",
							tab);
					softly.assertThat(flightName.length()).isGreaterThan(0);
					logger.info("Flight Name " + flightName);

					String lcc = "";
					try {
						lcc = (String) jsExecutor.executeScript(
								"return arguments[0].querySelector('.LCC_Wrapper.ng-star-inserted').textContent.trim();",
								tab);
						softly.assertThat(lcc.length()).isGreaterThan(0);
						logger.info("LCC " + lcc);
					} catch (Exception e) {
						// Handle exception
					}

					String flightStartTime = (String) jsExecutor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemTime').textContent.trim();",
							tab);
					softly.assertThat(flightStartTime.length()).isGreaterThan(0);
					logger.info("Flight Start Time " + flightStartTime);

					String tripContent = (String) jsExecutor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityCardTripContent').textContent.trim();",
							tab);
					softly.assertThat(tripContent.length()).isGreaterThan(0);
					logger.info("From and To  " + tripContent);

					String stop = "";
					
					try {
//						stop = (String) jsExecutor.executeScript(
//								"return arguments[0].querySelector('.empireFlight_stopvia.empireF_directionTxt.ng-star-inserted').textContent.trim();",
//								tab);
//						softly.assertThat(stop.length()).isGreaterThan(0);
//						logger.info("Stop " + stop);
					} catch (Exception e) {
						stop = (String) jsExecutor.executeScript(
								"return arguments[0].querySelector('.empireFlight_stopvia.ng-star-inserted').textContent.trim();",
								tab);
						softly.assertThat(stop.length()).isGreaterThan(0);
						logger.info("Stop " + stop);
					}

					String cityName = (String) jsExecutor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemCityName').textContent.trim();",
							tab);
					softly.assertThat(cityName.length()).isGreaterThan(0);
					logger.info("Source: " + cityName);

					String time = (String) jsExecutor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityTime').textContent.trim();",
							tab);
					softly.assertThat(time.length()).isGreaterThan(0);
					logger.info("Time: " + time);
					
//
//					String amountText = (String) jsExecutor.executeScript(
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
//					String installmentWrap = (String) jsExecutor.executeScript(
//							"return arguments[0].querySelector('.empireF_installmentwrap.ng-star-inserted').textContent.trim();",
//							tab);
//					softly.assertThat(installmentWrap.length()).isGreaterThan(0);
//					logger.info("Installments: " + installmentWrap);

					String endTime = (String) jsExecutor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemTime').textContent.trim();",
							tab);
					softly.assertThat(endTime.length()).isGreaterThan(0);
					logger.info("End Time: " + endTime);

					String destination = (String) jsExecutor.executeScript(
							"return arguments[0].querySelector('.empireFlight_multicityItemCityName').textContent.trim();",
							tab);
					softly.assertThat(destination.length()).isGreaterThan(0);
					logger.info("Destination: " + destination);

//					String refundable = (String) jsExecutor.executeScript(
//							"return arguments[0].querySelector('.empireFlight_multicityRefundTxt.ref.ng-star-inserted').textContent.trim();",
//							tab);
//					softly.assertThat(refundable.length()).isGreaterThan(0);
//					logger.info("Refundable: " + refundable);
//
//					String flightDetails = (String) jsExecutor.executeScript(
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
					//softly.assertThat(true).isEqualTo(CommonUtils.isDepartDuration(durationList));
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
}
