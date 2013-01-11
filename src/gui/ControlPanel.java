package gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import base.LastfmCollage;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	UsernameTextField usernameTextField;
	TimePeriodChooserPanel timePeriodChooser;
	DimensionsPanel dimensionsPanel;
	JButton generateCollageButton;
	JButton saveCollageButton;
	
	LastfmCollage lastfmCollage;
	
	public ControlPanel(MainFrame parent, LastfmCollage lastfmCollage)
	{
		super();
		
		this.lastfmCollage = lastfmCollage;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		usernameTextField = new UsernameTextField(lastfmCollage);
		this.add(usernameTextField);
		
		timePeriodChooser = new TimePeriodChooserPanel(lastfmCollage);
		this.add(timePeriodChooser);
		
		dimensionsPanel = new DimensionsPanel(lastfmCollage);
		this.add(dimensionsPanel);
		
		generateCollageButton = new JButton("Generate Collage");
		generateCollageButton.setActionCommand("generate");
		generateCollageButton.addActionListener(parent);
		generateCollageButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(generateCollageButton);
		
		saveCollageButton = new JButton("Save Collage");
		saveCollageButton.setActionCommand("save");
		saveCollageButton.addActionListener(parent);
		saveCollageButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(saveCollageButton);
		
		JPanel emptyPanel = new JPanel();
		this.add(emptyPanel);
		
		this.setBorder(BorderFactory.createTitledBorder(""));
	}
	
	@Override
	public void setEnabled(boolean b) {
		usernameTextField.setEnabled(b);
		timePeriodChooser.setEnabled(b);
		dimensionsPanel.setEnabled(b);
		generateCollageButton.setEnabled(b);
		saveCollageButton.setEnabled(b);
		
		super.setEnabled(b);
	}

}
