package com.aos.utils;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.aos.base.TestRunner;

public class CommonUtils extends TestRunner {

	private static final String[] domains = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com" };
	private static final int MAX_USERNAME_LENGTH = 10;

	public static String generateRandomEmail() {
		Random random = new Random();

		// Generate a random username
		StringBuilder username = new StringBuilder();
		int usernameLength = random.nextInt(MAX_USERNAME_LENGTH) + 5; // Username length between 5 to 14 characters
		for (int i = 0; i < usernameLength; i++) {
			char randomChar = (char) (random.nextInt(26) + 'a'); // Generate random lowercase letter
			username.append(randomChar);
		}

		// Pick a random domain from the list of domains
		String randomDomain = domains[random.nextInt(domains.length)];

		// Combine username and domain to form the email address
		String randomEmail = username.toString() + "@" + randomDomain;

		return randomEmail;
	}

	public boolean checkImageLoad(WebElement image) {
		
		// Check if the image is loaded
		Boolean imageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				image);
		return imageLoaded;
	}

}
