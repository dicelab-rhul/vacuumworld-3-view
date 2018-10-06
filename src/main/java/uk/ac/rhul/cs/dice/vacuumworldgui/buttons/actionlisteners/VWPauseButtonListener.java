package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWGameProperties;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWPauseButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWSaveButton;
import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.VWStopButton;

public class VWPauseButtonListener extends VWAbstractButtonListener {
    public VWPauseButton pauseButton;
    public VWStopButton stopButton;
    public VWSaveButton saveButton;
    
    public VWPauseButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(VWGameProperties.getInstance().isPaused()) {
	    VWGameProperties.getInstance().resume();
	    this.pauseButton.getButton().setText("Pause");
	    this.saveButton.getButton().setEnabled(false);
	}
	else {
	    VWGameProperties.getInstance().pause();
	    this.pauseButton.getButton().setText("Resume");
	    this.saveButton.getButton().setEnabled(true);
	}
	
	this.pauseButton.getButton().revalidate();
	this.saveButton.getButton().revalidate();
    }
    
    public void setPauseButton(VWPauseButton pauseButton) {
	this.pauseButton = pauseButton;
    }
    
    public void setStopButton(VWStopButton stopButton) {
	this.stopButton = stopButton;
    }
    
    public void setSaveButton(VWSaveButton saveButton) {
	this.saveButton = saveButton;
    }
}