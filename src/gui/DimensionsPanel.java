package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import base.LastfmCollage;

@SuppressWarnings("serial")
public class DimensionsPanel extends JPanel implements ChangeListener {
	LabeledSpinner rowSpinner;
	LabeledSpinner colSpinner;
	LastfmCollage lastfmCollage;
	
	public DimensionsPanel(LastfmCollage lastfmCollage)
	{
		super();
		
		this.lastfmCollage = lastfmCollage;
		
		this.setLayout(new GridLayout(2, 1));
		
		rowSpinner = new LabeledSpinner("Rows", lastfmCollage.rowCount);
		rowSpinner.getSpinner().setMaximumSize(new Dimension(200, 15));
		rowSpinner.getSpinner().addChangeListener(this);
		
		colSpinner = new LabeledSpinner("Columns", lastfmCollage.colCount);
		colSpinner.getSpinner().setMaximumSize(new Dimension(200, 15));
		colSpinner.getSpinner().addChangeListener(this);
		
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

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource().equals(rowSpinner.getSpinner())) 
			lastfmCollage.rowCount = rowSpinner.getValue();
		else
			lastfmCollage.colCount = colSpinner.getValue();
	}
}
