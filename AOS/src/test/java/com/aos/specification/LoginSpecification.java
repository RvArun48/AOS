package com.aos.specification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aos.implementation.HomePageImplementation;
import com.aos.implementation.LoginImplementation;
import com.aos.utils.LogEvent;
import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginSpecification {
	
	public static final Logger logger = LogManager.getLogger(LoginImplementation.class);

	@Then("^I login to the application using \"(.+)\"$")
	public void login(String keyData) {

		try {

			new LoginImplementation().login(keyData);

		} catch (Exception e) {
			logger.info("Exception occured at login()->" + e.getMessage());
		}

	}
	
	@Then("I signup to create a new account")
	public void signUp() {

		try {
			
			new LoginImplementation().signUp();

		} catch (Exception e) {
			logger.info("Exception occured at signUp()->" + e.getMessage());
		}

	}
	
	@But("I forgot the password and I want to reset it for \"(.+)\"$")
	public void forgotPassword(String keyData) {

		try {
			
			new LoginImplementation().forgotPassword(keyData);

		} catch (Exception e) {
			logger.info("Exception occured at forgotPassword()->" + e.getMessage());
			
		}

	}
	
	@Then("I verify the elements present in the login page")
	public void verifyLoginPageContents(String keyData) {

		try {
			
			new LoginImplementation().verifyLoginPageContents();

		} catch (Exception e) {
			logger.info("Exception occured at verifyLoginPageContents()->" + e.getMessage());
			
			
		}

	}
}
