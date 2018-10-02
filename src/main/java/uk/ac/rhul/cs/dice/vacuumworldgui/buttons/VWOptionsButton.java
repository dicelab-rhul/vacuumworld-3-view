package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWOptionsButtonListener;

public class VWOptionsButton extends VWAbstractButton {

    public VWOptionsButton(Component parent) {
	super("Options", new VWOptionsButtonListener(parent), parent);
    }
    
    @Override
    public void createButton() {
	super.createButton();
	
	this.constraints.gridy = 2;
    }
}