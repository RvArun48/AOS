package com.aos.specification;

import com.aos.implementation.HomePageImplementation;
import com.aos.utils.LogEvent;
import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageSpecification {

	@Then("I verify header elements present in the home page")
	public void verifyElement() {

		try {

			new HomePageImplementation().verifyPresenceOfHeaderElements();

		} catch (Exception e) {

		}

	}

	@And("I verify the functionality of the header elements")
	public void verifyElementFunctionality() {

		try {

			new HomePageImplementation().verifyElementFunctionality();

		} catch (Exception e) {

		}

	}

}
