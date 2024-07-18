package com.aos.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestUtils {
	
	public static int getResponseCode(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode;
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Return -1 to indicate failure
        }
    }

}
