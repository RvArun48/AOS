package com.aos.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
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
	
	public static String generatePassword(int length, Complexity complexity) {
	
	
		final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	    final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final String DIGITS = "0123456789";
	    final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=<>?";

	    final SecureRandom random = new SecureRandom();

	    if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than zero.");
        }
	   

	        StringBuilder password = new StringBuilder(length);
	        List<String> charCategories = new ArrayList<String>();

	        switch (complexity) {
	            case LOW:
	                charCategories.add(LOWERCASE);
	                break;
	            case MEDIUM:
	                charCategories.add(LOWERCASE);
	                charCategories.add(UPPERCASE);
	                break;
	            case HIGH:
	                charCategories.add(LOWERCASE);
	                charCategories.add(UPPERCASE);
	                charCategories.add(DIGITS);
	                break;
	            case VERY_HIGH:
	                charCategories.add(LOWERCASE);
	                charCategories.add(UPPERCASE);
	                charCategories.add(DIGITS);
	                charCategories.add(SPECIAL_CHARACTERS);
	                break;
	        }

	        for (int i = 0; i < length; i++) {
	            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
	            int position = random.nextInt(charCategory.length());
	            password.append(charCategory.charAt(position));
	        }

	        return new String(password);
	    
	

	}
}
