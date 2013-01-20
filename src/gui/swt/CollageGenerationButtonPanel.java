package gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class CollageGenerationButtonPanel extends Composite {
	
	private Button generateCollageButton;
	private Button saveCollageButton;

	public CollageGenerationButtonPanel(Composite parent) {
		super(parent, SWT.NONE);
		this.setLayout(new GridLayout(1, true));
		
		this.generateCollageButton = new Button(this, SWT.PUSH);
		this.generateCollageButton.setText("Generate Collage");
		this.generateCollageButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		this.saveCollageButton = new Button(this, SWT.PUSH);
		this.saveCollageButton.setText("Save Collage");
		this.saveCollageButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	/**
	 * @return the generateCollageButton
	 */
	public Button getGenerateCollageButton() {
		return generateCollageButton;
	}

	/**
	 * @return the saveCollageButton
	 */
	public Button getSaveCollageButton() {
		return saveCollageButton;
	}

}
