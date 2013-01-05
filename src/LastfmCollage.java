import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
		int rowCount = 0;
		int colCount = 0;
		TimePeriod period = TimePeriod.DAY7;
		
		for (int argIndex = 0, argCount = args.length; argIndex < argCount; argIndex++)
		{
			String arg = args[argIndex];
			switch (arg)
			{
			case "-u":
				username = args[argIndex+1];
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
				break;
			case "-r":
				rowCount = Integer.valueOf(args[argIndex+1]);
				break;
			case "-c":
				colCount = Integer.valueOf(args[argIndex+1]);
				break;
			case "-k":
				key = args[argIndex+1];
				break;
			}
		}

		if (username.isEmpty())
		{
			System.err.print("No username given");
			System.exit(-1);
		}
		
		if (rowCount <= 0)
		{
			if (colCount <= 0)
			{
				rowCount = 3;
				colCount = 3;
			}
			
			rowCount = colCount;
		}
		else
		{
			if (colCount <= 0)
				colCount = rowCount;
		}
		
		parser = new JSONParser();
		ExecutorService threadPool = Executors.newFixedThreadPool(16);
		
		BufferedImage collage = new BufferedImage(300 * colCount, 300 * rowCount, BufferedImage.TYPE_INT_RGB);
		
		List<JSONObject> jsonAlbums = getTopAlbums(username, period, rowCount * colCount);
		int row = 0;
		int column = 0;
		for (JSONObject jsonAlbum : jsonAlbums)
		{
			Runnable runnable = new Album.AlbumRunnable(jsonAlbum, collage, row, column);
			column++;
			if (column == colCount)
			{
				column = 0;
				row++;
			}
			threadPool.execute(runnable);
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmssSSS-zzz");
		String formattedDate = formatter.format(new Date());
		String filename = username + "-" + Integer.toString(rowCount) + "x" + Integer.toString(colCount) + "-" + period.lastfmString + "-" + formattedDate + ".jpg";
		File imageFile = new File(filename);
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(2, TimeUnit.MINUTES);
			ImageIO.write(collage, "JPG", imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			imageFile.delete();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			imageFile.delete();
		}
	}

	
	static List<JSONObject> getTopAlbums(String userName, TimePeriod period, int num)
	{
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
		
		return jsonAlbums;
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
}
