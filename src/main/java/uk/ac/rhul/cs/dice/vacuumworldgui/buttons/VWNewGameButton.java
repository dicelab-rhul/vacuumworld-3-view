package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.GridBagConstraints;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWNewGameButtonListener;

public class VWNewGameButton extends VWAbstractButton {

    public VWNewGameButton(Component parent) {
	super("New", new VWNewGameButtonListener(parent), parent);
    }
    
    @Override
    public void createButton() {
	super.createButton();
	
	this.constraints.gridy = 0;
	this.constraints.anchor = GridBagConstraints.CENTER;
    }
}