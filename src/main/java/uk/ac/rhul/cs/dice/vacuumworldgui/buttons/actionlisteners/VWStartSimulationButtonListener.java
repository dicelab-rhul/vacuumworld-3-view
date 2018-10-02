package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameWindow;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;

public class VWStartSimulationButtonListener extends VWAbstractButtonListener {
    private VWState state;
    private VWGameWindow gameWindow;
    
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
	this.gameWindow = new VWGameWindow(this.state, this.state.getGridSize());
	
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
	
	dumpInitialState();
	
	loop();
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

    private void dumpInitialState() {
	System.out.println(this.state.serializeState().toString(4));
    }

    private void sendStateToModel() {
	VWGameProperties.getInstance().getManager().sendStateToModel(this.state.serializeState());
    }
}