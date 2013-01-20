package gui.swt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import base.TimePeriod;

public class TimePeriodButtonGroup {
	private Group group;
	private Map<TimePeriod, Button> timePeriodButtonMap;

	public TimePeriodButtonGroup(Composite parent) {		
		this.timePeriodButtonMap = new HashMap<TimePeriod, Button>();
		
		this.group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		this.group.setText("Time Period");
		this.group.setLayout(new GridLayout(2,true));
		
		for (TimePeriod period : TimePeriod.values()) {
			Button button = new Button(group, SWT.RADIO);
			button.setText(period.getGUIString());
			timePeriodButtonMap.put(period, button);
		}
	}

	public void pack() {
		this.group.pack();
	}

	/**
	 * @return the timePeriodButtonMap
	 */
	public Map<TimePeriod, Button> getTimePeriodButtonMap() {
		return timePeriodButtonMap;
	}

	public void setEnabled(boolean enabled) {
		for (Button button : timePeriodButtonMap.values()) {
			button.setEnabled(enabled);
		}
		
		this.group.setEnabled(enabled);
	}

	public void setLayoutData(Object gridData) {
		this.group.setLayoutData(gridData);
	}
}
