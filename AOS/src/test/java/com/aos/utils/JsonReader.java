package com.aos.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.core.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

	private static final String LOCAL = "local";
	private static final String CONFIG = "config";
	private static final String ENVIRONMENT = "ENVIRONMENT";

	/*
	 * public JSONObject getDataJsonObject(String fileName) throws
	 * FileNotFoundException, IOException, ParseException {
	 * 
	 * Object data = new JSONParser().parse( new
	 * FileReader(System.getProperty("user.dir") + "//src//test//resources//" +
	 * fileName + ".json")); JSONObject object = null; object = (JSONObject) data;
	 * return object; }
	 */

	public JSONObject getDataJsonObject(String fileName, String keyData)
			throws FileNotFoundException, IOException, ParseException {
		Object data = null;
		data = new JSONParser().parse(
				new FileReader(System.getProperty("user.dir") + "//src//test//resources//testData//" + fileName + ".json"));

		JSONObject object = null;
		object = (JSONObject) data;
		return (JSONObject) object.get(keyData);
	}

	public JSONObject getDataJsonObjectFromArray(String fileName, int lineItem)
			throws FileNotFoundException, IOException, ParseException {

		Object data = null;

		data = new JSONParser().parse(
				new FileReader(System.getProperty("user.dir") + "//src//test//resources//testData//" + fileName + ".json"));

		JSONArray array = (JSONArray) data;
		return (JSONObject) array.get(lineItem);

	}

}
