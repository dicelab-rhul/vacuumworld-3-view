package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameWindow;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWStartSimulationButtonListener extends VWAbstractButtonListener {
    private VWState state;
    
    public VWStartSimulationButtonListener(Component parent, VWState state) {
	super(parent);
	
	this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(this.state.areThereAnyActors()) {
	    System.out.println("Starting simulation!");
	    startSimulation();
	}
	else {
	    System.out.println("Not starting: not enough actors!");
	}
    }

    private void startSimulation() {
	new VWGameWindow(this.state, this.state.getGridSize());
	
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
	
	dumpInitialState();
	sendStateToModel();
    }

    private void dumpInitialState() {
	System.out.println(this.state.serializeState().toString(4));
    }

    private void sendStateToModel() {
	// TODO Auto-generated method stub
	
    }
}