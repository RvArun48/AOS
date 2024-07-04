package com.aos.utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class LogEvent {
	public static void logEventWithScreenshot(ExtentTest extentTest, Status logType, String comment, WebDriver driver,
			String scenario) throws IOException {
		extentTest.log(logType, comment, MediaEntityBuilder
				.createScreenCaptureFromPath(ScreenshotUtils.getScreenshotPath(driver, scenario)).build());
	}
}