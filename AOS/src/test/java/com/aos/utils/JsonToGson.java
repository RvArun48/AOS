package com.aos.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class JsonToGson {

	public static Object convertToObj(String fileName, String object, Type listType) {
		try {
			return new Gson().fromJson(LoadJsonObject.getJsonObject(fileName, object).toString(), listType);
		} catch (Exception e) {
			System.out.println("Exception occured at convertToObj()" + e.getLocalizedMessage());
		}
		return null;

	}

	public static Object convertToObjFromArray(String fileName, int dataIndex, Type listType) {
		try {
			return new Gson().fromJson(LoadJsonObject.getJsonObject(fileName, dataIndex).toString(), listType);
		} catch (Exception e) {
			System.out.println("Exception occured at convertToObj()" + e.getLocalizedMessage());
		}
		return null;

	}

}
