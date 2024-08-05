package com.aos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	public static boolean checkImageLoad(WebElement image) {

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

	/**
	 * Zooms out the browser to the specified scale.
	 *
	 * @param driver The WebDriver instance.
	 * @param scale  The zoom scale (e.g., 0.9 for 90% zoom out).
	 */
	public static void zoomOutBrowser(WebDriver driver, double scale) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("document.body.style.zoom = '" + scale + "'");
	}

	public static void killChromeDiverInstances() {
		try {
			// Command to terminate chromedriver.exe process
			String[] command = { "taskkill", "/F", "/IM", "chromedriver.exe", "/T" };

			// Create a ProcessBuilder instance
			ProcessBuilder processBuilder = new ProcessBuilder(command);

			// Start the process
			Process process = processBuilder.start();

			// Read the output from the command
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Wait for the process to complete
			int exitCode = process.waitFor();
			System.out.println("Command exited with code: " + exitCode);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears all contents inside the specified folder.
	 *
	 * @param folderPath The path to the folder to be cleared.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void clearFolder() throws IOException {
		// Get the temporary directory path
		String tempDir = System.getProperty("java.io.tmpdir");

		// Clear the scoped_dir% folders inside the temporary folder
		try {
			clearScopedDirFolders(tempDir, "scoped_dir");
			System.out.println("Scoped_dir% folders cleared.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to clear scoped_dir% folders.");
		}
	}

	/**
	 * Clears all folders inside the specified folder that start with the given
	 * prefix.
	 *
	 * @param folderPath The path to the folder to be cleared.
	 * @param prefix     The prefix to filter folders.
	 * @throws IOException If an I/O error occurs.
	 */
	private static void clearScopedDirFolders(String folderPath, String prefix) throws IOException {
		Path path = Paths.get(folderPath);
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, prefix + "*")) {
			for (Path filePath : directoryStream) {
				if (Files.isDirectory(filePath)) {
					// Recursively clear the sub-directory
					clearFolder(filePath);
				}
				// Delete the empty directory
				try {
					Files.delete(filePath);
				} catch (IOException e) {
					System.err.println("Failed to delete: " + filePath + " - " + e.getMessage());
				}
			}
		}
	}

	/**
	 * Clears all contents inside the specified folder.
	 *
	 * @param folderPath The path to the folder to be cleared.
	 * @throws IOException If an I/O error occurs.
	 */
	private static void clearFolder(Path folderPath) throws IOException {
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
			for (Path filePath : directoryStream) {
				if (Files.isDirectory(filePath)) {
					// Recursively clear the sub-directory
					clearFolder(filePath);
				}
				// Delete the file or empty directory
				try {
					Files.delete(filePath);
				} catch (IOException e) {
					System.err.println("Failed to delete: " + filePath + " - " + e.getMessage());
				}
			}
		}
	}

	public static boolean isAscendingOrder(List<Double> priceList) {
		if (priceList == null || priceList.size() <= 1) {
			return true; // An empty list or a single-element list is trivially sorted
		}

		for (int i = 0; i < priceList.size() - 1; i++) {
			if (priceList.get(i) > priceList.get(i + 1)) {
				return false; // Found an element that is greater than the next element
			}
		}
		return true; // No elements are out of order
	}

	public static boolean isDescendingOrder(List<Double> priceList) {
		if (priceList == null || priceList.size() <= 1) {
			return true; // An empty list or a single-element list is trivially sorted
		}

		for (int i = 0; i < priceList.size() - 1; i++) {
			if (priceList.get(i) < priceList.get(i + 1)) {
				return false; // Found an element that is less than the next element
			}
		}
		return true; // No elements are out of order
	}
	
	 public static void waitForAngularToLoad(WebDriver driver) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor jsExec = (JavascriptExecutor) driver;

	        String angularReadyScript = "var callback = arguments[arguments.length - 1];"
	                + "if (window.getAllAngularTestabilities) {"
	                + "  var testabilities = window.getAllAngularTestabilities();"
	                + "  var count = testabilities.length;"
	                + "  var decrement = function() {"
	                + "    count--;"
	                + "    if (count === 0) {"
	                + "      callback(true);"
	                + "    }"
	                + "  };"
	                + "  testabilities.forEach(function(testability) {"
	                + "    testability.whenStable(decrement);"
	                + "  });"
	                + "} else {"
	                + "  callback(false);"
	                + "}";

	        wait.until(driver1 -> jsExec.executeAsyncScript(angularReadyScript));
	    }
	 
	 public static void scrollDownAndUp(WebDriver driver) throws InterruptedException {
		 JavascriptExecutor jsExec = (JavascriptExecutor) driver;

         // Scroll down to the bottom slowly
         String scrollDownScript = "window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });";
         jsExec.executeScript(scrollDownScript);
         
         // Wait for the scroll to complete
         Thread.sleep(3000); // Adjust the sleep time if needed

         // Scroll up to the top slowly
         String scrollUpScript = "window.scrollTo({ top: 0, behavior: 'smooth' });";
         jsExec.executeScript(scrollUpScript);
	 }
}
