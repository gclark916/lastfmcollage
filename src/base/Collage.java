package base;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

public class Collage {
	
	private BufferedImage image;
	CollageSettings settings;
	Set<CollageListener> listeners;
	
	public Collage(CollageSettings settings) {
		this.settings = settings;
		this.listeners = new HashSet<CollageListener>();
	}

	public void generateCollage(Runnable runAfter) {		
		ExecutorService threadPool = Executors.newFixedThreadPool(16);
		
		setImage(new BufferedImage(300 * settings.colCount, 300 * settings.rowCount, BufferedImage.TYPE_INT_RGB));
		image.createGraphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		List<JSONObject> jsonAlbums = LastfmAPI.getTopAlbums(settings.key, settings.username, settings.period, settings.rowCount * settings.colCount);
		int row = 0;
		int column = 0;
		for (JSONObject jsonAlbum : jsonAlbums)
		{
			Runnable runnable = new Album.AlbumRunnable(jsonAlbum, this, row, column, this.settings.drawText, listeners);
			column++;
			if (column == settings.colCount)
			{
				column = 0;
				row++;
			}
			threadPool.execute(runnable);
		}
		
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		runAfter.run();
	}
	
	public void saveToFile()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmssSSS-zzz");
		String formattedDate = formatter.format(new Date());
		String filename = settings.username + "-" + Integer.toString(settings.rowCount) + "x" + Integer.toString(settings.colCount) + "-" + settings.period.getLastfmString() + "-" + formattedDate + ".jpg";
		File imageFile = new File(filename);
		try {
			ImageIO.write(this.getImage(), "JPG", imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			imageFile.delete();
		}
	}
	
	public void addCollageListener(CollageListener listener) {
		this.listeners.add(listener);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
