package com.aos.specification;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.base.TestRunner;
import com.aos.implementation.AosImplementation;
import com.aos.model.BookTicketDTO;
import com.aos.model.PassengerDetailsDTO;
import com.aos.pageObjects.*;
import com.aos.utils.DateUtil;
import com.aos.utils.JsonToGson;
import com.aos.utils.LogEvent;
import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.reflect.TypeToken;

import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AosSpecification extends TestRunner {

	public ExtentReports report = null;
	public static ExtentTest extentTest = null;
	public static String scenarioName = null;
	HomePage homePage = null;
	SearchResultsPage searchResultsPage = null;
	PassengerDetailsPage passengerDetailsPage = null;
	public static final Logger logger = LogManager.getLogger(AosSpecification.class);
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	@Before
	public void setup(Scenario scenario) {
		scenarioName = scenario.getName();

		report = super.setUp(scenario);
	}

	@Given("I want to open the application")
	public void openWebsite() {
		String baseUrl = ReadProperty.getPropValues("BASE_URL", "config");
		try {

			extentTest = report.createTest(this.scenarioName);

			driver.get(baseUrl);
			Thread.sleep(2000);
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Home Page verification", driver,
					this.scenarioName);

		} catch (Exception e) {
			System.out.println("Exception occured at openWebsite()" + e.getLocalizedMessage());
		}

	}

	@And("^book a flight ticket for \"(.+)\"$")
	public void bookTicket(String data) throws IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {

			Type bt = new TypeToken<BookTicketDTO>() {
				private static final long serialVersionUID = 1L;
			}.getType();
			BookTicketDTO bookTicketDTO = (BookTicketDTO) JsonToGson.convertToObj("book_ticket", data, bt);
			Type pd = new TypeToken<PassengerDetailsDTO>() {
				private static final long serialVersionUID = -7767108171943612798L;

			}.getType();

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

			homePage = PageFactory.initElements(driver, HomePage.class);
			searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
			passengerDetailsPage = PageFactory.initElements(driver, PassengerDetailsPage.class);

			logger.info("Waiting for one way tab to be clickable");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.oneWayTab));
			homePage.oneWayTab.click();

			logger.info("Enter the origin input: " + bookTicketDTO.getFrom());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.from));
			homePage.from.click();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.fromInput));
			homePage.fromInput.sendKeys(bookTicketDTO.getFrom());
			homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
					bookTicketDTO.getFrom());

			wait.until(ExpectedConditions.elementToBeClickable(homePage.to));
			homePage.to.click();

			logger.info("Enter the Destination input: " + bookTicketDTO.getTo());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.toInput));
			homePage.toInput.sendKeys(bookTicketDTO.getTo());
			try {
				homePage.getElementByXpath(driver, "(//*[@class='fs_menuBadge' and contains(text(),'${token}')])[1]",
						bookTicketDTO.getTo());
			} catch (Exception e) {
				// TODO: handle exception
			}

			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Entering source and destination", driver,
					scenarioName);

			wait.until(ExpectedConditions.elementToBeClickable(homePage.departureCalendar));
			homePage.departureCalendar.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
					By.xpath("(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='"
							+ DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "d")
							+ "'])[1]"))));
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Choosing travel date", driver, scenarioName);
			logger.info("Selecting the Departure date: "
					+ DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "dd MMM yyyy"));

			homePage.getElementByXpath(driver,
					"(//span[@draggable='false' and  not(contains(@class,'disabled'))]/span[text()='${token}'])[1]",
					DateUtil.addDaysToCurrentDate(Integer.parseInt(bookTicketDTO.getDate()), "d"));

			
			wait.until(ExpectedConditions.elementToBeClickable(homePage.passengerCountDropDown));
			
	        // Scroll to the top of the page
	        js.executeScript("window.scrollTo(0, 0);");
	        Thread.sleep(5000);

			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the passenger count", driver,
					scenarioName);
			homePage.passengerCountDropDown.click();
			wait.until(ExpectedConditions.elementToBeClickable(homePage.departureCalendar));

			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Select the departure date", driver, scenarioName);

			logger.info("Selecting the class: " + bookTicketDTO.getPassengerClass());

			if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("Economy")) {
				homePage.passengerClass_Economy.click();
			} else if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("Premimum Economy")) {
				homePage.passengerClass_PremiumEconomy.click();
			} else if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("Business Class")) {
				homePage.passengerClass_BusinessClass.click();
			} else if (bookTicketDTO.getPassengerClass().equalsIgnoreCase("First class")) {
				homePage.passengerClass_FirstClass.click();
			}

//passenger count
			AosImplementation.addPassengersCount(bookTicketDTO, executor, wait);

			Thread.sleep(10000);

			logger.info("Waiting for search Flight Button to be clickable");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.searcFlightButton));
			homePage.searcFlightButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(searchResultsPage.searchResultCard));
			searchResultsPage.sourceDestinationResultTitle.click();
			wait.until(ExpectedConditions.elementToBeClickable(searchResultsPage.sourceDestinationResultTitle));
