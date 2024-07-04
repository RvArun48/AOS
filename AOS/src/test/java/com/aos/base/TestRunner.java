package com.aos.base;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aos.utils.ReadProperty;
import com.aventstack.extentreports.ExtentReports;

import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.aos.specification" }, plugin = {
		"json:target/RunCuke/cucumber.json", "pretty" }, tags = "@Test")

public class TestRunner {

	public static WebDriver driver = null;
	private static ExtentReports test = null;

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
			}
			options.addArguments("--window-size=1920,1080");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.merge(options);
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		return test;
	}

	public static void tearDown() {
		ExtentManager.getInstance().flush();
		driver.quit();
	}

}
