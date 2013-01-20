package gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class SettingsPane extends Composite {

	private UsernameTextBox usernameTextBox;
	private TimePeriodButtonGroup timePeriodButtonGroup;
	private DimensionsPanel dimensionsGroup;
	private Button showNamesCheckbox;

	public SettingsPane(Composite parent) {
		super(parent, SWT.BORDER);
		this.setLayout(new GridLayout(1, true));
		
		this.setUsernameTextBox(new UsernameTextBox(this));
		GridData textgd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		this.usernameTextBox.setLayoutData(textgd);
		
		this.timePeriodButtonGroup =  new TimePeriodButtonGroup(this);
		this.timePeriodButtonGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		this.dimensionsGroup = new DimensionsPanel(this);
		this.dimensionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		this.showNamesCheckbox = new Button(this, SWT.CHECK);
		this.showNamesCheckbox.setText("Show names");
		
		this.pack();
	}

	
	public void pack() {
		this.usernameTextBox.pack();
		this.timePeriodButtonGroup.pack();
		this.dimensionsGroup.pack();
		this.showNamesCheckbox.pack();
		super.pack();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.usernameTextBox.setEnabled(enabled);
		this.timePeriodButtonGroup.setEnabled(enabled);
		this.dimensionsGroup.setEnabled(enabled);
		this.showNamesCheckbox.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	public UsernameTextBox getUsernameTextBox() {
		return usernameTextBox;
	}

	public void setUsernameTextBox(UsernameTextBox usernameTextBox) {
		this.usernameTextBox = usernameTextBox;
	}

	/**
	 * @return the timePeriodButtonGroup
	 */
	public TimePeriodButtonGroup getTimePeriodButtonGroup() {
		return timePeriodButtonGroup;
	}

	/**
	 * @return the dimensionsGroup
	 */
	public DimensionsPanel getDimensionsGroup() {
		return dimensionsGroup;
	}

}
