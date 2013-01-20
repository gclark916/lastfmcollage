package gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class DimensionsPanel {

	private Group group;
	private LabeledSpinner rowSpinner;
	private LabeledSpinner colSpinner;

	public DimensionsPanel(Composite parent) {
		this.group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		this.group.setText("Dimensions");
		this.group.setLayout(new GridLayout(2, false));
		this.group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		this.rowSpinner = new LabeledSpinner(group, "Rows");
		
		this.colSpinner = new LabeledSpinner(group, "Columns");
	}

	public void pack() {
		this.group.pack();
	}

	/**
	 * @return the rowSpinner
	 */
	public LabeledSpinner getRowSpinner() {
		return rowSpinner;
	}

	/**
	 * @return the colSpinner
	 */
	public LabeledSpinner getColSpinner() {
		return colSpinner;
	}

	public void setLayoutData(GridData gridData) {
		this.group.setLayoutData(gridData);
	}

	public void setEnabled(boolean enabled) {
		this.rowSpinner.setEnabled(enabled);
		this.colSpinner.setEnabled(enabled);
		this.group.setEnabled(enabled);
	}

}
