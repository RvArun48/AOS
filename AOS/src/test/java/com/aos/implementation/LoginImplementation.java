package com.aos.implementation;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aos.base.TestRunner;
import com.aos.model.BookTicketDTO;
import com.aos.model.CommonDTO;
import com.aos.model.LoginDTO;
import com.aos.pageObjects.HomePage;
import com.aos.utils.CommonUtils;
import com.aos.utils.Complexity;
import com.aos.utils.GenericActions;
import com.aos.utils.JsonToGson;
import com.aos.utils.LogEvent;
import com.aos.utils.WebElementInfo;
import com.aventstack.extentreports.Status;
import com.google.common.reflect.TypeToken;

public class LoginImplementation extends TestRunner {

	public WebDriver driver = getDriver();

	public static final Logger logger = LogManager.getLogger(LoginImplementation.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);

	public void login(String keyData) throws IOException {
		Type bt = new TypeToken<LoginDTO>() {
			private static final long serialVersionUID = 1L;
		}.getType();
		LoginDTO loginDTO = (LoginDTO) JsonToGson.convertToObj("signin_details", keyData, bt);
		try {

			SoftAssertions softly = new SoftAssertions();
			logger.info("login DTO->" + loginDTO);
//			
//			
			if (keyData.equals("user_b2c")) {

			GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
			GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
					"Entering email id:" + loginDTO.getSigninEmail(), logger);

			GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
					"Entering password:" + loginDTO.getSigninPassword(), logger);
			
			GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);
			logger.info("attribute value is: " + homePage.loginSuccessValidation.getText());
			softly.assertThat(homePage.loginSuccessValidation.getText()).isEqualTo("My Account");
			
			}

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			if (keyData.equals("no_username_no_password")) {

				GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
				GenericActions.sendKeys(homePage.signInUserName, loginDTO.getSigninEmail(),
						"Entering email id:" + loginDTO.getSigninEmail(), logger);

				GenericActions.sendKeys(homePage.signInPassword, loginDTO.getSigninPassword(),
						"Entering password:" + loginDTO.getSigninPassword(), logger);
				GenericActions.clickElement(homePage.popupsigninButton, "Clicking the SignIn", logger);
                 Thread.sleep(5000);
				softly.assertThat(homePage.warningAlertEmail.getText()).isEqualTo("Email is required");
				softly.assertThat(homePage.warningAlertPassword.getText()).isEqualTo("Password is required");

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
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
					getScenarioName());
			
		}

	}

	public void signUp() throws IOException, InterruptedException {
		try {

			SoftAssertions softly = new SoftAssertions();

			String userName = CommonUtils.generateRandomEmail();
			String password = CommonUtils.generatePassword(12, Complexity.VERY_HIGH);
			
			CommonDTO.getInstance().setUserName(userName);
			CommonDTO.getInstance().setUserName(password);
			
			
			logger.info("userName->" + userName + " password->" + password);

			GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn", logger);
			GenericActions.clickElement(homePage.createAccountSignup, "Clicking the Create Account", logger);

			GenericActions.sendKeys(homePage.createAccountSignUpUserName, userName, "Entering email id:" + userName,
					logger);

			GenericActions.sendKeys(homePage.createAccountSignUpPassword, password, "Entering password:" + password,
					logger);
			GenericActions.clickElement(homePage.createAccount, "Clicking the SignIn", logger);
			logger.info("attribute value is: " + homePage.loginSuccessValidation.getText());
//			softly.assertThat(homePage.loginSuccessValidation.getText()).isEqualTo("My Account");

			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}

		} catch (AssertionError e) {
			logger.info("Exception occured at signUp()->" + e.getMessage());
		}
	}

	public void forgotPassword(String keyData) {
		try {
			SoftAssertions softly = new SoftAssertions();
			List<WebElementInfo> webElements = Arrays.asList(
			
//			GenericActions.clickElement(homePage.SigninElementGroup, "Clicking the SignIn" , logger);
//			GenericActions.clickElement(homePage.forgotPassword, "Clicking the Forgotpassword" , logger);
		//	GenericActions.clickElement(homePage.forgotPasswordContinue, "Clicking the Continue" , logger);
			
			new WebElementInfo(By.xpath("//*[text()='Forgot password?']"), "Forgot password?"),
			new WebElementInfo(By.xpath("//*[text()='Email address']"), "Email address"),
			new WebElementInfo(By.xpath("//*[text()='Continue']"), "Continue"));
			
			
			
		
			
			
			
			
			
			
			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}					
			
		} catch (Exception e) {
			logger.info("Exception occured at forgotPassword()->" + e.getMessage());
		}
	}

	public void verifyLoginPageContents() {
		try {
			SoftAssertions softly = new SoftAssertions();
			List<WebElementInfo> webElements = Arrays.asList(
					new WebElementInfo(By.xpath("//*[@class='btn btn-primary'][contains(text(),'Sign in')]"),
							"Sign In Option"),
					
					
					new WebElementInfo(By.xpath("//*[text()='Email']"), "Email"),
					new WebElementInfo(By.xpath("//*[text()='Password']"), "Password"),
					new WebElementInfo(By.xpath("(//button[text()='Sign in'])[2]"), "Sign In"),
					new WebElementInfo(By.xpath("//*[text()='Create Account']"), "Create Account"),
				
					new WebElementInfo(By.xpath("//*[@class='signIn_forgotPassLink']/child::*[contains(text(),'Forgot password?')]"), "Forgot password")
					
					
					
					
					
					
					
					
					
					
					 );
			
			
			
			
			
			try {
				softly.assertAll();
			} catch (AssertionError e) {
				LogEvent.logEventWithScreenshot(getExtentTest(), Status.FAIL, e.getMessage(), driver,
						getScenarioName());
			}					
			
		} catch (Exception e) {
			logger.info("Exception occured at verifyLoginPageContents()->" + e.getMessage());
		}
	}

}
