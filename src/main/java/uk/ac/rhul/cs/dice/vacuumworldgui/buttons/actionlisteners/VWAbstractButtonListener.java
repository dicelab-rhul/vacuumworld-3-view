package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionListener;

public abstract class VWAbstractButtonListener implements ActionListener {
    private Component parent;
    
    public VWAbstractButtonListener(Component parent) {
	this.parent = parent;
    }
    
    public Component getParent() {
	return this.parent;
    }
}