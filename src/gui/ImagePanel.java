package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	public Image image;
	public ImageObserver imageObserver;
	
	public ImagePanel(final Image image) {
	    this.image = image;
	    
	    imageObserver = new ImageObserver() {

			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y,
					int width, int height) {
				
				assert(img == image);
				
				if ((infoflags & SOMEBITS | FRAMEBITS | ALLBITS) != 0) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							repaint();
						}
					});
				}
				
				return true;
			}
	    };
	    
	    this.setAlignmentX(CENTER_ALIGNMENT);
	    this.setAlignmentY(CENTER_ALIGNMENT);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
		  super.paintComponents(g);
		  Image scaledImage = null;
		  
		  int scaledWidth = 0;
		  int scaledHeight = 0;
		  if (image != null)
		  {
			  float widthScaleFactor = (float) this.getWidth() / (float) image.getWidth(null);
			  float heightScaleFactor = (float) this.getHeight() / (float) image.getHeight(null);
			  float minScaleFactor = widthScaleFactor < heightScaleFactor ? widthScaleFactor : heightScaleFactor;
			  scaledWidth = (int) (minScaleFactor * (float) image.getWidth(null));
			  scaledHeight = (int) (minScaleFactor * (float) image.getHeight(null));
			  scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		  }
		  g.drawImage(scaledImage, 0, 0, scaledWidth, scaledHeight, null);
		  g.clearRect(scaledWidth, 0, this.getWidth()-scaledWidth, this.getHeight());
		  g.clearRect(0, scaledHeight, this.getWidth(), this.getHeight()-scaledHeight);
	  }
}
