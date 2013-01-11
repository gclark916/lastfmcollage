package base;
import gui.ImagePanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import util.JSON;

public class LastfmCollage {
	
	public String key;
	public JSONParser parser;
	public String username;
	public int rowCount;
	public int colCount;
	public TimePeriod period;
	public BufferedImage collage;	
	
	public LastfmCollage(String username, int rowCount, int colCount, TimePeriod period, String key) {
		this.username = username;
		this.rowCount = rowCount;
		this.colCount = colCount;
		this.period = period;
		this.key = key;
		this.parser = new JSONParser();
	}
	
	class CollageGenerator implements Runnable {
		
		LastfmCollage lastfmCollage;
		ImagePanel imagePanel;
		Runnable runAfter;
		
		CollageGenerator(LastfmCollage lastfmCollage, ImagePanel imagePanel, Runnable runAfter) {
			this.lastfmCollage = lastfmCollage;
			this.imagePanel = imagePanel;
			this.runAfter = runAfter;
		}
		
		@Override
		// TODO: accessing and editing non-mutexed variables from a Runnable is a bad idea
		public void run() {
			ExecutorService threadPool = Executors.newFixedThreadPool(16);
			
			BufferedImage collage = new BufferedImage(300 * colCount, 300 * rowCount, BufferedImage.TYPE_INT_RGB);
			imagePanel.image = collage;
			
			List<JSONObject> jsonAlbums = getTopAlbums(username, period, rowCount * colCount);
			int row = 0;
			int column = 0;
			for (JSONObject jsonAlbum : jsonAlbums)
			{
				Runnable runnable = new Album.AlbumRunnable(jsonAlbum, collage, row, column, imagePanel);
				column++;
				if (column == colCount)
				{
					column = 0;
					row++;
				}
				threadPool.execute(runnable);
			}
			
			threadPool.shutdown();
			try {
				threadPool.awaitTermination(1, TimeUnit.MINUTES);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						imagePanel.repaint();
					}
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			lastfmCollage.collage = collage;
			
			runAfter.run();
		}
		
	}
	
	public void generateCollage(ImagePanel imagePanel, Runnable runAfter) {
		CollageGenerator collageGenerator = new CollageGenerator(this, imagePanel, runAfter);
		Thread thread = new Thread(collageGenerator);
		thread.start();
	}
	
	public void saveCollageToFile()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmssSSS-zzz");
		String formattedDate = formatter.format(new Date());
		String filename = username + "-" + Integer.toString(rowCount) + "x" + Integer.toString(colCount) + "-" + period.getLastfmString() + "-" + formattedDate + ".jpg";
		File imageFile = new File(filename);
		try {
			ImageIO.write(collage, "JPG", imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			imageFile.delete();
		}
	}

	List<JSONObject> getTopAlbums(String userName, TimePeriod period, int num)
	{
		String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + userName + "&period=" + period.getLastfmString() + "&limit=" + Integer.toString(num) + "&api_key=" + key + "&format=json";
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
