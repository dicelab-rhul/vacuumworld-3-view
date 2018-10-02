package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.GridBagConstraints;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWStopAndSaveButtonListener;

public class VWStopAndSaveButton extends VWAbstractButton {
    
    public VWStopAndSaveButton(Component parent, VWState state) {
	super("Stop & Save", new VWStopAndSaveButtonListener(parent, state), parent);
    }
    
    @Override
    public void createButton() {
	super.createButton();
	
	this.constraints.gridy = 0;
	this.constraints.anchor = GridBagConstraints.CENTER;
    }
}