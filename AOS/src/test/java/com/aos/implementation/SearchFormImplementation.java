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

			logger.info("Enter the Destination input: " + bookTicketDTO.getTo());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.toInput));
			homePage.toInput.sendKeys(bookTicketDTO.getTo());
			homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
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

//			homePage.fromsegmenttwo.clear();
//			wait.until(ExpectedConditions.elementToBeClickable(homePage.fromsegmenttwo));
//			homePage.fromsegmenttwo.sendKeys(bookTicketDTO.getFromTwo());
//			homePage.getElementByXpath(driver, "(//*[@class='dropdown-item fs_menuItem ng-star-inserted']/child::*[contains(text(),'${token}')])[1]",
//					bookTicketDTO.getFromTwo());

			logger.info("Enter the Destination input: " + bookTicketDTO.getToTwo());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.toSegmentTwo));
			homePage.toSegmentTwo.sendKeys(bookTicketDTO.getToTwo());
			homePage.getElementByXpath(driver,
					"//*[@class='fs_menuItemContent']//following::*[contains(text(),'${token}')]",
					bookTicketDTO.getTo().split(",")[1].trim(), homePage.fromToContainer_2);

//			try {
//				homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
//						bookTicketDTO.getToTwo());
//			} catch (Exception e) {
//				// TODO: handle exception
//			}

			Thread.sleep(5000);

