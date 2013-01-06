package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import base.TimePeriod;

public class TimePeriodChooserPanel extends JPanel {

	public TimePeriodChooserPanel() {
		super(new GridLayout(3, 2));
	    setBorder(BorderFactory.createTitledBorder("Time Period"));
	    ButtonGroup group = new ButtonGroup();
	    JRadioButton option;
	    for (TimePeriod period : TimePeriod.values())
	    {
	    	option = new JRadioButton(period.getGUIString());
	    	group.add(option);
	    	add(option);
	    }
	    
	    this.setMaximumSize(new Dimension(200,300));
	}
}