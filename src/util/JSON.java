package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {
	
	public static JSONObject getJson(String url, JSONParser parser){
		
		InputStream is = null;
		String result = "";
		JSONObject jsonObject = null;
		
		// HTTP
		try {
			URL request = new URL(url);
			is = request.openStream();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	    
		// Read response to string
		try {	    	
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();	            
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
    
		// Convert string to object
		try {
			jsonObject = (JSONObject) parser.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    
		return jsonObject;
 
	}
}
