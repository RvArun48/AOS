package com.aos.implementation;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aos.model.BookTicketDTO;
import com.aos.model.PassengerDetailsDTO;
import com.aos.pageObjects.PassengerDetailsPage;
import com.aos.specification.AosSpecification;
import com.aos.utils.CommonUtils;
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
			logger.info("Exception occured at openWebsite()" + e.getLocalizedMessage());
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
		PassengerDetailsPage passengerDetailsPage = PageFactory.initElements(driver, PassengerDetailsPage.class);

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
		WebElement issuingCountryGcc;
		WebElement nationalityGcc;
		WebElement nationalityExpiryDateGcc;
		WebElement nationalityExpiryMonthGcc;
		WebElement nationalityExpiryYearGcc;

		WebElement nationalitySrff;
		WebElement iqamaExpiryDateSrff;
		WebElement iqamaExpiryMonthSrff;
		WebElement iqamaExpiryYearSrff;

		WebElement mealPreference;
		WebElement seatPreference;
		WebElement specialRequst;
		WebElement airline;

		WebElement visaRequiredCountry;
		WebElement visaExpiryYear;
		WebElement visaExpiryMonth;

		WebElement visaExpiryDay;

		WebElement visaIssuedYear;

		WebElement visaIssuedMonth;

		WebElement visaIssuedDay;

		WebElement issuedCountry;
		WebElement placeOfBirthCountry;
		WebElement destinationCountry;
		WebElement residenceCountry;
		

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

		nationalitySrff = driver.findElements(By.xpath("//*[@bindlabel='country_name']")).get((i));
		issuingCountryGcc = driver.findElements(By.xpath("//*[@formcontrolname='DocumentIssuingCountry']")).get((i));

		nationalityGcc = driver.findElements(By.xpath("//*[@formcontrolname='Nationality']")).get((i));
		nationalityExpiryDateGcc = driver
				.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryDay']//input[@type='text']")).get((i));
		nationalityExpiryMonthGcc = driver.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryMonth']"))
				.get((i));
		nationalityExpiryYearGcc = driver.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryYear']")).get((i));

		iqamaExpiryDateSrff = driver
				.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryDay']//input[@type='text']")).get((i));
		iqamaExpiryMonthSrff = driver.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryMonth']")).get((i));
		iqamaExpiryYearSrff = driver.findElements(By.xpath("//*[@formcontrolname='DocumentExpiryYear']")).get((i));

		mealPreference = driver.findElements(By.xpath("//*[@formcontrolname='SSR_Meal']")).get((i));
		seatPreference = driver.findElements(By.xpath("//*[@formcontrolname='SSR_Seat']")).get((i));

		specialRequst = driver.findElements(By.xpath("//*[@formcontrolname='SSR_SpecialRequest']")).get((i));
		airline = driver.findElements(By.xpath("//*[@formcontrolname='Airline']")).get((i));

		placeOfBirthCountry = driver.findElements(By.xpath("//*[@formcontrolname='PlaceOfBirthCountry']")).get((i));
		issuedCountry = driver.findElements(By.xpath("//*[@formcontrolname='VisaIssuedCountry']")).get((i));

		visaIssuedDay = driver.findElements(By.xpath("//*[@formcontrolname='VisaIssueDay']")).get((i));

		visaIssuedMonth = driver.findElements(By.xpath("//*[@formcontrolname='VisaIssueMonth']")).get((i));

		visaIssuedYear = driver.findElements(By.xpath("//*[@formcontrolname='VisaIssueYear']")).get((i));

		visaExpiryDay = driver.findElements(By.xpath("//*[@formcontrolname='VisaExpiryDay']")).get((i));

		visaExpiryMonth = driver.findElements(By.xpath("//*[@formcontrolname='VisaExpiryMonth']")).get((i));

		visaExpiryYear = driver.findElements(By.xpath("//*[@formcontrolname='VisaExpiryYear']")).get((i));

		visaRequiredCountry = driver.findElements(By.xpath("//*[@formcontrolname='VisaRequiringCountry']")).get((i));
		
		destinationCountry = driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_Country']")).get((i));
		
		residenceCountry = driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_Country']")).get((i));
		
		
		
		
		
		

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

		if (passengerDTOList.get(dataIndex).getDocumentType().equalsIgnoreCase("Local Id")) {

			wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.localId));
			passengerDetailsPage.localId.click();

			logger.info("Click the local id : " + passengerDTOList.get(dataIndex).getLocalDocumentNo());
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElements(By.xpath("//*[@formcontrolname='DocumentNumber']")).get(i)));

			driver.findElements(By.xpath("//*[@formcontrolname='DocumentNumber']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getLocalDocumentNo());
		}

		if (passengerDTOList.get(dataIndex).getDocumentType()
				.equalsIgnoreCase("Iqama ID (Saudi Residence for Foreigners)")) {

			wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.iqamaId));
			passengerDetailsPage.iqamaId.click();

			logger.info("Click the local id : " + passengerDTOList.get(dataIndex).getIqamaId());
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElements(By.xpath("//*[@formcontrolname='DocumentNumber']")).get(i)));

			driver.findElements(By.xpath("//*[@formcontrolname='DocumentNumber']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getIqamaId());

			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();

			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller details", driver,
					scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option ng-star-inserted']//child::*[text()='"
									+ passengerDTOList.get(dataIndex).getNationalitySrff() + "']")));
			Thread.sleep(5000);

			logger.info("Selecting the Iqama Expiry Day: " + passengerDTOList.get(dataIndex).getIqamaExpiryDateSrff());
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Iqama Expiry Day", driver,
					scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option ng-star-inserted']//child::*[text()='"
									+ passengerDTOList.get(dataIndex).getIqamaExpiryDateSrff() + "']")));

			logger.info(
					"Selecting the Iqama Expiry Month: " + passengerDTOList.get(dataIndex).getIqamaExpiryMonthSrff());
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Iqama Expiry Month", driver,
					scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option ng-star-inserted']//child::*[text()='"
									+ passengerDTOList.get(dataIndex).getIqamaExpiryMonthSrff() + "']")));

			logger.info("Selecting the Iqama Expiry Year: " + passengerDTOList.get(dataIndex).getIqamaExpiryYearSrff());
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Iqama Expiry Year", driver,
					scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option ng-star-inserted']//child::*[text()='"
									+ passengerDTOList.get(dataIndex).getIqamaExpiryYearSrff() + "']")));

		}

		if (passengerDTOList.get(dataIndex).getDocumentType()
				.equalsIgnoreCase("National ID (Only for GCC Nationals)")) {

//			if (bookTicketDTO.getBaggageOnly()) {
//				logger.info("clicking on baggage only");
//				homePage.baggageOnlyElementGroup.click();
//			}

			wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.nationalId));
			passengerDetailsPage.nationalId.click();

			logger.info("Enter the Nationality : " + passengerDTOList.get(dataIndex).getNationalityIdGcc());
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElements(By.xpath("//*[@formcontrolname='DocumentNumber']")).get(i)));

			driver.findElements(By.xpath("//*[@formcontrolname='DocumentNumber']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getNationalityIdGcc());

			logger.info("Selecting the Issuing Country Gcc: " + passengerDTOList.get(dataIndex).getIssuingCountryGcc());

			//wait.until(ExpectedConditions.elementToBeClickable(issuingCountryGcc));
    		new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Issuing Country Gcc details",
					driver, scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option ng-star-inserted']//child::*[text()='"
									+ passengerDTOList.get(dataIndex).getIssuingCountryGcc() + "']")));
			Thread.sleep(3000);

			logger.info("Selecting the Nationality: " + passengerDTOList.get(dataIndex).getNationalityGcc());
			Thread.sleep(3000);
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Nationality Id details",
					driver, scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getNationalityGcc() + "']")));

			logger.info("Selecting the Nationality Expiry Day: "
					+ passengerDTOList.get(dataIndex).getNationalityExpiryDateGcc());

			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Nationality Expiry Day",
					driver, scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getNationalityExpiryDateGcc() + "']")));

			logger.info("Selecting the Nationality Expiry Month: "
					+ passengerDTOList.get(dataIndex).getNationalityExpiryMonthGcc());

			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Nationality Expiry Month",
					driver, scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getNationalityExpiryMonthGcc() + "']")));

			logger.info("Selecting the Nationality Expiry Year: "
					+ passengerDTOList.get(dataIndex).getNationalityExpiryYearGcc());

			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
			LogEvent.logEventWithScreenshot(extentTest, Status.INFO, "Adding the traveller Nationality Expiry Year",
					driver, scenarioName);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getNationalityExpiryYearGcc() + "']")));

		}
		if (passengerDTOList.get(dataIndex).getDocumentType().equalsIgnoreCase("Passport Information")) {

			wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.passportInformation));
			passengerDetailsPage.passportInformation.click();

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

		try {

			logger.info("Enter the Visa number : " + passengerDTOList.get(dataIndex).getVisaNumber());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='VisaNumber']")).get(i)));
			driver.findElements(By.xpath("//*[@formcontrolname='VisaNumber']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getVisaNumber());

			logger.info("Enter the BIrth city : " + passengerDTOList.get(dataIndex).getPlaceOfBirthCity());
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElements(By.xpath("//*[@formcontrolname='PlaceOfBirthCity']")).get(i)));
			driver.findElements(By.xpath("//*[@formcontrolname='PlaceOfBirthCity']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getPlaceOfBirthCity());

			logger.info("Selecting the place of birth : " + passengerDTOList.get(dataIndex).getplaceOfBirthCountry());
			wait.until(ExpectedConditions.elementToBeClickable(placeOfBirthCountry));
			placeOfBirthCountry.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getplaceOfBirthCountry() + "'])")));

			logger.info("Enter the Visa issue city : " + passengerDTOList.get(dataIndex).getVisaIssuedCity());
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElements(By.xpath("//*[@formcontrolname='VisaIssuedCity']")).get(i)));
			driver.findElements(By.xpath("//*[@formcontrolname='VisaIssuedCity']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getVisaIssuedCity());

			logger.info("Selecting the Issue country : " + passengerDTOList.get(dataIndex).getIssuedCountry());
			wait.until(ExpectedConditions.elementToBeClickable(issuedCountry));
			issuedCountry.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getIssuedCountry() + "'])")));

			logger.info("Selecting the Visa Issue day : " + passengerDTOList.get(dataIndex).getVisaIssuedDay());
			wait.until(ExpectedConditions.elementToBeClickable(visaIssuedDay));
			visaIssuedDay.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getVisaIssuedDay() + "'])")));

			logger.info("Selecting the Visa Issue month : " + passengerDTOList.get(dataIndex).getVisaIssuedMonth());
			wait.until(ExpectedConditions.elementToBeClickable(visaIssuedMonth));
			visaIssuedMonth.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getVisaIssuedMonth() + "'])")));

			logger.info("Selecting the Visa Issue year : " + passengerDTOList.get(dataIndex).getVisaIssuedYear());
			wait.until(ExpectedConditions.elementToBeClickable(visaIssuedYear));
			visaIssuedYear.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getVisaIssuedYear() + "'])")));

			logger.info("Selecting the Visa Expiry Day  : " + passengerDTOList.get(dataIndex).getVisaExpiryDay());
			wait.until(ExpectedConditions.elementToBeClickable(visaExpiryDay));
			visaExpiryDay.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getVisaExpiryDay() + "'])")));

			logger.info("Selecting the Visa Expiry month : " + passengerDTOList.get(dataIndex).getVisaExpiryMonth());
			wait.until(ExpectedConditions.elementToBeClickable(visaExpiryMonth));
			visaExpiryMonth.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getVisaExpiryMonth() + "'])")));

			logger.info("Selecting the Visa Expiry year : " + passengerDTOList.get(dataIndex).getVisaExpiryYear());
			wait.until(ExpectedConditions.elementToBeClickable(visaExpiryYear));
			visaExpiryYear.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getVisaExpiryYear() + "'])")));

			logger.info("Selecting the Visa requireing country  : "
					+ passengerDTOList.get(dataIndex).getVisaRequiredCountry());
			wait.until(ExpectedConditions.elementToBeClickable(visaRequiredCountry));
			visaRequiredCountry.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getVisaRequiredCountry() + "'])")));

		} catch (Exception e) {

		}
		
	try {
			
		logger.info(
				"Enter the Destination Address : " + passengerDTOList.get(dataIndex).getDestinationAddress());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_Address']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_Address']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getDestinationAddress());
		
		
		logger.info(
				"Enter the Destination city : " + passengerDTOList.get(dataIndex).getDestinationCity());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_City']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_City']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getDestinationCity());
		
		
		logger.info("Selecting the country : " + passengerDTOList.get(dataIndex).getDestinationCountry());
		wait.until(ExpectedConditions.elementToBeClickable(destinationCountry));
		destinationCountry.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getDestinationCountry() + "'])")));
		
		
		logger.info(
				"Enter the Destination State : " + passengerDTOList.get(dataIndex).getDestinationState());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_State']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_State']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getDestinationState());
		
		logger.info(
				"Enter the Destination pin : " + passengerDTOList.get(dataIndex).getDestinationPin());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_Zip']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_destination_Zip']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getDestinationPin());
		
		
		
		
		
		
		
		logger.info(
				"Enter the Destination Address : " + passengerDTOList.get(dataIndex).getResidenceAddress());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_Address']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_Address']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getResidenceAddress());
		
		
		logger.info(
				"Enter the Destination city : " + passengerDTOList.get(dataIndex).getResidenceCity());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_City']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_City']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getResidenceCity());
		
		
		logger.info("Selecting the country : " + passengerDTOList.get(dataIndex).getResidenceCountry());
		wait.until(ExpectedConditions.elementToBeClickable(residenceCountry));
		residenceCountry.click();
		executor.executeScript("arguments[0].click();",
				passengerDetailsContainer.get(i)
						.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
								+ passengerDTOList.get(dataIndex).getResidenceCountry() + "'])")));
		
		
		logger.info(
				"Enter the Destination state : " + passengerDTOList.get(dataIndex).getResidenceState());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_State']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_State']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getResidenceState());
		
		
		logger.info(
				"Enter the Destination state : " + passengerDTOList.get(dataIndex).getResidencePin());
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_Zip']")).get(i)));

		driver.findElements(By.xpath("//*[@formcontrolname='DOCA_residence_Zip']")).get(i)
				.sendKeys(passengerDTOList.get(dataIndex).getResidencePin());
		
		
		
		
		
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		try {

			wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsPage.optionalPreference));
			passengerDetailsPage.optionalPreference.click();

			logger.info("Selecting the Meal pereferance : " + passengerDTOList.get(dataIndex).getMealPreference());
			wait.until(ExpectedConditions.elementToBeClickable(mealPreference));
			mealPreference.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getMealPreference() + "'])")));

			logger.info("Selecting the Special request: " + passengerDTOList.get(dataIndex).getSpecialRequst());
			wait.until(ExpectedConditions.elementToBeClickable(specialRequst));
			specialRequst.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getSpecialRequst() + "'])")));

			logger.info("Selecting the  Airline: " + passengerDTOList.get(dataIndex).getAirline());
			wait.until(ExpectedConditions.elementToBeClickable(airline));
			airline.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getAirline() + "'])")));

			logger.info("Selecting the Seat preferance: " + passengerDTOList.get(dataIndex).getSeatPreference());
			wait.until(ExpectedConditions.elementToBeClickable(seatPreference));
			seatPreference.click();
			executor.executeScript("arguments[0].click();",
					passengerDetailsContainer.get(i)
							.findElement(By.xpath("(//*[@class='ng-option-label ng-star-inserted' and text()='"
									+ passengerDTOList.get(dataIndex).getSeatPreference() + "'])")));

			logger.info(
					"Enter the Frequent flyer number : " + passengerDTOList.get(dataIndex).getFrequentFlyingNumber());
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElements(By.xpath("//*[@formcontrolname='Number']")).get(i)));

			driver.findElements(By.xpath("//*[@formcontrolname='Number']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getFrequentFlyingNumber());

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {

			logger.info("Enter the Form identity : " + passengerDTOList.get(dataIndex).getFormIdentity());
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElements(By.xpath("//*[@formcontrolname='FormofIdentityNumber']")).get(i)));

			driver.findElements(By.xpath("//*[@formcontrolname='FormofIdentityNumber']")).get(i)
					.sendKeys(passengerDTOList.get(dataIndex).getFormIdentity());
		} catch (Exception e) {
			// TODO: handle exception
		}

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
