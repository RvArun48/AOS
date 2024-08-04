package com.aos.utils;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aos.specification.AosSpecification;

public class DateAndTimeUtil {
	public static final Logger logger = LogManager.getLogger(DateAndTimeUtil.class);

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

	/**
	 * 
	 * @param otherStartTime     - Start Time extracted from card
	 * @param extractedStartTime - Start Time extracted from slider
	 * @param otherEndTime       - End Time extracted from card
	 * @param extractedEndTime   - End Time extracted from slider
	 * @return
	 */
	public static boolean compareTimes(LocalTime otherStartTime, LocalTime extractedStartTime, LocalTime otherEndTime,
			LocalTime extractedEndTime) {

		logger.info("otherStartTime:" + otherStartTime + " extractedStartTime:" + extractedStartTime + " otherEndTime:"
				+ otherEndTime + " extractedEndTime:" + extractedEndTime);
		if (extractedStartTime != null && extractedEndTime != null && otherStartTime != null && otherEndTime != null) {
			if (otherStartTime.isAfter(extractedStartTime) && otherEndTime.isBefore(extractedEndTime)) {
				logger.info("The other time range is within the extracted time range.");
				return true;
			} else {
				logger.info("The other time range is not within the extracted time range.");
				return false;
			}
		} else {
			logger.info("Failed to extract times correctly.");
		}
		return false;
	}

}
