package gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class MainComposite extends Composite {

	private SettingsPane settingsPane;
	private ImagePanel imageCanvas;
	private CollageGenerationButtonPanel buttonPanel;
	
	public MainComposite(Composite parent) {
		super(parent, SWT.NONE);
		this.setLayout(new GridLayout(2, false));
		
		Composite westPane = new Composite(this, SWT.NONE);
		GridLayout westPaneLayout = new GridLayout(1, true);
		westPaneLayout.marginWidth = 0;
		westPaneLayout.marginHeight = 0;
		westPane.setLayout(westPaneLayout);
		westPane.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		
		this.settingsPane = new SettingsPane(westPane);
		this.buttonPanel = new CollageGenerationButtonPanel(westPane);
		this.buttonPanel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		this.imageCanvas = new ImagePanel(this);
		this.imageCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		this.pack();
	}

	public SettingsPane getSettingsPane() {
		return settingsPane;
	}

	public void setSettingsPane(SettingsPane settingsPane) {
		this.settingsPane = settingsPane;
	}

	/**
	 * @return the imageCanvas
	 */
	public ImagePanel getImageCanvas() {
		return imageCanvas;
	}

	/**
	 * @return the buttonPanel
	 */
	public CollageGenerationButtonPanel getButtonPanel() {
		return buttonPanel;
	}

}
