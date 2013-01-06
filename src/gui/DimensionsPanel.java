package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DimensionsPanel extends JPanel {
	JLabeledTextField rowTextField;
	JLabeledTextField colTextField;
	
	public DimensionsPanel(int defaultRow, int defaultCol)
	{
		super();
		this.setLayout(new GridLayout(2, 1));
		
		rowTextField = new JLabeledTextField("Rows", Integer.toString(defaultRow));
		rowTextField.getJTextField().setMaximumSize(new Dimension(200, 15));
		rowTextField.setPreferredSize(new Dimension(200, 15));
		colTextField = new JLabeledTextField("Columns", Integer.toString(defaultCol));
		colTextField.getJTextField().setMaximumSize(new Dimension(200, 15));
		this.add(rowTextField);
		this.add(colTextField);
		
		this.setMaximumSize(new Dimension(200, 40));
		
		setBorder(BorderFactory.createTitledBorder("Dimensions"));
	}
}
