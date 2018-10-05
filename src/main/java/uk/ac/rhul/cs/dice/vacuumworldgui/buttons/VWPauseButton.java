package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWPauseButtonListener;

public class VWPauseButton extends VWAbstractButton {

    public VWPauseButton(Component parent) {
	super("Pause", new VWPauseButtonListener(parent), parent);
	
	getActionListener().setButton(this);
    }
    
    @Override
    public VWPauseButtonListener getActionListener() {
        return (VWPauseButtonListener) super.getActionListener();
    }
}