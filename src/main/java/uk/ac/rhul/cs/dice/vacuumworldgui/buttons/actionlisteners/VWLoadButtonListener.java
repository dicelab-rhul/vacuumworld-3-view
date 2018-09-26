package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

public class VWLoadButtonListener extends VWAbstractButtonListener {

    public VWLoadButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(ActionEvent.ACTION_PERFORMED == e.getID()) {
	    System.out.println("Clicked on Load!!!");
	}
    }
}