package com.aos.base;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aos.utils.CommonUtils;
import com.aos.utils.ReadProperty;
import com.aos.utils.ZipUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.aos.specification" }, plugin = {
		"json:target/RunCuke/cucumber.json", "pretty" }, tags = "@oneway_flight_listing")

public class TestRunner {

	public static WebDriver driver = null;
	private static ExtentReports test = null;
	private static ExtentTest extentTest = null;
	private static String scenarioName = null;

	public static String getScenarioName() {
		return scenarioName;
	}

	public static void setScenarioName(String scenarioName) {
		TestRunner.scenarioName = scenarioName;
	}

	public static ExtentTest getExtentTest() {
		return extentTest;
	}

	public static void setExtentTest(ExtentTest extentTest) {
		TestRunner.extentTest = extentTest;
	}

	public ExtentReports setUp(Scenario scenario) {
		// ExtentManager.createInstance("extent-report-" + scenarioName + ".html");
		test = ExtentManager.getInstance();
		if (ReadProperty.getPropValues("BROWSER", "config").equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			if (ReadProperty.getPropValues("HEADLESS_MODE", "config").equalsIgnoreCase("true")) {
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
			}

			options.addArguments("--window-size=1920,1080");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.merge(options);
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		return test;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public static void tearDown() {
		ExtentManager.getInstance().flush();
		driver.quit();
	}

	@AfterClass
	public static void zipReport() {
		String zipFileName = "test-report.zip";
		try {
			ZipUtil.zipFiles(zipFileName, "./Screenshots", "./extent-reports.html");
			CommonUtils.killChromeDiverInstances();
			CommonUtils.clearFolder();
//            EmailUtil.sendEmailWithAttachment(
//                "smtp.example.com", "587", "your-email@example.com", "your-email-password",
//                "recipient-email@example.com", "Test Report", "Please find the attached test report.", zipFileName
//            );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
