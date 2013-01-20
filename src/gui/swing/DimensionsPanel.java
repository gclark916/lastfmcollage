package gui.swing;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DimensionsPanel extends JPanel {
	LabeledSpinner rowSpinner;
	LabeledSpinner colSpinner;
	
	public DimensionsPanel(int rowCount, int colCount)
	{
		super();
		
		this.setLayout(new GridLayout(2, 1));
		
		rowSpinner = new LabeledSpinner("Rows", rowCount);
		rowSpinner.getSpinner().setMaximumSize(new Dimension(200, 15));
		
		colSpinner = new LabeledSpinner("Columns", colCount);
		colSpinner.getSpinner().setMaximumSize(new Dimension(200, 15));
		
		this.add(rowSpinner);
		this.add(colSpinner);
		
		this.setMaximumSize(new Dimension(200, 40));
		
		setBorder(BorderFactory.createTitledBorder("Dimensions"));
	}

	@Override
	public void setEnabled(boolean b) {
		rowSpinner.setEnabled(b);
		colSpinner.setEnabled(b);
		
		super.setEnabled(b);
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
}
