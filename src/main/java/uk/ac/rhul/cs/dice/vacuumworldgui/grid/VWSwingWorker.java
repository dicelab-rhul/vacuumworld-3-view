package uk.ac.rhul.cs.dice.vacuumworldgui.grid;

import javax.swing.SwingWorker;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWLoadButtonListener;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWStartSimulationButtonListener;

public class VWSwingWorker extends SwingWorker<Void, Void> {
    private VWStartSimulationButtonListener firstListener;
    private VWLoadButtonListener secondListener;
    
    
    public VWSwingWorker(VWStartSimulationButtonListener listener) {
	this.firstListener = listener;
    }
    
    public VWSwingWorker(VWLoadButtonListener listener) {
	this.secondListener = listener;
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
	while (true) {
	    LogUtils.log("View here: waiting for an update from the controller!");
	    JSONObject state = this.firstListener.waitForModel();
	    LogUtils.log("View here: received an update from the controller!");
	    this.firstListener.redrawGUI(state);
	    VWGameProperties.getInstance().getManager().sendAcknowledgementToModel();
	}
    }

    private void loopOnSecondListener() {
	while (true) {
	    LogUtils.log("View here: waiting for an update from the controller!");
	    JSONObject state = this.secondListener.waitForModel();
	    LogUtils.log("View here: received an update from the controller!");
	    this.secondListener.redrawGUI(state);
	    VWGameProperties.getInstance().getManager().sendAcknowledgementToModel();
	}
    }
}