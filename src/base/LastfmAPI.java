package base;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import util.JSON;

public class LastfmAPI {
	
	static JSONParser parser;
	static {
		parser = new JSONParser();
	}

	public static List<JSONObject> getTopAlbums(String key, String userName, TimePeriod period, int limit)
	{
		String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + userName + "&period=" + period.getLastfmString() + "&limit=" + Integer.toString(limit) + "&api_key=" + key + "&format=json";
		JSONObject json = JSON.getJson(url, parser);
		JSONObject topalbums = (JSONObject) json.get("topalbums");
		if (topalbums == null)
		{
			JSONObject error = (JSONObject) json.get("error");
			String lastfmError = (String) error.get("message");
			System.err.println("Error fetching data from Last.fm. " + lastfmError);
			return null;
		}
		List<JSONObject> jsonAlbums = (JSONArray) topalbums.get("album");
		
		return jsonAlbums;
	}

}
