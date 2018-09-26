package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWLoadButtonListener;

public class VWLoadButton extends VWAbstractButton {

    public VWLoadButton(Component parent) {
	super("Load", new VWLoadButtonListener(parent), parent);
    }

    @Override
    public void createButton() {
	super.createButton();
	
	this.constraints.gridy = 1;
    }
}