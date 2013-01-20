package controller.swt;

import gui.swt.MainComposite;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import base.Collage;
import base.CollageSettings;

public class MainController {
	
	private Collage collage;
	private SettingsPaneController settingsPaneController;
	private MainComposite mainComposite;
	
	public MainController(CollageSettings initialSettings) {
		this.collage = new Collage(initialSettings);
		
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Last.fm Collage");
		
		this.mainComposite = new MainComposite(shell);
		shell.setLayout(new FillLayout());
		
		this.mainComposite.getButtonPanel().getGenerateCollageButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Runnable runnable = new Runnable() {
					@Override public void run() {
						collage.generateCollage(new Runnable() {
							
							@Override
							public void run(){
								Display.getDefault().asyncExec(new Runnable() {
		
									@Override
									public void run() {
										settingsPaneController.getSettingsPane().setEnabled(true);
										mainComposite.getButtonPanel().setEnabled(true);
										Collage c1 = collage;
										Image convertedImage = new Image(Display.getDefault(), util.Image.convertAWTtoSWT(collage.getImage()));
										mainComposite.getImageCanvas().setImage(convertedImage);
										mainComposite.getImageCanvas().redraw();
									}
									
								});
							}
						});
					}
				};
				
				settingsPaneController.getSettingsPane().setEnabled(false);
				mainComposite.getButtonPanel().setEnabled(false);
				
				Thread thread = new Thread(runnable);
				thread.start();
			}
		});
		
		this.settingsPaneController = new SettingsPaneController(initialSettings, mainComposite.getSettingsPane());
		
		shell.open();
		// Create and check the event loop
		while (!shell.isDisposed()) {
		  if (!display.readAndDispatch())
		    display.sleep();
		}
		display.dispose(); 
	}

	public static void main(String[] args) {
		CollageSettings settings = new CollageSettings(args);
        new MainController(settings);
    }
}
