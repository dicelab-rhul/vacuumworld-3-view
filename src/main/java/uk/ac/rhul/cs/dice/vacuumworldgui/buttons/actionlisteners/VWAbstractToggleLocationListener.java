package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.MouseListener;

public abstract class VWAbstractToggleLocationListener implements MouseListener {
    private Component parent;
    
    public VWAbstractToggleLocationListener(Component parent) {
	this.parent = parent;
    }
    
    public Component getParent() {
	return this.parent;
    }
}