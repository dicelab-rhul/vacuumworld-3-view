package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.GridBagConstraints;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWStartButtonListener;

public class VWStartButton extends VWAbstractButton {

    public VWStartButton(Component parent) {
	super("Start", new VWStartButtonListener(parent), parent);
    }
    
    @Override
    public void createButton() {
	super.createButton();
	
	this.constraints.gridy = 0;
	this.constraints.anchor = GridBagConstraints.CENTER;
    }
}