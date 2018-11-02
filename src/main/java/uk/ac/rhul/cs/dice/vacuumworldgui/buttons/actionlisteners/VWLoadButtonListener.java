package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VacuumWorldCheckedException;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameWindow;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWSwingWorker;

public class VWLoadButtonListener extends VWAbstractButtonListener {
    private JFileChooser loader;
    private VWGameWindow gameWindow;
    
    public VWLoadButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() instanceof JButton) {
	    LogUtils.log("Clicked on Load!!!");
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
	    LogUtils.log("No file selected.");
	}
    }

    private void attemptToLoadTheGame(File file) {
	try {
	    String path = file.getAbsolutePath();
	    VWState.reset();
	    VWState.getInstance(path);
	    
	    LogUtils.log("Loaded " + path + ".");
	    
	    getParent().invalidate();
	    ((JFrame) getParent()).dispose();
	    this.gameWindow = new VWGameWindow();
	    
	    sendStateToModel();
	    loop();
	}
	catch(Exception e) {
	    LogUtils.fakeLog(e);
	    LogUtils.log("Failed to load the state.");
	}
    }
    
    private void loop() {
	new VWSwingWorker(this).execute();
    }

    public void redrawGUI(JSONObject state) {
	VWState.reset(state);
	VWState.getInstance(state);
	LogUtils.log("View here: redrawing the grid...");
	this.gameWindow.reset(VWState.getExistingInstance().getGridSize());
	LogUtils.log("Done!");
    }

    public JSONObject waitForModel() throws VacuumWorldCheckedException {
	return VWGameProperties.getInstance().getManager().fetchUpdateFromModel();
    }

    private void sendStateToModel() {
	LogUtils.log("View here: sending initial state to the controller...");
	VWGameProperties.getInstance().getManager().sendStateToModel(VWState.getExistingInstance().serializeState());
	LogUtils.log("Done!");
    }

    private int loadState() {
	this.loader = new JFileChooser();
	this.loader.setFileSelectionMode(JFileChooser.FILES_ONLY);
	this.loader.setDialogTitle("Please select a JSON savestate.");
	
	return this.loader.showOpenDialog(getParent());
    }
}