import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Album {
	String name;
	String artist;
	Image art;
	int playcount;
	
	static class AlbumRunnable implements Runnable
	{
		JSONObject jsonAlbum;
		BufferedImage collage;
		int row;
		int column;
		
		AlbumRunnable(JSONObject jsonAlbum, BufferedImage collage, int row, int column)
		{
			this.jsonAlbum = jsonAlbum;
			this.collage = collage;
			this.row = row;
			this.column = column;
		}

		@Override
		public void run() {
			Album album = new Album(this.jsonAlbum);
			Graphics2D collageG2D = collage.createGraphics();
			collageG2D.drawImage(album.art, column * 300, row * 300, null);
		}
		
	}
	
	Album(JSONObject jsonAlbum) {
		// Album name
		this.name = (String) jsonAlbum.get("name");
		
		// Album artist
		JSONObject jsonArtist = (JSONObject) jsonAlbum.get("artist");
		this.artist = (String) jsonArtist.get("name");
		
		// Playcount
		this.playcount = Integer.valueOf(((String) jsonAlbum.get("playcount")));
		
		// Album art
		JSONArray jsonImageArray = (JSONArray) jsonAlbum.get("image");
		JSONObject jsonImage = (JSONObject) jsonImageArray.get(3);
		String imageURL = (String) jsonImage.get("#text");
		try {
			URL url = new URL(imageURL);
			this.art = ImageIO.read(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
