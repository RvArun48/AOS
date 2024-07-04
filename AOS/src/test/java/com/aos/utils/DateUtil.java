package com.aos.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	/**
	 * Adds the specified number of days to the current date and returns the result
	 * as a string.
	 *
	 * @param daysToAdd the number of days to add to the current date
	 * @return the new date as a string in the requested format
	 */
	public static String addDaysToCurrentDate(int daysToAdd, String pattern) {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Add the specified number of days to the current date
		LocalDate newDate = currentDate.plusDays(daysToAdd);

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		// Convert the new date to a string using the defined format
		return newDate.format(formatter);
	}

}
