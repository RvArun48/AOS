package com.aos.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

			File screenshotDir = new File("Screenshots");
			if (!screenshotDir.exists()) {
				screenshotDir.mkdir();
			}

			String screenshotPath = "./Screenshots/" + screenshotName + ".png";
			System.out.println("Screenshot path->" + screenshotPath);

			// Save the screenshot
			FileUtils.writeByteArrayToFile(new File(screenshotPath), screenshotBytes);

			return screenshotPath;
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
			return "";
		}
	}

	public static String getScreenshotPath(WebDriver driver, String name) {
		String screenshotPath = captureScreenshot(driver,
				name + new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date()));
		return screenshotPath;
	}
}
