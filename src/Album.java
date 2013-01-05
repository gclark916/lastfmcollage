import java.awt.Image;
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
	
	Album(JSONObject jsonAlbum) {
		// Album name
		this.name = (String) jsonAlbum.get("name");
		
		// Album artist
		JSONObject jsonArtist = (JSONObject) jsonAlbum.get("artist");
		this.artist = (String) jsonArtist.get("name");
		
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
