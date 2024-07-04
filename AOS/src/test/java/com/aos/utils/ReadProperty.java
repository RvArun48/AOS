package com.aos.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {
	public static String getPropValues(String key, String fileName) {

		String result = null;
		try {

			FileInputStream input = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//config.properties");

			Properties prop = new Properties();

			prop.load(input);

			result = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;

	}

}
