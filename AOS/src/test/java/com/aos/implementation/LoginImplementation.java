package com.aos.implementation;

import java.lang.reflect.Type;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aos.base.TestRunner;
import com.aos.model.BookTicketDTO;
import com.aos.model.LoginDTO;
import com.aos.pageObjects.HomePage;
import com.aos.utils.GenericActions;
import com.aos.utils.JsonToGson;
import com.aos.utils.LogEvent;
import com.aventstack.extentreports.Status;
import com.google.common.reflect.TypeToken;

public class LoginImplementation extends TestRunner {

	public WebDriver driver = getDriver();

	public static final Logger logger = LogManager.getLogger(LoginImplementation.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);

	public void login(String keyData) {
		Type bt = new TypeToken<LoginDTO>() {
			private static final long serialVersionUID = 1L;
		}.getType();
		LoginDTO loginDTO = (LoginDTO) JsonToGson.convertToObj("signin_details", keyData, bt);
		try {

			SoftAssertions softly = new SoftAssertions();
			logger.info("login DTO->" + loginDTO);
//			
//			GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn" , logger);
//			GenericActions.clickElement(homePage.forgotPassword, "Clicking the Forgotpassword" , logger);
//			GenericActions.clickElement(homePage.forgotPasswordContinue, "Clicking the Continue" , logger);
//			softly.assertThat(homePage.warningAlertEmail.getText()).isEqualTo(" Email is required ");
//			GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(), "Entering email id:" + loginDTO.getSigninEmail(), logger);
//			GenericActions.clickElement(homePage.forgotPasswordContinue, "Clicking the Forgotpassword" , logger);
//			softly.assertThat(homePage.forgotPasswordValidation.getAttribute("type")).isEqualTo(" Email Sent ");

			GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
			GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
					"Entering email id:" + loginDTO.getSigninEmail(), logger);

			GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
					"Entering password:" + loginDTO.getSigninPassword(), logger);
			GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);
			logger.info("attribute value is: " + homePage.loginSuccessValidation.getText());
			softly.assertThat(homePage.loginSuccessValidation.getText()).isEqualTo("My Account");
			

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			if (keyData.equals("no_username_no_password")) {

				GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
				GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
						"Entering email id:" + loginDTO.getSigninEmail(), logger);

				GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
						"Entering password:" + loginDTO.getSigninPassword(), logger);
				GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);

				softly.assertThat(homePage.warningAlertEmail.getText()).isEqualTo("Email is required");
				softly.assertThat(homePage.warningAlertPassword.getText()).isEqualTo("Email is required");

			}
			if (keyData.equals("valid_username_no_password")) {

				GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
				GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
						"Entering email id:" + loginDTO.getSigninEmail(), logger);

				GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
						"Entering password:" + loginDTO.getSigninPassword(), logger);
				GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);

				softly.assertThat(homePage.warningAlertPassword.getText()).isEqualTo("Password is required");

			}
			if (keyData.equals("no_username_Valid_password")) {

				GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
				GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
						"Entering email id:" + loginDTO.getSigninEmail(), logger);

				GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
						"Entering password:" + loginDTO.getSigninPassword(), logger);
				GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);

				softly.assertThat(homePage.warningAlertEmail.getText()).isEqualTo("Email is required");

			}
			if (keyData.equals("valid_username_invalid_password")) {

				GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
				GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
						"Entering email id:" + loginDTO.getSigninEmail(), logger);

				GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
						"Entering password:" + loginDTO.getSigninPassword(), logger);
				GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);

				softly.assertThat(homePage.paswValidation.getText()).isEqualTo(" Please enter a valid password ");

			}
			if (keyData.equals("invalid_username_Valid_password")) {

				GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
				GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
						"Entering email id:" + loginDTO.getSigninEmail(), logger);

				GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
						"Entering password:" + loginDTO.getSigninPassword(), logger);
				GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);

				softly.assertThat(homePage.emailValidation.getText()).isEqualTo("The username don't seem to be right.");

			}

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

			Thread.sleep(3000);

		} catch (Exception e) {
			logger.info("Exception occured at login()->" + e.getMessage());
		}

	}

}
