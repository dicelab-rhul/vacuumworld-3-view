package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWState;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWStartSimulationButtonListener;

public class VWStartSimulationButton extends VWAbstractButton {

    public VWStartSimulationButton(Component parent, VWState state) {
	super("GO!", new VWStartSimulationButtonListener(parent, state), parent);
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