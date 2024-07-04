package com.aos.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.EventLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class LoadJsonObject {

	public static JSONObject getJsonObject(String fileName, String object)
			throws FileNotFoundException, IOException, ParseException {
		JsonReader jr = new JsonReader();
		JSONObject obj = jr.getDataJsonObject(fileName, object);
		try {

			if (obj != null) {
				System.out.println("Object is not null");

			} else {
				System.out.println("Object is null");
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getJSONObject()" + e.getLocalizedMessage());
		}
		return obj;
	}

	public static JSONObject getJsonObject(String fileName, int dataIndex)
			throws FileNotFoundException, IOException, ParseException {
		JsonReader jr = new JsonReader();
		JSONObject obj = jr.getDataJsonObjectFromArray(fileName, dataIndex);
		try {

			if (obj != null) {
				System.out.println("Object is not null");
			} else {
				System.out.println("Object is null");
			}
		} catch (Exception e) {
			System.out.println("Exception occured in getJSONObject()" + e.getLocalizedMessage());
		}
		return obj;
	}

}
