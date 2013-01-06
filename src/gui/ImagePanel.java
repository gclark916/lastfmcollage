package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	Image image;
	
	public ImagePanel(Image image) {
	    this.image = image;
	    this.setAlignmentX(CENTER_ALIGNMENT);
	    this.setAlignmentY(CENTER_ALIGNMENT);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    //g.drawImage(image, 0, 0, null);
		  int minDimension = this.getWidth() < this.getHeight() ? this.getWidth() : this.getHeight();
		  g.drawImage(image, 0, 0, minDimension, minDimension, null);
	  }

}
