package controller.swt;

import java.util.Map;

import org.eclipse.swt.widgets.Button;

import gui.swt.SettingsPane;
import base.CollageSettings;
import base.TimePeriod;

public class SettingsPaneController {

	private CollageSettings settings;
	private SettingsPane settingsPane;

	public SettingsPaneController(CollageSettings initialSettings, SettingsPane settingsPane) {
		this.settings = initialSettings;
		this.setSettingsPane(settingsPane);
		
		settingsPane.getUsernameTextBox().setText(this.settings.username);
		
		Map<TimePeriod, Button> buttonMap = settingsPane.getTimePeriodButtonGroup().getTimePeriodButtonMap();
		Button button = buttonMap.get(this.settings.period);
		button.setSelection(true);
		
		settingsPane.getDimensionsGroup().getRowSpinner().setValues(this.settings.rowCount, 1, 100, 0, 1, 5);
		settingsPane.getDimensionsGroup().getColSpinner().setValues(this.settings.rowCount, 1, 100, 0, 1, 5);
	}

	public SettingsPane getSettingsPane() {
		return settingsPane;
	}

	public void setSettingsPane(SettingsPane settingsPane) {
		this.settingsPane = settingsPane;
	}

}
