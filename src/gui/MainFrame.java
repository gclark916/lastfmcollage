package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import base.LastfmCollage;
import base.TimePeriod;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	JRadioButton[] timePeriodButtons;
	JButton generateCollageButton;
	JLabeledTextField username;
	ControlPanel controlPanel;
	ImagePanel imagePanel;
	
	LastfmCollage lastfmCollage;
	
	public MainFrame(String username, int rowCount, int colCount, TimePeriod period, String key) {
		super();
		
		this.lastfmCollage = new LastfmCollage(username, rowCount, colCount, period, key);
		
		setTitle("Last.fm Collage");
		setSize(800,600);
		setLocation(10,200);
		Container content = getContentPane();
		
		controlPanel = new ControlPanel(this, lastfmCollage);
		content.add(controlPanel, BorderLayout.WEST);
		
		imagePanel = new ImagePanel(null);
		content.add(imagePanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand())
		{
		case "generate":
			lastfmCollage.generateCollage(imagePanel, new Runnable() {

				@Override
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							controlPanel.setEnabled(true);
						}
						
					});
				}
				
			});
			controlPanel.setEnabled(false);
			imagePanel.repaint();
            break;
		case "save":
			lastfmCollage.saveCollageToFile();
			break;
		}
    }
	
	public static void main(String[] args) {
		String username = "";
		int rowCount = 0;
		int colCount = 0;
		TimePeriod period = TimePeriod.DAY7;
		String key = "d048f1e12a1c2039e45d9b94d622bc1e";
		
		for (int argIndex = 0, argCount = args.length; argIndex < argCount; argIndex++)
		{
			String arg = args[argIndex];
			switch (arg)
			{
			case "-u":
				username = args[argIndex+1];
				break;
			case "-p":
				String periodArg = args[argIndex+1];
				switch (periodArg)
				{
				case "overall":
					period = TimePeriod.OVERALL;
					break;
					
				case "7days":
					period = TimePeriod.DAY7;
					break;
					
				case "1month":
					period = TimePeriod.MONTH1;
					break;
					
				case "3month":
					period = TimePeriod.MONTH3;
					break;
					
				case "6month":
					period = TimePeriod.MONTH6;
					break;
					
				case "12month":
					period = TimePeriod.MONTH12;
					break;
				}
				break;
			case "-r":
				rowCount = Integer.valueOf(args[argIndex+1]);
				break;
			case "-c":
				colCount = Integer.valueOf(args[argIndex+1]);
				break;
			case "-k":
				key = args[argIndex+1];
				break;
			}
		}

		if (username.isEmpty())
		{
			System.err.print("No username given");
			System.exit(-1);
		}
		
		if (rowCount <= 0)
		{
			if (colCount <= 0)
			{
				rowCount = 3;
				colCount = 3;
			}
			
			rowCount = colCount;
		}
		else
		{
			if (colCount <= 0)
				colCount = rowCount;
		}
		
		MainFrame mainFrame = new MainFrame(username, colCount, colCount, period, key);
		mainFrame.setVisible(true);
	}
}
