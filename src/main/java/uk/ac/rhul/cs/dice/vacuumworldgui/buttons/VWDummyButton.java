package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWDummyButtonListener;

public class VWDummyButton extends VWAbstractButton {

    public VWDummyButton(String text, Component parent) {
	super(text, new VWDummyButtonListener(parent), parent);
    }
    
    @Override
    public void createButton() {
        super.createButton();
        
        getButton().setEnabled(false);
    }
}