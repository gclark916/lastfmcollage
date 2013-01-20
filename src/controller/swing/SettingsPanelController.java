package controller.swing;

import gui.swing.SettingsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;


import base.CollageSettings;
import base.TimePeriod;

public class SettingsPanelController implements DocumentListener, ActionListener, ChangeListener, ItemListener {

	static Map<String, TimePeriod> actionCommandsToTimePeriods;
	static {
		Map<String, TimePeriod> tempMap = new HashMap<String, TimePeriod>();
		for (TimePeriod period : TimePeriod.values())
		{
			tempMap.put(period.getLastfmString(), period);
		}
		actionCommandsToTimePeriods = Collections.unmodifiableMap(tempMap);
	}
	
	CollageSettings settings;
	SettingsPanel	settingsPanel;
	
	public SettingsPanelController(CollageSettings settings) {
		this.settings = settings;
		this.settingsPanel = new SettingsPanel(settings);
		
		this.settingsPanel.getUsernameTextField().getDocument().addDocumentListener(this);
		Map<TimePeriod, JRadioButton> periodButtonMap = this.settingsPanel.getTimePeriodChooser().getPeriodButtons();
		for (TimePeriod period : periodButtonMap.keySet()) {
			JRadioButton button = periodButtonMap.get(period);
			button.addActionListener(this);
	    	button.setActionCommand(period.getLastfmString());
		}
		this.settingsPanel.getDimensionsPanel().getRowSpinner().getSpinner().addChangeListener(this);
		this.settingsPanel.getDimensionsPanel().getColSpinner().getSpinner().addChangeListener(this);
		this.settingsPanel.getShowNamesCheckBox().addItemListener(this);
	}

	/* DocumentListener */
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		try {
			settings.username = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		try {
			settings.username = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try {
			settings.username = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/* ActionListener */

	@Override
	public void actionPerformed(ActionEvent e) {
		TimePeriod period = actionCommandsToTimePeriods.get(e.getActionCommand());
		settings.period = period;
	}
	
	/* ChangeListener */

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource().equals(settingsPanel.getDimensionsPanel().getRowSpinner().getSpinner())) 
			settings.rowCount = settingsPanel.getDimensionsPanel().getRowSpinner().getValue();
		else
			settings.colCount = settingsPanel.getDimensionsPanel().getColSpinner().getValue();
	}

	/* ItemListener */
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() == ItemEvent.SELECTED)
			settings.drawText = true;
		else
			settings.drawText = false;
	}
}
