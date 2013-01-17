package gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import base.CollageSettings;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {
	UsernameTextField usernameTextField;
	TimePeriodChooserPanel timePeriodChooser;
	DimensionsPanel dimensionsPanel;
	
	public SettingsPanel(CollageSettings settings)
	{
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		usernameTextField = new UsernameTextField(settings.username);
		this.add(usernameTextField);
		
		timePeriodChooser = new TimePeriodChooserPanel(settings.period);
		this.add(timePeriodChooser);
		
		dimensionsPanel = new DimensionsPanel(settings.rowCount, settings.colCount);
		this.add(dimensionsPanel);
		
		JPanel emptyPanel = new JPanel();
		this.add(emptyPanel);
		
		this.setBorder(BorderFactory.createTitledBorder(""));
	}
	
	@Override
	public void setEnabled(boolean b) {
		usernameTextField.setEnabled(b);
		timePeriodChooser.setEnabled(b);
		dimensionsPanel.setEnabled(b);
		
		super.setEnabled(b);
	}

	/**
	 * @return the usernameTextField
	 */
	public UsernameTextField getUsernameTextField() {
		return usernameTextField;
	}

	/**
	 * @return the timePeriodChooser
	 */
	public TimePeriodChooserPanel getTimePeriodChooser() {
		return timePeriodChooser;
	}

	/**
	 * @return the dimensionsPanel
	 */
	public DimensionsPanel getDimensionsPanel() {
		return dimensionsPanel;
	}

}
