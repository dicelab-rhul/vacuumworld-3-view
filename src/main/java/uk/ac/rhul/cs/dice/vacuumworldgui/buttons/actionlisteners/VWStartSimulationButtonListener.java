package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import org.cloudstrife9999.logutilities.LogUtils;
import org.json.JSONObject;

import uk.ac.rhul.cs.dice.vacuumworld.vwcommon.VacuumWorldCheckedException;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameWindow;
import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.dialogs.VWIllegalStateDialog;
import uk.ac.rhul.cs.dice.vacuumworldgui.grid.VWSwingWorker;

public class VWStartSimulationButtonListener extends VWAbstractButtonListener {
    private VWGameWindow gameWindow;
    
    public VWStartSimulationButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(VWState.getExistingInstance().areThereAnyActors()) {
	    checkAndStartSimulation();
	}
	else {
	    LogUtils.log("Not starting: not enough actors!");
	    VWIllegalStateDialog dialog = new VWIllegalStateDialog(getParent(), "Not starting: not enough actors!");
	    dialog.createDialog();
	}
    }

    private void checkAndStartSimulation() {
	if(VWState.getExistingInstance().isAValidInitialState()) {
	    LogUtils.log("Starting simulation!");
	    startSimulation();
	}
	else {
	    LogUtils.log("Illegal initial state: not starting the simulation!");
	    VWIllegalStateDialog dialog = new VWIllegalStateDialog(getParent(), "Illegal initial state: not starting the simulation!");
	    dialog.createDialog();
	}
    }
    
    private void startSimulation() {
	this.gameWindow = new VWGameWindow();
	
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
	
	VWGameProperties.getInstance().setStarted();
	
	sendStateToModel();
	loop();
    }

    private void loop() {
	new VWSwingWorker(this).execute();
    }

    public void redrawGUI(JSONObject state) {
	VWState.reset(state);
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
}