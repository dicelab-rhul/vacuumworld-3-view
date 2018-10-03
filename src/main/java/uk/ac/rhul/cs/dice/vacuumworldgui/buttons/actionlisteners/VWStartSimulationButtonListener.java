package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameWindow;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWSwingWorker;

public class VWStartSimulationButtonListener extends VWAbstractButtonListener {
    private volatile VWState state;
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
	
	sendStateToModel();
	loop();
    }

    private void loop() {
	new VWSwingWorker(this).execute();
    }

    public void redrawGUI(JSONObject state) {
	VWState.reset(state);
	this.state = VWState.getInstance(state);
	LogUtils.log("View here: redrawing the grid...");
	this.gameWindow.reset(this.state, this.state.getGridSize());
	LogUtils.log("Done!");
    }

    public JSONObject waitForModel() {
	return VWGameProperties.getInstance().getManager().fetchUpdateFromModel();
    }

    private void sendStateToModel() {
	LogUtils.log("View here: sending initial state to the controller...");
	VWGameProperties.getInstance().getManager().sendStateToModel(this.state.serializeState());
	LogUtils.log("Done!");
    }
}