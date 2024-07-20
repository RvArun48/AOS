package com.aos.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class LogEvent {
	public static final Logger logger = LogManager.getLogger(LogEvent.class);
	public static void logEventWithScreenshot(ExtentTest extentTest, Status logType, String comment, WebDriver driver,
			String scenario) {
		try {
			extentTest.log(logType, comment, MediaEntityBuilder
					.createScreenCaptureFromPath(ScreenshotUtils.getScreenshotPath(driver, scenario)).build());
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
	}
}