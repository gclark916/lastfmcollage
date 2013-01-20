package gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class UsernameTextBox {

	private Group group;
	private Text usernameText;

	public UsernameTextBox(Composite parent) {		
		this.group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		this.group.setText("Last.fm Username");
		this.group.setLayout(new GridLayout(1, true));
		
		this.usernameText = new Text(group, SWT.BORDER);
		this.usernameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		this.usernameText.pack();
		
		this.group.pack();
	}

	public void pack() {
		this.group.pack();
	}
	
	public void setText(String text) {
		usernameText.setText(text);
	}
	
	public void setLayoutData(Object layoutData) {
		this.group.setLayoutData(layoutData);
	}

	public void setEnabled(boolean enabled) {
		this.group.setEnabled(enabled);
		this.usernameText.setEnabled(enabled);
	}

}
