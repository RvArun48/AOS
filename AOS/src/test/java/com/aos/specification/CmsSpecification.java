package com.aos.specification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aos.implementation.HomePageImplementation;
import com.aos.implementation.LoginImplementation;
import com.aos.implementation.CmsImplementation;
import com.aos.utils.LogEvent;
import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CmsSpecification {
	
	public static final Logger logger = LogManager.getLogger(CmsSpecification.class);

	@Then("I validate the package")
	public void validatePackage() {

		try {

			new CmsImplementation().validatePackage();

		} catch (Exception e) {
			logger.info("Exception occured at validatePackage()->" + e.getMessage());
		}

	}
	
	@Then("I validate Popular Hotels")
	public void validatePopularHotels() {

		try {

			new CmsImplementation().validatePopularHotels();

		} catch (Exception e) {
			logger.info("Exception occured at validatePopularHotels()->" + e.getMessage());
		}

	}
	@Then("I validate Top Destination")
	public void validateTopDestination() {

		try {

			new CmsImplementation().validateTopDestination();

		} catch (Exception e) {
			logger.info("Exception occured at validateTopDestination()->" + e.getMessage());
		}

	}
	
	@Then("I validate Hot Deals")
	public void validateHotDeals() {

		try {

			new CmsImplementation().validateHotDeals();

		} catch (Exception e) {
			logger.info("Exception occured at validateHotDeals()->" + e.getMessage());
		}

	}
	
}
