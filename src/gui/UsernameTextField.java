package gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class UsernameTextField extends JTextField {
	public UsernameTextField(String defaultUsername) {
		super(defaultUsername);
		this.setBorder(BorderFactory.createTitledBorder("Last.fm Username"));
		this.setMaximumSize(new Dimension(200, 15));
		this.setMinimumSize(new Dimension(150, 15));
	}
}
