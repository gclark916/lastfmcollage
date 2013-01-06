package gui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	UsernameTextField usernameTextField;
	TimePeriodChooserPanel timePeriodChooser;
	DimensionsPanel dimensionsPanel;
	JButton generateCollageButton;
	JButton saveCollageButton;
	
	public ControlPanel(MainFrame parent)
	{
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		usernameTextField = new UsernameTextField("");
		this.add(usernameTextField);
		
		timePeriodChooser = new TimePeriodChooserPanel();
		this.add(timePeriodChooser);
		
		dimensionsPanel = new DimensionsPanel(3, 3);
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

}
