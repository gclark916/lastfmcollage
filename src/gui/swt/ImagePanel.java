package gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ImagePanel extends Canvas implements PaintListener {

	private Image image;

	public ImagePanel(Composite parent) {
		super(parent, SWT.BORDER);
		
		this.addPaintListener(this);
	}

	@Override
	public void paintControl(PaintEvent e) {
		if (image != null) {
			int imageWidth = image.getImageData().width;
			int imageHeight = image.getImageData().height;
			
			float widthScaleFactor = (float) e.width / (float) imageWidth;
			float heightScaleFactor = (float) e.height / (float) imageHeight;
			float minScaleFactor = widthScaleFactor < heightScaleFactor ? widthScaleFactor : heightScaleFactor;
			float scaledWidth = (int) (minScaleFactor * (float) imageWidth);
			float scaledHeight = (int) (minScaleFactor * (float) imageHeight);
			e.gc.setInterpolation(SWT.LOW);
			e.gc.setAntialias(SWT.ON);
			e.gc.drawImage(image, 0, 0, imageWidth, imageHeight, 0, 0, (int) scaledWidth, (int) scaledHeight);
		}
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image.dispose();
		this.image = image;
	}

}
