package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import base.Collage;
import base.CollageListener;
import base.CollageSettings;
import base.CollageEvent;
import gui.ImagePanel;
import gui.MainFrame;

public class MainController implements ActionListener, CollageListener {
	SettingsPanelController settingsPanelController;
	MainFrame mainFrame;
	Collage collage;
	
	public MainController(CollageSettings initialSettings) {
		this.collage = new Collage(initialSettings);
		this.settingsPanelController = new SettingsPanelController(initialSettings);
		this.mainFrame = new MainFrame(settingsPanelController.settingsPanel);
		
		JButton generateCollageButton = mainFrame.getCollageGenerationPanel().getGenerateCollageButton();
		generateCollageButton.setActionCommand("generate");
		generateCollageButton.addActionListener(this);
		
		JButton saveCollageButton = mainFrame.getCollageGenerationPanel().getSaveCollageButton();
		saveCollageButton.setActionCommand("save");
		saveCollageButton.addActionListener(this);
		
		collage.addCollageListener(this);
		
		mainFrame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand())
		{
		case "generate":
			Runnable collageGeneratorRunnable = new Runnable() {
				@Override
				public void run() {
					collage.generateCollage(new Runnable() {
		
						@Override
						public void run() {
							SwingUtilities.invokeLater(new Runnable() {
		
								@Override
								public void run() {
									settingsPanelController.settingsPanel.setEnabled(true);
									mainFrame.getCollageGenerationPanel().setEnabled(true);
									mainFrame.getImagePanel().repaint();
								}
								
							});
						}
						
					});
				}
			};
			Thread thread = new Thread(collageGeneratorRunnable);
			thread.start();
			
			mainFrame.getImagePanel().image = collage.getImage();
			settingsPanelController.settingsPanel.setEnabled(false);
			mainFrame.getCollageGenerationPanel().setEnabled(false);
			mainFrame.getImagePanel().repaint();
            break;
		case "save":
			collage.saveToFile();
			break;
		}
    }
	
	public static void main(String[] args) {
		
		CollageSettings settings = new CollageSettings(args);
		
		MainController mainController = new MainController(settings);
	}

	@Override
	public void collageUpdated(CollageEvent event) {
		ImagePanel imagePanel = mainFrame.getImagePanel();
		imagePanel.image = event.getCollage().getImage();
		mainFrame.getImagePanel().repaint();
	}
}
