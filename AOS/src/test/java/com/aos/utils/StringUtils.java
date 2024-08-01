package com.aos.utils;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static Double ConvertStringToDouble(String input) {

		String numericalString = input.replaceAll("[^\\d.,]", ""); // Remove all non-digit characters except '.', ','
		double value = 0;
		numericalString = numericalString.replace(",", ""); // Remove commas

		try {
			value = Double.parseDouble(numericalString);
			System.out.println("Parsed double value: " + value);
		} catch (NumberFormatException e) {
			System.err.println("Unable to parse the number");
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
			System.out.println("Extracted Time: " + time);
		} else {
			System.out.println("No time found in the string.");
		}
		return LocalTime.parse(time);
	}
	
	
}
