package com.aos.utils;

import java.lang.reflect.Type;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aos.implementation.LoginImplementation;
import com.google.gson.Gson;

public class JsonToGson {

	public static final Logger logger = LogManager.getLogger(JsonToGson.class);

	public static Object convertToObj(String fileName, String object, Type listType) {
		try {
			
			return new Gson().fromJson(LoadJsonObject.getJsonObject(fileName, object).toString(), listType);
		} catch (Exception e) {
			logger.info("Exception occured at convertToObj()" + e.toString());
		}
		return null;

	}

	public static Object convertToObjFromArray(String fileName, int dataIndex, Type listType) {
		try {
			return new Gson().fromJson(LoadJsonObject.getJsonObject(fileName, dataIndex).toString(), listType);
		} catch (Exception e) {
			logger.info("Exception occured at convertToObj()" + e.toString());
		}
		return null;

	}

}
