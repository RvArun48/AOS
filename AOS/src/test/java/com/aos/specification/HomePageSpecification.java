package com.aos.specification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aos.implementation.HomePageImplementation;
import com.aos.utils.LogEvent;
import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageSpecification {

	public static final Logger logger = LogManager.getLogger(HomePageSpecification.class);

	@Then("I verify header elements present in the home page")
	public void verifyElement() {

		try {

			new HomePageImplementation().verifyPresenceOfHeaderElements();

		} catch (Exception e) {
			logger.info("Exception occurred at verifyElement()->" + e.toString());
		}

	}

	@And("I verify the functionality of the header elements")
	public void verifyElementFunctionality() {

		try {

			new HomePageImplementation().verifyElementFunctionality();

		} catch (Exception e) {
			logger.info("Exception occurred at verifyElementFunctionality()->" + e.toString());
		}

	}

	@Then("I verify the images present in homepage")
	public void verifyImageLoad() {

		try {

			new HomePageImplementation().verifyImageLoad();

		} catch (Exception e) {
			logger.info("Exception occurred at verifyImageLoad()->" + e.toString());
		}

	}

	@Then("^I verify whatsapp link functionality$")
	public void verifyAppLink() {

		try {

			new HomePageImplementation().verifyWhatsappLink();

		} catch (Exception e) {
			logger.info("Exception occurred at verifyAppLink()->" + e.toString());
		}

	}

	@And("I verify the functionality of the footer elements")
	public void verifyIFooterFunctionality() {

		try {

			new HomePageImplementation().verifyIFooterFunctionality();

		} catch (Exception e) {

		}

	}
	
}
