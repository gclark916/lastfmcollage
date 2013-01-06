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
	
	public DimensionsPanel(int defaultRow, int defaultCol)
	{
		super();
		this.setLayout(new GridLayout(2, 1));
		
		rowTextField = new JLabeledTextField("Rows", Integer.toString(defaultRow));
		rowTextField.getJTextField().setMaximumSize(new Dimension(200, 15));
		rowTextField.getJTextField().getDocument().addDocumentListener(this);
		
		colTextField = new JLabeledTextField("Columns", Integer.toString(defaultCol));
		colTextField.getJTextField().setMaximumSize(new Dimension(200, 15));
		colTextField.getJTextField().getDocument().addDocumentListener(this);
		
		this.add(rowTextField);
		this.add(colTextField);
		
		this.setMaximumSize(new Dimension(200, 40));
		
		setBorder(BorderFactory.createTitledBorder("Dimensions"));
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		updateDimension(arg0);
	}

	private void updateDimension(DocumentEvent arg0) {
		try {
			if (arg0.getDocument().equals(rowTextField.getJTextField().getDocument()))
				LastfmCollage.rowCount = Integer.valueOf(arg0.getDocument().getText(0, arg0.getDocument().getLength()));
			else 
				LastfmCollage.colCount = Integer.valueOf(arg0.getDocument().getText(0, arg0.getDocument().getLength()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		updateDimension(arg0);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		updateDimension(arg0);
	}
}
