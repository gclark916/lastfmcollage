package gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import base.CollageSettings;

@SuppressWarnings("serial")
public class UsernameTextField extends JTextField {
	
	public UsernameTextField(String username) {
		super(username);
		
		this.setBorder(BorderFactory.createTitledBorder("Last.fm Username"));
		this.setMaximumSize(new Dimension(200, 15));
		this.setMinimumSize(new Dimension(150, 15));
	}
}
