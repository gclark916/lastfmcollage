package gui.swing;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class LabeledSpinner extends JPanel {
	private JLabel label;
	private JSpinner spinner;

  public LabeledSpinner(String labelString, int value, LayoutManager layout) {
    super();
    setLayout(layout);
    label = new JLabel(labelString);
    spinner = new JSpinner( new SpinnerNumberModel(value, 0, 100, 1));
    add(label);
    add(spinner);
  }
 
  public LabeledSpinner(String labelString, int value) {
    this(labelString, value, new GridLayout());
  }

  public JLabel getJLabel() {
    return(label); 
  }

  public JSpinner getSpinner() { 
    return(spinner);
  }

  public int getValue() {
    return (int) spinner.getValue();
  }

  public void setValue(int value) {
    getSpinner().setValue(value);
  }

  public void setFonts(Font f) {
    getJLabel().setFont(f);
    getSpinner().setFont(f);
  }

  @Override
  public void setEnabled(boolean status) {
   getJLabel().setEnabled(status);
   getSpinner().setEnabled(status);
   super.setEnabled(status);
  }
}
