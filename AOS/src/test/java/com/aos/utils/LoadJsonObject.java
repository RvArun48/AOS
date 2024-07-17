package com.aos.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.EventLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class LoadJsonObject {

	public static final Logger logger = LogManager.getLogger(LoadJsonObject.class);
	
	public static JSONObject getJsonObject(String fileName, String object)
			throws FileNotFoundException, IOException, ParseException {
		JsonReader jr = null;
		JSONObject obj = null;
		try {
			jr = new JsonReader();
			obj = jr.getDataJsonObject(fileName, object);
			if (obj != null) {
				//logger.info("Object is not null");

			} else {
				//logger.info("Object is null");
			}
		} catch (Exception e) {
			logger.info("Exception occured in getJSONObject()" + e.getLocalizedMessage());
		}
		return obj;
	}

	public static JSONObject getJsonObject(String fileName, int dataIndex)
			throws FileNotFoundException, IOException, ParseException {
		JsonReader jr = null;
		JSONObject obj = null;
		try {
			jr = new JsonReader();
			obj = jr.getDataJsonObjectFromArray(fileName, dataIndex);
			if (obj != null) {
				//logger.info("Object is not null");
			} else {
				//logger.info("Object is null");
			}
		} catch (Exception e) {
			logger.info("Exception occured in getJSONObject()" + e.getLocalizedMessage());
		}
		return obj;
	}

}
