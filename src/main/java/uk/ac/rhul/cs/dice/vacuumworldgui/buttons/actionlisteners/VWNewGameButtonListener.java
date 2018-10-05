package uk.ac.rhul.cs.dice.vacuumworldgui.buttons.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import uk.ac.rhul.cs.dice.vacuumworldgui.VWConstructGameWindow;

public class VWNewGameButtonListener extends VWAbstractButtonListener {
    
    public VWNewGameButtonListener(Component parent) {
	super(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(ActionEvent.ACTION_PERFORMED == e.getID()) {
	    System.out.println("Clicked on Start!!!");
	    createAndShowConstructGameWindow();
	}
    }

    private void createAndShowConstructGameWindow() {
	new VWConstructGameWindow();
	getParent().setVisible(false);
	getParent().invalidate();
	((JFrame) getParent()).dispose();
    }
}