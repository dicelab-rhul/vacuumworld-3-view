package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWStopButtonListener;

public class VWStopButton extends VWAbstractButton {
    
    public VWStopButton(Component parent) {
	super("Stop", new VWStopButtonListener(parent), parent);
    }
    
    @Override
    public void createButton() {
	super.createButton();
	
	getButton().setMinimumSize(new Dimension(200, 40));
	getButton().setPreferredSize(new Dimension(200, 40));
	
	this.constraints.gridy = 0;
	this.constraints.anchor = GridBagConstraints.CENTER;
    }
}