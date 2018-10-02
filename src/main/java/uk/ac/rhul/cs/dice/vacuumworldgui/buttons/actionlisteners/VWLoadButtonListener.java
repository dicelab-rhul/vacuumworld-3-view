package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameWindow;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWLoadButtonListener extends VWAbstractButtonListener {
    private JFileChooser loader;
    private VWState state;
    private VWGameWindow gameWindow;
    
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
	    this.state = VWState.getInstance(path);
	    
	    System.out.println("Loaded " + path + ".");
	    
	    getParent().invalidate();
	    ((JFrame) getParent()).dispose();
	    this.gameWindow = new VWGameWindow(this.state, this.state.getGridSize());
	    
	    loop();
	}
	catch(Exception e) {
	    LogUtils.fakeLog(e);
	    System.out.println("Failed to load the state.");
	}
    }
    
    private void loop() {
	sendStateToModel();
	JSONObject state = waitForModel();
	redrawGUI(state);
    }

    private void redrawGUI(JSONObject state) {
	this.gameWindow.dispose();
	this.gameWindow = new VWGameWindow(this.state, this.state.getGridSize());
    }

    private JSONObject waitForModel() {
	return VWGameProperties.getInstance().getManager().fetchUpdateFromModel();
    }

    private void sendStateToModel() {
	VWGameProperties.getInstance().getManager().sendStateToModel(this.state.serializeState());
    }

    private int loadState() {
	this.loader = new JFileChooser();
	this.loader.setFileSelectionMode(JFileChooser.FILES_ONLY);
	this.loader.setDialogTitle("Please select a JSON savestate.");
	
	return this.loader.showOpenDialog(getParent());
    }
}