package gui;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import base.TimePeriod;

@SuppressWarnings("serial")
public class TimePeriodChooserPanel extends JPanel {
	
	Map<TimePeriod, JRadioButton> periodButtons;

	public TimePeriodChooserPanel(TimePeriod initialPeriod) {
		super(new GridLayout(3, 2));
		
		periodButtons = new HashMap<TimePeriod, JRadioButton>();
		
	    setBorder(BorderFactory.createTitledBorder("Time Period"));
	    ButtonGroup group = new ButtonGroup();
	    for (TimePeriod period : TimePeriod.values())
	    {
	    	JRadioButton option = new JRadioButton(period.getGUIString());
	    	group.add(option);
	    	add(option);
	    	periodButtons.put(period, option);
	    }
	    
	    periodButtons.get(initialPeriod).setSelected(true);
	    
	    this.setMaximumSize(new Dimension(200,300));
	}
	
	@Override
	public void setEnabled(boolean b) {
		for (JRadioButton button : periodButtons.values()) {
			button.setEnabled(b);
		}
		
		super.setEnabled(b);
	}

	/**
	 * @return the periodButtons
	 */
	public Map<TimePeriod, JRadioButton> getPeriodButtons() {
		return periodButtons;
	}
}