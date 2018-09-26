package uk.ac.rhul.cs.dice.vacuumworldgui.buttons;

import java.awt.Component;

import uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners.VWCreditsButtonListener;

public class VWCreditsButton extends VWAbstractButton {

    public VWCreditsButton(Component parent) {
	super("Credits", new VWCreditsButtonListener(parent), parent);
    }
    
    @Override
    public void createButton() {
        super.createButton();
        
        this.constraints.gridy = 2;
    }
}