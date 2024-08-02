package com.aos.utils;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringUtils {
	public static final Logger logger = LogManager.getLogger(StringUtils.class);
	public static Double ConvertStringToDouble(String input) {
		

		String numericalString = input.replaceAll("[^\\d.,]", ""); // Remove all non-digit characters except '.', ','
		double value = 0;
		numericalString = numericalString.replace(",", ""); // Remove commas

		try {
			value = Double.parseDouble(numericalString);
			logger.info("Parsed double value: " + value);
		} catch (NumberFormatException e) {
			logger.info("Unable to parse the number");
		}
		return value;
	}

	public static LocalTime extractTime(String input) {
		String time = "";
		// Define the regular expression pattern for time (HH:mm)
		String timePattern = "\\b\\d{2}:\\d{2}\\b";

		// Compile the pattern
		Pattern pattern = Pattern.compile(timePattern);

		// Match the pattern against the input string
		Matcher matcher = pattern.matcher(input);

		// Check if a match is found
		if (matcher.find()) {
			// Extract the matched time
			time = matcher.group();
			logger.info("Extracted Time: " + time);
		} else {
			logger.info("No time found in the string.");
		}
		return LocalTime.parse(time);
	}
	
	
}