//			wait.until(ExpectedConditions.elementToBeClickable(homePage.datesegmenttwo));
//			homePage.datesegmenttwo.click();
//
//			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
//					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
//							+ DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateTwo()), "d")
//							+ "'])[2]"))));
//			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Choosing travel date", driver,
//					getScenarioName());
//			logger.info("Selecting the Departure date: "
//					+ DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateTwo()), "dd MMM yyyy"));
//
//			homePage.getElementByXpath(driver,
//					"(//span[@draggable='false' and not(contains(@class, 'disabled'))]/span[text()='${token}'])[1]",
//					DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDateTwo()), "d"));
//				Thread.sleep(5000);

		} catch (Exception e) {
			logger.info("Exception occurred at i_enter_the_source_as() -> " + e.getMessage());
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
		}

	}

	public void I_enter_the_segment_three() throws InterruptedException {

		BookTicketDTO bookTicketDTO = CommonDTO.getInstance().getBookTicketDTO();

//		
//		homePage.fromsegmentthree.clear();
//		wait.until(ExpectedConditions.elementToBeClickable(homePage.fromsegmentthree));
//		homePage.fromsegmenttwo.sendKeys(bookTicketDTO.getFromThree());
//
//		homePage.getElementByXpath(driver, "(//*[@class='dropdown-item fs_menuItem ng-star-inserted']/child::*[contains(text(),'${token}')])[2]",
//				bookTicketDTO.getFromThree());

		logger.info("Enter the Destination input: " + bookTicketDTO.getToThree());
		wait.until(ExpectedConditions.elementToBeClickable(homePage.tosegmentthree));
		homePage.tosegmentthree.sendKeys(bookTicketDTO.getToThree());
		homePage.getElementByXpath(driver,
				"(//*[@class='dropdown-item fs_menuItem ng-star-inserted']/child::*[contains(text(),'${token}')])[1]",
				bookTicketDTO.getDateThree());

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void I_enter_the_segment_four() {
		// TODO Auto-generated method stub

	}

	public void I_enter_the_segment_five() {
		// TODO Auto-generated method stub

	}

	public void I_enter_the_segment_six() {
		// TODO Auto-generated method stub

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
			int desiredMaxValue = 65000; // Example maximum value
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

			try {

				wait.until(ExpectedConditions.elementToBeClickable(homePage.moreFilters));
				homePage.moreFilters.click();

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getAircraftTypes());

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getDeprtureStop());

				homePage.getElementByXpath(driver, "(//*[@class='mdc-label' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getDepartureAirport());

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
			List<WebElement> mainFlightListContainer = driver
					.findElements(By.xpath("//*[@class='empireFlight_main-box-listing ng-star-inserted']"));
			logger.info("mainFlightListContainer ->" + mainFlightListContainer.size());
			for (WebElement card : mainFlightListContainer) {

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

				softly.assertThat(
						card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref ng-star-inserted']"))
								.getText().length())
						.isGreaterThan(0);
				logger.info("Refundable " + " -> "
						+ card.findElement(By.xpath("//*[@class='empireFlight_refund-text ref ng-star-inserted']"))
								.getText());

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

					tripContainer.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"));

					softly.assertThat(tripContainer
							.findElement(By.xpath(
									"(//*[@class='empireFlight_time include ng-star-inserted'])[" + (i + 1) + "]"))
							.getText().length()).isGreaterThan(0);
					logger.info("Baggage Details " + (i + 1) + " -> "
							+ tripContainer
									.findElement(By.xpath("//*[@class='empireFlight_time include ng-star-inserted']"))
									.getText());

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

//				softly.assertThat(
//						card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText().length())
//						.isGreaterThan(0);
//				logger.info(
//						"LCC " + card.findElement(By.xpath("//*[@class='LCC_Wrapper ng-star-inserted']")).getText());

//				softly.assertThat(card.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']"))
//						.getText().length()).isGreaterThan(0);
//				logger.info("Available seat: " + card
//						.findElement(By.xpath("//*[@class='empireFlight_seatsleft ng-star-inserted']")).getText());

//
//				

//				softly.assertThat(
//						card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText().length())
//						.isGreaterThan(0);
//				logger.info("Departure Calander: "
//						+ card.findElement(By.xpath("(//span[contains(text(),'Departure')])[1]")).getText());

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

			}

//			
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//			wait.until(ExpectedConditions
//					.elementToBeClickable(driver.findElement(By.xpath("//*[@class='FFC-box ng-star-inserted']"))));
//
//
//			softly.assertThat(driver.findElement(By.xpath("//*[@class='FFC-search-fare-price']")).getText().length())
//					.isGreaterThan(0);
//			logger.info(
//					"Flight Price " + driver.findElement(By.xpath("//*[@class='FFC-search-fare-price']")).getText());
//			WebElement containerElement = (WebElement) ((JavascriptExecutor) driver)
//					.executeScript("return document.querySelector('.empireF_PricingCalenRetuWrapper')");
//
//			// Use JavaScript Executor to find the child element within the container
//			WebElement flexiCalendar = (WebElement) ((JavascriptExecutor) driver).executeScript(
//					"return arguments[0].querySelector('.FFC-search-fare-amount.FFC-search-fare-active-amount')",
//					containerElement);
//
//			// Perform the mouse hover action using Actions class
//			Actions actions = new Actions(driver);
//			actions.moveToElement(flexiCalendar).perform();
//			Thread.sleep(1000);
//			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, "Hovering over the calendar", driver,
//					getScenarioName());
//			
//			       flexiCalendar.click();
//			

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

//			homePage.timeFunctionality.click();
//			Thread.sleep(5000);
//			// Locate the slider element
//			WebElement slider = driver.findElement(
//					By.xpath("(//*[@class='ngx-slider-span ngx-slider-bar-wrapper ngx-slider-full-bar'])[1]"));
//			// WebElement sliderRight =
//			// driver.findElement(By.xpath("(//*[@class='ngx-slider-span
//			// ngx-slider-bar-wrapper ngx-slider-right-out-selection'])[1]"));
//
//			WebElement minPointer = driver.findElement(
//					By.xpath("(//*[@class='ngx-slider-span ngx-slider-pointer ngx-slider-pointer-min'])[1]"));
//			WebElement maxPointer = driver.findElement(
//					By.xpath("(//*[@class='ngx-slider-span ngx-slider-pointer ngx-slider-pointer-max'])[1]"));
//
//			// Determine the width of the slider
//			int sliderWidth = slider.getSize().getWidth();
//
//			// Define the desired minimum and maximum values
//			int desiredMinValue = 25000; // Example minimum value
//			int desiredMaxValue = 65000; // Example maximum value
//			int minValue = 10800; // Minimum possible value of the slider
//			int maxValue = 80100; // Maximum possible value of the slider
//			int sliderRange = maxValue - minValue;
//
//			// Calculate the offset for the minimum pointer
//			// Calculate the offset for the minimum pointer
//			int minOffset = (desiredMinValue - minValue) * sliderWidth / sliderRange;
//
//			// Move the minimum pointer
//			// Actions actions = new Actions(driver);
//			actions.clickAndHold(minPointer).moveByOffset(minOffset, 0).release().perform();
//
//			// Recalculate the width of the slider after moving the minimum pointer
//			sliderWidth = slider.getSize().getWidth();
//
//			// Calculate the offset for the maximum pointer relative to its current position
//			int maxOffset = (desiredMaxValue - minValue) * sliderWidth / sliderRange;
//			int currentMaxPosition = maxPointer.getLocation().getX();
//			int currentMinPosition = minPointer.getLocation().getX();
//			int maxMoveOffset = maxOffset - (currentMaxPosition - currentMinPosition);
//
//			Thread.sleep(1500);
//			// Move the maximum pointer
//			actions.clickAndHold(maxPointer).moveByOffset(maxMoveOffset, 0).release().perform();
//			Thread.sleep(1000);
//			LocalTime sliderMinTime = LocalTime.parse(driver
//					.findElement(
//							By.xpath("(//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-value'])[1]"))
//					.getText());
//			LocalTime sliderMaxTime = LocalTime.parse(driver
//					.findElement(By.xpath("(//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-high'])[1]"))
//					.getText());
//
//			logger.info("sliderMinTime->" + sliderMinTime);
//			logger.info("sliderMaxTime->" + sliderMaxTime);
//
//			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-value'])[1]
//			// (//*[@class='ngx-slider-span ngx-slider-bubble ngx-slider-model-high'])[1]
//			softly.assertThat(DateAndTimeUtil.compareTimes(CommonDTO.getInstance().getFlightStartTime(), sliderMinTime,
//					CommonDTO.getInstance().getFlightEndTime(), sliderMaxTime));

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
//				} catch (Exception e) {
//					logger.info("Selecting the filter for Airlines() -> " + e.getMessage());
//					LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.toString(), driver, getScenarioName());
//				}

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

}
