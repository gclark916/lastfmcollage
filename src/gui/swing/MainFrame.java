package gui.swing;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	CollageGenerationPanel collageGenerationPanel;
	SettingsPanel settingsPanel;
	ImagePanel imagePanel;
	
	public MainFrame(SettingsPanel settingsPanel) {
		super();
		
		this.settingsPanel = settingsPanel;
		this.collageGenerationPanel = new CollageGenerationPanel();
		this.imagePanel = new ImagePanel(null);
		
		setTitle("Last.fm Collage");
		setSize(800,600);
		setLocation(10,200);
		Container content = getContentPane();
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.add(this.settingsPanel);
		westPanel.add(this.collageGenerationPanel);
		
		content.add(westPanel, BorderLayout.WEST);
		
		content.add(imagePanel, BorderLayout.CENTER);
	}

	/**
	 * @return the collageGenerationPanel
	 */
	public CollageGenerationPanel getCollageGenerationPanel() {
		return collageGenerationPanel;
	}

	/**
	 * @return the settingsPanel
	 */
	public SettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	/**
	 * @return the imagePanel
	 */
	public ImagePanel getImagePanel() {
		return imagePanel;
	}
}
