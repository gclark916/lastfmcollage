import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LastfmCollage {
	
	static String key = "d048f1e12a1c2039e45d9b94d622bc1e";
	static JSONParser parser;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String username = "";
		int rowCount = 3;
		TimePeriod period = TimePeriod.DAY7;
		
		for (int argIndex = 0, argCount = args.length; argIndex < argCount; argIndex++)
		{
			String arg = args[argIndex];
			switch (arg)
			{
			case "-u":
				username = args[argIndex+1];
				argIndex++;
				break;
			case "-p":
				String periodArg = args[argIndex+1];
				switch (periodArg)
				{
				case "overall":
					period = TimePeriod.OVERALL;
					break;
					
				case "7days":
					period = TimePeriod.DAY7;
					break;
					
				case "1month":
					period = TimePeriod.MONTH1;
					break;
					
				case "3month":
					period = TimePeriod.MONTH3;
					break;
					
				case "6month":
					period = TimePeriod.MONTH6;
					break;
					
				case "12month":
					period = TimePeriod.MONTH12;
					break;
					
				}
			}
		}
		
		if (username.isEmpty())
		{
			System.err.print("No username given");
			System.exit(-1);
		}
			
		
		parser = new JSONParser();
		
		int colCount = rowCount;
		List<Album> albums = getTopAlbums(username, period, rowCount * colCount);
		
		BufferedImage collage = new BufferedImage(300 * colCount, 300 * rowCount, BufferedImage.TYPE_INT_RGB);
		Graphics2D collageG2D = collage.createGraphics();
		int rowIndex = 0;
		int colIndex = 0;
		for (Album album : albums)
		{
			collageG2D.drawImage(album.art, colIndex * 300, rowIndex * 300, null);
			colIndex++;
			if (colIndex == colCount)
			{
				colIndex = 0;
				rowIndex++;
			}
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmssSSS-zzz");
		String formattedDate = formatter.format(new Date());
		String filename = username + "-" + Integer.toString(rowCount) + "x" + Integer.toString(colCount) + "-" + period.lastfmString + "-" + formattedDate + ".jpg";
		File imageFile = new File(filename);
		try {
			ImageIO.write(collage, "JPG", imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	static List<Album> getTopAlbums(String userName, TimePeriod period, int num)
	{
		List<Album> albums = null;
		
		String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + userName + "&period=" + period.lastfmString + "&limit=" + Integer.toString(num) + "&api_key=" + key + "&format=json";
		JSONObject json = getJson(url);
		JSONObject topalbums = (JSONObject) json.get("topalbums");
		if (topalbums == null)
		{
			JSONObject error = (JSONObject) json.get("error");
			String lastfmError = (String) error.get("message");
			System.err.println("Error fetching data from Last.fm. " + lastfmError);
			return null;
		}
		List<JSONObject> jsonAlbums = (JSONArray) topalbums.get("album");
		albums =  new LinkedList<Album>();
		for (JSONObject jsonAlbum : jsonAlbums)
		{
			Album album = new Album(jsonAlbum);
			albums.add(album);
		}
		return albums;
	}
	
	public static JSONObject getJson(String url){
		
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
	
	static class TopAlbums {
		List<Album> topalbums;
	}
}
