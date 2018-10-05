package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWPauseButton;

public class VWPauseButtonListener extends VWAbstractButtonListener {
    public VWPauseButton button;
    
    public VWPauseButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(VWGameProperties.getInstance().isPaused()) {
	    VWGameProperties.getInstance().resume();
	    this.button.getButton().setText("Pause");
	}
	else {
	    VWGameProperties.getInstance().pause();
	    this.button.getButton().setText("Resume");
	}
	
	this.button.getButton().revalidate();
    }
    
    public void setButton(VWPauseButton button) {
	this.button = button;
    }
}