// validation point

			logger.info("Checking the origin and Destination as per search");
			if (!searchResultsPage.sourceDestinationResultTitle.getText()
					.contains(bookTicketDTO.getRelevantKeywordFrom())
					|| !searchResultsPage.sourceDestinationResultTitle.getText()
							.contains(bookTicketDTO.getRelevantKeywordTo())) {
				LogEvent.logEventWithScreenshot(extentTest, Status.FAIL,
						"Search result data is not relevant to the search term", driver, scenarioName);
				Assert.assertTrue("Search result data is not relevant to the search term: ", false);
			} else {
				LogEvent.logEventWithScreenshot(extentTest, Status.PASS,
						"Checkpoint -1: Search result data is relevant to the search term", driver, scenarioName);
			}

			// Loading test data for adult, child and infant passengers
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

			logger.info("adultPassengersCount->" + adultPassengersCount);
			logger.info("childPassengersCount->" + childPassengersCount);
			logger.info("infantPassengersCount->" + infantPassengersCount);

// After Search

			logger.info("Selecting the filter for Airlines: " + bookTicketDTO.getFilterAirlines());
			wait.until(ExpectedConditions.elementToBeClickable(homePage.filterAirlines));
			homePage.filterAirlines.click();

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(
					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]"))));
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO,
					"Select the airline filter" + bookTicketDTO.getFilterAirlines() + "", driver, scenarioName);

			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
					"(//*[@class='mdc-label' and contains(text(),'" + bookTicketDTO.getFilterAirlines() + "')])[1]")));

			Thread.sleep(10000);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			logger.info("Selecting the Flight");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.selectAirlinesbooking));
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Select the Flight", driver, scenarioName);
			homePage.selectAirlinesbooking.click();

			try {
				wait.until(ExpectedConditions.elementToBeClickable(homePage.continueBooking));
				homePage.continueBooking.click();
				LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Pop up found", driver, scenarioName);
			} catch (Exception e) {
				LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "No pop up found", driver, scenarioName);
			}

			logger.info("Click the Booknow Button");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.Booknow));
			homePage.Booknow.click();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Click the Booknow button", driver, scenarioName);

			Thread.sleep(5000);
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//*[@class='empireF_TravelerFormPerDetail']"))));

			List<WebElement> passengerDetailsContainer = driver
					.findElements(By.xpath("//*[@formarrayname='clientDetails']"));
			passengerDetailsPage = PageFactory.initElements(driver, PassengerDetailsPage.class);
			Actions actions = new Actions(driver);

			int totalPassengerCount = adultPassengersCount + childPassengersCount + infantPassengersCount;

			System.out.println("totalPassengerCount->" + totalPassengerCount);

			int i = 0;
			int dataIndex = 0;
			System.out.println("passengerDetailsContainer->" + passengerDetailsContainer.size());

			while (i < adultPassengersCount) {
				System.out.println("adultPassengerDTOList->" + adultPassengerDTOList.size());
				AosImplementation.addPassengerDetails(executor, wait, adultPassengerDTOList, passengerDetailsContainer,
						actions, i, dataIndex);
				dataIndex++;
				i++;
			}
			dataIndex = 0;
			while (dataIndex < childPassengersCount) {
				System.out.println("childPassengerDTOList->" + childPassengerDTOList.size());
				AosImplementation.addPassengerDetails(executor, wait, childPassengerDTOList, passengerDetailsContainer,
						actions, i, dataIndex);
				i++;
				dataIndex++;
			}
			dataIndex = 0;
			while (dataIndex < infantPassengersCount) {
				System.out.println("infantPassengerDTOList->" + infantPassengerDTOList.size());
				AosImplementation.addPassengerDetails(executor, wait, infantPassengerDTOList, passengerDetailsContainer,
						actions, i, dataIndex);
				i++;
				dataIndex++;
			}

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

			logger.info("Click the continue To Payment Button");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.continueToPayment));
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Click to continue payment", driver, scenarioName);
			homePage.continueToPayment.click();
			// Thread.sleep(10000);

			logger.info("Click the process To Pay Button");
			wait.until(ExpectedConditions.elementToBeClickable(homePage.processToPay));
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Click to Process to pay", driver, scenarioName);
			homePage.processToPay.click();

			// Thread.sleep(10000);

//			Set<String> windowHandles = driver.getWindowHandles();
//	        List<String> tabs = new ArrayList<>(windowHandles);
//
//	        // Switch to the new tab
//	        driver.switchTo().window(tabs.get(1));

			logger.info("Enter the card No: " + bookTicketDTO.getCardNo());
			wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.cardNo));
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Card Detail Page", driver, scenarioName);
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

			AosImplementation.verifyTicketBookingStatus(wait);
		} catch (Exception e) {
			e.printStackTrace();
			LogEvent.logEventWithScreenshot(extentTest, Status.FAIL,
					"Exception occured at bookTicket()->" + e.toString(), driver, this.scenarioName);
			System.out.println("Exception occured at bookTicket()" + e.getMessage());
		}
	}

	@After
	public void afterScenario() {
		tearDown();
	}
}
