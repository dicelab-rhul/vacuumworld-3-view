package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWSaveResultDialog;

public class VWSaveButtonListener extends VWAbstractButtonListener {
    private volatile VWState state;

    public VWSaveButtonListener(Component parent, VWState state) {
	super(parent);
	
	this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if(VWGameProperties.getInstance().isPaused() || !VWGameProperties.getInstance().hasTheSimulationBeenStarted()) {
	    saveState();
	}
	else {
	    LogUtils.log("You cannot save the game now. You need to pause the simulation before.");
	}
    }
    
    private void saveState() {
	if(VWState.getExistingInstance().isAValidInitialState()) {
	    String name = System.currentTimeMillis() + ".json";
		
	    LogUtils.log("Saving the current state to " + name + " ...");
		
	    VWState.getExistingInstance().saveState(name);
	    
	    popResultDialog(true, name);
	}
	else {
	    LogUtils.log("Not saving the state, as it is an illegal state.");
	    
	    popResultDialog(false, null);
	}
    }

    private void popResultDialog(boolean saved, String path) {
	VWSaveResultDialog dialog = new VWSaveResultDialog(getParent(), saved, path);
	dialog.createDialog();
    }

    public VWState getState() {
	return this.state;
    }
}