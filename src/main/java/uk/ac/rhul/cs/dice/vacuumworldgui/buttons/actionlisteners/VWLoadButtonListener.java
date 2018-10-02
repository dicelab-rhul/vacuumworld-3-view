package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameWindow;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWLoadButtonListener extends VWAbstractButtonListener {
    private JFileChooser loader;
    
    public VWLoadButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() instanceof JButton) {
	    System.out.println("Clicked on Load!!!");
	    int val = loadState();
	    
	    loadGame(val);
	}
    }

    private void loadGame(int val) {
	if(val == JFileChooser.APPROVE_OPTION) {
	    File file = this.loader.getSelectedFile();
	    
	    this.loader.invalidate();
	    
	    attemptToLoadTheGame(file);
	}
	else {
	    System.out.println("No file selected.");
	}
    }

    private void attemptToLoadTheGame(File file) {
	try {
	    String path = file.getAbsolutePath();
	    VWState state = VWState.getInstance(path);
	    
	    getParent().invalidate();
	    ((JFrame) getParent()).dispose();
	    new VWGameWindow(state, state.getGridSize());
	}
	catch(Exception e) {
	    LogUtils.fakeLog(e);
	    System.out.println("Failed to load the state.");
	}
    }

    private int loadState() {
	this.loader = new JFileChooser();
	this.loader.setFileSelectionMode(JFileChooser.FILES_ONLY);
	this.loader.setDialogTitle("Please select a JSON savestate.");
	
	return this.loader.showOpenDialog(getParent());
    }
}