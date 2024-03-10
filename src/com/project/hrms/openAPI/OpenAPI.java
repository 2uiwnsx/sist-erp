package com.project.hrms.openAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OpenAPI {

	public static String checkIsHoliday(int month, int date) {
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		String today = String.format("%d%02d%02d", year, month, date);

		String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?";
		
		url += "serviceKey=iZ8x%2F3ft3nJNnO9FFzmYcmmN6gKK64i7RNRvz0Qc6abwjp6J8wbQIAJPZMPosKnPNeeMHTAhXTkTQ9TetaNaXg%3D%3D";
		url += "&pageNo=1";
		url += "&numOfRows=1000";
		url += "&_type=json";
		url += "&solYear=" + year;
		url += "&Month=" + month;

		try {
			
			URL obj_url = new URL(url);
			
			HttpURLConnection conn = (HttpURLConnection)obj_url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			BufferedReader reader = null;
			
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
			} else {
				
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				
			}
			
			JSONParser parser = new JSONParser();
			
			JSONObject root = (JSONObject)parser.parse(reader);	
			
			JSONObject response = (JSONObject)root.get("response");	
			
			JSONObject body = (JSONObject)response.get("body");
			
			JSONObject items = (JSONObject)body.get("items");
			
			JSONArray arr = (JSONArray)items.get("item");
				
			for (Object obj : arr) {
					
				JSONObject item = (JSONObject)obj;
					
				String dateName = item.get("dateName").toString();
				String locdate = item.get("locdate").toString();
					
				if (today.equals(locdate)) {
						
					return dateName;
						
				}
						
			}

			reader.close();
			
			conn.disconnect();
			
		} catch (Exception e) {
			
			System.out.println("at OpenAPI.checkIsHoliday");
			
			e.printStackTrace();
			
		}
		
		return "";

	}

}
