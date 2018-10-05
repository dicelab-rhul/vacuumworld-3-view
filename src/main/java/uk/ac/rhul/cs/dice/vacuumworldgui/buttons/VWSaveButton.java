package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWSaveButtonListener;

public class VWSaveButton extends VWAbstractButton {

    public VWSaveButton(Component parent, VWState state) {
	super("Save", new VWSaveButtonListener(parent, state), parent);
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