package com.aos.utils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aos.base.TestRunner;
import com.aventstack.extentreports.Status;

public class GenericActions extends TestRunner {

	public static void clickElement(WebElement element, String message, Logger logger) throws IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			logger.info(message);
			element.click();
			Thread.sleep(1500);
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, message, driver, getScenarioName());
		} catch (Exception e) {
			logger.info("Exception occured at clickElement()->" + e.getMessage());
		}

	}

	public static void sendKeys(WebElement element, String data, String message, Logger logger) throws IOException {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			executor.executeScript("arguments[0].click();", element);

			logger.info(message);
			element.sendKeys(data);
			Thread.sleep(1500);
			LogEvent.logEventWithScreenshot(getExtentTest(), Status.INFO, message, driver, getScenarioName());
		} catch (Exception e) {
			logger.info("Exception occured at clickElement()->" + e.getMessage());
		}

	}

}
