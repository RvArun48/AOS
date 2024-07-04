package com.aos.implementation;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.model.BookTicketDTO;
import com.aos.model.PassengerDetailsDTO;
import com.aos.specification.AosSpecification;
import com.aos.utils.LogEvent;
import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AosImplementation extends AosSpecification {
	
	public static final Logger logger = LogManager.getLogger(AosImplementation.class);

	public ExtentTest openAmadeus(ExtentTest extentTest, String scenario, ExtentReports report, WebDriver driver) {
		String baseUrl = ReadProperty.getPropValues("BASE_URL", "config");
		try {

			extentTest = report.createTest(scenario);
			driver.get(baseUrl);
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Home Page verification", driver, scenario);

		} catch (Exception e) {
			System.out.println("Exception occured at openWebsite()" + e.getLocalizedMessage());
		}
		return extentTest;
	}

	public static void addPassengersCount(BookTicketDTO bookTicketDTO, JavascriptExecutor executor,
			WebDriverWait wait) {
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
	}

	public static void addPassengerDetails(JavascriptExecutor executor, WebDriverWait wait,
			List<PassengerDetailsDTO> passengerDTOList, List<WebElement> passengerDetailsContainer, Actions actions,
			int i, int dataIndex) throws IOException, InterruptedException {
		WebElement title;
		WebElement dobDay;
		WebElement dobMonth;
		WebElement dobYear;
		WebElement issuingCountry;
		WebElement nationality;
		WebElement pidDay;
		WebElement pidMonth;
		WebElement pidYear;
		WebElement pedDay;
		WebElement pedMonth;
		WebElement pedYear;
		title = wait.until(
				ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//*[@formcontrolname='Title']")).get(i)));

		title = driver.findElements(By.xpath("//*[@formcontrolname='Title']")).get((i));

		dobDay = driver.findElements(By.xpath("//*[@formcontrolname='BirthDate']")).get((i));

		dobMonth = driver.findElements(By.xpath("//*[@formcontrolname='BirthMonth']")).get((i));

		dobYear = driver.findElements(By.xpath("//*[@formcontrolname='BirthYear']")).get((i));

		issuingCountry = driver.findElements(By.xpath("//*[@formcontrolname='DocumentIssuingCountry']")).get((i));

		nationality = driver.findElements(By.xpath("//*[@formcontrolname='Nationality']")).get((i));

		pidDay = driver.findElements(By.xpath("//*[@formcontrolname='DocumentIssueDay']")).get((i));

		pidMonth = driver.findElements(By.xpath("//*[@formcontrolname='DocumentIssueMonth']")).get((i));

		pidYear = driver.findElements(By.xpath("//*[@formcontrolname='DocumentIssueYear']")).get((i));

		pedDay = driver.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryDay']")).get((i));

		pedMonth = driver.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryMonth']")).get((i));

		pedYear = driver.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryYear']")).get((i));

		actions.moveToElement(title).perform();

		title.click();

		logger.info("Selecting the Title: " + passengerDTOList.get(dataIndex).getTitle());
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getTitle() + "'])[1]//parent::div")));
		LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller personal details", driver,
				scenarioName);

		logger.info("Enter the First Name: " + passengerDTOList.get(dataIndex).getFirstName());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//input[@formcontrolname='FirstName']")).get(i)));

		driver.findElements(By.xpath("//input[@formcontrolname='FirstName']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getFirstName());

		logger.info("Enter the Last Name: " + passengerDTOList.get(dataIndex).getLastName());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//input[@formcontrolname='LastName']")).get(i)));

		driver.findElements(By.xpath("//input[@formcontrolname='LastName']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getLastName());

		dobDay.click();

		logger.info("Selecting the Date of Birth Day: " + passengerDTOList.get(dataIndex).getDobDate());
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getDobDate() + "'])[1]//parent::div")));

		dobMonth.click();
		logger.info("Selecting the Date of Birth Month: " + passengerDTOList.get(dataIndex).getDobMonth());

		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getDobMonth() + "']")));

		dobYear.click();
		logger.info("Selecting the Date of Birth Year: " + passengerDTOList.get(dataIndex).getDobYear());
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getDobYear() + "']")));

		logger.info("Selecting the Issuing Country: " + passengerDTOList.get(dataIndex).getIssuingCountry());

		wait.until(ExpectedConditions.elementToBeClickable(issuingCountry));
		issuingCountry.click();
		LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller passport details", driver,
				scenarioName);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getIssuingCountry() + "'])[1]//parent::div")));

		logger.info("Selecting the Nationality: " + passengerDTOList.get(dataIndex).getNationality());
		wait.until(ExpectedConditions.elementToBeClickable(nationality));
		nationality.click();

		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getNationality() + "'])[1]//parent::div")));

		logger.info("Selecting the Passport Issue Day : " + passengerDTOList.get(dataIndex).getPidDay());
		wait.until(ExpectedConditions.elementToBeClickable(pidDay));
		pidDay.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getPidDay() + "'])[1]//parent::div")));

		logger.info("Selecting the Passport Issue Month : " + passengerDTOList.get(dataIndex).getPidMonth());
		wait.until(ExpectedConditions.elementToBeClickable(pidMonth));
		pidMonth.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getPidMonth() + "'])[1]//parent::div")));

		logger.info("Selecting the Passport Issue Year : " + passengerDTOList.get(dataIndex).getPidYear());
		wait.until(ExpectedConditions.elementToBeClickable(pidYear));
		pidYear.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getPidYear() + "'])[1]//parent::div")));

		logger.info("Selecting the Passport Expriry Day : " + passengerDTOList.get(dataIndex).getPedDay());
		wait.until(ExpectedConditions.elementToBeClickable(pedDay));
		pedDay.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getPedDay() + "'])[1]//parent::div")));

		logger.info("Selecting the Passport Expriry Month : " + passengerDTOList.get(dataIndex).getPedMonth());
		wait.until(ExpectedConditions.elementToBeClickable(pedMonth));
		pedMonth.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getPedMonth() + "'])[1]//parent::div")));

		logger.info("Selecting the Passport Expriry Year : " + passengerDTOList.get(dataIndex).getPedYear());
		wait.until(ExpectedConditions.elementToBeClickable(pedYear));
		pedYear.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getPedYear() + "'])[1]//parent::div")));

		logger.info("Enter the passport No : " + passengerDTOList.get(dataIndex).getPassportNo());
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElements(By.xpath("//input[@formcontrolname='DocumentNumber']")).get(i)));

		driver.findElements(By.xpath("//input[@formcontrolname='DocumentNumber']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getPassportNo());
	}

	public static void verifyTicketBookingStatus(WebDriverWait wait) throws IOException {
		try {
			// confirmed
			logger.info("Your booking is Confirmed");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Confirmed')])[1]")));
			if (driver.findElement(By.xpath(
					"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Confirmed')])[1]"))
					.getText().contains("Your booking")) {
				LogEvent.logEventWithScreenshot(extentTest, Status.PASS,
						"Ticket booking status is: <b><u>" + driver.findElement(By.xpath(
								"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking')])[1]"))
								.getText() + "</u></b>",
						driver, AosSpecification.scenarioName);
			}

		} catch (Exception e) {
			try {
				// pending
				logger.info("Your booking is Pending");
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
						"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Pending')])[1]")));
				if (driver.findElement(By.xpath(
						"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is Pending')])[1]"))
						.getText().contains("Your booking")) {
					LogEvent.logEventWithScreenshot(extentTest, Status.PASS,
							"Ticket booking status is: <b><u>" + driver.findElement(By.xpath(
									"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking')])[1]"))
									.getText() + "</u></b>",
							driver, AosSpecification.scenarioName);
				}

			} catch (Exception e1) {
				try {
					// on hold
					logger.info("Your booking is On hold");
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
							"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is On hold')])[1]")));
					if (driver.findElement(By.xpath(
							"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking is On hold')])[1]"))
							.getText().contains("Your booking")) {
						LogEvent.logEventWithScreenshot(extentTest, Status.PASS,
								"Ticket booking status is: <b><u>" + driver.findElement(By.xpath(
										"(//*[@class='empireFlight_confirmBookingStatus' and contains(text(),'Your booking')])[1]"))
										.getText() + "</u></b>",
								driver, AosSpecification.scenarioName);
					}

				} catch (Exception e3) {
					logger.info("Ticket booking is unsuccessful");
					LogEvent.logEventWithScreenshot(extentTest, Status.FAIL, "Ticket booking is unsuccessful", driver,
							AosSpecification.scenarioName);
				}
			}
		}
	}

}
