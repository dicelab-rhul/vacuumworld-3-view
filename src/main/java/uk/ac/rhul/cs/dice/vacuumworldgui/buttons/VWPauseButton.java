package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWPauseButtonListener;

public class VWPauseButton extends VWAbstractButton {

    public VWPauseButton(Component parent) {
	super("Pause", new VWPauseButtonListener(parent), parent);
	
	getActionListener().setPauseButton(this);
    }
    
    @Override
    public VWPauseButtonListener getActionListener() {
        return (VWPauseButtonListener) super.getActionListener();
    }
    
    public void setStopButtonRefecence(VWStopButton button) {
	getActionListener().setStopButton(button);
    }
    
    public void setSaveButtonListener(VWSaveButton button) {
	getActionListener().setSaveButton(button);
    }
}