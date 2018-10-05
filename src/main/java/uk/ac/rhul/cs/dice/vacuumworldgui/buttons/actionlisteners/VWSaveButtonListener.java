package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import org.cloudstrife9999.logutilities.LogUtils;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWSaveButtonListener extends VWAbstractButtonListener {
    private volatile VWState state;

    public VWSaveButtonListener(Component parent, VWState state) {
	super(parent);
	
	this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if(VWGameProperties.getInstance().isPaused()) {
	    saveState();
	}
	else {
	    LogUtils.log("You cannot save the game now. You need to pause the simulation before.");
	}
    }
    
    private void saveState() {
	String name = System.currentTimeMillis() + ".json";
	
	System.out.println("Saving the current state to " + name + " ...");
	
	VWState.getExistingInstance().saveState(name);
    }

    public VWState getState() {
	return this.state;
    }
}