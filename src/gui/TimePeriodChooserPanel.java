package gui;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import base.LastfmCollage;
import base.TimePeriod;

@SuppressWarnings("serial")
public class TimePeriodChooserPanel extends JPanel implements ActionListener {
	
	static Map<String, TimePeriod> actionCommandsToTimePeriods;
	static {
		Map<String, TimePeriod> tempMap = new HashMap<String, TimePeriod>();
		for (TimePeriod period : TimePeriod.values())
		{
			tempMap.put(period.getLastfmString(), period);
		}
		actionCommandsToTimePeriods = Collections.unmodifiableMap(tempMap);
	}

	public TimePeriodChooserPanel(TimePeriod defaultPeriod) {
		super(new GridLayout(3, 2));
	    setBorder(BorderFactory.createTitledBorder("Time Period"));
	    ButtonGroup group = new ButtonGroup();
	    JRadioButton option;
	    for (TimePeriod period : TimePeriod.values())
	    {
	    	option = new JRadioButton(period.getGUIString());
	    	if (defaultPeriod.equals(period))
	    		option.setSelected(true);
	    	option.addActionListener(this);
	    	option.setActionCommand(period.getLastfmString());
	    	group.add(option);
	    	add(option);
	    }
	    
	    this.setMaximumSize(new Dimension(200,300));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TimePeriod period = actionCommandsToTimePeriods.get(e.getActionCommand());
		LastfmCollage.period = period;
	}
}