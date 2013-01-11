package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import base.LastfmCollage;

@SuppressWarnings("serial")
public class DimensionsPanel extends JPanel implements DocumentListener {
	JLabeledTextField rowTextField;
	JLabeledTextField colTextField;
	LastfmCollage lastfmCollage;
	
	public DimensionsPanel(LastfmCollage lastfmCollage)
	{
		super();
		
		this.lastfmCollage = lastfmCollage;
		
		this.setLayout(new GridLayout(2, 1));
		
		rowTextField = new JLabeledTextField("Rows", Integer.toString(lastfmCollage.rowCount));
		rowTextField.getJTextField().setMaximumSize(new Dimension(200, 15));
		rowTextField.getJTextField().getDocument().addDocumentListener(this);
		
		colTextField = new JLabeledTextField("Columns", Integer.toString(lastfmCollage.colCount));
		colTextField.getJTextField().setMaximumSize(new Dimension(200, 15));
		colTextField.getJTextField().getDocument().addDocumentListener(this);
		
		this.add(rowTextField);
		this.add(colTextField);
		
		this.setMaximumSize(new Dimension(200, 40));
		
		setBorder(BorderFactory.createTitledBorder("Dimensions"));
	}
	
	@Override
	public void setEnabled(boolean b) {
		rowTextField.setEnabled(b);
		colTextField.setEnabled(b);
		
		super.setEnabled(b);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		updateDimension(arg0);
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		updateDimension(arg0);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		updateDimension(arg0);
	}
	
	private void updateDimension(DocumentEvent arg0) {
		try {
			if (arg0.getDocument().equals(rowTextField.getJTextField().getDocument()))
				lastfmCollage.rowCount = Integer.valueOf(arg0.getDocument().getText(0, arg0.getDocument().getLength()));
			else 
				lastfmCollage.colCount = Integer.valueOf(arg0.getDocument().getText(0, arg0.getDocument().getLength()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
