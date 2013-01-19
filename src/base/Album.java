package base;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import javax.swing.SwingUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import base.CollageEvent.EventType;


public class Album {
	String name;
	String artist;
	Image art;
	int playcount;
	
	static class AlbumRunnable implements Runnable
	{
		JSONObject jsonAlbum;
		Collage collage;
		int row;
		int column;
		boolean drawText;
		Set<CollageListener> listeners;
		
		AlbumRunnable(JSONObject jsonAlbum, Collage collage, int row, int column, boolean drawText, Set<CollageListener> listeners)
		{
			this.jsonAlbum = jsonAlbum;
			this.collage = collage;
			this.row = row;
			this.column = column;
			this.drawText = drawText;
			this.listeners = listeners;
		}

		@Override
		public void run() {
			Album album = new Album(this.jsonAlbum);
			Graphics2D collageG2D = collage.getImage().createGraphics();
			collageG2D.drawImage(album.art, column * 300, row * 300, 300, 300, null);
			
			if (drawText) {
		        Image artistText = createScaledTextImage(album.artist);
		        Image albumText = createScaledTextImage(album.name);
				collageG2D.drawImage(artistText, column * 300 + 5, row * 300 + 5, null);
				collageG2D.drawImage(albumText, column * 300 + 5, row * 300 + 25, null);
			}
			
			SwingUtilities.invokeLater(new Runnable () {
				public void run() {
					CollageEvent event = new CollageEvent(collage, EventType.UPDATED);
					for (CollageListener listener : listeners) 
						listener.collageUpdated(event);
				}
			});
		}
		
		private Image createScaledTextImage(String text) {
			BufferedImage textImage = new BufferedImage(870, 60, BufferedImage.TYPE_INT_ARGB);
			Graphics2D textG2D = textImage.createGraphics();
			FontRenderContext frc = textG2D.getFontRenderContext();
			Font font = new Font("Impact", Font.PLAIN, 60);
			
	        GlyphVector gv = font.createGlyphVector(frc, text);
	        Rectangle2D box = gv.getVisualBounds();
	        float xOff = (float) -box.getX();
	        float yOff = (float) -box.getY();
	        Shape shape = gv.getOutline(xOff,yOff);
	        
	        textG2D.setColor(Color.WHITE);
	        textG2D.fill(shape);
	        
	        textG2D.setStroke(new BasicStroke(2f));
	        textG2D.setColor(Color.BLACK);
	        textG2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        textG2D.draw(shape);
	        Image scaledText = textImage.getScaledInstance(290, 20, Image.SCALE_SMOOTH);
	        
	        return scaledText;
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
			this.art = util.ImageIO.read(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
