package com.aos.specification;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.base.TestRunner;
import com.aos.implementation.AosImplementation;
import com.aos.implementation.SearchFormImplementation;
import com.aos.model.BookTicketDTO;
import com.aos.model.CommonDTO;
import com.aos.model.PassengerDetailsDTO;
import com.aos.pageObjects.HomePage;
import com.aos.pageObjects.PassengerDetailsPage;
import com.aos.pageObjects.SearchResultsPage;
import com.aos.utils.DateUtil;
import com.aos.utils.JsonToGson;
import com.aos.utils.LogEvent;
import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.reflect.TypeToken;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchFormSpecification extends TestRunner {

	public ExtentReports report = null;
	public static ExtentTest extentTest = null;
	public static String scenarioName = null;
	HomePage homePage = null;
	SearchResultsPage searchResultsPage = null;
	PassengerDetailsPage passengerDetailsPage = null;
	public static final Logger logger = LogManager.getLogger(SearchFormSpecification.class);
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	@Before
	public void setup(Scenario scenario) {
		scenarioName = scenario.getName();
		report = super.setUp(scenario);
	}

	@Given("^I setup the test data with \"(.*?)\"$")
	public void testDataSetup(String keyData) {
		Type bt = new TypeToken<BookTicketDTO>() {
			private static final long serialVersionUID = 1L;
		}.getType();
		BookTicketDTO bookTicketDTO = (BookTicketDTO) JsonToGson.convertToObj("book_ticket", keyData, bt);
		Type pd = new TypeToken<PassengerDetailsDTO>() {
			private static final long serialVersionUID = -7767108171943612798L;

		}.getType();

		CommonDTO.getInstance().setBookTicketDTO(bookTicketDTO);
	}

	@Given("I am on the flight booking page")
	public void i_am_on_the_flight_booking_page() {

		try {
			new AosSpecification().openWebsite();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the source")
	public void i_enter_the_source_as() {
		try {
			new SearchFormImplementation().i_enter_the_source_as();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the destination")
	public void i_enter_the_destination_as() {
		try {
			new SearchFormImplementation().i_enter_the_destination_as();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the departure date")
	public void i_enter_the_departure_date_as() {
		try {
			new SearchFormImplementation().i_enter_the_departure_date_as();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I select the flight category")
	public void i_select_the_flight_category_as() {
		try {
			new SearchFormImplementation().i_select_the_flight_category_as();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I add passengers")
	public void I_add_passengers() {
		try {
			new SearchFormImplementation().I_add_passengers();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click on the search button")
	public void i_click_on_the_search_button() {
		try {
			new SearchFormImplementation().i_click_on_the_search_button();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("I add advance search options")
	public void i_add_advance_search_options() {
		try {
			new SearchFormImplementation().i_add_advance_search_options();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I should see the search results page")
	public void i_should_see_the_search_results_page() {

		boolean isResultsDisplayed = driver.findElement(By.id("search-results")).isDisplayed();
		assert (isResultsDisplayed);
		driver.quit();
	}

	@Then("I select the trip type")
	public void I_select_the_trip_as() {
		try {
			new SearchFormImplementation().I_select_the_trip_as();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the  preferred airlines")
    public void i_enter_the_preferred_airlines() {
    	try {
    		new SearchFormImplementation().i_enter_the_preferred_airlines();}catch(

	Exception e)
	{
		// TODO: handle exception
	}

	}

	@After
	public void afterScenario() {
		tearDown();
	}
}
