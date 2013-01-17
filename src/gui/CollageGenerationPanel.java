package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CollageGenerationPanel extends JPanel {
	JButton generateCollageButton;
	JButton saveCollageButton;
	
	public CollageGenerationPanel() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		generateCollageButton = new JButton("Generate Collage");
		generateCollageButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(generateCollageButton);
		
		saveCollageButton = new JButton("Save Collage");
		saveCollageButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(saveCollageButton);
	}

	/**
	 * @return the generateCollageButton
	 */
	public JButton getGenerateCollageButton() {
		return generateCollageButton;
	}

	/**
	 * @return the saveCollageButton
	 */
	public JButton getSaveCollageButton() {
		return saveCollageButton;
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		generateCollageButton.setEnabled(enabled);
		saveCollageButton.setEnabled(enabled);
	}
}
