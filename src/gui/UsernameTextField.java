package gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import base.LastfmCollage;

@SuppressWarnings("serial")
public class UsernameTextField extends JTextField implements DocumentListener {
	
	LastfmCollage lastfmCollage;
	
	public UsernameTextField(LastfmCollage lastfmCollage) {
		super(lastfmCollage.username);
		
		this.lastfmCollage = lastfmCollage;
		
		this.getDocument().addDocumentListener(this);
		this.setBorder(BorderFactory.createTitledBorder("Last.fm Username"));
		this.setMaximumSize(new Dimension(200, 15));
		this.setMinimumSize(new Dimension(150, 15));
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		try {
			lastfmCollage.username = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		try {
			lastfmCollage.username = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try {
			lastfmCollage.username = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
