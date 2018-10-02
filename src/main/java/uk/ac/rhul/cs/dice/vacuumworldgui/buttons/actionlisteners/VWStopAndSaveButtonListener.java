package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWStopAndSaveButtonListener extends VWAbstractButtonListener {
    private VWState state;
    
    public VWStopAndSaveButtonListener(Component parent, VWState state) {
	super(parent);
	
	this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println("Stopping the simulation...");
	
	saveState();
	VWGameProperties.getInstance().getManager().sendStopToModel();
	
	System.out.println("Done.");
	
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
    }
    
    private void saveState() {
	String name = System.currentTimeMillis() + ".json";
	
	System.out.println("Saving the current state to " + name + " ...");
	
	this.state.saveState(name);
    }

    public VWState getState() {
	return this.state;
    }
}