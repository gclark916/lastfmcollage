package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;


public class MainFrame extends JFrame implements ActionListener {
	JRadioButton[] timePeriodButtons;
	JButton generateCollageButton;
	JLabeledTextField username;
	ControlPanel controlPanel;
	ImagePanel imagePanel;
	
	public MainFrame() {
		super();
		setTitle("Last.fm Collage");
		setSize(800,600);
		setLocation(10,200);
		Container content = getContentPane();
		
		controlPanel = new ControlPanel(this);
		content.add(controlPanel, BorderLayout.WEST);
		
		imagePanel = new ImagePanel(null);
		content.add(imagePanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand())
		{
		case "generate":
			Image image = base.LastfmCollage.generateCollage();
            imagePanel.image = image;
            imagePanel.repaint();
            break;
		case "save":
			base.LastfmCollage.saveCollageToFile();
			break;
		}
    }
}
