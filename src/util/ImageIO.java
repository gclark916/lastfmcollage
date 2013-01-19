package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;

public class ImageIO {
	static public BufferedImage read(URL url) throws IOException {
		BufferedImage img = null;
		InputStream is = url.openStream();
		ImageInputStream iis = javax.imageio.ImageIO.createImageInputStream(is);
		try {
		    for (Iterator<javax.imageio.ImageReader> i = javax.imageio.ImageIO.getImageReaders(iis); 
		         img == null && i.hasNext(); ) {
		        javax.imageio.ImageReader r = i.next();
		        try {
		            r.setInput(iis);
		            img = r.read(0);
		        } catch (IOException e) {}
		    }
		} finally {
		    iis.close();
		}
		return img;
	}
	
	static public boolean writeHighQualityJPG(BufferedImage bufferedImage, File file) throws FileNotFoundException, IOException {
		Iterator<ImageWriter> iter = javax.imageio.ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = iter.next();
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwp.setCompressionQuality(1);
		FileImageOutputStream output = new FileImageOutputStream(file);
		writer.setOutput(output);
		IIOImage iioimage = new IIOImage(bufferedImage, null, null);
		writer.write(null, iioimage, iwp);
		writer.dispose();

		return false;
	}
}
