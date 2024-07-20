package com.aos.utils;

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
}
