package gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class LabeledSpinner {

	private Spinner spinner;
	private Label label;

	/* Assumes parent is using a 2 column grid */
	LabeledSpinner(Composite parent, String labelText) {		
		this.label = new Label(parent, SWT.HORIZONTAL);
		this.label.setText(labelText);
		
		this.spinner = new Spinner(parent, SWT.BORDER);
		this.spinner.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
	}
	
	public void setValues(int selection, int minimum, int maximum, int digits, int increment, int pageIncrement) {
		spinner.setValues(selection, minimum, maximum, digits, increment, pageIncrement);
	}

	public void setEnabled(boolean enabled) {
		this.label.setEnabled(enabled);
		this.spinner.setEnabled(enabled);
	}

}
