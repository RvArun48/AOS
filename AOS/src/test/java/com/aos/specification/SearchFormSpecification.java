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
import com.aos.utils.DateAndTimeUtil;
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

	@Given("^I setup the edit data with \"(.*?)\"$")
	public void testDataSetup1(String keyData) {
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
			new SearchFormImplementation().i_enter_the_preferred_airlines();
		} catch (

		Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I change the currency type")
	public void I_change_the_currency_type() {
		try {
			new SearchFormImplementation().I_change_the_currency_type();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I change the language")
	public void I_change_the_language() {
		try {
			new SearchFormImplementation().I_change_the_language();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the segment two")
	public void I_enter_the_segment_two() {
		try {
			new SearchFormImplementation().I_enter_the_segment_two();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the segment three")
	public void I_enter_the_segment_three() {
		try {
			new SearchFormImplementation().I_enter_the_segment_three();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the segment four")
	public void I_enter_the_segment_four() {
		try {
			new SearchFormImplementation().I_enter_the_segment_four();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the segment five")
	public void I_enter_the_segment_five() {
		try {
			new SearchFormImplementation().I_enter_the_segment_five();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the segment six")
	public void I_enter_the_segment_six() {
		try {
			new SearchFormImplementation().I_enter_the_segment_six();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click the add another city")
	public void I_click_the_add_another_city() {
		try {
			new SearchFormImplementation().I_click_the_add_another_city();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click the flexible data")
	public void I_click_the_flexible_data() {
		try {
			new SearchFormImplementation().I_click_the_flexible_data();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click the umrah fare")
	public void I_click_the_umrah_fare() {
		try {
			new SearchFormImplementation().I_click_the_umrah_fare();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I enter the return date")
	public void I_enter_the_return_date() {
		try {
			new SearchFormImplementation().I_enter_the_return_date();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click on the edit search")
	public void I_click_on_the_edit_search() {
		try {
			new SearchFormImplementation().I_click_on_the_edit_search();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate advance search")
	public void I_need_to_validate_advance_search() {
		try {
			new SearchFormImplementation().I_need_to_validate_advance_search();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate flight listing")
	public void I_need_to_validate_flight_listing() {
		try {
			new SearchFormImplementation().I_need_to_validate_flight_listing();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate flight listing multi segment three")
	public void I_need_to_validate_flight_listing_multi_segment_three() {
		try {
			new SearchFormImplementation().I_need_to_validate_flight_listing_multi_segment_three();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate flight listing multi segment six")
	public void I_need_to_validate_flight_listing_multi_segment_six() {
		try {
			new SearchFormImplementation().I_need_to_validate_flight_listing_multi_segment_six();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate flexi calander")
	public void I_need_to_validate_flexi_calander() {
		try {
			new SearchFormImplementation().I_need_to_validate_flexi_calander();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate installments")
	public void I_need_to_validate_installments() {
		try {
			new SearchFormImplementation().I_need_to_validate_installments();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate time")
	public void I_need_to_validate_time() {
		try {
			new SearchFormImplementation().I_need_to_validate_time();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate price")
	public void I_need_to_validate_price() {
		try {
			new SearchFormImplementation().I_need_to_validate_price();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate stop")
	public void I_need_to_validate_stop() {
		try {
			new SearchFormImplementation().I_need_to_validate_stop();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate duration")
	public void I_need_to_validate_duration() {
		try {
			new SearchFormImplementation().I_need_to_validate_duration();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate airline")
	public void I_need_to_validate_airline() {
		try {
			new SearchFormImplementation().I_need_to_validate_airline();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate refundable")
	public void I_need_to_validate_refundable() {
		try {
			new SearchFormImplementation().I_need_to_validate_refundable();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click on the more filter")
	public void I_click_on_the_mor_filter() {
		try {
			new SearchFormImplementation().I_click_on_the_mor_filter();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate departure airport")
	public void I_need_to_validate_departure_airport() {
		try {
			new SearchFormImplementation().I_need_to_validate_departure_airport();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate arrival airport")
	public void I_need_to_validate_arrival_airport() {
		try {
			new SearchFormImplementation().I_need_to_validate_arrival_airport();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate departure stopover")
	public void I_need_to_validate_departure_stopover() {
		try {
			new SearchFormImplementation().I_need_to_validate_departure_stopover();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate aircraft types")
	public void I_need_to_validate_aircraft_types() {
		try {
			new SearchFormImplementation().I_need_to_validate_aircraft_types();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click on the apply button")
	public void I_click_on_the_apply_button() {
		try {
			new SearchFormImplementation().I_click_on_the_apply_button();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate fare option")
	public void I_click_on_the_fare_option() {
		try {
			new SearchFormImplementation().I_click_on_the_fare_option();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate sortby filter")
	public void I_click_on_the_sortby_filter() {
		try {
			new SearchFormImplementation().I_click_on_the_sortby_filter();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate more fligt option")
	public void I_need_to_validate_more_fligt_option() {
		try {
			new SearchFormImplementation().I_need_to_validate_more_fligt_option();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate flexi calander round trip")
	public void I_need_to_validate_flexi_calander_round_trip() {
		try {
			new SearchFormImplementation().I_need_to_validate_flexi_calander_round_trip();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate more fligt option round trip")
	public void I_need_to_validate_more_fligt_option_round_trip() {
		try {
			new SearchFormImplementation().I_need_to_validate_more_fligt_option_round_trip();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate fare option three segment")
	public void I_need_to_validate_fare_option_three_segment() {
		try {
			new SearchFormImplementation().I_need_to_validate_fare_option_three_segment();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click on the flight details")
	public void I_click_on_the_flight_details() {
		try {
			new SearchFormImplementation().I_click_on_the_flight_details();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate flight details")
	public void I_need_to_validate_flight_details() {
		try {
			new SearchFormImplementation().I_need_to_validate_flight_details();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate fare option card")
	public void I_click_on_the_fare_option_card() {
		try {
			new SearchFormImplementation().I_click_on_the_fare_option_card();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I click on the flight ltinerary")
	public void I_click_on_the_flight_ltinerary() {
		try {
			new SearchFormImplementation().I_click_on_the_flight_ltinerary();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate flight ltinerary")
	public void I_need_to_validate_flight_ltinerary() {
		try {
			new SearchFormImplementation().I_need_to_validate_flight_ltinerary();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("I click on the baggage")
	public void I_click_on_the_baggage() {
		try {
			new SearchFormImplementation().I_click_on_the_baggage();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate baggage")
	public void I_need_to_validate_baggage() {
		try {
			new SearchFormImplementation().I_need_to_validate_baggage();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("I click on the fare breakup")
	public void I_click_on_the_fare_breakup() {
		try {
			new SearchFormImplementation().I_click_on_the_fare_breakup();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Then("I need to validate fare breakup")
	public void I_need_to_validate_fare_breakup() {
		try {
			new SearchFormImplementation().I_need_to_validate_fare_breakup();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("I need to validate fare option card for round trip")
	public void I_need_to_validate_fare_option_card_for_round_trip() {
		try {
			new SearchFormImplementation().I_need_to_validate_fare_option_card_for_round_trip();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("I click on the booknow")
	public void I_click_on_the_booknow() {
		try {
			new SearchFormImplementation().I_click_on_the_booknow();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("I need to add the traveller details")
	public void I_need_to_add_the_traveller_details() {
		try {
			new SearchFormImplementation().I_need_to_add_the_traveller_details();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("I need to validate flight Summary")
	public void I_need_to_validate_flight_Summary() {
		try {
			new SearchFormImplementation().I_need_to_validate_flight_Summary();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	

	private void calander() {
		// TODO Auto-generated method stub

	}

	@After
	public void afterScenario() {
		tearDown();
	}
}
