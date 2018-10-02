package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWStopAndSaveButtonListener extends VWAbstractButtonListener {
    private VWState state;
    
    public VWStopAndSaveButtonListener(Component parent, VWState state) {
	super(parent);
	
	this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	System.out.println("Stopping the simulation");
	
	saveState();
	
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
    }
    
    private void saveState() {
	// TODO Auto-generated method stub
	
    }

    public VWState getState() {
	return this.state;
    }
}