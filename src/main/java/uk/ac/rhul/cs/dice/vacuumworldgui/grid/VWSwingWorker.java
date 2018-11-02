package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import java.awt.Component;

import javax.swing.SwingWorker;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VacuumWorldCheckedException;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWLoadButtonListener;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWStartSimulationButtonListener;
import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWFatalErrorDialog;

public class VWSwingWorker extends SwingWorker<Void, Void> {
    private VWStartSimulationButtonListener firstListener;
    private VWLoadButtonListener secondListener;
    private volatile boolean stop;
    
    public VWSwingWorker(VWStartSimulationButtonListener listener) {
	this.firstListener = listener;
	this.stop = false;
    }
    
    public VWSwingWorker(VWLoadButtonListener listener) {
	this.secondListener = listener;
	this.stop = false;
    }

    public void setStop() {
	this.stop = true;
    }
    
    public void removeStop() {
	this.stop = false;
    }
    
    @Override
    protected Void doInBackground() {
	if(this.firstListener != null) {
	    loopOnFirstListener();
	}
	else if(this.secondListener != null) {
	    loopOnSecondListener();
	}
	
	return null;
    }

    private void loopOnFirstListener() {
	try {
	    while (!this.stop) {
		LogUtils.log("View here: waiting for an update from the controller!");
		JSONObject state = this.firstListener.waitForModel();
		LogUtils.log("View here: received an update from the controller!");
		this.firstListener.redrawGUI(state);
		checkForPause();
		VWGameProperties.getInstance().getManager().sendAcknowledgementToModel();
	    }	    
	}
	catch(VacuumWorldCheckedException e) {
	    stopSystem();
	}
    }

    private void stopSystem() {
	LogUtils.log("View here: creating error dialog.");
	Component parent = this.firstListener == null ? this.secondListener.getParent() : this.firstListener.getParent();
	VWFatalErrorDialog dialog = new VWFatalErrorDialog(parent);
	
	dialog.createDialog();
    }

    private void checkForPause() {
	if(VWGameProperties.getInstance().isPaused()) {
	    LogUtils.log("The system is paused...");
	}
	
	while(VWGameProperties.getInstance().isPaused()) {
	    if(System.currentTimeMillis() % 1000000 == 0) {
		LogUtils.log("The system is still paused...");
	    }
	}
    }

    private void loopOnSecondListener() {
	try {
	    while (!this.stop) {
		LogUtils.log("View here: waiting for an update from the controller!");
		JSONObject state = this.secondListener.waitForModel();
		LogUtils.log("View here: received an update from the controller!");
		this.secondListener.redrawGUI(state);
		checkForPause();
		VWGameProperties.getInstance().getManager().sendAcknowledgementToModel();
	    }    
	}
	catch(VacuumWorldCheckedException e) {
	    stopSystem();
	}
    }
}